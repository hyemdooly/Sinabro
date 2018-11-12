package me.android.hyemdooly.sinabro.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.android.hyemdooly.sinabro.R

class MainActivity : AppCompatActivity() {

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.translation -> {
//                message.setText(R.string.title_translation)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
