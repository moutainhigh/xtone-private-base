using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LightDataModel
{
    partial class tbl_phone_locateItem
    {
        public static tbl_cityItem GetRowByMobile(Shotgun.Database.IBaseDataClass2 dBase, int spNum)
        {
            var q = GetQueries(dBase);
            q.Filter.AndFilters.Add(Fields.phone, spNum.ToString());
            var m = q.GetRowByFilters();
            if (m == null)
                return null;
            return tbl_cityItem.GetRowById(dBase, m.city_id);
        }

    }
}
