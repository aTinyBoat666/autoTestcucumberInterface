package zyCucumber.support;

import zyCucumber.utils.FileUtils;

import java.util.Map;

public class DataRepo {
    /**
     * 存放全局配置信息
     */
    private Map<String,Object> globalSetingMap;

    /**
     * 存放环境信息
     */
    private Map<String,Object> environmentMap;

    /**
     * 存放脚本信息
     */
    private Map<String, Object> scriptMap;

    /**
     * 初始化
     */
    public void init() {
        readGlobalSettingData();
    }

    /**
     * 读取全局配置信息
     */
    public void  readGlobalSettingData() {
        globalSetingMap = FileUtils.getResourceMap("zyCucumber/supportInformation/globalSetting.yaml");
    }

    /**
     * 获取全局配置信息
     * @return
     */
    public  Map<String,Object> getGlobalSetingMap() {
        return  this.globalSetingMap;
    }

    /**
     * 获取所运行的环境id
     * @return
     */
    public String getEenironment() {
        return  System.getProperty("automation.environment");
    }

    /**
     * 读取运行的环境信息
     * @param envName
     */
    public void readEnvironmentMap(String envName) {
        environmentMap = FileUtils.getResourceMap(envName);
    }

    /**
     * 读取脚本信息
     * @param scriptPath
     */
    public void readScriptMap(String scriptPath) {
       scriptMap = FileUtils.getResourceMap(scriptPath);
    }
}
