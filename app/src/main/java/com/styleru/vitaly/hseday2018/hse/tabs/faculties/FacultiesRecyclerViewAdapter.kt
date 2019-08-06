package com.styleru.vitaly.hseday2018.hse.tabs.faculties

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.dataclasses.Faculty
import com.styleru.vitaly.hseday2018.hse.AboutFacultyActivity

class FacultiesRecyclerViewAdapter(private val dataFaculties:ArrayList<Faculty>, val context:Context) : RecyclerView.Adapter<FacultiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultiesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.faculties_item, parent, false)
        val holder = FacultiesViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: FacultiesViewHolder, position: Int) {
        holder.title.text = dataFaculties[position].name

        holder.itemView.setOnClickListener { v ->
            val intent = Intent(context, AboutFacultyActivity::class.java)
            intent.putExtra("name", dataFaculties[position].name)
            intent.putExtra("description", dataFaculties[position].description)
            intent.putExtra("link", dataFaculties[position].link)
            intent.putExtra("departments", dataFaculties[position].departments)
            intent.putExtra("image", dataFaculties[position].imageUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataFaculties.size
    }
}