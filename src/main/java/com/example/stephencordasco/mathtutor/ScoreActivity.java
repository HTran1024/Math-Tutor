package com.example.stephencordasco.mathtutor;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    // Define variables to pass data to the second part of the Score Activity
    // Define the ints
    int s_scores;
    int s_outOf;
    // Define the arrays
    int s_numSet1[] = new int[15];
    int s_numSet2[] = new int[15];
    int s_answer[] = new int[15];
    int s_userAnswer[] = new int[15];
    // Define a String
    String s_op_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get the data from the previous activity and store it

        // Get the user's score (int) and number of quiz questions
        Bundle extras1 = getIntent().getExtras();
        s_scores = extras1.getInt("UserScore");

        Bundle extras2 = getIntent().getExtras();
        s_outOf = extras2.getInt("OutOf");

        // Get the arrays (integer arrays)
        Bundle extras3 = getIntent().getExtras();
        s_numSet1 = extras3.getIntArray("NumSet1");

        Bundle extras4 = getIntent().getExtras();
        s_numSet2 = extras4.getIntArray("NumSet2");

        Bundle extras5 = getIntent().getExtras();
        s_answer = extras5.getIntArray("Answer");

        Bundle extras6 = getIntent().getExtras();
        s_userAnswer = extras6.getIntArray("UserAnswer");

        Bundle extras7 = getIntent().getExtras();
        s_op_type = extras7.getString("OperationType");

        // Reference to the title TV
        TextView scoreTitleTV = (TextView) findViewById(R.id.score_title_TV);

        // Display the title (includes the user's score)

        // Convert ints to Strings
        String scoresToString = String.valueOf(s_scores);
        String outOfToString = String.valueOf(s_outOf);
        // Set the title
        scoreTitleTV.setText(getString(R.string.score_title, scoresToString, outOfToString));

        // Reference to the home button
        Button homeButton = (Button) findViewById(R.id.score_activity_home_btn);

        // Set the onClickListener for the button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to take the user back to the home screen
                Intent backToHome = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(backToHome);
            }
        });

        // References to all other buttons
        Button btn1 = (Button) findViewById(R.id.btn_to_question_one);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn_to_question_two);
        btn2.setOnClickListener(this);

        Button btn3 = (Button) findViewById(R.id.btn_to_question_three);
        btn3.setOnClickListener(this);

        Button btn4 = (Button) findViewById(R.id.btn_to_question_four);
        btn4.setOnClickListener(this);

        Button btn5 = (Button) findViewById(R.id.btn_to_question_five);
        btn5.setOnClickListener(this);

        Button btn6 = (Button) findViewById(R.id.btn_to_question_six);
        btn6.setOnClickListener(this);

        Button btn7 = (Button) findViewById(R.id.btn_to_question_seven);
        btn7.setOnClickListener(this);

        Button btn8 = (Button) findViewById(R.id.btn_to_question_eight);
        btn8.setOnClickListener(this);

        Button btn9 = (Button) findViewById(R.id.btn_to_question_nine);
        btn9.setOnClickListener(this);

        Button btn10 = (Button) findViewById(R.id.btn_to_question_ten);
        btn10.setOnClickListener(this);

        Button btn11 = (Button) findViewById(R.id.btn_to_question_eleven);
        btn11.setOnClickListener(this);

        Button btn12 = (Button) findViewById(R.id.btn_to_question_twelve);
        btn12.setOnClickListener(this);

        Button btn13 = (Button) findViewById(R.id.btn_to_question_thirteen);
        btn13.setOnClickListener(this);

        Button btn14 = (Button) findViewById(R.id.btn_to_question_fourteen);
        btn14.setOnClickListener(this);

        Button btn15 = (Button) findViewById(R.id.btn_to_question_fifteen);
        btn15.setOnClickListener(this);

        // References to the TVs (Results)
        TextView s_TV1 = (TextView) findViewById(R.id.result_question_one);
        TextView s_TV2 = (TextView) findViewById(R.id.result_question_two);
        TextView s_TV3 = (TextView) findViewById(R.id.result_question_three);
        TextView s_TV4 = (TextView) findViewById(R.id.result_question_four);
        TextView s_TV5 = (TextView) findViewById(R.id.result_question_five);
        TextView s_TV6 = (TextView) findViewById(R.id.result_question_six);
        TextView s_TV7 = (TextView) findViewById(R.id.result_question_seven);
        TextView s_TV8 = (TextView) findViewById(R.id.result_question_eight);
        TextView s_TV9 = (TextView) findViewById(R.id.result_question_nine);
        TextView s_TV10 = (TextView) findViewById(R.id.result_question_ten);
        TextView s_TV11 = (TextView) findViewById(R.id.result_question_eleven);
        TextView s_TV12 = (TextView) findViewById(R.id.result_question_twelve);
        TextView s_TV13 = (TextView) findViewById(R.id.result_question_thirteen);
        TextView s_TV14 = (TextView) findViewById(R.id.result_question_fourteen);
        TextView s_TV15 = (TextView) findViewById(R.id.result_question_fifteen);
        // References to the TVs (Quiz numbers)
        TextView s_numTV6 = (TextView) findViewById(R.id.question_six);
        TextView s_numTV7 = (TextView) findViewById(R.id.question_seven);
        TextView s_numTV8 = (TextView) findViewById(R.id.question_eight);
        TextView s_numTV9 = (TextView) findViewById(R.id.question_nine);
        TextView s_numTV10 = (TextView) findViewById(R.id.question_ten);
        TextView s_numTV11 = (TextView) findViewById(R.id.question_eleven);
        TextView s_numTV12 = (TextView) findViewById(R.id.question_twelve);
        TextView s_numTV13 = (TextView) findViewById(R.id.question_thirteen);
        TextView s_numTV14 = (TextView) findViewById(R.id.question_fourteen);
        TextView s_numTV15 = (TextView) findViewById(R.id.question_fifteen);

        // Define a control variable
        int i = 0;

        // Set the result based on whether or the not the user answered the equation correctly
        while (i < 15) {
            switch (i) {
                case 0:
                    // Call the method passing the answer array, user_answer array, a TV, and the
                    //  control variable
                    gradeQuestion(s_answer, s_userAnswer, s_TV1, i);
                    break;

                case 1:
                    gradeQuestion(s_answer, s_userAnswer, s_TV2, i);
                    break;

                case 2:
                    gradeQuestion(s_answer, s_userAnswer, s_TV3, i);
                    break;

                case 3:
                    gradeQuestion(s_answer, s_userAnswer, s_TV4, i);
                    break;

                case 4:
                    gradeQuestion(s_answer, s_userAnswer, s_TV5, i);
                    break;

                case 5:
                    gradeQuestion(s_answer, s_userAnswer, s_TV6, i);
                    // If the user only selected to quiz 5 questions
                    setInvisible(i, s_outOf, s_TV6, s_numTV6, btn6);
                    break;

                case 6:
                    gradeQuestion(s_answer, s_userAnswer, s_TV7, i);
                    setInvisible(i, s_outOf, s_TV7, s_numTV7, btn7);
                    break;

                case 7:
                    gradeQuestion(s_answer, s_userAnswer, s_TV8, i);
                    setInvisible(i, s_outOf, s_TV8, s_numTV8, btn8);
                    break;

                case 8:
                    gradeQuestion(s_answer, s_userAnswer, s_TV9, i);
                    setInvisible(i, s_outOf, s_TV9, s_numTV9, btn9);
                    break;

                case 9:
                    gradeQuestion(s_answer, s_userAnswer, s_TV10, i);
                    setInvisible(i, s_outOf, s_TV10, s_numTV10, btn10);
                    break;

                case 10:
                    gradeQuestion(s_answer, s_userAnswer, s_TV11, i);
                    // If the user only selected to quiz 10 questions
                    setInvisible(i, s_outOf, s_TV11, s_numTV11, btn11);
                    break;

                case 11:
                    gradeQuestion(s_answer, s_userAnswer, s_TV12, i);
                    setInvisible(i, s_outOf, s_TV12, s_numTV12, btn12);
                    break;

                case 12:
                    gradeQuestion(s_answer, s_userAnswer, s_TV13, i);
                    setInvisible(i, s_outOf, s_TV13, s_numTV13, btn13);
                    break;

                case 13:
                    gradeQuestion(s_answer, s_userAnswer, s_TV14, i);
                    setInvisible(i, s_outOf, s_TV14, s_numTV14, btn14);
                    break;

                case 14:
                    gradeQuestion(s_answer, s_userAnswer, s_TV15, i);
                    setInvisible(i, s_outOf, s_TV15, s_numTV15, btn15);
                    break;

                default:
                    break;
            }
            // Increment the control variable
            i++;
        }
    }

    public void goToQuestion(int index) {
        Intent goToNextActivity = new Intent(ScoreActivity.this, Score2Activity.class);
        goToNextActivity.putExtra("sIndex", index);
        goToNextActivity.putExtra("sNumSet1", s_numSet1);
        goToNextActivity.putExtra("sNumSet2", s_numSet2);
        goToNextActivity.putExtra("sAnswer", s_answer);
        goToNextActivity.putExtra("sUserAnswer", s_userAnswer);
        goToNextActivity.putExtra("sOperationType", s_op_type);
        startActivity(goToNextActivity);
    }

    public void gradeQuestion(int[] answer, int[] u_answer, TextView aTV, int i) {
        if (answer[i] == u_answer[i]) {
            aTV.setText(R.string.correct);
        }
    }

    public void setInvisible(int index, int ceil, TextView resultTV, TextView numTV, Button aBtn) {
        if ((ceil - 1) < index) {
            numTV.setVisibility(View.INVISIBLE);
            resultTV.setVisibility(View.INVISIBLE);
            aBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_to_question_one:
                // Call the method (launches the Intent for each button)
                goToQuestion(0);
                break;

            case R.id.btn_to_question_two:
                goToQuestion(1);
                break;

            case R.id.btn_to_question_three:
                goToQuestion(2);
                break;

            case R.id.btn_to_question_four:
                goToQuestion(3);
                break;

            case R.id.btn_to_question_five:
                goToQuestion(4);
                break;

            case R.id.btn_to_question_six:
                goToQuestion(5);
                break;

            case R.id.btn_to_question_seven:
                goToQuestion(6);
                break;

            case R.id.btn_to_question_eight:
                goToQuestion(7);
                break;

            case R.id.btn_to_question_nine:
                goToQuestion(8);
                break;

            case R.id.btn_to_question_ten:
                goToQuestion(9);
                break;

            case R.id.btn_to_question_eleven:
                goToQuestion(10);
                break;

            case R.id.btn_to_question_twelve:
                goToQuestion(11);
                break;

            case R.id.btn_to_question_thirteen:
                goToQuestion(12);
                break;

            case R.id.btn_to_question_fourteen:
                goToQuestion(13);
                break;

            case R.id.btn_to_question_fifteen:
                goToQuestion(14);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
