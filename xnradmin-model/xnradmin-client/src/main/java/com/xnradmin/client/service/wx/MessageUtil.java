package com.xnradmin.client.service.wx;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xnradmin.client.messag.resp.Article;
import com.xnradmin.client.messag.resp.MusicMessage;
import com.xnradmin.client.messag.resp.NewsMessage;
import com.xnradmin.client.messag.resp.TextMessage;
import com.xnradmin.client.messag.resp.Voice;
import com.xnradmin.client.messag.resp.VoiceMessage;

public class MessageUtil
{
  public static final String RESP_MESSAGE_TYPE_TEXT = "text";
  public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
  public static final String RESP_MESSAGE_TYPE_NEWS = "news";
  public static final String REQ_MESSAGE_TYPE_TEXT = "text";
  public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
  public static final String REQ_MESSAGE_TYPE_LINK = "link";
  public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
  public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
  public static final String REQ_MESSAGE_TYPE_EVENT = "event";
  public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
  public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
  public static final String EVENT_TYPE_CLICK = "click";
  
  private static XStream xstream = new XStream(new XppDriver() {
    public HierarchicalStreamWriter createWriter(Writer out) {
      return new PrettyPrintWriter(out)
      {
        boolean cdata;

        public void startNode(String name, Class clazz)
        {
          super.startNode(name, clazz);
        }

        protected void writeText(QuickWriter writer, String text) {
          if (this.cdata) {
            writer.write("<![CDATA[");
            writer.write(text);
            writer.write("]]>");
          } else {
            writer.write(text);
          }
        }
      };
    }
  });

  public static Map<String, String> parseXml(HttpServletRequest request)
    throws Exception
  {
    Map map = new HashMap();

    InputStream inputStream = request.getInputStream();
    System.out.println(inputStream.toString() + ": inputString");

    SAXReader reader = new SAXReader();
    Document document = reader.read(inputStream);

    System.out.println(document + ": document");
    Element root = document.getRootElement();

    List<Element> elementList = root.elements();

    for (Element e : elementList)
    {
      map.put(e.getName(), e.getText());
    }

    inputStream.close();

    return map;
  }

  public static String textMessageToXml(TextMessage textMessage)
  {
    xstream.alias("xml", textMessage.getClass());
    return xstream.toXML(textMessage);
  }

  public static String musicMessageToXml(MusicMessage musicMessage)
  {
    xstream.alias("xml", musicMessage.getClass());
    return xstream.toXML(musicMessage);
  }

  public static String newsMessageToXml(NewsMessage newsMessage)
  {
    xstream.alias("xml", newsMessage.getClass());
    xstream.alias("item", new Article().getClass());
    return xstream.toXML(newsMessage); }

  public static String voiceMessageToXml(VoiceMessage voiceMessage) {
    xstream.alias("xml", voiceMessage.getClass());
    xstream.alias("mediaId", new Voice().getClass());
    return xstream.toXML(voiceMessage);
  }
}