package zyCucumber.interfaceUtils;

import com.alibaba.fastjson.JSON;
import cucumber.api.java.cs.A;
import gherkin.lexer.Da;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zyCucumber.entity.RequestMessage;
import zyCucumber.support.DataRepo;

import java.io.File;
import java.util.Map;

@Component("httpHandler")
public class HttpHandler extends HttpBaseHandler{
    private static final Logger logger = Logger.getLogger(HttpHandler.class);

    @Autowired
    private DataRepo dataRepo;
    @Autowired
    private RequestMessage requestMessage;

    @Override
    protected void initRequestInterfaceData(String interfaceName) {
        // 读取全局配置信息
        Map<String,Object> globalsetingMap = dataRepo.getGlobalSetingMap();
        // 获取脚本信息
        Map<String,Object> scriptMap = (Map<String,Object>)dataRepo.getScriptMap().get(interfaceName);
        // 获取接口信息基本路径
        String basePath = (String)globalsetingMap.get("TEST_SCRIPT_PATH");
        // 读取接口信息
        String scriptPath = basePath + File.separator + scriptMap.get("InterfaceScript");
        dataRepo.readInterfaceScript(scriptPath);
    }

    @Override
    protected void preHandlerForUrl() {
        //获取接口信息
       Map<String,Object> scriptMap  =  (Map<String,Object>)dataRepo.getInterfaceScriptMap().get("Request");
       String subUrl = (String)scriptMap.get("SubUrl");
        //获取环境信息
       String requestUrl = (String)dataRepo.getEnvironmentMap().get("REQUEST_URL");
        // 拼接url
       StringBuffer sb = new StringBuffer(requestUrl);
       String url = sb.append(subUrl).toString();
       getContext().setRequestUrl(url);
    }

    @Override
    protected void preHandlerForType() {
        //获取接口信息
        Map<String,Object> scriptMap  =  (Map<String,Object>)dataRepo.getInterfaceScriptMap().get("Request");
        String type =  (String)scriptMap.get("Type");
        getContext().setRequestType(type);
    }

    @Override
    protected void preHandlerForHeader() {
        logger.info("这个是请求头信息");
    }

    @Override
    protected void preHandlerForJsonBody(String interfaceName) {
        // 获取接口信息
        Map<String,Object> interfaceScriptMap = dataRepo.getInterfaceScriptMap();
        Map<String,Object> requestParamMap = (Map<String,Object>) interfaceScriptMap.get("RequestParams");
        // 获取脚本信息
        Map<String,Object> scriptMap = (Map<String,Object>)dataRepo.getScriptMap().get(interfaceName);
        Map<String,Object> requestDataMap = (Map<String,Object>)scriptMap.get("RequestData");
        dataRepo.setInterfaceScriptMap(requestParamMap);
        requestMessage.consistRequestBody(requestDataMap);
        getContext().setJsonBody(JSON.toJSONString(dataRepo.getInterfaceScriptMap()));


    }

    @Override
    protected void send() {

    }

    @Override
    protected void preHandlerAfterSend(String interfaceName) {

    }
}
