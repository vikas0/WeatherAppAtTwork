package com.vikaspandey.weatherapp.database

import android.arch.persistence.room.*


@Dao
interface CityDao {
    @get:Query("SELECT * FROM city")
    val all: List<City>

//    @Query("SELECT * FROM city WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>


    @Query("SELECT * FROM city WHERE is_favorite == 1")
    fun loadAllFavorite(): List<City>


    @Insert
    fun insert(vararg city: City)

    @Delete
    fun delete(city: Array<out City>)
@Update
    fun update(city: City) {


    }
}
