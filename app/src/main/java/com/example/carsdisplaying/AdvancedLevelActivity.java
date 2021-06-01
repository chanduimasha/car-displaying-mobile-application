package com.example.carsdisplaying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class AdvancedLevelActivity extends AppCompatActivity {

    private final int[] images = new int[] {
            R.drawable.ford_gt_1, R.drawable.ford_gt_2, R.drawable.ford_gt_3, R.drawable.ford_gt_4, R.drawable.ford_gt_5, R.drawable.ford_gt_6,
            R.drawable.lamborghini_1, R.drawable.lamborghini_2, R.drawable.lamborghini_3, R.drawable.lamborghini_4, R.drawable.lamborghini_5, R.drawable.lamborghini_6,
            R.drawable.benz_1, R.drawable.benz_2, R.drawable.benz_3, R.drawable.benz_4, R.drawable.benz_5, R.drawable.benz_6,
            R.drawable.bmw_1, R.drawable.bmw_2, R.drawable.bmw_3, R.drawable.bmw_4, R.drawable.bmw_5, R.drawable.bmw_6,
            R.drawable.bugatti_1, R.drawable.bugatti_2, R.drawable.bugatti_3, R.drawable.bugatti_4, R.drawable.bugatti_5, R.drawable.bugatti_6};

    private int imageNumber1, imageNumber2, imageNumber3, score;
    private ImageView imageView1, imageView2, imageView3;
    private String car1, car2, car3;
    private EditText input1, input2, input3;
    private String answer1, answer2, answer3;
    private int attempt = 3;
    private TextView textView1, textView2, textView3, viewScore, timerCount;
    private Button nextBtn;
    private boolean type;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        timerCount = findViewById(R.id.textView7);

        // CountDown timer
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

        do {
            imageView1 = findViewById(R.id.imageView1);
            imageView2 = findViewById(R.id.imageView2);
            imageView3 = findViewById(R.id.imageView3);


            // Random images
            imageNumber1 = (int) (Math.random() * images.length);
            imageNumber2 = (int) (Math.random() * images.length);
            imageNumber3 = (int) (Math.random() * images.length);

            imageView1.setImageResource(images[imageNumber1]);
            imageView2.setImageResource(images[imageNumber2]);
            imageView3.setImageResource(images[imageNumber3]);

        } while ((imageNumber1 == imageNumber2) || (imageNumber1 == imageNumber3) || (imageNumber2 == imageNumber3));

        car1 = getCar(imageNumber1).toLowerCase();
        car2 = getCar(imageNumber2).toLowerCase();
        car3 = getCar(imageNumber3).toLowerCase();

    }

    private String getCar(int imgNumber) {
        if (imgNumber >= 0 && imgNumber < 6) {
            return "Ford";
        }
        else if (imgNumber >= 6 && imgNumber < 12) {
            return "Lamborghini";
        }
        else if (imgNumber >= 12 && imgNumber < 18) {
            return "Benz";
        }
        else if (imgNumber >= 18 && imgNumber < 24) {
            return "BMW";
        }
        else {
            return "Bugatti";
        }

    }

    public void submit(View view) {

        input1 = findViewById(R.id.editText1);
        input2 = findViewById(R.id.editText2);
        input3 = findViewById(R.id.editText3);

        answer1 = input1.getText().toString().toLowerCase();
        answer2 = input2.getText().toString().toLowerCase();
        answer3 = input3.getText().toString().toLowerCase();

        nextBtn = findViewById(R.id.submit);
        viewScore = findViewById(R.id.marks);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        if (attempt != 0) {

            if (answer1.equals(car1)) {
                input1.setTextColor(Color.parseColor("#00ff00"));
                textView1.setText("Correct");
                textView1.setTextColor(Color.parseColor("#ffcc00"));
                input1.setEnabled(false);
            } else {
                input1.setTextColor(Color.parseColor("#ff0000"));
            }

            if (answer2.equals(car2)) {
                input2.setTextColor(Color.parseColor("#00ff00"));
                textView2.setText("Correct");
                textView2.setTextColor(Color.parseColor("#ffcc00"));
                input2.setEnabled(false);
            } else {
                input2.setTextColor(Color.parseColor("#ff0000"));

            }
            if (answer3.equals(car3)) {
                input3.setTextColor(Color.parseColor("#00ff00"));
                textView3.setText("Correct");
                textView3.setTextColor(Color.parseColor("#ffcc00"));
                input3.setEnabled(false);
            } else {
                input3.setTextColor(Color.parseColor("#ff0000"));
            }

            attempt -= 1;

            if (attempt == 0) {
                if (!answer1.equals(car1)) {
                    textView1.setText("Correct answer is " + car1);
                    textView1.setTextColor(Color.parseColor("#ffcc00"));
                    input1.setEnabled(false);
                }

                if (!answer2.equals(car2)) {
                    textView2.setText("Correct answer is " + car2);
                    textView2.setTextColor(Color.parseColor("#ffcc00"));
                    input2.setEnabled(false);
                }

                if (!answer3.equals(car3)) {
                    textView3.setText("Correct answer is " + car3);
                    textView3.setTextColor(Color.parseColor("#ffcc00"));
                    input3.setEnabled(false);
                }
                nextBtn.setText("Next");
                nextBtn.setOnClickListener(v -> next());
            }

        }


        // Calculate the score
        if (answer1.equals(car1) && answer2.equals(car2) && answer3.equals(car3)) {
            nextBtn.setText("Next");
            nextBtn.setOnClickListener(v -> next());
            score = 3;
        }
        else if (answer1.equals(car1) && answer2.equals(car2)) {
            score = 2;
        }
        else if (answer1.equals(car1) && answer3.equals(car3)) {
            score = 2;
        }
        else if (answer2.equals(car2) && answer3.equals(car3)) {
            score = 2;
        }
        else if (answer1.equals(car1)) {
            score = 1;
        }
        else if (answer2.equals(car2)) {
            score = 1;
        } else if (answer3.equals(car3)) {
            score = 1;
        } else {
            score = 0;
        }
        viewScore.setText("Score - " + score);

    }

    private void next() {
        Intent intent = new Intent(this, AdvancedLevelActivity.class);
        intent.putExtra("TIMER", true);
        startActivity(intent);
    }

}