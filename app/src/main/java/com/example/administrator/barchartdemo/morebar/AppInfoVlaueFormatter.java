package com.example.administrator.barchartdemo.morebar;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by guofh on 2018/1/22.
 */

public class AppInfoVlaueFormatter implements IValueFormatter{

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        int x = (int) entry.getX();
        return null;
    }
}
