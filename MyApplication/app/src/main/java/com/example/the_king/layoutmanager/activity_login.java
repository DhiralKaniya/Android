package com.example.the_king.layoutmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class activity_login extends AppCompatActivity {
    private Button b1 ;
    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b1 = (Button)findViewById(R.id.login);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);

    }

    public void checklogin(View view){
        boolean res = checkData();

        if(res==true){
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.SharedFile),MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin@123")){
                Intent intent = new Intent(this,MainActivity.class);
                editor.putString(getString(R.string.login_user),"admin");
                startActivity(intent);
                finish();
            }else{
                res = checkfromdb();
                if(res==true){
                    Intent intent = new Intent(this,MainActivity.class);
                    editor.putString(getString(R.string.login_user),username.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
            editor.commit();
        }
    }
    public boolean checkfromdb(){
        String data[] = new String[2];
        data[0] = username.getText().toString();
        data[1] = password.getText().toString();
        List<User> myuser = User.findWithQuery(User.class,"SELECT * FROM User WHERE username = ? AND password= ?",data);
        if(myuser.size() <= 0) {
            Toast.makeText(this,"username and password invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;

    }
    public boolean checkData(){
        if(password.getText().toString() == null ||  username.getText().toString() == null) {
            Toast.makeText(this,"username and password should not be blank",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {

            return true;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,activity_home.class);
        startActivity(intent);
        finish();
    }
}
