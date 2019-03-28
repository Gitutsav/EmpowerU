package com.example.lenovo.empoweru;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class studenceattendencehm extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "studentattendencep.db";
    private static final String TABLE1="studentattendm";
    private static final String TABLE2="teacherattendancem";
    private static final String TABLE4="showstatush";
    private static  String DATABASE_CREATE1 =
            "create table studentattendm (attendence_id integer primary key autoincrement, "
                    + "lat double ,"+"longi double ,"+"accuracy integer,"+"slot_id smallint,"+"taken_by_id integer,"+"taken_on_time text,"
                    +"datet text,"+"school_id integer,"+"remark text,"+"flag integer);";
    /*private static  String DATABASE_CREATE2 =
            "create table teacherattendancem (attendence_id integer , "
                    + "user_id integer ,"+"present_status integer,"+"flag integer);";
    private static  String DATABASE_CREATE3 =
            "create table showstatush (attendence_id integer,"+"present integer, "
                    + "absent integer ,"+"leave integer,"+"datet text,"+"remark text,"+"flag integer);";
*/
    public studenceattendencehm(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DATABASE_CREATE1);
       // db.execSQL(DATABASE_CREATE2);
      //  db.execSQL(DATABASE_CREATE3);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS teacherattend " );
     //   db.execSQL("DROP TABLE IF EXISTS teacherattendance " );
     //   db.execSQL("DROP TABLE IF EXISTS showstatus");

        // Create tables again
        onCreate(db);
    }

    public long insertable1(Double lat,Double longi,int accuracy,Integer slot_id,Integer taken_by,
                            String taken_on,String datet,Integer school_id, String remark, Integer flag){
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
        values.put("remark",remark);
        values.put("flag",flag);
/*
        values.put("lat", 0.1);
        values.put("longi",0.2);
        values.put("accuracy",3);
        values.put("slot_id",12);
        values.put("taken_by",45);
        values.put("taken_on","taken_on");
        values.put("datet","0datet");
        values.put("school_id",5695);
        values.put("remark","remark");
        values.put("flag",0);
*/


        // insert row
        long id = db.insert(TABLE1, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
  /*  public long inserttable2(Integer attendence_id,Integer user_id,Integer present_status,Integer flag){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("user_id",user_id);
        values.put("present_status",present_status);
        values.put("flag",flag);


        // insert row
        long id = db.insert(TABLE2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    */
  /*  public long insertshowstatus(Integer attendence_id,Integer present,Integer absent,Integer leave,Integer flag,String remark,String datet){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("attendence_id", attendence_id);
        values.put("present", present);
        values.put("absent",absent);
        values.put("leave",leave);
        values.put("flag",flag);
        values.put("remark",remark);
        values.put("datet",datet);

        // insert row
        long id = db.insert(TABLE4, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    */
    public Cursor getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        return res;
    }
 /*   public Cursor getAllData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE2,null);
        return res;
    }
    public Cursor getAllData3() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE4,null);
        return res;
    }
*/
  /*  public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE4,
                new String[]{Note.COL_1, Note.COL_2, Note.COL_3,Note.COL_4,Note.COL_5,Note.COL_6,Note.COL_7},
                Note.COL_1 + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COL_1)),
                cursor.getInt(cursor.getColumnIndex(Note.COL_2)),
                cursor.getInt(cursor.getColumnIndex(Note.COL_3)),
                cursor.getInt(cursor.getColumnIndex(Note.COL_4)),
                cursor.getString(cursor.getColumnIndex(Note.COL_5)),
                cursor.getString(cursor.getColumnIndex(Note.COL_6)),
                cursor.getInt(cursor.getColumnIndex(Note.COL_7)));

        // close the db connection
        cursor.close();

        return note;
    }
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        // String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
        //        Note.COLUMN_TIMESTAMP + " DESC";
        String selectQuery = "SELECT  * FROM " + TABLE4 ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setAttendence_id(cursor.getInt(cursor.getColumnIndex("attendence_id")));
                note.setPresent(cursor.getInt(cursor.getColumnIndex("present")));
                note.setAbsent(cursor.getInt(cursor.getColumnIndex("absent")));
                note.setLeave(cursor.getInt(cursor.getColumnIndex("leave")));
                note.setDatet(cursor.getString(cursor.getColumnIndex("datet")));
                note.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                note.setFlag(cursor.getInt(cursor.getColumnIndex("flag")));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COL_1, note.getAttendence_id());
        values.put(Note.COL_2, note.getPresent());
        values.put(Note.COL_3, note.getAbsent());
        values.put(Note.COL_4, note.getLeave());
        values.put(Note.COL_5, note.getDatet());
        values.put(Note.COL_6, note.getRemark());
        values.put(Note.COL_7, note.getFlag());
        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COL_1 + " = ?",
                new String[]{String.valueOf(note.getAttendence_id())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COL_1 + " = ?",
                new String[]{String.valueOf(note.getAttendence_id())});
        db.close();
    }
    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
    */
}

