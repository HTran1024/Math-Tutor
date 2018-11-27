package com.example.stephencordasco.mathtutor;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PracticeSetUpActivity extends AppCompatActivity {
    // References to the spinners
    Spinner mathSpinner;
    Spinner diffSpinner;
    Spinner timeSpinner;
    Spinner numQSpinner;

    // References to the adapters
    ArrayAdapter<CharSequence> mathAdapter;
    ArrayAdapter<CharSequence> diffAdapter;
    ArrayAdapter<CharSequence> timeAdapter;
    ArrayAdapter<CharSequence> numQAdapter;

    // Store the data passed from the main menu
    int userSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_set_up);

        // Get the integer passed from the main menu to determine what activity to set up
        Bundle extras = getIntent().getExtras();

        // Store the data passed in the global variables
        userSelected = extras.getInt("Selected");

        // Reference to the TVs
        TextView time_limit_TV = (TextView) findViewById(R.id.time_limit);
        TextView num_questions_TV = (TextView) findViewById(R.id.number_of_questions);

        // Initialize Spinner objects
        mathSpinner = (Spinner) findViewById(R.id.math_type_spinner);
        diffSpinner = (Spinner) findViewById(R.id.difficulty_spinner);
        timeSpinner = (Spinner) findViewById(R.id.time_limit_spinner);
        numQSpinner = (Spinner) findViewById(R.id.num_of_questions_spinner);

        // Initialize the adapters
        mathAdapter = ArrayAdapter.createFromResource(this,
                R.array.math_types, android.R.layout.simple_spinner_item);
        diffAdapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_types, android.R.layout.simple_spinner_item);
        timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_limits, android.R.layout.simple_spinner_item);
        numQAdapter = ArrayAdapter.createFromResource(this,
                R.array.num_questions, android.R.layout.simple_spinner_item);


        // Determine the spinners to be displayed for the following activities

        // For the Practice activity
        if (userSelected == 0)
        {
            // Set the drop down view for the adapter and add the adapter to the spinner
            mathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mathSpinner.setAdapter(mathAdapter);

            diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            diffSpinner.setAdapter(diffAdapter);

            // Set the other spinners invisible
            time_limit_TV.setVisibility(View.INVISIBLE);
            num_questions_TV.setVisibility(View.INVISIBLE);
            timeSpinner.setVisibility(View.INVISIBLE);
            numQSpinner.setVisibility(View.INVISIBLE);
        }
        // For the Quiz activity
        else if (userSelected == 1) {
            // Set the drop down view for the adapter and add the adapter to the spinner
            mathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mathSpinner.setAdapter(mathAdapter);

            diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            diffSpinner.setAdapter(diffAdapter);

            timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            timeSpinner.setAdapter(timeAdapter);

            numQAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            numQSpinner.setAdapter(numQAdapter);
        }

        // Define a reference to the begin practice button
        final Button beginPractice = (Button) findViewById(R.id.begin_practice_btn);

        beginPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define an intent
                Intent beginIntent;

                // The user is going to the Practice Activity
                if (userSelected == 0) {
                    // Intent to begin the practice activity
                    beginIntent = new Intent(PracticeSetUpActivity.this, Practice.class);

                    // Send the selected spinner data to the practice activity
                    beginIntent.putExtra("SpinnerValue1", mathSpinner.getSelectedItem().toString());
                    beginIntent.putExtra("SpinnerValue2", diffSpinner.getSelectedItem().toString());

                    // Start the activity
                    startActivity(beginIntent);
                }
                // The user is going to the Quiz Activity
                else if (userSelected == 1) {
                    // Intent to begin the quiz activity
                    beginIntent = new Intent(PracticeSetUpActivity.this, QuizActivity.class);

                    // Send the selected spinner data to the quiz activity
                    beginIntent.putExtra("SpinnerValue1", mathSpinner.getSelectedItem().toString());
                    beginIntent.putExtra("SpinnerValue2", diffSpinner.getSelectedItem().toString());
                    beginIntent.putExtra("SpinnerValue3", timeSpinner.getSelectedItem().toString());
                    beginIntent.putExtra("SpinnerValue4", numQSpinner.getSelectedItem().toString());

                    // Start the activity
                    startActivity(beginIntent);
                }
            }
        });
    }
}
