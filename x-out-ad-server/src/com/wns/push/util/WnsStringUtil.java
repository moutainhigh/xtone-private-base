package com.wns.push.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wns2.util.WnsMd5;

public class WnsStringUtil {
	 /**
     * 
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static String removeSuffix(String str, String suffix) throws Exception {
        if (null == str)
            return null;
        if ("".equals(str.trim()))
            return "";

        if (null == suffix || "".equals(suffix))
            return str;

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }

        throw new Exception("WnsStringUtil exception");
    }

    /**
     * Check the String is blank or not
     * 
     * @param str
     * @return
     * @throws UtilException
     */
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean isBlank(Long str) {
        return null==str;
    }

    /**
     * 鐏忓棗顕挒陇娴嗛幋鎬眛ring
     * 
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    /**
     * 
     * @param str
     * @return
     */
    public static String getString(String str) {
        if (null == str)
            return "";
        return str;

    }

    /**
     * 鐠侊紕鐣绘稉銈勯嚋閺佹澘鐡х�妤冾儊娑撹尙娈戦崪锟�     * 
     * @param augend
     * @param addend
     * @return
     * @throws UtilException
     */
    public static String getSum(String augend, String second, String addend) {
        if (augend == null)
            augend = "0";
        if (second == null)
            second = "0";
        if (addend == null)
            addend = "0";
        int sum = Integer.parseInt(augend) + Integer.parseInt(second) + Integer.parseInt(addend);
        return new Integer(sum).toString();
    }

    public static String change(String str, int n, boolean isLeft) {
        if (str == null || str.length() >= n)
            return str;
        StringBuffer s = new StringBuffer();
        for (int i = str.length(); i < n; i++)
            s.append('0');
        if (isLeft)
            return s.append(str).toString();
        else
            return s.insert(0, str).toString();
    }

    public static String getInString(String str) {
        if (str == null)
            return null;
        int len =  str.length();
        StringBuffer buf = new StringBuffer(len<<1).append('\'');
        for(int i = 0; i < len; i++){
            char Char = str.charAt(i);
            if(','==Char)
                buf.append("','");
            else
                buf.append(Char);
        }
        return buf.append('\'').toString();
    }

    /**
     * 閺嶈宓侀弽鍥槕閼惧嘲褰噑tr娑擃厽娓堕崥搴濈娑撶專lag閸氬海娈戦崘鍛啇
     * 
     * @param str
     * @param flag
     * @return
     */
    public static String getLastStr(String str, String flag) {
        if (isBlank(str))
            return null;
        int index = str.lastIndexOf(flag);
        if (index < 0) {
            return str;
        } else {
            return str.substring(index + flag.length());
        }

    }

    /**
     * 閼惧嘲褰囧锝呭灟鐞涖劏鎻蹇撳爱闁板秶娈戠�妤冾儊娑撹绱濈亸锟界粭锕�樀閻炲棔绔存稉瀣剁礉娑撳秶鍔ч崠褰掑帳閺冩湹绱扮拋銈勭稊閸掑棛绮�
     * 
     * @param str
     * @return
     */
    public static String getRegexStr(String str) {
        String ret = "";
        if (isBlank(str))
            return "";
        if (str.indexOf('$', 0) > -1) {
            while (str.length() > 0) {
                if (str.indexOf('$', 0) > -1) {
                    ret += str.subSequence(0, str.indexOf('$', 0));
                    ret += "\\$";
                    str = str.substring(str.indexOf('$', 0) + 1, str.length());
                } else {
                    ret += str;
                    str = "";
                }
            }

        } else {

            ret = str;
        }

        return ret;

    }

