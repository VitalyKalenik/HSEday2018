package com.styleru.vitaly.hseday2018.hse.tabs.organisations

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.dataclasses.Organisation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import net.cachapa.expandablelayout.ExpandableLayout


class OrganisationsFragment : Fragment() {
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView
    private lateinit var recyclerView5: RecyclerView
    private lateinit var recyclerView6: RecyclerView
    private lateinit var recyclerView7: RecyclerView
    private lateinit var recyclerView8: RecyclerView
    private lateinit var recyclerView9: RecyclerView

    private lateinit var adapter1: OrganisationsRecyclerViewAdapter
    private lateinit var adapter2: OrganisationsRecyclerViewAdapter
    private lateinit var adapter3: OrganisationsRecyclerViewAdapter
    private lateinit var adapter4: OrganisationsRecyclerViewAdapter
    private lateinit var adapter5: OrganisationsRecyclerViewAdapter
    private lateinit var adapter6: OrganisationsRecyclerViewAdapter
    private lateinit var adapter7: OrganisationsRecyclerViewAdapter
    private lateinit var adapter8: OrganisationsRecyclerViewAdapter
    private lateinit var adapter9: OrganisationsRecyclerViewAdapter

    private lateinit var constraintLayout1: ConstraintLayout
    private lateinit var constraintLayout2: ConstraintLayout
    private lateinit var constraintLayout3: ConstraintLayout
    private lateinit var constraintLayout4: ConstraintLayout
    private lateinit var constraintLayout5: ConstraintLayout
    private lateinit var constraintLayout6: ConstraintLayout
    private lateinit var constraintLayout7: ConstraintLayout
    private lateinit var constraintLayout8: ConstraintLayout
    private lateinit var constraintLayout9: ConstraintLayout

    private lateinit var expandableLayout1: ExpandableLayout
    private lateinit var expandableLayout2: ExpandableLayout
    private lateinit var expandableLayout3: ExpandableLayout
    private lateinit var expandableLayout4: ExpandableLayout
    private lateinit var expandableLayout5: ExpandableLayout
    private lateinit var expandableLayout6: ExpandableLayout
    private lateinit var expandableLayout7: ExpandableLayout
    private lateinit var expandableLayout8: ExpandableLayout
    private lateinit var expandableLayout9: ExpandableLayout

    private lateinit var icon1: ImageView
    private lateinit var icon2: ImageView
    private lateinit var icon3: ImageView
    private lateinit var icon4: ImageView
    private lateinit var icon5: ImageView
    private lateinit var icon6: ImageView
    private lateinit var icon7: ImageView
    private lateinit var icon8: ImageView
    private lateinit var icon9: ImageView

