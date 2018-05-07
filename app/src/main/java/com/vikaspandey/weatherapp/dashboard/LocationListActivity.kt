package com.vikaspandey.weatherapp.dashboard

import android.app.Activity
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle

import com.vikaspandey.weatherapp.R
import kotlinx.android.synthetic.main.activity_location_list.*
import android.content.Intent
import android.media.audiofx.BassBoost
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.vikaspandey.weatherapp.R.id.fab
import com.vikaspandey.weatherapp.database.City


class LocationListActivity : AppCompatActivity() {
    val NEW_CITY_ACTIVITY_REQUEST_CODE = 1

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    val dbPresenter = DbPresenter(this);
    var fab : FloatingActionButton? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_list)

        setSupportActionBar(findViewById(R.id.toolbar))
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
                fab= findViewById(R.id.fab) as FloatingActionButton;
       fab!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NewCityActivity::class.java)
            startActivityForResult(intent, NEW_CITY_ACTIVITY_REQUEST_CODE)
        })
        // Set up the ViewPager with the sections adapter.
       var viewpager = findViewById(R.id.container) as ViewPager;
        viewpager.adapter= mSectionsPagerAdapter;
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(position: Int) {

                if(position == 0)
                    fab!!.visibility = View.VISIBLE;
                else
                    fab!!.visibility = View.INVISIBLE;


            }

        })

//        dummySetUp();


    }

    private fun dummySetUp() {
        val size  = dbPresenter.loadAllCity()?.size ;
    if(( size == 3)==false)
        fillDb();
    }

    private fun fillDb() {

       dbPresenter.insertCity(City().apply { setName("Bangalore")  }.apply { setFav(true) })
        dbPresenter.insertCity(City().apply { setName("Mumbai") })
        dbPresenter.insertCity(City().apply { setName("Kolkata") })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_CITY_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val city = City().apply { this.setName(data.getStringExtra(NewCityActivity.EXTRA_REPLY)) }
            dbPresenter.insertCity(city)
        } else {
            Toast.makeText(
                    applicationContext,
                   "Not Saved!",
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_location_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 1)
                return AllCityFragment.newInstance(1);
          else
                return FavoriteCitiesFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            var rootView = inflater.inflate(R.layout.fragment_location_list, container, false)
           if(arguments?.getInt(ARG_SECTION_NUMBER) == 1)
               (rootView.findViewById(R.id.section_label) as TextView).text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
