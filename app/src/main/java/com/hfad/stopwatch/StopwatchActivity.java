package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StopwatchActivity extends Activity {

    @BindView(R.id.time_view)
    public TextView timeView;

    @BindView(R.id.start_button)
    public Button start_button;

    @BindView(R.id.stop_button)
    public Button stop_button;

    @BindView(R.id.reset_button)
    public Button reset_button;

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
        Log.i("log", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("log", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("log", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (wasRunning) {
            running = true;
        }

        Log.i("log", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        wasRunning = running;
        running = false;

        Log.i("log", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("log", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("log", "onDestroy");
    }

    @OnClick(R.id.start_button)
    public void onClickStart(View view) {
        running = true;
    }

    @OnClick(R.id.stop_button)
    public void onClickStop(View view) {
        running = false;
    }

    @OnClick(R.id.reset_button)
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final Handler handler = new Handler();

        handler.post(new Runnable() {

            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }
}
