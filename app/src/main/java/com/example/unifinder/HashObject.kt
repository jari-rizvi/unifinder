package com.example.unifinder

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import java.security.MessageDigest

object HashObject {


    fun hashFunction(data: String): String {
        val sha256 = MessageDigest.getInstance("SHA-256")
        val hashedBytes = sha256.digest(data.toByteArray())
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }

    fun verifyHash(originalData: String, hashValue: String): Boolean {
        val calculatedHash = hashFunction(originalData)
        return calculatedHash == hashValue
    }

    // Example usage
    fun main() {
        val originalData = "sensitive_data"
        val originalData2 = "sensitive_data"
        val hashedValue = hashFunction(originalData)
        println("Hashed value: $hashedValue")

        val isValid = verifyHash(originalData2, hashedValue)
        println("Is valid? $isValid")
    }

   /* fun progressDialog(context: Context,layoutInflater:LayoutInflater): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater: LayoutInflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.progressdialog, null)
        builder.setView(dialogView)
        // Find views within the custom layout
        val dialog = builder.create()
        dialog.setCancelable(false)
        return dialog
    }*/
}