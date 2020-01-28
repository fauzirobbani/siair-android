package com.example.siair.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.model.LoginRequestBody;
import com.example.siair.model.User;
import com.example.siair.ui.admin.MainActivity;
import com.example.siair.viewmodel.LoginViewModel;
import com.google.gson.Gson;

public class AuthActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    EditText editTextUsernameLogin;
    EditText editTextPasswordLogin;
    Button btnLogin;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editTextUsernameLogin = findViewById(R.id.login_username);
        editTextPasswordLogin = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);
        progressBarLogin = findViewById(R.id.login_pb);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getIsLoading().observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    loginSuccess(user);
                } else {
                    loginError();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin () {
        String username = editTextUsernameLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();
        loginViewModel.setUser(new LoginRequestBody(username, password));
    }

    private void showLoading () {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    private void hideLoading () {
        progressBarLogin.setVisibility(View.GONE);
    }

    private void loginSuccess (User user) {
        Gson gson = new Gson();
        String jsonLogin = gson.toJson(user);
        SharedPreferences.Editor sharedEditor = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).edit();
        sharedEditor.putString("DATA_LOGIN", jsonLogin);
        sharedEditor.apply();
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
        finish();
    }

    private void loginError () {
        Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
    }
}
