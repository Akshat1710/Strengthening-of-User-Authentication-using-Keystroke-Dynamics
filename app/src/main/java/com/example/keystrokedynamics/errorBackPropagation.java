package com.example.keystrokedynamics;

/**
 * Created by Mukesh on 10-03-2016.
 */

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class errorBackPropagation {

    public static double input_layer[];
    public static double hidden_layer[];
    public static double weight_input_to_hidden[][];
    public static double input_hidden_layer[];
    public static double input_output_layer[];
    public static double output_layer[];
    public static double target_output[];
    public static double weight_hidden_to_output[][];
    public static final double learning_rate=0.7;
    public static double error_output[];
    public static double error_hidden[];
    public static double change_in_weight_hidden_and_output[][];
    public static double change_in_weight_hidden_and_input[][];

    Context con;
    int decision = 0;
    errorBackPropagation(Context con){
        this.con=con;
    }

    public void initialiseWeightsAndBias(){
        weight_input_to_hidden=new double[input_layer.length][hidden_layer.length];
        weight_hidden_to_output=new double[hidden_layer.length][output_layer.length];

    }
    public void initialiseInputLayer(){
        input_layer=new double[45];
    }
    public void initialiseHiddenLayer(){
        hidden_layer=new double[16];
    }
    public void initialiseOutputLayer(){
        output_layer=new double[2];
    }
    public void initialiseTargetOutput(){
        target_output=new double[2];
    }
    public void setLegitimatetargetOutput(){
        target_output[0]=1;
        target_output[1]=0;
    }

    public void initialiseError(){
        error_output=new double[output_layer.length];
        error_hidden=new double[hidden_layer.length];
    }
    public void initialiseChangeInWeightsAndBias(){
        change_in_weight_hidden_and_output=new double[hidden_layer.length][output_layer.length];
        change_in_weight_hidden_and_input=new double[input_layer.length][hidden_layer.length];

    }
    public void initialiseInputForHiddenLayer(){
        input_hidden_layer=new double[hidden_layer.length];
    }
    public void initialiseInputForOutputLayer(){
        input_output_layer=new double[output_layer.length];
    }
    public double activationFunction(double lambda,int i) {
        double a=Math.exp(lambda);
        double b=1.0/(double)a;
        double c=1.0+b;
        double x=1.0/(double)c;
        return x;
    }
    public double derivativeOfActivationFunction(double output_layer) {
        return output_layer*(1-output_layer);
    }
    public void functionHiddenLayer(){
        for(int i=0;i<input_hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++){
                input_hidden_layer[i]+=input_layer[j]*weight_input_to_hidden[j][i];
            }
            hidden_layer[i]=activationFunction(input_hidden_layer[i],i);

        }
    }
    public void functionOutputLayer(){
        for(int i=0;i<input_output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                input_output_layer[i]+=hidden_layer[j]*weight_hidden_to_output[j][i];

            }
            output_layer[i]=activationFunction(input_output_layer[i],i);

        }
        ContentValues cv = new ContentValues();
        cv.put(DatabaseNetwork.output1,output_layer[0]);
        cv.put(DatabaseNetwork.output2,output_layer[1]);
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.output_table, null, cv);
    }
    public void functionErrorOutputLayer(){
        for(int i=0;i<output_layer.length;i++){
            error_output[i]=(target_output[i]-output_layer[i])*derivativeOfActivationFunction(output_layer[i]);
        }
    }
    public void functionChangeInWeightsHiddenToOutput() {
        for(int i=0;i<output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                change_in_weight_hidden_and_output[j][i]=learning_rate*error_output[i]*hidden_layer[j];
            }
        }
    }
    public void functionErrorHiddenLayer(){
        for(int j=0;j<hidden_layer.length;j++){
            double x=0;
            for(int k=0;k<output_layer.length;k++){
                x+=error_output[k]*weight_hidden_to_output[j][k];
            }
            error_hidden[j]=x*derivativeOfActivationFunction(hidden_layer[j]);
        }
    }
    public void functionChangeInWeightsInputToHidden() {
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++){
                change_in_weight_hidden_and_input[j][i]=learning_rate*error_hidden[i]*input_layer[j];
            }
        }
    }
    //Weight Updation
    public void updateWeights(){
        for(int i=0;i<output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                weight_hidden_to_output[j][i]=weight_hidden_to_output[j][i]+change_in_weight_hidden_and_output[j][i];
            }
        }
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++){
                weight_input_to_hidden[j][i]=weight_input_to_hidden[j][i]+change_in_weight_hidden_and_input[j][i];
            }
        }
    }
    public void randomizeWeights(){
        int i=0;
        for(;i<input_layer.length;i++) {
            for(int j=0;j<hidden_layer.length;) {
                double min = 0 ;
                double max = 0.3 ;
                double x=Math.random();
                if(x>=min && x<=max) {

                        weight_input_to_hidden[i][j] = x;
                        j++;

                }
            }
        }

        i=0;
        for(;i<hidden_layer.length;i++) {
            for(int j=0;j<output_layer.length;j++) {

                double min = 0 ;
                double max = 0.3 ;
                double x = Math.random();
                if(x>=min && x<=max) {
                    weight_hidden_to_output[i][j] = x ;
                }
            }
        }
    }
    //Testing Methods
    public void getInputLayer(double input_layer[]){
        this.input_layer=input_layer;
    }
    public void getFinalWeightsFromInputToHidden(Context c){
        String col[]=new String[]{
                DatabaseNetwork.wih11,
                DatabaseNetwork.wih21,
                DatabaseNetwork.wih31,
                DatabaseNetwork.wih41,
                DatabaseNetwork.wih51,
                DatabaseNetwork.wih61,
                DatabaseNetwork.wih71,
                DatabaseNetwork.wih81,
                DatabaseNetwork.wih91,
                DatabaseNetwork.wih101,
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
                DatabaseNetwork.wih12,
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
                DatabaseNetwork.wih15,DatabaseNetwork.wih25,
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
                DatabaseNetwork.wih16,DatabaseNetwork.wih26,
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
                DatabaseNetwork.wih17,DatabaseNetwork.wih27,
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
                DatabaseNetwork.wih18,DatabaseNetwork.wih28,
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

                DatabaseNetwork.wih19 ,
                DatabaseNetwork.wih29 ,
                DatabaseNetwork.wih39 ,
                DatabaseNetwork.wih49 ,
                DatabaseNetwork.wih59 ,
                DatabaseNetwork.wih69 ,
                DatabaseNetwork.wih79 ,
                DatabaseNetwork.wih89 ,
                DatabaseNetwork.wih99 ,
                DatabaseNetwork.wih109 ,
                DatabaseNetwork.wih119 ,
                DatabaseNetwork.wih129 ,
                DatabaseNetwork.wih139 ,
                DatabaseNetwork.wih149 ,
                DatabaseNetwork.wih159 ,
                DatabaseNetwork.wih169 ,
                DatabaseNetwork.wih179 ,
                DatabaseNetwork.wih189 ,
                DatabaseNetwork.wih199 ,
                DatabaseNetwork.wih209 ,
                DatabaseNetwork.wih219 ,
                DatabaseNetwork.wih229 ,
                DatabaseNetwork.wih239 ,
                DatabaseNetwork.wih249 ,
                DatabaseNetwork.wih259 ,
                DatabaseNetwork.wih269 ,
                DatabaseNetwork.wih279 ,
                DatabaseNetwork.wih289 ,
                DatabaseNetwork.wih299 ,
                DatabaseNetwork.wih309 ,
                DatabaseNetwork.wih319 ,
                DatabaseNetwork.wih329 ,
                DatabaseNetwork.wih339 ,
                DatabaseNetwork.wih349 ,
                DatabaseNetwork.wih359 ,
                DatabaseNetwork.wih369 ,
                DatabaseNetwork.wih379 ,
                DatabaseNetwork.wih389 ,
                DatabaseNetwork.wih399 ,
                DatabaseNetwork.wih409 ,
                DatabaseNetwork.wih419 ,
                DatabaseNetwork.wih429 ,
                DatabaseNetwork.wih439 ,
                DatabaseNetwork.wih449 ,
                DatabaseNetwork.wih459,

                DatabaseNetwork.wih110 ,
                DatabaseNetwork.wih210 ,
                DatabaseNetwork.wih310 ,
                DatabaseNetwork.wih410 ,
                DatabaseNetwork.wih510 ,
                DatabaseNetwork.wih610 ,
                DatabaseNetwork.wih710 ,
                DatabaseNetwork.wih810 ,
                DatabaseNetwork.wih910 ,
                DatabaseNetwork.wih1010 ,
                DatabaseNetwork.wih1110 ,
                DatabaseNetwork.wih1210 ,
                DatabaseNetwork.wih1310 ,
                DatabaseNetwork.wih1410 ,
                DatabaseNetwork.wih1510 ,
                DatabaseNetwork.wih1610 ,
                DatabaseNetwork.wih1710 ,
                DatabaseNetwork.wih1810 ,
                DatabaseNetwork.wih1910 ,
                DatabaseNetwork.wih2010 ,
                DatabaseNetwork.wih2110 ,
                DatabaseNetwork.wih2210 ,
                DatabaseNetwork.wih2310 ,
                DatabaseNetwork.wih2410 ,
                DatabaseNetwork.wih2510 ,
                DatabaseNetwork.wih2610 ,
                DatabaseNetwork.wih2710 ,
                DatabaseNetwork.wih2810 ,
                DatabaseNetwork.wih2910 ,
                DatabaseNetwork.wih3010 ,
                DatabaseNetwork.wih3110 ,
                DatabaseNetwork.wih3210 ,
                DatabaseNetwork.wih3310 ,
                DatabaseNetwork.wih3410 ,
                DatabaseNetwork.wih3510 ,
                DatabaseNetwork.wih3610 ,
                DatabaseNetwork.wih3710 ,
                DatabaseNetwork.wih3810 ,
                DatabaseNetwork.wih3910 ,
                DatabaseNetwork.wih4010 ,
                DatabaseNetwork.wih4110 ,
                DatabaseNetwork.wih4210 ,
                DatabaseNetwork.wih4310 ,
                DatabaseNetwork.wih4410 ,
                DatabaseNetwork.wih4510 ,
                DatabaseNetwork.wih_11,
                DatabaseNetwork.wih_21,
                DatabaseNetwork.wih_31,
                DatabaseNetwork.wih_41,
                DatabaseNetwork.wih_51,
                DatabaseNetwork.wih_61,
                DatabaseNetwork.wih_71,
                DatabaseNetwork.wih_81,
                DatabaseNetwork.wih_91,
                DatabaseNetwork.wih_101,
                DatabaseNetwork.wih_111,
                DatabaseNetwork.wih_121,
                DatabaseNetwork.wih_131,
                DatabaseNetwork.wih_141,
                DatabaseNetwork.wih_151,
                DatabaseNetwork.wih_161,
                DatabaseNetwork.wih_171,
                DatabaseNetwork.wih_181,
                DatabaseNetwork.wih_191,
                DatabaseNetwork.wih_201,
                DatabaseNetwork.wih_211,
                DatabaseNetwork.wih_221,
                DatabaseNetwork.wih_231,
                DatabaseNetwork.wih_241,
                DatabaseNetwork.wih_251,
                DatabaseNetwork.wih_261,
                DatabaseNetwork.wih_271,
                DatabaseNetwork.wih_281,
                DatabaseNetwork.wih_291,
                DatabaseNetwork.wih_301,
                DatabaseNetwork.wih_311,
                DatabaseNetwork.wih_321,
                DatabaseNetwork.wih_331,
                DatabaseNetwork.wih_341,
                DatabaseNetwork.wih_351,
                DatabaseNetwork.wih_361,
                DatabaseNetwork.wih_371,
                DatabaseNetwork.wih_381,
                DatabaseNetwork.wih_391,
                DatabaseNetwork.wih_401,
                DatabaseNetwork.wih_411,
                DatabaseNetwork.wih_421,
                DatabaseNetwork.wih_431,
                DatabaseNetwork.wih_441,
                DatabaseNetwork.wih_451,
                DatabaseNetwork.wih_12,
                DatabaseNetwork.wih_22,
                DatabaseNetwork.wih_32,
                DatabaseNetwork.wih_42,
                DatabaseNetwork.wih_52,
                DatabaseNetwork.wih_62,
                DatabaseNetwork.wih_72,
                DatabaseNetwork.wih_82,
                DatabaseNetwork.wih_92,
                DatabaseNetwork.wih_102,
                DatabaseNetwork.wih_112,
                DatabaseNetwork.wih_122,
                DatabaseNetwork.wih_132,
                DatabaseNetwork.wih_142,
                DatabaseNetwork.wih_152,
                DatabaseNetwork.wih_162,
                DatabaseNetwork.wih_172,
                DatabaseNetwork.wih_182,
                DatabaseNetwork.wih_192,
                DatabaseNetwork.wih_202,
                DatabaseNetwork.wih_212,
                DatabaseNetwork.wih_222,
                DatabaseNetwork.wih_232,
                DatabaseNetwork.wih_242,
                DatabaseNetwork.wih_252,
                DatabaseNetwork.wih_262,
                DatabaseNetwork.wih_272,
                DatabaseNetwork.wih_282,
                DatabaseNetwork.wih_292,
                DatabaseNetwork.wih_302,
                DatabaseNetwork.wih_312,
                DatabaseNetwork.wih_322,
                DatabaseNetwork.wih_332,
                DatabaseNetwork.wih_342,
                DatabaseNetwork.wih_352,
                DatabaseNetwork.wih_362,
                DatabaseNetwork.wih_372,
                DatabaseNetwork.wih_382,
                DatabaseNetwork.wih_392,
                DatabaseNetwork.wih_402,
                DatabaseNetwork.wih_412,
                DatabaseNetwork.wih_422,
                DatabaseNetwork.wih_432,
                DatabaseNetwork.wih_442,
                DatabaseNetwork.wih_452,
                DatabaseNetwork.wih_13,
                DatabaseNetwork.wih_23,
                DatabaseNetwork.wih_33,
                DatabaseNetwork.wih_43,
                DatabaseNetwork.wih_53,
                DatabaseNetwork.wih_63,
                DatabaseNetwork.wih_73,
                DatabaseNetwork.wih_83,
                DatabaseNetwork.wih_93,
                DatabaseNetwork.wih_103,
                DatabaseNetwork.wih_113,
                DatabaseNetwork.wih_123,
                DatabaseNetwork.wih_133,
                DatabaseNetwork.wih_143,
                DatabaseNetwork.wih_153,
                DatabaseNetwork.wih_163,
                DatabaseNetwork.wih_173,
                DatabaseNetwork.wih_183,
                DatabaseNetwork.wih_193,
                DatabaseNetwork.wih_203,
                DatabaseNetwork.wih_213,
                DatabaseNetwork.wih_223,
                DatabaseNetwork.wih_233,
                DatabaseNetwork.wih_243,
                DatabaseNetwork.wih_253,
                DatabaseNetwork.wih_263,
                DatabaseNetwork.wih_273,
                DatabaseNetwork.wih_283,
                DatabaseNetwork.wih_293,
                DatabaseNetwork.wih_303,
                DatabaseNetwork.wih_313,
                DatabaseNetwork.wih_323,
                DatabaseNetwork.wih_333,
                DatabaseNetwork.wih_343,
                DatabaseNetwork.wih_353,
                DatabaseNetwork.wih_363,
                DatabaseNetwork.wih_373,
                DatabaseNetwork.wih_383,
                DatabaseNetwork.wih_393,
                DatabaseNetwork.wih_403,
                DatabaseNetwork.wih_413,
                DatabaseNetwork.wih_423,
                DatabaseNetwork.wih_433,
                DatabaseNetwork.wih_443,
                DatabaseNetwork.wih_453,
                DatabaseNetwork.wih_14,
                DatabaseNetwork.wih_24,
                DatabaseNetwork.wih_34,
                DatabaseNetwork.wih_44,
                DatabaseNetwork.wih_54,
                DatabaseNetwork.wih_64,
                DatabaseNetwork.wih_74,
                DatabaseNetwork.wih_84,
                DatabaseNetwork.wih_94,
                DatabaseNetwork.wih_104,
                DatabaseNetwork.wih_114,
                DatabaseNetwork.wih_124,
                DatabaseNetwork.wih_134,
                DatabaseNetwork.wih_144,
                DatabaseNetwork.wih_154,
                DatabaseNetwork.wih_164,
                DatabaseNetwork.wih_174,
                DatabaseNetwork.wih_184,
                DatabaseNetwork.wih_194,
                DatabaseNetwork.wih_204,
                DatabaseNetwork.wih_214,
                DatabaseNetwork.wih_224,
                DatabaseNetwork.wih_234,
                DatabaseNetwork.wih_244,
                DatabaseNetwork.wih_254,
                DatabaseNetwork.wih_264,
                DatabaseNetwork.wih_274,
                DatabaseNetwork.wih_284,
                DatabaseNetwork.wih_294,
                DatabaseNetwork.wih_304,
                DatabaseNetwork.wih_314,
                DatabaseNetwork.wih_324,
                DatabaseNetwork.wih_334,
                DatabaseNetwork.wih_344,
                DatabaseNetwork.wih_354,
                DatabaseNetwork.wih_364,
                DatabaseNetwork.wih_374,
                DatabaseNetwork.wih_384,
                DatabaseNetwork.wih_394,
                DatabaseNetwork.wih_404,
                DatabaseNetwork.wih_414,
                DatabaseNetwork.wih_424,
                DatabaseNetwork.wih_434,
                DatabaseNetwork.wih_444,
                DatabaseNetwork.wih_454,
                DatabaseNetwork.wih_15 ,
                DatabaseNetwork.wih_25 ,
                DatabaseNetwork.wih_35 ,
                DatabaseNetwork.wih_45 ,
                DatabaseNetwork.wih_55 ,
                DatabaseNetwork.wih_65 ,
                DatabaseNetwork.wih_75 ,
                DatabaseNetwork.wih_85 ,
                DatabaseNetwork.wih_95 ,
                DatabaseNetwork.wih_105 ,
                DatabaseNetwork.wih_115 ,
                DatabaseNetwork.wih_125 ,
                DatabaseNetwork.wih_135 ,
                DatabaseNetwork.wih_145 ,
                DatabaseNetwork.wih_155 ,
                DatabaseNetwork.wih_165 ,
                DatabaseNetwork.wih_175 ,
                DatabaseNetwork.wih_185 ,
                DatabaseNetwork.wih_195 ,
                DatabaseNetwork.wih_205 ,
                DatabaseNetwork.wih_215 ,
                DatabaseNetwork.wih_225 ,
                DatabaseNetwork.wih_235 ,
                DatabaseNetwork.wih_245 ,
                DatabaseNetwork.wih_255 ,
                DatabaseNetwork.wih_265 ,
                DatabaseNetwork.wih_275 ,
                DatabaseNetwork.wih_285 ,
                DatabaseNetwork.wih_295 ,
                DatabaseNetwork.wih_305 ,
                DatabaseNetwork.wih_315 ,
                DatabaseNetwork.wih_325 ,
                DatabaseNetwork.wih_335 ,
                DatabaseNetwork.wih_345 ,
                DatabaseNetwork.wih_355 ,
                DatabaseNetwork.wih_365 ,
                DatabaseNetwork.wih_375 ,
                DatabaseNetwork.wih_385 ,
                DatabaseNetwork.wih_395 ,
                DatabaseNetwork.wih_405 ,
                DatabaseNetwork.wih_415 ,
                DatabaseNetwork.wih_425 ,
                DatabaseNetwork.wih_435 ,
                DatabaseNetwork.wih_445 ,
                DatabaseNetwork.wih_455 ,

                DatabaseNetwork.wih_16 ,
                DatabaseNetwork.wih_26 ,
                DatabaseNetwork.wih_36 ,
                DatabaseNetwork.wih_46 ,
                DatabaseNetwork.wih_56 ,
                DatabaseNetwork.wih_66 ,
                DatabaseNetwork.wih_76 ,
                DatabaseNetwork.wih_86 ,
                DatabaseNetwork.wih_96 ,
                DatabaseNetwork.wih_106 ,
                DatabaseNetwork.wih_116 ,
                DatabaseNetwork.wih_126 ,
                DatabaseNetwork.wih_136 ,
                DatabaseNetwork.wih_146 ,
                DatabaseNetwork.wih_156 ,
                DatabaseNetwork.wih_166 ,
                DatabaseNetwork.wih_176 ,
                DatabaseNetwork.wih_186 ,
                DatabaseNetwork.wih_196 ,
                DatabaseNetwork.wih_206 ,
                DatabaseNetwork.wih_216 ,
                DatabaseNetwork.wih_226 ,
                DatabaseNetwork.wih_236 ,
                DatabaseNetwork.wih_246 ,
                DatabaseNetwork.wih_256 ,
                DatabaseNetwork.wih_266 ,
                DatabaseNetwork.wih_276 ,
                DatabaseNetwork.wih_286 ,
                DatabaseNetwork.wih_296 ,
                DatabaseNetwork.wih_306 ,
                DatabaseNetwork.wih_316 ,
                DatabaseNetwork.wih_326 ,
                DatabaseNetwork.wih_336 ,
                DatabaseNetwork.wih_346 ,
                DatabaseNetwork.wih_356 ,
                DatabaseNetwork.wih_366 ,
                DatabaseNetwork.wih_376 ,
                DatabaseNetwork.wih_386 ,
                DatabaseNetwork.wih_396 ,
                DatabaseNetwork.wih_406 ,
                DatabaseNetwork.wih_416 ,
                DatabaseNetwork.wih_426 ,
                DatabaseNetwork.wih_436 ,
                DatabaseNetwork.wih_446 ,
                DatabaseNetwork.wih_456

        };
        DBHelper mydbhelper=new DBHelper(c, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME, col, null, null, null, null, null);
        int k=0;
        cur.moveToLast();
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++) {
                weight_input_to_hidden[j][i] = Double.parseDouble(cur.getString(k++));
            }
        }
        mydbhelper.close();
    }
    public void getFinalWeightsFromHiddenToOutput(){
        String col[]=new String[]{
                DatabaseNetwork.who11,
                DatabaseNetwork.who21,
                DatabaseNetwork.who31,
                DatabaseNetwork.who41,
                DatabaseNetwork.who51,
                DatabaseNetwork.who61,
                DatabaseNetwork.who71,
                DatabaseNetwork.who81,
                DatabaseNetwork.who91,
                DatabaseNetwork.who101,
                DatabaseNetwork.who_11,
                DatabaseNetwork.who_21,
                DatabaseNetwork.who_31,
                DatabaseNetwork.who_41,
                DatabaseNetwork.who_51,
                DatabaseNetwork.who_61,
                DatabaseNetwork.who12,
                DatabaseNetwork.who22,
                DatabaseNetwork.who32,
                DatabaseNetwork.who42,
                DatabaseNetwork.who52,
                DatabaseNetwork.who62,
                DatabaseNetwork.who72,
                DatabaseNetwork.who82,
                DatabaseNetwork.who92,
                DatabaseNetwork.who102,
                DatabaseNetwork.who_12,
                DatabaseNetwork.who_22,
                DatabaseNetwork.who_32,
                DatabaseNetwork.who_42,
                DatabaseNetwork.who_52,
                DatabaseNetwork.who_62,

        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.hidden_to_output, col, null, null, null, null, null);
        cur.moveToLast();
        weight_hidden_to_output[0][0]=Double.parseDouble(cur.getString(0));
        weight_hidden_to_output[1][0]=Double.parseDouble(cur.getString(1));
        weight_hidden_to_output[2][0]=Double.parseDouble(cur.getString(2));
        weight_hidden_to_output[3][0]=Double.parseDouble(cur.getString(3));
        weight_hidden_to_output[4][0]=Double.parseDouble(cur.getString(4));
        weight_hidden_to_output[5][0]=Double.parseDouble(cur.getString(5));
        weight_hidden_to_output[6][0]=Double.parseDouble(cur.getString(6));
        weight_hidden_to_output[7][0]=Double.parseDouble(cur.getString(7));
        weight_hidden_to_output[8][0]=Double.parseDouble(cur.getString(8));
        weight_hidden_to_output[9][0]=Double.parseDouble(cur.getString(9));

        weight_hidden_to_output[10][0]=Double.parseDouble(cur.getString(10));
        weight_hidden_to_output[11][0]=Double.parseDouble(cur.getString(11));
        weight_hidden_to_output[12][0]=Double.parseDouble(cur.getString(12));
        weight_hidden_to_output[13][0]=Double.parseDouble(cur.getString(13));
        weight_hidden_to_output[14][0]=Double.parseDouble(cur.getString(14));
        weight_hidden_to_output[15][0]=Double.parseDouble(cur.getString(15));


        weight_hidden_to_output[0][1]=Double.parseDouble(cur.getString(16));
        weight_hidden_to_output[1][1]=Double.parseDouble(cur.getString(17));
        weight_hidden_to_output[2][1]=Double.parseDouble(cur.getString(18));
        weight_hidden_to_output[3][1]=Double.parseDouble(cur.getString(19));
        weight_hidden_to_output[4][1]=Double.parseDouble(cur.getString(20));
        weight_hidden_to_output[5][1]=Double.parseDouble(cur.getString(21));
        weight_hidden_to_output[6][1]=Double.parseDouble(cur.getString(22));
        weight_hidden_to_output[7][1]=Double.parseDouble(cur.getString(23));
        weight_hidden_to_output[8][1]=Double.parseDouble(cur.getString(24));
        weight_hidden_to_output[9][1]=Double.parseDouble(cur.getString(25));
        weight_hidden_to_output[10][1]=Double.parseDouble(cur.getString(26));
        weight_hidden_to_output[11][1]=Double.parseDouble(cur.getString(27));
        weight_hidden_to_output[12][1]=Double.parseDouble(cur.getString(28));
        weight_hidden_to_output[13][1]=Double.parseDouble(cur.getString(29));
        weight_hidden_to_output[14][1]=Double.parseDouble(cur.getString(30));
        weight_hidden_to_output[15][1]=Double.parseDouble(cur.getString(31));

    }

    public void setFinalWeightsFromHiddenToOutput(){
        ContentValues cv = new ContentValues();
        for(int i=0;i<output_layer.length;i++) {
            int j=0;
            switch(i) {
                case 0:
                    j=0;
                    cv.put(DatabaseNetwork.who11, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who21, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who31, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who41, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who51, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who61, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who71, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who81, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who91, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who101, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_11, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_21, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_31, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_41, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_51, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_61, weight_hidden_to_output[j++][i]);

                    break;
                case 1:
                    j=0;
                    cv.put(DatabaseNetwork.who12, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who22, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who32, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who42, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who52, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who62, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who72, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who82, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who92, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who102, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_12, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_22, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_32, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_42, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_52, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who_62, weight_hidden_to_output[j++][i]);

                    break;

            }
        }
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.hidden_to_output, null, cv);

    }
    public void setFinalWeightsFromInputToHidden(Context c){


        ContentValues cv = new ContentValues();
        for(int i=0;i<hidden_layer.length;i++){
            int j=0;
            switch(i) {
                case 0:
                    j=0;
                    cv.put(DatabaseNetwork.wih11,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih21,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih31,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih41,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih51,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih61,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih71,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih81,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih91,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih101,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih111,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih121,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih131,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih141,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih151,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih161,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih171,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih181,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih191,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih201,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih211,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih221,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih231,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih241,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih251,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih261,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih271,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih281,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih291,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih301,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih311,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih321,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih331,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih341,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih351,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih361,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih371,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih381,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih391,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih401,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih411,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih421,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih431,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih441,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih451,weight_input_to_hidden[j++][i] );

                    break;
                case 1:j=0;
                    cv.put(DatabaseNetwork.wih12,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih22,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih32,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih42,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih52,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih62,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih72,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih82,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih92,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih102,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih112,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih122,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih132,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih142,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih152,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih162,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih172,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih182,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih192,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih202,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih212,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih222,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih232,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih242,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih252,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih262,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih272,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih282,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih292,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih302,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih312,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih322,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih332,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih342,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih352,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih362,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih372,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih382,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih392,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih402,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih412,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih422,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih432,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih442,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih452,weight_input_to_hidden[j++][i] );

                    break;
                case 2:
                    j=0;
                    cv.put(DatabaseNetwork.wih13,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih23,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih33,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih43,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih53,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih63,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih73,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih83,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih93,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih103,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih113,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih123,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih133,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih143,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih153,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih163,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih173,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih183,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih193,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih203,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih213,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih223,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih233,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih243,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih253,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih263,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih273,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih283,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih293,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih303,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih313,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih323,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih333,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih343,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih353,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih363,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih373,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih383,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih393,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih403,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih413,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih423,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih433,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih443,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih453,weight_input_to_hidden[j++][i] );

                    break;
                case 3:
                    j=0;
                    cv.put(DatabaseNetwork.wih14,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih24,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih34,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih44,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih54,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih64,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih74,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih84,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih94,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih104,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih114,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih124,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih134,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih144,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih154,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih164,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih174,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih184,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih194,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih204,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih214,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih224,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih234,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih244,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih254,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih264,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih274,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih284,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih294,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih304,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih314,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih324,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih334,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih344,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih354,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih364,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih374,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih384,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih394,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih404,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih414,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih424,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih434,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih444,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih454,weight_input_to_hidden[j++][i] );


                    break;

                case 4: j=0;
                    cv.put(DatabaseNetwork.wih15,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih25,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih35,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih45,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih55,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih65,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih75,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih85,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih95,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih105,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih115,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih125,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih135,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih145,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih155,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih165,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih175,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih185,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih195,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih205,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih215,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih225,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih235,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih245,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih255,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih265,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih275,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih285,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih295,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih305,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih315,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih325,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih335,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih345,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih355,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih365,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih375,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih385,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih395,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih405,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih415,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih425,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih435,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih445,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih455,weight_input_to_hidden[j++][i] );

                    break;
                case 5:
                    j=0;
                    cv.put(DatabaseNetwork.wih16,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih26,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih36,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih46,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih56,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih66,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih76,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih86,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih96,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih106,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih116,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih126,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih136,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih146,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih156,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih166,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih176,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih186,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih196,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih206,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih216,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih226,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih236,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih246,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih256,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih266,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih276,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih286,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih296,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih306,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih316,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih326,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih336,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih346,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih356,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih366,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih376,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih386,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih396,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih406,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih416,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih426,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih436,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih446,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih456,weight_input_to_hidden[j++][i] );

                    break;
                case 6: j=0;
                    cv.put(DatabaseNetwork.wih17,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih27,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih37,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih47,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih57,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih67,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih77,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih87,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih97,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih107,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih117,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih127,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih137,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih147,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih157,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih167,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih177,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih187,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih197,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih207,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih217,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih227,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih237,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih247,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih257,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih267,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih277,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih287,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih297,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih307,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih317,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih327,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih337,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih347,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih357,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih367,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih377,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih387,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih397,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih407,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih417,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih427,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih437,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih447,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih457,weight_input_to_hidden[j++][i] );

                    break;
                case 7: j=0;
                    cv.put(DatabaseNetwork.wih18,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih28,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih38,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih48,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih58,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih68,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih78,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih88,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih98,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih108,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih118,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih128,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih138,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih148,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih158,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih168,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih178,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih188,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih198,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih208,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih218,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih228,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih238,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih248,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih258,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih268,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih278,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih288,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih298,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih308,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih318,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih328,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih338,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih348,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih358,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih368,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih378,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih388,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih398,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih408,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih418,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih428,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih438,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih448,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih458,weight_input_to_hidden[j++][i] );

                    break;
                case 8: j=0;
                    cv.put(DatabaseNetwork.wih19,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih29,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih39,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih49,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih59,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih69,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih79,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih89,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih99,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih109,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih119,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih129,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih139,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih149,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih159,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih169,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih179,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih189,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih199,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih209,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih219,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih229,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih239,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih249,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih259,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih269,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih279,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih289,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih299,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih309,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih319,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih329,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih339,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih349,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih359,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih369,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih379,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih389,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih399,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih409,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih419,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih429,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih439,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih449,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih459,weight_input_to_hidden[j++][i] );
                    break;

                case 9: j=0;
                    cv.put(DatabaseNetwork.wih110,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih210,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih310,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih410,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih510,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih610,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih710,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih810,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih910,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1010,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1110,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1210,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1310,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1410,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1510,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1610,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1710,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1810,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih1910,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2010,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2110,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2210,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2310,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2410,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2510,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2610,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2710,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2810,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih2910,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3010,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3110,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3210,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3310,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3410,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3510,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3610,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3710,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3810,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih3910,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4010,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4110,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4210,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4310,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4410,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih4510,weight_input_to_hidden[j++][i] );
                    break;

                case 10:
                    j=0;
                    cv.put(DatabaseNetwork.wih_11,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_21,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_31,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_41,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_51,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_61,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_71,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_81,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_91,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_101,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_111,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_121,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_131,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_141,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_151,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_161,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_171,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_181,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_191,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_201,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_211,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_221,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_231,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_241,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_251,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_261,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_271,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_281,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_291,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_301,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_311,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_321,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_331,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_341,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_351,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_361,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_371,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_381,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_391,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_401,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_411,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_421,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_431,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_441,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_451,weight_input_to_hidden[j++][i] );

                    break;
                case 11:j=0;
                    cv.put(DatabaseNetwork.wih_12,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_22,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_32,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_42,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_52,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_62,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_72,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_82,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_92,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_102,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_112,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_122,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_132,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_142,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_152,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_162,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_172,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_182,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_192,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_202,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_212,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_222,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_232,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_242,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_252,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_262,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_272,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_282,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_292,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_302,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_312,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_322,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_332,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_342,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_352,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_362,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_372,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_382,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_392,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_402,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_412,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_422,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_432,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_442,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_452,weight_input_to_hidden[j++][i] );

                    break;
                case 12:
                    j=0;
                    cv.put(DatabaseNetwork.wih_13,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_23,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_33,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_43,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_53,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_63,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_73,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_83,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_93,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_103,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_113,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_123,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_133,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_143,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_153,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_163,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_173,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_183,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_193,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_203,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_213,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_223,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_233,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_243,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_253,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_263,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_273,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_283,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_293,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_303,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_313,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_323,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_333,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_343,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_353,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_363,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_373,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_383,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_393,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_403,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_413,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_423,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_433,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_443,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_453,weight_input_to_hidden[j++][i] );

                    break;
                case 13:
                    j=0;
                    cv.put(DatabaseNetwork.wih_14,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_24,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_34,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_44,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_54,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_64,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_74,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_84,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_94,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_104,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_114,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_124,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_134,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_144,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_154,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_164,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_174,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_184,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_194,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_204,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_214,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_224,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_234,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_244,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_254,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_264,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_274,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_284,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_294,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_304,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_314,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_324,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_334,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_344,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_354,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_364,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_374,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_384,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_394,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_404,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_414,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_424,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_434,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_444,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_454,weight_input_to_hidden[j++][i] );


                    break;

                case 14: j=0;
                    cv.put(DatabaseNetwork.wih_15,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_25,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_35,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_45,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_55,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_65,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_75,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_85,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_95,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_105,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_115,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_125,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_135,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_145,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_155,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_165,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_175,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_185,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_195,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_205,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_215,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_225,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_235,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_245,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_255,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_265,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_275,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_285,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_295,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_305,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_315,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_325,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_335,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_345,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_355,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_365,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_375,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_385,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_395,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_405,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_415,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_425,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_435,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_445,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_455,weight_input_to_hidden[j++][i] );

                    break;
                case 15:
                    j=0;
                    cv.put(DatabaseNetwork.wih_16,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_26,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_36,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_46,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_56,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_66,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_76,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_86,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_96,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_106,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_116,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_126,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_136,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_146,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_156,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_166,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_176,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_186,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_196,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_206,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_216,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_226,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_236,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_246,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_256,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_266,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_276,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_286,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_296,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_306,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_316,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_326,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_336,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_346,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_356,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_366,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_376,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_386,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_396,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_406,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_416,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_426,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_436,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_446,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih_456,weight_input_to_hidden[j++][i] );

                    break;

            }

        }
        DBHelper mydbhelper=new DBHelper(c, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.NETWORK_TABLE_NAME, null, cv);

    }
    public void getTotalmeanSquareError(){
        String col[]=new String[]{
                DatabaseNetwork.error1,
                DatabaseNetwork.error2
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.error_table, col, null, null, null, null, null);
        double error = 0;
        for(cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()){
            error += Double.parseDouble(cur.getString(0))*Double.parseDouble(cur.getString(0))+Double.parseDouble(cur.getString(1))*Double.parseDouble(cur.getString(1));
            error/=2;
        }
    }
    public void setError(){
        DBHelper mydbhelper=new DBHelper(con , Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DatabaseNetwork.error1,error_output[0]);
        cv.put(DatabaseNetwork.error2, error_output[1]);

        long r=mydatabase.insertOrThrow(DatabaseNetwork.error_table, null, cv);

    }
    public int functionOutputLayerTesting(){
        for(int i=0;i<input_output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                input_output_layer[i]+=hidden_layer[j]*weight_hidden_to_output[j][i];

            }
            output_layer[i]=activationFunction(input_output_layer[i],i);

        }
        Log.e("Output 1",output_layer[0]+"");
        Log.e("Output 2",output_layer[1]+"");
        int k =  validateUser();
        return k;
    }

    private int validateUser() {
        String col[]=new String[]{
                DatabaseNetwork.output1,
                DatabaseNetwork.output2
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.output_table, col, null, null, null, null, null);
        int k=0;
        decision = 0;
        cur.moveToLast();
        /*
        Log.e("Trained Output 1", cur.getString(0));
        Log.e("Trained Output 2", cur.getString(1));
        */

        if(output_layer[0]>=Double.parseDouble(cur.getString(0))&& output_layer[1]<=Double.parseDouble(cur.getString(1))){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
                alertDialogBuilder.setTitle("I sensed! It was you.") ;

                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dialog d=(Dialog)arg0;
                        decision = 1;

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
                alertDialogBuilder.setTitle("Access Denied") ;

                alertDialogBuilder.setPositiveButton("Retry",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dialog d=(Dialog)arg0;
                        decision = 0 ;
                        d.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

    return decision;

    }

}
