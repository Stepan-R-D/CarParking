package com.example.myparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseActivity extends AppCompatActivity {

    public TextView text_Info_choose;
    public RadioGroup rGroup;
    public Button choose_btn;
    int price = 0;
    public final static String MESSAGE_PAY = "com.example.MyParking.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        text_Info_choose = findViewById(R.id.text_Info_choose);
        rGroup = findViewById(R.id.rGroup);
        choose_btn = findViewById(R.id.choose_btn);

        Intent intent_choose = getIntent();
        String msg = intent_choose.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //text_Info_choose.setText(msg);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        price = 0;
                        break;
                    case R.id.hr_1:

                        price = 150;
                        break;
                    case R.id.hr_2:

                        price = 270;
                        break;
                    case R.id.hr_3:

                        price = 400;
                        break;
                    case R.id.hr_6:

                        price = 750;
                        break;
                    case R.id.hr_12:

                        price = 1400;
                        break;
                    default:
                        break;
                }
            }
        });



        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(price == -1){
                    Toast.makeText(ChooseActivity.this, "Выберите тариф", Toast.LENGTH_LONG).show();
                    //text_Info_choose.setText(Integer.toString(price));
                }
                else {
                    /*
                    String car_no = field_carNumber.getText().toString();
                    new MainActivity.SendCarNo().execute(car_no); //execute async task

                    Intent intent_choose = new Intent(MainActivity.this, ChooseActivity.class);
                    intent_choose.putExtra(EXTRA_MESSAGE, car_no);
                    startActivity(intent_choose);
                    */
                    //text_Info_choose.setText(Integer.toString(price));

                    Intent intent_pay = new Intent(ChooseActivity.this, PayActivity.class);
                    intent_pay.putExtra(MESSAGE_PAY, Integer.toString(price));
                    startActivity(intent_pay);
                }
            }
        });





    }



}
