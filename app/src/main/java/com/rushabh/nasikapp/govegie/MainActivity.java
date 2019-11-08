package com.rushabh.nasikapp.govegie;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Food_Database database;
    SearchableSpinner ingredient_f,ingredient_s,ingredient_t;
    Button submit;
    Cursor mems;
    String f,s,t;
    ArrayList<String> rushabh=new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> dataSorted;
    String name,ing1,ing2,ing3,all_ing,recipie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,Sampurna.class);
               startActivity(i);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        submit = (Button) findViewById(R.id.submit);
        ingredient_f = (SearchableSpinner) findViewById(R.id.first_ingredient);
        ingredient_s = (SearchableSpinner) findViewById(R.id.second_ingredient);
        ingredient_t = (SearchableSpinner) findViewById(R.id.third_ingredient);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute();
            }
        });
        f_execute();

       ingredient_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               f = ingredient_f.getSelectedItem().toString();
               return;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        ingredient_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = ingredient_s.getSelectedItem().toString();
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ingredient_t.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t = ingredient_t.getSelectedItem().toString();
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void f_execute(){
        Food_Database db = new Food_Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllData();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lables);
        // Drop down layout style - list view with radio button
//        dataAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        ingredient_f.setAdapter(dataAdapter);
        ingredient_s.setAdapter(dataAdapter);
        ingredient_t.setAdapter(dataAdapter);
    }


    public void execute(){
        final int[] id = {0};
        database=new Food_Database(MainActivity.this);

        SQLiteDatabase db=database.getReadableDatabase();
        mems=db.rawQuery("select * from foodie where (Ingredient1='"+f+"')||(Ingredient1='"+s+"')||(Ingredient3='"+t+"')||(Ingredient2='"+f+"')||(Ingredient2='"+s+"')||(Ingredient3='"+t+"')||((Ingredient3='"+f+"')||(Ingredient3='"+s+"')||(Ingredient3='"+t+"'))",null);
        rushabh.clear();
        if(mems.moveToFirst()) {
            do {
                rushabh.add(""+mems.getString(1));
            } while (mems.moveToNext());
        }
//        if(mems==null){
//            Toast.makeText(this, "No items found!!!", Toast.LENGTH_SHORT).show();
//        }
        dataSorted = new ArrayAdapter<String>(this,
                R.layout.textview,R.id.text,rushabh);

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this,R.style.MyDialogTheme);
        builderSingle.setTitle("List of items:-");
        Toast.makeText(this, ""+mems.getCount(), Toast.LENGTH_SHORT).show();

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.setAdapter(dataSorted, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName="";
                strName = dataSorted.getItem(which);
                Food_Database dbHelper = new Food_Database(MainActivity.this);
                SQLiteDatabase newDB = dbHelper.getWritableDatabase();

                Cursor c = newDB.rawQuery("select * from foodie where name='"+strName+"'", null);
                    if(c!=null) {
                        if (c.moveToFirst()) {
                            do {
                                name = c.getString(1);
                                ing1 = c.getString(2);
                                ing2 = c.getString(3);
                                ing3 = c.getString(4);
                                all_ing = c.getString(7);
                                recipie = c.getString(8);
                            } while (c.moveToNext());
                        }
                    }
                                Intent i = new Intent(MainActivity.this, Details.class);
                                i.putExtra("Id", id);
                                i.putExtra("name", name);
                                i.putExtra("ing1", ing1);
                                i.putExtra("Ing2", ing2);
                                i.putExtra("Ing3", ing3);
                                i.putExtra("all_ing", all_ing);
                                i.putExtra("recipie", recipie);
                                startActivity(i);
            }
        });
        builderSingle.show();

//        Intent i=new Intent(MainActivity.this,Sorted_List.class);
//
//        i.putExtra("array_list",rushabh);
//
//        startActivity(i);
//        mems.close();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
