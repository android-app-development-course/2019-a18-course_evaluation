package com.scottmangiapane.courseevaluation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.scottmangiapane.courseevaluation.ClassData.UserModel;
import com.scottmangiapane.courseevaluation.ui.login.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().add(R.id.login_fragment,new LoginFragment()).commit();
    }

}

