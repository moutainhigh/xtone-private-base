package com.epplus.face;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.core_sur.interfaces.EPEngine;
import com.epplus.bean.Bdata;
import com.epplus.bean.DexBean;
import com.epplus.bean.EncodeUtils;
import com.epplus.bean.PackageData;
import com.epplus.bean.VersionData;
import com.epplus.publics.EPPayHelper;
import com.epplus.utils.LLog;
import com.epplus.utils.Util;

import dalvik.system.DexClassLoader;

public class EPPlusPayService extends Service {
	private SharedPreferences sp;
	// String versionUrl = "http://121.40.16.65:83/GetSdkUpdate.aspx";
	// String versionUrl = new Bdata().guu(true);//65
	// String versionUrl = new Bdata().guu(false);//225
	//http://dx.n8wan.com/
//	String versionUrl = "http://192.168.1.210:8080/xtone-interface-package-manager/r";
//	String resultUrl = "http://192.168.1.210:8080/xtone-interface-package-manager/d";
	String versionUrl = new Bdata().gver();//"http://dx.n8wan.com/r";
	String resultUrl = new Bdata().gresult();//"http://dx.n8wan.com/d";
	
	
	
	
	
	private int type = 1000;
	private boolean isChecklog = false;
	List<VersionData> versionDataList = new ArrayList<VersionData>(); // �������˷��ص�����
	List<PackageData> packageDataList = new ArrayList<PackageData>(); // ����http�����jar���������б�(����+�汾��)
	List<PackageData> uploadDataList = new ArrayList<PackageData>(); // �汾���ظ��³ɹ���jar���������б�(����+�汾��)

	PackageData packageData = new PackageData();
	File file = null;
	private int initCount = 0; // ��ʼ���Ĵ��������ʧ���������ʼ����5�κ��ٳ�ʼ����
	private String jsonParamStr, jsonUploadDataParamStr, jsonParamEncodeStr;

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	public EPEngine getEngine(VersionData versionData) {
		// ���ݲ�Ϊ�յ�ʱ��
		if (versionData != null) {
			LLog.log("versionData != null");
			File lxDexFile = new File(getLXDexFilePath(versionData.getSectionDexPathName()));
			LLog.log("lxDexFile.exists():" + lxDexFile.exists());
			if (lxDexFile.exists()) {
				EPEngine computeEngineInteface = execDex(getLXDexFilePath(versionData.getSectionDexPathName()),
						versionData.getCoreClassName());
				LLog.log("���Է���Dex�汾Engine");
				if (computeEngineInteface != null) {
					LLog.log("�ɹ�����Dex�汾Engine");
					return computeEngineInteface;
				}
			}
			LLog.log("���ر��ذ汾Engine");
			return execDex(
					copyAssetsToPath(versionData.getXmlName(), getLXDexFilePath(versionData.getSectionDexPathName())),
					versionData.getCoreClassName());
		} else { // ����Ϊ�յ�ʱ��
			LLog.log("versionData == null");
			File lxDexFile = new File(getLXDexFilePath(null));
			LLog.log("111---lxDexFile.exists():" + lxDexFile.exists());
			if (lxDexFile.exists()) {
				EPEngine computeEngineInteface = execDex(getLXDexFilePath(null), "com.core_sur.publics.EPCoreManager");
				LLog.log("111---���Է���Dex�汾Engine");
				if (computeEngineInteface != null) {
					LLog.log("111---�ɹ�����Dex�汾Engine");
					return computeEngineInteface;
				}
			}
			LLog.log("111---���ر��ذ汾Engine");
			return execDex(copyAssetsToPath("ep", getLXDexFilePath(null)), "com.core_sur.publics.EPCoreManager");
		}
	}

