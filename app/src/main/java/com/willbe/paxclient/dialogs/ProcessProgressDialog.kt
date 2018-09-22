package com.willbe.paxclient.dialogs

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.view.KeyEvent

class ProcessProgressDialog {

    interface OnSetListener {
        fun onSetListener(dialog: ProgressDialog, cancelable: Boolean, enDismiss: Boolean)
    }

    fun createDialog(context: Context, msg: String, cancelable: Boolean, enDismiss: Boolean, onSetListener: OnSetListener): Dialog {
        val dialog = ProgressDialog(context)
        dialog.setMessage(msg)
        dialog.isIndeterminate = true
        dialog.setCancelable(cancelable)
        if (!enDismiss)
            dialog.setOnKeyListener { dialogInterface, i, keyEvent -> i == KeyEvent.KEYCODE_BACK && keyEvent.repeatCount == 0 }
        dialog.setCanceledOnTouchOutside(false)
        onSetListener.onSetListener(dialog, cancelable, enDismiss)
        return dialog
    }
}