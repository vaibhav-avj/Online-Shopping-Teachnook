package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button reg;
    private InputValidation iv;
    private TextInputLayout email,name,pass,cpass;
    private EditText ETemail,ETname,ETpass,ETcpass;
    private User user;
    private DatabaseHelper dbhelper;
    private TextView al_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        dbhelper = new DatabaseHelper(MainActivity.this);
        iv=new InputValidation(MainActivity.this);

        reg = findViewById(R.id.regBtn);
        al_account = findViewById(R.id.al_acc);

        email = findViewById(R.id.lay2);
        name = findViewById(R.id.lay1);
        pass = findViewById(R.id.lay3);
        cpass = findViewById(R.id.lay4);

        ETpass= findViewById(R.id.etpass);
        ETname= findViewById(R.id.etname);
        ETemail= findViewById(R.id.etemail);
        ETcpass= findViewById(R.id.etcpass);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!iv.validateUsername(ETname,name,"Enter a valid user name"))
                    return;
                if(!iv.validateEmail(ETemail,email,"Enter Valid Email"))
                    return;
                if(!iv.validatePassword(ETpass,pass,"Enter Valid Password"))
                    return;
                if(!iv.confirmPassword(ETpass, ETcpass, cpass,"Password doesn't Match" )){
                    return;
                }
                if(!dbhelper.checkUser(ETemail.getText().toString().trim())){
                    user.setUsername(ETname.getText().toString().trim());
                    user.setPassword(ETpass.getText().toString().trim());
                    user.setEmail(ETemail.getText().toString().trim());
                    dbhelper.addUser(user);
                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    emptyTextFields();

                    Intent in = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(in);
                    finish();
                }
                else
                    Toast.makeText(MainActivity.this, "User Already Exists!", Toast.LENGTH_SHORT).show();
            }
        });

        al_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void emptyTextFields() {
        ETemail.setText("");
        ETpass.setText("");
        ETcpass.setText("");
        ETname.setText("");

    }

}