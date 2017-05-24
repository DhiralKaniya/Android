package com.example.the_king.layoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class activity_signup extends AppCompatActivity {

    private Button singup;
    private TextView email;
    private TextView username;
    private TextView password;
    private TextView cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Home");
        singup = (Button)findViewById(R.id.signupbtn);
        email = (TextView)findViewById(R.id.text_email);
        username = (TextView) findViewById(R.id.text_username);
        password = (TextView) findViewById(R.id.text_password);
        cpassword = (TextView)findViewById(R.id.text_confirmpassword);

     }

    public void register(View view){
        boolean res = checkData();
        if(res){
            res = addIntoDatabase();
            if(res){
                Toast.makeText(this,"You have successfully signup please login with you username and password",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,activity_login.class);
                startActivity(intent);
                finish();
            }
        }
    }
    public boolean addIntoDatabase(){
        try {
            User newuser = new User(username.getText().toString(), password.getText().toString(), email.getText().toString());
            newuser.save();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean checkData(){
        if(!password.getText().toString().equals(cpassword.getText().toString())) {
            Toast.makeText(this,"Password  & Confirm password Should be match",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.getText().toString().length() < 6 || password.getText().toString().length() > 12){
            Toast.makeText(this,"Password  length must be between 6 to 12 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(email.getText().toString().toString() == null || username.getText().toString().toString() == null){
            Toast.makeText(this,"Username or Email Invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String estring = email.getText().toString().trim();
            if(estring.matches(emailPattern))
                return true;
            else{
                Toast.makeText(this,"Email address invalid",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return false;

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,activity_home.class);
        startActivity(intent);
        finish();
    }
}
