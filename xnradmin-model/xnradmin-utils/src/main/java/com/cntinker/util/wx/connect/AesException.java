package com.cntinker.util.wx.connect;

public class AesException extends Exception
{
  public static final int OK = 0;
  public static final int ValidateSignatureError = -40001;
  public static final int ParseXmlError = -40002;
  public static final int ComputeSignatureError = -40003;
  public static final int IllegalAesKey = -40004;
  public static final int ValidateCorpidError = -40005;
  public static final int EncryptAESError = -40006;
  public static final int DecryptAESError = -40007;
  public static final int IllegalBuffer = -40008;
  private int code;

  private static String getMessage(int code)
  {
    switch (code)
    {
    case -40001:
      return "ǩ����֤����";
    case -40002:
      return "xml����ʧ��";
    case -40003:
      return "sha��������ǩ��ʧ��";
    case -40004:
      return "SymmetricKey�Ƿ�";
    case -40005:
      return "corpidУ��ʧ��";
    case -40006:
      return "aes����ʧ��";
    case -40007:
      return "aes����ʧ��";
    case -40008:
      return "���ܺ�õ���buffer�Ƿ�";
    }

    return null;
  }

  public int getCode()
  {
    return this.code;
  }

  public AesException(int code) {
    super(getMessage(code));
    this.code = code;
  }
}