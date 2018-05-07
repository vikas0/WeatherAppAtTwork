package com.vikaspandey.weatherapp.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vikaspandey.weatherapp.R


import com.vikaspandey.weatherapp.dashboard.FavoriteCitiesFragment.OnListFragmentInteractionListener
import com.vikaspandey.weatherapp.dashboard.dummy.DummyContent.DummyItem
import com.vikaspandey.weatherapp.database.City

import kotlinx.android.synthetic.main.fragment_city.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyfavoriteRecyclerViewAdapter(
        private val mValues: List<City>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyfavoriteRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as City
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position] as City
//        holder.mIdView.text = position+1+"";
        holder.mContentView.text = item.getName()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById(R.id.item_number);
        val mContentView: TextView = mView.findViewById(R.id.content);

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
