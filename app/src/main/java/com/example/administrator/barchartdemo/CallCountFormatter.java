package com.example.administrator.barchartdemo;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import static android.content.ContentValues.TAG;

/**
 * Created by guofh on 2018/1/15.
 */

public class CallCountFormatter implements IAxisValueFormatter {
    private final BarLineChartBase<?> mChart;
    public CallCountFormatter(BarLineChartBase<?> chart) {
        this.mChart = chart ;

    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.i(TAG,"getFormattedValue-------------"+value);

        Log.i(TAG,"mChart.getVisibleXRange()-------------"+mChart.getVisibleXRange());
        if(value==0.0 || value == 6.0){
            return "" ;
        }else {
            return "13685624925";
        }
    }
    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
