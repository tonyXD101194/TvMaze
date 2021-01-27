package com.app.tvmaze.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.tvmaze.R
import com.app.tvmaze.adapters.shows.ShowsAdapter
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.interfaces.FollowInterface
import com.app.tvmaze.interfaces.NavigationInterface
import com.app.tvmaze.model.show.ShowModel
import com.app.tvmaze.ui.content.MainActivity
import com.app.tvmaze.ui.detail.DetailShowFragment
import kotlinx.android.synthetic.main.fragment_favorite_show.*

class FavoriteShowFragment: Fragment(), ClickInterface, FollowInterface {

    companion object {

        fun newInstance(list: List<ShowModel>, navigationInterface: NavigationInterface): FavoriteShowFragment {

            val fragment = FavoriteShowFragment()

            fragment.list = list
            fragment.navigationInterface = navigationInterface

            return fragment
        }
    }

    private lateinit var list: List<ShowModel>

    private lateinit var navigationInterface: NavigationInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.fragment_favorite_title)

        return inflater.inflate(R.layout.fragment_favorite_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeAdapter()
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

    private fun initializeAdapter() {

        if (list.isEmpty()) {

            this.fragmentFavoriteShowTextViewShows.visibility = View.VISIBLE
            this.fragmentFavoriteShowRecyclerViewShows.visibility = View.GONE
        } else {

            this.fragmentFavoriteShowTextViewShows.visibility = View.GONE
            this.fragmentFavoriteShowRecyclerViewShows.visibility = View.VISIBLE

            this.fragmentFavoriteShowRecyclerViewShows.adapter = ShowsAdapter(
                list = list,
                callback = this,
                followInterface = this,
                fromFavorite = true
            )
        }
    }

    override fun onClickItem(model: Any) {

        navigationInterface.pushFragment(
            fragment = DetailShowFragment.newInstance(
                model = model as ShowModel,
                navigationInterface = navigationInterface)
        )
    }

    override fun onClickFavoriteShow(index: Int, id: Int, isFavorite: Boolean) {
        // Nothing to do...
    }
}