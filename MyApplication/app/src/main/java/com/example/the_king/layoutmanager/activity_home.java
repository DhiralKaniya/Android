package com.example.the_king.layoutmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkExistingUser();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        Button login = (Button) findViewById(R.id.Loginbtn);
        Button signup = (Button) findViewById(R.id.Signuphomebutton);
        Button googlebtn = (Button) findViewById(R.id.googlesignup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),activity_login.class);
                startActivity(intent);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),activity_signup.class);
                startActivity(intent);
                finish();
                }
            }
        );
        googlebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Portal Under Developement",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void checkExistingUser(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.SharedFile),MODE_PRIVATE);
        String myuser = sharedPreferences.getString(getString(R.string.login_user),getString(R.string.login_user_default));
        if(!myuser.equals(getString(R.string.login_user_default))){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
