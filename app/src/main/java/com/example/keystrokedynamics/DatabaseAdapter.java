

package com.example.keystrokedynamics;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DatabaseAdapter extends BaseAdapter {
    String [] b1_x,b1_y,b2_x,b2_y,b3_x,b3_y,b4_x,b4_y,b5_x,b5_y,b6_x,b6_y,
            b1_size, b2_size, b3_size, b4_size, b5_size, b6_size,
            p1_press,p2_press,p3_press,p4_press,p5_press,p6_press,
            b1_pressure, b2_pressure, b3_pressure, b4_pressure, b5_pressure, b6_pressure,
            p1_p2_flight,p2_p3_flight,p3_p4_flight,p4_p5_flight,p5_p6_flight,
            p1_p2_trigraph,p2_p3_trigraph,p3_p4_trigraph,p4_p5_trigraph,
            p1_p2_digraph,p2_p3_digraph,p3_p4_digraph,p4_p5_digraph,p5_p6_digraph,
            total_time;
    LayoutInflater inflater;
    Context con;
    DatabaseAdapter(
                    String [] b1_x,String [] b1_y,
                    String [] b2_x,String [] b2_y,
                    String [] b3_x,String [] b3_y,
                    String [] b4_x,String [] b4_y,
                    String [] b5_x,String [] b5_y,
                    String [] b6_x,String [] b6_y,
                    String [] b1_size,
                    String [] b2_size,String [] b3_size,
                    String [] b4_size,String [] b5_size,
                    String [] b6_size,
                    String [] p1_press,String [] p2_press,
                    String [] p3_press,String [] p4_press,
                    String [] p5_press,String [] p6_press,
                    String [] b1_pressure,
                    String [] b2_pressure,String [] b3_pressure,
                    String [] b4_pressure,String [] b5_pressure,
                    String [] b6_pressure,
                    String [] p1_p2_flight,String [] p2_p3_flight,String [] p3_p4_flight,String [] p4_p5_flight,String [] p5_p6_flight,
                    String [] p1_p2_trigraph,String [] p2_p3_trigraph,String [] p3_p4_trigraph,String [] p4_p5_trigraph,String []
                    p1_p2_digraph,String [] p2_p3_digraph,String [] p3_p4_digraph,String [] p4_p5_digraph,String [] p5_p6_digraph,String []
                    total_time, Context con){

        this.b1_x=b1_x;
        this.b1_y=b1_y;
        this.b2_x=b2_x;
        this.b2_y=b2_y;
        this.b3_x=b3_x;
        this.b3_y=b3_y;
        this.b4_x=b4_x;
        this.b4_y=b4_y;
        this.b5_x=b5_x;
        this.b5_y=b5_y;
        this.b6_x=b6_x;
        this.b6_y=b6_y;
        this.b1_size=b1_size;
        this.b2_size=b2_size;
        this.b3_size=b3_size;
        this.b4_size=b4_size;
        this.b5_size=b5_size;
        this.b6_size=b6_size;

        this.p1_press=p1_press;
        this.p2_press=p2_press;
        this.p3_press=p3_press;
        this.p4_press=p4_press;
        this.p5_press=p5_press;
        this.p6_press=p6_press;

        this.b1_pressure=b1_pressure;
        this.b2_pressure=b2_pressure;
        this.b3_pressure=b3_pressure;
        this.b4_pressure=b4_pressure;
        this.b5_pressure=b5_pressure;
        this.b6_pressure=b6_pressure;

        this.p1_p2_flight=p1_p2_flight;
        this.p2_p3_flight=p2_p3_flight;
        this.p3_p4_flight=p3_p4_flight;
        this.p4_p5_flight=p4_p5_flight;
        this.p5_p6_flight=p5_p6_flight;
        this.p1_p2_trigraph =p1_p2_trigraph ;
        this.p2_p3_trigraph =p2_p3_trigraph ;
        this.p3_p4_trigraph =p3_p4_trigraph ;
        this.p4_p5_trigraph =p4_p5_trigraph ;
        this.p1_p2_digraph=p1_p2_digraph;
        this.p2_p3_digraph=p2_p3_digraph;
        this.p3_p4_digraph=p3_p4_digraph;
        this.p4_p5_digraph=p4_p5_digraph;
        this.p5_p6_digraph=p5_p6_digraph;

        this.total_time=total_time;
        this.con=con;
        inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return b1_x.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View cV, ViewGroup p) {
        TextView b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,testnumber,pt,ft,vad,kuku,kdkd,trigraph,tkuku,tkdkd;
        View r=inflater.inflate(R.layout.show,null);

        b1=(TextView)r.findViewById(R.id.b1);
        b2=(TextView)r.findViewById(R.id.b2);
        b3=(TextView)r.findViewById(R.id.b3);
        b4=(TextView)r.findViewById(R.id.b4);
        b5=(TextView)r.findViewById(R.id.b5);
        b6=(TextView)r.findViewById(R.id.b6);

        testnumber=(TextView)r.findViewById(R.id.text);
        pt=(TextView)r.findViewById(R.id.text_press);
        ft=(TextView)r.findViewById(R.id.text_flight);
        vad=(TextView)r.findViewById(R.id.text_vad);
        trigraph=(TextView)r.findViewById(R.id.t);

        //b0.setText("Button 0 : ( "+b0_x[pos]+" , "+b0_y[pos]+" ) , \nSize : "+b0_size[pos]+" , \nPressure : "+b0_pressure[pos]);
        b1.setText("Button 1 : ( "+b1_x[pos]+" , "+b1_y[pos]+" ) , \nSize : "+b1_size[pos]+" , \nPressure : "+b1_pressure[pos]);
        b2.setText("Button 2 : ( "+b2_x[pos]+" , "+b2_y[pos]+" ) , \nSize : "+b2_size[pos]+" , \nPressure : "+b2_pressure[pos]);
        b3.setText("Button 3 : ( "+b3_x[pos]+" , "+b3_y[pos]+" ) , \nSize : "+b3_size[pos]+" , \nPressure : "+b3_pressure[pos]);
        b4.setText("Button 4 : ( "+b4_x[pos]+" , "+b4_y[pos]+" ) , \nSize : "+b4_size[pos]+" , \nPressure : "+b4_pressure[pos]);
        b5.setText("Button 5 : ( "+b5_x[pos]+" , "+b5_y[pos]+" ) , \nSize : "+b5_size[pos]+" , \nPressure : "+b5_pressure[pos]);
        b6.setText("Button 6 : ( "+b6_x[pos]+" , "+b6_y[pos]+" ) , \nSize : "+b6_size[pos]+" , \nPressure : "+b6_pressure[pos]);
        /*b7.setText("Button 7 : ( "+b7_x[pos]+" , "+b7_y[pos]+" ) , \nSize : "+b7_size[pos]+" , \nPressure : "+b7_pressure[pos]);
        b8.setText("Button 8 : ( "+b8_x[pos]+" , "+b8_y[pos]+" ) , \nSize : "+b8_size[pos]+" , \nPressure : "+b8_pressure[pos]);
        b9.setText("Button 9 : ( "+b8_x[pos]+" , "+b9_y[pos]+" ) , \nSize : "+b9_size[pos]+" , \nPressure : "+b9_pressure[pos]);
        */
        pt.setText("Press Time : "+p1_press[pos]+" , "+p2_press[pos]+" , "+p3_press[pos]+" , "+p4_press[pos]+" , "+p5_press[pos]+" , "+p6_press[pos]);
        ft.setText("Flight Time : "+p1_p2_flight[pos]+" , "+p2_p3_flight[pos]+" , "+p3_p4_flight[pos]+" , "+p4_p5_flight[pos]+" , "+p5_p6_flight[pos]);
        /*kuku.setText("Key Up-Key Up Digraph Time : "+p1_p2_keyup_keyup[pos]+" , "+p2_p3_keyup_keyup[pos]+" , "+p3_p4_keyup_keyup[pos]+" , "+p4_p5_keyup_keyup[pos]+" , "+p5_p6_keyup_keyup[pos]);
        kdkd.setText("Key down-Key down Digraph Time : "+p1_p2_keydown_keydown[pos]+" , "+p2_p3_keydown_keydown[pos]+" , "+p3_p4_keydown_keydown[pos]+" , "+p4_p5_keydown_keydown[pos]+" , "+p5_p6_keydown_keydown[pos]);
        */
        vad.setText("Digraph Time : "+p1_p2_digraph[pos]+" , "+p2_p3_digraph[pos]+" , "+p3_p4_digraph[pos]+" , "+p4_p5_digraph[pos]+" , "+p5_p6_digraph[pos]+" , ");
        /*tkuku.setText("Keyup-Keyup Trigraph Time : "+p1_p2_kuku_trigraph[pos]+" , "+p2_p3_kuku_trigraph[pos]+" , "+p3_p4_kuku_trigraph[pos]+" , "+p4_p5_kuku_trigraph[pos]+"  ");
        tkdkd.setText("Keydown-Keydown Trigraph Time : "+p1_p2_kdkd_trigraph[pos]+" , "+p2_p3_kdkd_trigraph[pos]+" , "+p3_p4_kdkd_trigraph[pos]+" , "+p4_p5_kdkd_trigraph[pos]+"  ");
        */
        trigraph.setText("Trigraph Time : "+p1_p2_trigraph[pos]+" , "+p2_p3_trigraph[pos]+" , "+p3_p4_trigraph[pos]+" , "+p4_p5_trigraph[pos]+"  ");
        testnumber.setText("#TestCase "+(total_time.length-pos) +" Total Time : "+ total_time[pos]);
        return r;
    }
}
