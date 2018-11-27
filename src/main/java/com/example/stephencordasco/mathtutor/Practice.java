package com.example.stephencordasco.mathtutor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Practice extends AppCompatActivity {

    // References to Strings storing data passed from previous activity
    String math_selection;
    String diff_selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // Reference to the title
        TextView tv_title = (TextView) findViewById(R.id.practiceTitle);
        // Reference to the first number (TextView)
        TextView firstNumText = (TextView) findViewById(R.id.firstDigitTextView);
        // Reference to the second number (TextView)
        TextView secondNumText = (TextView) findViewById(R.id.secondDigitTextView);
        // Reference to the operator (TextView)
        TextView operator = (TextView) findViewById(R.id.mathTypeTextView);
        // Reference to the equals sign (TV)
        TextView tv_equals = (TextView) findViewById(R.id.equalsTextView);
        // Reference to the Check button
        Button chk_btn = (Button) findViewById(R.id.checkBtn);
        // Reference to the Cont/TryAgain button
        Button cont_try_btn = (Button) findViewById(R.id.continue_tryAgain_btn);
        // Reference to the EditText field
        final EditText checkText = (EditText) findViewById(R.id.editText);

        // Receive the string names that are passed from the spinners in PracticeSetUpActivity
        Bundle extras1 = getIntent().getExtras();
        Bundle extras2 = getIntent().getExtras();

        // Store the data in the String
        math_selection = extras1.getString("SpinnerValue1");
        diff_selection = extras2.getString("SpinnerValue2");

        // Set the listener and Intent for the Home button
        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intentHome = new Intent(Practice.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        // Set the listener and Intent for the Setup button
        Button setup_btn = (Button) findViewById(R.id.back_to_setup_btn);
        setup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent backToSetup = new Intent(Practice.this, PracticeSetUpActivity.class);
                //Practice.this.startActivity(backToSetup);
                finish();
            }
        });

        // Set the text for the title
        tv_title.setText(R.string.prac_title1);

        // Set the components to be invisible

        // TextViews
        firstNumText.setVisibility(View.INVISIBLE);
        secondNumText.setVisibility(View.INVISIBLE);
        operator.setVisibility(View.INVISIBLE);
        tv_equals.setVisibility(View.INVISIBLE);

        // Buttons
        chk_btn.setVisibility(View.INVISIBLE);
        cont_try_btn.setVisibility(View.INVISIBLE);

        // EditText field
        checkText.setVisibility(View.INVISIBLE);
    }

    // A set of globals used to generate and calculate a sum
    int number1 = 0;
    int number2 = 0;

    // Store the case to determine which type of math is to be used
    int a_case = -1;
    int a_diff = -1;

    // Store the sum of the equation
    int answer = 0;

    // Method for the Generate button: generates random numbers
    public void generate(View view) {
        // References to the TextViews, EditText field, and Buttons

        // Reference to the title
        TextView tv_title = (TextView) findViewById(R.id.practiceTitle);
        // Reference to the first number (TextView)
        TextView firstNumText = (TextView) findViewById(R.id.firstDigitTextView);
        // Reference to the second number (TextView)
        TextView secondNumText = (TextView) findViewById(R.id.secondDigitTextView);
        // Reference to the operator (TextView)
        TextView operator = (TextView) findViewById(R.id.mathTypeTextView);
        // Reference to the equals sign (TV)
        TextView tv_equals = (TextView) findViewById(R.id.equalsTextView);
        // Reference to the Begin and Check buttons
        Button bgn_btn = (Button) findViewById(R.id.prac_begin_btn);
        Button chk_btn = (Button) findViewById(R.id.checkBtn);
        // Reference to the EditText field
        EditText checkText = (EditText) findViewById(R.id.editText);

        // Set the Begin button to be invisible and other components visible
        bgn_btn.setVisibility(View.INVISIBLE);

        // TextViews
        firstNumText.setVisibility(View.VISIBLE);
        secondNumText.setVisibility(View.VISIBLE);
        operator.setVisibility(View.VISIBLE);
        tv_equals.setVisibility(View.VISIBLE);

        // Button
        chk_btn.setVisibility(View.VISIBLE);

        // EditText field
        checkText.setVisibility(View.VISIBLE);

        // Change the text for the title
        tv_title.setText(R.string.prac_title2);

        // Create a Random class object
        Random rand = new Random();

        // Conditions based off the difficulty selection by the user:
        switch (diff_selection) {
            // Beginner difficulty
            case "Beginner (Default : 1 : 10)":
                number1 = rand.nextInt(9) + 1;
                number2 = rand.nextInt(10);
                a_diff = 0;
                break;
            case "Principiante (Defecto : 1 : 10)":
                number1 = rand.nextInt(9) + 1;
                number2 = rand.nextInt(10);
                a_diff = 0;
                break;
            // Intermediate difficulty
            case "Intermediate (1 : 50)":
                number1 = rand.nextInt(50) + 1;
                number2 = rand.nextInt(51);
                a_diff = 1;
                break;
            case "Intermedio (1 : 50)":
                number1 = rand.nextInt(50) + 1;
                number2 = rand.nextInt(51);
                a_diff = 1;
                break;
            // Advanced difficulty
            case "Advanced (1 : 100)":
                number1 = rand.nextInt(100) + 1;
                number2 = rand.nextInt(101);
                a_diff = 1;
                break;
            case "Avanzado (1 : 100)":
                number1 = rand.nextInt(100) + 1;
                number2 = rand.nextInt(101);
                a_diff = 1;
                break;
            default:
                break;
        }

        // Change the operator based off what the user selected for math type
        switch(math_selection) {
            // For addition...
            case "Addition (+)":
                operator.setText(R.string.add_operator);
                a_case = 0;
                break;
            case "Adición (+)":
                operator.setText(R.string.add_operator);
                a_case = 0;
                break;
            // ...or for subtraction...
            case "Subtraction (-)":
                operator.setText(R.string.sub_operator);
                a_case = 1;
                break;
            case "Sustracción (-)":
                operator.setText(R.string.sub_operator);
                a_case = 1;
                break;
            // ...or for multiplication
            case "Multiplication (x)":
                operator.setText(R.string.multiplication_operator);
                a_case = 2;
                break;
            case "Multiplicación (x)":
                operator.setText(R.string.multiplication_operator);
                a_case = 2;
                break;
            case "Division (÷)":
                operator.setText(R.string.division_operator);
                a_case = 3;
                break;
            case "División (÷)":
                operator.setText(R.string.division_operator);
                a_case = 3;
                break;
            default:
                break;
        }

        // Calculate the answer
        switch(a_case) {
            case 0:
                // Find the sum for Addition
                answer = number1 + number2;
                break;
            case 1:
                // Adjust the second number so we don't get a negative answer
                number2 = rand.nextInt(number1);
                // Find the difference
                answer = number1 - number2;
                break;
            case 2:
                // Find the product
                if (a_diff == 0) {
                    number1 = rand.nextInt(6) + 1;
                    number2 = rand.nextInt(7);
                }
                else if (a_diff == 1) {
                    number1 = rand.nextInt(12) + 1;
                    number2 = rand.nextInt(12) + 1;
                }
                answer = number1 * number2;
                break;
            case 3:
                // Find the quotient
                if (a_diff == 0) {
                    number1 = rand.nextInt(36) + 1;
                    number2 = rand.nextInt(6) + 1;
                    while (number1 % number2 != 0) {
                        number2 = rand.nextInt(6) + 1;
                    }
                }
                else if (a_diff == 1) {
                    number1 = rand.nextInt(144) + 1;
                    number2 = rand.nextInt(12) + 1;
                    while (number1 % number2 != 0) {
                        number2 = rand.nextInt(12) + 1;
                    }
                }
                // Calculate the quotient
                answer = number1/number2;
            default:
                break;
        }

        // For the screen display (equation):

        // Convert the first number (int) to a string
        String firstNumStr = String.valueOf(number1);
        // Set the text of the first number (TextView) to the first number (int)
        firstNumText.setText(firstNumStr);

        // Convert the second number (int) to a string
        String secondNumStr = String.valueOf(number2);
        // Set the text of the second number (TextView) to the second number (int)
        secondNumText.setText(secondNumStr);
    }

    // Method for the Check button: checks if the user entered the correct answer or not
    public void check(View view) {
        // Store the user's answer
        int userAnswer;

        // Reference to the EditText field
        final EditText checkText = (EditText) findViewById(R.id.editText);

        // Reference to a TextView
        final TextView correct_incorrect = (TextView) findViewById(R.id.correct_incorrect_TV);

        // Reference to the Cont/TryAgain button
        final Button cont_try_btn = (Button) findViewById(R.id.continue_tryAgain_btn);

        // Reference to the check button
        final Button chk_btn = (Button) findViewById(R.id.checkBtn);

        // ** ERROR CHECK **
        // Convert the userAnswer to an integer if the user has generated an equation
        if (checkText.getText().toString().equals("")) {
            userAnswer = -1;
        }
        else {
            userAnswer = Integer.parseInt(checkText.getText().toString());
        }

        // Condition to determine if the user is correct or not
        if (answer == userAnswer)
        {
            // Display that the user got the answer correct
            correct_incorrect.setVisibility(View.VISIBLE);
            correct_incorrect.setText(R.string.correct);

            // For the button
            cont_try_btn.setVisibility(View.VISIBLE);
            cont_try_btn.setText(R.string.prac_continue);

            // Set the check button and EditText field disabled
            chk_btn.setEnabled(false);
            checkText.setEnabled(false);

            // Set the onClickListener for the cont_try button
            cont_try_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set the button invisible
                    cont_try_btn.setVisibility(View.INVISIBLE);

                    // Set the TextView invisible
                    correct_incorrect.setVisibility(View.INVISIBLE);

                    // Enable them
                    chk_btn.setEnabled(true);
                    checkText.setEnabled(true);

                    // Generate a new equation
                    generate(view);

                    // Clear the EditText field
                    checkText.getText().clear();
                }
            });
        }
        else
        {
            // Display that the user got the answer wrong
            correct_incorrect.setVisibility(View.VISIBLE);
            correct_incorrect.setText(R.string.incorrect);

            // For the button
            cont_try_btn.setVisibility(View.VISIBLE);
            cont_try_btn.setText(R.string.prac_tryAgain);

            // Disable the check button and EditText field
            chk_btn.setEnabled(false);
            checkText.setEnabled(false);

            // Set the onClickListener for the cont_try button
            cont_try_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set the button invisible
                    cont_try_btn.setVisibility(View.INVISIBLE);

                    // Set the TextView invisible
                    correct_incorrect.setVisibility(View.INVISIBLE);

                    // Enable the check button
                    chk_btn.setEnabled(true);
                    checkText.setEnabled(true);

                    // Clear the EditText field
                    checkText.getText().clear();
                }
            });
        }
    }
}