    private lateinit var dataOrganisations: ArrayList<Organisation>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_organisations, container, false)
        bindViews(view)
        return view
    }

    private fun bindViews(view: View) {
        val text = resources.openRawResource(R.raw.organisations)
                .bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder()
                .build()
        val type = Types.newParameterizedType(List::class.java, Organisation::class.java)
        val jsonAdapter: JsonAdapter<ArrayList<Organisation>> = moshi.adapter(type)

        dataOrganisations = jsonAdapter.fromJson(text)!!

        expandableLayout1 = view.findViewById(R.id.organisation_item_expandable_layout_1)
        expandableLayout2 = view.findViewById(R.id.organisation_item_expandable_layout_2)
        expandableLayout3 = view.findViewById(R.id.organisation_item_expandable_layout_3)
        expandableLayout4 = view.findViewById(R.id.organisation_item_expandable_layout_4)
        expandableLayout5 = view.findViewById(R.id.organisation_item_expandable_layout_5)
        expandableLayout6 = view.findViewById(R.id.organisation_item_expandable_layout_6)
        expandableLayout7 = view.findViewById(R.id.organisation_item_expandable_layout_7)
        expandableLayout8 = view.findViewById(R.id.organisation_item_expandable_layout_8)
        expandableLayout9 = view.findViewById(R.id.organisation_item_expandable_layout_9)

        constraintLayout1 = view.findViewById(R.id.org_layout_1)
        constraintLayout2 = view.findViewById(R.id.org_layout_2)
        constraintLayout3 = view.findViewById(R.id.org_layout_3)
        constraintLayout4 = view.findViewById(R.id.org_layout_4)
        constraintLayout5 = view.findViewById(R.id.org_layout_5)
        constraintLayout6 = view.findViewById(R.id.org_layout_6)
        constraintLayout7 = view.findViewById(R.id.org_layout_7)
        constraintLayout8 = view.findViewById(R.id.org_layout_8)
        constraintLayout9 = view.findViewById(R.id.org_layout_9)

        icon1 = view.findViewById(R.id.organisation_icon_1)
        icon2 = view.findViewById(R.id.organisation_icon_2)
        icon3 = view.findViewById(R.id.organisation_icon_3)
        icon4 = view.findViewById(R.id.organisation_icon_4)
        icon5 = view.findViewById(R.id.organisation_icon_5)
        icon6 = view.findViewById(R.id.organisation_icon_6)
        icon7 = view.findViewById(R.id.organisation_icon_7)
        icon8 = view.findViewById(R.id.organisation_icon_8)
        icon9 = view.findViewById(R.id.organisation_icon_9)

        recyclerView1 = view.findViewById(R.id.organisation_item_rv_1)
        recyclerView2 = view.findViewById(R.id.organisation_item_rv_2)
        recyclerView3 = view.findViewById(R.id.organisation_item_rv_3)
        recyclerView4 = view.findViewById(R.id.organisation_item_rv_4)
        recyclerView5 = view.findViewById(R.id.organisation_item_rv_5)
        recyclerView6 = view.findViewById(R.id.organisation_item_rv_6)
        recyclerView7 = view.findViewById(R.id.organisation_item_rv_7)
        recyclerView8 = view.findViewById(R.id.organisation_item_rv_8)
        recyclerView9 = view.findViewById(R.id.organisation_item_rv_9)

        recyclerView1.layoutManager = LinearLayoutManager(context)
        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView3.layoutManager = LinearLayoutManager(context)
        recyclerView4.layoutManager = LinearLayoutManager(context)
        recyclerView5.layoutManager = LinearLayoutManager(context)
        recyclerView6.layoutManager = LinearLayoutManager(context)
        recyclerView7.layoutManager = LinearLayoutManager(context)
        recyclerView8.layoutManager = LinearLayoutManager(context)
        recyclerView9.layoutManager = LinearLayoutManager(context)

        adapter1 = OrganisationsRecyclerViewAdapter(dataOrganisations[0].organisations, context!!)
        adapter2 = OrganisationsRecyclerViewAdapter(dataOrganisations[1].organisations, context!!)
        adapter3 = OrganisationsRecyclerViewAdapter(dataOrganisations[2].organisations, context!!)
        adapter4 = OrganisationsRecyclerViewAdapter(dataOrganisations[3].organisations, context!!)
        adapter5 = OrganisationsRecyclerViewAdapter(dataOrganisations[4].organisations, context!!)
        adapter6 = OrganisationsRecyclerViewAdapter(dataOrganisations[5].organisations, context!!)
        adapter7 = OrganisationsRecyclerViewAdapter(dataOrganisations[6].organisations, context!!)
        adapter8 = OrganisationsRecyclerViewAdapter(dataOrganisations[7].organisations, context!!)
        adapter9 = OrganisationsRecyclerViewAdapter(dataOrganisations[8].organisations, context!!)


        recyclerView1.adapter = adapter1
        recyclerView2.adapter = adapter2
        recyclerView3.adapter = adapter3
        recyclerView4.adapter = adapter4
        recyclerView5.adapter = adapter5
        recyclerView6.adapter = adapter6
        recyclerView7.adapter = adapter7
        recyclerView8.adapter = adapter8
        recyclerView9.adapter = adapter9

        constraintLayout1.setOnClickListener {
            if (!expandableLayout1.isExpanded)
                closeExpandableLayouts()
            expandableLayout1.toggle()
            if (expandableLayout1.isExpanded) {
                icon1.animate().rotation(180.0f).start()
            } else {
                icon1.animate().rotation(0.0f).start()
            }
        }
        constraintLayout2.setOnClickListener {
            if (!expandableLayout2.isExpanded)
                closeExpandableLayouts()
            expandableLayout2.toggle()
            if (expandableLayout2.isExpanded) {
                icon2.animate().rotation(180.0f).start()
            } else {
                icon2.animate().rotation(0.0f).start()
            }
        }
        constraintLayout3.setOnClickListener {
            if (!expandableLayout3.isExpanded)
                closeExpandableLayouts()
            expandableLayout3.toggle()
            if (expandableLayout3.isExpanded) {
                icon3.animate().rotation(180.0f).start()
            } else {
                icon3.animate().rotation(0.0f).start()
            }
        }
        constraintLayout4.setOnClickListener {
            if (!expandableLayout4.isExpanded)
                closeExpandableLayouts()
            expandableLayout4.toggle()
            if (expandableLayout4.isExpanded) {
                icon4.animate().rotation(180.0f).start()
            } else {
                icon4.animate().rotation(0.0f).start()
            }

        }
        constraintLayout5.setOnClickListener {
            if (!expandableLayout5.isExpanded)
                closeExpandableLayouts()
            expandableLayout5.toggle()
            if (expandableLayout5.isExpanded) {
                icon5.animate().rotation(180.0f).start()
            } else {
                icon5.animate().rotation(0.0f).start()
            }
        }
        constraintLayout6.setOnClickListener {
            if (!expandableLayout6.isExpanded)
                closeExpandableLayouts()
            expandableLayout6.toggle()
            if (expandableLayout6.isExpanded) {
                icon6.animate().rotation(180.0f).start()
            } else {
                icon6.animate().rotation(0.0f).start()
            }
        }
        constraintLayout7.setOnClickListener {
            if (!expandableLayout7.isExpanded)
                closeExpandableLayouts()
            expandableLayout7.toggle()
            if (expandableLayout7.isExpanded) {
                icon7.animate().rotation(180.0f).start()
            } else {
                icon7.animate().rotation(0.0f).start()
            }
        }
        constraintLayout8.setOnClickListener {
            if (!expandableLayout8.isExpanded)
                closeExpandableLayouts()
            expandableLayout8.toggle()
            if (expandableLayout8.isExpanded) {
                icon8.animate().rotation(180.0f).start()
            } else {
                icon8.animate().rotation(0.0f).start()
            }
        }
        constraintLayout9.setOnClickListener {
            if (!expandableLayout9.isExpanded)
                closeExpandableLayouts()
            expandableLayout9.toggle()
            if (expandableLayout9.isExpanded) {
                icon9.animate().rotation(180.0f).start()
            } else {
                icon9.animate().rotation(0.0f).start()
            }
        }
    }

    private fun closeExpandableLayouts() {
        if (expandableLayout1.isExpanded) {
            expandableLayout1.collapse()
            icon1.animate().rotation(0.0f).start()
        }
        if (expandableLayout2.isExpanded) {
            expandableLayout2.collapse()
            icon2.animate().rotation(0.0f).start()
        }
        if (expandableLayout3.isExpanded) {
            expandableLayout3.collapse()
            icon3.animate().rotation(0.0f).start()
        }
        if (expandableLayout4.isExpanded) {
            expandableLayout4.collapse()
            icon4.animate().rotation(0.0f).start()
        }
        if (expandableLayout5.isExpanded) {
            expandableLayout5.collapse()
            icon5.animate().rotation(0.0f).start()
        }
        if (expandableLayout6.isExpanded) {
            expandableLayout6.collapse()
            icon6.animate().rotation(0.0f).start()
        }
        if (expandableLayout7.isExpanded) {
            expandableLayout7.collapse()
            icon7.animate().rotation(0.0f).start()
        }
        if (expandableLayout8.isExpanded) {
            expandableLayout8.collapse()
            icon8.animate().rotation(0.0f).start()
        }
        if (expandableLayout9.isExpanded) {
            expandableLayout9.collapse()
            icon9.animate().rotation(0.0f).start()
        }

        recyclerView1.isNestedScrollingEnabled = false
        recyclerView2.isNestedScrollingEnabled = false
        recyclerView3.isNestedScrollingEnabled = false
        recyclerView4.isNestedScrollingEnabled = false
        recyclerView5.isNestedScrollingEnabled = false
        recyclerView6.isNestedScrollingEnabled = false
        recyclerView7.isNestedScrollingEnabled = false
        recyclerView8.isNestedScrollingEnabled = false
        recyclerView9.isNestedScrollingEnabled = false
    }
}
