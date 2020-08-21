package zyCucumber.entity;

import org.apache.http.Header;

import java.util.Map;

/**
 * 封装接口请求信息
 */
public class InterfaceRequestContext {
    /**
     * 请求url
     */
    private String requestUrl;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求头信息
     */
    private Header[] headers;
    /**
     * 请求体
     */
    private String jsonBody;
    /**
     * 接口信息集合
     */
    private Map<String,Object> interfaceScriptMap;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public Map<String, Object> getInterfaceScriptMap() {
        return interfaceScriptMap;
    }

    public void setInterfaceScriptMap(Map<String, Object> interfaceScriptMap) {
        this.interfaceScriptMap = interfaceScriptMap;
    }
}
