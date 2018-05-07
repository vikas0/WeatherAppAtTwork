package com.vikaspandey.weatherapp.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.vikaspandey.weatherapp.R
import com.vikaspandey.weatherapp.R.id.city
import com.vikaspandey.weatherapp.R.id.progressBar
import com.vikaspandey.weatherapp.dashboard.LocationListActivity
//import com.vikaspandey.weatherapp.R.id.city
import com.vikaspandey.weatherapp.model.WeatherInfo
import com.vikaspandey.weatherapp.network.WeatherService
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() ,DetailView{
    override fun ShowError(errorMsg: String) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show();
    }

    private lateinit var presenter: DetailPresenter
    private val weatherService: WeatherService = WeatherService(this)
    override fun updateWeather(weather: WeatherInfo) {

        (findViewById(R.id.city) as TextView ).text = weather.name;
        (findViewById(R.id.temperature) as TextView ).text = (weather.main.temp.toString())

    }
    var cityName:String = ""

    override fun showLoader() {
        (findViewById(R.id.progressBar) as ProgressBar ).visibility = View.VISIBLE;
    }

    override fun hideLoader() {
        (findViewById(R.id.progressBar) as ProgressBar ).visibility = View.INVISIBLE

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = DetailPresenter(this, weatherService)
        cityName = intent.getStringExtra("Name")
        if(cityName==null || cityName.length == 0)
            cityName="Bangalore"
        presenter.fetchWeather(cityName)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.dashboard -> {

                val intent = Intent(this, LocationListActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
