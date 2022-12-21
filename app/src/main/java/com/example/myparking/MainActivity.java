package com.example.myparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public EditText field_carNumber;
    public Button start_btn;
    public TextView text_Info;

    public final static String EXTRA_MESSAGE = "com.example.MyParking.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field_carNumber = findViewById(R.id.field_carNumber);
        start_btn = findViewById(R.id.start_btn);
        text_Info = findViewById(R.id.text_Info);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(field_carNumber.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Свободных мест нет. Извините", Toast.LENGTH_LONG).show();
                }
                else {
                    String car_no = field_carNumber.getText().toString();
                    new SendCarNo().execute(car_no);

                    HttpsRequests requests = new HttpsRequests();

                    HashMap<String, String> rightHereMap = new HashMap<String, String>()
                    {
                        {
                            put("number", car_no.toString());
                        }
                    };


                   //requests.sendAsyncRequest("/add_car",rightHereMap,HttpMethods.POST);

                    try {
                        text_Info.setText(requests.sendAsyncRequest("/add_car",rightHereMap,HttpMethods.POST));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    //Intent intent_choose = new Intent(MainActivity.this, ChooseActivity.class);

                    //intent_choose.putExtra(EXTRA_MESSAGE, car_no);
                    //startActivity(intent_choose);


                }
            }
        });
    }

    public class SendCarNo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text_Info.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            text_Info.setText(result);
        }

    }
}