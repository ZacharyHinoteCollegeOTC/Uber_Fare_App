package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    private EditText mDistance;
    //private Slider mPrefSlider;
    public String car = "";

    //private Spinner spinner = (Spinner) findViewById(R.id.spinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDistance = findViewById(R.id.distanceInputDecimal);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pref_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });






    }



    public void estimateUberCost(View view) {
        // Get the text that was typed into the EditText
        String distanceString = mDistance.getText().toString();

        // Convert the text into a double
        double distance = Double.parseDouble(distanceString);
        String checkedCar = car;
        if (checkedCar == "") {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
        }
        else {
            String mileage = String.valueOf(distance);
            //Toast.makeText(MainActivity.this, "Car Type: "  + checkedCar + ", Estimated Mileage: " + mileage, Toast.LENGTH_LONG).show();

            SharedPreferences sharedPref = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("carType", checkedCar);

            editor.putString("mileage", mileage);
            editor.apply();
            /*String savedCarPref = sharedPref.getString("carType", null);
            String savedMileage = sharedPref.getString("mileage", null);
            System.out.println("Commit Status: " + SuccessfulCommit);
            System.out.println("Car Pref: " + savedCarPref);
            System.out.println("Mileage: " + savedMileage);*/
            startActivity(new Intent(MainActivity.this, SecondActivity.class));


        }



    }





}

