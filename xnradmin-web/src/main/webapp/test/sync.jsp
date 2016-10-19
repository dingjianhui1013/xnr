<%@ page contentType="text/html;charset=utf-8" language="java"
	import="java.io.*" import="java.text.*" import="java.net.*" import="java.util.*"%>
<%!
private static String postXml(String url,String xml,String character,
        boolean displayHeadInfo) throws IOException{
    StringBuffer tempStr = new StringBuffer();
    BufferedReader br = null;
    URL u = new URL(url);
    URLConnection con = u.openConnection();
    con.setDoOutput(true);
    con.setDoInput(true);
    con.setUseCaches(false);
    con.setRequestProperty("Content-Type","text/xml");
    con.setRequestProperty("Pragma:","no-cache");
    con.setRequestProperty("Cache-Control","no-cache");
    con.setRequestProperty("Content-length",String.valueOf(xml.length()));

    DataOutputStream out = new DataOutputStream(con.getOutputStream());
    // OutputStreamWriter out = new
    // OutputStreamWriter(con.getOutputStream());

    out.write(new String(xml.getBytes(character)).getBytes());

    InputStream urlStream = null;
    urlStream = con.getInputStream();
    Map m = con.getHeaderFields();

    Iterator it = m.keySet().iterator();
    String currentLine = "";
    if(displayHeadInfo){
        while(it.hasNext()){
            String k = (String) it.next();
            tempStr.append(k).append(":").append(con.getHeaderField(k))
                    .append("\n");
        }
    }
    br = new BufferedReader(new InputStreamReader(urlStream));
    while( ( currentLine = br.readLine() ) != null){
        tempStr.append(currentLine);
    }

    return tempStr.toString();
}


private static String decode(String src){
    if(src.length() < 2)
        return null;
    byte[] dest = new byte[src.length() / 2];
    byte rb;
    String str;
    Arrays.fill(dest,(byte) 0);
    int index = 0;
    for(int ii = 0;ii < src.length() - 1;ii ++ ){
        str = "#" + src.substring(ii,ii + 2);
        rb = (byte) Integer.decode(str).intValue();
        dest[index ++ ] = rb;
        ii ++ ;
    }
    String res = null;
    try{
        res = new String(dest,"UTF-8");
    }catch(UnsupportedEncodingException e){
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return res;
}
%>
<%try{
        request.setCharacterEncoding("utf-8");
        String url = "http://115.29.38.253:8080/interface/sync.jsp";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                request.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String currentLine = "";
        while( ( currentLine = br.readLine() ) != null){
            sb.append(currentLine);
        }
        String source = sb.toString();
        String end = postXml(url, source, "utf-8", false);
        out.clear();
        out.println(end);
	}catch(Throwable e){
        e.printStackTrace();
	}
%>