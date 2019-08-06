package com.styleru.vitaly.hseday2018.hse.tabs.faculties

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.styleru.vitaly.hseday2018.R

class FacultiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var title:TextView = itemView.findViewById(R.id.faculty_item_name)
}