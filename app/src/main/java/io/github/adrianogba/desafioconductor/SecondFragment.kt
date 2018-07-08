package io.github.adrianogba.desafioconductor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.second_fragment.*

import io.github.adrianogba.desafioconductor.util.barchart.MyAxisValueFormatter
import io.github.adrianogba.desafioconductor.util.barchart.XYMarkerView
import android.support.v4.content.ContextCompat

import io.github.adrianogba.desafioconductor.util.barchart.LabelFormatter



class FirstFragment : Fragment(), OnChartValueSelectedListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadChart()

    }



    private fun loadChart(){

        barChart.setNoDataText("Carregando...")
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled=false
        barChart.setMaxVisibleValueCount(22)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)
        barChart.isDoubleTapToZoomEnabled=false


        val custom:IAxisValueFormatter = MyAxisValueFormatter()

        val leftAxis = barChart.axisLeft
        //leftAxis.setTypeface(mTfLight);
        leftAxis.valueFormatter=custom
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop=15f
        leftAxis.axisMinimum=0f

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(false)
        //rightAxis.setTypeface(mTfLight);
        rightAxis.isEnabled=false

        val l = barChart.legend
        l.verticalAlignment=Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment=Legend.LegendHorizontalAlignment.LEFT
        l.orientation=Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form=Legend.LegendForm.SQUARE
        l.formSize=9f
        l.textSize=11f
        l.xEntrySpace=4f



        //setData(12,50f)

    }
     fun setData(labels:ArrayList<String>, yVals1:ArrayList<BarEntry>){

         val xAxisFormatter = LabelFormatter(labels)
         val xAxis = barChart.xAxis
         xAxis.position=XAxis.XAxisPosition.BOTTOM
         //xAxis.setTypeface(mTfLight)
         xAxis.setDrawGridLines(false)
         xAxis.granularity=1f
         xAxis.labelCount = 7
         xAxis.valueFormatter=xAxisFormatter

         val mv = XYMarkerView(context, xAxisFormatter)
         mv.chartView=barChart
         barChart.marker=mv

         //barChart.xAxis.valueFormatter = LabelFormatter(labels)

        val set1:BarDataSet

        if (barChart.data!=null && barChart.data.dataSetCount>0){

            set1 = barChart.data.getDataSetByIndex(0) as BarDataSet
            set1.values=yVals1
            barChart.data.notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1= BarDataSet(yVals1, "Compras")

            set1.setDrawIcons(false)

            val colors = ArrayList<Int>()

            colors.add(ContextCompat.getColor(context, android.R.color.holo_orange_light))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_blue_light))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_orange_light))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_green_light))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_red_light))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_orange_dark))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_orange_dark))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_purple))
            colors.add(ContextCompat.getColor(context, android.R.color.holo_blue_bright))

            set1.colors=colors


            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            //data.setValueTypeface(mTfLight);
            data.barWidth=0.9f

            barChart.data=data

        }

    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }


}