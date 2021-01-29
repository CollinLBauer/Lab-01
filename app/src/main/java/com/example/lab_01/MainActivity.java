package com.example.lab_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startB = this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = this.findViewById((R.id.timer));
        timeElapsedView = this.findViewById(R.id.timeElapsed);
        timeElapsedView.setText(String.format(getString(R.string.timeElapsed), 0));
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(String.format(getString(R.string.timer), startTime));
    }

    @Override
    public void onClick(View v) {
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText(getString(R.string.startLabel));
        }
        else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText(getString(R.string.resetLabel));

        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            text.setText(getString(R.string.timerFinish));
            timeElapsedView.setText(String.format(getString(R.string.timeElapsed), startTime)); //startTime
        }

        public void onTick(long millisUntilFinished) {
            text.setText(String.format(getString(R.string.timeRemaining), millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(String.format(getString(R.string.timeElapsed), timeElapsed));
        }
    }
}
