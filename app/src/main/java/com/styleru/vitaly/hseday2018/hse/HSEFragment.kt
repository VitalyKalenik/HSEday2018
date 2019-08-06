package com.styleru.vitaly.hseday2018.hse

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.view.ViewPager
import com.styleru.vitaly.hseday2018.CustomIndicator
import com.styleru.vitaly.hseday2018.hse.tabs.faculties.FacultiesFragment
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.hse.tabs.TabViewPagerAdapter
import com.styleru.vitaly.hseday2018.hse.tabs.aboutHSE.AboutHSEFragment
import com.styleru.vitaly.hseday2018.hse.tabs.organisations.OrganisationsFragment
import com.kekstudio.dachshundtablayout.DachshundTabLayout


class HSEFragment : Fragment() {
    private lateinit var tabLayout: DachshundTabLayout
    private lateinit var firstViewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hse, container, false)

        firstViewPager = view.findViewById(R.id.viewpager_content)

        tabLayout = view.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(firstViewPager)
        val customIndicator = CustomIndicator(tabLayout)
        tabLayout.animatedIndicator = customIndicator

        setupViewPager(firstViewPager)

        return view

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TabViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FacultiesFragment(), context!!.resources.getString(R.string.faculties))
        adapter.addFragment(OrganisationsFragment(), context!!.resources.getString(R.string.organisations))
        adapter.addFragment(AboutHSEFragment(), context!!.resources.getString(R.string.hse))
        viewPager.adapter = adapter
    }
}
