package com.example.administrator.barchartdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    //初始化
    private void initView() {
        Button btnMore = (Button) findViewById(R.id.btn_more);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoreBarActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //柱状图
        mBarChart = (BarChart) findViewById(R.id.mBarChart);
        //设置柱状图点击的时候，的回调函数
        mBarChart.setOnChartValueSelectedListener(this);
        //柱状图的阴影
        mBarChart.setDrawBarShadow(false);
        //设置柱状图Value值显示在柱状图上方 true 为显示上方，默认false value值显示在柱状图里面
        mBarChart.setDrawValueAboveBar(true);
        //Description Label 是否可见
        mBarChart.getDescription().setEnabled(false);
        // 设置最大可见Value值的数量 针对于ValueFormartter有效果
        mBarChart.setMaxVisibleValueCount(60);
        // 二指控制X轴Y轴同时放大
        mBarChart.setPinchZoom(false);
        //是否显示表格背景颜色
        mBarChart.setDrawGridBackground(false);
        //设置X轴显示文字旋转角度-60意为逆时针旋转60度
        mBarChart.getXAxis().setLabelRotationAngle(-60);


        XAxis xAxis = mBarChart.getXAxis();
        //设置X轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //X轴纵向分割线，一般不设置显示
        xAxis.setDrawGridLines(false);
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        xAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        xAxis.setLabelCount(7);
        //X轴最大坐标
        xAxis.setAxisMaximum(6f);
        //X轴最小坐标
        xAxis.setAxisMinimum(0.5f);
        //自定义X轴
        IAxisValueFormatter xAxisFormatter = new CallCountFormatter(mBarChart);
        xAxis.setValueFormatter(xAxisFormatter);



        //点击柱状图时显示的悬浮提示
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(mBarChart);
        mBarChart.setMarker(mv);

        //Y左边轴
        YAxis leftAxis = mBarChart.getAxisLeft();
        //设置Y左边轴显示的值 label 数量
        leftAxis.setLabelCount(8, false);
        //设置值显示的位置，我们这里设置为显示在Y轴外面
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置Y轴 与值的空间空隙 这里设置30f意为30%空隙，默认是10%
        leftAxis.setSpaceTop(30f);
        //设置Y轴最小坐标和最大坐标
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(80f);
        leftAxis.setValueFormatter(new BarLeftYValueFormatter());

        //Y轴右边轴的设置，跟左边轴类似
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(30f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(80f);

        // 设置表格标示的位置
        Legend l = mBarChart.getLegend();
        //标示坐落再表格左下方
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //标示水平朝向
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //标示在表格外
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);

        //模拟数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(1.2f, 10,"微信"));
        yVals1.add(new BarEntry(2.2f, 20,"qq"));
        yVals1.add(new BarEntry(3.2f, 30,"陌陌"));
        yVals1.add(new BarEntry(4.2f, 40,"百度"));
        yVals1.add(new BarEntry(5.2f, 50,"支付宝"));

        setData(yVals1);

    }

    //设置数据
    private void setData(ArrayList yVals1) {
        float start = 1f;
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "号码联系次数统计");
            //设置有四种颜色
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.6f);
            data.setValueFormatter(new CallCountValueFormatter());
            //设置数据
            mBarChart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
