package com.example.onlineshopping;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.ContextUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.appcompat.widget.Toolbar;
public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout dl;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    View headerView;

    DatabaseHelper dbHelper;
    TextView name,email;
    User user;
    String UserEmailAddress= null;
    private cart_fragment fragCartList = new cart_fragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        dbHelper = new DatabaseHelper(HomePage.this);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        dl=findViewById(R.id.drawerLayout);

        NavigationView nv = findViewById(R.id.navMenu);
        headerView = nv.getHeaderView(0);
        name =headerView.findViewById(R.id.nav_nameTextView);
        email=headerView.findViewById(R.id.nav_emailTextView);
        nv.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,dl,toolbar,R.string.nav_open,R.string.nav_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        UserEmailAddress = i.getStringExtra("Email");
        setNameEmail(UserEmailAddress);
//
//
//        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString("User","");
//        User prefUser = gson.fromJson(json, User.class);

        if(savedInstanceState==null) {
            toolbar.setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
            nv.setCheckedItem(R.id.nav_home);
        }

    }

    private void setNameEmail(String userEmail){

        try{
            user= dbHelper.getUnameEmail(userEmail);
            name.setText("Hello, " + user.getUsername());
            email.setText(user.getEmail());
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new home_fragment()).commit();
                toolbar.setTitle("Home");
                break;
            case R.id.nav_acc:
                passValues();
                break;
            case R.id.nav_cart:
                Bundle bundle = new Bundle();
                bundle.putString("email",UserEmailAddress);
                cart_fragment cart= new cart_fragment();
                cart.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,cart).commit();
                toolbar.setTitle("Cart");
                break;
            case R.id.nav_signout:
                confirmSignOut();
                break;
        }
    dl.closeDrawer(GravityCompat.START);

    return true;
    }


    private void passValues(){
        try{
            Bundle bundle = new Bundle();
            String[] unameStr= name.getText().toString().split(",");
            account_fragment acc = new account_fragment();
            bundle.putString("uname",unameStr[1]);
            bundle.putString("email",UserEmailAddress);
            acc.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,acc).commit();
            toolbar.setTitle("Account Information");
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    private void confirmSignOut(){
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder1.setMessage("Do you want to sign Out?\n" +
                "Items in your cart will be removed!. ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        openLogin();
                        fragCartList.resetCart();
                        Toast.makeText(getApplicationContext(), "Sign Out Successful!", Toast.LENGTH_SHORT).show();

                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void openLogin(){
        Intent i = new Intent(HomePage.this, LoginActivity.class);
        startActivity(i);
        this.finish();
    }


}