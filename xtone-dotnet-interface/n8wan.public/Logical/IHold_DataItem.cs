using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace n8wan.Public.Logical
{
    public interface IHold_DataItem
    {
        /// <summary>
        /// 扣量比例
        /// </summary>
        int hold_percent { get; }
        /// <summary>
        /// 单日最大同步金额
        /// </summary>
        decimal hold_amount { get; }
        /// <summary>
        /// 单日已经同步金额
        /// </summary>
        decimal amount { get; }
        /// <summary>
        /// 扣量周期，已经处理多少条数据
        /// </summary>
        int hold_CycCount { get; set; }
        /// <summary>
        /// 扣量周期，已经扣除量
        /// </summary>
        int hold_CycProc { get; set; }
    }
}
