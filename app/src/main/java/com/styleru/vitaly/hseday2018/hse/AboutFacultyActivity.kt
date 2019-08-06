package com.styleru.vitaly.hseday2018.hse

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.styleru.vitaly.hseday2018.R

class AboutFacultyActivity : AppCompatActivity() {
    private lateinit var actionBarTitle: TextView
    private lateinit var actionBarBackButton: ImageButton
    private lateinit var title: TextView
    private lateinit var text: TextView
    private lateinit var image: ImageView
    private lateinit var link: TextView
    private lateinit var departments: TextView
    private lateinit var departmentsTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_faculty)

        createCustomStatusBar()
        createCustomActionBar()

        title = findViewById(R.id.faculty_name)
        text = findViewById(R.id.faculty_description)
        image = findViewById(R.id.faculty_image)
        link = findViewById(R.id.faculty_contacts)
        departments = findViewById(R.id.faculty_departments)
        departmentsTitle = findViewById(R.id.faculty_departments_title)

        getDataFromIntent()

    }

    private fun createCustomActionBar() {
        // Ставим кастомный action bar
        supportActionBar!!.elevation = 12f
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        actionBarTitle = supportActionBar!!.customView.findViewById(R.id.action_bar_text)
        actionBarBackButton = supportActionBar!!.customView.findViewById(R.id.action_bar_back)
        actionBarBackButton.setOnClickListener { finish() }
        actionBarTitle.text = this.resources.getString(R.string.faculty)
        actionBarBackButton.visibility = View.VISIBLE
    }

    private fun createCustomStatusBar() {
        // Меняем цвет action bar в зависимости от версии устройства
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !android.os.Build.MANUFACTURER.toLowerCase().equals("xiaomi"))
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            window.statusBarColor = this.resources.getColor(R.color.statusBarColor)
    }

    private fun getDataFromIntent() {
        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .into(image)

        title.text = intent.getStringExtra("name")
        text.text = intent.getStringExtra("description")

        if (intent.getStringExtra("departments").equals("")) {
            departments.visibility = View.GONE
            departmentsTitle.visibility = View.GONE
        }
        if (intent.getStringExtra("link").equals(""))
            link.visibility = View.GONE

        link.text = intent.getStringExtra("link")
        departments.text = intent.getStringExtra("departments")
    }
}
