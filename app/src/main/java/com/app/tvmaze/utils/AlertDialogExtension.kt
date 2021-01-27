package com.app.tvmaze.utils

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

// region generic dialogs

private fun getAlertDialog(
    context: Context,
    message: String = "",
    positiveText: String = ""
): AlertDialog {

    val dialogBuilder = AlertDialog.Builder(context)

    if (message.isNotEmpty()) {

        dialogBuilder.setMessage(message)
    }

    if (positiveText.isNotEmpty()) {

        dialogBuilder.setPositiveButton(positiveText) { dialog, _ ->

            dialog.dismiss()
        }
    }

    return dialogBuilder.create()
}

fun Activity.alert(
    messageStringId: Int,
    positiveStringId: Int
) {

    val message: String = this.getString(messageStringId)
    val positiveText: String = this.getString(positiveStringId)

    this.alert(
        message = message,
        positiveText = positiveText
    )
}

fun Activity.alert(
    message: String,
    positiveText: String
) {

    val alertDialog: AlertDialog =
        getAlertDialog(
            context = this,
            message = message,
            positiveText = positiveText
        )

    alertDialog.show()
}

// endregion