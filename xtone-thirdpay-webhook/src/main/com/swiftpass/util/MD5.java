package com.swiftpass.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;
import org.apache.commons.codec.digest.DigestUtils;

/** 
* 功能：MD5签名
* 版本：3.3
* 修改日期：2012-08-17
* */
public class MD5 {
    public static void main(String[] args) {
        String str = "appId=wx8632a91376b81e24&callback_url=http://u.shdplan.com/usr/details.html?paySuc=true&awaId=AID201508100946430003&money=1.99&nonceStr=1441076469693&package=prepay_id=wx2015090111010993f0dd52c80787614418&signType=MD5&status=0&timeStamp=1441076469693";
        System.out.println(MD5.sign(str, "&key=8748fd966d0ffc47abd59e97", "utf-8"));
        
    }
    
    
    public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
    
    
    
    /**
	 * MD5值计算<p>
	 * MD5的算法在RFC1321 中定义:
	 * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：
	 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
	 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
	 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
	 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0
	 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
	 * @param res 源字符串
	 * @return md5值
	 */
	public final static String md5s(String res) {
		if(res ==null||"".equals(res)){
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] strTemp;
		try {
			strTemp = res.getBytes("utf-8");
			//strTemp = res.getBytes();
		} catch (Exception e1) {
			return null;
		}
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String dd = new String(str);
			return dd;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5值计算+Base64<p>
	 * MD5的算法在RFC1321 中定义:
	 * 在RFC 1321中
	 * @param res 源字符串
	 * @return md5值
	 */
	public final static byte[] md5SrcDigest(String res) {
		if(res == null || "".equals(res)){
			return null;
		}
		byte[] strTemp = res.getBytes();
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return md;
		} catch (Exception e) {
			return null;
		}
	}
    
    
    
    

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
    	System.out.println(text);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}