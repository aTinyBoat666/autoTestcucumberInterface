package zyCucumber.utils.httpclientutil;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {
    public static final String ENTITY_STRING = "ENTITY_STRING";

    public static String checkHasParas(String url, List<NameValuePair> nvps, String encoding) {
        if(url.contains("?")&& url.indexOf("?") < url.indexOf("=")){
            Map<String,Object> map = buildParas(url.substring(url.indexOf("?") + 1));
            map2HttpEntity(nvps,map,encoding);
            url = url.substring(0,url.indexOf("?"));
        }
        return url;
    }

    public  static HttpEntity map2HttpEntity(List<NameValuePair> nvps, Map<String, Object> map, String encoding) {
        HttpEntity entity = null;
        if(map != null && map.size()> 0 ) {
            boolean isSpecial = false;
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Object> entry = (Map.Entry<String, Object>) iterator.next();
                if("ENTITY_STRING".equals(entry.getKey())) {
                    entity = new StringEntity(String.valueOf(entry.getValue()),encoding);
                    break;
                }
                nvps.add(new BasicNameValuePair((String)entry.getKey(),String.valueOf(entry.getValue())));
            }
        }
        return entity;
    }

    public static Map<String, Object> buildParas(String paras) {
        String[] p = paras.split("&");
        String[][] ps = new String[p.length][2];

        for (int i=0;i<p.length;++i){
            int pos = p[i].indexOf("=");
            ps[i][0] = p[i].substring(0,pos);
            ps[i][1] = p[i].substring(pos+1);
        }
        return buildParas(ps);
    }

    public static Map<String,Object> buildParas(String[][] paras){
        Map<String,Object> map = new HashMap<>();
        String[][] var2 = paras;
        int var3 = paras.length;
        for(int var4 = 0 ;var4 < var3; ++var4) {
            String[] para = var2[var4];
            map.put(para[0],para[1]);
        }
        return map;
    }
}
