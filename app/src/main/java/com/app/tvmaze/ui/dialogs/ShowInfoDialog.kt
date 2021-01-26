package com.app.tvmaze.ui.dialogs

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.tvmaze.R
import com.app.tvmaze.model.show.ShowModel
import kotlinx.android.synthetic.main.dialog_show_info.*

class ShowInfoDialog: DialogFragment() {

    companion object {

        const val TAG_SHOW_INFO = "TAG_SHOW_INFO"

        fun newInstance(show: ShowModel): ShowInfoDialog  {

            val dialog = ShowInfoDialog()

            dialog.show = show

            return dialog
        }
    }

    private lateinit var show: ShowModel

    override fun onStart() {
        super.onStart()

        val dialog = dialog

        if (dialog != null) {

            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT

            dialog.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_show_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeViews()
        this.initializeListeners()
    }

    private fun initializeListeners() {

        this.dialogShowInfoImageViewClose.setOnClickListener {

            this.dismiss()
        }

        this.dialogShowInfoTextViewSiteDescription.setOnClickListener {

            // TODO open website
        }
    }

    private fun initializeViews() {

        this.dialogShowInfoTextViewDaysDescription.text = show.schedule.days.joinToString(", ")
        this.dialogShowInfoTextViewHourDescription.text = show.schedule.time
        this.dialogShowInfoTextViewGenderDescription.text = show.genres.joinToString(" | ")

        this.dialogShowInfoTextViewSiteDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        this.dialogShowInfoTextViewSiteDescription.text = show.officialSite
    }
}