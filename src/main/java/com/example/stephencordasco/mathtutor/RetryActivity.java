package com.example.stephencordasco.mathtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RetryActivity extends AppCompatActivity {

    // Define global variables used to store data from the previous activity
    int sIndex;
    // Define the arrays
    int sNumSet1[] = new int[15];
    int sNumSet2[] = new int[15];
    int sAnswer[] = new int[15];
    int sUserAnswer[] = new int[15];
    // Define a String
    String sOperationType;

    // Define a String and an Int to help display the answer
    String sToInt;
    int sAnswerTV;
    int user_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);

        // Define variables to grab the data from the previous activity
        Bundle extras1 = getIntent().getExtras();
        Bundle extras2 = getIntent().getExtras();
        Bundle extras3 = getIntent().getExtras();
        Bundle extras4 = getIntent().getExtras();
        Bundle extras5 = getIntent().getExtras();
        Bundle extras6 = getIntent().getExtras();

        // Initialize the globals with the data retrieved
        sIndex = extras1.getInt("sIndex");
        sNumSet1 = extras2.getIntArray("sNumSet1");
        sNumSet2 = extras3.getIntArray("sNumSet2");
        sAnswer = extras4.getIntArray("sAnswer");
        sUserAnswer = extras5.getIntArray("sUserAnswer");
        sOperationType = extras6.getString("sOperationType");

        // Reference to the text views (for the equation)
        TextView s_num1_TV = (TextView) findViewById(R.id.score_first_num_TV);
        final TextView s_num2_TV = (TextView) findViewById(R.id.score_second_num_TV);
        TextView s_operation_TV = (TextView) findViewById(R.id.score_operation_TV);

        // References to the text views (for the result of reattempt/show answer)
        final TextView answer_label_TV = (TextView) findViewById(R.id.see_answer_TV);
        final TextView answer_TV = (TextView) findViewById(R.id.correct_answer_TV);
        final TextView correct_incorrect_TV =
                (TextView) findViewById(R.id.score2_correct_incorrect_TV);

        // Set the answer label and answer to be invisible
        answer_label_TV.setVisibility(View.INVISIBLE);
        answer_TV.setVisibility(View.INVISIBLE);

        // Display the equation based off the button the user selected
        for (int index = 0; index < 15; index++) {
            displayEquation(s_num1_TV, s_num2_TV, sOperationType, s_operation_TV, sIndex,
                    sNumSet1, sNumSet2);
        }

        // Reference to the buttons
        final Button s_retry_btn = (Button) findViewById(R.id.score_retry_btn);
        final Button s_check_btn = (Button) findViewById(R.id.score_check_btn);
        final Button s_see_answer = (Button) findViewById(R.id.score_answer_btn);
        final Button s_back_btn = (Button) findViewById(R.id.score_goBack_btn);

        // Reference to the ET field
        final EditText score2_user_answer_ET = (EditText) findViewById(R.id.score_answer_field_ET);

        // Set the check button invisible
        s_check_btn.setVisibility(View.INVISIBLE);
        // Set the ET field to be disabled
        score2_user_answer_ET.setEnabled(false);

        // Test: hide the keyboard on Activity run
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Set the onClickListeners

        // For the back button
        s_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent backToResults = new Intent(RetryActivity.this, ResultActivity.class);
                //RetryActivity.this.startActivity(backToResults);
                finish();
            }
        });

        // For the retry button
        s_retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the check button visible
                s_check_btn.setVisibility(View.VISIBLE);
                // Disable the see answer button
                s_see_answer.setEnabled(false);
                // Enable the ET field
                score2_user_answer_ET.setEnabled(true);
            }
        });

        // For the check button
        s_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the see answer button enabled
                s_see_answer.setEnabled(true);
                // Set the button disabled after the user presses it
                s_check_btn.setEnabled(false);

                // Set the retry button invisible
                s_retry_btn.setVisibility(View.INVISIBLE);

                // Check the operation
                switch(sOperationType) {
                    case "Addition (+)":
                        // Calculate the sum
                        sAnswerTV = sNumSet1[sIndex] + sNumSet2[sIndex];
                        break;
                    case "Adición (+)":
                        // Calculate the sum
                        sAnswerTV = sNumSet1[sIndex] + sNumSet2[sIndex];
                        break;

                    case "Subtraction (-)":
                        // Calculate the difference
                        sAnswerTV = sNumSet1[sIndex] - sNumSet2[sIndex];
                        break;
                    case "Sustracción (-)":
                        // Calculate the difference
                        sAnswerTV = sNumSet1[sIndex] - sNumSet2[sIndex];
                        break;

                    case "Multiplication (x)":
                        // Calculate the product
                        sAnswerTV = sNumSet1[sIndex] * sNumSet2[sIndex];
                        break;
                    case "Multiplicación (x)":
                        // Calculate the product
                        sAnswerTV = sNumSet1[sIndex] * sNumSet2[sIndex];
                        break;

                    case "Division (÷)":
                        // Calculate the quotient
                        sAnswerTV = sNumSet1[sIndex] / sNumSet2[sIndex];
                        break;

                    case "División (÷)":
                        sAnswerTV = sNumSet1[sIndex] / sNumSet2[sIndex];
                        break;

                    default:
                        break;
                }

                // Get the user answer
                // Check the ET field
                if (score2_user_answer_ET.getText().toString().equals("")) {
                    // If the ET field is empty set the user answer to a negative value
                    user_answer = -1;
                }
                else {
                    // Store the user answer
                    user_answer = Integer.parseInt(score2_user_answer_ET.getText().toString());
                }

                // Check the user answer
                if (user_answer == sAnswerTV) {
                    correct_incorrect_TV.setText(R.string.correct);
                }
                else {
                    correct_incorrect_TV.setText(R.string.incorrect);
                }
            }
        });

        // For the see answer button
        s_see_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the TextViews to be visible again
                answer_label_TV.setVisibility(View.VISIBLE);
                answer_TV.setVisibility(View.VISIBLE);

                // Set the other components to be disabled
                s_retry_btn.setEnabled(false);
                score2_user_answer_ET.setEnabled(false);
                s_see_answer.setEnabled(false);

                // Check the operation
                switch(sOperationType) {
                    case "Addition (+)":
                        sAnswerTV = sNumSet1[sIndex] + sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;
                    case "Adición (+)":
                        sAnswerTV = sNumSet1[sIndex] + sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;

                    case "Subtraction (-)":
                        sAnswerTV = sNumSet1[sIndex] - sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;
                    case "Sustracción (-)":
                        sAnswerTV = sNumSet1[sIndex] - sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;

                    case "Multiplication (x)":
                        sAnswerTV = sNumSet1[sIndex] * sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;
                    case "Multiplicación (x)":
                        sAnswerTV = sNumSet1[sIndex] * sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;

                    case "Division (÷)":
                        sAnswerTV = sNumSet1[sIndex] / sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;
                    case "División (÷)":
                        sAnswerTV = sNumSet1[sIndex] / sNumSet2[sIndex];
                        sToInt = String.valueOf(sAnswerTV);
                        break;

                    default:
                        break;
                }

                // Set the text for the TV
                answer_TV.setText(sToInt);
            }
        });
    }

    public void displayEquation(TextView firstTV, TextView secondTV, String opT, TextView opType,
                                int index, int intArr1[], int intArr2[]) {
        // Define a String variable to help convert the ints
        String iToS;
        // Convert and set the text of the first TV
        iToS = String.valueOf(intArr1[index]);
        firstTV.setText(iToS);
        // Convert and set the text of the second TV
        iToS = String.valueOf(intArr2[index]);
        secondTV.setText(iToS);

        // Determine the operation type (either addition)
        switch(opT) {
            case "Addition (+)":
                opType.setText(R.string.add_operator);
                break;
            case "Adición (+)":
                opType.setText(R.string.add_operator);
                break;

            case "Subtraction (-)":
                opType.setText(R.string.sub_operator);
                break;
            case "Sustracción (-)":
                opType.setText(R.string.sub_operator);
                break;

            case "Multiplication (x)":
                opType.setText(R.string.multiplication_operator);
                break;
            case "Multiplicación (x)":
                opType.setText(R.string.multiplication_operator);
                break;

            case "Division (÷)":
                opType.setText(R.string.division_operator);
                break;
            case "División (÷)":
                opType.setText(R.string.division_operator);
                break;

            default:
                break;
        }
    }
}
