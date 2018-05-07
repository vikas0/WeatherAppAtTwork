package com.vikaspandey.weatherapp.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.vikaspandey.weatherapp.model.WeatherInfo
import org.json.JSONObject

class WeatherService constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: WeatherService? = null
        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: WeatherService(context)
                }
    }


    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    val GET_WEATHER = "http://api.openweathermap.org/data/2.5/weather?q="
    val OPEN_WEATHER_MAP_API_KEY = "72d965852dbf335ff7177ba68699d666";

    fun fetchWeather(cityName: String,apiListner:ApiListner)
    {
        val Url = GET_WEATHER+cityName+"&appid="+OPEN_WEATHER_MAP_API_KEY;
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,Url , null,
                Response.Listener<JSONObject> { response ->
//            val news = response
//                    .getJSONObject("query")
//                    .getJSONObject("results")
//                    .getJSONArray("item")
                apiListner.onSuccess( Gson().fromJson(response.toString(),WeatherInfo::class.java));

        },
                Response.ErrorListener {
                    apiListner.onError(it.message.toString());
                });

        addToRequestQueue(jsonObjectRequest);
//
//        VolleyService.requestQueue.add(request)
//        VolleyService.requestQueue.start()

    }




}

interface ApiListner {
    fun onSuccess(weatherInfo: WeatherInfo)
    fun onError(errorMsg:String)

}
