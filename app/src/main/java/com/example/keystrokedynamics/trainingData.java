package com.example.keystrokedynamics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

public class trainingData {
    Context con;
    double [] newinputset_add1,newinputset_add2,newinputset_sub1,newinputset_sub2, newinputset;
    public trainingData(Context con){
        this.con=con;
    }
    public void generateLegitimateDataset(){
        DBHelper mydbhelper=new DBHelper(con,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        String []col=new String[]{
                Database.b1_x,Database.b1_y,
                Database.b2_x,Database.b2_y,
                Database.b3_x,Database.b3_y,
                Database.b4_x,Database.b4_y,
                Database.b5_x,Database.b5_y,
                Database.b6_x,Database.b6_y,
                Database.b1_size, Database.b2_size,
                Database.b3_size, Database.b4_size,
                Database.b5_size, Database.b6_size,
                Database.b1_pressure,Database.b2_pressure,
                Database.b3_pressure,Database.b4_pressure,
                Database.b5_pressure,Database.b6_pressure,
                Database.p1_press,Database.p2_press,
                Database.p3_press,Database.p4_press,
                Database.p5_press,Database.p6_press,
                Database.p1_p2_flight,
                Database.p2_p3_flight,
                Database.p3_p4_flight,
                Database.p4_p5_flight,
                Database.p5_p6_flight,
                Database.p1_p2_trigraph,
                Database.p2_p3_trigraph,
                Database.p3_p4_trigraph,
                Database.p4_p5_trigraph,
                Database.p1_p2_digraph,
                Database.p2_p3_digraph,
                Database.p3_p4_digraph,
                Database.p4_p5_digraph,
                Database.p5_p6_digraph,
                Database.total_time,


        };
        
        Cursor c=mydatabase.query(Database.TABLE_NAME,col,null,null,null,null,null);

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()) {
            newinputset_add1 = new double[45];
            newinputset_sub1 = new double[45];
            newinputset_add2 = new double[45];
            newinputset_sub2 = new double[45];
            newinputset = new double[45];
            Random random = new Random();
            int add_sub;
            for(int j=0,i=0,q=0,k=0;j<45;j++,i++,k++,q++) {
                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_add1[i] = Double.parseDouble(c.getString(q)) + (0.02 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_add1[i] = Double.parseDouble(c.getString(q)) - (0.02 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_sub1[i] = Double.parseDouble(c.getString(q)) - (0.03 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_sub1[i] = Double.parseDouble(c.getString(q)) + (0.03 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_add2[i] = Double.parseDouble(c.getString(q)) + (0.4 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_add2[i] = Double.parseDouble(c.getString(q)) - (0.4 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_sub2[i] = Double.parseDouble(c.getString(q)) - (0.05 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_sub2[i] = Double.parseDouble(c.getString(q)) + (0.05 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset[i] = Double.parseDouble(c.getString(q)) + (0.01 * Double.parseDouble(c.getString(k)));
                else
                    newinputset[i] = Double.parseDouble(c.getString(q)) - (0.01 * Double.parseDouble(c.getString(k)));

            }
            putNewData();
        }
    }

    private void putNewData() {
        DBHelper mydbhelper = new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int i=0;
        cv.put(Database.b1_x, newinputset_add1[i++]);
        cv.put(Database.b1_y, newinputset_add1[i++]);
        cv.put(Database.b2_x, newinputset_add1[i++]);
        cv.put(Database.b2_y, newinputset_add1[i++]);
        cv.put(Database.b3_x, newinputset_add1[i++]);
        cv.put(Database.b3_y, newinputset_add1[i++]);
        cv.put(Database.b4_x, newinputset_add1[i++]);
        cv.put(Database.b4_y, newinputset_add1[i++]);
        cv.put(Database.b5_x, newinputset_add1[i++]);
        cv.put(Database.b5_y, newinputset_add1[i++]);
        cv.put(Database.b6_x, newinputset_add1[i++]);
        cv.put(Database.b6_y, newinputset_add1[i++]);
        cv.put(Database.b1_size, newinputset_add1[i++]);
        cv.put(Database.b2_size, newinputset_add1[i++]);
        cv.put(Database.b3_size, newinputset_add1[i++]);
        cv.put(Database.b4_size, newinputset_add1[i++]);
        cv.put(Database.b5_size, newinputset_add1[i++]);
        cv.put(Database.b6_size, newinputset_add1[i++]);
        cv.put(Database.p1_press, newinputset_add1[i++]);
        cv.put(Database.p2_press, newinputset_add1[i++]);
        cv.put(Database.p3_press, newinputset_add1[i++]);
        cv.put(Database.p4_press, newinputset_add1[i++]);
        cv.put(Database.p5_press, newinputset_add1[i++]);
        cv.put(Database.p6_press, newinputset_add1[i++]);
        cv.put(Database.b1_pressure, newinputset_add1[i++]);
        cv.put(Database.b2_pressure, newinputset_add1[i++]);
        cv.put(Database.b3_pressure, newinputset_add1[i++]);
        cv.put(Database.b4_pressure, newinputset_add1[i++]);
        cv.put(Database.b5_pressure, newinputset_add1[i++]);
        cv.put(Database.b6_pressure, newinputset_add1[i++]);
        cv.put(Database.p1_p2_flight, newinputset_add1[i++]);
        cv.put(Database.p2_p3_flight, newinputset_add1[i++]);
        cv.put(Database.p3_p4_flight, newinputset_add1[i++]);
        cv.put(Database.p4_p5_flight, newinputset_add1[i++]);
        cv.put(Database.p5_p6_flight, newinputset_add1[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_add1[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_add1[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_add1[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_add1[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_add1[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_add1[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_add1[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_add1[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_add1[i++]);
        cv.put(Database.total_time, newinputset_add1[i++]);

        long r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_sub1[i++]);
        cv.put(Database.b1_y, newinputset_sub1[i++]);
        cv.put(Database.b2_x, newinputset_sub1[i++]);
        cv.put(Database.b2_y, newinputset_sub1[i++]);
        cv.put(Database.b3_x, newinputset_sub1[i++]);
        cv.put(Database.b3_y, newinputset_sub1[i++]);
        cv.put(Database.b4_x, newinputset_sub1[i++]);
        cv.put(Database.b4_y, newinputset_sub1[i++]);
        cv.put(Database.b5_x, newinputset_sub1[i++]);
        cv.put(Database.b5_y, newinputset_sub1[i++]);
        cv.put(Database.b6_x, newinputset_sub1[i++]);
        cv.put(Database.b6_y, newinputset_sub1[i++]);
        cv.put(Database.b1_size, newinputset_sub1[i++]);
        cv.put(Database.b2_size, newinputset_sub1[i++]);
        cv.put(Database.b3_size, newinputset_sub1[i++]);
        cv.put(Database.b4_size, newinputset_sub1[i++]);
        cv.put(Database.b5_size, newinputset_sub1[i++]);
        cv.put(Database.b6_size, newinputset_sub1[i++]);
        cv.put(Database.p1_press, newinputset_sub1[i++]);
        cv.put(Database.p2_press, newinputset_sub1[i++]);
        cv.put(Database.p3_press, newinputset_sub1[i++]);
        cv.put(Database.p4_press, newinputset_sub1[i++]);
        cv.put(Database.p5_press, newinputset_sub1[i++]);
        cv.put(Database.p6_press, newinputset_sub1[i++]);
        cv.put(Database.b1_pressure, newinputset_sub1[i++]);
        cv.put(Database.b2_pressure, newinputset_sub1[i++]);
        cv.put(Database.b3_pressure, newinputset_sub1[i++]);
        cv.put(Database.b4_pressure, newinputset_sub1[i++]);
        cv.put(Database.b5_pressure, newinputset_sub1[i++]);
        cv.put(Database.b6_pressure, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_flight, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_flight, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_flight, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_flight, newinputset_sub1[i++]);
        cv.put(Database.p5_p6_flight, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_sub1[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_sub1[i++]);
        cv.put(Database.total_time, newinputset_sub1[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_add2[i++]);
        cv.put(Database.b1_y, newinputset_add2[i++]);
        cv.put(Database.b2_x, newinputset_add2[i++]);
        cv.put(Database.b2_y, newinputset_add2[i++]);
        cv.put(Database.b3_x, newinputset_add2[i++]);
        cv.put(Database.b3_y, newinputset_add2[i++]);
        cv.put(Database.b4_x, newinputset_add2[i++]);
        cv.put(Database.b4_y, newinputset_add2[i++]);
        cv.put(Database.b5_x, newinputset_add2[i++]);
        cv.put(Database.b5_y, newinputset_add2[i++]);
        cv.put(Database.b6_x, newinputset_add2[i++]);
        cv.put(Database.b6_y, newinputset_add2[i++]);
        cv.put(Database.b1_size, newinputset_add2[i++]);
        cv.put(Database.b2_size, newinputset_add2[i++]);
        cv.put(Database.b3_size, newinputset_add2[i++]);
        cv.put(Database.b4_size, newinputset_add2[i++]);
        cv.put(Database.b5_size, newinputset_add2[i++]);
        cv.put(Database.b6_size, newinputset_add2[i++]);
        cv.put(Database.p1_press, newinputset_add2[i++]);
        cv.put(Database.p2_press, newinputset_add2[i++]);
        cv.put(Database.p3_press, newinputset_add2[i++]);
        cv.put(Database.p4_press, newinputset_add2[i++]);
        cv.put(Database.p5_press, newinputset_add2[i++]);
        cv.put(Database.p6_press, newinputset_add2[i++]);
        cv.put(Database.b1_pressure, newinputset_add2[i++]);
        cv.put(Database.b2_pressure, newinputset_add2[i++]);
        cv.put(Database.b3_pressure, newinputset_add2[i++]);
        cv.put(Database.b4_pressure, newinputset_add2[i++]);
        cv.put(Database.b5_pressure, newinputset_add2[i++]);
        cv.put(Database.b6_pressure, newinputset_add2[i++]);
        cv.put(Database.p1_p2_flight, newinputset_add2[i++]);
        cv.put(Database.p2_p3_flight, newinputset_add2[i++]);
        cv.put(Database.p3_p4_flight, newinputset_add2[i++]);
        cv.put(Database.p4_p5_flight, newinputset_add2[i++]);
        cv.put(Database.p5_p6_flight, newinputset_add2[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_add2[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_add2[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_add2[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_add2[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_add2[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_add2[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_add2[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_add2[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_add2[i++]);
        cv.put(Database.total_time, newinputset_add2[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_sub2[i++]);
        cv.put(Database.b1_y, newinputset_sub2[i++]);
        cv.put(Database.b2_x, newinputset_sub2[i++]);
        cv.put(Database.b2_y, newinputset_sub2[i++]);
        cv.put(Database.b3_x, newinputset_sub2[i++]);
        cv.put(Database.b3_y, newinputset_sub2[i++]);
        cv.put(Database.b4_x, newinputset_sub2[i++]);
        cv.put(Database.b4_y, newinputset_sub2[i++]);
        cv.put(Database.b5_x, newinputset_sub2[i++]);
        cv.put(Database.b5_y, newinputset_sub2[i++]);
        cv.put(Database.b6_x, newinputset_sub2[i++]);
        cv.put(Database.b6_y, newinputset_sub2[i++]);
        cv.put(Database.b1_size, newinputset_sub2[i++]);
        cv.put(Database.b2_size, newinputset_sub2[i++]);
        cv.put(Database.b3_size, newinputset_sub2[i++]);
        cv.put(Database.b4_size, newinputset_sub2[i++]);
        cv.put(Database.b5_size, newinputset_sub2[i++]);
        cv.put(Database.b6_size, newinputset_sub2[i++]);
        cv.put(Database.p1_press, newinputset_sub2[i++]);
        cv.put(Database.p2_press, newinputset_sub2[i++]);
        cv.put(Database.p3_press, newinputset_sub2[i++]);
        cv.put(Database.p4_press, newinputset_sub2[i++]);
        cv.put(Database.p5_press, newinputset_sub2[i++]);
        cv.put(Database.p6_press, newinputset_sub2[i++]);
        cv.put(Database.b1_pressure, newinputset_sub2[i++]);
        cv.put(Database.b2_pressure, newinputset_sub2[i++]);
        cv.put(Database.b3_pressure, newinputset_sub2[i++]);
        cv.put(Database.b4_pressure, newinputset_sub2[i++]);
        cv.put(Database.b5_pressure, newinputset_sub2[i++]);
        cv.put(Database.b6_pressure, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_flight, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_flight, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_flight, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_flight, newinputset_sub2[i++]);
        cv.put(Database.p5_p6_flight, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_sub2[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_sub2[i++]);
        cv.put(Database.total_time, newinputset_sub2[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

    }


    public void additionalData(){
        DBHelper mydbhelper=new DBHelper(con,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        String []col=new String[]{
                DatabaseBackup.b1__x,DatabaseBackup.b1__y,
                DatabaseBackup.b2__x,DatabaseBackup.b2__y,
                DatabaseBackup.b3__x,DatabaseBackup.b3__y,
                DatabaseBackup.b4__x,DatabaseBackup.b4__y,
                DatabaseBackup.b5__x,DatabaseBackup.b5__y,
                DatabaseBackup.b6__x,DatabaseBackup.b6__y,
                DatabaseBackup.b1__size, DatabaseBackup.b2__size,
                DatabaseBackup.b3__size, DatabaseBackup.b4__size,
                DatabaseBackup.b5__size, DatabaseBackup.b6__size,
                DatabaseBackup.b1__pressure,DatabaseBackup.b2__pressure,
                DatabaseBackup.b3__pressure,DatabaseBackup.b4__pressure,
                DatabaseBackup.b5__pressure,DatabaseBackup.b6__pressure,
                DatabaseBackup.p1__press,DatabaseBackup.p2__press,
                DatabaseBackup.p3__press,DatabaseBackup.p4__press,
                DatabaseBackup.p5__press,DatabaseBackup.p6__press,
                DatabaseBackup.p1__p2__flight,
                DatabaseBackup.p2__p3__flight,
                DatabaseBackup.p3__p4__flight,
                DatabaseBackup.p4__p5__flight,
                DatabaseBackup.p5__p6__flight,
                DatabaseBackup.p1__p2__trigraph,
                DatabaseBackup.p2__p3__trigraph,
                DatabaseBackup.p3__p4__trigraph,
                DatabaseBackup.p4__p5__trigraph,
                DatabaseBackup.p1__p2__digraph,
                DatabaseBackup.p2__p3__digraph,
                DatabaseBackup.p3__p4__digraph,
                DatabaseBackup.p4__p5__digraph,
                DatabaseBackup.p5__p6__digraph,
                DatabaseBackup.total__time,


        };

        Cursor c=mydatabase.query(DatabaseBackup.TABLE__NAME,col,null,null,null,null,null);
        mydatabase.execSQL("delete from "+ Database.TABLE_NAME);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {


                newinputset = new double[45];
                Random random = new Random();
                int add_sub;
                for (int j = 0, i = 0, q = 0, k = 0; j < 45; j++, i++, k++, q++) {
                    add_sub = random.nextInt(2);
                    if (add_sub == 1)
                        newinputset[i] = Double.parseDouble(c.getString(q)) + (0.03 * Double.parseDouble(c.getString(k)));
                    else
                        newinputset[i] = Double.parseDouble(c.getString(q)) - (0.03 * Double.parseDouble(c.getString(k)));
                }
            ContentValues cv = new ContentValues();
            int i=0;
            cv.put(Database.b1_x, newinputset[i++]);
            cv.put(Database.b1_y, newinputset[i++]);
            cv.put(Database.b2_x, newinputset[i++]);
            cv.put(Database.b2_y, newinputset[i++]);
            cv.put(Database.b3_x, newinputset[i++]);
            cv.put(Database.b3_y, newinputset[i++]);
            cv.put(Database.b4_x, newinputset[i++]);
            cv.put(Database.b4_y, newinputset[i++]);
            cv.put(Database.b5_x, newinputset[i++]);
            cv.put(Database.b5_y, newinputset[i++]);
            cv.put(Database.b6_x, newinputset[i++]);
            cv.put(Database.b6_y, newinputset[i++]);
            cv.put(Database.b1_size, newinputset[i++]);
            cv.put(Database.b2_size, newinputset[i++]);
            cv.put(Database.b3_size, newinputset[i++]);
            cv.put(Database.b4_size, newinputset[i++]);
            cv.put(Database.b5_size, newinputset[i++]);
            cv.put(Database.b6_size, newinputset[i++]);
            cv.put(Database.p1_press, newinputset[i++]);
            cv.put(Database.p2_press, newinputset[i++]);
            cv.put(Database.p3_press, newinputset[i++]);
            cv.put(Database.p4_press, newinputset[i++]);
            cv.put(Database.p5_press, newinputset[i++]);
            cv.put(Database.p6_press, newinputset[i++]);
            cv.put(Database.b1_pressure, newinputset[i++]);
            cv.put(Database.b2_pressure, newinputset[i++]);
            cv.put(Database.b3_pressure, newinputset[i++]);
            cv.put(Database.b4_pressure, newinputset[i++]);
            cv.put(Database.b5_pressure, newinputset[i++]);
            cv.put(Database.b6_pressure, newinputset[i++]);
            cv.put(Database.p1_p2_flight, newinputset[i++]);
            cv.put(Database.p2_p3_flight, newinputset[i++]);
            cv.put(Database.p3_p4_flight, newinputset[i++]);
            cv.put(Database.p4_p5_flight, newinputset[i++]);
            cv.put(Database.p5_p6_flight, newinputset[i++]);
            cv.put(Database.p1_p2_trigraph, newinputset[i++]);
            cv.put(Database.p2_p3_trigraph, newinputset[i++]);
            cv.put(Database.p3_p4_trigraph, newinputset[i++]);
            cv.put(Database.p4_p5_trigraph, newinputset[i++]);
            cv.put(Database.p1_p2_digraph, newinputset[i++]);
            cv.put(Database.p2_p3_digraph, newinputset[i++]);
            cv.put(Database.p3_p4_digraph, newinputset[i++]);
            cv.put(Database.p4_p5_digraph, newinputset[i++]);
            cv.put(Database.p5_p6_digraph, newinputset[i++]);
            cv.put(Database.total_time, newinputset[i++]);

            long r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        }


    }
}
