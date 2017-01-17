package com.xbc.xframe.net;

/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public abstract class Response {

    public Request request = null;

    public int statusCode = 0;

    public String result = null;


    /**
     * HTTP Status Code=200
     *
     * @return
     */
    public boolean isSuccess() {
        return statusCode == 200;
    }


    /**
     * @return HTTP Status Code
     */
    public int getStatusCode() {
        return statusCode;
    }

}
