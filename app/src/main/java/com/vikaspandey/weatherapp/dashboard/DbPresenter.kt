package com.vikaspandey.weatherapp.dashboard

import android.arch.persistence.room.Room
import android.content.Context
import com.vikaspandey.weatherapp.database.AppDatabase
import com.vikaspandey.weatherapp.database.City

class DbPresenter
(val context: Context) {
    val db: AppDatabase? = Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, "database-name").build()
    public fun insertCity(city: City)
    {
        db?.cityDao()?.insert(city)
    }
    fun loadAllCity():List<City>?
    {
       return db?.cityDao()?.all;
    }

    fun loadFabs():List<City>?
    {
        return db?.cityDao()?.loadAllFavorite();
    }

    fun upateCity(city: City) {
        db?.cityDao()?.update(city)

    }
}