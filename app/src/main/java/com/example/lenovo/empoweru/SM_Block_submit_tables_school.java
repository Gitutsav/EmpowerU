package com.example.lenovo.empoweru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SM_Block_submit_tables_school extends SQLiteOpenHelper {
    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sm_block_submit_db_teachers15.db";
    private static final String TABLE1="submit_table1";
    private static final String TABLE2="submit_table2";
    private static final String TABLE3="submit_table3";
    private static final String TABLE4="submit_table4";
    private static final String TABLE5="submit_table5";
    private static  String submit_1 =
            "create table  submit_table1(attendence_id integer primary key autoincrement, "
                    + "lat double ,"+"longi double ,"+"accuracy integer,"+"slot_id smallint,"+"taken_by_id integer,"+"taken_on_time text,"
                    +"datet text,"+"school_id integer,"+"school_name text,"+"block_name text,"+"cluster_name text,"+"remark text,"+"flag integer);";
    private static  String submit_2=
            "create table submit_table2(attendence_id integer , "
                    + "mq_id integer ,"+"option_id integer,"+"m_answer text);";
    private static  String submit_3=
            "create table submit_table3(attendence_id integer,"+"school_name text,"+"block_name text,"+"cluster_name text,"+"datet text,"+"remark text,"+"flag integer);";
    private static  String submit_4=
            "create table submit_table4(attendence_id integer , "
                    + "my_image text ,"+"img_remark text);";
    private static  String submit_5=
            "create table submit_table5(attendence_id integer , "+"category_id integer , "
                    + "my_image text ,"+"img_remark text);";
    public SM_Block_submit_tables_school(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(submit_1);
        db.execSQL(submit_2);
        db.execSQL(submit_3);
        db.execSQL(submit_4);
        db.execSQL(submit_5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS submit_table1 " );
        db.execSQL("DROP TABLE IF EXISTS submit_table2 " );
        db.execSQL("DROP TABLE IF EXISTS submit_table3");
        db.execSQL("DROP TABLE IF EXISTS submit_table4");
        db.execSQL("DROP TABLE IF EXISTS submit_table5");
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
    public long insertsubmittable2(Integer attendence_id,Integer mq_id,Integer option_id,String m_answer){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("mq_id",mq_id);
        values.put("option_id",option_id);
        values.put("m_answer",m_answer);

        long id = db.insert(TABLE2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsubmittable3(Integer attendence_id,
                                   String block_name, String cluster_name,String school_name,
                                String remark,String datet,Integer flag){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("remark",remark);
        values.put("school_name",school_name );
        values.put("block_name",block_name);
        values.put("cluster_name",cluster_name);
        values.put("datet",datet);
        values.put("flag", flag);

        long id = db.insert(TABLE3, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsubmittable4(Integer attendence_id,String my_image,String img_remark){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("my_image",my_image);
        values.put("img_remark",img_remark);

        long id = db.insert(TABLE4, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsubmittable5(Integer attendence_id,Integer category_id,String my_image,String img_remark){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("category_id", category_id);
        values.put("my_image",my_image);
        values.put("img_remark",img_remark);

        long id = db.insert(TABLE5, null, values);

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
    public Cursor getAllData4() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE4,null);
        return res;
    }
    public Cursor getAllData5() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE5,null);
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
    public Cursor getAllData44(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM submit_table4 WHERE attendence_id = "+ id,null);
        return res;
    }
    public Cursor getAllData55(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM submit_table5 WHERE attendence_id = "+ id,null);
        return res;
    }

}
