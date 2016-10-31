package com.xnradmin.client.messag.resp;
public class TextMessage extends BaseMessageResp
{
  private String Content;

  public String getContent()
  {
    return this.Content;
  }

  public void setContent(String content) {
    this.Content = content;
  }
}