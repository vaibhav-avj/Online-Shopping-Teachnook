package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private TextView signUpView;
    private Button loginButton;
    private TextInputLayout email_lay, pass_lay;
    private EditText pass,email;

    private DatabaseHelper dbhelper;
    private InputValidation inputValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signUpView =findViewById(R.id.signup);
        loginButton = findViewById(R.id.loginBtn);

        email_lay = findViewById(R.id.emailLayout);
        pass_lay = findViewById(R.id.passLayout);
        email = findViewById(R.id.lp_email);
        pass = findViewById(R.id.lp_pass);

        dbhelper= new DatabaseHelper(LoginActivity.this);
        inputValidation= new InputValidation(LoginActivity.this);

//        SharedPreferences prefs= getPreferences(MODE_PRIVATE);
//        User spUser= dbhelper.returnUser(email.getText().toString().trim());
//        SharedPreferences.Editor prefsEditor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(spUser);
//        prefsEditor.putString("User",json);
//        boolean status= prefs.getBoolean("Log_in",true);
//        if(status){
//            Toast.makeText(this, "User Already logged in!", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(LoginActivity.this, HomePage.class);
//            i.putExtra("Email", spUser.getEmail());
//            startActivity(i);
//            finish();
//        }
//        prefsEditor.commit();

        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String sqlPassword="";
               if(!inputValidation.validateEmail(email,email_lay,"Enter Valid Email"))
                   return;
               if(!inputValidation.validatePassword(pass,pass_lay,"Enter Valid Password"))
                   return;
               try{
                   sqlPassword = dbhelper.validateUser(email.getText().toString().trim());
               }
               catch(Exception e){
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
               }

               if(sqlPassword.equals(pass.getText().toString().trim()))
               {
                   Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(LoginActivity.this, HomePage.class);
                   i.putExtra("Email",email.getText().toString().trim());
                   startActivity(i);
                   finish();
               }else{
                   Toast.makeText(LoginActivity.this, "Invalid Email Id or Password!", Toast.LENGTH_SHORT).show();
               }

              }
        });





    }



}