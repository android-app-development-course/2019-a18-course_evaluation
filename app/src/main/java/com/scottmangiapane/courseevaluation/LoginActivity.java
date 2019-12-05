package com.scottmangiapane.courseevaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scottmangiapane.courseevaluation.ui.login.LoginFragment;
import com.scottmangiapane.courseevaluation.ui.signup.SignupFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().add(R.id.login_fragment,new LoginFragment()).commit();

    }
}
