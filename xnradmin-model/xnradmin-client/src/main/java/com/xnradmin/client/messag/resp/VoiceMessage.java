package com.xnradmin.client.messag.resp;

public class VoiceMessage extends BaseMessageResp
{
  private Voice voice;

  public Voice getVoice()
  {
    return this.voice;
  }

  public void setVoice(Voice voice) {
    this.voice = voice;
  }
}