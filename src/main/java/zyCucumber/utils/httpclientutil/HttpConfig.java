package zyCucumber.utils.httpclientutil;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpConfig {
    private HttpClient client;
    private Header[] headers;
    private boolean isReturnRespHeader;
    private HttpMethods method;
    private String  methodName;
    private HttpContext context;
    private Map<String,Object> map;
    private String json;
    private String encoding;
    private String inenc;
    private String ontenc;
    private static final ThreadLocal<OutputStream> outs = new ThreadLocal<>();
    private static final ThreadLocal<String> urls = new ThreadLocal<>();

    public HttpConfig() {
        this.method = HttpMethods.GET;
        this.encoding = Charset.defaultCharset().displayName();
    }

    public HttpConfig context(HttpContext context) {
        this.context = context;
        return this;
    }

    public HttpContext context() {
        return this.context;
    }
    public static HttpConfig custom() {
        return new HttpConfig();
    }

    public HttpConfig client(HttpClient client) {
        this.client = client;
        return this;
    }

    public HttpConfig url(String url) {
        urls.set(url);
        return this;
    }

    public HttpConfig headers(Header[] headers) {
        this.headers = headers;
        return this;
    }

    public HttpConfig headers(Header[] headers,boolean isReturnRespHeader) {
        this.headers = headers;
        this.isReturnRespHeader = isReturnRespHeader;
        return this;
    }

    public HttpConfig json(String json) {
        this.json = json;
        this.map = new HashMap<>();
        this.map.put("ENTITY_STRING",json);
        return  this;
    }

    public HttpConfig map(Map<String,Object> map) {
        synchronized (this.getClass()){
            if(this.map != null && map != null) {
                this.map.putAll(map);
            }else{
                this.map = map;
            }

            return this;
        }
    }

    public HttpClient client() {
        return  this.client;
    }

    public String url() {
        return (String)urls.get();
    }

    public HttpConfig method(HttpMethods method) {
        this.method = method;
        return  this;
    }

    public HttpMethods method() {
        return this.method;
    }

    public Header[] headers() {
        return this.headers;
    }

    public String inenc() {
        return this.inenc == null ? encoding : inenc;
    }

    public Map<String,Object> map() {
        return map;
    }
}
