package com.app.tvmaze.adapters.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.people.PeopleModel

class PeopleAdapter(
    private val list: List<PeopleModel>,
    private val callback: ClickInterface
): RecyclerView.Adapter<PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)

        return PeopleViewHolder(
            view = view,
            callback = callback
        )
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }
}