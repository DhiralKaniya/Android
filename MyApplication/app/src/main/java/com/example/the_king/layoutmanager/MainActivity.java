package com.example.the_king.layoutmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username = initializeUser();
        checkuser();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();

        if(username.equals("admin")){
            menu.findItem(R.id.nav_product_add).setVisible(true);
        }else{
            menu.findItem(R.id.nav_product_add).setVisible(false);
        }
        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> myitem = new ArrayList<String>();
        myitem.add("item 1");
        myitem.add("item 2");
        myitem.add("item 3");

        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myitem);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataadapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.SharedFile),MODE_PRIVATE).edit();
            editor.remove(getString(R.string.login_user)).commit();
            username = getString(R.string.login_user_default);
            checkuser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String initializeUser(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.SharedFile),MODE_PRIVATE);
        String myuser = sharedPreferences.getString(getString(R.string.login_user),getString(R.string.login_user_default));
        Toast.makeText(this,"Welcome "+myuser,Toast.LENGTH_LONG).show();
        return myuser;
    }
    public void checkuser(){
        if(username.equals(getString(R.string.login_user_default))) {
            Intent intent = new Intent(this, activity_home.class);
            startActivity(intent);
            finish();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
           fragment = new Home_Fragment();
        } else if (id == R.id.nav_gallery) {
            fragment = new Gallery_Fragment();
        } else if (id == R.id.nav_aboutus) {
            fragment = new Aboutus_Fragment();
        } else if (id == R.id.nav_contactus) {
            fragment = new Contactus_Fragment();
        } else if (id == R.id.nav_product_add) {
            fragment = new Insert_Fragment();
        } else if (id == R.id.nav_product_view) {
            fragment = new view_Fragment();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.display,fragment);
            getFragmentManager().popBackStack();
            fragmentTransaction.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
