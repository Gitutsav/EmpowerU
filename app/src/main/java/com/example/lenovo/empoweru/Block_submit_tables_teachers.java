package com.example.lenovo.empoweru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Block_submit_tables_teachers extends SQLiteOpenHelper {
    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "block_submit_db_teachers8.db";
    private static final String TABLE1="submit_table1";
    private static final String TABLE2="submit_table2";
    private static final String TABLE3="submit_table3";
    private static  String submit_1 =
            "create table  submit_table1(attendence_id integer primary key autoincrement, "
                    + "lat double ,"+"longi double ,"+"accuracy integer,"+"slot_id smallint,"+"taken_by_id integer,"+"taken_on_time text,"
                    +"datet text,"+"school_id integer,"+"school_name text,"+"block_name text,"+"cluster_name text,"+"remark text,"+"flag integer);";
    private static  String submit_2=
            "create table submit_table2(attendence_id integer , "
                    + "user_id integer ,"+"present_status integer,"+"flag integer);";
    private static  String submit_3=
            "create table submit_table3(attendence_id integer,"+"school_name text,"+"block_name text,"+"cluster_name text,"+"present integer, "
                    + "absent integer ,"+"leave integer,"+"datet text,"+"remark text,"+"flag integer);";
    public Block_submit_tables_teachers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(submit_1);
        db.execSQL(submit_2);
        db.execSQL(submit_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS submit_table1 " );
        db.execSQL("DROP TABLE IF EXISTS submit_table2 " );
        db.execSQL("DROP TABLE IF EXISTS submit_table3");
        onCreate(db);
    }

    public long insersubmittable1(Double lat,Double longi,int accuracy,Integer slot_id,Integer taken_by,
                            String taken_on,String datet,Integer school_id,String block_name
                                  , String cluster_name,String school_name,String remark, Integer flag){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("lat", lat);
        values.put("longi",longi);
        values.put("accuracy",accuracy);
        values.put("slot_id",slot_id);
        values.put("taken_by_id",taken_by);
        values.put("taken_on_time",taken_on);
        values.put("datet",datet);
        values.put("school_id",school_id);
        values.put("school_name",school_name );
        values.put("block_name",block_name);
        values.put("cluster_name",cluster_name);
        values.put("remark",remark);
        values.put("flag",flag);

        long id = db.insert(TABLE1, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsubmittable2(Integer attendence_id,Integer user_id,Integer present_status,Integer flag){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("user_id",user_id);
        values.put("present_status",present_status);
        values.put("flag",flag);

        long id = db.insert(TABLE2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsubmittable3(Integer attendence_id,Integer present,Integer absent,
                                   String block_name, String cluster_name,String school_name,
                                   Integer leave,Integer flag,String remark,String datet){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("present", present);
        values.put("absent",absent);
        values.put("leave",leave);
        values.put("flag",flag);
        values.put("remark",remark);
        values.put("school_name",school_name );
        values.put("block_name",block_name);
        values.put("cluster_name",cluster_name);
        values.put("datet",datet);

        long id = db.insert(TABLE3, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public Cursor getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        return res;
    }
    public Cursor getAllData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE2,null);
        return res;
    }
    public Cursor getAllData3() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE3,null);
        return res;
    }
    public void update(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("flag",1);

        // updating row
        //return db.update(submit_3, values,  "attendence_id = "+String.valueOf(id), null);
       String strSQL = "UPDATE submit_table3 SET flag = 1 WHERE attendence_id = "+ id;

        db.execSQL(strSQL);
    }
    public Cursor getAllData11(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM submit_table1 WHERE attendence_id = "+ id,null);
        return res;
    }
    public Cursor getAllData22(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM submit_table2 WHERE attendence_id = "+ id,null);
        return res;
    }

}
