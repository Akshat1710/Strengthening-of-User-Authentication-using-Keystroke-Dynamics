package com.example.keystrokedynamics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Random;

public class trainingActivity {
    Context con;
    double input_layer[];
    int global_legitimate_count, global_illegitimate_count ;
    public trainingActivity(Context con){
        this.con = con;
        global_legitimate_count = 0;

    }
    public trainingActivity(Context con, int c){
        this.con = con;
        global_legitimate_count = c ;

    }
    public void performTraining(){
        DBHelper mydbhelper = new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur_legitimate=mydatabase.rawQuery("SELECT * from " + Database.TABLE_NAME, null);
        int count_legitimate = cur_legitimate.getCount();

        while(count_legitimate != 0 ){
            startLegitimateTraining();
            global_legitimate_count++;
            count_legitimate = count_legitimate - 1;
        }
        String col[]=new String[]{
                DatabaseNetwork.output1,
                DatabaseNetwork.output2
        };
        mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.output_table, col, null, null, null, null, null);
        int k=0;
        cur.moveToLast();
        global_legitimate_count = 0;
        double threshold_output_0 = 0.95 ;
        double threshold_output_1 = 0.05 ;
        if((Double.parseDouble(cur.getString(0))< threshold_output_0)||(Double.parseDouble(cur.getString(1))> threshold_output_1)){
            trainingData td = new trainingData(con);
            td.additionalData();
            startLegitimateTraining();
            global_legitimate_count++;
        }
    }





    public void startLegitimateTraining() {
        errorBackPropagation e=new errorBackPropagation(con);
        e.initialiseHiddenLayer();
        e.initialiseInputLayer();
        e.initialiseOutputLayer();
        e.initialiseTargetOutput();
        e.setLegitimatetargetOutput();
        e.initialiseWeightsAndBias();
        e.initialiseChangeInWeightsAndBias();
        e.initialiseError();
        e.initialiseInputForHiddenLayer();
        e.initialiseInputForOutputLayer();
        DBHelper mydbhelper=new DBHelper(con,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.rawQuery("SELECT * from " + DatabaseNetwork.NETWORK_TABLE_NAME, null);
        int count = cur.getCount();
        if(count!=0){
            e.getFinalWeightsFromInputToHidden(con);
            e.getFinalWeightsFromHiddenToOutput();

        }else{
            e.randomizeWeights();
        }
        input_layer=new double[45];
        setLegitimateInputLayer();
        e.getInputLayer(input_layer);
        e.functionHiddenLayer();
        e.functionOutputLayer();
        e.functionErrorOutputLayer();
        e.functionChangeInWeightsHiddenToOutput();
        e.functionErrorHiddenLayer();
        e.functionChangeInWeightsInputToHidden();
        e.updateWeights();
        e.setFinalWeightsFromInputToHidden(con);
        e.setFinalWeightsFromHiddenToOutput();

        e.setError();
        e.getTotalmeanSquareError();
    }

    private void setLegitimateInputLayer() {
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
                Database.total_time
        };
        Cursor c=mydatabase.query(Database.TABLE_NAME,col,null,null,null,null,null);
        c.moveToPosition(global_legitimate_count);
        for(int i=0,q=0;i<45;i++){
            input_layer[i]=Double.parseDouble(c.getString(q++));
        }
        String print="";
        for(int i=0;i<45;i++)
            print+=input_layer[i]+",";

        //Log.e("Legitimate : ", print);

    }
}
