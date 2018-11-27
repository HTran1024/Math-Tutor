package com.example.stephencordasco.mathtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Reference to the Register button
        Button r_registerButton = (Button) findViewById(R.id.reg_register_button);

        // Prevent the keypad from appearing after minimize during quiz run
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Set the listener for the button
        r_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registered = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(registered);
            }
        });
    }
}
