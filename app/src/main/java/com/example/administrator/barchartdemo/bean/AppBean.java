package com.example.administrator.barchartdemo.bean;

import java.util.List;

/**
 * Created by guofh on 2018/1/26.
 */

public class AppBean {

    private String phoneName ;
    private List<Apps> appsList ;

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public List<Apps> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Apps> appsList) {
        this.appsList = appsList;
    }
}
