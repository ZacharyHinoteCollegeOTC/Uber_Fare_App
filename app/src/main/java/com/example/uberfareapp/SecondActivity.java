package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    //private TextView vehicleText = (TextView)findViewById(R.id.vehicleTextView);
    //private TextView mileageText = (TextView)findViewById(R.id.mileageTextView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView vehicleText = (TextView)findViewById(R.id.vehicleTextView);
        TextView mileageText = (TextView)findViewById(R.id.mileageTextView);
        TextView totalText = (TextView)findViewById(R.id.finalPriceTextView);
        SharedPreferences sharedPref = getSharedPreferences("myprefs", Context.MODE_PRIVATE);


        String carPref = sharedPref.getString("carType", null);
        vehicleText.setText("Selected Car: " + carPref + ".");

        String mileage = sharedPref.getString("mileage", null);
        mileageText.setText("Mileage: " + mileage + " miles.");
        String total = String.valueOf(calculateTotal(carPref, mileage));
        totalText.setText(total);

        Button finishButton = (Button)findViewById(R.id.button2);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });



        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
            }
        });

    }

    public double calculateTotal(String vehicleName, String mileage) {
        double numericMileage = Double.parseDouble(mileage);
        double total = numericMileage * 3.25;
        total += 3;

        if (vehicleName.equals("Smart Car")) {
            total += 2;
        }
        else if (vehicleName.equals("Minivan")) {
            total += 5;
        }


        return(total);
    }

}