package com.example.myparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FInalActivity extends AppCompatActivity {

    public TextView text_no_fin;
    String result_cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        text_no_fin = findViewById(R.id.text_no_fin);

        result_cell = "503";

        text_no_fin.setText(result_cell);
    }
}