package com.example.stephencordasco.mathtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Reference to the TV
        TextView l_registerHereTV = (TextView) findViewById(R.id.log_register_here_TV);

        // Reference to the Login Button
        Button l_loginButton = (Button) findViewById(R.id.log_login_button);

        // Prevent the keypad from appearing after minimize during quiz run
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Set the listener for the TV
        l_registerHereTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(register);
            }
        });

        // Set the listener for the Button
        l_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggedIn = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(loggedIn);
            }
        });
    }
}
