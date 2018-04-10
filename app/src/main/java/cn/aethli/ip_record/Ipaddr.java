package cn.aethli.ip_record;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 93162 on 2018/3/29.
 */

public class Ipaddr {
    public static String getIp(){
        String regex="(\\d{1,3}\\.){3}\\d{1,3}";
        String ipstr = null;
        StringBuffer sb=new StringBuffer();

        try {
            URL url = new URL("http://ip.chinaz.com/getip.aspx/");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF8"));
                while ((ipstr=br.readLine())!=null){
                    sb.append(ipstr);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Pattern p= Pattern.compile(regex);
        Matcher m=p.matcher(sb.toString());
        if(m.find()) {
            ipstr=m.group(0);
        }
        return ipstr;
    }
}
