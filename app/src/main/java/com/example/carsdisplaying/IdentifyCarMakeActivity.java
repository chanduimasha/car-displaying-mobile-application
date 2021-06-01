package com.example.carsdisplaying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class IdentifyCarMakeActivity extends AppCompatActivity {

    private final int[] images = new int[] {
            R.drawable.ford_gt_1, R.drawable.ford_gt_2, R.drawable.ford_gt_3, R.drawable.ford_gt_4, R.drawable.ford_gt_5, R.drawable.ford_gt_6,
            R.drawable.lamborghini_1, R.drawable.lamborghini_2, R.drawable.lamborghini_3, R.drawable.lamborghini_4, R.drawable.lamborghini_5, R.drawable.lamborghini_6,
            R.drawable.benz_1, R.drawable.benz_2, R.drawable.benz_3, R.drawable.benz_4, R.drawable.benz_5, R.drawable.benz_6,
            R.drawable.bmw_1, R.drawable.bmw_2, R.drawable.bmw_3, R.drawable.bmw_4, R.drawable.bmw_5, R.drawable.bmw_6,
            R.drawable.bugatti_1, R.drawable.bugatti_2, R.drawable.bugatti_3, R.drawable.bugatti_4, R.drawable.bugatti_5, R.drawable.bugatti_6};
    private int imageNumber;
    private ImageView imageView;
    private View viewScreen;
    private Spinner spinner;
    private TextView answerView, correctAnswer, timerCount;
    private Button indentBtn;
    private boolean type;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_make);

        timerCount = findViewById(R.id.timer);

        //CountDown timer
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getBoolean("TIMER");
        }

        if (type) {
            timer = new CountDownTimer(20000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerCount.setText(String.valueOf(millisUntilFinished/1000));

                }

                @Override
                public void onFinish() {
                    timerCount.setText("Finish");

                }
            }.start();
        }

        imageView = findViewById(R.id.car_view);

        // Random images
        imageNumber = (int)(Math.random() * images.length);
        imageView.setImageResource(images[imageNumber]);

    }

    public void clickEvent(View view) {
        spinner = findViewById(R.id.spinner2); // Dropdown menu
        viewScreen = findViewById(R.id.view_main);
        answerView = findViewById(R.id.guess_Answer);
        indentBtn = findViewById(R.id.Identify_btn);
        correctAnswer = findViewById(R.id.co_answer);

        String text = spinner.getSelectedItem().toString();

        switch (text) {
            case "Ford GT":
                if (imageNumber >= 0 && imageNumber < 6) {
                    answerView.setTextColor(Color.parseColor("#00ff00"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.correct_Message);
                }
                else {
                    answerView.setTextColor(Color.parseColor("#ff0000"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.wrong_Message);
                    displayCorrect(imageNumber, correctAnswer);
                }
                spinner.setEnabled(false);
                indentBtn.setText(R.string.next_Button);
                indentBtn.setOnClickListener(v -> next());
                break;


            case "Lamborghini":
                if (imageNumber >= 6 && imageNumber < 12) {
                    answerView.setTextColor(Color.parseColor("#00ff00"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.correct_Message);
                }
                else {
                    answerView.setTextColor(Color.parseColor("#ff0000"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.wrong_Message);
                    displayCorrect(imageNumber, correctAnswer);
                }
                spinner.setEnabled(false);
                indentBtn.setText(R.string.next_Button);
                indentBtn.setOnClickListener(v -> next());
                break;


            case "Benz":
                if (imageNumber >= 12 && imageNumber < 18) {
                    answerView.setTextColor(Color.parseColor("#00ff00"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.correct_Message);
                }
                else {
                    answerView.setTextColor(Color.parseColor("#ff0000"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.wrong_Message);
                    displayCorrect(imageNumber, correctAnswer);
                }
                System.out.println(text);
                spinner.setEnabled(false);
                indentBtn.setText(R.string.next_Button);
                indentBtn.setOnClickListener(v -> next());
                break;


            case "BMW":
                if (imageNumber >= 18 && imageNumber < 24) {
                    answerView.setTextColor(Color.parseColor("#00ff00"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.correct_Message);
                }
                else {
                    answerView.setTextColor(Color.parseColor("#ff0000"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.wrong_Message);
                    displayCorrect(imageNumber, correctAnswer);
                }
                spinner.setEnabled(false);
                indentBtn.setText(R.string.next_Button);
                indentBtn.setOnClickListener(v -> next());
                break;


            case "Bugatti":
                if (imageNumber >= 24 && imageNumber < 30) {
                    answerView.setTextColor(Color.parseColor("#00ff00"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.correct_Message);
                }
                else {
                    answerView.setTextColor(Color.parseColor("#ff0000"));
                    answerView.setVisibility(View.VISIBLE);
                    answerView.setText(R.string.wrong_Message);
                    displayCorrect(imageNumber, correctAnswer);
                }
                spinner.setEnabled(false);
                indentBtn.setText(R.string.next_Button);
                indentBtn.setOnClickListener(v -> next());
                break;

            default:
                Toast toast = Toast.makeText(this, "Please select a car brand", Toast.LENGTH_SHORT);
                toast.show();
        }

    }

    private void displayCorrect(int imgNumber, TextView correctAnswer) {
        correctAnswer.setVisibility(View.VISIBLE);
        if (imgNumber >= 0 && imgNumber < 6) {
            correctAnswer.setText(R.string.correct_Ford_Gt);
        }
        else if (imgNumber >= 6 && imgNumber < 12) {
            correctAnswer.setText(R.string.correct_Lamborghini);
        }
        else if (imgNumber >= 12 && imgNumber < 18) {
            correctAnswer.setText(R.string.correct_Benz);
        }
        else if (imgNumber >= 18 && imgNumber < 24) {
            correctAnswer.setText(R.string.correct_Bmw);
        }
        else {
            correctAnswer.setText(R.string.correct_Bugatti);
        }
    }

    private void next() {
        Intent intent = new Intent(this, IdentifyCarMakeActivity.class);
        intent.putExtra("TIMER", true);
        startActivity(intent);
    }
}