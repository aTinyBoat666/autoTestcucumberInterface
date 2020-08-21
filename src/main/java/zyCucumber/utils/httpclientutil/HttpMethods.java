package zyCucumber.utils.httpclientutil;

public enum HttpMethods {
    GET(0,"GET"),POST(1,"POST"),PUT(2,"PUT"),DELETE(3,"DELETE");
    private int code;
    private String name;

    private HttpMethods(int code,String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }
}
