package com.example.keystrokedynamics;
public class Database {
    public static final String KEYSTROKE_DYNAMICS="Keystroke_dynamics";
    public static final int VERSION=1;

    int decision = 0;

    public static final String PASSWORD_TABLE_NAME="Password_table";
    public static final String password="password";

    public static final String TABLE_NAME="Keystroke_values";

    public static final String TRAINING_TABLE_NAME="training_table";
    public static final String TRAINING_STATUS="training_status";

    public static final String ILLEGITIMATE_TABLE_NAME="illegitimate_Keystroke_values";

    //Co ordinates
    public static final String b1_x="b1_x";
    public static final String b1_y="b1_y";
    public static final String b2_x="b2_x";
    public static final String b2_y="b2_y";
    public static final String b3_x="b3_x";
    public static final String b3_y="b3_y";
    public static final String b4_y="b4_y";
    public static final String b4_x="b4_x";
    public static final String b5_y="b5_y";
    public static final String b5_x="b5_x";
    public static final String b6_y="b6_y";
    public static final String b6_x="b6_x";

    //Size of finger touch for each button
    public static final String b1_size="b1_size";
    public static final String b2_size="b2_size";
    public static final String b3_size="b3_size";
    public static final String b4_size="b4_size";
    public static final String b5_size="b5_size";
    public static final String b6_size="b6_size";

    //Press Time for 6 digit Password
    public static final String p1_press="p1_press";
    public static final String p2_press="p2_press";
    public static final String p3_press="p3_press";
    public static final String p4_press="p4_press";
    public static final String p5_press="p5_press";
    public static final String p6_press="p6_press";

    //Pressure for each button pressed
    public static final String b1_pressure="b1_pressure";
    public static final String b2_pressure="b2_pressure";
    public static final String b3_pressure="b3_pressure";
    public static final String b4_pressure="b4_pressure";
    public static final String b5_pressure="b5_pressure";
    public static final String b6_pressure="b6_pressure";

    //Flight time between two buttons
    public static final String p1_p2_flight="p1_p2_flight";
    public static final String p2_p3_flight="p2_p3_flight";
    public static final String p3_p4_flight="p3_p4_flight";
    public static final String p4_p5_flight="p4_p5_flight";
    public static final String p5_p6_flight="p5_p6_flight";

    //Digraph
    public static final String p1_p2_digraph="p1_p2_digraph";
    public static final String p2_p3_digraph="p2_p3_digraph";
    public static final String p3_p4_digraph="p3_p4_digraph";
    public static final String p4_p5_digraph="p4_p5_digraph";
    public static final String p5_p6_digraph="p5_p6_digraph";

    //Trigraph
    public static final String p1_p2_trigraph="p1_p2_trigraph";
    public static final String p2_p3_trigraph="p2_p3_trigraph";
    public static final String p3_p4_trigraph="p3_p4_trigraph";
    public static final String p4_p5_trigraph="p4_p5_trigraph";

    //Word per minute
    public static final String total_time="total_time";
}

