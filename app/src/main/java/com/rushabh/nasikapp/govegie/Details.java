package com.rushabh.nasikapp.govegie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    String name,ing1,ing2,ing3,all_ing,recipie;
    Toolbar toolbar;
    TextView r,ing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ing= (TextView)findViewById(R.id.ingredients);
        r=(TextView)findViewById(R.id.recipie);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i=getIntent();
        name=i.getStringExtra("name");
        ing1=i.getStringExtra("ing1");
        ing2=i.getStringExtra("ing2");
        ing3=i.getStringExtra("ing3");
        all_ing=i.getStringExtra("all_ing");
        recipie=i.getStringExtra("recipie");

        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

        ing.setText(all_ing);
        getSupportActionBar().setTitle(name);
        r.setText(recipie);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(
                r,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        );
        TextViewCompat.setAutoSizeTextTypeWithDefaults(
                ing,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

}
