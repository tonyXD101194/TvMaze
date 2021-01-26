package com.app.tvmaze.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.tvmaze.R
import com.app.tvmaze.adapters.seasons.SeasonAdapter
import com.app.tvmaze.interfaces.ClickInterface
import com.app.tvmaze.model.season.SeasonListModel
import com.app.tvmaze.model.show.ShowModel
import com.app.tvmaze.utils.alert
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_show.*
import java.lang.StringBuilder

class DetailShowFragment: Fragment(), ClickInterface {

    companion object {

        fun newInstance(model: ShowModel): DetailShowFragment {

            val fragment = DetailShowFragment()

            fragment.model = model

            return fragment
        }
    }

    private lateinit var viewModel: DetailShowViewModel

    private lateinit var model: ShowModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(DetailShowViewModel::class.java)

        return inflater.inflate(R.layout.fragment_detail_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeView()
        this.initializeListeners()
        this.initializeObservers()
    }

    private fun initializeListeners() {


    }

    private fun initializeObservers() {

        this.viewModel.getSeasons(
            id = model.id
        )

        this.viewModel.listSeason.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.initializeAdapter(
                    list = it
                )
            } else {


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

        val append = StringBuilder()

        append.append(this.fragmentDetailShowTextViewDays.text)
        append.append(" ")
        append.append(model.schedule.days.joinToString(", "))

        this.fragmentDetailShowTextViewDays.text = append.toString()
        this.fragmentDetailShowTextViewHour.text = model.schedule.time
        this.fragmentDetailShowTextViewGender.text = model.genres.joinToString(", ")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.fragmentDetailShowTextViewSummary.text = Html.fromHtml(model.summary, Html.FROM_HTML_MODE_COMPACT)
        } else {
            this.fragmentDetailShowTextViewSummary.text = Html.fromHtml(model.summary)
        }
    }

    private fun initializeAdapter(list: List<SeasonListModel>) {

        this.fragmentDetailShowRecyclerViewEpisodes.adapter = SeasonAdapter(
            list = list,
            callback = this
        )
    }

    override fun onClickItem(model: Any) {

        // TODO open dialog
    }
}