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

class AboutOrganisationActivity : AppCompatActivity() {
    private lateinit var actionBarTitle: TextView
    private lateinit var actionBarBackButton: ImageButton
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var contacts: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_organisation)

        createCustomActionBar()
        createCustomStatusBar()

        name = findViewById(R.id.organisation_name)
        description = findViewById(R.id.organisation_description)
        contacts = findViewById(R.id.organisation_contacts)
        image = findViewById(R.id.organisation_image)

        loadDataFromIntent()

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
        actionBarTitle.text = this.resources.getString(R.string.organisation)
        actionBarBackButton.visibility = View.VISIBLE
    }

    private fun createCustomStatusBar() {
        // Меняем цвет action bar в зависимости от версии устройства
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !android.os.Build.MANUFACTURER.toLowerCase().equals("xiaomi"))
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            window.statusBarColor = this.resources.getColor(R.color.statusBarColor)
    }

    private fun loadDataFromIntent() {
        name.text = intent.getStringExtra("name")
        description.text = intent.getStringExtra("description")
        contacts.text = intent.getStringExtra("link")
        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .into(image)
    }
}
