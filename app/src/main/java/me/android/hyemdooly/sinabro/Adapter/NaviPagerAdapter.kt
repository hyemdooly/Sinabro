package me.android.hyemdooly.sinabro.Adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import me.android.hyemdooly.sinabro.Fragment.DictionaryFragment
import me.android.hyemdooly.sinabro.Fragment.FeedbackFragment
import me.android.hyemdooly.sinabro.Fragment.TranslationFragment

class NaviPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position){
            0-> {
                return TranslationFragment.newInstance()
            }
            1-> {
                return DictionaryFragment.newInstance()
            }
            2-> {
                return FeedbackFragment.newInstance()
            }
            else-> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

}