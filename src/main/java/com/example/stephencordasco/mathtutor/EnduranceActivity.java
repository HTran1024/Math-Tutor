package com.example.stephencordasco.mathtutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EnduranceActivity extends AppCompatActivity {
    // Global variables

    // Define's the max questions (used for the time activity)
    int MAX_ENDURANCE_QUESTIONS = 25;
    // CountDownTimer object
    CountDownTimer e_timer;
    // For the other timer
    Chronometer stopWatch;

    // For Chronometer
    long startTime;
    long countUp;

    // For the activity
    int whichActivity = -1;

    // Used to display the equation, calculate a solution, check user's answer, and count
    //   questions generated as well as user score
    int chooseOperation;
    int eFirstNum;
    int eSecondNum;
    int eCounter = 0;
    int e_userAnswer;
    int e_answer;
    int e_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endurance);

        // Reference to TVs
        TextView e_first_num_TV = (TextView) findViewById(R.id.endurance_first_num);
        TextView e_second_num_TV = (TextView) findViewById(R.id.endurance_second_num);
        TextView e_operation_TV = (TextView) findViewById(R.id.endurance_operation);
        TextView e_equals_TV = (TextView) findViewById(R.id.endurance_equals_bar);

        // Reference to Buttons
        Button e_homeButton = (Button) findViewById(R.id.endurance_home_button);
        Button e_submitButton = (Button) findViewById(R.id.endurance_submit_button);
        Button e_endButton = (Button) findViewById(R.id.endurance_end_button);

        // Reference to the ET field
        EditText e_userAnswerET = (EditText) findViewById(R.id.endurance_user_answer_ET);

        // Set Submit, ET field, and TVs to be invisible
        e_first_num_TV.setVisibility(View.INVISIBLE);
        e_second_num_TV.setVisibility(View.INVISIBLE);
        e_operation_TV.setVisibility(View.INVISIBLE);
        e_equals_TV.setVisibility(View.INVISIBLE);
        e_submitButton.setVisibility(View.INVISIBLE);
        e_endButton.setVisibility(View.INVISIBLE);
        e_userAnswerET.setVisibility(View.INVISIBLE);

        // Set the listener for the home button
        e_homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enduranceToMenu = new Intent(EnduranceActivity.this, MainActivity.class);
                EnduranceActivity.this.startActivity(enduranceToMenu);
            }
        });

        // Set the listener for the end button
        e_endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    // Method to run the Endurance game (going for the best time)
    public void goForTime(View v) {
        // Reference to TVs
        TextView e_first_num_TV = (TextView) findViewById(R.id.endurance_first_num);
        TextView e_second_num_TV = (TextView) findViewById(R.id.endurance_second_num);
        TextView e_operation_TV = (TextView) findViewById(R.id.endurance_operation);
        TextView e_equals_TV = (TextView) findViewById(R.id.endurance_equals_bar);
        TextView e_time_prompt_TV = (TextView) findViewById(R.id.endurance_time_prompt_TV);
        TextView e_score_prompt_TV = (TextView) findViewById(R.id.endurance_score_prompt_TV);
        final TextView e_timer_TV = (TextView) findViewById(R.id.endurance_timer_TV);

        // Reference to Buttons
        Button e_submitButton = (Button) findViewById(R.id.endurance_submit_button);
        Button e_goForTime = (Button) findViewById(R.id.endurance_time_button);
        Button e_goForScore = (Button) findViewById(R.id.endurance_score_button);
        Button e_endButton = (Button) findViewById(R.id.endurance_end_button);

        // Reference to the ET field
        EditText e_userAnswerET = (EditText) findViewById(R.id.endurance_user_answer_ET);

        // Set the components to be Visible
        e_first_num_TV.setVisibility(View.VISIBLE);
        e_second_num_TV.setVisibility(View.VISIBLE);
        e_operation_TV.setVisibility(View.VISIBLE);
        e_equals_TV.setVisibility(View.VISIBLE);
        e_submitButton.setVisibility(View.VISIBLE);
        e_endButton.setVisibility(View.VISIBLE);
        e_userAnswerET.setVisibility(View.VISIBLE);

        // Set the goForTime and goForScore buttons to be invisible
        e_time_prompt_TV.setVisibility(View.INVISIBLE);
        e_score_prompt_TV.setVisibility(View.INVISIBLE);
        e_goForTime.setVisibility(View.INVISIBLE);
        e_goForScore.setVisibility(View.INVISIBLE);

        // Set the activity
        whichActivity = 0;

        // Start the timer
        stopWatch = (Chronometer) findViewById(R.id.chrono);
        startTime = SystemClock.elapsedRealtime();

        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                countUp = (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000;
                @SuppressLint("DefaultLocale") String asText =
                        String.format("Time limit: %02d:%02d", (countUp/60), (countUp % 60));
                e_timer_TV.setText(asText);
            }
        });
        stopWatch.start();

        // Generate the first equation
        generate();
    }

    // Method to run the Endurance game (going for the best score)
    public void goForScore(View v) {
        // Reference to TVs
        TextView e_first_num_TV = (TextView) findViewById(R.id.endurance_first_num);
        TextView e_second_num_TV = (TextView) findViewById(R.id.endurance_second_num);
        TextView e_operation_TV = (TextView) findViewById(R.id.endurance_operation);
        TextView e_equals_TV = (TextView) findViewById(R.id.endurance_equals_bar);
        TextView e_time_prompt_TV = (TextView) findViewById(R.id.endurance_time_prompt_TV);
        TextView e_score_prompt_TV = (TextView) findViewById(R.id.endurance_score_prompt_TV);

        // Reference to Buttons
        Button e_submitButton = (Button) findViewById(R.id.endurance_submit_button);
        Button e_goForTime = (Button) findViewById(R.id.endurance_time_button);
        Button e_goForScore = (Button) findViewById(R.id.endurance_score_button);
        Button e_endButton = (Button) findViewById(R.id.endurance_end_button);

        // Reference to the ET field
        EditText e_userAnswerET = (EditText) findViewById(R.id.endurance_user_answer_ET);

        // Set the components to be Visible
        e_first_num_TV.setVisibility(View.VISIBLE);
        e_second_num_TV.setVisibility(View.VISIBLE);
        e_operation_TV.setVisibility(View.VISIBLE);
        e_equals_TV.setVisibility(View.VISIBLE);
        e_submitButton.setVisibility(View.VISIBLE);
        e_endButton.setVisibility(View.VISIBLE);
        e_userAnswerET.setVisibility(View.VISIBLE);

        // Set the goForTime and goForScore buttons to be invisible
        e_time_prompt_TV.setVisibility(View.INVISIBLE);
        e_score_prompt_TV.setVisibility(View.INVISIBLE);
        e_goForTime.setVisibility(View.INVISIBLE);
        e_goForScore.setVisibility(View.INVISIBLE);

        // Set the activity
        whichActivity = 1;

        // Start the CountDownTimer (using e_timer)
        startTimer();

        // Generate the first equation
        generate();
    }

    public void submit(View v) {
        // Reference to the TVs
        TextView e_score_TV = (TextView) findViewById(R.id.endurance_score_TV);

        // Reference to the Submit button
        Button e_submit = (Button) findViewById(R.id.endurance_submit_button);

        // Reference to the ET field
        EditText e_userAnswerET = (EditText) findViewById(R.id.endurance_user_answer_ET);

        // Get the answer and user answer and check

        // If the user did not enter anything into the ET field
        if (e_userAnswerET.getText().toString().equals("")) {
            // Set the user answer to -1
            e_userAnswer = -1;
        }
        else {
            // Perform the appropriate logic based off the operation
            switch(chooseOperation) {
                // Addition
                case 1: e_answer = eFirstNum + eSecondNum;
                    break;
                // Subtraction
                case 2: e_answer = eFirstNum - eSecondNum;
                    break;
                // Multiplication
                case 3: e_answer = eFirstNum * eSecondNum;
                    break;
                default:
                    break;
            }

            // Get the user's answer
            e_userAnswer = Integer.parseInt(e_userAnswerET.getText().toString());
        }

        // Check the user's answer
        if (e_userAnswer == e_answer) {
            // Increment the score if the user answered correctly
            e_score++;
        }

        // Check if the user has completed the 25 questions
        if (whichActivity == 0) {
            if (eCounter == MAX_ENDURANCE_QUESTIONS) {
                stopWatch.stop();

                // Convert the score and total questions to String variables
                String score = String.valueOf(e_score);
                String total = String.valueOf(MAX_ENDURANCE_QUESTIONS);
                // Display the score
                e_score_TV.setText(getString(R.string.score_title, score, total));

                // Disable some of the components
                e_submit.setEnabled(false);
                e_userAnswerET.setEnabled(false);
            }
            else {
                // Generate a new equation after storing the user answer
                generate();
                // Clear the ET field
                e_userAnswerET.getText().clear();
            }
        }
        // Or the timer has not stopped so keep generating new problems
        else if (whichActivity == 1) {
            // Generate a new equation after storing the user answer
            generate();
            // Clear the ET field
            e_userAnswerET.getText().clear();
        }
    }

    public void startTimer() {
        // Reference to TVs
        final TextView e_timer_TV = (TextView) findViewById(R.id.endurance_timer_TV);
        final TextView e_score_TV = (TextView) findViewById(R.id.endurance_score_TV);

        // Reference to submit button
        final Button e_submit = (Button) findViewById(R.id.endurance_submit_button);

        // Reference to the ET
        final EditText e_userAnswer_ET = (EditText) findViewById(R.id.endurance_user_answer_ET);

        e_timer = new CountDownTimer(120000, 1000) {

            public void onTick(long ms) {
                @SuppressLint("DefaultLocale") String text = String.format("Time limit: %02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(ms) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                        TimeUnit.MILLISECONDS.toSeconds(ms) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));

                e_timer_TV.setText(text);
            }

            // Set the text of the timer TextView
            public void onFinish() {
                // Convert the score and total questions to String variables
                String score = String.valueOf(e_score);
                String total = String.valueOf(eCounter);
                // Display the score
                e_score_TV.setText(getString(R.string.score_title, score, total));

                // Disable some of the components
                e_submit.setEnabled(false);
                e_userAnswer_ET.setEnabled(false);
            }
        }.start();
    }

    public void generate() {
        // Create a Random class object
        Random rand = new Random();

        // Reference to TVs
        TextView e_first_num_TV = (TextView) findViewById(R.id.endurance_first_num);
        TextView e_second_num_TV = (TextView) findViewById(R.id.endurance_second_num);
        TextView e_operation_TV = (TextView) findViewById(R.id.endurance_operation);

        // Determine the operation
        chooseOperation = rand.nextInt(3) + 1;

        // Generate the numbers and perform the appropriate logic for each case
        switch(chooseOperation) {
            // Addition was chosen
            case 1: e_operation_TV.setText(R.string.add_operator);
                // Generate the numbers
                eFirstNum = rand.nextInt(50) + 1;
                eSecondNum = rand.nextInt(50) + 1;
                break;
            // Subtraction was chosen
            case 2: e_operation_TV.setText(R.string.sub_operator);
                // Generate the numbers
                eFirstNum = rand.nextInt(50) + 1;
                eSecondNum = rand.nextInt(eFirstNum) + 1;
                break;
            // Multiplication was chosen
            case 3: e_operation_TV.setText(R.string.multiplication_operator);
                // Generate the numbers
                eFirstNum = rand.nextInt(12) + 1;
                eSecondNum = rand.nextInt(12) + 1;
                break;
            default:
                break;
        }

        // Display the contents to the screen
        //  Convert the first and second number (ints) to a String and set it as the text
        String firstNumConversion = String.valueOf(eFirstNum);
        String secondNumConversion = String.valueOf(eSecondNum);
        // Set the text of the TVs
        e_first_num_TV.setText(firstNumConversion);
        e_second_num_TV.setText(secondNumConversion);

        // Increment the counter each time the method is called
        eCounter++;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
