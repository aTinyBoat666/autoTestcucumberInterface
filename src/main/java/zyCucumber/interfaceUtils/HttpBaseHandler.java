package zyCucumber.interfaceUtils;

public abstract class HttpBaseHandler {
    public HttpBaseHandler(){}

    /**
     * 初始化对应请求的接口信息
     * @param interfaceName 接口名称
     */
    protected abstract void initRequestInterfaceData(String interfaceName);

    /**
     * 请求的Url
     */
    protected abstract void preHandlerForUrl();

    /**
     * 请求的type
     */
    protected abstract void preHandlerForType();

    /**
     * 请求头信息
     */
    protected abstract void preHandlerForHeader();

    /**
     * 请求体信息
     * @param interfaceName 接口名称
     */
    protected abstract void preHandlerForJsonBody(String interfaceName);

    /**
     * 发送信息
     */
    protected abstract void send();

    /**
     * 请求后的处理
     * @param interfaceName 接口名称
     */
    protected abstract void preHandlerAfterSend(String interfaceName);

    /**
     * 请求处理方法
     * @param interfaceName 接口名称
     */
    public void preRequestProcess(String interfaceName) {
        initRequestInterfaceData(interfaceName);
        preHandlerForUrl();
        preHandlerForType();
        preHandlerForHeader();
        preHandlerForJsonBody(interfaceName);
        send();
        preHandlerAfterSend(interfaceName);
    }
}
