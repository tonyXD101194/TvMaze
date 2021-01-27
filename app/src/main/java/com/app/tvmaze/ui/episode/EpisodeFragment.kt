package com.app.tvmaze.ui.episode

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import com.app.tvmaze.R
import com.app.tvmaze.model.episode.EpisodeModel
import com.app.tvmaze.ui.content.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_episode.*

class EpisodeFragment: Fragment() {

    companion object {

        fun newInstance(episode: EpisodeModel): EpisodeFragment {

            val fragment = EpisodeFragment()

            fragment.episode = episode

            return fragment
        }
    }

    private lateinit var episode: EpisodeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as MainActivity).supportActionBar?.title = episode.name

        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeView()
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

    private fun initializeView() {

        this.fragmentEpisodeTextViewName.text = episode.name
        this.fragmentEpisodeTextViewNumberDescription.text = episode.number.toString()
        this.fragmentEpisodeTextViewSeasonDescription.text = episode.season.toString()
        this.fragmentEpisodeTextViewDurationDescription.text = String.format(
            getString(R.string.fragment_episode_minutes), episode.runtime.toString()
        )

        Picasso.with(requireContext()).load(episode.image.original)
            .into(this.fragmentEpisodeImageViewImage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.fragmentEpisodeTextViewSummary.text = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_COMPACT)
        } else {
            this.fragmentEpisodeTextViewSummary.text = Html.fromHtml(episode.summary)
        }

        this.fragmentEpisodeTextViewSummary.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {

                val maxLines = fragmentEpisodeTextViewSummary.height / fragmentEpisodeTextViewSummary.lineHeight
                fragmentEpisodeTextViewSummary.maxLines = maxLines - 1

                fragmentEpisodeTextViewSummary.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}