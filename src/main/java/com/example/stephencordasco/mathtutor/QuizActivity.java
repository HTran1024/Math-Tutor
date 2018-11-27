package com.example.stephencordasco.mathtutor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // Reference to the timer
    CountDownTimer q_timer;

    // Reference to the user's selection of math type and difficulty (Strings)
    String q_math_selection;
    String q_diff_selection;
    String q_time_selection;
    String q_numQ_selection;

    // Global integer variables used for quiz equations
    int[] q_num1 = new int[15];
    int[] q_num2 = new int[15];

    // Global integer variables to store the answer and user answer
    int[] q_answer = new int[15];
    int[] q_user_answer = new int[15];
    int arraySize;

    // Global counters to help display a specific set of questions and store the score
    int q_counter = 0;
    int q_score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the data from the setUp activity
        Bundle q_extras1 = getIntent().getExtras();
        Bundle q_extras2 = getIntent().getExtras();
        Bundle q_extras3 = getIntent().getExtras();
        Bundle q_extras4 = getIntent().getExtras();

        // Store the data in the String variables
        q_math_selection = q_extras1.getString("SpinnerValue1");
        q_diff_selection = q_extras2.getString("SpinnerValue2");
        q_time_selection = q_extras3.getString("SpinnerValue3");
        q_numQ_selection = q_extras4.getString("SpinnerValue4");

        // Reference to the home button
        Button home = (Button) findViewById(R.id.homeBtn2);
        // Reference to the begin button
        final Button begin_quiz_btn = (Button) findViewById(R.id.begin_quiz_btn);
        // Reference to the score button
        final Button q_score_btn = (Button) findViewById(R.id.quiz_show_score);
        // Reference to the submit button
        final Button q_submit_btn = (Button) findViewById(R.id.quiz_submit_answer);

        // Reference to timerTextView
        final TextView timer = (TextView) findViewById(R.id.timerTextView);
        // Reference to the equals sign
        final TextView q_equals_tv = (TextView) findViewById(R.id.quiz_equals_bar);
        // Reference to the title TV
        final TextView q_title_tv = (TextView) findViewById(R.id.quizTitleTV);

        // Reference to the EditText
        final EditText q_edit_text = (EditText) findViewById(R.id.quiz_user_answer);

        // Prevent the keypad from appearing after minimize during quiz run
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Set the components invisible
        q_score_btn.setVisibility(View.INVISIBLE);
        q_submit_btn.setVisibility(View.INVISIBLE);
        q_equals_tv.setVisibility(View.INVISIBLE);
        q_edit_text.setVisibility(View.INVISIBLE);

        // Score button cannot be clicked
        q_score_btn.setEnabled(false);
        // Submit button cannot be clicked
        q_submit_btn.setEnabled(false);

        // Display the title
        q_title_tv.setText(R.string.quiz_title1);
        timer.setText(R.string.quiz_title2);

        // Get the array size
        switch(q_numQ_selection) {
            case "5 (Default)":
                arraySize = 5;
                break;
            case "5 (Defecto)":
                arraySize = 5;
                break;
            case "10 (Skills)":
                arraySize = 10;
                break;
            case "10 (Habilidades)":
                arraySize = 10;
                break;
            case "15 (Champion)":
                arraySize = 15;
                break;
            case "15 (Campeón)":
                arraySize = 15;
                break;
            default:
                break;
        }

        // Initialize the answer_array and user_answer_array first
        for (int i = 0; i < arraySize; i++) {
            q_user_answer[i] = -1;
        }

        // Set the onClickListener for the home button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        // Set the onClickListener
        begin_quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the button invisible
                begin_quiz_btn.setVisibility(View.INVISIBLE);

                // Set the other components visible
                q_submit_btn.setVisibility(View.VISIBLE);
                q_equals_tv.setVisibility(View.VISIBLE);
                q_edit_text.setVisibility(View.VISIBLE);

                // Display the instructions
                q_title_tv.setText(R.string.quiz_title4);

                // Initialize the timer based off the user's selection
                // Defines a variable to store the time
                int q_time = 0;

                // Determine the user's selection
                switch (q_time_selection) {
                    case "Default (2:00)":
                        q_time = 120000;
                        break;
                    case "Defecto (2:00)":
                        q_time = 120000;
                        break;
                    case "Speedy (1:30)":
                        q_time = 90000;
                        break;
                    case "Rápido (1:30)":
                        q_time = 90000;
                        break;
                    case "Lightning (0:30)":
                        q_time = 30000;
                        break;
                    case "Relámpago (0:30)":
                        q_time = 30000;
                        break;
                    default:
                        break;
                }

                q_timer = new CountDownTimer(q_time, 1000) {

                    public void onTick(long ms) {
                        @SuppressLint("DefaultLocale") String text = String.format("Time limit: %02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(ms) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                                TimeUnit.MILLISECONDS.toSeconds(ms) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));

                        timer.setText(text);
                    }

                    // Set the text of the timer TextView
                    public void onFinish() {
                        // Display a new prompt
                        q_title_tv.setText(R.string.quiz_title3);
                        timer.setText(R.string.time_up);

                        // Set the Begin button and Score button to be visible
                        q_score_btn.setVisibility(View.VISIBLE);

                        // Enable the Score button
                        q_score_btn.setEnabled(true);

                        // Disable the Submit button
                        q_submit_btn.setEnabled(false);

                        // Disable the ET field
                        q_edit_text.setEnabled(false);
                    }
                }.start();

                // Set the begin button to invisible to prevent overlapping timers
                begin_quiz_btn.setVisibility(View.INVISIBLE);
                q_score_btn.setVisibility(View.INVISIBLE);

                // Set the Submit button to be clickable
                q_submit_btn.setEnabled(true);
                q_edit_text.setEnabled(true);

                // Generate the first problem
                generate(view);
            }
        });

        // Set the onClickListener for the submit button
        q_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reference to the title TV
                TextView q_title_tv = (TextView) findViewById(R.id.quizTitleTV);

                // ** ERROR CHECK **
                if (q_edit_text.getText().toString().equals("")) {
                    // Set user answer to -1
                    q_user_answer[q_counter - 1] = -1;
                }
                else
                {
                    // Get the answer using the appropriate logic
                    switch(q_math_selection) {
                        case "Addition (+)":
                            // Get the answer in the case of Addition
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] + q_num2[q_counter - 1];
                            break;
                        case "Adición (+)":
                            // Get the answer in the case of Addition
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] + q_num2[q_counter - 1];
                            break;
                        case "Subtraction (-)":
                            // Get the answer in the case of Subtraction
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] - q_num2[q_counter - 1];
                            break;
                        case "Sustracción (-)":
                            // Get the answer in the case of Subtraction
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] - q_num2[q_counter - 1];
                            break;
                        case "Multiplication (x)":
                            // Get the answer in the case of Multiplication
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] * q_num2[q_counter - 1];
                            break;
                        case "Multiplicación (x)":
                            // Get the answer in the case of Multiplication
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] * q_num2[q_counter - 1];
                            break;
                        case "Division (÷)":
                            // Get the quotient
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] / q_num2[q_counter - 1];
                            break;
                        case "División (÷)":
                            q_answer[q_counter - 1] = q_num1[q_counter - 1] / q_num2[q_counter - 1];
                            break;
                        default:
                            break;
                    }

                    // Store the user's answer for the questions they are able to answer
                    q_user_answer[q_counter - 1] = Integer.parseInt(q_edit_text.getText().toString());

                    // Check the user's answer
                    if (q_user_answer[q_counter - 1] == q_answer[q_counter - 1]) {
                        // If the user answered correctly, increase the score count
                        q_score++;
                    }
                }

                // Check to see if the user finished the quiz
                if (q_counter == arraySize) {
                    // Stop the timer
                    q_timer.cancel();

                    // Display a new prompt
                    q_title_tv.setText(R.string.quiz_title3);

                    // Display that the quiz has ended
                    timer.setText(R.string.time_up);

                    // Set the Begin button and Score button to be visible
                    q_score_btn.setVisibility(View.VISIBLE);

                    // Enable the Score button
                    q_score_btn.setEnabled(true);

                    // Disable the Submit button
                    q_submit_btn.setEnabled(false);

                    // Disable the ET field
                    q_edit_text.setEnabled(false);
                }
                else {
                    generate(view);
                    q_edit_text.getText().clear();
                }
            }
        });

        // Set the onClickListener for the score button
        q_score_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Declare an intent to change to and send data to the ResultActivity
                Intent goToScore = new Intent(QuizActivity.this, ResultActivity.class);
                goToScore.putExtra("UserScore", q_score);
                goToScore.putExtra("OutOf", arraySize);
                goToScore.putExtra("NumSet1", q_num1);
                goToScore.putExtra("NumSet2", q_num2);
                goToScore.putExtra("Answer", q_answer);
                goToScore.putExtra("UserAnswer", q_user_answer);
                goToScore.putExtra("OperationType", q_math_selection);
                startActivity(goToScore);
            }
        });
    }

    public void generate(View view) {
        // Create a Random class object
        Random rand = new Random();

        // Reference to a TextView
        TextView q_operator = (TextView) findViewById(R.id.quiz_operation);

        // Begin our switch statement to use the appropriate logic
        switch(q_math_selection) {
            // Check whether the user selected Addition...
            case "Addition (+)":
                // Set the TextView
                q_operator.setText(R.string.add_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {

                    // ...and generate the appropriate numbers...
                    case "Beginner (Default : 1 : 10)":
                        // Store a random number in num1 and num2
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(10);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        // Store a random number in num1 and num2
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(10);
                        break;

                    case "Intermediate (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(51);
                        break;
                    case "Intermedio (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(51);
                        break;

                    case "Advanced (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(101);
                        break;
                    case "Avanzado (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(101);
                        break;

                    default:
                        // Error check
                        break;
                }
                // Break out of the nested switch statement
                break;
            case "Adición (+)":
                // Set the TextView
                q_operator.setText(R.string.add_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {

                    // ...and generate the appropriate numbers...
                    case "Beginner (Default : 1 : 10)":
                        // Store a random number in num1 and num2
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(10);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        // Store a random number in num1 and num2
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(10);
                        break;

                    case "Intermediate (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(51);
                        break;
                    case "Intermedio (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(51);
                        break;

                    case "Advanced (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(101);
                        break;
                    case "Avanzado (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(101);
                        break;

                    default:
                        // Error check
                        break;
                }
                // Break out of the nested switch statement
                break;

            // ...or check if the user selected subtraction
            case "Subtraction (-)":
                // Set the TextView
                q_operator.setText(R.string.sub_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {

                    // ...generating the appropriate numbers
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    case "Intermediate (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Intermedio (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    case "Advanced (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Avanzado (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    default:
                        // Error check
                        break;
                }
                // Break out of the nested switch statement
                break;
            case "Sustracción (-)":
                // Set the TextView
                q_operator.setText(R.string.sub_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {

                    // ...generating the appropriate numbers
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(9) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    case "Intermediate (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Intermedio (1 : 50)":
                        q_num1[q_counter] = rand.nextInt(50) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    case "Advanced (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;
                    case "Avanzado (1 : 100)":
                        q_num1[q_counter] = rand.nextInt(100) + 1;
                        q_num2[q_counter] = rand.nextInt(q_num1[q_counter]);
                        break;

                    default:
                        // Error check
                        break;
                }
                // Break out of the nested switch statement
                break;

            // ...or check if the user selected multiplication
            case "Multiplication (x)":
                // Set the TextView
                q_operator.setText(R.string.multiplication_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(6) + 1;
                        q_num2[q_counter] = rand.nextInt(7);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(6) + 1;
                        q_num2[q_counter] = rand.nextInt(7);
                        break;
                    default:
                        q_num1[q_counter] = rand.nextInt(12) + 1;
                        q_num2[q_counter] = rand.nextInt(12) + 1;
                        break;
                }
                break;
            case "Multiplicación (x)":
                // Set the TextView
                q_operator.setText(R.string.multiplication_operator);

                // ...and check the difficulty...
                switch (q_diff_selection) {
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(6) + 1;
                        q_num2[q_counter] = rand.nextInt(7);
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(6) + 1;
                        q_num2[q_counter] = rand.nextInt(7);
                        break;
                    default:
                        q_num1[q_counter] = rand.nextInt(12) + 1;
                        q_num2[q_counter] = rand.nextInt(12) + 1;
                        break;
                }
                break;

            case "Division (÷)":
                // Set the TV for operator
                q_operator.setText(R.string.division_operator);

                // ...and check the difficulty...
                switch(q_diff_selection) {
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(36) + 1;
                        q_num2[q_counter] = rand.nextInt(6) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(6) + 1;
                        }
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(36) + 1;
                        q_num2[q_counter] = rand.nextInt(6) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(6) + 1;
                        }
                        break;
                    default:
                        q_num1[q_counter] = rand.nextInt(144) + 1;
                        q_num2[q_counter] = rand.nextInt(12) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(12) + 1;
                        }
                        break;
                }
                break;

            case "División (÷)":
                // Set the TV for operator
                q_operator.setText(R.string.division_operator);

                // ...and check the difficulty...
                switch(q_diff_selection) {
                    case "Beginner (Default : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(36) + 1;
                        q_num2[q_counter] = rand.nextInt(6) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(6) + 1;
                        }
                        break;
                    case "Principiante (Defecto : 1 : 10)":
                        q_num1[q_counter] = rand.nextInt(36) + 1;
                        q_num2[q_counter] = rand.nextInt(6) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(6) + 1;
                        }
                        break;
                    default:
                        q_num1[q_counter] = rand.nextInt(144) + 1;
                        q_num2[q_counter] = rand.nextInt(12) + 1;
                        while (q_num1[q_counter] % q_num2[q_counter] != 0) {
                            q_num2[q_counter] = rand.nextInt(12) + 1;
                        }
                        break;
                }
                break;

            default:
                break;
        }

        // For the screen display (equation):

        // Reference to the first number
        TextView q_number1 = (TextView) findViewById(R.id.quiz_first_num);
        String q_numToStr1 = String.valueOf(q_num1[q_counter]);
        q_number1.setText(q_numToStr1);

        // Reference to the second number
        TextView q_number2 = (TextView) findViewById(R.id.quiz_second_num);
        String q_numToStr2 = String.valueOf(q_num2[q_counter]);
        q_number2.setText(q_numToStr2);

        // Increment the equation counter
        q_counter++;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}