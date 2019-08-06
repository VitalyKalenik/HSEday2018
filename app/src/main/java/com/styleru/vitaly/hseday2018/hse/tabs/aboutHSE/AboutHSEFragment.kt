package com.styleru.vitaly.hseday2018.hse.tabs.aboutHSE

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.styleru.vitaly.hseday2018.R


class AboutHSEFragment : Fragment() {
    private lateinit var image:ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_hse, container, false)
        image = view.findViewById(R.id.about_hse_image)
        Glide.with(context!!)
                .load("https://i.imgur.com/l8xOuCB.jpg")
                .into(image)
        return view
    }
}
