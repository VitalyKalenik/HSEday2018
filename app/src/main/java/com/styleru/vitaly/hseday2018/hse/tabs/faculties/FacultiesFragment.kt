package com.styleru.vitaly.hseday2018.hse.tabs.faculties

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.dataclasses.Faculty
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class FacultiesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FacultiesRecyclerViewAdapter
    private lateinit var dataFaculties: ArrayList<Faculty>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_faculties, container, false)
        recyclerView = view.findViewById(R.id.faculties_rv)
        recyclerView.layoutManager = LinearLayoutManager(context)
        dataFaculties = ArrayList()

        getDataFromJson()

        adapter = FacultiesRecyclerViewAdapter(dataFaculties, context!!)
        recyclerView.adapter = adapter

        return view
    }

    private fun getDataFromJson(){
        val text = resources.openRawResource(R.raw.faculties)
                .bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Faculty::class.java)
        val jsonAdapter = moshi.adapter<ArrayList<Faculty>>(type)
        dataFaculties = jsonAdapter.fromJson(text)!!
    }
}
