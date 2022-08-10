package com.example.stepscounter_obi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText edtGoal;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtGoal = findViewById(R.id.editTextTextPersonName);
        btnSubmit = findViewById(R.id.button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                try {
                    i.putExtra("goal", Integer.parseInt(edtGoal.getText().toString()));
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}