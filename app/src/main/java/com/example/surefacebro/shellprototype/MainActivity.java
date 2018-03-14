package com.example.surefacebro.shellprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static SeekBar seek_bar;
    private static TextView text_view;

    private int lowBarProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lowBarProg = 0;
        seekbar();
        togglebutton();
        settingsButtonSetUp();
    }

//---------------------------------------------------------------------------------------------------------

    public void settingsButtonSetUp() {

        Button button = (Button) findViewById(R.id.settingsButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Settings Button Pushed", Toast.LENGTH_LONG).show();
                openSettingsFunc();
            }
        });

    }

    public void togglebutton() {
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    int max = getResources().getInteger(R.integer.currentProg);
                    Toast.makeText(MainActivity.this,"Button Toggled ON with Val " + max, Toast.LENGTH_LONG).show();
                } else {
                    // The toggle is disabled
                    Toast.makeText(MainActivity.this,"Button Toggled OFF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void seekbar(){
        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        text_view =(TextView)findViewById(R.id.textView);
        text_view.setText("Covered : " + seek_bar.getProgress() + " / " +seek_bar.getMax());

        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Covered : " + progress + " / " +seek_bar.getMax());
                        //Toast.makeText(MainActivity.this,"SeekBar in progress",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(MainActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_view.setText("Covered : " + progress_value + " / " +seek_bar.getMax());
                        lowBarProg = progress_value;
                        //Toast.makeText(MainActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }


    public void openSettingsFunc() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("currentProg", lowBarProg);
        startActivity(intent);
    }

    public int getLowBarProg()
    {
        return lowBarProg;
    }


}
