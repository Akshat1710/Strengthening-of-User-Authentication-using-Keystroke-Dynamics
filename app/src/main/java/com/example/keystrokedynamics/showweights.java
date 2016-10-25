package com.example.keystrokedynamics;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class showweights extends AppCompatActivity {

    String [] wh1,wh2,wh3,wh4,wh5,wh6,wh7,wh8,error ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showweights);
        Intent in=getIntent();
        ListView l2=(ListView)findViewById(R.id.listView2);
        DBHelper mydbhelper=new DBHelper(this, Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.rawQuery("select * from " + DatabaseNetwork.NETWORK_TABLE_NAME, null);
        int count = cur.getCount();
        wh1=new String[count];
        wh2=new String[count];
        wh3=new String[count];
        wh4=new String[count];
        wh5=new String[count];
        wh6=new String[count];
        wh7=new String[count];
        wh8=new String[count];
        error=new String[count];
        String []col=new String[]{ DatabaseNetwork.wih11,DatabaseNetwork.wih21,
                DatabaseNetwork.wih31,DatabaseNetwork.wih41,
                DatabaseNetwork.wih51,DatabaseNetwork.wih61,
                DatabaseNetwork.wih71,DatabaseNetwork.wih81,
                DatabaseNetwork.wih91,DatabaseNetwork.wih101,
                DatabaseNetwork.wih111,
                DatabaseNetwork.wih121,
                DatabaseNetwork.wih131,
                DatabaseNetwork.wih141,
                DatabaseNetwork.wih151,
                DatabaseNetwork.wih161,
                DatabaseNetwork.wih171,
                DatabaseNetwork.wih181,
                DatabaseNetwork.wih191,
                DatabaseNetwork.wih201,
                DatabaseNetwork.wih211,
                DatabaseNetwork.wih221,
                DatabaseNetwork.wih231,
                DatabaseNetwork.wih241,
                DatabaseNetwork.wih251,
                DatabaseNetwork.wih261,
                DatabaseNetwork.wih271,
                DatabaseNetwork.wih281,
                DatabaseNetwork.wih291,
                DatabaseNetwork.wih301,
                DatabaseNetwork.wih311,
                DatabaseNetwork.wih321,
                DatabaseNetwork.wih331,
                DatabaseNetwork.wih341,
                DatabaseNetwork.wih351,
                DatabaseNetwork.wih361,
                DatabaseNetwork.wih371,
                DatabaseNetwork.wih381,
                DatabaseNetwork.wih391,
                DatabaseNetwork.wih401,
                DatabaseNetwork.wih411,
                DatabaseNetwork.wih421,
                DatabaseNetwork.wih431,
                DatabaseNetwork.wih441,
                DatabaseNetwork.wih451,

        };

        Cursor c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);
        String dummy;
        c.moveToLast();
        int j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh1[j++]=dummy;
            c.moveToPrevious();
        }
        col=new String[]{DatabaseNetwork.wih12,
                DatabaseNetwork.wih22,
                DatabaseNetwork.wih32,
                DatabaseNetwork.wih42,
                DatabaseNetwork.wih52,
                DatabaseNetwork.wih62,
                DatabaseNetwork.wih72,
                DatabaseNetwork.wih82,
                DatabaseNetwork.wih92,
                DatabaseNetwork.wih102,
                DatabaseNetwork.wih112,
                DatabaseNetwork.wih122,
                DatabaseNetwork.wih132,
                DatabaseNetwork.wih142,
                DatabaseNetwork.wih152,
                DatabaseNetwork.wih162,
                DatabaseNetwork.wih172,
                DatabaseNetwork.wih182,
                DatabaseNetwork.wih192,
                DatabaseNetwork.wih202,
                DatabaseNetwork.wih212,
                DatabaseNetwork.wih222,
                DatabaseNetwork.wih232,
                DatabaseNetwork.wih242,
                DatabaseNetwork.wih252,
                DatabaseNetwork.wih262,
                DatabaseNetwork.wih272,
                DatabaseNetwork.wih282,
                DatabaseNetwork.wih292,
                DatabaseNetwork.wih302,
                DatabaseNetwork.wih312,
                DatabaseNetwork.wih322,
                DatabaseNetwork.wih332,
                DatabaseNetwork.wih342,
                DatabaseNetwork.wih352,
                DatabaseNetwork.wih362,
                DatabaseNetwork.wih372,
                DatabaseNetwork.wih382,
                DatabaseNetwork.wih392,
                DatabaseNetwork.wih402,
                DatabaseNetwork.wih412,
                DatabaseNetwork.wih422,
                DatabaseNetwork.wih432,
                DatabaseNetwork.wih442,
                DatabaseNetwork.wih452,
        };
        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh2[j++]=dummy;
            c.moveToPrevious();
        }

        col=new String[]{
                DatabaseNetwork.wih13,
                DatabaseNetwork.wih23,
                DatabaseNetwork.wih33,
                DatabaseNetwork.wih43,
                DatabaseNetwork.wih53,
                DatabaseNetwork.wih63,
                DatabaseNetwork.wih73,
                DatabaseNetwork.wih83,
                DatabaseNetwork.wih93,
                DatabaseNetwork.wih103,
                DatabaseNetwork.wih113,
                DatabaseNetwork.wih123,
                DatabaseNetwork.wih133,
                DatabaseNetwork.wih143,
                DatabaseNetwork.wih153,
                DatabaseNetwork.wih163,
                DatabaseNetwork.wih173,
                DatabaseNetwork.wih183,
                DatabaseNetwork.wih193,
                DatabaseNetwork.wih203,
                DatabaseNetwork.wih213,
                DatabaseNetwork.wih223,
                DatabaseNetwork.wih233,
                DatabaseNetwork.wih243,
                DatabaseNetwork.wih253,
                DatabaseNetwork.wih263,
                DatabaseNetwork.wih273,
                DatabaseNetwork.wih283,
                DatabaseNetwork.wih293,
                DatabaseNetwork.wih303,
                DatabaseNetwork.wih313,
                DatabaseNetwork.wih323,
                DatabaseNetwork.wih333,
                DatabaseNetwork.wih343,
                DatabaseNetwork.wih353,
                DatabaseNetwork.wih363,
                DatabaseNetwork.wih373,
                DatabaseNetwork.wih383,
                DatabaseNetwork.wih393,
                DatabaseNetwork.wih403,
                DatabaseNetwork.wih413,
                DatabaseNetwork.wih423,
                DatabaseNetwork.wih433,
                DatabaseNetwork.wih443,
                DatabaseNetwork.wih453,

        };
        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh3[j++]=dummy;
            c.moveToPrevious();
        }
        col=new String[]{
                DatabaseNetwork.wih14,
                DatabaseNetwork.wih24,
                DatabaseNetwork.wih34,
                DatabaseNetwork.wih44,
                DatabaseNetwork.wih54,
                DatabaseNetwork.wih64,
                DatabaseNetwork.wih74,
                DatabaseNetwork.wih84,
                DatabaseNetwork.wih94,
                DatabaseNetwork.wih104,
                DatabaseNetwork.wih114,
                DatabaseNetwork.wih124,
                DatabaseNetwork.wih134,
                DatabaseNetwork.wih144,
                DatabaseNetwork.wih154,
                DatabaseNetwork.wih164,
                DatabaseNetwork.wih174,
                DatabaseNetwork.wih184,
                DatabaseNetwork.wih194,
                DatabaseNetwork.wih204,
                DatabaseNetwork.wih214,
                DatabaseNetwork.wih224,
                DatabaseNetwork.wih234,
                DatabaseNetwork.wih244,
                DatabaseNetwork.wih254,
                DatabaseNetwork.wih264,
                DatabaseNetwork.wih274,
                DatabaseNetwork.wih284,
                DatabaseNetwork.wih294,
                DatabaseNetwork.wih304,
                DatabaseNetwork.wih314,
                DatabaseNetwork.wih324,
                DatabaseNetwork.wih334,
                DatabaseNetwork.wih344,
                DatabaseNetwork.wih354,
                DatabaseNetwork.wih364,
                DatabaseNetwork.wih374,
                DatabaseNetwork.wih384,
                DatabaseNetwork.wih394,
                DatabaseNetwork.wih404,
                DatabaseNetwork.wih414,
                DatabaseNetwork.wih424,
                DatabaseNetwork.wih434,
                DatabaseNetwork.wih444,
                DatabaseNetwork.wih454,

        };
        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh4[j++]=dummy;
            c.moveToPrevious();
        }


        col=new String[]{ DatabaseNetwork.wih15,DatabaseNetwork.wih25,
                DatabaseNetwork.wih35,DatabaseNetwork.wih45,
                DatabaseNetwork.wih55,DatabaseNetwork.wih65,
                DatabaseNetwork.wih75,DatabaseNetwork.wih85,
                DatabaseNetwork.wih95,DatabaseNetwork.wih105,
                DatabaseNetwork.wih115,
                DatabaseNetwork.wih125,
                DatabaseNetwork.wih135,
                DatabaseNetwork.wih145,
                DatabaseNetwork.wih155,
                DatabaseNetwork.wih165,
                DatabaseNetwork.wih175,
                DatabaseNetwork.wih185,
                DatabaseNetwork.wih195,
                DatabaseNetwork.wih205,
                DatabaseNetwork.wih215,
                DatabaseNetwork.wih225,
                DatabaseNetwork.wih235,
                DatabaseNetwork.wih245,
                DatabaseNetwork.wih255,
                DatabaseNetwork.wih265,
                DatabaseNetwork.wih275,
                DatabaseNetwork.wih285,
                DatabaseNetwork.wih295,
                DatabaseNetwork.wih305,
                DatabaseNetwork.wih315,
                DatabaseNetwork.wih325,
                DatabaseNetwork.wih335,
                DatabaseNetwork.wih345,
                DatabaseNetwork.wih355,
                DatabaseNetwork.wih365,
                DatabaseNetwork.wih375,
                DatabaseNetwork.wih385,
                DatabaseNetwork.wih395,
                DatabaseNetwork.wih405,
                DatabaseNetwork.wih415,
                DatabaseNetwork.wih425,
                DatabaseNetwork.wih435,
                DatabaseNetwork.wih445,
                DatabaseNetwork.wih455,

        };

        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);
        
        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh5[j++]=dummy;
            c.moveToPrevious();
        }

        col=new String[]{ DatabaseNetwork.wih16,DatabaseNetwork.wih26,
                DatabaseNetwork.wih36,DatabaseNetwork.wih46,
                DatabaseNetwork.wih56,DatabaseNetwork.wih66,
                DatabaseNetwork.wih76,DatabaseNetwork.wih86,
                DatabaseNetwork.wih96,DatabaseNetwork.wih106,
                DatabaseNetwork.wih116,
                DatabaseNetwork.wih126,
                DatabaseNetwork.wih136,
                DatabaseNetwork.wih146,
                DatabaseNetwork.wih156,
                DatabaseNetwork.wih166,
                DatabaseNetwork.wih176,
                DatabaseNetwork.wih186,
                DatabaseNetwork.wih196,
                DatabaseNetwork.wih206,
                DatabaseNetwork.wih216,
                DatabaseNetwork.wih226,
                DatabaseNetwork.wih236,
                DatabaseNetwork.wih246,
                DatabaseNetwork.wih256,
                DatabaseNetwork.wih266,
                DatabaseNetwork.wih276,
                DatabaseNetwork.wih286,
                DatabaseNetwork.wih296,
                DatabaseNetwork.wih306,
                DatabaseNetwork.wih316,
                DatabaseNetwork.wih326,
                DatabaseNetwork.wih336,
                DatabaseNetwork.wih346,
                DatabaseNetwork.wih356,
                DatabaseNetwork.wih366,
                DatabaseNetwork.wih376,
                DatabaseNetwork.wih386,
                DatabaseNetwork.wih396,
                DatabaseNetwork.wih406,
                DatabaseNetwork.wih416,
                DatabaseNetwork.wih426,
                DatabaseNetwork.wih436,
                DatabaseNetwork.wih446,
                DatabaseNetwork.wih456,

        };

        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh6[j++]=dummy;
            c.moveToPrevious();
        }

        col=new String[]{ DatabaseNetwork.wih17,DatabaseNetwork.wih27,
                DatabaseNetwork.wih37,DatabaseNetwork.wih47,
                DatabaseNetwork.wih57,DatabaseNetwork.wih67,
                DatabaseNetwork.wih77,DatabaseNetwork.wih87,
                DatabaseNetwork.wih97,DatabaseNetwork.wih107,
                DatabaseNetwork.wih117,
                DatabaseNetwork.wih127,
                DatabaseNetwork.wih137,
                DatabaseNetwork.wih147,
                DatabaseNetwork.wih157,
                DatabaseNetwork.wih167,
                DatabaseNetwork.wih177,
                DatabaseNetwork.wih187,
                DatabaseNetwork.wih197,
                DatabaseNetwork.wih207,
                DatabaseNetwork.wih217,
                DatabaseNetwork.wih227,
                DatabaseNetwork.wih237,
                DatabaseNetwork.wih247,
                DatabaseNetwork.wih257,
                DatabaseNetwork.wih267,
                DatabaseNetwork.wih277,
                DatabaseNetwork.wih287,
                DatabaseNetwork.wih297,
                DatabaseNetwork.wih307,
                DatabaseNetwork.wih317,
                DatabaseNetwork.wih327,
                DatabaseNetwork.wih337,
                DatabaseNetwork.wih347,
                DatabaseNetwork.wih357,
                DatabaseNetwork.wih367,
                DatabaseNetwork.wih377,
                DatabaseNetwork.wih387,
                DatabaseNetwork.wih397,
                DatabaseNetwork.wih407,
                DatabaseNetwork.wih417,
                DatabaseNetwork.wih427,
                DatabaseNetwork.wih437,
                DatabaseNetwork.wih447,
                DatabaseNetwork.wih457,

        };

        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh7[j++]=dummy;
            c.moveToPrevious();
        }

        col=new String[]{ DatabaseNetwork.wih18,DatabaseNetwork.wih28,
                DatabaseNetwork.wih38,DatabaseNetwork.wih48,
                DatabaseNetwork.wih58,DatabaseNetwork.wih68,
                DatabaseNetwork.wih78,DatabaseNetwork.wih88,
                DatabaseNetwork.wih98,DatabaseNetwork.wih108,
                DatabaseNetwork.wih118,
                DatabaseNetwork.wih128,
                DatabaseNetwork.wih138,
                DatabaseNetwork.wih148,
                DatabaseNetwork.wih158,
                DatabaseNetwork.wih168,
                DatabaseNetwork.wih178,
                DatabaseNetwork.wih188,
                DatabaseNetwork.wih198,
                DatabaseNetwork.wih208,
                DatabaseNetwork.wih218,
                DatabaseNetwork.wih228,
                DatabaseNetwork.wih238,
                DatabaseNetwork.wih248,
                DatabaseNetwork.wih258,
                DatabaseNetwork.wih268,
                DatabaseNetwork.wih278,
                DatabaseNetwork.wih288,
                DatabaseNetwork.wih298,
                DatabaseNetwork.wih308,
                DatabaseNetwork.wih318,
                DatabaseNetwork.wih328,
                DatabaseNetwork.wih338,
                DatabaseNetwork.wih348,
                DatabaseNetwork.wih358,
                DatabaseNetwork.wih368,
                DatabaseNetwork.wih378,
                DatabaseNetwork.wih388,
                DatabaseNetwork.wih398,
                DatabaseNetwork.wih408,
                DatabaseNetwork.wih418,
                DatabaseNetwork.wih428,
                DatabaseNetwork.wih438,
                DatabaseNetwork.wih448,
                DatabaseNetwork.wih458,

        };

        c=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME,col,null,null,null,null,null);

        c.moveToLast();
        j=0;
        while(!c.isBeforeFirst()){
            dummy="";
            for(int k=0;k<45;k++){
                dummy+=c.getString(k)+" , ";
            }
            wh8[j++]=dummy;
            c.moveToPrevious();
        }
/*
        col=new String[]{
                DatabaseNetwork.error1,
                //DatabaseNetwork.error2
        };

        c=mydatabase.query(DatabaseNetwork.error_table, col, null, null, null, null, null);
        j=0;
        for(c.moveToLast();c.isBeforeFirst();c.moveToPrevious()){
            dummy="";
            for(int k=0;k<1;k++) {
                dummy += Double.parseDouble(c.getString(k)) + " , ";
            }
            error[j++]=dummy;
        }*/


        l2.setAdapter(new Databaseweightadapter(wh1,wh2,wh3,wh4,wh5,wh6,wh7,wh8,error,this));



    }
}
