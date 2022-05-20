package com.example.onlineshopping;
import android.content.Context;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {
    private Context context;
    private MainActivity ma = new MainActivity();
    public InputValidation(Context context) {
        this.context = context;
    }
    protected Boolean validateUsername(EditText uname, TextInputLayout regUsername, String errorMessage) {
        String val = uname.getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        regUsername.setErrorEnabled(false);
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regUsername.setError(errorMessage);
            return false;
        }
        else {
            regUsername.setError(errorMessage);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    protected Boolean validateEmail(@NonNull EditText em, TextInputLayout regEmail, String errorMessage) {
        String val = em.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(errorMessage);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    protected Boolean validatePassword(EditText pass, TextInputLayout regPassword, String errorMessage) {
        String val = pass.getText().toString().trim();
        regPassword.setErrorEnabled(false);
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(errorMessage);
            regPassword.setErrorEnabled(false);
            return true;

        }
    }

    protected Boolean confirmPassword(@NonNull EditText pass1, @NonNull EditText pass2, TextInputLayout layout, String errorMessage){
        String val1 = pass1.getText().toString().trim();
        String val2 = pass2.getText().toString().trim();

        if(val1.isEmpty() || val2.isEmpty()) {
            layout.setError("Enter Values in both fields");
            return false;
        }
        else if(!val1.contentEquals(val2)){
            layout.setError(errorMessage);
            return false;
        }
        else{
            layout.setErrorEnabled(false);

        }
        return true;

    }








}
