package com.example.stepscounter_obi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private TextView tv_steps, tv_goal;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private Button btnRs;
    private boolean checkGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_steps = (TextView) findViewById(R.id.tv_steps);
        tv_goal = findViewById(R.id.tvGoal);
        btnRs = (Button) findViewById(R.id.btnRs);
        btnRs.setOnClickListener(view -> {
            stepCount = 0;
            tv_goal.setText("Chưa hoàn thành mục tiêu!");
            checkGoal=false;
        });
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (!checkGoal){
                    if (sensorEvent != null) {
                        float x_acceleration = sensorEvent.values[0];
                        float y_acceleration = sensorEvent.values[1];
                        float z_acceleration = sensorEvent.values[2];

                        double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration
                                * z_acceleration);
                        double MagnitudeDelta = Magnitude - MagnitudePrevious;
                        MagnitudePrevious = Magnitude;

                        if (MagnitudeDelta > 4) {
                            stepCount++;
                        }
                        tv_steps.setText(stepCount.toString());
                        Intent i = getIntent();
                        int goal = i.getIntExtra("goal", 0);
                        if (stepCount == goal) {
                            tv_goal.setText("Đã hoàn thành mục tiêu!");
                            checkGoal=true;
                        }
                    }
                }

            }

            @Override
            public void onAccuracyChanged (Sensor sensor,int i){

            }
        }

                ;
        sensorManager.registerListener(stepDetector,sensor,sensorManager.SENSOR_DELAY_NORMAL);

    }


}