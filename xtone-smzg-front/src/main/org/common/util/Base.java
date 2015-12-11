package org.common.util;

public class Base {

  public static boolean isNumeric(String str) {
    if (str.matches("\\d*")) {
      return true;
    } else {
      return false;
    }
  }

}
