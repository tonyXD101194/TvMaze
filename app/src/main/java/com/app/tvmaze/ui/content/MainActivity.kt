package com.app.tvmaze.ui.content

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.NavigationInterface
import com.app.tvmaze.ui.people.PeopleFragment
import com.app.tvmaze.ui.shows.ShowsFragment


class MainActivity : AppCompatActivity(), NavigationInterface {

    private val showsFragment: ShowsFragment by lazy {

        ShowsFragment.newInstance(
            callback = this
        )
    }

    private val peopleFragment: PeopleFragment by lazy {

        PeopleFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        this.initializeFragments()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.action_favorite_list -> {

                val fragment = supportFragmentManager.findFragmentById(R.id.activityMainFragmentContent)

                if (fragment is ShowsFragment) {

                    fragment.onClickFavorite()
                }

                return true
            }

            R.id.action_people -> {

                pushFragment(
                    fragment = peopleFragment
                )

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val searchViewMenuItem: MenuItem = menu!!.findItem(R.id.action_search)

        val searchView = searchViewMenuItem.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint = resources.getString(R.string.main_activity_search_hint)

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                val fragment = supportFragmentManager.findFragmentById(R.id.activityMainFragmentContent)

                if (fragment is ShowsFragment && query != null) {

                    showsFragment.setShowNyName(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        searchView.setOnCloseListener {

            val fragment = supportFragmentManager.findFragmentById(R.id.activityMainFragmentContent)

            if (fragment is ShowsFragment) {

                showsFragment.resetList()
            }

            false
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private fun initializeFragments() {

        pushFragment(
            fragment = showsFragment
        )
    }

    override fun pushFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.activityMainFragmentContent, fragment).addToBackStack(fragment.tag).commit()
    }

    override fun popFragment() {

        supportFragmentManager.popBackStack()
    }
}
