package zyCucumber.support;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import zyCucumber.utils.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Map;


/**
 * 执行测试脚本执行步骤
 */
public class StepsHandle {
    private static final  Logger logger = Logger.getLogger(StepsHandle.class);
    @Autowired
    private DataRepo dataRepo;

    @Before
    public void beforeScenario(Scenario scenario) {
        // 获取测试名称
        String name = scenario.getName();
        logger.info("测试名称"+ name);
        // 获取注解名称
        Collection<String> collection  = scenario.getSourceTagNames();
        // 获取用例脚本名称
        String caseInfo = AnnotationdealUtils.getCaseInfo(collection);
        logger.info("用例脚本名称:" + caseInfo);
        //判断是否存在用例脚本
        if(StringUtils.isBlank(caseInfo)) {
            Assert.fail("【" + name +"】" +"测试案例信息为空，请检查@CASE_INFO()的内容！");
        }
        // 获取全局配置信息
        Map<String,Object> globalSettingMap = dataRepo.getGlobalSetingMap();
        // 获取所运行的环境信息路径
        String envName = globalSettingMap.get("TEST_ENV_PATH") + File.separator + dataRepo.getEenironment() +File.separator + "envConfig.yaml";
        dataRepo.readEnvironmentMap(envName);
        //读取用例脚本信息
        if(caseInfo != null ) {
            String productName = null;
            String[] caseInfos = caseInfo.split("_");
            if(caseInfos != null && caseInfos.length == 2) {
                productName = caseInfos[0];
            }else {
                Assert.fail("脚本用例表达式编写有误");
            }
            // 构造用例脚本路径
            String scriptPath = globalSettingMap.get("CASE_FILE_PATH") + File.separator + productName + File.separator + caseInfo + ".yaml";
            logger.info("脚本文件路径为:" + scriptPath);
            dataRepo.readScriptMap(scriptPath);
        }else {
            Assert.fail("脚本文件名为空,请检查标签书写格式");
        }

    }

}
