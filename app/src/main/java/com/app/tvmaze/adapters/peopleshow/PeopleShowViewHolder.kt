package com.app.tvmaze.adapters.peopleshow

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.model.people.PeopleShowsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_people_detail.view.*

class PeopleShowViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {

    fun bind(show: PeopleShowsModel) {

        if (show.show.show.image != null) {

            Picasso.with(view.context).load(show.show.show.image!!.original)
                .into(view.itemPeopleDetailImageView)
        }

        view.itemPeopleDetailTextViewName.text = show.show.show.name
    }
}