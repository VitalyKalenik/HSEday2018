package com.styleru.vitaly.hseday2018.main

import android.os.Build
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.hse.HSEFragment
import com.styleru.vitaly.hseday2018.map.MapFragment
import com.styleru.vitaly.hseday2018.quest.QuestFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var actionBarTitle: TextView

    private val mapFragment = MapFragment()
    private val aboutHSEFragment = HSEFragment()
    private val questFragment = QuestFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                supportActionBar!!.elevation = 12f
                val transaction = supportFragmentManager.beginTransaction()
                transaction.hide(questFragment).hide(aboutHSEFragment).show(mapFragment).commit()
                actionBarTitle.text = this.resources.getString(R.string.title_map)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_quest -> {
                supportActionBar!!.elevation = 0f
                val transaction = supportFragmentManager.beginTransaction()
                transaction.hide(aboutHSEFragment).hide(mapFragment).show(questFragment).commit()
                actionBarTitle.text = this.resources.getString(R.string.title_quest)
                questFragment.refreshProgress()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                supportActionBar!!.elevation = 0f
                val transaction = supportFragmentManager.beginTransaction()
                transaction.hide(questFragment).hide(mapFragment).show(aboutHSEFragment).commit()
                actionBarTitle.text = this.resources.getString(R.string.title_info)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStatusBar()
        createCustomActionBar()
        addFragments()

        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_map
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
        if (mapFragment.ifPanelClosed())
            super.onBackPressed()
        mapFragment.closePanels()
    }

    fun addBadge() {
        // get badge container (parent)
        val bottomMenu = bottomNavigationView.getChildAt(0) as? BottomNavigationMenuView
        val v = bottomMenu?.getChildAt(1) as? BottomNavigationItemView

        // inflate badge from layout
        val badge = LayoutInflater.from(this)
                .inflate(R.layout.menu_item_red_badge, bottomMenu, false)

        // create badge layout parameter
        val badgeLayout: FrameLayout.LayoutParams = FrameLayout.LayoutParams(badge?.layoutParams).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = resources.getDimension(R.dimen.design_bottom_navigation_margin).toInt()

            // <dimen name="bagde_left_margin">8dp</dimen>
            leftMargin = resources.getDimension(R.dimen.bagde_left_margin).toInt()
        }

        // add view to bottom bar with layout parameter
        v?.addView(badge, badgeLayout)
    }

    private fun createCustomActionBar(){
        // Ставим кастомный action bar
        supportActionBar!!.apply {
            elevation = 12f
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)
            setCustomView(R.layout.custom_action_bar)
        }

        actionBarTitle = supportActionBar!!.customView.findViewById(R.id.action_bar_text)
        actionBarTitle.text = this.resources.getString(R.string.title_map)
    }

    private fun initStatusBar(){
        // Меняем цвет status bar в зависимости от версии устройства
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !android.os.Build.MANUFACTURER.toLowerCase().equals("xiaomi"))
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            window.statusBarColor = this.resources.getColor(R.color.statusBarColor)
    }

    private fun addFragments(){
        val fm = supportFragmentManager

        fm.beginTransaction().add(R.id.fragment_container, mapFragment, "1").commit()
        fm.beginTransaction().add(R.id.fragment_container, aboutHSEFragment, "2").hide(aboutHSEFragment).commit()
        fm.beginTransaction().add(R.id.fragment_container, questFragment, "3").hide(questFragment).commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt("selectedItem", bottomNavigationView.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavigationView.selectedItemId = savedInstanceState!!.getInt("selectedItem")
    }
}