package com.example.sunnyweather.ui.place


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.MainActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.ui.weather.WeatherActivity
import com.example.sunnyweather.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment :Fragment() {
    val viewModel by lazy { androidx.lifecycle.ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter:PlaceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity&&viewModel.isSavePlace()){
            val place=viewModel.getSavePlace()
            val intent= Intent(context,WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        val layoutManager=LinearLayoutManager(activity)
        recyclerview.layoutManager=layoutManager
        adapter=PlaceAdapter(this,viewModel.placeList)
        recyclerview.adapter=adapter
        searchPlaceEdit.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                val content=editable.toString()
                if (content.isNotEmpty()){
                    viewModel.searchPlaces(content)
                }else{
                    recyclerview.visibility=View.GONE
                    bgImageView.visibility=View.VISIBLE
                    viewModel.placeList.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        })
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer {result->
            val places=result.getOrNull()
            if (places!=null){
                recyclerview.visibility=View.VISIBLE
                bgImageView.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能咨询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}