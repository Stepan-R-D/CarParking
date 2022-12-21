package com.example.myparking;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    public Button pay_btn;
    public EditText edit_number_pay;
    public EditText text_mo_year;
    public EditText text_cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        pay_btn = findViewById(R.id.pay_btn);
        edit_number_pay = findViewById(R.id.edit_number_pay);
        text_mo_year = findViewById(R.id.text_mo_year);
        text_cvv = findViewById(R.id.text_cvv);

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_number_pay.getText().toString().trim().length() != 16){
                    Toast.makeText(PayActivity.this, "Номер карты введен неверно", Toast.LENGTH_LONG).show();
                }
                else {
                    if(text_mo_year.getText().toString().trim().length() != 5){
                        Toast.makeText(PayActivity.this, "Месяц и год введены неверно", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(text_cvv.getText().toString().trim().length() != 3){
                            Toast.makeText(PayActivity.this, "CVV код введен неверно", Toast.LENGTH_LONG).show();
                        }
                        else {
                            //Toast.makeText(PayActivity.this, "Платёж не прошёл. " +
                              //      "Проверьте корректность ввода и повторите попытку", Toast.LENGTH_LONG).show();

                            Intent intent_fin = new Intent(PayActivity.this, FInalActivity.class);
                            startActivity(intent_fin);


                        }

                    }



                }
            }
        });



    }
}