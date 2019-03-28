package com.example.lenovo.empoweru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HM_details5 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hm15.db";
    private static final String TABLE_NAME_user_details = "user_details";
    private static final String TABLE_NAME_menu_list = "menu_list";
    private static final String TABLE_NAME_slot_list = "slot_list";
    private static final String TABLE_NAME_phone = "phone";
    private static final String TABLE_NAME_data = "data";
    private static final String TABLE_NAME_teacher = "teacher";
    private static final String TABLE_NAME_rate_list = "rate_list";
    private static final String TABLE_NAME_helpline = "helpline";
    private static final String TABLE_NAME_class_list = "class_list";
    private static final String TABLE_NAME_school_details = "school_detail";
    private static final String TABLE_NAME_question_category = "question_category";
    private static final String TABLE_NAME_leave = "leave";
    SQLiteDatabase dba;
    public static final String class_list =
            "CREATE TABLE " + "class_list" + "("
                    + "class_id" + " SMALLINT,"
                    + "class_name" + " TEXT,"
                    + "student_count" + " INTEGER,"
                    + "class_value" + " TEXT"
                    + ")";


    public static final String data =
            "CREATE TABLE " + "data" + "("
                    + "role" + " INTEGER,"
                    + "user_id" + " INTEGER,"
                    + "name" + " TEXT,"
                    + "level" + " INTEGER"
                    + ")";
    public static final String helpline =
            "CREATE TABLE " + "helpline" + "("
                    + "helpline_no" + " TEXT,"
                    + "helpline_name" + " TEXT"
                    + ")";

    // Create table SQL query
    public static final String menu_list =
            "CREATE TABLE " + "menu_list" + "("
                    + "menu_id" + " SMALLINT,"
                    + "menu_name" + " TEXT,"
                    + "menu_name_regional" + " TEXT"
                    + ")";
    public static final String phone =
            "CREATE TABLE " + "phone" + "("
                    + "number" + " TEXT"
                    + ")";
    public static final String rate_list =
            "CREATE TABLE " + "rate_list" + "("
                    + "e_fund_deduction" + " Text,"
                    + "fund_deduction" + " SMALLINT,"
                    + "mdm_food_rate_id" + " TEXT,"
                    + "class_type" + " SMALLINT,"
                    + "rice_deduction" + " TEXT"
                    //      + COL_6 + " TEXT"
                    + ")";
    public static final String school_detail =
            "CREATE TABLE " + "school_detail" + "("
                    + "is_coed" + " Text,"
                    + "school_category" + " TEXT,"
                    + "school_name" + " TEXT,"
                    + "cluster" + " TEXT,"
                    + "dise_code" + " TEXT,"
                    + "control_department" + " TEXT,"
                    + "school_id" + " INTEGER,"
                    + "block" + " TEXT"
                    + ")";
    public static final String slot_list =
            "CREATE TABLE " + "slot_list" + "("
                    + "to_time" + " TIME,"
                    + "slot_id" + " INTEGER,"
                    + "from_time" + " TIME"
                    + ")";
    public static final String teacher =
            "CREATE TABLE " + "teacher" + "("
                    + "teacher_id" + " INTEGER,"
                    + "teacher_name" + " TEXT,"
                    + "designation" + " TEXT,"
                    + "gender" + " SMALLINT,"
                    + "contact" + " TEXT,"
                    + "role" + " TEXT"
                    + ")";
    public static final String user_details =
            "CREATE TABLE " + "user_details" + "("
                    + "doj" + " TEXT,"
                    + "school" + " TEXT,"
                    + "name" + " TEXT,"
                    + "degree" + " TEXT,"
                    + "dob" + " TEXT,"
                    + "gender" + " INTEGER,"
                    + "pro_quali" + " TEXT,"
                    + "noa" + " TEXT,"
                    + "email" + " TEXT,"
                    + "cluster" + " TEXT,"
                    + "designation" + " TEXT,"
                    + "contact"+ " TEXT,"
                    + "working_status" + " TEXT,"
                    + "village" + " TEXT,"
                    + "appointed_for" + " TEXT,"
                    + "panchayat"+ " TEXT,"
                    + "adhaar"+ " TEXT,"
                    + "block"+ " TEXT"
                    + ")";
    public static final String question_category =
            "CREATE TABLE " + " question_category" + "("
                    + "qc" + " TEXT,"
                    + "qc_id" + " INTEGER"
                    + ")";
    public static final String leave =
            "CREATE TABLE " + "leave" + "("
                    + "leave_type" + " TEXT,"
                    + "leave_type_id" + " INTEGER"
                    + ")";
    public HM_details5(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //onClear(db);
       // dba=db;
   //   db.delete(teacher,null ,null );
        db.execSQL(user_details);
        db.execSQL(menu_list);
        db.execSQL(slot_list);
        db.execSQL(phone);
        db.execSQL(data);
        db.execSQL(teacher);
        db.execSQL(rate_list);
        db.execSQL(helpline);
        db.execSQL(class_list);
        db.execSQL(school_detail);
        db.execSQL(question_category);
        db.execSQL(leave);


    }