	private String copyAssetsToPath(String jarName, String lxDexFilePath) {
		LLog.log("come in copyAssetsToPath");
		/*if (lxDexFilePath != null) {
			try {
				Util.copyFile(getAssets().open("ep/" + jarName + ".jar"), new File(lxDexFilePath).getAbsolutePath());
				return lxDexFilePath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		return null;
	}

	private void init(VersionData versionData) {

		if (versionData == null) {
			return;
		}

		//LLog.error("come in versionData.getInitFlag()==0");
		EPEngine engine = getEngine(versionData);
		if (engine == null) {
			LLog.log("come in engine==null");
			initCount++;
			if (initCount < 5) {
				initCheckVersion();
			}
			return;
		}
		engine.init(type, this, isChecklog);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null && intent.getExtras() != null) {
			int type = intent.getExtras().getInt("type");
			switch (type) {
			case 1000:
				isChecklog = intent.getExtras().getBoolean("isChecklog");
				break;
			default:
				break;
			}
		}
		return super.onStartCommand(intent, START_STICKY, startId);
	}

	@SuppressLint("NewApi")
	private EPEngine execDex(String path, String coreClassName) {
		LLog.log("execDex---path:" + path + ",,,coreClassName:" + coreClassName);
		if (path == null) {
			return null;
		}

		try {
			DexClassLoader cl = new DexClassLoader(path, getLXDexPath(), null, getClassLoader());
			Class<?> c = cl.loadClass(coreClassName);
			Method m = c.getMethod("getInstance");
			EPEngine cEngineInteface = (EPEngine) m.invoke(c);
			return cEngineInteface;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LLog.log("EPPluspayService---onCreate");
		File data = getApplication().getFilesDir();
		LLog.log("oncreate---data:" + data.getAbsolutePath());
		file = new File(data.getAbsolutePath() + "/ep.txt");
		if (!file.exists()) {
			LLog.log("file not exists");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			LLog.log("file exists");
		}
		initCheckVersion();
	}

	public void initCheckVersion() {
		packageDataList.clear();
		String resultStr = Util.getInputString(file);
		// data/data/files�µ�ep.txt��Ϊ������if��֧����֮��else��֧
		if (resultStr.length() > 0 && !resultStr.isEmpty()) {
			LLog.log("come in resultStr.length() > 0");
			String jarData[] = resultStr.split(",");
			for (int i = 0; i < jarData.length; i++) {
				int splitPos = jarData[i].indexOf(':');
				// �������ƺͰ汾��
				packageDataList
						.add(new PackageData(jarData[i].substring(0, splitPos), jarData[i].substring(splitPos + 1)));
			}
		} else {
			LLog.log("come in resultStr.length() == 0");
			// �������ƺͰ汾��(Ĭ�ϵ���ep �����޸�)
			packageDataList.add(new PackageData("ep", "0.0"));
		}

		LLog.log("packageDataList.size()=" + packageDataList.size());

		DexBean dexBean = new DexBean();
		dexBean = Util.getDexBean(this, packageDataList);// �õ�DexBeanʵ��
		jsonParamStr = Util.parseObjectToJsonString(dexBean, packageDataList);// ��Dexbean����ת��Ϊjson
																				// String
		LLog.log("jsonParamStr:" + jsonParamStr);
		jsonParamEncodeStr = EncodeUtils.encode(jsonParamStr);
		LLog.log("jsonParamEncodeStr:" + jsonParamEncodeStr);
		CheckVersionTask checkVersionTask = new CheckVersionTask();
		checkVersionTask.execute(); // �÷���ֻ����UI�߳�ʹ�á�
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		initCount = 0; // ��ʼ��������Ϊ0
		Intent localIntent = new Intent();
		localIntent.setClass(this, this.getClass());
		this.startService(localIntent);
	}

	public String getLXDexFilePath(String sectionDexPathName) {
		String cache = Util.getDirCache(getApplication()).getAbsolutePath();
		if (sectionDexPathName == null) {
			return new File(cache, getPackageName() + ".ep.dex").getAbsolutePath();
		} else {
			return new File(cache, getPackageName() + sectionDexPathName).getAbsolutePath();
		}
	}

	// data/data/����/app_dex
	public String getLXDexPath() {
		return this.getDir("dex", 0).getAbsolutePath();
	}

	public void updateVersion(List<VersionData> versionData) {
		if (versionData == null || versionData.size() == 0) {
			init(null);
			LLog.log("�汾��֤ʧ��");
			return;
		}
		for (int i = 0; i < versionData.size(); i++) {

			// ���µİ汾��Ҫ��������if��֧��֮��else��֧
			if (versionData.get(i).getStatus() == 0) {
				LLog.log("�����°汾��Dex[" + i + "]:" + versionData.get(i).getXmlName());
				UpdateVersionTask updateVersionTask = new UpdateVersionTask(versionData.get(i));
				updateVersionTask.execute();
			} else {
				LLog.log("��ǰ���ذ汾�����µİ汾 ���ø���:" + versionData.get(i).getXmlName());
				// ��Ϊû�г�ʼ���汾�ţ����ԣ�����Ҫ���°汾��ʱ��Ͱѷ���˸��İ汾��д��xml�ļ�
				sp = getApplication().getSharedPreferences(versionData.get(i).getXmlName(), Context.MODE_PRIVATE);
				sp.edit().putString("version", versionData.get(i).getVersion()).commit();
				init(versionData.get(i));
			}
		}

	};

	private class CheckVersionTask extends AsyncTask<Void, String, List<VersionData>> {

		public CheckVersionTask() {
		}

		@Override
		protected List<VersionData> doInBackground(Void... arg0) {

			try {
				LLog.log("url:" + versionUrl);
				String encoderJson = URLEncoder.encode(jsonParamEncodeStr, HTTP.UTF_8);				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(versionUrl);
				httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
				StringEntity se = new StringEntity(encoderJson);
				se.setContentType(CONTENT_TYPE_TEXT_JSON);
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
				httpPost.setEntity(se);
				// Log.e("test","doInBackground---0.0");
				HttpResponse response = httpClient.execute(httpPost);
				// Log.e("test","doInBackground---0");
				InputStream in = response.getEntity().getContent();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				int len = 0;
				byte[] b = new byte[255];
				while ((len = in.read(b)) != -1) {
					ops.write(b, 0, len);
				}
				ops.close();
				in.close();

				String version = ops.toString("UTF-8");
				LLog.log("version:" + version);
				if (version == null) {
					return null;
				}
				versionDataList.clear();
				JSONObject jsonObject = new JSONObject(version);

				JSONArray jsonArray = jsonObject.getJSONArray("result");
				LLog.log("jsonArray length:" + jsonArray.length());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jb = jsonArray.getJSONObject(i);
					versionDataList.add(new VersionData(jb));
				}

				if (versionDataList != null && versionDataList.size() > 0) {
					for (int i = 0; i < versionDataList.size(); i++) {
						LLog.log("i=" + i + "--version:" + versionDataList.get(i).getVersion());
					}
				}

				return versionDataList;

			} catch (Exception e1) {
				LLog.error("CheckVersionTask---catch (Exception e1):" + e1.getMessage());
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<VersionData> versionData) {
			super.onPostExecute(versionData);
			if (versionData != null) {
				LLog.log("come in onPostExecute:" + versionData.size());
			}
			updateVersion(versionData);
		}

	}

	private class UpdateVersionTask extends AsyncTask<String, Void, Boolean> {
		private VersionData versionData;

		public UpdateVersionTask(VersionData versionData) {
			this.versionData = versionData;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {
			try {
				LLog.log("versionData.getUpdateSdkUrl():" + versionData.getUpdateSdkUrl());
				URL url = new URL(versionData.getUpdateSdkUrl());
				LLog.log("UpdateVersionTask---0");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				LLog.log("UpdateVersionTask---1");
				conn.setReadTimeout(3000);
				conn.setConnectTimeout(3000);
				LLog.log("UpdateVersionTask---2");
				conn.connect();
				LLog.log("UpdateVersionTask---3");
				InputStream in = conn.getInputStream();
				LLog.log("UpdateVersionTask---4");
				File file = new File(getLXDexFilePath(versionData.getSectionDexPathName()) + "_temp");
				FileOutputStream ops = new FileOutputStream(file.getAbsolutePath());
				int len = 0;
				byte[] b = new byte[255];
				while ((len = in.read(b)) != -1) {
					ops.write(b, 0, len);
				}
				ops.close();
				in.close();
				return true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				LLog.error("e.getMessage():" + e.getMessage());
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				String lxDexFilePath = getLXDexFilePath(versionData.getSectionDexPathName());
				File tempFile = new File(lxDexFilePath + "_temp");
				File file = new File(lxDexFilePath);
				try {
					FileInputStream in = new FileInputStream(tempFile);
					FileOutputStream ops = new FileOutputStream(file.getAbsolutePath());
					int len = 0;
					byte[] b = new byte[255];
					while ((len = in.read(b)) != -1) {
						ops.write(b, 0, len);
					}
					ops.close();
					in.close();
					updateVersionSuccess(versionData);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				LLog.log("����ʧ��");
				init(versionData);
			}
		}

		public void updateVersionSuccess(VersionData versionData) {
			Log.e("test", "come in updateVersionSuccess");
			sp = getApplication().getSharedPreferences(versionData.getXmlName(), Context.MODE_PRIVATE);
			sp.edit().putString("version", versionData.getVersion()).commit();
			// TODO ���汾��д��/data/data/����/files/ep.txt�����ʽxmlName:version
			// originStr ep.txt�ļ��е����� Util.getResultStr�ڶ��������ĸ�ʽ��xxx��yyy,
			// ����xxxΪ���� yyyΪ���İ汾��
			String originStr = Util.getInputString(file);

			String resultStr = Util.getResultStr(originStr,
					versionData.getXmlName() + ":" + versionData.getVersion() + ",");
			// resultStr:�õ����µ�ep.txt�ļ������ݣ�
			Util.getOutPutString(file, resultStr); // ��resultStr д�뵽ep.txt�ļ���

			// TODO http�������سɹ������ݻش�������ˣ�д����־���ݿ���(Dexbean����)
			uploadDataList.clear();
			uploadDataList.add(new PackageData(versionData.getXmlName(), versionData.getVersion()));

			//backResultToServer();
			Log.e("test", "updateVersionSuccess--initFlag=" + versionData.getInitFlag());
			init(versionData);
		}

	}

	private class BackResultToServerTask extends AsyncTask<Void, String, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			LLog.log("BackResultToServerTask--tempResult:" + resultUrl);
			try {
				String encoderJson = URLEncoder.encode(jsonUploadDataParamStr, HTTP.UTF_8);
				LLog.log("url:" + resultUrl);
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(resultUrl);
				httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
				StringEntity se = new StringEntity(encoderJson);
				se.setContentType(CONTENT_TYPE_TEXT_JSON);
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
				httpPost.setEntity(se);
				LLog.log("BackResultToServerTask---doInBackground---0.0");
				HttpResponse response = httpClient.execute(httpPost);
				InputStream in = response.getEntity().getContent();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				int len = 0;
				byte[] b = new byte[255];
				while ((len = in.read(b)) != -1) {
					ops.write(b, 0, len);
				}
				ops.close();
				in.close();
				String result = ops.toString("UTF-8");
				if (result == null) {
					return null;
				}
				LLog.log("BackResultToServerTask--result:" + result);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// updateVersion(version);
		}

	}

	private void backResultToServer() {

		DexBean dexBean = new DexBean();
		dexBean = Util.getDexBean(this, uploadDataList);
		jsonUploadDataParamStr = Util.parseObjectToJsonString(dexBean, uploadDataList);
		LLog.log("jsonUploadDataParamStr:" + jsonUploadDataParamStr);

		BackResultToServerTask backResultToServerTask = new BackResultToServerTask();
		backResultToServerTask.execute();
	}
}
