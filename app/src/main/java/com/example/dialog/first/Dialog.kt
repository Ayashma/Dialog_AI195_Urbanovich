package com.example.dialog.first

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

// контейнер диалоговых окон
class Dialog : DialogFragment() {

    private var title: String? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            message = it.getString(ARG_MESSAGE)
        }
    }

    // создание объекта диалога
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = it?.let { thisdlg -> androidx.appcompat.app.AlertDialog.Builder(thisdlg) }
            builder
                ?.setTitle(title)
                ?.setMessage(message)
                ?.setPositiveButton("OK") { _, _ -> }
            builder!!.create()
        }
    }

    // структура текста в диалоге
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"

        fun newInstance(title: String, message: String) = Dialog().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_MESSAGE, message)
            }
        }
    }
}