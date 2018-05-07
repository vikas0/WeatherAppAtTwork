package com.vikaspandey.weatherapp.database
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo

@Entity

  class City
{
    @PrimaryKey
    private var name: String = ""
    @ColumnInfo(name = "is_favorite")
    private var isFavorite:Boolean = false

  public fun getName():String {
      return name;

  }
    public fun setName(name:String){
      this.name = name;

    }

    public fun setFav(isFav:Boolean){
        this.isFavorite = isFav;

    }

    public fun isFav():Boolean
    {
        return isFavorite;

    }

}