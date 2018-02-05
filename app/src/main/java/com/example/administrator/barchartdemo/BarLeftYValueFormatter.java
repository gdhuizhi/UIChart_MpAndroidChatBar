package com.example.administrator.barchartdemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by guofh on 2018/1/25.
 */

public class BarLeftYValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return value+"";
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
