package com.example.unifinder.RegisterPassenger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PDFListAdapter extends ArrayAdapter<String> {
    public PDFListAdapter(Context context, List<String> pdfFiles) {
        super(context, 0, pdfFiles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView pdfTextView = convertView.findViewById(android.R.id.text1);
        pdfTextView.setText(getItem(position));

        return convertView;
    }
}

