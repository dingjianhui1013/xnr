package com.xnradmin.client.messag.resp;
public class BaseMessageResp
{
  private String ToUserName;
  private String FromUserName;
  private long CreateTime;
  private String MsgType;
  private int FuncFlag;
  private String AgentId;
  private String MsgId;

  public String getToUserName()
  {
    return this.ToUserName;
  }

  public void setToUserName(String toUserName) {
    this.ToUserName = toUserName;
  }

  public String getFromUserName() {
    return this.FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.FromUserName = fromUserName;
  }

  public long getCreateTime() {
    return this.CreateTime;
  }

  public void setCreateTime(long createTime) {
    this.CreateTime = createTime;
  }

  public String getMsgType() {
    return this.MsgType;
  }

  public void setMsgType(String msgType) {
    this.MsgType = msgType;
  }

  public int getFuncFlag() {
    return this.FuncFlag;
  }

  public void setFuncFlag(int funcFlag) {
    this.FuncFlag = funcFlag;
  }

  public String getAgentId() {
    return this.AgentId;
  }

  public void setAgentId(String agentId) {
    this.AgentId = agentId;
  }

  public String getMsgId() {
    return this.MsgId;
  }

  public void setMsgId(String msgId) {
    this.MsgId = msgId;
  }
}