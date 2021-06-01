package com.example.carsdisplaying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class IdentifyCarImageActivity extends AppCompatActivity {

    private final int[] images = new int[] {
            R.drawable.ford_gt_1, R.drawable.ford_gt_2, R.drawable.ford_gt_3, R.drawable.ford_gt_4, R.drawable.ford_gt_5, R.drawable.ford_gt_6,
            R.drawable.lamborghini_1, R.drawable.lamborghini_2, R.drawable.lamborghini_3, R.drawable.lamborghini_4, R.drawable.lamborghini_5, R.drawable.lamborghini_6,
            R.drawable.benz_1, R.drawable.benz_2, R.drawable.benz_3, R.drawable.benz_4, R.drawable.benz_5, R.drawable.benz_6,
            R.drawable.bmw_1, R.drawable.bmw_2, R.drawable.bmw_3, R.drawable.bmw_4, R.drawable.bmw_5, R.drawable.bmw_6,
            R.drawable.bugatti_1, R.drawable.bugatti_2, R.drawable.bugatti_3, R.drawable.bugatti_4, R.drawable.bugatti_5, R.drawable.bugatti_6};

    private int imageNumber1, imageNumber2, imageNumber3;
    private ImageView imageView1, imageView2, imageView3;
    private int car;
    private boolean type;
    private CountDownTimer timer;
    private TextView timerCount, answerView, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image);

        timerCount = findViewById(R.id.textView8);
        answerView = findViewById(R.id.answer);
        textView = findViewById(R.id.carBrand);

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
            imageView1 = findViewById(R.id.imageView5);
            imageView2 = findViewById(R.id.imageView7);
            imageView3 = findViewById(R.id.imageView8);


            // Random images
            imageNumber1 = (int) (Math.random() * images.length);
            imageNumber2 = (int) (Math.random() * images.length);
            imageNumber3 = (int) (Math.random() * images.length);

            imageView1.setImageResource(images[imageNumber1]);
            imageView2.setImageResource(images[imageNumber2]);
            imageView3.setImageResource(images[imageNumber3]);

        } while ((imageNumber1 == imageNumber2) || (imageNumber1 == imageNumber3) || (imageNumber2 == imageNumber3));


        int[] pictures = new int[] {
                imageNumber1,imageNumber2,imageNumber3
        };

        // Random image
        car = (int)(Math.random() * pictures.length);


        switch (car){
            case 0:
                String car1 = getCar(imageNumber1);
                textView.setText(car1);
            case 1:
                String car2 = getCar(imageNumber2);
                textView.setText(car2);
            case 2:
                String car3 = getCar(imageNumber3);
                textView.setText(car3);
        }


    }

    private String getCar(int imgNumber) {
        if (imgNumber >= 0 && imgNumber < 6) {
            return "Ford GT";
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




    public void clickOne(View view) {
        String carBrandName = textView.getText().toString();
        String answer = getCar(imageNumber1);
        if (answer.equals(carBrandName)) {
            answerView.setText(R.string.correct_Message);
            answerView.setTextColor(Color.parseColor("#00ff00"));
        }
        else {
            answerView.setText(R.string.wrong_Message);
            answerView.setTextColor(Color.parseColor("#ff0000"));
        }
        System.out.println(carBrandName + "-" + answer);

    }

    public void clickTwo(View view) {
        String carBrandName = textView.getText().toString();
        String answer = getCar(imageNumber2);
        if (answer.equals(carBrandName)) {
            answerView.setText(R.string.correct_Message);
            answerView.setTextColor(Color.parseColor("#00ff00"));
        }
        else {
            answerView.setText(R.string.wrong_Message);
            answerView.setTextColor(Color.parseColor("#ff0000"));
        }
        System.out.println(carBrandName + "-" + answer);

    }

    public void clickThree(View view) {
        String carBrandName = textView.getText().toString();
        String answer = getCar(imageNumber3);
        if (answer.equals(carBrandName)) {
            answerView.setText(R.string.correct_Message);
            answerView.setTextColor(Color.parseColor("#00ff00"));
        }
        else {
            answerView.setText(R.string.wrong_Message);
            answerView.setTextColor(Color.parseColor("#ff0000"));
        }
        System.out.println(carBrandName + "-" + answer);


    }



    public void reload(View view) {
        Intent intent = new Intent(this, IdentifyCarImageActivity.class);
        intent.putExtra("TIMER", true);
        startActivity(intent);
    }
}