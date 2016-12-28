package com.xbc.xframe.api;

/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public class ApiImpl implements IApi {
    public static ApiImpl INSTANCE = new ApiImpl();

    private ApiImpl() {

    }

    public static ApiImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public void getWeather() {

    }

}
