package com.example.administrator.barchartdemo.morebar;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/17.
 */

public class AppInfoXAxisFormatter implements IAxisValueFormatter {


    private final HashMap<Integer, String> appMap;

    public AppInfoXAxisFormatter(HashMap<Integer, String> appMap) {

        this.appMap = appMap ;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int i = (int) value;
        String s = appMap.get(i);
        if("".equals(s)||s==null){
            return "" ;
        }else {
            return s ;
        }
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
