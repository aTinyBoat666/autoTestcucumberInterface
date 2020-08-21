package zyCucumber.projectSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import zyCucumber.interfaceUtils.HttpHandler;
import zyCucumber.support.DataRepo;

import java.util.Iterator;
import java.util.Map;

public class DemoStep {
    private static final Logger logger = Logger.getLogger(DemoStep.class);
    @Autowired
    private DataRepo dataRepo;
    @Autowired
    private HttpHandler httpHandler;

    @Given("初始化数据<(.+)>")
    public void initData(String userName) {
        Map<String,Object> map = dataRepo.getGlobalSetingMap();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        logger.info("执行步骤开始,初始化数据为:" + userName);
    }

    @When("^测试接口调用<(.+)>,密码<(.+)>$")
    public void requestInterface(String interfaceStr) {
        logger.info("请求的接口名称为:"+ interfaceStr);
        httpHandler.preRequestProcess(interfaceStr);
    }
}
