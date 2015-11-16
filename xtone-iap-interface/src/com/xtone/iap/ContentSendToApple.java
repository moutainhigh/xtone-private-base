package com.xtone.iap;

import com.google.gson.annotations.SerializedName;

public class ContentSendToApple {
  
  @SerializedName("receipt-data")
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
