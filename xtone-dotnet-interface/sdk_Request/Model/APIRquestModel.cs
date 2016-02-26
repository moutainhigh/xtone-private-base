using sdk_Request.Logical;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace sdk_Request.Model
{
    [DataContract]
    public class APIRquestModel
    {
        /// <summary>
        /// 传给SP的透传内容
        /// </summary>
        [DataMember]
        public String apiExdata;

        /// <summary>
        /// tbl_sp_trone_api
        /// </summary>
        [DataMember(Name = "apiOrderId")]
        public String tbl_sp_trone_api_id;
        [DataMember]
        public int cid;
        [DataMember]
        public String clientIp;
        [DataMember]
        public String cpVerifyCode;
        /// <summary>
        /// 渠道的透传内容
        /// </summary>
        [DataMember]
        public String extrData;
        [DataMember]
        public int id;
        [DataMember]
        public String imei;
        [DataMember]
        public String imsi;
        [DataMember]
        public String ip;
        [DataMember]
        public int isHidden;
        [DataMember]
        public int lac;
        [DataMember]
        public String mobile;
        [DataMember]
        public String msg;
        [DataMember]
        public String netType;
        [DataMember]
        public String packageName;
        [DataMember]
        public String port;
        [DataMember]
        public int price;
        [DataMember]
        public String sdkVersion;
        [DataMember]
        public String spExField;
        [DataMember]
        public String spLinkId;
        [DataMember]
        public int troneId;
        [DataMember]
        public API_ERROR status;
        [DataMember(Name = "troneOrderId")]
        public String tbl_trone_order_id;
        /// <summary>
        /// 预留的SP需求字段，可根据业务要求渠道进行传参
        /// </summary>
        public String extraParams;
    }


}
