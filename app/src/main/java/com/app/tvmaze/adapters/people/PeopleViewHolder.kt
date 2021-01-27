package com.app.tvmaze.adapters.people

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.people.PeopleModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_people.view.*

class PeopleViewHolder(
    private val view: View,
    private val callback: ClickInterface
): RecyclerView.ViewHolder(view) {

    fun bind(people: PeopleModel) {

        if (people.person.image != null) {

            Picasso.with(view.context).load(people.person.image!!.original)
                .into(view.itemPeopleImageView)
        }

        view.itemPeopleTextViewName.text = people.person.name

        view.setOnClickListener {

            callback.onClickItem(
                model = people
            )
        }
    }
}