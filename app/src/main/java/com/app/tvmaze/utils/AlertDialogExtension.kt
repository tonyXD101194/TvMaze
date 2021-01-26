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


// region alert dialog callback

//private fun getAlertDialogCallback(
//    context: Context,
//    message: String = "",
//    positiveText: String = "",
//    negativeText: String = "",
//    callback: AlertDialogEventInterface
//): AlertDialog {
//
//    val dialogBuilder = AlertDialog.Builder(context)
//
//    dialogBuilder.setCancelable(false)
//
//    if (message.isNotEmpty()) {
//
//        dialogBuilder.setMessage(message)
//    }
//
//    if (positiveText.isNotEmpty()) {
//
//        dialogBuilder.setPositiveButton(positiveText) { dialog, _ ->
//
//            dialog.dismiss()
//            callback.onAccept()
//        }
//    }
//
//    if (negativeText.isNotEmpty()) {
//
//        dialogBuilder.setNegativeButton(negativeText) { dialog, _ ->
//
//            dialog.dismiss()
//            callback.onCancel()
//        }
//    }
//
//    return dialogBuilder.create()
//}

//fun Activity.alertAcceptCallback(
//    message: Int,
//    positiveText: Int,
//    callback: AlertDialogEventInterface
//) {
//
////    val positiveText: String = this.getString(R.string.button_text_accept)
////    val negativeText: String = this.getString(R.string.button_text_cancel)
//
//    this.alertCallback(
//        message = this.getString(message),
//        positiveText = this.getString(positiveText),
//        negativeText = "",
//        callback = callback
//    )
//}

//fun Activity.alertCallback(
//    message: Int,
//    positiveText: Int,
//    negativeText: Int,
//    callback: AlertDialogEventInterface
//) {
//
////    val positiveText: String = this.getString(R.string.button_text_accept)
////    val negativeText: String = this.getString(R.string.button_text_cancel)
//
//    this.alertCallback(
//        message = this.getString(message),
//        positiveText = this.getString(positiveText),
//        negativeText = this.getString(negativeText),
//        callback = callback
//    )
//}
//
//fun Activity.alertCallback(
//    message: String,
//    positiveText: String,
//    negativeText: String,
//    callback: AlertDialogEventInterface
//) {
//
//    val alertDialog: AlertDialog =
//        getAlertDialogCallback(
//            context = this,
//            message = message,
//            positiveText = positiveText,
//            negativeText = negativeText,
//            callback = callback
//        )
//
//    alertDialog.show()
//}

//fun Activity.alertLogout(
//    callback: AlertDialogEventInterface
//) {
//
//    this.alertLogout(
//        message = R.string.message_close_session,
//        positiveText = R.string.button_yes,
//        negativeText = R.string.button_no,
//        callback = callback
//    )
//}
//
//fun Activity.alertLogout(
//    message: Int,
//    positiveText: Int,
//    negativeText: Int,
//    callback: AlertDialogEventInterface
//) {
//
//    val alertDialog: AlertDialog =
//        getAlertDialogCallback(
//            context = this,
//            message = this.getString(message),
//            positiveText = this.getString(positiveText),
//            negativeText = this.getString(negativeText),
//            callback = callback
//        )
//
//    alertDialog.show()
//}

// endregion