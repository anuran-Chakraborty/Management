package com.example.anuran.management;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;


public class Client extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt;
   // public String name="";
   // public String old="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("id");
        String old = bundle.getString("old");
       //Toast.makeText(getApplicationContext(),old, Toast.LENGTH_LONG).show();
        txt=(TextView)findViewById(R.id.textView500);
        txt.setText(name);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button viewSites=(Button)findViewById(R.id.ViewSites);
        Button issue=(Button)findViewById(R.id.issue);
        Button poc=(Button)findViewById(R.id.poc);
        //Bundle bundle = getIntent().getExtras();
//        SharedPrefManagerClient pref=new SharedPrefManagerClient(getApplicationContext());
  //      HashMap<String, String> user = pref.getUsername();
     //   final String ccid = user.get(SharedPrefManagerClient.KEY_USER_USERNAME_CLIENT);
      //  Toast.makeText(getApplicationContext(),ccid, Toast.LENGTH_LONG).show();
     /*   viewSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Client.this,ViewSites.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("cid", ccid);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });*/

//        issue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Client.this,raiseIssue.class);
//                startActivity(intent);
//            }
//        });


        poc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Client.this,AddPoc.class);
                startActivity(intent);
            }
        });

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {



        } else if (id == R.id.nav_change) {
            Intent intent=new Intent(Client.this,ChangePassword.class);
            Bundle bundle = getIntent().getExtras();

            String name = bundle.getString("id");
            String old = bundle.getString("old");
           // Toast.makeText(getApplicationContext(),old, Toast.LENGTH_LONG).show();
            intent.putExtra("id",name);
            intent.putExtra("old",old);
            startActivity(intent);

        } else if (id == R.id.nav_logout){
            SharedPrefManagerClient.getInstance(Client.this).logout();
            startActivity(new Intent(Client.this,clientLogin.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