    /**
     * 濮ｆ棁绶濇稉銈勯嚋String鐎电钖勯崐鍏兼Ц閸氾妇娴夌粵锟�     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        if (null == str1) {
            str1 = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (str1.trim().equals(str2.trim())) {
            return true;
        }
        return false;
    }
    
    /**
     * 鐏忓攳ame鏉烆剚宕查幋鎰邦湚鐎涙鐦濇径褍鍟�
     * 
     * @param name
     * @return
     */
    public static String trunUpName(String name) {
        if (WnsStringUtil.isBlank(name)) {
            return name;
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }
    
    // 閸樼粯甯�崜宥咁嚤鐎涙顑�0 閸滐拷缁岀儤鐗搁敍宀冨殾鐏忔垳绻氶悾娆愭付閸欏疇绔熸稉锟界秴
    public static String ltrim(String inStr){
        StringBuffer sb = new StringBuffer();
        if (inStr == null || inStr.length() == 0) return inStr;     
        for (int i = 0; i < inStr.length(); i++){
            if ((i == inStr.length()-1) || inStr.charAt(i) != '0' && inStr.charAt(i) != ' ')
                sb.append(inStr.charAt(i));
        }
        return sb.toString();
    }
    
    /**
     * 閹跺﹤鐡х粭锔胯閻劎澹掔�姘摟缁楋箒藟閹存劕娴愮�姘摟閼哄倿鏆辨惔锔炬畱鐎涙顑佹稉锟�     * @param src 閸樼喎鐡х粭锔胯
     * @param temp 鐞氼偉藟閻ㄥ嫬鐡х粭锟�     * @param sumLength 鐞涖儱鍘栭崥搴ｆ畱鐎涙濡梹鍨
     * @param direction 鐞涖儳娈戦弬鐟版倻閿涘閿涙艾涔忕悰銉礉R閿涙艾褰哥悰銉礉姒涙顓婚弰顖氫箯鐞涖儻绱濊箛鐣屾殣婢堆冪毈閸愶拷
     * @return
     */
    private static String fillStringByByte(String src, char temp, int sumLength, String direction) {
        String dest = "";
        src = src != null ? src.trim() : "";
        while(--sumLength >= src.getBytes().length) {
            dest += temp;
        }
        return "R".equalsIgnoreCase(direction) ? src + dest : dest + src;
    }
    
    /**
     * 閻劎澹掔�姘辨畱鐎涙顑佸锕佀夐幋鎰祼鐎规艾鐡ч懞鍌炴毐鎼达妇娈戠�妤冾儊娑擄拷
     * @param src 閸樼喎鐡х粭锔胯
     * @param temp 鐞氼偉藟閻ㄥ嫬鐡х粭锟�     * @param sumLength 鐞涖儱鍘栭崥搴ｆ畱鐎涙濡梹鍨
     * @return
     */
    public static String lFillString(String src, char temp, int sumLength) {
        return fillStringByByte(src, temp, sumLength, "L");
    }
    
    /**
     * 閻劎澹掔�姘辨畱鐎涙顑侀崣瀹犓夐幋鎰祼鐎规艾鐡ч懞鍌炴毐鎼达妇娈戠�妤冾儊娑擄拷
     * @param src 閸樼喎鐡х粭锔胯
     * @param temp 鐞氼偉藟閻ㄥ嫬鐡х粭锟�     * @param sumLength 鐞涖儱鍘栭崥搴ｆ畱鐎涙濡梹鍨
     * @return
     */
    public static String rFillString(String src, char temp, int sumLength) {
        return fillStringByByte(src, temp, sumLength, "R");
    }
    /**
     * 鐎电BK缂傛牜鐖滈惃鍕摟缁楋缚瑕嗘潪顒佸床閹存�TF-8閻ㄥ嫬鐡х粭锔胯閿涘苯鑻熸穱婵婄槈娑撳秳楠囬悽鐔惰础閻拷
     * 鏉烆剚宕查崥搴ｆ畱UTF-8鐎涙顑佹稉鎻掑讲娴犮儱鍩勯悽鈺猠w String(UTF-8.getByte(),"GBK")鏉烆剙娲杇bk
     * 
     * @param gbk
     * @return
     */
    public static String convertGBK2UTF8(String gbk){
        String utf8 = "";
        try {
            utf8 = new String(gbk2utf8(gbk),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utf8;
    }

    public static byte[] gbk2utf8(String chenese) {
        char c[] = chenese.toCharArray();
        byte[] fullByte = new byte[3 * c.length];
        for (int i = 0; i < c.length; i++) {
            int m = (int) c[i];
            String word = Integer.toBinaryString(m);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");

            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);

            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i * 3] = bf[0];
            bf[1] = b1;
            fullByte[i * 3 + 1] = bf[1];
            bf[2] = b2;
            fullByte[i * 3 + 2] = bf[2];

        }
        return fullByte;
    }
    
    public static String replaceBlank(String str) {  
    	String dest = ""; 
    	if (str!=null) {  
    		Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
    		Matcher m = p.matcher(str);  
    		dest = m.replaceAll("");  
    	} 
    	char [] chars = dest.toCharArray();
    	StringBuffer buffer = new StringBuffer();
    	if(chars != null && chars.length > 0){
    		for(int i = 0; i < chars.length; i++){
        		if((int)chars[i] != 160){
        			buffer.append(chars[i]);
        		}
        	}
        	dest = buffer.toString();
    	}
    	return dest;  

    } 


    /***
     * Delete empty char
     * @param inputStr
     * @return
     */
    public static String killspace(String inputStr) {
		inputStr = (inputStr == null ? "" : inputStr);
		inputStr = inputStr.trim();
		return inputStr;
	}
    public static String killNull(String inputStr) {
		inputStr = (inputStr == null ? "" : inputStr);
		return inputStr;
	}
    
    
    public static String substring(String string, int index){
    	char[] charArray = string.toCharArray();
    	int number = 0;
    	String substring = "";
    	for(int i = 0,j=0; i < index && j<string.length();){
    		char tempChar = charArray[j];
    		if(Character.getType(tempChar) == Character.OTHER_LETTER){
    			i = i+2;
    			if(i > index){
    				if(i == (index + 1)){
    					continue;
    				}
    			}
    		}else{
    			i++;
    		}
    		j++;
    		number ++;
    	}
    	substring =  string.substring(0, number);
    	return substring;
    }
    
    public static String genDeviceId(String imsi, String imei, String model, String mac){
        String dev_id = imsi + imei + model + mac;
        dev_id = WnsMd5.md5(dev_id);
        return dev_id;
    }
//    
//    public static void main(String [] args){
//    	System.out.println(NgsteamStringUtil.substring("鐎硅泛娲�閿涙俺鍚归弮锟� 7));
//    }
}
