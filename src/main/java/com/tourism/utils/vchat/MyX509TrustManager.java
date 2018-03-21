package com.tourism.utils.vchat;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by ppm on 2017/8/31.
 */
public class MyX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
