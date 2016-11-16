package com.cntinker.util.wx.connect;

import java.util.ArrayList;

class ByteGroup
{
  ArrayList<Byte> byteContainer = new ArrayList();

  public byte[] toBytes() {
    byte[] bytes = new byte[this.byteContainer.size()];
    for (int i = 0; i < this.byteContainer.size(); ++i) {
      bytes[i] = ((Byte)this.byteContainer.get(i)).byteValue();
    }
    return bytes;
  }

  public ByteGroup addBytes(byte[] bytes) {
    for (byte b : bytes) {
      this.byteContainer.add(Byte.valueOf(b));
    }
    return this;
  }

  public int size() {
    return this.byteContainer.size();
  }
}