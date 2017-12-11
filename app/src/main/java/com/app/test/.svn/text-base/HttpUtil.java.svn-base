package com.app.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.util.Log;

/**
 *  * @Description:HttpUrlConnection封装
 *  * @author:axin
 *  * @time:2016-10-8 下午3:25:52
 */
public class HttpUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     *  * @Description:发送安全get请求
     *  * @param @param url 请求的URL
     *  * @param @return
     *  * @param @throws NoSuchAlgorithmException
     *  * @param @throws NoSuchProviderException
     *  * @param @throws IOException
     *  * @param @throws KeyManagementException
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午10:55:52
     */
    private static String get(String url) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new HttpX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        URL urlGet = new URL(url);
        HttpsURLConnection https = (HttpsURLConnection) urlGet.openConnection();
        // 连接超时
        https.setConnectTimeout(25000);
        // 读取超时
        https.setReadTimeout(25000);
        https.setRequestMethod("GET");
        https.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        https.setSSLSocketFactory(ssf);
        https.setDoOutput(true);
        https.setDoInput(true);
        https.connect();

        InputStream in = https.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (https != null) {
            // 关闭连接
            https.disconnect();
        }
        return bufferRes.toString();
    }

    /**
     *  * @Description:发送get请求
     *  * @param @param url
     *  * @param @param https 是否支持https
     *  * @param @return
     *  * @param @throws NoSuchAlgorithmException
     *  * @param @throws NoSuchProviderException
     *  * @param @throws IOException
     *  * @param @throws KeyManagementException
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午11:21:33
     */
    public static String get(String url, Boolean https) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        if (https != null && https) {
            return get(url);
        } else {
            StringBuffer bufferRes = null;
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 连接超时
            http.setConnectTimeout(25000);
            // 读取超时
            http.setReadTimeout(25000);
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();

            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            in.close();
            if (http != null) {
                // 关闭连接
                http.disconnect();
            }
            return bufferRes.toString();
        }
    }

    /**
     *  * @Description:发送带参数的get请求
     *  * @param @param url
     *  * @param @param params
     *  * @param @return
     *  * @param @throws KeyManagementException
     *  * @param @throws NoSuchAlgorithmException
     *  * @param @throws NoSuchProviderException
     *  * @param @throws IOException
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午11:23:47
     */
    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        return get(initParams(url, params));
    }

    /**
     *  * @Description:发送安全post请求
     *  * @param @param url
     *  * @param @param params
     *  * @param @return
     *  * @param @throws IOException
     *  * @param @throws NoSuchAlgorithmException
     *  * @param @throws NoSuchProviderException
     *  * @param @throws KeyManagementException
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午11:24:12
     */
    private static String post(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new HttpX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        URL urlGet = new URL(url);
        HttpsURLConnection https = (HttpsURLConnection) urlGet.openConnection();
        // 连接超时
        https.setConnectTimeout(25000);
        // 读取超时
        https.setReadTimeout(25000);
        https.setRequestMethod("POST");
        https.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        https.setSSLSocketFactory(ssf);
        https.setDoOutput(true);
        https.setDoInput(true);
        https.connect();

        OutputStream out = https.getOutputStream();
        out.write(params.getBytes(DEFAULT_CHARSET));
        out.flush();
        out.close();

        InputStream in = https.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (https != null) {
            // 关闭连接
            https.disconnect();
        }
        return bufferRes.toString();
    }

    /**
     *  * @Description:发送post请求
     *  * @param @param url
     *  * @param @param params
     *  * @param @param https 是否支持https
     *  * @param @return
     *  * @param @throws IOException
     *  * @param @throws NoSuchAlgorithmException
     *  * @param @throws NoSuchProviderException
     *  * @param @throws KeyManagementException
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午1:42:10
     */
    public static String post(String url, String params, Boolean https) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        if (https != null && https) {
            return post(url, params);
        } else {
            StringBuffer bufferRes = null;
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 连接超时
            http.setConnectTimeout(25000);
            // 读取超时
            http.setReadTimeout(25000);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();

            OutputStream out = http.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();

            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            in.close();
            if (https != null) {
                // 关闭连接
                http.disconnect();
            }
            return bufferRes.toString();
        }
    }

    /**
     *  * @Description:创建动态URL
     *  * @param @param url
     *  * @param @param params
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午11:24:58
     */
    private static String initParams(String url, Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=");
            if (!value.isEmpty()) {
                try {
                    sb.append(URLEncoder.encode(value, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("url", "url exception:" + url);
                }
            }
        }
        return sb.toString();
    }

    /**
     *  * @Description:X509证书管理
     *  * @author:axin
     *  * @time:2016-10-25 下午1:48:50
     */
    public static class HttpX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
}
