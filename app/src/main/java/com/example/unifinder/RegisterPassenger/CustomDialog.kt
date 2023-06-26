package com.example.unifinder.RegisterPassenger

import android.app.AlertDialog
import android.content.Context

class CustomDialog(context: Context) {

    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle("Dialog Title")
        .setMessage("Dialog Message")
        .setPositiveButton("OK") { _, _ ->
            // Handle OK button click
        }
        .setNegativeButton("Cancel") { _, _ ->
            // Handle Cancel button click
        }
        .create()

    fun show() {
        dialog.show()
    }

}