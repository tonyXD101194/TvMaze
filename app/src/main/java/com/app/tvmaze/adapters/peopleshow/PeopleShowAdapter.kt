package com.app.tvmaze.adapters.peopleshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.model.people.PeopleShowsModel

class PeopleShowAdapter(
    private val list: List<PeopleShowsModel>
): RecyclerView.Adapter<PeopleShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleShowViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people_detail, parent, false)

        return PeopleShowViewHolder(
            view = view
        )
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: PeopleShowViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }
}