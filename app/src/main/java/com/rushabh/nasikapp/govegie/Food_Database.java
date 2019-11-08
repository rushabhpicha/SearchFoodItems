package com.rushabh.nasikapp.govegie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Food_Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "Item.db";
    private static final int DB_VERSION = 47;
    private Context myContext;

    public Food_Database(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
        this.myContext = ctx;
    }

    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS foodie");
            createTables(db);
        }
    }
    public void createTables(SQLiteDatabase db)
    {
        try {
            System.out.print("Table Created");
            Toast.makeText(myContext, "skdjcnskcjnsdcsocnslidncscn", Toast.LENGTH_SHORT).show();
            db.execSQL("create table foodie (id integer primary key autoincrement,name text,Ingredient1 text,Ingredient2 text,Ingredient3 text,Ingredient4 text,Ingredient5 text,All_Ingredients text,Recipie text);");
            db.execSQL("insert into foodie values(1,'Khandvi','Besan(Bengal gram flour)','curd','lemon juice','a','b','1 cup besan (bengal gram flour)\n" +
                    "1 cup fresh curd (dahi)" +
                    "2 to 3 drops of lemon juice\n" +
                    "2 tsp ginger-green chilli paste\n" +
                    "1/4 tsp turmeric powder (haldi)\n" +
                    "a pinch of asafoetida (hing)\n" +
                    "salt to taste\n" +
                    "3 1/4 tsp oil\n" +
                    "1 tsp mustard seeds ( rai / sarson)\n" +
                    "1/4 tsp asafoetida (hing)\n" +
                    "5 to 6 curry leaves (kadi patta)\n" +
                    "1/2 tsp finely chopped green chillies\n','Mix the curds with 1 1/2 cups of water.\n" +
                    "Combine the besan, curds-water mixture, lemon juice, ginger-green chilli paste, turmeric powder, asafoetida and salt in a deep non-stick pan and mix well to make a smooth batter (taking care that no lump remains).\n" +
                    "Cook on a slow flame, while stirring continuously till it becomes thick (approx. 8 to 10 minutes).\n" +
                    "Grease a thali (approx. 10”) on the reverse side using ¼ tsp of oil and spread a spoonful of the batter on a greased thali and wait for a few seconds and try to roll up. If it doesn’t, then cook for a few more minutes, and check once more till done.\n" +
                    "Divide the batter into two equal portions. While the batter is still hot, spread each portion evenly using a spatula, on the reverse greased side of 2 thalis evenly to make a thin uniform layer using a spatula (refer step 1).\n" +
                    "When cool, cut the khandvi on each thali lengthwise (approx. 1½\" in width) into equal portions (as shown in the image) and roll up each gently (refer to step 2 and 3).\n" +
                    "For the tempering, just before serving heat the remaining 3 tsp oil in a small non-stick pan and add the mustard seeds.\n" +
                    "When the seeds crackle, add the asafoetida, curry leaves and green chillies and sauté on a medium flame for a few seconds.\n" +
                    "Pour the tempering over the khandvis.\n" +
                    "Serve immediately garnished with coconut and coriander.')");
//            db.execSQL("insert into foodie values(2,'Jain Cheese Balls','Processed Cheese','Raw Banana','Processed cheese 1 cup grated + for garnishing"+
//            "Raw banana boiled, peeled and mashed 1 medium"+
//            "Green capsicum finely chopped ¼ cup"+
//            "Red chilli flakes 1 teaspoon"+
//            "Cornflour/ corn starch 2-3 tablespoons"+
//            "Refined flour (maida) ¼ cup"+
//            "Brown bread crumbs for coating"+
//            "Mix cheese, banana, capsicum, pepper powder, chilli flakes, oregano, salt in a bowl. Add coriander and cornflour and mix well."+
//            "Divide the mixture into equal portions, shape them into balls."+
//            "Heat sufficient oil in a kadai."+
//            "To make slurry, mix refined flour and some water in a bowl."+
//            "Spread breadcrumbs on a plate."+
//            "Dip the balls in slurry, coat them in breadcrumbs and deep-fry in hot oil till brown and crisp. Drain on an absorbent paper"+
//            "Serve hot with cheese and coriander.')");

            db.execSQL("insert into foodie values(3, 'Pineapple Panna','Fresh pineapple', 'Pineapple juice', 'Castor sugar (caster sugar) 2 teaspoons 2 tablespoons', 'Fresh mint leaves 15-20', 'Black peppercorns 5-6', 'Roasted cumin seeds 1 teaspoon,Lemon juice ½ teaspoon,Salt to taste,Ginger finely chopped ½ inch,Black salt (kala namak) to taste', 'Heat a non-stick grill pan. Place pineapple slices on it, sprinkle 2 teaspoons castor sugar on top and grill till they turn soft. Remove from heat and cool to room temperature,Place grilled pineapple slices in a blender jar. Add mint leaves, peppercorns, cumin seeds, lemon juice, salt, ginger, black salt, 2 tablespoons castor sugar and pineapple juice and blend well,Pour into individual glasses and serve chilled')");
            System.out.print("Table Created");
            Toast.makeText(myContext, "Waaah", Toast.LENGTH_SHORT).show();
            Log.d("kjj", "Table Created");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<String> getAllData(){
        List<String> items = new ArrayList<String>();
        String select="select Ingredient1,Ingredient2,Ingredient3,Ingredient4,Ingredient5 from foodie";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    String data1 = cursor.getString(0);
                    String data2=cursor.getString(1);
                    String data3=cursor.getString(2);
                    String data4=cursor.getString(3);
                    String data5=cursor.getString(4);
                    items.add("" + data1);
                    items.add("" +data2);
                    items.add("" +data3);
                    items.add("" +data4);
                    items.add("" +data5);
                } while (cursor.moveToNext());
            }
        }
//        if(cursor!=null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    String levelData = cursor.getString(3);
//                    items.add("" + levelData);
//                } while (cursor.moveToNext());
//            }
//        }
//        if(cursor!=null){
//            if(cursor.moveToFirst()) {
//                do {
//                    String levelData = cursor.getString(4);
//                    items.add("" + levelData);
//                } while (cursor.moveToNext());
//            }
//        }
        cursor.close();
        db.close();
        return items;
    }
}

