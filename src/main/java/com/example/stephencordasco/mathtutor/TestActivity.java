package com.example.stephencordasco.mathtutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TestActivity extends AppCompatActivity {

    // Global variables

    // Max test questions
    int MAX_TEST_QUESTIONS = 25;

    // Variable for the timer
    CountDownTimer t_timer;

    // Variable for the numbers
    int t_firstNum = 0;
    int t_secondNum = 0;
    int t_answer = 0;
    int t_userAnswer = 0;
    int chooseOperation;

    // Counter for the problems generated
    int t_counter = 0;
    int t_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Prevent the keypad from appearing after minimize during quiz run
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Reference to the TVs
        final TextView testTimerTV = (TextView) findViewById(R.id.t_timerTextView);
        final TextView testEqualsTV = (TextView) findViewById(R.id.test_equals_bar);
        final TextView testTitleTV = (TextView) findViewById(R.id.testTitleTV);

        // Reference to the Buttons
        Button testHomeButton = (Button) findViewById(R.id.homeBtn3);
        final Button testBeginButton = (Button) findViewById(R.id.begin_test_btn);
        final Button testSubmitButton = (Button) findViewById(R.id.test_submit_answer);
        final Button testShowScoreButton = (Button) findViewById(R.id.test_show_score);

        // Reference to the ET
        final EditText testET = (EditText) findViewById(R.id.test_user_answer);

        // Set some of the components to be invisible
        testEqualsTV.setVisibility(View.INVISIBLE);
        testSubmitButton.setVisibility(View.INVISIBLE);
        testShowScoreButton.setVisibility(View.INVISIBLE);
        testET.setVisibility(View.INVISIBLE);

        // Display the title
        testTitleTV.setText(R.string.test_first_prompt);
        testTimerTV.setText(R.string.test_second_prompt);

        // Set the listener for the home button
        testHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(TestActivity.this, MainActivity.class);
                TestActivity.this.startActivity(menuIntent);
            }
        });

        // Set the listener for the begin button
        testBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set components invisible
                testBeginButton.setVisibility(View.INVISIBLE);

                // Set components visible
                testEqualsTV.setVisibility(View.VISIBLE);
                testSubmitButton.setVisibility(View.VISIBLE);
                testET.setVisibility(View.VISIBLE);

                // Display a different message on the screen
                testTitleTV.setText(R.string.quiz_title4);

                t_timer = new CountDownTimer(600000, 1000) {

                    public void onTick(long ms) {
                        @SuppressLint("DefaultLocale") String text = String.format("Time limit: %02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(ms) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                                TimeUnit.MILLISECONDS.toSeconds(ms) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));

                        testTimerTV.setText(text);
                    }

                    // Set the text of the timer TextView
                    public void onFinish() {
                        // Display a new prompt
                        testTitleTV.setText(R.string.quiz_title3);
                        testTimerTV.setText(R.string.test_third_prompt);

                        // Set the score button to be visible
                        testShowScoreButton.setVisibility(View.VISIBLE);

                        // Disable some of the components
                        testSubmitButton.setEnabled(false);
                        testET.setEnabled(false);
                    }
                }.start();

                // Generate the first equation
                generate(v);
            }
        }); // End of the Begin button listener

        // Set the listener for the submit button
        testSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the user did not enter anything into the ET field
                if (testET.getText().toString().equals("")) {
                    // Set the user answer to -1
                    t_userAnswer = -1;
                }
                else {
                    // Perform the appropriate logic based off the operation
                    switch(chooseOperation) {
                        // Addition
                        case 1: t_answer = t_firstNum + t_secondNum;
                            break;
                        // Subtraction
                        case 2: t_answer = t_firstNum - t_secondNum;
                            break;
                        // Multiplication
                        case 3: t_answer = t_firstNum * t_secondNum;
                            break;
                        default:
                            break;
                    }

                    // Get the user's answer
                    t_userAnswer = Integer.parseInt(testET.getText().toString());
                }

                // Check the user's answer
                if (t_userAnswer == t_answer) {
                    // Increment the score if the user answered correctly
                    t_score++;
                }

                // Check if the user has completed the test (answered 25 questions)
                if (t_counter == MAX_TEST_QUESTIONS) {
                    // Stop the timer
                    t_timer.cancel();

                    // Display a new prompt
                    testTitleTV.setText(R.string.quiz_title3);
                    testTimerTV.setText(R.string.test_third_prompt);

                    // Set the score button to be visible
                    testShowScoreButton.setVisibility(View.VISIBLE);

                    // Disable some of the components
                    testSubmitButton.setEnabled(false);
                    testET.setEnabled(false);
                }
                else {
                    // Generate a new equation
                    generate(v);
                    // Clear the ET field
                    testET.getText().clear();
                }
            }
        });

        // Set the listener for the score button
        testShowScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convert the score and total questions to String variables
                String score = String.valueOf(t_score);
                String total = String.valueOf(MAX_TEST_QUESTIONS);
                // Display the score
                testTimerTV.setText(getString(R.string.score_title, score, total));
            }
        });
    }

    public void generate(View view) {
        // Create a Random class object
        Random rand = new Random();

        // Reference to the TVs
        TextView t_firstNumTV = (TextView) findViewById(R.id.test_first_num);
        TextView t_secondNumTV = (TextView) findViewById(R.id.test_second_num);
        TextView t_operationTV = (TextView) findViewById(R.id.test_operation);

        // Determine the operation
        chooseOperation = rand.nextInt(3) + 1;

        // Generate the numbers and perform the appropriate logic for each case
        switch(chooseOperation) {
            // Addition was chosen
            case 1: t_operationTV.setText(R.string.add_operator);
                // Generate the numbers
                t_firstNum = rand.nextInt(100) + 1;
                t_secondNum = rand.nextInt(100) + 1;
                break;
            // Subtraction was chosen
            case 2: t_operationTV.setText(R.string.sub_operator);
                // Generate the numbers
                t_firstNum = rand.nextInt(100) + 1;
                t_secondNum = rand.nextInt(t_firstNum) + 1;
                break;
            // Multiplication was chosen
            case 3: t_operationTV.setText(R.string.multiplication_operator);
                // Generate the numbers
                t_firstNum = rand.nextInt(12) + 1;
                t_secondNum = rand.nextInt(12) + 1;
                break;
            default:
                break;
        }

        // Display the contents to the screen
        //  Convert the first and second number (ints) to a String and set it as the text
        String firstNumConversion = String.valueOf(t_firstNum);
        String secondNumConversion = String.valueOf(t_secondNum);
        // Set the text of the TVs
        t_firstNumTV.setText(firstNumConversion);
        t_secondNumTV.setText(secondNumConversion);

        // Increment the counter each time the method is called
        t_counter++;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
