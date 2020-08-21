package zyCucumber.interfaceUtils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zyCucumber.support.DataRepo;

import java.util.ArrayList;
import java.util.Map;

@Component
public class GetDataUtilCls {
    @Autowired
    private DataRepo dataRepo;
    private static final Logger logger = Logger.getLogger(GetDataUtilCls.class);

    private String key;
    private ArrayList<String> data;
    private String fromSourceType;
    private Object dataGetMode;
    private String dataMode;
    private String dataReplaceExpress;
    private Object dataValue = null;

    public void setData(Object data) {
        this.data = (ArrayList<String>) data;
    }

    public void parseGetDataMode() {
        this.fromSourceType = (String)this.data.get(0);
        this.dataGetMode = this.data.get(1);
        this.dataMode = this.data.get(2);
        this.dataReplaceExpress = this.data.get(3);
        switch (fromSourceType){
            case "fromPublicParams":
                Map<String,Object> publicParams = (Map<String,Object>)dataRepo.getScriptMap().get("PublicParams");
                if(publicParams != null && publicParams.containsKey(this.dataGetMode)){
                    this.dataValue = (String)publicParams.get(this.dataGetMode);
                }else{
                    logger.info("该测试用例中公共参数部分不包含该参数"+ dataGetMode);
                }
                break;

        }
    }

    public void setDataByDataModeExpress() {
        switch (dataMode){
            case "ByJsonMode":
                Map<String,Object> interfaceMap = dataRepo.getInterfaceScriptMap();
                DocumentContext ect = JsonPath.parse(interfaceMap);
                JsonPath jp = JsonPath.compile(dataReplaceExpress,new Predicate[0]);
                ect.set(jp,this.dataValue);
                break;
            default:
        }
    }

    public void setKey(String key) {
        this.key = key;
    }


}
