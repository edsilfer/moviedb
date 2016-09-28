package br.com.edsilfer.bidder.util

import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.TypedValue
import br.com.edsilfer.kiwi.commons.Constants
import br.com.edsilfer.moviedb.R
import br.com.gogame.view.dialogs.DialogInput
import com.afollestad.materialdialogs.MaterialDialog
import com.gc.materialdesign.views.ProgressBarIndeterminate

/**
 * ANKO Support library for AppCompatActivity class. It extends the base class offering common servi
 * ces that allows the programmer to produce a cleaner code
 */
// ENUNS ===========================================================================================
enum class DisplayContainer {DIALOG, SNACKBAR }

enum class LogLevel { WARNING, ERROR }

// UI NOTIFICATIONS ================================================================================
fun AppCompatActivity.showInputDialog(title: String,
                                      hint: String,
                                      onAccept: (text: String) -> Unit = {},
                                      onCancel: (text: String) -> Unit = {},
                                      onDismiss: (text: String) -> Unit = {},
                                      inputText: String? = "",
                                      inputTextSize: Float = 16f) {
    DialogInput(this)
            .setDialogTitle(title)
            .setInputHint(hint)
            .onAccept(onAccept)
            .onCancel(onCancel)
            .onDismiss(onDismiss)
            .setInputText(inputText!!)
            .setInputTextSize(inputTextSize)
            .show()
}

fun AppCompatActivity.showUnderConstructionPopUp() {
    val value = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
    MaterialDialog.Builder(this)
            .title(Constants.NotificationInterface.INFO_UNDER_CONSTRUCTION_TITLE)
            .titleColor(value.data)
            .content(Constants.NotificationInterface.INFO_UNDER_CONSTRUCTION_MESSAGE)
            .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
            .positiveColor(value.data)
            .negativeText("")
            .show()
}

// STRING PARAMETERS
fun AppCompatActivity.showErrorPopUp(content: String) {
    runOnUiThread {
        MaterialDialog.Builder(this)
                .title(Html.fromHtml("<b><font color='#c53929'>" + Constants.NotificationInterface.INFO_ERROR_TITLE + "</d></font>"))
                .content(content)
                .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
                .positiveColor(resources.getColor(android.R.color.holo_red_dark))
                .show()
    }
}

fun AppCompatActivity.showWarningPopUp(content: String) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#FFCC00'>" + Constants.NotificationInterface.INFO_WARNING_TITLE + "</d></font>"))
            .content(content)
            .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
            .positiveColor(resources.getColor(R.color.rsc_dialog_warning))
            .show()
}

fun AppCompatActivity.showPopUp(title: String, content: String, negativeText: String = Constants.NotificationInterface.BUTTON_CANCEL, onPositive: () -> Unit = {}) {
    runOnUiThread {
        val value = TypedValue()
        theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
        MaterialDialog.Builder(this)
                .title(title)
                .titleColor(value.data)
                .content(content)
                .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
                .positiveColor(value.data)
                .negativeColor(value.data)
                .negativeText(negativeText)
                .onPositive { materialDialog, dialogAction -> onPositive() }
                .show()
    }
}


// RESOURCE PARAMETERS =============================================================================
fun AppCompatActivity.showWarningPopUp(content: Int) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#FFCC00'>" + Constants.NotificationInterface.INFO_WARNING_TITLE + "</d></font>"))
            .content(getString(content))
            .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
            .positiveColor(resources.getColor(R.color.rsc_dialog_warning))
            .show()
}

fun AppCompatActivity.showErrorPopUp(content: Int) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#c53929'>" + Constants.NotificationInterface.INFO_ERROR_TITLE + "</d></font>"))
            .content(getString(content))
            .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
            .positiveColor(resources.getColor(android.R.color.holo_red_dark))
            .show()
}

fun AppCompatActivity.showPopUp(title: Int, content: Int) {
    val value = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
    MaterialDialog.Builder(this)
            .title(getString(title))
            .titleColor(value.data)
            .content(getString(content))
            .positiveText(Constants.NotificationInterface.BUTTON_OKAY)
            .positiveColor(value.data)
            .negativeColor(value.data)
            .negativeText(Constants.NotificationInterface.BUTTON_CANCEL)
            .show()
}

// LOADING =========================================================================================
fun AppCompatActivity.showCircularProgressBar() {
    try {
        runOnUiThread {
            findViewById(R.id.circularProgressBar)!!
                    .animate()
                    .translationY(getResources().getDimension(R.dimen.rsc_circular_progress_bar_height) / 2)
                    .alpha(1f).duration = Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR_CIRCULAR_DURATION.toLong()
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }

}

fun AppCompatActivity.hideCircularProgressBar() {
    try {
        runOnUiThread {
            findViewById(R.id.circularProgressBar)!!
                    .animate().translationY(getResources().getDimension(R.dimen.rsc_circular_progress_bar_height) / 2)
                    .alpha(0f)
                    .duration = Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR_CIRCULAR_DURATION.toLong()
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }
}

fun AppCompatActivity.showIndeterminateProgressBar() {
    try {
        runOnUiThread {
            val progressBar = findViewById(R.id.progress_bar) as ProgressBarIndeterminate
            progressBar.visibility = ProgressBarIndeterminate.VISIBLE
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }
}

fun AppCompatActivity.hideIndeterminateProgressBar() {
    try {
        runOnUiThread {
            val progressBar = findViewById(R.id.progress_bar) as ProgressBarIndeterminate
            progressBar.visibility = ProgressBarIndeterminate.GONE
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }
}

// OTHER ===========================================================================================

