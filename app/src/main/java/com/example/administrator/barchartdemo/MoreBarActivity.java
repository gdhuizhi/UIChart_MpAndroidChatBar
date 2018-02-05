package com.example.administrator.barchartdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.barchartdemo.bean.AppBean;
import com.example.administrator.barchartdemo.bean.Apps;
import com.example.administrator.barchartdemo.morebar.AppInfoCountValueFormatter;
import com.example.administrator.barchartdemo.morebar.AppInfoXAxisFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guofh on 2018/1/26.
 */

public class MoreBarActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private BarChart mAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initList();


    }

    private void initList() {
        ArrayList<AppBean> appBeans = new ArrayList<>();
        ArrayList<Apps> appList = new ArrayList<>();

        Apps apps1 = new Apps();
        Apps apps2 = new Apps();
        Apps apps3 = new Apps();
        apps1.setAppName("微信");
        apps1.setUseCount(3);
        apps2.setAppName("qq");
        apps2.setUseCount(4);
        apps3.setAppName("陌陌");
        apps3.setUseCount(5);
        appList.add(apps1);
        appList.add(apps2);
        appList.add(apps3);

        for (int i = 0; i < 3; i++) {
            AppBean appBean = new AppBean();
            appBean.setPhoneName("1368954"+i);
            appBean.setAppsList(appList);
            appBeans.add(appBean);
        }

        initAppChart(appBeans);
    }

    private void initView() {
        //柱状图
        mAppBar = (BarChart) findViewById(R.id.mBarChart);
        findViewById(R.id.btn_more).setVisibility(View.GONE);

    }


    private void initAppChart(ArrayList<AppBean> appList) {
        //设置表格上的点，被点击的时候，的回调函数
        mAppBar.setOnChartValueSelectedListener(this);
        mAppBar.setDrawBarShadow(false);
        mAppBar.setDrawValueAboveBar(true);
        mAppBar.getDescription().setEnabled(false);
        // 如果60多个条目显示在图表,drawn没有值
        mAppBar.setMaxVisibleValueCount(10);
        // 扩展现在只能分别在x轴和y轴
        mAppBar.setPinchZoom(false);
        //是否显示表格颜色
        mAppBar.setDrawGridBackground(false);
        mAppBar.getXAxis().setLabelRotationAngle(-60);



        XAxis xAxis = mAppBar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        float total = appList.size()*3+(appList.size()-1)*2+4;
        xAxis.setLabelCount((int) (total+1));
        xAxis.setAxisMaximum(total);
        xAxis.setAxisMinimum(0f);
//        xAxis.setValueFormatter(appXValueFormatter);

        //设置悬浮
//        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
//        mv.setChartView(mBarChart);
//        mBarChart.setMarker(mv);

        YAxis leftAxis = mAppBar.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(30f);
        //这个替换setStartAtZero(true)
//        leftAxis.setAxisMinimum(Float.parseFloat(list.get(4).getRelateCount())*3/10);
//        leftAxis.setAxisMaximum(Float.parseFloat(list.get(0).getRelateCount())*100/85);

        YAxis rightAxis = mAppBar.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(30f);
//        rightAxis.setAxisMinimum(Float.parseFloat(list.get(4).getRelateCount())*3/10);
//        rightAxis.setAxisMaximum(Float.parseFloat(list.get(0).getRelateCount())*100/85);

        // 设置标示，就是那个一组y的value的
        Legend l = mAppBar.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        //隐藏 legend 这里 legend 最好自己绘制
        l.setEnabled(false);

        //模拟数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();

        int[] ints = new int[appList.size()*3];
        int j = 0 ;

        for (int i = 0; i < appList.size() ; i++) {
            //加入 每个信息对应的 三组 数据
            List<Apps> apps = appList.get(i).getAppsList();
            // 取前三个常用的app
            if(apps!=null&&apps.size()!=0){
                yVals1.add(new BarEntry((i+1)*3+(i+1)*2, Float.valueOf(apps.get(0).getUseCount()),apps.get(0).getAppName()));
                yVals1.add(new BarEntry((i+1)*3+(i+1)*2-1, Float.valueOf(apps.get(1).getUseCount()),apps.get(1).getAppName()));
                yVals1.add(new BarEntry((i+1)*3+(i+1)*2-2, Float.valueOf(apps.get(2).getUseCount()),apps.get(2).getAppName()));
                integerStringHashMap.put(((i+1)*3+(i+1)*2-1),appList.get(i).getPhoneName());
                ints[j] = ColorTemplate.COLORFUL_COLORS[j%5];
                ints[j+1] = ColorTemplate.COLORFUL_COLORS[j%5];
                ints[j+2] = ColorTemplate.COLORFUL_COLORS[j%5];
                j+=3 ;
            }

        }

        AppInfoXAxisFormatter appInfoCountFormatter = new AppInfoXAxisFormatter(integerStringHashMap);

        xAxis.setValueFormatter(appInfoCountFormatter);

        setAppChartData(yVals1,appList,ints);
        mAppBar.animateY(3000);

    }

    public void setAppChartData(ArrayList<BarEntry> yVals1, ArrayList<AppBean> applist, int[] ints) {

        float start = 1f;
        BarDataSet set1;
        if (mAppBar.getData() != null &&
                mAppBar.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mAppBar.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mAppBar.getData().notifyDataChanged();
            mAppBar.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "App使用记录统计");
            //设置有四种颜色
            set1.setColors(ints);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.6f);

            AppInfoCountValueFormatter appInfoVlaueFormatter = new AppInfoCountValueFormatter();

            data.setValueFormatter(appInfoVlaueFormatter);
            //设置数据
            mAppBar.setData(data);
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