/*    public void onClear() {
      //  SQLiteDatabase db = this.dba;
      //  this.onClear(db);
    }
*/
  /*  public void onClear(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS user_details");
        db.execSQL("DROP TABLE IF EXISTS menu_list");
        db.execSQL("DROP TABLE IF EXISTS slot_list");
        db.execSQL("DROP TABLE IF EXISTS phone");
        db.execSQL("DROP TABLE IF EXISTS data");
        db.execSQL("DROP TABLE IF EXISTS teacher");
        db.execSQL("DROP TABLE IF EXISTS helpline");
        db.execSQL("DROP TABLE IF EXISTS class_list");
        db.execSQL("DROP TABLE IF EXISTS school_detail");
        db.execSQL("DROP TABLE IF EXISTS rate_list");
        onCreate(db);
    }

*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

      //  onClear(db);
        db.execSQL("DROP TABLE IF EXISTS user_details");
        db.execSQL("DROP TABLE IF EXISTS menu_list");
        db.execSQL("DROP TABLE IF EXISTS slot_list");
        db.execSQL("DROP TABLE IF EXISTS phone");
        db.execSQL("DROP TABLE IF EXISTS data");
        db.execSQL("DROP TABLE IF EXISTS teacher");
        db.execSQL("DROP TABLE IF EXISTS helpline");
        db.execSQL("DROP TABLE IF EXISTS class_list");
        db.execSQL("DROP TABLE IF EXISTS school_detail");
        db.execSQL("DROP TABLE IF EXISTS rate_list");
        db.execSQL("DROP TABLE IF EXISTS leave");
        db.execSQL("DROP TABLE IF EXISTS question_category");
        // Create tables again
        onCreate(db);
    }
    public long insertNoteclasslist(int class_id, String class_name, int student_count,int class_value) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("class_id",class_id);
        values.put("class_name",class_name);
        values.put("student_count",student_count);
        values.put("class_value",class_value);


        // insert row
        long id = db.insert(TABLE_NAME_class_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNotedata(int role,int user_id,String name,int level) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("role",role);
        values.put("user_id",user_id);
        values.put("name",name);
        values.put("level",level);


        // insert row
        long id = db.insert(TABLE_NAME_data, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertNotehelpline(String helpline_no,String helpline_name) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("helpline_no",helpline_no);
        values.put("helpline_name", helpline_name);


        // insert row
        long id = db.insert(TABLE_NAME_helpline, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertNotemenulist(int menu_idp,String menu_namep,String menu_name_regionalp) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("menu_id",menu_idp);
        values.put("menu_name", menu_namep);
        values.put("menu_name_regional", menu_name_regionalp);
        //  values.put(Notemenulist.COL_4, age);
        // values.put(Notemenulist.COL_5, username);
        // values.put(Notemenulist.COL_6, password);


        // insert row
        long id = db.insert(TABLE_NAME_menu_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNotephone(String number) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("number",number);


        // insert row
        long id = db.insert(TABLE_NAME_phone, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteratelist(String e_fund_deduction,String fund_deduction,int mdm_food_rate_id,int class_type,String rice_deduction) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("e_fund_deduction",e_fund_deduction);
        values.put("fund_deduction", fund_deduction);
        values.put("mdm_food_rate_id", mdm_food_rate_id);
        values.put("class_type", class_type);
        values.put("rice_deduction", rice_deduction);
        // values.put(Notemenulist.COL_6, password);


        // insert row
        long id = db.insert(TABLE_NAME_rate_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteschooldetail(String is_coed, String school_category,String school_name, String cluster,
                                       String dise_code, String control_department, int school_id, String block) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("is_coed",is_coed);
        values.put("school_category", school_category);
        values.put("school_name", school_name);
        values.put("cluster", cluster);
        values.put("dise_code", dise_code);
        values.put("control_department", control_department);
        values.put("school_id", school_id);
        values.put("block", block);


        // insert row
        long id = db.insert(TABLE_NAME_school_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteslotlist(String to_time,int slot_id,String from_time) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("to_time",to_time);
        values.put("slot_id",slot_id);
        values.put("from_time",from_time);


        // insert row


        long id = db.insert(TABLE_NAME_slot_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteteacher(int teacher_id,String teacher_name,String designation, int gender,String contact,String role) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("teacher_id",teacher_id);
        values.put("teacher_name", teacher_name);
        values.put("designation", designation);
        values.put("gender", gender);
        values.put("contact", contact);
        values.put("role", role);


        // insert row
        long id = db.insert(TABLE_NAME_teacher, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteuserdetails(String dojs, String schools,String names, String degrees,
                                      String dobs, int genders,String pro_qualis, String noas,
                                      String emails, String clusters,String designations, String contacts,
                                      String working_statuss, String villages,String appointed_fors, String panchayats,
                                      long adhaars, String blocks) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("doj",dojs);
        values.put("school", schools);
        values.put("name", names);
        values.put("degree", degrees);
        values.put("dob", dobs);
        values.put("gender", genders);
        values.put("pro_quali",pro_qualis);
        values.put("noa", noas);
        values.put("email", emails);
        values.put("cluster", clusters);
        values.put("designation", designations);
        values.put("contact", contacts);
        values.put("working_status", working_statuss);
        values.put("village", villages);
        values.put("appointed_for", appointed_fors);
        values.put("panchayat",panchayats);
        values.put("adhaar", String.valueOf(adhaars));
        values.put("block",blocks);


        // insert row
        long id = db.insert(TABLE_NAME_user_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNotequestioncategory3(String qc,Integer qc_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("qc",qc);
        values.put("qc_id", qc_id);


        // insert row
        long id = db.insert(TABLE_NAME_question_category, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteleave(String leave_type,Integer leave_type_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("leave_type",leave_type);
        values.put("leave_type_id", leave_type_id);


        // insert row
        long id = db.insert(TABLE_NAME_leave, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public Cursor question_category_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_question_category,null);
        return res;
    }
    public Cursor leave_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_leave,null);
        return res;
    }
    public Cursor user_detail_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user_details",null);
        return res;
    }
    public Cursor menu_list() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_menu_list,null);
        return res;
    }
    public Cursor slot_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_slot_list,null);
        return res;
    }
    public Cursor phone_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_phone,null);
        return res;
    }
    public Cursor data_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_data,null);
        return res;
    }
    public Cursor teacher_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from teacher",null);
        return res;
    }
    public Cursor rate_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_rate_list,null);
        return res;
    }

    public Cursor helpline_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_helpline,null);
        return res;
    }
    public Cursor class_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_class_list,null);
        return res;
    }
    public Cursor school_detail_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_school_details,null);
        return res;
    }


    public Cursor block_cluster_school(int cluster_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from school_detaill3 where cluster_id = "+String.valueOf(cluster_id),null);
        return res;
    }
    public Cursor block_cluster_school_teacher(int school_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from teacherl3 where school_id = "+String.valueOf(school_id),null);
        return res;
    }
    public Cursor block_cluster_school_classes(int school_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from class_listl3 where school_id = "+String.valueOf(school_id),null);
        return res;
    }
    public Cursor getSlot_id(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_slot_list+" WHERE to_time>='"+time+"' AND "+"from_time<='"+time+"'",null);
        return res;
    }
 /*   public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }*/
 public Integer deleteData (String TABLE_NAME) {
     SQLiteDatabase db = this.getWritableDatabase();
     return db.delete(TABLE_NAME, null, null);
 }
}
