package com.styleru.vitaly.hseday2018.hse.tabs.organisations

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.dataclasses.Org
import com.styleru.vitaly.hseday2018.hse.AboutOrganisationActivity

class OrganisationsRecyclerViewAdapter(val orgList: List<Org>, val context: Context) : RecyclerView.Adapter<OrganisationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganisationsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.organisations_item, parent, false)
        return OrganisationsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orgList.size
    }

    override fun onBindViewHolder(holder: OrganisationsViewHolder, position: Int) {
        holder.orgName.text = orgList[position].organisation
        if (orgList[position].description != null)
            holder.itemView.setOnClickListener {
                val intent = Intent(context, AboutOrganisationActivity::class.java)
                intent.putExtra("name", orgList[position].organisation)
                intent.putExtra("description", orgList[position].description)
                intent.putExtra("link", orgList[position].contacts)
                intent.putExtra("image", orgList[position].image)
                context.startActivity(intent)
            }
    }
}