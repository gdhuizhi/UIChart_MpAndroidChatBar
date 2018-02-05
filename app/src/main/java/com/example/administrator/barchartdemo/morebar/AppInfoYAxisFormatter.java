package com.example.administrator.barchartdemo.morebar;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by guofh on 2018/1/18.
 */

public class AppInfoYAxisFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return (int)value+"次";
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
