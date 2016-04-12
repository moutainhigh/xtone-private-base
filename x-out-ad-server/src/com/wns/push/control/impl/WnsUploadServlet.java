package com.wns.push.control.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;
import org.common.util.ConfigManager;

import com.wns.push.admin.bean.WnsApp;
import com.wns.push.bean.ApkItem;
import com.wns.push.bean.LibItem;
import com.wns.push.dao.ApkDao;
import com.wns.push.dao.LibDao;
import com.wns2.base.bean.ResponseBean;
import com.wns2.base.bean.TransmissionUtil;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.factory.WnsFileFactory;
import com.wns2.util.WnsApkUtil;
import com.wns2.util.WnsCrc32;
import com.wns2.util.WnsSpringHelper;
import com.wns2.util.WnsUtil;

public class WnsUploadServlet extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(WnsUploadServlet.class);
  private static final long serialVersionUID = 7630300784693118565L;

  @SuppressWarnings("unchecked")
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    LOG.debug("-------WnsUploadServlet.doPost------");
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);

    if (isMultipart) {
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);

      WnsSysProperty sysProperty = (WnsSysProperty) WnsSpringHelper.getBean("bSysProperty");

      String name = null;
      String pkgName = null;
      String sign = null;
      String version = null;
      String channel = null;
      String phonetype = null;
      int versionCode = 0;
      String crc32 = null;
      int size = 0;
      String url = null;
      boolean isLib = false;

      String retjson = "";

      Iterator items;
      try {
        items = upload.parseRequest(request).iterator();
        while (items.hasNext()) {
          FileItem item = (FileItem) items.next();
          if (!item.isFormField()) {
            name = item.getName();
            size = (int) item.getSize();
            String[] ret = WnsFileFactory.addFile(name, item.getSize());
            File uploadedFile = new File(ret[0]);
            item.write(uploadedFile);

            if (name.endsWith(".apk")) {
              WnsApkUtil apkUtil = new WnsApkUtil();
              WnsApp app = apkUtil.parseApk(ret[0]);
              version = app.getVersionname();
              versionCode = Integer.parseInt(app.getVersion());
              pkgName = app.getPkgname();
              // sign = app.getSign();

              if (url == null) {
                url = sysProperty.getResPath() + "/upload/" + ret[1];
              }
              isLib = false;
            } else if (name.endsWith(".jar")) {
              isLib = true;
              crc32 = String.valueOf(WnsCrc32.ngsteamCrcCalcFile(ret[0]));
              if (url == null) {
                url = sysProperty.getResPath() + "/upload/" + ret[1];
              }
            }

          } else {
            String key = item.getFieldName();// text1
            if ("channel".equals(key)) {
              channel = item.getString();
            } else if ("version".equals(key)) {
              version = item.getString();
              versionCode = WnsUtil.parseVersion(version);
            } else if ("phonetype".equals(key)) {
              phonetype = item.getString();
            } else if ("url".equals(key)) {
              url = item.getString();
              if (!url.startsWith("http://")) {
                url = null;
              }
            }
            // String value = item.getString();
          }
        }

        if (isLib) {
          LibDao dao = (LibDao) WnsSpringHelper.getBean("dlibDao");
          LibItem item = dao.findByNameAndChannel(name, channel);
          if (item == null) {
            retjson = "{'result':0, 'error':'库中没有对应jar包'}";
          } else if (item.getVersioncode() >= versionCode) {
            retjson = "{'result':0, 'error':'库中已有更高版本的jar包'}";
          } else {
            item = new LibItem();
            item.setChannel(channel);
            item.setName(name);
            item.setPhonetype(phonetype);
            item.setVersion(version);
            item.setVersioncode(versionCode);
            item.setCrc32(crc32);
            item.setUrl(url);
            dao.insert(item);
            retjson = "{'result':1, 'error':'', 'msg':'更新jar包成功'}";
          }
        } else {
          ApkDao dao = (ApkDao) WnsSpringHelper.getBean("dapkDao");
          ApkItem item = dao.findByPkgNameAndChannel(pkgName, channel);
          if (item == null) {
            retjson = "{'result':0, 'error':'库中没有对应apk包'}";
          } else if (item.getVersioncode() >= versionCode) {
            retjson = "{'result':0, 'error':'库中已有更高版本的apk包'}";
          } else {
            item = new ApkItem();
            item.setChannel(channel);
            item.setName(name);
            item.setPhonetype(phonetype);
            item.setVersion(version);
            item.setVersioncode(versionCode);
            item.setUrl(url);
            item.setSize(size);
            item.setPkgname(pkgName);
            item.setSign(sign);

            dao.insert(item);
            retjson = "{'result':1, 'error':'', 'msg':'更新apk包成功'}";
          }
        }
        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(retjson);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");

    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    LOG.debug("----------upload service------->" + isMultipart);
    if (isMultipart) {

      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      Iterator items;
      try {
        items = upload.parseRequest(request).iterator();
        while (items.hasNext()) {
          FileItem item = (FileItem) items.next();
          LOG.debug("item.name====>" + item.getName());

          if (!item.isFormField()) {
            // String saveDir = "/data/server/tomcatA/webapps/game/yh";
            String saveDir = ConfigManager.getConfigData("save.path");
            LOG.debug("saveDir====>" + saveDir);
            if (!(new File(saveDir).isDirectory())) {
              new File(saveDir).mkdir();
            }
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
            String date = dateFormat.format(now);

            String extentionname = getFileExtentionName(item.getName());
            LOG.debug("extentionname====>" + extentionname);
            if (extentionname.equals("apk")) {

              String newFileName = date + ".apk";
              String path = saveDir + File.separatorChar + newFileName;
              File uploaderFile = new File(path);
              item.write(uploaderFile);

              /* 输出返回结果 */
              OutputStream out = null;
              String json = null;
              try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html");
                out = response.getOutputStream();
                ResponseBean bean = new ResponseBean(0);
                bean.add("apkurl", "http://cdn.kongdr.com/game/yh/" + newFileName);
                json = TransmissionUtil.responseToJson(bean);
                LOG.debug("json=>" + json);
                out.write(json.getBytes("UTF-8"));
              } catch (Exception e) {
                e.printStackTrace();
              } finally {
                if (out != null) {
                  out.close();
                }
              }
            } else if (extentionname.equals("jpg") || extentionname.equals("png")) {
              String newFileName = date + "." + extentionname;
              String path = saveDir + File.separatorChar + newFileName;
              File uploaderFile = new File(path);
              item.write(uploaderFile);

              /* 输出返回结果 */
              OutputStream out = null;
              String json = null;
              try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                out = response.getOutputStream();
                ResponseBean bean = new ResponseBean(0);
                bean.add("imageurl", "http://cdn.kongdr.com/game/yh/" + newFileName);
                json = TransmissionUtil.responseToJson(bean);
                LOG.debug("json=>" + json);
                out.write(json.getBytes("UTF-8"));
              } catch (Exception e) {
                e.printStackTrace();
              } finally {
                if (out != null) {
                  out.close();
                }
              }
            }

          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return;
      }
      response.setStatus(HttpServletResponse.SC_OK);

    }
  }

  public static void post(File targetFile) {
    String targetURL = null;// TODO 指定URL
    targetURL = "http://localhost:8080/AdUpaload/upload"; // servleturl
    PostMethod filePost = new PostMethod(targetURL);

    try {

      // 通过以下方法可以模拟页面参数提交
      // filePost.setParameter("name", "中文");
      // filePost.setParameter("pass", "1234");

      Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
      filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
      HttpClient client = new HttpClient();
      client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
      int status = client.executeMethod(filePost);
      if (status == HttpStatus.SC_OK) {
        LOG.info("上传成功");
        // 上传成功
      } else {
        LOG.info("上传失败");
        // 上传失败
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      filePost.releaseConnection();
    }
  }

  public static String getFileExtentionName(String fileName) {
    String name = "";
    String extention = "";
    if (fileName.length() > 0 && fileName != null) { // --截取文件名
      int i = fileName.lastIndexOf(".");
      if (i > -1 && i < fileName.length()) {
        name = fileName.substring(0, i); // --文件名
        extention = fileName.substring(i + 1); // --扩展名
      }
    }
    return extention;
  }
}