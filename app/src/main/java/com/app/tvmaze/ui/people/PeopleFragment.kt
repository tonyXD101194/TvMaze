package com.app.tvmaze.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.tvmaze.R
import com.app.tvmaze.adapters.people.PeopleAdapter
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.people.PeopleModel
import com.app.tvmaze.ui.content.MainActivity
import com.app.tvmaze.utils.alert
import kotlinx.android.synthetic.main.fragment_people.*

class PeopleFragment: Fragment(), ClickInterface {

    companion object {

        private const val ROWS_CATEGORIES = 3

        fun newInstance(): PeopleFragment = PeopleFragment()
    }

    private lateinit var viewModel: PeopleViewModel

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
            ViewModelProvider(this).get(PeopleViewModel::class.java)

        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.fragment_people_title)

        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeObservers()
        this.initializeListeners()
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

        this.viewModel.list.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentPeopleTextViewError.visibility = View.GONE
                this.fragmentPeopleRecyclerViewPeople.visibility = View.VISIBLE

                this.initializeAdapter(
                    list = it
                )
            } else {

                this.fragmentPeopleTextViewError.visibility = View.VISIBLE
                this.fragmentPeopleRecyclerViewPeople.visibility = View.GONE
            }
        })

        this.viewModel.message.observe(viewLifecycleOwner, Observer {

            requireActivity().alert(
                messageStringId = it,
                positiveStringId = R.string.accept_button
            )
        })
    }

    private fun initializeListeners() {

        this.fragmentPeopleSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                viewModel.getPeople(
                    query = query!!
                )

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
    }

    private fun initializeAdapter(list: List<PeopleModel>) {

        this.fragmentPeopleRecyclerViewPeople.layoutManager =
            GridLayoutManager(activity, ROWS_CATEGORIES)

        this.fragmentPeopleRecyclerViewPeople.adapter = PeopleAdapter(
            list = list,
            callback = this
        )
    }

    override fun onClickItem(model: Any) {
        // TODO
    }
}