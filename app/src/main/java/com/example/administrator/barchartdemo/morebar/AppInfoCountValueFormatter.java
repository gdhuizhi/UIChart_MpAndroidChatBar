package com.example.administrator.barchartdemo.morebar;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by Administrator on 2018/1/17.
 */

public class AppInfoCountValueFormatter implements IValueFormatter {




    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // 返回的数据 必须保证为 1.0 ，2.0 ， 3.0

        String data = (String) entry.getData();

        return data;




    }
}
