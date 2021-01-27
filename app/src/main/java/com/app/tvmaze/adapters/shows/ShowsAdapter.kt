package com.app.tvmaze.adapters.shows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.interfaces.FollowInterface
import com.app.tvmaze.model.show.ShowModel

class ShowsAdapter(
    private val list: List<ShowModel>,
    private val callback: ClickInterface,
    private val followInterface: FollowInterface,
    private val fromFavorite: Boolean
): RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_show, parent, false)

        return ShowViewHolder(
            view = view,
            callback = callback,
            followInterface = followInterface,
            fromFavorite = fromFavorite
        )
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }

    fun setItemChange(index: Int, isFavorite: Boolean) {

        if (index < list.size) {

            list[index].isFavorite = isFavorite

            notifyDataSetChanged()
        }
    }
}