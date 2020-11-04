package com.inovex.tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText signInUserTypeEditText, signInPasswordEditText, signInUsernameEditText;
    private Button loginButton;
    private TextView signInforgetpasswordtextView;
    private TextView signInSignUptextView;
    private ProgressBar progressBar;
    String chk_password/* = "123456"*/ ;
    String chk_username;
    String chk_userType;
    String usertype = "driver" ;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Sign In Here");

        signInUsernameEditText = (EditText) findViewById(R.id.signInUsernameId);
        radioGroup = findViewById(R.id.radio);
        signInPasswordEditText = (EditText) findViewById(R.id.signInpasswordId);
        progressBar = (ProgressBar) findViewById(R.id.progressbarId);
        loginButton = (Button) findViewById(R.id.login_btn);
        signInforgetpasswordtextView = (TextView) findViewById(R.id.signInforgetPasswordtextviewid);
        signInSignUptextView = (TextView) findViewById(R.id.signInSignUp);

        signInSignUptextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        signInforgetpasswordtextView.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_driver:
                if (checked)
                    usertype = "driver";
                break;
            case R.id.radio_user:
                if (checked)
                    usertype = "user";
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                userLogin();
                break;


        }
    }

    private void userLogin() {

        String json = "" ;
        try {
            InputStream inputStream = getAssets().open("user.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject obj = new JSONObject(json);
            chk_password = obj.getString("password");
            chk_username = obj.getString("username");
            chk_userType = obj.getString("usertype");


        } catch (Exception e) {
            e.printStackTrace();
        }

        String username =  signInUsernameEditText.getText().toString().trim();
        String password =  signInPasswordEditText.getText().toString().trim();


        if(username.isEmpty())
        {
            signInUsernameEditText.setError("Enter your username");
            signInUsernameEditText.requestFocus();
            return;
        }


        //checking the validity of the password
        if(password.isEmpty())
        {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            signInPasswordEditText.setError("Password is too short. Password Should be more than 6");
            signInPasswordEditText.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        if((chk_username.equals(username)) && (chk_password.equals(password)) && (chk_userType.equals(usertype))) {


            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
            //signInEmailEditText.setText(json);
        }
        else {
            Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
    }


}