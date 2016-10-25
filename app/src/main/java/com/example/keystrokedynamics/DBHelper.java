package com.example.keystrokedynamics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String c="CREATE TABLE "+Database.TABLE_NAME+" ( "+
                Database.b1_x+" TEXT,"+
                Database.b1_y+" TEXT,"+
                Database.b2_x+" TEXT,"+
                Database.b2_y+" TEXT,"+
                Database.b3_x+" TEXT,"+
                Database.b3_y+" TEXT,"+
                Database.b4_x+" TEXT,"+
                Database.b4_y+" TEXT,"+
                Database.b5_x+" TEXT,"+
                Database.b5_y+" TEXT,"+
                Database.b6_x+" TEXT,"+
                Database.b6_y+" TEXT,"+
                Database.b1_size+" TEXT,"+
                Database.b2_size+" TEXT,"+
                Database.b3_size+" TEXT,"+
                Database.b4_size+" TEXT,"+
                Database.b5_size+" TEXT,"+
                Database.b6_size+" TEXT,"+
                Database.p1_press+" TEXT,"+
                Database.p2_press+" TEXT,"+
                Database.p3_press+" TEXT,"+
                Database.p4_press+" TEXT,"+
                Database.p5_press+" TEXT,"+
                Database.p6_press+" TEXT,"+
                Database.b1_pressure+" TEXT,"+
                Database.b2_pressure+" TEXT,"+
                Database.b3_pressure+" TEXT,"+
                Database.b4_pressure+" TEXT,"+
                Database.b5_pressure+" TEXT,"+
                Database.b6_pressure+" TEXT,"+
                Database.p1_p2_flight+" TEXT,"+
                Database.p2_p3_flight+" TEXT,"+
                Database.p3_p4_flight+" TEXT,"+
                Database.p4_p5_flight+" TEXT,"+
                Database.p5_p6_flight+" TEXT,"+
                Database.p1_p2_digraph+" TEXT,"+
                Database.p2_p3_digraph+" TEXT,"+
                Database.p3_p4_digraph+" TEXT,"+
                Database.p4_p5_digraph+" TEXT,"+
                Database.p5_p6_digraph+" TEXT,"+
                Database.p1_p2_trigraph+" TEXT,"+
                Database.p2_p3_trigraph+" TEXT,"+
                Database.p3_p4_trigraph+" TEXT,"+
                Database.p4_p5_trigraph+" TEXT,"+
                Database.total_time+" TEXT );";
        db.execSQL(c);
            String cb="CREATE TABLE "+DatabaseBackup.TABLE__NAME+" ( "+
                    DatabaseBackup.b1__x+" TEXT,"+
                    DatabaseBackup.b1__y+" TEXT,"+
                    DatabaseBackup.b2__x+" TEXT,"+
                    DatabaseBackup.b2__y+" TEXT,"+
                    DatabaseBackup.b3__x+" TEXT,"+
                    DatabaseBackup.b3__y+" TEXT,"+
                    DatabaseBackup.b4__x+" TEXT,"+
                    DatabaseBackup.b4__y+" TEXT,"+
                    DatabaseBackup.b5__x+" TEXT,"+
                    DatabaseBackup.b5__y+" TEXT,"+
                    DatabaseBackup.b6__x+" TEXT,"+
                    DatabaseBackup.b6__y+" TEXT,"+
                    DatabaseBackup.b1__size+" TEXT,"+
                    DatabaseBackup.b2__size+" TEXT,"+
                    DatabaseBackup.b3__size+" TEXT,"+
                    DatabaseBackup.b4__size+" TEXT,"+
                    DatabaseBackup.b5__size+" TEXT,"+
                    DatabaseBackup.b6__size+" TEXT,"+
                    DatabaseBackup.p1__press+" TEXT,"+
                    DatabaseBackup.p2__press+" TEXT,"+
                    DatabaseBackup.p3__press+" TEXT,"+
                    DatabaseBackup.p4__press+" TEXT,"+
                    DatabaseBackup.p5__press+" TEXT,"+
                    DatabaseBackup.p6__press+" TEXT,"+
                    DatabaseBackup.b1__pressure+" TEXT,"+
                    DatabaseBackup.b2__pressure+" TEXT,"+
                    DatabaseBackup.b3__pressure+" TEXT,"+
                    DatabaseBackup.b4__pressure+" TEXT,"+
                    DatabaseBackup.b5__pressure+" TEXT,"+
                    DatabaseBackup.b6__pressure+" TEXT,"+
                    DatabaseBackup.p1__p2__flight+" TEXT,"+
                    DatabaseBackup.p2__p3__flight+" TEXT,"+
                    DatabaseBackup.p3__p4__flight+" TEXT,"+
                    DatabaseBackup.p4__p5__flight+" TEXT,"+
                    DatabaseBackup.p5__p6__flight+" TEXT,"+
                    DatabaseBackup.p1__p2__digraph+" TEXT,"+
                    DatabaseBackup.p2__p3__digraph+" TEXT,"+
                    DatabaseBackup.p3__p4__digraph+" TEXT,"+
                    DatabaseBackup.p4__p5__digraph+" TEXT,"+
                    DatabaseBackup.p5__p6__digraph+" TEXT,"+
                    DatabaseBackup.p1__p2__trigraph+" TEXT,"+
                    DatabaseBackup.p2__p3__trigraph+" TEXT,"+
                    DatabaseBackup.p3__p4__trigraph+" TEXT,"+
                    DatabaseBackup.p4__p5__trigraph+" TEXT,"+
                    DatabaseBackup.total__time+" TEXT );";
            db.execSQL(cb);
            String cl="CREATE TABLE "+Database.ILLEGITIMATE_TABLE_NAME+" ( "+
                    Database.b1_x+" TEXT,"+
                    Database.b1_y+" TEXT,"+
                    Database.b2_x+" TEXT,"+
                    Database.b2_y+" TEXT,"+
                    Database.b3_x+" TEXT,"+
                    Database.b3_y+" TEXT,"+
                    Database.b4_x+" TEXT,"+
                    Database.b4_y+" TEXT,"+
                    Database.b5_x+" TEXT,"+
                    Database.b5_y+" TEXT,"+
                    Database.b6_x+" TEXT,"+
                    Database.b6_y+" TEXT,"+
                    Database.b1_size+" TEXT,"+
                    Database.b2_size+" TEXT,"+
                    Database.b3_size+" TEXT,"+
                    Database.b4_size+" TEXT,"+
                    Database.b5_size+" TEXT,"+
                    Database.b6_size+" TEXT,"+
                    Database.p1_press+" TEXT,"+
                    Database.p2_press+" TEXT,"+
                    Database.p3_press+" TEXT,"+
                    Database.p4_press+" TEXT,"+
                    Database.p5_press+" TEXT,"+
                    Database.p6_press+" TEXT,"+
                    Database.b1_pressure+" TEXT,"+
                    Database.b2_pressure+" TEXT,"+
                    Database.b3_pressure+" TEXT,"+
                    Database.b4_pressure+" TEXT,"+
                    Database.b5_pressure+" TEXT,"+
                    Database.b6_pressure+" TEXT,"+
                    Database.p1_p2_flight+" TEXT,"+
                    Database.p2_p3_flight+" TEXT,"+
                    Database.p3_p4_flight+" TEXT,"+
                    Database.p4_p5_flight+" TEXT,"+
                    Database.p5_p6_flight+" TEXT,"+
                    Database.p1_p2_digraph+" TEXT,"+
                    Database.p2_p3_digraph+" TEXT,"+
                    Database.p3_p4_digraph+" TEXT,"+
                    Database.p4_p5_digraph+" TEXT,"+
                    Database.p5_p6_digraph+" TEXT,"+
                    Database.p1_p2_trigraph+" TEXT,"+
                    Database.p2_p3_trigraph+" TEXT,"+
                    Database.p3_p4_trigraph+" TEXT,"+
                    Database.p4_p5_trigraph+" TEXT,"+
                    Database.total_time+" TEXT );";
            db.execSQL(cl);

        String p="CREATE TABLE "+Database.PASSWORD_TABLE_NAME+" ( "+
                Database.password + " TEXT );";
        db.execSQL(p);

            String n="CREATE TABLE "+DatabaseNetwork.NETWORK_TABLE_NAME+" ( "+
                    DatabaseNetwork.wih11+" TEXT,"+
                    DatabaseNetwork.wih21+" TEXT,"+
                    DatabaseNetwork.wih31+" TEXT,"+
                    DatabaseNetwork.wih41+" TEXT,"+
                    DatabaseNetwork.wih51+" TEXT,"+
                    DatabaseNetwork.wih61+" TEXT,"+
                    DatabaseNetwork.wih71+" TEXT,"+
                    DatabaseNetwork.wih81+" TEXT,"+
                    DatabaseNetwork.wih91+" TEXT,"+
                    DatabaseNetwork.wih101+" TEXT,"+
                    DatabaseNetwork.wih111+" TEXT,"+
                    DatabaseNetwork.wih121+" TEXT,"+
                    DatabaseNetwork.wih131+" TEXT,"+
                    DatabaseNetwork.wih141+" TEXT,"+
                    DatabaseNetwork.wih151+" TEXT,"+
                    DatabaseNetwork.wih161+" TEXT,"+
                    DatabaseNetwork.wih171+" TEXT,"+
                    DatabaseNetwork.wih181+" TEXT,"+
                    DatabaseNetwork.wih191+" TEXT,"+
                    DatabaseNetwork.wih201+" TEXT,"+
                    DatabaseNetwork.wih211+" TEXT,"+
                    DatabaseNetwork.wih221+" TEXT,"+
                    DatabaseNetwork.wih231+" TEXT,"+
                    DatabaseNetwork.wih241+" TEXT,"+
                    DatabaseNetwork.wih251+" TEXT,"+
                    DatabaseNetwork.wih261+" TEXT,"+
                    DatabaseNetwork.wih271+" TEXT,"+
                    DatabaseNetwork.wih281+" TEXT,"+
                    DatabaseNetwork.wih291+" TEXT,"+
                    DatabaseNetwork.wih301+" TEXT,"+
                    DatabaseNetwork.wih311+" TEXT,"+
                    DatabaseNetwork.wih321+" TEXT,"+
                    DatabaseNetwork.wih331+" TEXT,"+
                    DatabaseNetwork.wih341+" TEXT,"+
                    DatabaseNetwork.wih351+" TEXT,"+
                    DatabaseNetwork.wih361+" TEXT,"+
                    DatabaseNetwork.wih371+" TEXT,"+
                    DatabaseNetwork.wih381+" TEXT,"+
                    DatabaseNetwork.wih391+" TEXT,"+
                    DatabaseNetwork.wih401+" TEXT,"+
                    DatabaseNetwork.wih411+" TEXT,"+
                    DatabaseNetwork.wih421+" TEXT,"+
                    DatabaseNetwork.wih431+" TEXT,"+
                    DatabaseNetwork.wih441+" TEXT,"+
                    DatabaseNetwork.wih451+" TEXT,"+

                    DatabaseNetwork.wih12+" TEXT,"+
                    DatabaseNetwork.wih22+" TEXT,"+
                    DatabaseNetwork.wih32+" TEXT,"+
                    DatabaseNetwork.wih42+" TEXT,"+
                    DatabaseNetwork.wih52+" TEXT,"+
                    DatabaseNetwork.wih62+" TEXT,"+
                    DatabaseNetwork.wih72+" TEXT,"+
                    DatabaseNetwork.wih82+" TEXT,"+
                    DatabaseNetwork.wih92+" TEXT,"+
                    DatabaseNetwork.wih102+" TEXT,"+
                    DatabaseNetwork.wih112+" TEXT,"+
                    DatabaseNetwork.wih122+" TEXT,"+
                    DatabaseNetwork.wih132+" TEXT,"+
                    DatabaseNetwork.wih142+" TEXT,"+
                    DatabaseNetwork.wih152+" TEXT,"+
                    DatabaseNetwork.wih162+" TEXT,"+
                    DatabaseNetwork.wih172+" TEXT,"+
                    DatabaseNetwork.wih182+" TEXT,"+
                    DatabaseNetwork.wih192+" TEXT,"+
                    DatabaseNetwork.wih202+" TEXT,"+
                    DatabaseNetwork.wih212+" TEXT,"+
                    DatabaseNetwork.wih222+" TEXT,"+
                    DatabaseNetwork.wih232+" TEXT,"+
                    DatabaseNetwork.wih242+" TEXT,"+
                    DatabaseNetwork.wih252+" TEXT,"+
                    DatabaseNetwork.wih262+" TEXT,"+
                    DatabaseNetwork.wih272+" TEXT,"+
                    DatabaseNetwork.wih282+" TEXT,"+
                    DatabaseNetwork.wih292+" TEXT,"+
                    DatabaseNetwork.wih302+" TEXT,"+
                    DatabaseNetwork.wih312+" TEXT,"+
                    DatabaseNetwork.wih322+" TEXT,"+
                    DatabaseNetwork.wih332+" TEXT,"+
                    DatabaseNetwork.wih342+" TEXT,"+
                    DatabaseNetwork.wih352+" TEXT,"+
                    DatabaseNetwork.wih362+" TEXT,"+
                    DatabaseNetwork.wih372+" TEXT,"+
                    DatabaseNetwork.wih382+" TEXT,"+
                    DatabaseNetwork.wih392+" TEXT,"+
                    DatabaseNetwork.wih402+" TEXT,"+
                    DatabaseNetwork.wih412+" TEXT,"+
                    DatabaseNetwork.wih422+" TEXT,"+
                    DatabaseNetwork.wih432+" TEXT,"+
                    DatabaseNetwork.wih442+" TEXT,"+
                    DatabaseNetwork.wih452+" TEXT,"+

                    DatabaseNetwork.wih13+" TEXT,"+
                    DatabaseNetwork.wih23+" TEXT,"+
                    DatabaseNetwork.wih33+" TEXT,"+
                    DatabaseNetwork.wih43+" TEXT,"+
                    DatabaseNetwork.wih53+" TEXT,"+
                    DatabaseNetwork.wih63+" TEXT,"+
                    DatabaseNetwork.wih73+" TEXT,"+
                    DatabaseNetwork.wih83+" TEXT,"+
                    DatabaseNetwork.wih93+" TEXT,"+
                    DatabaseNetwork.wih103+" TEXT,"+
                    DatabaseNetwork.wih113+" TEXT,"+
                    DatabaseNetwork.wih123+" TEXT,"+
                    DatabaseNetwork.wih133+" TEXT,"+
                    DatabaseNetwork.wih143+" TEXT,"+
                    DatabaseNetwork.wih153+" TEXT,"+
                    DatabaseNetwork.wih163+" TEXT,"+
                    DatabaseNetwork.wih173+" TEXT,"+
                    DatabaseNetwork.wih183+" TEXT,"+
                    DatabaseNetwork.wih193+" TEXT,"+
                    DatabaseNetwork.wih203+" TEXT,"+
                    DatabaseNetwork.wih213+" TEXT,"+
                    DatabaseNetwork.wih223+" TEXT,"+
                    DatabaseNetwork.wih233+" TEXT,"+
                    DatabaseNetwork.wih243+" TEXT,"+
                    DatabaseNetwork.wih253+" TEXT,"+
                    DatabaseNetwork.wih263+" TEXT,"+
                    DatabaseNetwork.wih273+" TEXT,"+
                    DatabaseNetwork.wih283+" TEXT,"+
                    DatabaseNetwork.wih293+" TEXT,"+
                    DatabaseNetwork.wih303+" TEXT,"+
                    DatabaseNetwork.wih313+" TEXT,"+
                    DatabaseNetwork.wih323+" TEXT,"+
                    DatabaseNetwork.wih333+" TEXT,"+
                    DatabaseNetwork.wih343+" TEXT,"+
                    DatabaseNetwork.wih353+" TEXT,"+
                    DatabaseNetwork.wih363+" TEXT,"+
                    DatabaseNetwork.wih373+" TEXT,"+
                    DatabaseNetwork.wih383+" TEXT,"+
                    DatabaseNetwork.wih393+" TEXT,"+
                    DatabaseNetwork.wih403+" TEXT,"+
                    DatabaseNetwork.wih413+" TEXT,"+
                    DatabaseNetwork.wih423+" TEXT,"+
                    DatabaseNetwork.wih433+" TEXT,"+
                    DatabaseNetwork.wih443+" TEXT,"+
                    DatabaseNetwork.wih453+" TEXT,"+

                    DatabaseNetwork.wih14+" TEXT,"+
                    DatabaseNetwork.wih24+" TEXT,"+
                    DatabaseNetwork.wih34+" TEXT,"+
                    DatabaseNetwork.wih44+" TEXT,"+
                    DatabaseNetwork.wih54+" TEXT,"+
                    DatabaseNetwork.wih64+" TEXT,"+
                    DatabaseNetwork.wih74+" TEXT,"+
                    DatabaseNetwork.wih84+" TEXT,"+
                    DatabaseNetwork.wih94+" TEXT,"+
                    DatabaseNetwork.wih104+" TEXT,"+
                    DatabaseNetwork.wih114+" TEXT,"+
                    DatabaseNetwork.wih124+" TEXT,"+
                    DatabaseNetwork.wih134+" TEXT,"+
                    DatabaseNetwork.wih144+" TEXT,"+
                    DatabaseNetwork.wih154+" TEXT,"+
                    DatabaseNetwork.wih164+" TEXT,"+
                    DatabaseNetwork.wih174+" TEXT,"+
                    DatabaseNetwork.wih184+" TEXT,"+
                    DatabaseNetwork.wih194+" TEXT,"+
                    DatabaseNetwork.wih204+" TEXT,"+
                    DatabaseNetwork.wih214+" TEXT,"+
                    DatabaseNetwork.wih224+" TEXT,"+
                    DatabaseNetwork.wih234+" TEXT,"+
                    DatabaseNetwork.wih244+" TEXT,"+
                    DatabaseNetwork.wih254+" TEXT,"+
                    DatabaseNetwork.wih264+" TEXT,"+
                    DatabaseNetwork.wih274+" TEXT,"+
                    DatabaseNetwork.wih284+" TEXT,"+
                    DatabaseNetwork.wih294+" TEXT,"+
                    DatabaseNetwork.wih304+" TEXT,"+
                    DatabaseNetwork.wih314+" TEXT,"+
                    DatabaseNetwork.wih324+" TEXT,"+
                    DatabaseNetwork.wih334+" TEXT,"+
                    DatabaseNetwork.wih344+" TEXT,"+
                    DatabaseNetwork.wih354+" TEXT,"+
                    DatabaseNetwork.wih364+" TEXT,"+
                    DatabaseNetwork.wih374+" TEXT,"+
                    DatabaseNetwork.wih384+" TEXT,"+
                    DatabaseNetwork.wih394+" TEXT,"+
                    DatabaseNetwork.wih404+" TEXT,"+
                    DatabaseNetwork.wih414+" TEXT,"+
                    DatabaseNetwork.wih424+" TEXT,"+
                    DatabaseNetwork.wih434+" TEXT,"+
                    DatabaseNetwork.wih444+" TEXT,"+
                    DatabaseNetwork.wih454+" TEXT,"+

                    DatabaseNetwork.wih15+" TEXT,"+
                    DatabaseNetwork.wih25+" TEXT,"+
                    DatabaseNetwork.wih35+" TEXT,"+
                    DatabaseNetwork.wih45+" TEXT,"+
                    DatabaseNetwork.wih55+" TEXT,"+
                    DatabaseNetwork.wih65+" TEXT,"+
                    DatabaseNetwork.wih75+" TEXT,"+
                    DatabaseNetwork.wih85+" TEXT,"+
                    DatabaseNetwork.wih95+" TEXT,"+
                    DatabaseNetwork.wih105+" TEXT,"+
                    DatabaseNetwork.wih115+" TEXT,"+
                    DatabaseNetwork.wih125+" TEXT,"+
                    DatabaseNetwork.wih135+" TEXT,"+
                    DatabaseNetwork.wih145+" TEXT,"+
                    DatabaseNetwork.wih155+" TEXT,"+
                    DatabaseNetwork.wih165+" TEXT,"+
                    DatabaseNetwork.wih175+" TEXT,"+
                    DatabaseNetwork.wih185+" TEXT,"+
                    DatabaseNetwork.wih195+" TEXT,"+
                    DatabaseNetwork.wih205+" TEXT,"+
                    DatabaseNetwork.wih215+" TEXT,"+
                    DatabaseNetwork.wih225+" TEXT,"+
                    DatabaseNetwork.wih235+" TEXT,"+
                    DatabaseNetwork.wih245+" TEXT,"+
                    DatabaseNetwork.wih255+" TEXT,"+
                    DatabaseNetwork.wih265+" TEXT,"+
                    DatabaseNetwork.wih275+" TEXT,"+
                    DatabaseNetwork.wih285+" TEXT,"+
                    DatabaseNetwork.wih295+" TEXT,"+
                    DatabaseNetwork.wih305+" TEXT,"+
                    DatabaseNetwork.wih315+" TEXT,"+
                    DatabaseNetwork.wih325+" TEXT,"+
                    DatabaseNetwork.wih335+" TEXT,"+
                    DatabaseNetwork.wih345+" TEXT,"+
                    DatabaseNetwork.wih355+" TEXT,"+
                    DatabaseNetwork.wih365+" TEXT,"+
                    DatabaseNetwork.wih375+" TEXT,"+
                    DatabaseNetwork.wih385+" TEXT,"+
                    DatabaseNetwork.wih395+" TEXT,"+
                    DatabaseNetwork.wih405+" TEXT,"+
                    DatabaseNetwork.wih415+" TEXT,"+
                    DatabaseNetwork.wih425+" TEXT,"+
                    DatabaseNetwork.wih435+" TEXT,"+
                    DatabaseNetwork.wih445+" TEXT,"+
                    DatabaseNetwork.wih455+" TEXT,"+

                    DatabaseNetwork.wih16+" TEXT,"+
                    DatabaseNetwork.wih26+" TEXT,"+
                    DatabaseNetwork.wih36+" TEXT,"+
                    DatabaseNetwork.wih46+" TEXT,"+
                    DatabaseNetwork.wih56+" TEXT,"+
                    DatabaseNetwork.wih66+" TEXT,"+
                    DatabaseNetwork.wih76+" TEXT,"+
                    DatabaseNetwork.wih86+" TEXT,"+
                    DatabaseNetwork.wih96+" TEXT,"+
                    DatabaseNetwork.wih106+" TEXT,"+
                    DatabaseNetwork.wih116+" TEXT,"+
                    DatabaseNetwork.wih126+" TEXT,"+
                    DatabaseNetwork.wih136+" TEXT,"+
                    DatabaseNetwork.wih146+" TEXT,"+
                    DatabaseNetwork.wih156+" TEXT,"+
                    DatabaseNetwork.wih166+" TEXT,"+
                    DatabaseNetwork.wih176+" TEXT,"+
                    DatabaseNetwork.wih186+" TEXT,"+
                    DatabaseNetwork.wih196+" TEXT,"+
                    DatabaseNetwork.wih206+" TEXT,"+
                    DatabaseNetwork.wih216+" TEXT,"+
                    DatabaseNetwork.wih226+" TEXT,"+
                    DatabaseNetwork.wih236+" TEXT,"+
                    DatabaseNetwork.wih246+" TEXT,"+
                    DatabaseNetwork.wih256+" TEXT,"+
                    DatabaseNetwork.wih266+" TEXT,"+
                    DatabaseNetwork.wih276+" TEXT,"+
                    DatabaseNetwork.wih286+" TEXT,"+
                    DatabaseNetwork.wih296+" TEXT,"+
                    DatabaseNetwork.wih306+" TEXT,"+
                    DatabaseNetwork.wih316+" TEXT,"+
                    DatabaseNetwork.wih326+" TEXT,"+
                    DatabaseNetwork.wih336+" TEXT,"+
                    DatabaseNetwork.wih346+" TEXT,"+
                    DatabaseNetwork.wih356+" TEXT,"+
                    DatabaseNetwork.wih366+" TEXT,"+
                    DatabaseNetwork.wih376+" TEXT,"+
                    DatabaseNetwork.wih386+" TEXT,"+
                    DatabaseNetwork.wih396+" TEXT,"+
                    DatabaseNetwork.wih406+" TEXT,"+
                    DatabaseNetwork.wih416+" TEXT,"+
                    DatabaseNetwork.wih426+" TEXT,"+
                    DatabaseNetwork.wih436+" TEXT,"+
                    DatabaseNetwork.wih446+" TEXT,"+
                    DatabaseNetwork.wih456+" TEXT,"+

                    DatabaseNetwork.wih17+" TEXT,"+
                    DatabaseNetwork.wih27+" TEXT,"+
                    DatabaseNetwork.wih37+" TEXT,"+
                    DatabaseNetwork.wih47+" TEXT,"+
                    DatabaseNetwork.wih57+" TEXT,"+
                    DatabaseNetwork.wih67+" TEXT,"+
                    DatabaseNetwork.wih77+" TEXT,"+
                    DatabaseNetwork.wih87+" TEXT,"+
                    DatabaseNetwork.wih97+" TEXT,"+
                    DatabaseNetwork.wih107+" TEXT,"+
                    DatabaseNetwork.wih117+" TEXT,"+
                    DatabaseNetwork.wih127+" TEXT,"+
                    DatabaseNetwork.wih137+" TEXT,"+
                    DatabaseNetwork.wih147+" TEXT,"+
                    DatabaseNetwork.wih157+" TEXT,"+
                    DatabaseNetwork.wih167+" TEXT,"+
                    DatabaseNetwork.wih177+" TEXT,"+
                    DatabaseNetwork.wih187+" TEXT,"+
                    DatabaseNetwork.wih197+" TEXT,"+
                    DatabaseNetwork.wih207+" TEXT,"+
                    DatabaseNetwork.wih217+" TEXT,"+
                    DatabaseNetwork.wih227+" TEXT,"+
                    DatabaseNetwork.wih237+" TEXT,"+
                    DatabaseNetwork.wih247+" TEXT,"+
                    DatabaseNetwork.wih257+" TEXT,"+
                    DatabaseNetwork.wih267+" TEXT,"+
                    DatabaseNetwork.wih277+" TEXT,"+
                    DatabaseNetwork.wih287+" TEXT,"+
                    DatabaseNetwork.wih297+" TEXT,"+
                    DatabaseNetwork.wih307+" TEXT,"+
                    DatabaseNetwork.wih317+" TEXT,"+
                    DatabaseNetwork.wih327+" TEXT,"+
                    DatabaseNetwork.wih337+" TEXT,"+
                    DatabaseNetwork.wih347+" TEXT,"+
                    DatabaseNetwork.wih357+" TEXT,"+
                    DatabaseNetwork.wih367+" TEXT,"+
                    DatabaseNetwork.wih377+" TEXT,"+
                    DatabaseNetwork.wih387+" TEXT,"+
                    DatabaseNetwork.wih397+" TEXT,"+
                    DatabaseNetwork.wih407+" TEXT,"+
                    DatabaseNetwork.wih417+" TEXT,"+
                    DatabaseNetwork.wih427+" TEXT,"+
                    DatabaseNetwork.wih437+" TEXT,"+
                    DatabaseNetwork.wih447+" TEXT,"+
                    DatabaseNetwork.wih457+" TEXT,"+

                    DatabaseNetwork.wih18+" TEXT,"+
                    DatabaseNetwork.wih28+" TEXT,"+
                    DatabaseNetwork.wih38+" TEXT,"+
                    DatabaseNetwork.wih48+" TEXT,"+
                    DatabaseNetwork.wih58+" TEXT,"+
                    DatabaseNetwork.wih68+" TEXT,"+
                    DatabaseNetwork.wih78+" TEXT,"+
                    DatabaseNetwork.wih88+" TEXT,"+
                    DatabaseNetwork.wih98+" TEXT,"+
                    DatabaseNetwork.wih108+" TEXT,"+
                    DatabaseNetwork.wih118+" TEXT,"+
                    DatabaseNetwork.wih128+" TEXT,"+
                    DatabaseNetwork.wih138+" TEXT,"+
                    DatabaseNetwork.wih148+" TEXT,"+
                    DatabaseNetwork.wih158+" TEXT,"+
                    DatabaseNetwork.wih168+" TEXT,"+
                    DatabaseNetwork.wih178+" TEXT,"+
                    DatabaseNetwork.wih188+" TEXT,"+
                    DatabaseNetwork.wih198+" TEXT,"+
                    DatabaseNetwork.wih208+" TEXT,"+
                    DatabaseNetwork.wih218+" TEXT,"+
                    DatabaseNetwork.wih228+" TEXT,"+
                    DatabaseNetwork.wih238+" TEXT,"+
                    DatabaseNetwork.wih248+" TEXT,"+
                    DatabaseNetwork.wih258+" TEXT,"+
                    DatabaseNetwork.wih268+" TEXT,"+
                    DatabaseNetwork.wih278+" TEXT,"+
                    DatabaseNetwork.wih288+" TEXT,"+
                    DatabaseNetwork.wih298+" TEXT,"+
                    DatabaseNetwork.wih308+" TEXT,"+
                    DatabaseNetwork.wih318+" TEXT,"+
                    DatabaseNetwork.wih328+" TEXT,"+
                    DatabaseNetwork.wih338+" TEXT,"+
                    DatabaseNetwork.wih348+" TEXT,"+
                    DatabaseNetwork.wih358+" TEXT,"+
                    DatabaseNetwork.wih368+" TEXT,"+
                    DatabaseNetwork.wih378+" TEXT,"+
                    DatabaseNetwork.wih388+" TEXT,"+
                    DatabaseNetwork.wih398+" TEXT,"+
                    DatabaseNetwork.wih408+" TEXT,"+
                    DatabaseNetwork.wih418+" TEXT,"+
                    DatabaseNetwork.wih428+" TEXT,"+
                    DatabaseNetwork.wih438+" TEXT,"+
                    DatabaseNetwork.wih448+" TEXT,"+
                    DatabaseNetwork.wih458+" TEXT, " +
                    DatabaseNetwork.wih19+" TEXT,"+
                    DatabaseNetwork.wih29+" TEXT,"+
                    DatabaseNetwork.wih39+" TEXT,"+
                    DatabaseNetwork.wih49+" TEXT,"+
                    DatabaseNetwork.wih59+" TEXT,"+
                    DatabaseNetwork.wih69+" TEXT,"+
                    DatabaseNetwork.wih79+" TEXT,"+
                    DatabaseNetwork.wih89+" TEXT,"+
                    DatabaseNetwork.wih99+" TEXT,"+
                    DatabaseNetwork.wih109+" TEXT,"+
                    DatabaseNetwork.wih119+" TEXT,"+
                    DatabaseNetwork.wih129+" TEXT,"+
                    DatabaseNetwork.wih139+" TEXT,"+
                    DatabaseNetwork.wih149+" TEXT,"+
                    DatabaseNetwork.wih159+" TEXT,"+
                    DatabaseNetwork.wih169+" TEXT,"+
                    DatabaseNetwork.wih179+" TEXT,"+
                    DatabaseNetwork.wih189+" TEXT,"+
                    DatabaseNetwork.wih199+" TEXT,"+
                    DatabaseNetwork.wih209+" TEXT,"+
                    DatabaseNetwork.wih219+" TEXT,"+
                    DatabaseNetwork.wih229+" TEXT,"+
                    DatabaseNetwork.wih239+" TEXT,"+
                    DatabaseNetwork.wih249+" TEXT,"+
                    DatabaseNetwork.wih259+" TEXT,"+
                    DatabaseNetwork.wih269+" TEXT,"+
                    DatabaseNetwork.wih279+" TEXT,"+
                    DatabaseNetwork.wih289+" TEXT,"+
                    DatabaseNetwork.wih299+" TEXT,"+
                    DatabaseNetwork.wih309+" TEXT,"+
                    DatabaseNetwork.wih319+" TEXT,"+
                    DatabaseNetwork.wih329+" TEXT,"+
                    DatabaseNetwork.wih339+" TEXT,"+
                    DatabaseNetwork.wih349+" TEXT,"+
                    DatabaseNetwork.wih359+" TEXT,"+
                    DatabaseNetwork.wih369+" TEXT,"+
                    DatabaseNetwork.wih379+" TEXT,"+
                    DatabaseNetwork.wih389+" TEXT,"+
                    DatabaseNetwork.wih399+" TEXT,"+
                    DatabaseNetwork.wih409+" TEXT,"+
                    DatabaseNetwork.wih419+" TEXT,"+
                    DatabaseNetwork.wih429+" TEXT,"+
                    DatabaseNetwork.wih439+" TEXT,"+
                    DatabaseNetwork.wih449+" TEXT,"+
                    DatabaseNetwork.wih459+" TEXT,"+

                    DatabaseNetwork.wih110+" TEXT,"+
                    DatabaseNetwork.wih210+" TEXT,"+
                    DatabaseNetwork.wih310+" TEXT,"+
                    DatabaseNetwork.wih410+" TEXT,"+
                    DatabaseNetwork.wih510+" TEXT,"+
                    DatabaseNetwork.wih610+" TEXT,"+
                    DatabaseNetwork.wih710+" TEXT,"+
                    DatabaseNetwork.wih810+" TEXT,"+
                    DatabaseNetwork.wih910+" TEXT,"+
                    DatabaseNetwork.wih1010+" TEXT,"+
                    DatabaseNetwork.wih1110+" TEXT,"+
                    DatabaseNetwork.wih1210+" TEXT,"+
                    DatabaseNetwork.wih1310+" TEXT,"+
                    DatabaseNetwork.wih1410+" TEXT,"+
                    DatabaseNetwork.wih1510+" TEXT,"+
                    DatabaseNetwork.wih1610+" TEXT,"+
                    DatabaseNetwork.wih1710+" TEXT,"+
                    DatabaseNetwork.wih1810+" TEXT,"+
                    DatabaseNetwork.wih1910+" TEXT,"+
                    DatabaseNetwork.wih2010+" TEXT,"+
                    DatabaseNetwork.wih2110+" TEXT,"+
                    DatabaseNetwork.wih2210+" TEXT,"+
                    DatabaseNetwork.wih2310+" TEXT,"+
                    DatabaseNetwork.wih2410+" TEXT,"+
                    DatabaseNetwork.wih2510+" TEXT,"+
                    DatabaseNetwork.wih2610+" TEXT,"+
                    DatabaseNetwork.wih2710+" TEXT,"+
                    DatabaseNetwork.wih2810+" TEXT,"+
                    DatabaseNetwork.wih2910+" TEXT,"+
                    DatabaseNetwork.wih3010+" TEXT,"+
                    DatabaseNetwork.wih3110+" TEXT,"+
                    DatabaseNetwork.wih3210+" TEXT,"+
                    DatabaseNetwork.wih3310+" TEXT,"+
                    DatabaseNetwork.wih3410+" TEXT,"+
                    DatabaseNetwork.wih3510+" TEXT,"+
                    DatabaseNetwork.wih3610+" TEXT,"+
                    DatabaseNetwork.wih3710+" TEXT,"+
                    DatabaseNetwork.wih3810+" TEXT,"+
                    DatabaseNetwork.wih3910+" TEXT,"+
                    DatabaseNetwork.wih4010+" TEXT,"+
                    DatabaseNetwork.wih4110+" TEXT,"+
                    DatabaseNetwork.wih4210+" TEXT,"+
                    DatabaseNetwork.wih4310+" TEXT,"+
                    DatabaseNetwork.wih4410+" TEXT,"+
                    DatabaseNetwork.wih4510+" TEXT,"+

                    DatabaseNetwork.wih_11+" TEXT,"+
                    DatabaseNetwork.wih_21+" TEXT,"+
                    DatabaseNetwork.wih_31+" TEXT,"+
                    DatabaseNetwork.wih_41+" TEXT,"+
                    DatabaseNetwork.wih_51+" TEXT,"+
                    DatabaseNetwork.wih_61+" TEXT,"+
                    DatabaseNetwork.wih_71+" TEXT,"+
                    DatabaseNetwork.wih_81+" TEXT,"+
                    DatabaseNetwork.wih_91+" TEXT,"+
                    DatabaseNetwork.wih_101+" TEXT,"+
                    DatabaseNetwork.wih_111+" TEXT,"+
                    DatabaseNetwork.wih_121+" TEXT,"+
                    DatabaseNetwork.wih_131+" TEXT,"+
                    DatabaseNetwork.wih_141+" TEXT,"+
                    DatabaseNetwork.wih_151+" TEXT,"+
                    DatabaseNetwork.wih_161+" TEXT,"+
                    DatabaseNetwork.wih_171+" TEXT,"+
                    DatabaseNetwork.wih_181+" TEXT,"+
                    DatabaseNetwork.wih_191+" TEXT,"+
                    DatabaseNetwork.wih_201+" TEXT,"+
                    DatabaseNetwork.wih_211+" TEXT,"+
                    DatabaseNetwork.wih_221+" TEXT,"+
                    DatabaseNetwork.wih_231+" TEXT,"+
                    DatabaseNetwork.wih_241+" TEXT,"+
                    DatabaseNetwork.wih_251+" TEXT,"+
                    DatabaseNetwork.wih_261+" TEXT,"+
                    DatabaseNetwork.wih_271+" TEXT,"+
                    DatabaseNetwork.wih_281+" TEXT,"+
                    DatabaseNetwork.wih_291+" TEXT,"+
                    DatabaseNetwork.wih_301+" TEXT,"+
                    DatabaseNetwork.wih_311+" TEXT,"+
                    DatabaseNetwork.wih_321+" TEXT,"+
                    DatabaseNetwork.wih_331+" TEXT,"+
                    DatabaseNetwork.wih_341+" TEXT,"+
                    DatabaseNetwork.wih_351+" TEXT,"+
                    DatabaseNetwork.wih_361+" TEXT,"+
                    DatabaseNetwork.wih_371+" TEXT,"+
                    DatabaseNetwork.wih_381+" TEXT,"+
                    DatabaseNetwork.wih_391+" TEXT,"+
                    DatabaseNetwork.wih_401+" TEXT,"+
                    DatabaseNetwork.wih_411+" TEXT,"+
                    DatabaseNetwork.wih_421+" TEXT,"+
                    DatabaseNetwork.wih_431+" TEXT,"+
                    DatabaseNetwork.wih_441+" TEXT,"+
                    DatabaseNetwork.wih_451+" TEXT,"+

                    DatabaseNetwork.wih_12+" TEXT,"+
                    DatabaseNetwork.wih_22+" TEXT,"+
                    DatabaseNetwork.wih_32+" TEXT,"+
                    DatabaseNetwork.wih_42+" TEXT,"+
                    DatabaseNetwork.wih_52+" TEXT,"+
                    DatabaseNetwork.wih_62+" TEXT,"+
                    DatabaseNetwork.wih_72+" TEXT,"+
                    DatabaseNetwork.wih_82+" TEXT,"+
                    DatabaseNetwork.wih_92+" TEXT,"+
                    DatabaseNetwork.wih_102+" TEXT,"+
                    DatabaseNetwork.wih_112+" TEXT,"+
                    DatabaseNetwork.wih_122+" TEXT,"+
                    DatabaseNetwork.wih_132+" TEXT,"+
                    DatabaseNetwork.wih_142+" TEXT,"+
                    DatabaseNetwork.wih_152+" TEXT,"+
                    DatabaseNetwork.wih_162+" TEXT,"+
                    DatabaseNetwork.wih_172+" TEXT,"+
                    DatabaseNetwork.wih_182+" TEXT,"+
                    DatabaseNetwork.wih_192+" TEXT,"+
                    DatabaseNetwork.wih_202+" TEXT,"+
                    DatabaseNetwork.wih_212+" TEXT,"+
                    DatabaseNetwork.wih_222+" TEXT,"+
                    DatabaseNetwork.wih_232+" TEXT,"+
                    DatabaseNetwork.wih_242+" TEXT,"+
                    DatabaseNetwork.wih_252+" TEXT,"+
                    DatabaseNetwork.wih_262+" TEXT,"+
                    DatabaseNetwork.wih_272+" TEXT,"+
                    DatabaseNetwork.wih_282+" TEXT,"+
                    DatabaseNetwork.wih_292+" TEXT,"+
                    DatabaseNetwork.wih_302+" TEXT,"+
                    DatabaseNetwork.wih_312+" TEXT,"+
                    DatabaseNetwork.wih_322+" TEXT,"+
                    DatabaseNetwork.wih_332+" TEXT,"+
                    DatabaseNetwork.wih_342+" TEXT,"+
                    DatabaseNetwork.wih_352+" TEXT,"+
                    DatabaseNetwork.wih_362+" TEXT,"+
                    DatabaseNetwork.wih_372+" TEXT,"+
                    DatabaseNetwork.wih_382+" TEXT,"+
                    DatabaseNetwork.wih_392+" TEXT,"+
                    DatabaseNetwork.wih_402+" TEXT,"+
                    DatabaseNetwork.wih_412+" TEXT,"+
                    DatabaseNetwork.wih_422+" TEXT,"+
                    DatabaseNetwork.wih_432+" TEXT,"+
                    DatabaseNetwork.wih_442+" TEXT,"+
                    DatabaseNetwork.wih_452+" TEXT,"+

                    DatabaseNetwork.wih_13+" TEXT,"+
                    DatabaseNetwork.wih_23+" TEXT,"+
                    DatabaseNetwork.wih_33+" TEXT,"+
                    DatabaseNetwork.wih_43+" TEXT,"+
                    DatabaseNetwork.wih_53+" TEXT,"+
                    DatabaseNetwork.wih_63+" TEXT,"+
                    DatabaseNetwork.wih_73+" TEXT,"+
                    DatabaseNetwork.wih_83+" TEXT,"+
                    DatabaseNetwork.wih_93+" TEXT,"+
                    DatabaseNetwork.wih_103+" TEXT,"+
                    DatabaseNetwork.wih_113+" TEXT,"+
                    DatabaseNetwork.wih_123+" TEXT,"+
                    DatabaseNetwork.wih_133+" TEXT,"+
                    DatabaseNetwork.wih_143+" TEXT,"+
                    DatabaseNetwork.wih_153+" TEXT,"+
                    DatabaseNetwork.wih_163+" TEXT,"+
                    DatabaseNetwork.wih_173+" TEXT,"+
                    DatabaseNetwork.wih_183+" TEXT,"+
                    DatabaseNetwork.wih_193+" TEXT,"+
                    DatabaseNetwork.wih_203+" TEXT,"+
                    DatabaseNetwork.wih_213+" TEXT,"+
                    DatabaseNetwork.wih_223+" TEXT,"+
                    DatabaseNetwork.wih_233+" TEXT,"+
                    DatabaseNetwork.wih_243+" TEXT,"+
                    DatabaseNetwork.wih_253+" TEXT,"+
                    DatabaseNetwork.wih_263+" TEXT,"+
                    DatabaseNetwork.wih_273+" TEXT,"+
                    DatabaseNetwork.wih_283+" TEXT,"+
                    DatabaseNetwork.wih_293+" TEXT,"+
                    DatabaseNetwork.wih_303+" TEXT,"+
                    DatabaseNetwork.wih_313+" TEXT,"+
                    DatabaseNetwork.wih_323+" TEXT,"+
                    DatabaseNetwork.wih_333+" TEXT,"+
                    DatabaseNetwork.wih_343+" TEXT,"+
                    DatabaseNetwork.wih_353+" TEXT,"+
                    DatabaseNetwork.wih_363+" TEXT,"+
                    DatabaseNetwork.wih_373+" TEXT,"+
                    DatabaseNetwork.wih_383+" TEXT,"+
                    DatabaseNetwork.wih_393+" TEXT,"+
                    DatabaseNetwork.wih_403+" TEXT,"+
                    DatabaseNetwork.wih_413+" TEXT,"+
                    DatabaseNetwork.wih_423+" TEXT,"+
                    DatabaseNetwork.wih_433+" TEXT,"+
                    DatabaseNetwork.wih_443+" TEXT,"+
                    DatabaseNetwork.wih_453+" TEXT,"+

                    DatabaseNetwork.wih_14+" TEXT,"+
                    DatabaseNetwork.wih_24+" TEXT,"+
                    DatabaseNetwork.wih_34+" TEXT,"+
                    DatabaseNetwork.wih_44+" TEXT,"+
                    DatabaseNetwork.wih_54+" TEXT,"+
                    DatabaseNetwork.wih_64+" TEXT,"+
                    DatabaseNetwork.wih_74+" TEXT,"+
                    DatabaseNetwork.wih_84+" TEXT,"+
                    DatabaseNetwork.wih_94+" TEXT,"+
                    DatabaseNetwork.wih_104+" TEXT,"+
                    DatabaseNetwork.wih_114+" TEXT,"+
                    DatabaseNetwork.wih_124+" TEXT,"+
                    DatabaseNetwork.wih_134+" TEXT,"+
                    DatabaseNetwork.wih_144+" TEXT,"+
                    DatabaseNetwork.wih_154+" TEXT,"+
                    DatabaseNetwork.wih_164+" TEXT,"+
                    DatabaseNetwork.wih_174+" TEXT,"+
                    DatabaseNetwork.wih_184+" TEXT,"+
                    DatabaseNetwork.wih_194+" TEXT,"+
                    DatabaseNetwork.wih_204+" TEXT,"+
                    DatabaseNetwork.wih_214+" TEXT,"+
                    DatabaseNetwork.wih_224+" TEXT,"+
                    DatabaseNetwork.wih_234+" TEXT,"+
                    DatabaseNetwork.wih_244+" TEXT,"+
                    DatabaseNetwork.wih_254+" TEXT,"+
                    DatabaseNetwork.wih_264+" TEXT,"+
                    DatabaseNetwork.wih_274+" TEXT,"+
                    DatabaseNetwork.wih_284+" TEXT,"+
                    DatabaseNetwork.wih_294+" TEXT,"+
                    DatabaseNetwork.wih_304+" TEXT,"+
                    DatabaseNetwork.wih_314+" TEXT,"+
                    DatabaseNetwork.wih_324+" TEXT,"+
                    DatabaseNetwork.wih_334+" TEXT,"+
                    DatabaseNetwork.wih_344+" TEXT,"+
                    DatabaseNetwork.wih_354+" TEXT,"+
                    DatabaseNetwork.wih_364+" TEXT,"+
                    DatabaseNetwork.wih_374+" TEXT,"+
                    DatabaseNetwork.wih_384+" TEXT,"+
                    DatabaseNetwork.wih_394+" TEXT,"+
                    DatabaseNetwork.wih_404+" TEXT,"+
                    DatabaseNetwork.wih_414+" TEXT,"+
                    DatabaseNetwork.wih_424+" TEXT,"+
                    DatabaseNetwork.wih_434+" TEXT,"+
                    DatabaseNetwork.wih_444+" TEXT,"+
                    DatabaseNetwork.wih_454+" TEXT,"+

                    DatabaseNetwork.wih_15+" TEXT,"+
                    DatabaseNetwork.wih_25+" TEXT,"+
                    DatabaseNetwork.wih_35+" TEXT,"+
                    DatabaseNetwork.wih_45+" TEXT,"+
                    DatabaseNetwork.wih_55+" TEXT,"+
                    DatabaseNetwork.wih_65+" TEXT,"+
                    DatabaseNetwork.wih_75+" TEXT,"+
                    DatabaseNetwork.wih_85+" TEXT,"+
                    DatabaseNetwork.wih_95+" TEXT,"+
                    DatabaseNetwork.wih_105+" TEXT,"+
                    DatabaseNetwork.wih_115+" TEXT,"+
                    DatabaseNetwork.wih_125+" TEXT,"+
                    DatabaseNetwork.wih_135+" TEXT,"+
                    DatabaseNetwork.wih_145+" TEXT,"+
                    DatabaseNetwork.wih_155+" TEXT,"+
                    DatabaseNetwork.wih_165+" TEXT,"+
                    DatabaseNetwork.wih_175+" TEXT,"+
                    DatabaseNetwork.wih_185+" TEXT,"+
                    DatabaseNetwork.wih_195+" TEXT,"+
                    DatabaseNetwork.wih_205+" TEXT,"+
                    DatabaseNetwork.wih_215+" TEXT,"+
                    DatabaseNetwork.wih_225+" TEXT,"+
                    DatabaseNetwork.wih_235+" TEXT,"+
                    DatabaseNetwork.wih_245+" TEXT,"+
                    DatabaseNetwork.wih_255+" TEXT,"+
                    DatabaseNetwork.wih_265+" TEXT,"+
                    DatabaseNetwork.wih_275+" TEXT,"+
                    DatabaseNetwork.wih_285+" TEXT,"+
                    DatabaseNetwork.wih_295+" TEXT,"+
                    DatabaseNetwork.wih_305+" TEXT,"+
                    DatabaseNetwork.wih_315+" TEXT,"+
                    DatabaseNetwork.wih_325+" TEXT,"+
                    DatabaseNetwork.wih_335+" TEXT,"+
                    DatabaseNetwork.wih_345+" TEXT,"+
                    DatabaseNetwork.wih_355+" TEXT,"+
                    DatabaseNetwork.wih_365+" TEXT,"+
                    DatabaseNetwork.wih_375+" TEXT,"+
                    DatabaseNetwork.wih_385+" TEXT,"+
                    DatabaseNetwork.wih_395+" TEXT,"+
                    DatabaseNetwork.wih_405+" TEXT,"+
                    DatabaseNetwork.wih_415+" TEXT,"+
                    DatabaseNetwork.wih_425+" TEXT,"+
                    DatabaseNetwork.wih_435+" TEXT,"+
                    DatabaseNetwork.wih_445+" TEXT,"+
                    DatabaseNetwork.wih_455+" TEXT,"+

                    DatabaseNetwork.wih_16+" TEXT,"+
                    DatabaseNetwork.wih_26+" TEXT,"+
                    DatabaseNetwork.wih_36+" TEXT,"+
                    DatabaseNetwork.wih_46+" TEXT,"+
                    DatabaseNetwork.wih_56+" TEXT,"+
                    DatabaseNetwork.wih_66+" TEXT,"+
                    DatabaseNetwork.wih_76+" TEXT,"+
                    DatabaseNetwork.wih_86+" TEXT,"+
                    DatabaseNetwork.wih_96+" TEXT,"+
                    DatabaseNetwork.wih_106+" TEXT,"+
                    DatabaseNetwork.wih_116+" TEXT,"+
                    DatabaseNetwork.wih_126+" TEXT,"+
                    DatabaseNetwork.wih_136+" TEXT,"+
                    DatabaseNetwork.wih_146+" TEXT,"+
                    DatabaseNetwork.wih_156+" TEXT,"+
                    DatabaseNetwork.wih_166+" TEXT,"+
                    DatabaseNetwork.wih_176+" TEXT,"+
                    DatabaseNetwork.wih_186+" TEXT,"+
                    DatabaseNetwork.wih_196+" TEXT,"+
                    DatabaseNetwork.wih_206+" TEXT,"+
                    DatabaseNetwork.wih_216+" TEXT,"+
                    DatabaseNetwork.wih_226+" TEXT,"+
                    DatabaseNetwork.wih_236+" TEXT,"+
                    DatabaseNetwork.wih_246+" TEXT,"+
                    DatabaseNetwork.wih_256+" TEXT,"+
                    DatabaseNetwork.wih_266+" TEXT,"+
                    DatabaseNetwork.wih_276+" TEXT,"+
                    DatabaseNetwork.wih_286+" TEXT,"+
                    DatabaseNetwork.wih_296+" TEXT,"+
                    DatabaseNetwork.wih_306+" TEXT,"+
                    DatabaseNetwork.wih_316+" TEXT,"+
                    DatabaseNetwork.wih_326+" TEXT,"+
                    DatabaseNetwork.wih_336+" TEXT,"+
                    DatabaseNetwork.wih_346+" TEXT,"+
                    DatabaseNetwork.wih_356+" TEXT,"+
                    DatabaseNetwork.wih_366+" TEXT,"+
                    DatabaseNetwork.wih_376+" TEXT,"+
                    DatabaseNetwork.wih_386+" TEXT,"+
                    DatabaseNetwork.wih_396+" TEXT,"+
                    DatabaseNetwork.wih_406+" TEXT,"+
                    DatabaseNetwork.wih_416+" TEXT,"+
                    DatabaseNetwork.wih_426+" TEXT,"+
                    DatabaseNetwork.wih_436+" TEXT,"+
                    DatabaseNetwork.wih_446+" TEXT,"+
                    DatabaseNetwork.wih_456+" TEXT );";


            db.execSQL(n);


            String o="CREATE TABLE "+DatabaseNetwork.hidden_to_output+" ( "+
                    DatabaseNetwork.who11 + " TEXT ,"+
                    DatabaseNetwork.who21 + " TEXT ,"+
                    DatabaseNetwork.who31 + " TEXT ,"+
                    DatabaseNetwork.who41 + " TEXT ,"+
                    DatabaseNetwork.who51 + " TEXT ,"+
                    DatabaseNetwork.who61 + " TEXT ,"+
                    DatabaseNetwork.who71 + " TEXT ,"+
                    DatabaseNetwork.who81 + " TEXT ,"+
                    DatabaseNetwork.who91 + " TEXT ,"+
                    DatabaseNetwork.who101 + " TEXT ,"+
                    DatabaseNetwork.who_11 + " TEXT ,"+
                    DatabaseNetwork.who_21 + " TEXT ,"+
                    DatabaseNetwork.who_31 + " TEXT ,"+
                    DatabaseNetwork.who_41 + " TEXT ,"+
                    DatabaseNetwork.who_51 + " TEXT ,"+
                    DatabaseNetwork.who_61 + " TEXT ,"+
                    DatabaseNetwork.who12 + " TEXT ,"+
                    DatabaseNetwork.who22 + " TEXT ,"+
                    DatabaseNetwork.who32 + " TEXT ,"+
                    DatabaseNetwork.who42 + " TEXT ,"+
                    DatabaseNetwork.who52 + " TEXT ,"+
                    DatabaseNetwork.who62 + " TEXT ,"+
                    DatabaseNetwork.who72 + " TEXT ,"+
                    DatabaseNetwork.who82 + " TEXT, " +
                    DatabaseNetwork.who92 + " TEXT, " +
                    DatabaseNetwork.who102 + " TEXT, " +
                    DatabaseNetwork.who_12 + " TEXT ,"+
                    DatabaseNetwork.who_22 + " TEXT ,"+
                    DatabaseNetwork.who_32 + " TEXT ,"+
                    DatabaseNetwork.who_42 + " TEXT ,"+
                    DatabaseNetwork.who_52 + " TEXT ,"+
                    DatabaseNetwork.who_62 + " TEXT );";

        db.execSQL(o);


        String error="CREATE TABLE "+DatabaseNetwork.error_table+" ( "+
                    DatabaseNetwork.error1 + " TEXT ,"+
                   DatabaseNetwork.error2 + " TEXT );";

        db.execSQL(error);
        String target="CREATE TABLE "+DatabaseNetwork.output_table+" ( "+
                    DatabaseNetwork.output1 + " TEXT ,"+
                    DatabaseNetwork.output2 + " TEXT );";

        db.execSQL(target);

        String training="CREATE TABLE "+Database.TRAINING_TABLE_NAME+" ( "+
                    Database.TRAINING_STATUS + " TEXT );";
        db.execSQL(training);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}

