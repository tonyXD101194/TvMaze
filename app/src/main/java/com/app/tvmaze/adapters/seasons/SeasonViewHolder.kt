package com.app.tvmaze.adapters.seasons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.season.SeasonListModel
import kotlinx.android.synthetic.main.item_episode.view.*
import kotlinx.android.synthetic.main.item_seasons.view.*

class SeasonViewHolder(
    private val view: View,
    private val callback: ClickInterface): RecyclerView.ViewHolder(view) {

    fun bind(listModel: SeasonListModel) {

        view.itemSeasonsTextViewNumber.text = String.format(
            view.context.resources.getString(R.string.fragment_detail_show_number_season, listModel.numberSeason.toString())
        )

        if (listModel.expandable) {

            view.itemSeasonsLinearLayoutEpisodes.visibility = View.VISIBLE
            view.itemSeasonsTextViewNumber
                .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0)
        }

        view.itemSeasonsTextViewNumber.setOnClickListener {

            if (view.itemSeasonsLinearLayoutEpisodes.visibility == View.GONE) {

                listModel.expandable = true

                view.itemSeasonsLinearLayoutEpisodes.visibility = View.VISIBLE
                view.itemSeasonsTextViewNumber
                    .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0)
            } else {

                listModel.expandable = false

                view.itemSeasonsLinearLayoutEpisodes.visibility = View.GONE
                view.itemSeasonsTextViewNumber
                    .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_play_arrow_black_24dp, 0)
            }
        }

        listModel.listEpisodes.forEach { item ->

            val inflater = view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val textViewEpisode: View = inflater.inflate(R.layout.item_episode, null)

            (textViewEpisode.itemEpisodeTextViewName as TextView).text = item.name

            textViewEpisode.setOnClickListener {

                callback.onClickItem(item)
            }

            view.itemSeasonsLinearLayoutEpisodes.addView(textViewEpisode)
        }
    }
}