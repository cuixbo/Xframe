package com.xbc.xframe.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public abstract class Request {

    public String _device;

    public String _app;

    public String url;

    public Map<String, String> param = new HashMap<String, String>();

    public Map<String, String> getParameter() {
        return param;
    }
}
