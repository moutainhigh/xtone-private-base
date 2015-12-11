package org.common.util;

import org.apache.log4j.Logger;

public class HexChange {

  // it must no repeat !
  private final static Logger LOG = Logger.getLogger(HexChange.class);
  public static String STRING_MAP = "23456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
  
  private static int HEX_LENGTH = STRING_MAP.length();
  private static char[] CHAR_MAPS = new char[STRING_MAP.length()];
  public static int OUTPUT_FIX_LENGTH = 13;
//  public static final String EXCEPTION_OVERLOAD = " overload "; // limit by long , won't overload 
  public static final String EXCEPTION_LESS_MIN = " input can not less than 0 ";
  
  static {
    for (int i=0;i<STRING_MAP.length();i++){
      CHAR_MAPS[i] = STRING_MAP.charAt(i);
    }
  }
  
  public static String convertToOtherHex(long input){
    if (input<0L){
      throw new IllegalArgumentException(EXCEPTION_LESS_MIN);
    }
    StringBuffer stringBuffer = new StringBuffer();
    long tmpMod = 0 ;
    long tmpMid = input ;
    for (int i = 0 ;i<OUTPUT_FIX_LENGTH;i++){
      if (tmpMid>0){
        tmpMod = tmpMid % HEX_LENGTH;
        stringBuffer.append(CHAR_MAPS[(int) tmpMod]);
        tmpMid = tmpMid / HEX_LENGTH;
      }else{
        stringBuffer.append(CHAR_MAPS[0]);
      }
    }
    stringBuffer.reverse();
    return stringBuffer.toString() ;
  }
  
  public static void main(String[] args){
    System.out.println(HexChange.convertToOtherHex(1437068648697011235L));
    System.out.println(HexChange.convertToOtherHex(8447980015824018780L));
  }
    
}
