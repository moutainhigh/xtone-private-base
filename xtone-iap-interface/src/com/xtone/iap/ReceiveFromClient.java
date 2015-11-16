package com.xtone.iap;

import com.google.gson.annotations.SerializedName;

public class ReceiveFromClient {
  
  @SerializedName("receipt-data")
  private String content;
  @SerializedName("Uin")
  private String uIn;
  @SerializedName("WorldID")
  private String worldID;

  @Override
  public String toString() {
    return "ReceiveFromClient [content=" + content + ", uIn=" + uIn + ", worldID=" + worldID + "]";
  }

  public String getuIn() {
    return uIn;
  }

  public void setuIn(String uIn) {
    this.uIn = uIn;
  }

  public String getWorldID() {
    return worldID;
  }

  public void setWorldID(String worldID) {
    this.worldID = worldID;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
