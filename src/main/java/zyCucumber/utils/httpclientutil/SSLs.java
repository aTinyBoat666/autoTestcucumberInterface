package zyCucumber.utils.httpclientutil;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SSLs {

    private static final SSLs.SSLHandler simpleVerfier = new SSLHandler();
    private static SSLSocketFactory sslFactory;
    private static SSLConnectionSocketFactory sslConnFactory;
    private static SSLIOSessionStrategy sslioSessionStrategy;
    private static SSLs sslutil = new SSLs();
    private SSLContext sc;

    public SSLs() {

    }

    public static SSLs getInstance() {
        return  sslutil;
    }

    public static SSLs custom() {
        return  new SSLs();
    }

    public static HostnameVerifier getVerifier() {
        return simpleVerfier;
    }

    public synchronized  SSLSocketFactory getSSLSF(SSLs.SSLProtocolVersion sslpv) throws  KeyManagementException ,NoSuchAlgorithmException  {
        if(sslFactory != null) {
            return sslFactory;
        }else{
           try {
               SSLContext sc = this.getSSLContext(sslpv);
               sc.init((KeyManager[])null,new TrustManager[]{simpleVerfier},new SecureRandom());
               sslFactory = sc.getSocketFactory();
           }catch (KeyManagementException | NoSuchAlgorithmException e){
               throw new RuntimeException(e);
           }
            return  sslFactory;
        }
    }

    public synchronized  SSLConnectionSocketFactory getSSLCONNSF(SSLs.SSLProtocolVersion sslpv) throws  KeyManagementException,NoSuchAlgorithmException {
        if(sslConnFactory != null) {
            return sslConnFactory;
        }else {
            try{
                SSLContext sc = this.getSSLContext(sslpv);
                sc.init((KeyManager[])null,new TrustManager[]{simpleVerfier},new SecureRandom());
                sslConnFactory = new SSLConnectionSocketFactory(sc,simpleVerfier);
            }catch (KeyManagementException | NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
           return sslConnFactory;
        }
    }

    public SSLs customSSL(String keyStorePath,String keyStorepass)  throws  Exception{
        FileInputStream inputStream = null;
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            inputStream = new FileInputStream(new File(keyStorePath));
            trustStore.load(inputStream, keyStorepass.toCharArray());
            this.sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            }catch (IOException e){

            }
        }
        return  this;
    }

    public synchronized  SSLIOSessionStrategy getSSLIOSS(SSLs.SSLProtocolVersion sslpv) throws   KeyManagementException,NoSuchAlgorithmException {
        if(sslioSessionStrategy != null) {
            return sslioSessionStrategy;
        }else {
            try{
                SSLContext sc = this.getSSLContext(sslpv);
                sc.init((KeyManager[])null,new TrustManager[]{simpleVerfier},new SecureRandom());
                sslioSessionStrategy = new SSLIOSessionStrategy(sc,simpleVerfier);
            }catch (KeyManagementException | NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
            return sslioSessionStrategy;
        }
    }

    public SSLContext getSSLContext(SSLProtocolVersion sslpv) throws  NoSuchAlgorithmException  {
       try {
           if(this.sc == null) {
               this.sc = SSLContext.getInstance(sslpv.getName());
           }
           return  sc;
       }catch (NoSuchAlgorithmException e){
           throw new RuntimeException(e);
       }

    }

    public static enum  SSLProtocolVersion{
        SSL("SSL"),
        SSLv3("SSLv3");
        private String name;

        private SSLProtocolVersion(String name) {
            this.name = name;
        }

        public  String getName() {
            return  this.name;
        }

        public static SSLs.SSLProtocolVersion find(String name) {
            SSLs.SSLProtocolVersion[] var1 = values();
            int var2 = var1.length;
            for (int var3 = 0; var3 < var2; ++var3){
                SSLs.SSLProtocolVersion pv = var1[var3];
                if(pv.getName().toUpperCase().equals(name.toUpperCase())) {
                    return  pv;
                }
            }
            throw new RuntimeException("未支持当前ssl版本号：" + name);
        }
    }

    private static class SSLHandler implements X509TrustManager, HostnameVerifier{
        private SSLHandler() {

        }

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
