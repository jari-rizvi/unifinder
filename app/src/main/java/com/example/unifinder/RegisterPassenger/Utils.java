package com.example.unifinder.RegisterPassenger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.unifinder.R;

public class Utils {

    public static Dialog progressDialog(Context context, LayoutInflater layoutInflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = layoutInflater;
        View dialogView = inflater.inflate(R.layout.progressdialog, null);
        builder.setView(dialogView);

        // Find views within the custom layout

        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        return dialog;
    }
}
