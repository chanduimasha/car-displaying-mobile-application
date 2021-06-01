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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HintsActivity extends AppCompatActivity {

    private final int[] images = new int[] {
            R.drawable.ford_gt_1, R.drawable.ford_gt_2, R.drawable.ford_gt_3, R.drawable.ford_gt_4, R.drawable.ford_gt_5, R.drawable.ford_gt_6,
            R.drawable.lamborghini_1, R.drawable.lamborghini_2, R.drawable.lamborghini_3, R.drawable.lamborghini_4, R.drawable.lamborghini_5, R.drawable.lamborghini_6,
            R.drawable.benz_1, R.drawable.benz_2, R.drawable.benz_3, R.drawable.benz_4, R.drawable.benz_5, R.drawable.benz_6,
            R.drawable.bmw_1, R.drawable.bmw_2, R.drawable.bmw_3, R.drawable.bmw_4, R.drawable.bmw_5, R.drawable.bmw_6,
            R.drawable.bugatti_1, R.drawable.bugatti_2, R.drawable.bugatti_3, R.drawable.bugatti_4, R.drawable.bugatti_5, R.drawable.bugatti_6};
    private int imageNumber;
    private ImageView imageView;
    private TextView textView, answer, correctAnswer, timerCount;
    private EditText editText;
    private Button nextBtn;
    private String car, dashedName, dashedNameSpaced;
    private int attempt = 3;
    private StringBuilder stringBuilder, stringBuilder1;
    private boolean type;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        timerCount = findViewById(R.id.textView6);

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

        imageView = findViewById(R.id.imageView6);
        // Random images
        imageNumber = (int)(Math.random() * images.length);
        imageView.setImageResource(images[imageNumber]);


        car = getCar(imageNumber).toLowerCase();

        stringBuilder = new StringBuilder();
        stringBuilder1 = new StringBuilder();

        for (int i=0; i<=car.length()-1; i++) {
            stringBuilder.append("-");
            stringBuilder.append(" ");
            stringBuilder1.append("-");
        }
        dashedNameSpaced = stringBuilder.toString();
        dashedName = stringBuilder1.toString();

        System.out.println(dashedName);
        System.out.println(dashedNameSpaced);

        textView = findViewById(R.id.textView);

        textView.setText(dashedNameSpaced);

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
        editText = findViewById(R.id.editText);
        answer = findViewById(R.id.textView4);
        correctAnswer = findViewById(R.id.textView5);
        nextBtn = findViewById(R.id.button6);

        String text = editText.getText().toString().toLowerCase();

        if (attempt != 1) {

            if (text.equals("") || (!car.contains(text))) {
                attempt -= 1;
                editText.setText("");
                System.out.println(attempt);
            }

            if (car.contains(text)) {
                try {
                    Pattern guessText = Pattern.compile(text);
                    Matcher matcher = guessText.matcher(car);

                    ArrayList<Integer> tempList = new ArrayList<>();

                    while (matcher.find()) {
                        tempList.add(matcher.start());
                    }

                    for (int i = 0; i <= tempList.size() - 1; i++) {
                        dashedName = dashedName.substring(0, tempList.get(i)) + text + dashedName.substring(1 + tempList.get(i));
                        System.out.println(dashedName);
                    }

                    textView.setText(dashedName);
                    editText.setText("");

                    if (dashedName.equals(car) && (attempt != 1)) {
                        answer.setText("Correct");
                        answer.setTextColor(Color.parseColor("#00ff00"));
                        nextBtn.setText("Next");
                        nextBtn.setOnClickListener(v -> next());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (dashedName.equals(car)) {
                answer.setText("Correct");
                answer.setTextColor(Color.parseColor("#00ff00"));
            } else {
                answer.setText("Wrong");
                answer.setTextColor(Color.parseColor("#ff0000"));
                correctAnswer.setText("Correct answer is " + car);
                correctAnswer.setTextColor(Color.parseColor("#ffcc00"));
            }
            nextBtn.setText("Next");
            nextBtn.setOnClickListener(v -> next());
        }

    }

    private void next() {
        Intent intent = new Intent(this, HintsActivity.class);
        intent.putExtra("TIMER", true);
        startActivity(intent);
    }
}