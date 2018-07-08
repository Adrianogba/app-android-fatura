package io.github.adrianogba.desafioconductor.util.barchart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        //mFormat = new DecimalFormat("###.###.###.##0,0");
        mFormat = new DecimalFormat("#,##0.00");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + "R$";
    }
}