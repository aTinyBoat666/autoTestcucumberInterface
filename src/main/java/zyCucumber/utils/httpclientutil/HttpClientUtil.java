package zyCucumber.utils.httpclientutil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class HttpClientUtil {
    private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

    private static HttpClient clientHttp;
    private  static HttpClient clientHttps;
    static {
        clientHttp = HCB.custom().build();
        try {
            clientHttps = HCB.custom().ssl().build();
        } catch (Exception e) {
            logger.error("创建Https协议的HttpClinet对象失败" + e.getMessage());
        }
    }

    private  static void create(HttpConfig config) {
        if(config.client() != null) {
            if(config.url().toLowerCase().startsWith("Https://")) {
                config.client(clientHttps);
            }else {
                config.client(clientHttp);
            }
        }
    }

    private static HttpResponse excute(HttpConfig config) {
        create(config);
        HttpResponse resp = null;

        try{
            HttpRequestBase request = getRequest(config.url(),config.method());
            request.setHeaders(config.headers());
            if(HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList();
                config.url(Utils.checkHasParas(config.url(),nvps,config.inenc()));
                HttpEntity entity = Utils.map2HttpEntity(nvps,config.map(),config.inenc());
                ((HttpEntityEnclosingRequestBase)request).setEntity(entity);
            }

            resp = config.context() != null ? config.client().execute(request):config.client().execute(request,config.context());
           return  resp;

        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request = null;
        switch(method.getCode()) {
            case 0:
               request = new HttpGet(url);
               break;
            case 1:
                request = new HttpPost(url);
                break;
            case 2:
                request = new HttpPut(url);
                break;
            case 3:
                request = new HttpDelete(url);
                break;
            default:
                request = new HttpPost(url);
        }
        return (HttpRequestBase) request;
    }

}
