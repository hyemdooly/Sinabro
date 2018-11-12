package me.android.hyemdooly.sinabro.Activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import me.android.hyemdooly.sinabro.Adapter.NaviPagerAdapter
import me.android.hyemdooly.sinabro.Fragment.DictionaryFragment
import me.android.hyemdooly.sinabro.Fragment.FeedbackFragment
import me.android.hyemdooly.sinabro.Fragment.TranslationFragment
import me.android.hyemdooly.sinabro.R
import android.widget.Toast
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity(), TranslationFragment.OnFragmentInteractionListener, DictionaryFragment.OnFragmentInteractionListener, FeedbackFragment.OnFragmentInteractionListener {

    private var translationState = true // nam -> book : true, book -> nam : false
    private var backKeyPressedTime: Long = 0
    private var prevMenuItem :MenuItem ?= null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_translation -> {
                view_pager_navi.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dictionary -> {
                view_pager_navi.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feedback -> {
                view_pager_navi.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pagerAdapter = NaviPagerAdapter(supportFragmentManager)
        view_pager_navi.adapter = pagerAdapter

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        view_pager_navi.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null)
                    prevMenuItem!!.isChecked = false
                else
                    navigation.menu.getItem(0).isChecked = false

                navigation.menu.getItem(position).isChecked = true
                prevMenuItem = navigation.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })


    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
        super.onBackPressed()
    }
}
