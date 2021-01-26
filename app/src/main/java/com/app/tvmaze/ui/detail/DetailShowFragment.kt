package com.app.tvmaze.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.tvmaze.R
import com.app.tvmaze.adapters.seasons.SeasonAdapter
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.interfaces.NavigationInterface
import com.app.tvmaze.model.episode.EpisodeModel
import com.app.tvmaze.model.season.SeasonListModel
import com.app.tvmaze.model.show.ShowModel
import com.app.tvmaze.ui.content.MainActivity
import com.app.tvmaze.ui.dialogs.ShowInfoDialog
import com.app.tvmaze.ui.episode.EpisodeFragment
import com.app.tvmaze.utils.alert
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_show.*

class DetailShowFragment: Fragment(), ClickInterface {

    companion object {

        fun newInstance(model: ShowModel, navigationInterface: NavigationInterface): DetailShowFragment {

            val fragment = DetailShowFragment()

            fragment.model = model
            fragment.navigationInterface = navigationInterface

            return fragment
        }
    }

    private val dialogInfo: ShowInfoDialog by lazy {

        ShowInfoDialog.newInstance(
            show = model
        )
    }

    private lateinit var viewModel: DetailShowViewModel

    private lateinit var model: ShowModel

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

        viewModel =
            ViewModelProvider(this).get(DetailShowViewModel::class.java)

        (requireActivity() as MainActivity).supportActionBar?.title = model.name

        return inflater.inflate(R.layout.fragment_detail_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeView()
        this.initializeListeners()
        this.initializeObservers()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.action_search)
        menuItem.isVisible = false
    }

    private fun initializeListeners() {

        this.fragmentDetailShowButtonInfo.setOnClickListener {

            dialogInfo.show(childFragmentManager, ShowInfoDialog.TAG_SHOW_INFO)
        }
    }

    private fun initializeObservers() {

        this.viewModel.getSeasons(
            id = model.id
        )

        this.viewModel.listSeason.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentDetailShowRecyclerViewEpisodes.visibility = View.VISIBLE
                this.fragmentDetailShowTextViewEpisodesError.visibility = View.GONE

                this.initializeAdapter(
                    list = it
                )
            } else {

                this.fragmentDetailShowRecyclerViewEpisodes.visibility = View.GONE
                this.fragmentDetailShowTextViewEpisodesError.visibility = View.VISIBLE
            }
        })

        this.viewModel.message.observe(viewLifecycleOwner, Observer {

            requireActivity().alert(
                messageStringId = it,
                positiveStringId = R.string.accept_button
            )
        })
    }

    private fun initializeView() {

        this.fragmentDetailShowTextViewName.text = model.name

        Picasso.with(requireContext()).load(model.image.original)
            .into(this.fragmentDetailShowImageViewImage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.fragmentDetailShowTextViewSummary.text = Html.fromHtml(model.summary, Html.FROM_HTML_MODE_COMPACT)
        } else {
            this.fragmentDetailShowTextViewSummary.text = Html.fromHtml(model.summary)
        }

        this.fragmentDetailShowTextViewSummary.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {

                val maxLines = fragmentDetailShowTextViewSummary.height / fragmentDetailShowTextViewSummary.lineHeight
                fragmentDetailShowTextViewSummary.maxLines = maxLines - 1

                fragmentDetailShowTextViewSummary.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun initializeAdapter(list: List<SeasonListModel>) {

        this.fragmentDetailShowRecyclerViewEpisodes.adapter = SeasonAdapter(
            list = list,
            callback = this
        )
    }

    override fun onClickItem(model: Any) {

        navigationInterface.pushFragment(
            fragment = EpisodeFragment.newInstance(
                episode = model as EpisodeModel
            )
        )
    }
}