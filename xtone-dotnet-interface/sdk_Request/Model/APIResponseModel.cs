using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Threading.Tasks;

namespace sdk_Request.Model
{

    /// <summary>
    /// 用户于响应API请求的对像
    /// </summary>
    [DataContract]
    public class APIResponseModel
    {

        private SP_RESULT _cmd;
        public APIResponseModel(SP_RESULT cmd, APIRquestModel request)
        {
            this._cmd = cmd;
            this.Request = request;
            if (cmd == null)
                return;
            request.status = (cmd.status == Logical.API_ERROR.OK) ? Logical.API_ERROR.OK : Logical.API_ERROR.UNKONW_ERROR;
        }

        [DataMember]
        public sdk_Request.Model.APIRquestModel Request { get; set; }

        [DataMember]
        public SP_RESULT Command
        {
            get
            {
                return this._cmd;
            }
            private set { }
        }

        public string ToJson()
        {
           return JsonConvert.SerializeObject(this);
        }

    }
}
