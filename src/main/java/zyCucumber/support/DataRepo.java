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
     * 接口信息
     */
    private Map<String,Object> interfaceScriptMap;

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

    public Map<String, Object> getEnvironmentMap() {
        return environmentMap;
    }

    public void setEnvironmentMap(Map<String, Object> environmentMap) {
        this.environmentMap = environmentMap;
    }

    /**
     * 读取运行的环境信息
     * @param envName
     */
    public void readEnvironmentMap(String envName) {
        Map<String,Object> map  = FileUtils.getResourceMap(envName);
        setEnvironmentMap(map);
    }

    /**
     * 读取脚本信息
     * @param scriptPath
     */
    public void readScriptMap(String scriptPath) {
       Map<String,Object> map =  FileUtils.getResourceMap(scriptPath);
       this.setScriptMap(map);
    }

    /**
     * 读取接口信息
     * @param interfaceScriptPath 接口文件路径（全路径）
     */
    public void readInterfaceScript(String interfaceScriptPath) {
        Map<String,Object> map = FileUtils.getResourceMap(interfaceScriptPath);
        this.setInterfaceScriptMap(map);
    }

    public Map<String, Object> getInterfaceScriptMap() {
        return interfaceScriptMap;
    }

    public void setInterfaceScriptMap(Map<String, Object> interfaceScriptMap) {
        this.interfaceScriptMap = interfaceScriptMap;
    }

    public Map<String, Object> getScriptMap() {
        return scriptMap;
    }

    public void setScriptMap(Map<String, Object> scriptMap) {
        this.scriptMap = scriptMap;
    }
}
