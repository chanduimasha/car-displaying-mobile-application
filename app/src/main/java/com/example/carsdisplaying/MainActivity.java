package com.example.carsdisplaying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    boolean timerState;
    Switch clickSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickSwitch = findViewById(R.id.switch1);
        clickSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> timerState = isChecked);

    }

    public void carMake(View view) {
        Intent intent = new Intent(this, IdentifyCarMakeActivity.class);
        intent.putExtra("TIMER", timerState);
        startActivity(intent);
    }

    public void hints(View view) {
        Intent intent = new Intent(this, HintsActivity.class);
        intent.putExtra("TIMER", timerState);
        startActivity(intent);
    }

    public void identifyCarImage(View view) {
        Intent intent = new Intent(this, IdentifyCarImageActivity.class);
        intent.putExtra("TIMER", timerState);
        startActivity(intent);    }

    public void advancedLevel(View view) {
        Intent intent = new Intent(this, AdvancedLevelActivity.class);
        intent.putExtra("TIMER", timerState);
        startActivity(intent);    }
    }
