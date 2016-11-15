package com.xnradmin.client.messag.resp;

public class MusicMessage extends BaseMessageResp
{
  private Music Music;

  public Music getMusic()
  {
    return this.Music;
  }

  public void setMusic(Music music) {
    this.Music = music;
  }
}