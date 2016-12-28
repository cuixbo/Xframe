package com.xbc.xframe.model.bean;

import com.xbc.xframe.net.BaseResponse;

/**
 * Created by xiaobo.cui on 2016/10/9.
 */
public class WeatherResponseBean extends BaseResponse {

    public WeatherInfo weatherInfo;

    public class WeatherInfo {
        public String city;
        public String cityid;
        public String temp1;
        public String temp2;
        public String weather;
        public String img1;
        public String img2;
        public String ptime;
    }
}
