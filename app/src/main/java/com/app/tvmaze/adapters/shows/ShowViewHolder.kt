package com.app.tvmaze.adapters.shows

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.interfaces.room.FollowInterface
import com.app.tvmaze.model.show.ShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_show.view.*

class ShowViewHolder(
    val view: View,
    private val callback: ClickInterface,
    private val followInterface: FollowInterface): RecyclerView.ViewHolder(view) {

    fun bind(model: ShowModel) {

        if (model.isFavorite) {

            view.itemShowImageViewFavorite.setImageResource(R.drawable.ic_favorite_white_24dp)
        } else {

            view.itemShowImageViewFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp)
        }

        view.itemShowTextViewNameShow.text = model.name

        Picasso.with(view.context).load(model.image.medium)
            .into(view.itemShowImageViewShow)

        view.setOnClickListener {

            callback.onClickItem(
                model = model
            )
        }

        view.itemShowImageViewFavorite.setOnClickListener {

            followInterface.onClickFavoriteShow(
                index = adapterPosition,
                id = model.id,
                isFavorite = !model.isFavorite
            )
        }
    }
}