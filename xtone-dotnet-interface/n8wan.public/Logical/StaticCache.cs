using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace n8wan.Public.Logical
{
    public enum Static_Cache_Staus
    {
        Idel,
        Loading,
        AllLoad
    }

    /// <summary>
    /// 静态变量缓存-带过期时间
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <typeparam name="IDX"></typeparam>
    public class StaticCache<T, IDX> where T : Shotgun.Model.Logical.LightDataModel, new()
    {
        Dictionary<IDX, T> _data;
        static List<StaticCache<T, IDX>> _allCache;

        /// <summary>
        /// 数据加载状态
        /// </summary>
        Static_Cache_Staus _satus;
        /// <summary>
        /// 数据实际过期时间
        /// </summary>
        DateTime _expired;
        private string _tabName;
        private string _idField;
        /// <summary>
        /// 缓存内存主索引，默认使用数据库主键
        /// </summary>
        private string _indexField;


        public StaticCache()
            : this(null)
        {
        }

        /// <summary>
        /// 创建一个指主键的索引的缓存器
        /// </summary>
        /// <param name="IdxField">缓存内存主索引，默认使用数据库主键，注意数据索引键值的唯一性</param>
        public StaticCache(string IdxField)
        {
            this.Expired = new TimeSpan(24, 24, 24);
            T m = new T();
            this._idField = m.IdentifyField;
            this._tabName = m.TableName;
            if (string.IsNullOrEmpty(IdxField))
                this._indexField = this._idField;
            else
                this._indexField = IdxField;
        }

        /// <summary>
        /// 数据缓存有效期时长
        /// </summary>
        public TimeSpan Expired { get; set; }

        public String TableName { get { return _tabName; } }
        public String IdField { get { return _idField; } }
        public Static_Cache_Staus Status { get { return _satus; } }

        private void LoadFreshData()
        {

            if (_satus != Static_Cache_Staus.Idel)
                return;
            lock (this)
            {
                if (_satus != Static_Cache_Staus.Idel)
                    return;
                _satus = Static_Cache_Staus.Loading;
                ThreadPool.QueueUserWorkItem(this.LoadData);
            }
        }

        void LoadData(object s)
        {
            int minId = 0;
            if (_data == null)
                _data = new Dictionary<IDX, T>();
            else if (_data.Count > 0)
                minId = _data.Values.Min(e => (int)e[_idField]);

            var q = new Shotgun.Model.List.LightDataQueries<T>(_tabName, _idField);
            if (minId > 0)
                q.Filter.AndFilters.Add(_idField, minId, Shotgun.Model.Filter.EM_DataFiler_Operator.More);
            q.SortKey.Add(_idField, Shotgun.Model.Filter.EM_SortKeyWord.asc);
            q.PageSize = 1000;
            q.dBase = CreatDBase();
            System.Diagnostics.Stopwatch st = new System.Diagnostics.Stopwatch();
            st.Start();
            if (_allCache == null)
                _allCache = new List<StaticCache<T, IDX>>();
            if (!_allCache.Contains(this))
                _allCache.Add(this);
            try
            {
                var RowCount = q.TotalCount;
                var PageCount = RowCount / q.PageSize + (RowCount % q.PageSize == 0 ? 0 : 1);
                for (var i = 1; i <= PageCount; i++)
                {
                    q.CurrentPage = i;
                    var items = q.GetDataList();
                    items.ForEach(e => _data[(IDX)e[_indexField]] = e);
                }
                this._expired = DateTime.Now.Add(this.Expired);
                this._satus = Static_Cache_Staus.AllLoad;

            }
            catch (System.Data.DataException ex)
            {
                WriteLog(ex.Message);
                _satus = Static_Cache_Staus.Idel;
                this._expired = DateTime.Now.Add(this.Expired);
            }
            finally
            {
                IDisposable dp = (IDisposable)q.dBase;
                if (dp != null)
                    dp.Dispose();
            }
            WriteLog(true, (int)st.ElapsedMilliseconds, _data.Count);

        }

        /// <summary>
        /// 检查数据是否过期，如果过期激发重新加载，并丢弃过期数据
        /// </summary>
        private Dictionary<IDX, T> CheckExpried()
        {
            if (_data == null)
            {
                LoadFreshData();
                return null;
            }
            if (_satus != Static_Cache_Staus.AllLoad)
                return null;
            if (DateTime.Now > _expired)
            {
                ClearCache();
                _satus = Static_Cache_Staus.Idel;
                WriteLog(false, 0, 0);
                LoadFreshData();
                return null;
            }
            return _data;

        }

        public IEnumerable<T> GetCacheData(bool iFull)
        {
            var tData = CheckExpried();
            if (tData == null)
                return null;
            if (iFull && _satus != Static_Cache_Staus.AllLoad)
                return null;
            return tData.Values;
        }

        /// <summary>
        /// 根据主索引，快速查找数据 （非完整数据）
        /// </summary>
        /// <param name="idx"></param>
        /// <returns></returns>
        public T GetDataByIdx(IDX idx)
        {
            if (_data == null)
                return null;
            var tData = CheckExpried();

            if (tData == null || !tData.ContainsKey(idx))
                return null;

            return tData[idx];
        }

        /// <summary>
        /// 找查数据（非完整数据）
        /// </summary>
        /// <param name="func"></param>
        /// <returns></returns>
        public T FindFirstData(Func<T, bool> func)
        {
            var tData = CheckExpried();
            if (tData == null)
                return null;
            try
            {
                return tData.Values.First(func);
            }
            catch
            {
                return null;
            }

        }

        private Shotgun.Database.IBaseDataClass2 CreatDBase()
        {
            return new Shotgun.Database.DBDriver().CreateDBase();
        }

        /// <summary>
        /// 从外部插入单条数据，通常发生在，数据缓存成功之后，新增的数据
        /// 注意Fields要全部读取的，否则在二次取出使用时可能出现问题
        /// </summary>
        /// <param name="data"></param>
        public void InsertItem(T data)
        {
            if (data == null)
                return;
            var dt = CheckExpried();
            if (dt == null || _satus != Static_Cache_Staus.AllLoad)
                return;
            dt[(IDX)data[this._indexField]] = data;
            WriteLog(true, 0, 1);
        }

        void WriteLog(bool iAdd, int elapsedMs, int count)
        {
            string msg = string.Format("{0} cache, count {1}, elapsed {2}ms",
                (iAdd ? "add" : "remove"), count, elapsedMs);
            WriteLog(msg);
        }

        void WriteLog(string msg)
        {
            Shotgun.Library.SimpleLogRecord.WriteLog("static_cache", _tabName + " " + msg);
        }

        /// <summary>
        /// 清除缓存数据
        /// </summary>
        public void ClearCache()
        {
            _data = null;
            if (_allCache == null)
                return;
            _allCache.Remove(this);
        }

        public static void ClearAllCache()
        {
            if (_allCache == null)
                return;
            StaticCache<T, IDX>[] all = _allCache.ToArray();

            foreach (var c in all)
            {
                c.ClearCache();
            }
        }
    }
}
