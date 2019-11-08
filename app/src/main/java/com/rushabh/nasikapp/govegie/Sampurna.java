package com.rushabh.nasikapp.govegie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sampurna extends AppCompatActivity {
    ListView listView;
    ArrayList<String> khana = new ArrayList<>();
    String name;
    String ing1;
    String ing2;
    String ing3;
    String all_ing;
    String recipie;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampurna);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = (ListView) findViewById(R.id.listView);

        displayResultList();
    }

    private void displayResultList() {
        try {
            Food_Database dbHelper = new Food_Database(Sampurna.this);
            SQLiteDatabase newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("select * from foodie", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        name = c.getString(1);
                        khana.add(name);
                    } while (c.moveToNext());
                }
                c.close();
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        // a listát berakni aegy arraylist-be
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, R.id.textView,
                khana) {

            public View getView(int pos, View convertView, android.view.ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_layout, null);
                }
                TextView tv = (TextView) v.findViewById(R.id.textView);
                tv.setText(khana.get(pos));
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Crimson-Roman.ttf");
                tv.setTypeface(font);
                return v;
            }
        };

        listView.setAdapter(adapter);// clear items
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sampurna.this, ""+name, Toast.LENGTH_SHORT).show();
                Food_Database dbHelper = new Food_Database(Sampurna.this);
                SQLiteDatabase newDB = dbHelper.getWritableDatabase();
                Cursor c = newDB.rawQuery("select * from foodie where name='" + name+"'", null);
                if (c != null) {
                    if (c.moveToFirst()) {
                        name = c.getString(1);
                        ing1 = c.getString(2);
                        ing2 = c.getString(3);
                        ing3 = c.getString(4);
                        all_ing = c.getString(5);
                        recipie = c.getString(6);
                    }
//                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), image);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                     byteArray= stream.toByteArray();
                }
                Intent i = new Intent(Sampurna.this, Details.class);
                i.putExtra("Id", id);
                i.putExtra("name", name);
                i.putExtra("ing1", ing1);
                i.putExtra("Ing2", ing2);
                i.putExtra("Ing3", ing3);
                i.putExtra("all_ing", all_ing);
                i.putExtra("recipie", recipie);
//                i.putExtra("picture", byteArray);
                startActivity(i);
            }
        });
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
