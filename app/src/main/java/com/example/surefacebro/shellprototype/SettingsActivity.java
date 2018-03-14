package com.example.surefacebro.shellprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    private int value;
    private int savedValue;
    private TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textBox =(TextView)findViewById(R.id.saveValTextView);
        Bundle bundle = getIntent().getExtras();
        value = bundle.getInt("currentProg");
        Toast.makeText(SettingsActivity.this,"Current Prog " + value, Toast.LENGTH_LONG).show();



        SharedPreferences sp = getSharedPreferences("SavedPrefs", MODE_PRIVATE);
        savedValue = sp.getInt("savedVal", -1);
        textBox.setText("Saved Value : " + savedValue);

        backButtonSetUp();
        saveButtonSetUp();
        loadButtonSetUp();
    }


    public void backButtonSetUp() {

        Button backButtonVar = (Button) findViewById(R.id.backButton);
        backButtonVar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Settings Button Pushed", Toast.LENGTH_LONG).show();
                backButtonFunc();

            }
        });
    }

    public void saveButtonSetUp() {

        Button backButtonVar = (Button) findViewById(R.id.saveButtonId);
        backButtonVar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Settings Button Pushed", Toast.LENGTH_LONG).show();
                SharedPreferences sp = getSharedPreferences("SavedPrefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("savedVal",value);
                edit.apply();
                savedValue = value;
                textBox.setText("Saved Value : " + value);

            }
        });
    }

    public void loadButtonSetUp() {

        Button backButtonVar = (Button) findViewById(R.id.loadButtonId);
        backButtonVar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("SavedPrefs", MODE_PRIVATE);
                int result = sp.getInt("savedVal", -1);

                Toast.makeText(SettingsActivity.this,"Saved Val" + result, Toast.LENGTH_LONG).show();


            }
        });
    }

    public void backButtonFunc() {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }
}
