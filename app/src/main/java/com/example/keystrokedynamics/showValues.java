package com.example.keystrokedynamics;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class showValues extends AppCompatActivity {
    String [] b1_x,b1_y,b2_x,b2_y,b3_x,b3_y,b4_x,b4_y,b5_x,b5_y,b6_x,b6_y,
            b1_size, b2_size, b3_size, b4_size, b5_size, b6_size,
            p1_press,p2_press,p3_press,p4_press,p5_press,p6_press,
            b1_pressure, b2_pressure, b3_pressure, b4_pressure, b5_pressure, b6_pressure,
            p1_p2_flight,p2_p3_flight,p3_p4_flight,p4_p5_flight,p5_p6_flight,
            p1_p2_digraph,p2_p3_digraph,p3_p4_digraph,p4_p5_digraph,p5_p6_digraph,
            p1_p2_trigraph,p2_p3_trigraph,p3_p4_trigraph,p4_p5_trigraph,
            total_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_values);


        Intent in=getIntent();


        ListView l=(ListView)findViewById(R.id.listView);
        DBHelper mydbhelper=new DBHelper(this,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.rawQuery("select * from " + Database.TABLE_NAME, null);
        int count = cur.getCount();
        b1_x=new String[count];
        b1_y=new String[count];
        b2_x=new String[count];
        b2_y=new String[count];
        b3_x=new String[count];
        b3_y=new String[count];
        b4_x=new String[count];
        b4_y=new String[count];
        b5_x=new String[count];
        b5_y=new String[count];
        b6_x=new String[count];
        b6_y=new String[count];
        b1_size=new String[count];
        b2_size=new String[count];
        b3_size=new String[count];
        b4_size=new String[count];
        b5_size=new String[count];
        b6_size=new String[count];
        p1_press=new String[count];
        p2_press=new String[count];
        p3_press=new String[count];
        p4_press=new String[count];
        p5_press=new String[count];
        p6_press=new String[count];
        b1_pressure=new String[count];
        b2_pressure=new String[count];
        b3_pressure=new String[count];
        b4_pressure=new String[count];
        b5_pressure=new String[count];
        b6_pressure=new String[count];
        p1_p2_flight=new String[count];
        p2_p3_flight=new String[count];
        p3_p4_flight=new String[count];
        p4_p5_flight=new String[count];
        p5_p6_flight=new String[count];
        p1_p2_digraph=new String[count];
        p2_p3_digraph=new String[count];
        p3_p4_digraph=new String[count];
        p4_p5_digraph=new String[count];
        p5_p6_digraph=new String[count];
        p1_p2_trigraph=new String[count];
        p2_p3_trigraph=new String[count];
        p3_p4_trigraph=new String[count];
        p4_p5_trigraph=new String[count];
        total_time=new String[count];

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
        int i=0,q=0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious(),i++) {
            q = 0;
            b1_x[i] = c.getString(q++);
            b1_y[i] = c.getString(q++);
            b2_x[i] = c.getString(q++);
            b2_y[i] = c.getString(q++);
            b3_x[i] = c.getString(q++);
            b3_y[i] = c.getString(q++);
            b4_x[i] = c.getString(q++);
            b4_y[i] = c.getString(q++);
            b5_x[i] = c.getString(q++);
            b5_y[i] = c.getString(q++);
            b6_x[i] = c.getString(q++);
            b6_y[i] = c.getString(q++);
            b1_size[i] = c.getString(q++);
            b2_size[i] = c.getString(q++);
            b3_size[i] = c.getString(q++);
            b4_size[i] = c.getString(q++);
            b5_size[i] = c.getString(q++);
            b6_size[i] = c.getString(q++);
            b1_pressure[i] = c.getString(q++);
            b2_pressure[i] = c.getString(q++);
            b3_pressure[i] = c.getString(q++);
            b4_pressure[i] = c.getString(q++);
            b5_pressure[i] = c.getString(q++);
            b6_pressure[i] = c.getString(q++);
            p1_press[i] = c.getString(q++);
            p2_press[i] = c.getString(q++);
            p3_press[i] = c.getString(q++);
            p4_press[i] = c.getString(q++);
            p5_press[i] = c.getString(q++);
            p6_press[i] = c.getString(q++);
            p1_p2_flight[i] = c.getString(q++);
            p2_p3_flight[i] = c.getString(q++);
            p3_p4_flight[i] = c.getString(q++);
            p4_p5_flight[i] = c.getString(q++);
            p5_p6_flight[i] = c.getString(q++);
            p1_p2_trigraph[i] = c.getString(q++);
            p2_p3_trigraph[i] = c.getString(q++);
            p3_p4_trigraph[i] = c.getString(q++);
            p4_p5_trigraph[i] = c.getString(q++);
            p1_p2_digraph[i] = c.getString(q++);
            p2_p3_digraph[i] = c.getString(q++);
            p3_p4_digraph[i] = c.getString(q++);
            p4_p5_digraph[i] = c.getString(q++);
            p5_p6_digraph[i] = c.getString(q++);
            total_time[i] = c.getString(q++);

        }
        l.setAdapter(new DatabaseAdapter(b1_x,b1_y,b2_x,b2_y,b3_x,b3_y,b4_x,b4_y,b5_x,b5_y,b6_x,b6_y,
                b1_size, b2_size, b3_size, b4_size, b5_size, b6_size,
                p1_press,p2_press,p3_press,p4_press,p5_press,p6_press,
                b1_pressure, b2_pressure, b3_pressure, b4_pressure, b5_pressure, b6_pressure,
                p1_p2_flight,p2_p3_flight,p3_p4_flight,p4_p5_flight,p5_p6_flight,
                p1_p2_trigraph,p2_p3_trigraph,p3_p4_trigraph,p4_p5_trigraph,
                p1_p2_digraph,p2_p3_digraph,p3_p4_digraph,p4_p5_digraph,p5_p6_digraph,
                total_time,showValues.this));

    }

}
