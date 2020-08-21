package zyCucumber.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zyCucumber.interfaceUtils.GetDataUtilCls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class RequestMessage {
    @Autowired
    private GetDataUtilCls getDataUtilCls;
    
    public void consistRequestBody(Map<String,Object> requestDataMap){
        if(requestDataMap != null && requestDataMap.size() > 0) {
            Iterator iterator = requestDataMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String)iterator.next();
                Object value = requestDataMap.get(key);
                getDataByMode(value,key);
            }
        }
        
    }

    public void getDataByMode(Object value, String key) {
        ArrayList<String> dataList = null;
        String var1 = value.getClass().toString();
        byte var2 = -1;
        switch (var1.hashCode()) {
            case 1421687343:
                if(var1.equals("class java.util.ArrayList")) {
                    var2 = 0;
                }
            default:
                switch (var2){
                    case 0:
                        dataList = (ArrayList) value;
                        getDataUtilCls.setKey(key);
                        getDataUtilCls.setData(dataList);
                        getDataUtilCls.parseGetDataMode();
                        getDataUtilCls.setDataByDataModeExpress();
                    default:
                }
        }
    }
}
