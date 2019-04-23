package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Log.i("log", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

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

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
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

}
