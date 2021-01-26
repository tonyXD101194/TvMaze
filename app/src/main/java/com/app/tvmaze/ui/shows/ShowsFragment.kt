package com.app.tvmaze.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.tvmaze.R
import com.app.tvmaze.adapters.shows.ShowsAdapter
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.interfaces.NavigationInterface
import com.app.tvmaze.model.show.ShowModel
import com.app.tvmaze.ui.content.MainActivity
import com.app.tvmaze.ui.detail.DetailShowFragment
import com.app.tvmaze.utils.alert
import kotlinx.android.synthetic.main.fragment_shows.*

class ShowsFragment: Fragment(), ClickInterface {

    companion object {

        fun newInstance(callback: NavigationInterface): ShowsFragment {

            val fragment = ShowsFragment()

            fragment.callback = callback

            return fragment
        }
    }

    private lateinit var viewModel: ShowsViewModel

    private lateinit var callback: NavigationInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(ShowsViewModel::class.java)

        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        return inflater.inflate(R.layout.fragment_shows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeListeners()
        this.initializeObservers()
    }

    private fun initializeListeners() {


    }

    private fun initializeObservers() {

        this.viewModel.getShows()

        this.viewModel.list.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentShowsTextViewError.visibility = View.GONE
                this.fragmentShowsRecyclerViewShows.visibility = View.VISIBLE

                this.initializeAdapters(
                    list = it
                )
            }
        })

        this.viewModel.listSearch.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentShowsTextViewEmpty.visibility = View.GONE
                this.fragmentShowsRecyclerViewShows.visibility = View.VISIBLE

                this.initializeAdapters(
                    list = it
                )
            } else {

                this.fragmentShowsTextViewEmpty.visibility = View.VISIBLE
                this.fragmentShowsRecyclerViewShows.visibility = View.GONE
            }
        })

        this.viewModel.message.observe(viewLifecycleOwner, Observer {

            requireActivity().alert(
                messageStringId = it,
                positiveStringId = R.string.accept_button
            )
        })
    }

    private fun initializeAdapters(list: List<ShowModel>) {

        this.fragmentShowsRecyclerViewShows.adapter = ShowsAdapter(
            list = list,
            callback = this
        )
    }

    fun setShowNyName(name: String) {

        this.viewModel.getShowsByName(
            name = name
        )
    }

    fun resetList() {

        this.viewModel.resetListCreated()
    }

    override fun onClickItem(model: Any) {

        callback.pushFragment(
            fragment = DetailShowFragment.newInstance(
                model = model as ShowModel,
                navigationInterface = callback)
        )
    }
}