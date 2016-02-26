using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdk_Request.Logical
{
    /// <summary>
    /// API请求常用错误信息
    /// </summary>
    public enum API_ERROR
    {
        CONFIG_ERROR = -2,
        UNKONW_ERROR = 1010,
        OK = 1011,
        TRONE_NOT_FOUND,
        TRONE_FEE_NOT_FOUND,
        /// <summary>
        /// 必须字段为空
        /// </summary>
        FIELD_MISS,
        /// <summary>
        /// 内部错误，通常指内容程序出错了
        /// </summary>
        INNER_ERROR,
        /// <summary>
        /// API 配置错误
        /// </summary>
        INNER_CONFIG_ERROR,
        /// <summary>
        /// 取指令失败
        /// </summary>
        GET_CMD_FAIL,
        /// <summary>
        /// 网关超时
        /// </summary>
        GATEWAY_TIMEOUT,
        /// <summary>
        /// 计费点错误（通道指没有该金额）
        /// </summary>
        ERROR_PAY_POINT,
        /// <summary>
        /// 未知格式数据（可能是SP服务出错或SP变更了输出格式)
        /// </summary>
        UNKONW_RESULT
        

    }
}
