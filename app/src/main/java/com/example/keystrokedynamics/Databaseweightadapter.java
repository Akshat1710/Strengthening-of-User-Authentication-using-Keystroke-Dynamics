package com.example.keystrokedynamics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by parth on 20-02-2016.
 */
public class Databaseweightadapter extends BaseAdapter {
    String [] wh1,wh2,wh3,wh4,wh5,wh6,wh7,wh8,error;
    LayoutInflater inflater;
    Context con;
    public Databaseweightadapter(String[] wh1,String [] wh2,String [] wh3,String [] wh4,
                                 String[] wh5,String [] wh6,String [] wh7,String [] wh8,
                                 String[] error, Context con) {

        this.wh1=wh1;
        this.wh2=wh2;
        this.wh3=wh3;
        this.wh4=wh4;
        this.wh5=wh5;
        this.wh6=wh6;
        this.wh7=wh7;
        this.wh8=wh8;
        this.error=error;
        this.con=con;
        inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wh1.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        View r=inflater.inflate(R.layout.show_weight_adapter,null);
        t1=(TextView)r.findViewById(R.id.textView);
        t2=(TextView)r.findViewById(R.id.textView5);
        t3=(TextView)r.findViewById(R.id.textView6);
        t4=(TextView)r.findViewById(R.id.textView10);
        t5=(TextView)r.findViewById(R.id.textView7);
        t6=(TextView)r.findViewById(R.id.textView12);
        t7=(TextView)r.findViewById(R.id.textView11);
        t8=(TextView)r.findViewById(R.id.textView13);
        t9=(TextView)r.findViewById(R.id.textView9);
        /*t10=(TextView)r.findViewById(R.id.textView8); */
        t1.setText("wh11="+wh1[position]);
        t2.setText("wh21="+wh2[position]);
        t3.setText("wh31="+wh3[position]);
        t4.setText("wh41="+wh4[position]);
        t5.setText("wh51="+wh5[position]);
        t6.setText("wh61="+wh6[position]);
        t7.setText("wh71="+wh7[position]);
        t8.setText("wh81="+wh8[position]);
        t9.setText("Error="+error[position]);
        /*t10.setText("wh101="+wh1[position]);*/




        return r;
    }
}
