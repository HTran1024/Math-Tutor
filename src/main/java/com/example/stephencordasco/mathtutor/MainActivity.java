package com.example.stephencordasco.mathtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // References to the practice and test buttons
        Button prac_Btn = (Button) findViewById(R.id.practiceBtn);
        Button quiz_Btn = (Button) findViewById(R.id.main_quiz_btn);

        Button test_Btn = (Button) findViewById(R.id.test_button);
        Button endurance_Btn = (Button) findViewById(R.id.endurance_button);

        Button login_Btn = (Button) findViewById(R.id.main_login_button);

        // Set the onClickListener for the practice button
        prac_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPrac = new Intent(MainActivity.this, PracticeSetUpActivity.class);
                intentPrac.putExtra("Selected", 0);
                startActivity(intentPrac);
            }
        });

        // Set the onClickListener for the quiz button
        quiz_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentQuiz = new Intent(MainActivity.this, PracticeSetUpActivity.class);
                intentQuiz.putExtra("Selected", 1);
                startActivity(intentQuiz);
            }
        });

        // Set the onClickListener for the test button
        test_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTest = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intentTest);
            }
        });

        // Set the listener for the endurance button
        endurance_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEndurance = new Intent(MainActivity.this, EnduranceActivity.class);
                startActivity(intentEndurance);
            }
        });

        // Set the listener for the login button
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
