package com.app.tvmaze.adapters.shows

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.show.ShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_show.view.*

class ShowViewHolder(
    val view: View,
private val callback: ClickInterface): RecyclerView.ViewHolder(view) {

    fun bind(model: ShowModel) {

        view.itemShowTextViewNameShow.text = model.name

        Picasso.with(view.context).load(model.image.medium)
            .into(view.itemShowImageViewShow)

        view.setOnClickListener {

            callback.onClickItem(
                model = model
            )
        }
    }
}