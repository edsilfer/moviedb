package br.com.gogame.view.dialogs

import android.app.Dialog
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import br.com.edsilfer.moviedb.R
import br.com.gogame.view.layouts.CustomDialog
import com.gc.materialdesign.views.ButtonFlat

/**
 * Provides the binding methods for a common Input Dialog interface, used in Anko Support libraries
 */
class DialogInput(var mActivity: AppCompatActivity) {

    private var mDialog: Dialog? = null

    private var mOkay: ButtonFlat? = null
    private var mCancel: ButtonFlat? = null
    private var mInput: EditText? = null
    private var mTitle: TextView? = null

    // LISTENERS
    private var mOnCancel: (text: String) -> Unit = { }
    private var mOnAccept: (text: String) -> Unit = { }
    private var mOnDismiss: (text: String) -> Unit = { }

    init {
        initMembers()
    }

    // PUBLIC INTERFACE ============================================================================
    fun show(): Unit {
        mDialog!!.show()
        return
    }

    fun onAccept(onAccept: (text: String) -> Unit): DialogInput {
        mOnAccept = onAccept
        return this
    }

    fun onCancel(onCancel: (text: String) -> Unit): DialogInput {
        mOnCancel = onCancel
        return this
    }

    fun onDismiss(onDismiss: (text: String) -> Unit): DialogInput {
        mOnDismiss = onDismiss
        return this
    }

    fun setInputHint(hint: String): DialogInput {
        (mDialog!!.findViewById(R.id.input_wrapper) as TextInputLayout).hint = hint
        return this
    }

    fun setInputTextSize(size: Float): DialogInput {
        mInput!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        return this
    }

    fun setInputText(hint: String): DialogInput {
        mInput!!.setText(hint)
        return this
    }

    fun setDialogTitle(title: String): DialogInput {
        mTitle!!.setText(title)
        return this
    }

    // LOADERS/INITIALIZATIONS =====================================================================
    private fun initDialog() {
        mDialog = CustomDialog(mActivity, { height, width -> })
        mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog!!.setContentView(R.layout.dlg_input)
    }

    private fun initMembers() {
        initDialog()
        mOkay = mDialog!!.findViewById(R.id.okay) as ButtonFlat
        mCancel = mDialog!!.findViewById(R.id.cancel) as ButtonFlat
        mInput = mDialog!!.findViewById(R.id.input) as EditText
        mTitle = mDialog!!.findViewById(R.id.dialog_title) as TextView
        setUIListeners()
    }

    private fun setUIListeners() {
        onOkayClicked()
        onCancelClicked()
        onDismiss()
    }

    // UI EVENTS ===================================================================================
    private fun onOkayClicked() {
        mOkay!!.setOnClickListener {
            mOnAccept(mInput!!.text.toString())
            mDialog!!.dismiss()
        }
    }

    private fun onCancelClicked() {
        mCancel!!.setOnClickListener {
            mOnCancel(mInput!!.text.toString())
            mDialog!!.dismiss()
        }
    }

    private fun onDismiss() {
        mDialog!!.setOnDismissListener {
            mOnDismiss(mInput!!.text.toString())
        }
    }
}

