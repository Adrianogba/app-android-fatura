package io.github.adrianogba.desafioconductor

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.*
import io.github.adrianogba.desafioconductor.adapter.ComprasListAdapter
import io.github.adrianogba.desafioconductor.model.Compra
import io.github.adrianogba.desafioconductor.model.Portador
import io.github.adrianogba.desafioconductor.util.minivolley.MiniVolley
import io.github.adrianogba.desafioconductor.util.minivolley.ServiceVolley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONObject
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    val firstfragment=FirstFragment()
    val secondfragment=SecondFragment()

    val service = ServiceVolley()
    val miniVolley = MiniVolley(service)

    val jsonParser = JsonParser()
    val gson = Gson()
    var loading=false
    var page=1

    val list = ArrayList<Compra>()
    internal lateinit var adapter: ComprasListAdapter

    val NUM_ITEMS = 2
    private lateinit var mPagerAdapter: SlidePagerAdapter
    private lateinit var myLayoutManager: RecyclerView.LayoutManager



    var gastos=0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter= ComprasListAdapter(list)
        myLayoutManager = LinearLayoutManager(this)
        rvListaCompras.layoutManager=myLayoutManager
        rvListaCompras.adapter=adapter

        mPagerAdapter = SlidePagerAdapter(supportFragmentManager)
        pager.adapter = mPagerAdapter
        tabs.setupWithViewPager(pager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.graph)
        tabs.getTabAt(1)!!.setIcon(R.drawable.creditcardicon)

        tabs.getTabAt(0)!!.icon!!.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        tabs.getTabAt(1)!!.icon!!.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

        pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (position > 1) {
                    // java.lang.IllegalArgumentException: No view found for id 0x7f070055 (com.example.viewpagerfragmentswap:id/root_frame) for fragment SecondFragment
                    for (i in 0 until supportFragmentManager.backStackEntryCount) {
                        supportFragmentManager.popBackStack()
                    }
                }
            }
        })

        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        //rvListaCompras.addOnScrollListener(RecyclerView.OnScrollListener())
        rvListaCompras.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down{
                    visibleItemCount = myLayoutManager.getChildCount()
                    totalItemCount = myLayoutManager.itemCount
                    pastVisiblesItems = (myLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (loading) {
                        if ( (myLayoutManager.childCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false
                            //getCompras()

                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            })
        //getPortador()
        getCompras()
    }

    inner class SlidePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            return if (position == 0) {
                firstfragment// This is where we create a RootFragment acting as a container for other fragments
            } else {
                secondfragment
            }
        }

        override fun getCount(): Int {
            return NUM_ITEMS
        }
    }




    val yVals1 = ArrayList<BarEntry>()
    val labels = ArrayList<String>()

    fun getCompras(){
        loading=false
        val params = JSONObject()
        //params.put("login", "teste")

        miniVolley.request(
                "https://2hm1e5siv9.execute-api.us-east-1.amazonaws.com/dev/card-statement?month=10&year=2018&page="+page,
                Request.Method.GET, params)
        { response ->

            val obj = jsonParser.parse(response) as JsonObject

            val mJson = obj.getAsJsonArray("purchases")


            if(response==null){

            } else if (mJson.size()==0){

            } else {
                for (i in 0 until mJson.size()){
                    val c = gson.fromJson(mJson.get(i), Compra::class.java)
                    gastos+=c.value.toFloat()
                    list.add(c)
                    yVals1.add(BarEntry(list.size.toFloat(), c.value.toFloat()))
                    //Toast.makeText(this, c.store, Toast.LENGTH_LONG).show()
                    labels.add(c.dateForLabel())
                }
                adapter.notifyDataSetChanged()

            }

            firstfragment.setData(labels,yVals1)
            val dec = DecimalFormat("###,###,##0.00")

            secondfragment.gastos.text= dec.format(gastos.toDouble()) + "R$"


            //page += 1
            adapter.notifyDataSetChanged()
            loading=true



        }


    }

}


