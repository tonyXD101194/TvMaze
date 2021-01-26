package com.app.tvmaze.adapters.seasons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.season.SeasonListModel

class SeasonAdapter(
    private val list: List<SeasonListModel>,
    private val callback: ClickInterface
): RecyclerView.Adapter<SeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seasons, parent, false)

        return SeasonViewHolder(
            view = view,
            callback = callback
        )
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.bind(
            listModel = list[position]
        )
    }
}