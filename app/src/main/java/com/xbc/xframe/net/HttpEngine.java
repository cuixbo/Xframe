package com.xbc.xframe.net;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public class HttpEngine {
    public static final int DEFAULT_CONNECT_TIMEOUT = 10 * 1000;
    public static final int DEFAULT_READ_TIMEOUT = 20 * 1000;
    public static final String GET = "GET";
    public static final String POST = "POST";


    public static HttpEngine INSTANCE = new HttpEngine();

    private HttpEngine() {

    }

    public static HttpEngine getInstance() {
        return INSTANCE;
    }


    public static Response execute(String method, Request request) throws Exception {
        Response mResponse = new BaseResponse();
        mResponse.request = request;
        HttpURLConnection connection = null;
        String url = request.url;
        if (GET.equalsIgnoreCase(method)) {
            url = getGetParameterUrl(request.url, request.param);
        }
        URL connUrl = new URL(url);
        connection = (HttpURLConnection) connUrl.openConnection();


        connection.setUseCaches(false);
//        connection.setRequestProperty("Connection", "close");
        connection.addRequestProperty("Content-type", "application/x-java-serialized-object;application/x-www-form-urlencoded;charset=utf-8");
        connection.setRequestMethod(method);
        connection.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
        connection.setReadTimeout(DEFAULT_READ_TIMEOUT);

        if (POST.equalsIgnoreCase(method)) {
            connection.setDoOutput(true);
            byte[] postBody = getParameterByte(request.getParameter());
            //发送请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(postBody);
            out.close();
        }

        //接受响应数据
        int code = connection.getResponseCode();
        mResponse.statusCode = code;

        InputStream in = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
        StringBuffer result = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        mResponse.result = result.toString();
        return mResponse;
    }

    public static Response execute(Request request) throws Exception {
        return execute(GET, request);
    }


    /**
     * param转化为byte[]
     *
     * @param param
     * @return
     */
    private static byte[] getParameterByte(Map<String, String> param) {
        byte[] bytes = new byte[0];
        StringBuffer paramString = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                paramString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (paramString.toString().endsWith("&")) {
            String ret = paramString.substring(0, paramString.lastIndexOf("&")).toString();
            bytes = ret.getBytes();
        }
        return bytes;
    }

    /**
     * 获取GET方式的 带参数的url
     *
     * @param url
     * @param param
     * @return
     */
    private static String getGetParameterUrl(String url, Map<String, String> param) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String getUrl = url;
        String parameterString = "";
        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (buffer.toString().endsWith("&")) {
            parameterString = buffer.substring(0, buffer.lastIndexOf("&")).toString();
            if (url.contains("?")) {
                if (url.endsWith("&")) {
                    getUrl = url + parameterString;
                } else {
                    getUrl = url + "&" + parameterString;
                }
            } else {
                getUrl = url + "?" + parameterString;
            }

        }
        return getUrl;
    }


}
