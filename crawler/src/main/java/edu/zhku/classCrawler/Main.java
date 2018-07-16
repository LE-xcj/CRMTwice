package edu.zhku.classCrawler;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
public class Main {
 
     
    private static final String ORIGINURL = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx";
    private static final String REQUESTURL = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel_rpt.aspx";
     
    private static final String TERM = "Sel_XNXQ";
    private static final String TYPE = "rad_gs";
    private static final String CORE =  "txt_yzm";
    private static final String LOCATION = "Sel_XQ";
    private static final String PLACE ="Sel_JX";
    private static final String ROOM = "Sel_ROOM";
     
    private static String sessionid = "";
    private static String pre = "";
     
    //标签    
    private static final String IMGURL_REG = "<img .*?src=['\"]([^<>]*?)['\"]>";
     
    private static final String IMGSRC_REG = "src=['\"]([^<>]*?)['\"]";
      
    private static final String PREFIX = "http://jw.zhku.edu.cn";
     
     
     
    /*
     * 获取src指向的地址值，然后下载验证码到本地
     */
    public static void saveImag(String src){
        String imgName = "code/img.jpg";
         
        File file = new File(imgName);
        InputStream is = null;
        FileOutputStream fos = null;
         
        try {
        	System.out.println(src);
            URL url = new URL(src);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            String cookie = pre + sessionid;
            System.out.println(cookie);
                     
            c.setRequestProperty("Referer", "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx");
            c.setRequestProperty("Cookie",cookie);
             
            c.connect();
            is = c.getInputStream();
            //is = url.openStream();
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length = 0;
             
            while((length = is.read(buffer)) != -1){
                fos.write(buffer,0,length);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(null != fos){
                    fos.flush();
                    fos.close();
                }
                if(null != is)
                    is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
     
    /*
     * 获取img标签中，src的地址值
     */
    public static String getSrc(List<String> listimageurl){
         
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        String temp = listImageSrc.get(0);
        String src = PREFIX + temp.substring(temp.indexOf("/"));
        System.out.println("img src" + src);
        return src;
    }
     
    /*
     * 获取html里所有img标签
     */
    public static List<String> getImgTag(String html){
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        List<String> listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }
     
    /*
     * 获取网页的html
     */
     
    public static String getHtml(){
 
        HttpURLConnection connection = null;
         
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bfr = null;
         
        StringBuffer buffer = null;
         
        try {
            URL url  = new URL(ORIGINURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
 
            Map<String , List<String>> heads = connection.getHeaderFields();
             
            List<String> list = heads.get("Set-Cookie");
            String value = list.get(0);
            pre = value.substring(0,value.indexOf(';')+1);
            value = list.get(1);
            sessionid = value.substring(0,value.indexOf(';'));    
             
             
            is = connection.getInputStream();
            isr = new InputStreamReader(is,"gb2312");
            bfr = new BufferedReader(isr);
             
            buffer = new StringBuffer();
            String str = "";
            while((str = bfr.readLine()) != null){
                buffer.append(str);
            }
             
             
             
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(null != bfr)
                    bfr.close();
                 
                if(null != isr)
                    isr.close();
                 
                if(null != is)
                    is.close();
                 
                if(null != connection)
                    connection.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         
        return buffer.toString();
    }
     
    public static String getData(String core){
         
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bfr = null;
         
        StringBuffer buffer = null;
        HttpURLConnection c = null;
     
        try {
            URL url = new URL(REQUESTURL);
            c =(HttpURLConnection) url.openConnection();
            c.setConnectTimeout(10000);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestMethod("POST");
             
            String cookie = pre + sessionid;
            System.out.println(cookie);
                     
            c.setRequestProperty("Referer", "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx");
            c.setRequestProperty("Cookie",cookie);
            System.out.println("this is cok :" + cookie);
                     
            System.out.println(core);
            String data = TERM + "=" + "20171" + "&" + 
                          TYPE + "=" + "2" + "&" +
                          CORE + "=" + core + "&" +
                          LOCATION + "=" + "3" + "&" +
                          PLACE + "=" + "301" + "&" +
                          ROOM + "=" + "301103" ;
             
            c.getOutputStream().write(data.getBytes());
             
            c.connect();
             
             
 
            int flag = c.getResponseCode();
            System.out.println(flag);
             
            is = c.getInputStream();
            isr = new InputStreamReader(is,"gb2312");
            bfr = new BufferedReader(isr);
             
            String line = "";
            buffer = new StringBuffer();
            while((line = bfr.readLine()) != null){
                buffer.append(line);
            }
             
             
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if (null != bfr){
                    bfr.close();
                }
                 
                if (null != isr){
                    isr.close();
                }
                 
                if (null != is){
                    is.close();
                }
                 
                if (null != c){
                    c.disconnect();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
             
        }
         
        return buffer.toString();
    }
     
}