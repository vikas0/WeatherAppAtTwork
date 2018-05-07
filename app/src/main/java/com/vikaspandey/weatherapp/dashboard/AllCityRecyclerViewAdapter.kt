package com.vikaspandey.weatherapp.dashboard

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vikaspandey.weatherapp.R


import com.vikaspandey.weatherapp.dashboard.AllCityFragment.OnListFragmentInteractionListener
import com.vikaspandey.weatherapp.dashboard.dummy.DummyContent.DummyItem
import com.vikaspandey.weatherapp.database.City
import com.vikaspandey.weatherapp.detail.DetailActivity

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class AllCityRecyclerViewAdapter(
val activity:Activity)
    : RecyclerView.Adapter<AllCityRecyclerViewAdapter.ViewHolder>() {


    private var mValues: List<City>? = null
init {
    mValues = (activity as LocationListActivity).dbPresenter.loadAllCity();
}
fun refresh()
{
    mValues = (activity as LocationListActivity).dbPresenter.loadAllCity();
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_all_city_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues!![position]
//        holder.mIdView.text = item.id
        holder.mContentView.text = item.getName()
        var color =android.R.color.holo_red_light
        if(item.isFav()==false)
            color =android.R.color.white
        holder.favUnfav.setColorFilter(ContextCompat.getColor(activity,color ), android.graphics.PorterDuff.Mode.MULTIPLY)

        with(holder.mView) {

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Name", item.getName())
            activity.startActivity(intent)
        }
        with(holder.favUnfav) {
if(item.isFav()==false)
{
    var city = item;
    city.setFav(true);
    (activity as LocationListActivity ).dbPresenter.upateCity(city)
   refresh()
}
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Name", item.getName())
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int
    {
        if(mValues== null)
            return 0;
        else
            return mValues!!.size;
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById(R.id.item_number);
        val mContentView: TextView = mView.findViewById(R.id.content);
        val favUnfav:ImageView = mView.findViewById(R.id.fav_unfav);

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
