package com.styleru.vitaly.hseday2018.hse.tabs.organisations

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.styleru.vitaly.hseday2018.R

class OrganisationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val orgName:TextView = itemView.findViewById(R.id.organisation_item_name)
}