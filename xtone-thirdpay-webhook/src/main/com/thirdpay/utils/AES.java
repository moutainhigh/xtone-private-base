package com.thirdpay.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.x.alipay.utils.Base642;

/**
 *
 * @author Administrator
 *
 */
public class AES {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            byte[] encrypted1 = new Base642().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
    	
   /*     
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         
        String cKey = "1234567890123456";
        // 需要加密的字串
        String cSrc = "12345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456www.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.so";
        System.out.println(cSrc);
        System.out.println(cSrc.length());
        // 加密
        String enString = AES.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
*/
    	 String cKey = "6010401031024102";
    	String enString = "nGIk2GKq/qgeXlTcBQ+kws2axn66Bv4pY2jAAw5C16hYotRYohjW+cr8CETNEwVlmz+KKQz7O3vQpF+oO8tzw7A6RCNDUmJ4Q3wMZWvdb1VC2/P+7gL4cQeoDYMLfGseMUFXqJoha6senIzg8SbjFm3DwDNigupWClpK8iJki6D/dHVj+oTJ9LBXNQpDSyaZH7sM5kZ48y8NKpWFYGCO23dF1xYet3XzaXCk1belEMZyLXbVUZk/LNmpD4YLn4MlfU9Mk2szYVTd402zevcPocew3mxDKJ3/9udcFy7lBtrmO0iEAU6a6BK6b67aLAsm";
    	
        // 解密
        String DeString = AES.Decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
        System.out.println("解密后的字串是：" + DeString.length());
    }
}

