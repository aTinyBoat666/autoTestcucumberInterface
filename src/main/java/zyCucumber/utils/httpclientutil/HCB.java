package zyCucumber.utils.httpclientutil;

import org.apache.http.impl.client.HttpClientBuilder;
import zyCucumber.utils.httpclientutil.SSLs.SSLProtocolVersion;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class HCB extends HttpClientBuilder {
    public boolean isSetPool = false;
    private SSLProtocolVersion sslpv;
    private SSLs ssls;

    private HCB(){
        this.sslpv = SSLProtocolVersion.SSLv3;
        this.ssls = SSLs.getInstance();
    }

    public static HCB custom() {
        return new HCB();
    }

    public HCB ssl() throws KeyManagementException, NoSuchAlgorithmException {
        return (HCB) this.setSSLSocketFactory(this.ssls.getSSLCONNSF(this.sslpv));
    }

    public HCB sslpv(String sslpv) {
        return this.sslpv(SSLProtocolVersion.find(sslpv));
    }

    public HCB sslpv(SSLProtocolVersion sslpv) {
        this.sslpv = sslpv;
        return this;
    }



}

