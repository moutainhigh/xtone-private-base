using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

namespace Shotgun.Library
{
    public class SimpleLogRecord
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="logFile"></param>
        /// <param name="msg"></param>
        public static void WriteLog(string logFile, string msg)
        {
            if (string.IsNullOrEmpty(logFile))
                return;
            FileInfo fi;
            if (logFile.Length > 2 && logFile.Substring(1, 1) == ":")
            {
                fi = new FileInfo(logFile);
            }
            else
            {
                logFile += "_" + DateTime.Today.ToString("yyyy-MM-dd") + ".log";
                fi = new FileInfo(AppDomain.CurrentDomain.BaseDirectory + "\\Log\\" + logFile);
            }
            try
            {
                DirectoryInfo di = fi.Directory;
                if (!di.Exists)
                    di.Create();
            }
            catch { return; }
            StreamWriter sWrite = null;
            try
            {
                sWrite = new StreamWriter(fi.FullName, true);
                sWrite.WriteLine("{0},{1}", DateTime.Now.ToString("HH:mm:ss"), msg);
                sWrite.Flush();
            }
            catch { return; }
            finally
            {
                if (sWrite != null)
                {
                    try
                    {
                        sWrite.Close();
                        sWrite.Dispose();
                    }
                    catch { }
                    sWrite = null;
                }
            }



        }

        public static void WriteLog(string msg)
        {
            WriteLog(null, msg);
        }
    }
}
