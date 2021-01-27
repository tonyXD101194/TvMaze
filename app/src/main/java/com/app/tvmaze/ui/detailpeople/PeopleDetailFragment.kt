package com.app.tvmaze.ui.detailpeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.tvmaze.R
import com.app.tvmaze.adapters.peopleshow.PeopleShowAdapter
import com.app.tvmaze.model.people.PeopleModel
import com.app.tvmaze.model.people.PeopleShowsModel
import com.app.tvmaze.ui.content.MainActivity
import com.app.tvmaze.utils.alert
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_people_detail.*

class PeopleDetailFragment: Fragment() {

    companion object {

        fun newInstance(peopleModel: PeopleModel): PeopleDetailFragment {

            val fragment = PeopleDetailFragment()

            fragment.people = peopleModel

            return fragment
        }
    }

    private lateinit var viewModel: PeopleDetailViewModel

    private lateinit var people: PeopleModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(PeopleDetailViewModel::class.java)

        (requireActivity() as MainActivity).supportActionBar?.title = people.person.name

        return inflater.inflate(R.layout.fragment_people_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeViews()
        this.initializeObservers()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.action_search)
        val menuItemFavorite = menu.findItem(R.id.action_favorite_list)
        val menuItemPeople = menu.findItem(R.id.action_people)

        menuItem.isVisible = false
        menuItemFavorite.isVisible = false
        menuItemPeople.isVisible = false
    }

    private fun initializeObservers() {

        this.viewModel.getPerson(
            id = people.person.id
        )

        this.viewModel.listShows.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentShowsTextViewError.visibility = View.GONE
                this.fragmentPeopleDetailRecyclerView.visibility = View.VISIBLE

                this.initializeAdapter(
                    list = it
                )
            } else {

                this.fragmentShowsTextViewError.visibility = View.VISIBLE
                this.fragmentPeopleDetailRecyclerView.visibility = View.GONE
            }
        })

        this.viewModel.message.observe(viewLifecycleOwner, Observer {

            requireActivity().alert(
                messageStringId = it,
                positiveStringId = R.string.accept_button
            )
        })
    }

    private fun initializeViews() {

        this.itemPeopleDetailTextViewName.text = people.person.name

        if (people.person.image != null) {

            Picasso.with(requireContext()).load(people.person.image!!.original)
                .into(this.fragmentPeopleDetailImageView)
        }
    }

    private fun initializeAdapter(list: List<PeopleShowsModel>) {

        this.fragmentPeopleDetailRecyclerView.adapter = PeopleShowAdapter(
            list = list
        )
    }
}