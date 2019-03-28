package com.example.lenovo.empoweru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class Block_details3 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "block5.db";
    private static final String TABLE_NAME_hostel_monitot_list = "hostel_monitot_listl3";
    private static final String TABLE_NAME_block_details = "block_detailsl3";
    private static final String TABLE_NAME_hostel_option_list = "hostel_option_listl3";
    private static final String TABLE_NAME_user_details = "user_detailsl3";
    private static final String TABLE_NAME_monitor_list = "monitot_listl3";
    private static final String TABLE_NAME_slot_list = "slot_listl3";
    private static final String TABLE_NAME_phone = "phonel3";
    private static final String TABLE_NAME_data = "datal3";
    private static final String TABLE_NAME_teacher = "teacherl3";
    private static final String TABLE_NAME_option_list = "option_listl3";
    private static final String TABLE_NAME_cluster_details = "cluster_detailsl3";
    private static final String TABLE_NAME_helpline = "helplinel3";
    private static final String TABLE_NAME_class_list = "class_listl3";
    private static final String TABLE_NAME_school_details = "school_detaill3";
    private static final String TABLE_NAME_question_category = "question_categoryl3";


    private static  String user_details =
            "create table user_detailsl3 (name text, "+"designation text, "+"dob Context, "+"gender smallint, "
                    + "email text, "+"contact text,"+"role text,"+"adhaar text,"+"block text);";
    private static String monitot_list =
            "create table monitot_listl3 (mq_name text , "+"is_image integer,"+"input_type_id integer,"+"opg_id integer,"
                    + "mq_name_regional text ,"+"q_no integer,"+"question_type integer, "+"mq_id integer);";
    public static final String slot_list =
            "CREATE TABLE " + "slot_listl3 " + "("
                    + "to_time" + " TEXT,"
                    + "slot_id" + " INTEGER,"
                    + "from_time" + " TEXT"
                    + ")";
    public static final String phone =
            "CREATE TABLE " + "phonel3" + "("
                    + "number" + " TEXT"+ ")";
    public static final String data =
            "CREATE TABLE " + "datal3 " + "("
                    + "role" + " INTEGER,"
                    + "user_id" + " INTEGER,"
                    + "name" + " TEXT,"
                    + "perm" + " INTEGER,"
                    + "level" + " INTEGER"
                    + ")";
    public static final String teacher =
            "CREATE TABLE " + "teacherl3" + "("
                    + "teacher_id" + " INTEGER,"
                    + "teacher_name" + " TEXT,"
                    + "designation" + " TEXT,"
                    + "gender" + " SMALLINT,"
                    + "contact" + " TEXT,"
                    + "role" + " TEXT,"
                    + "school_id" + " TEXT"
                    + ")";
    public static final String option_list =
            "CREATE TABLE " + "option_listl3" + "("
                    + "opc_id" + " INTEGER,"
                    + "opc_name_regional" + " TEXT,"
                    + "input_type_id" + " integer,"
                    + "option_choices" + " text,"
                    + "opg_id" + " integer,"
                    + "mq_id" + " integer"
                    + ")";
    public static final String cluster_details =
            "CREATE TABLE " + "cluster_detailsl3 " + "("
                    + "cluster_name" + " TEXT,"
                    + "cluster_id" + " INTEGER,"
                    + "block_id" + " INTEGER"
                    + ")";
    public static final String helpline =
            "CREATE TABLE " + "helplinel3" + "("
                    + "helpline_no" + " TEXT,"
                    + "helpline_name" + " TEXT"
                    + ")";
    public static final String class_list =
            "CREATE TABLE " + "class_listl3" + "("
                    + "class_id" + " SMALLINT,"
                    + "class_name" + " TEXT,"
                    + "student_count" + " INTEGER,"
                    + "class_value" + " integer,"
                    + "school_id" + " integer"
                    + ")";
    public static final String school_detail =
            "CREATE TABLE " + "school_detaill3" + "("
                    + "is_coed" + " TEXT,"
                    + "school_category" + " TEXT,"
                    + "school_name" + " TEXT,"
                    + "cluster" + " TEXT,"
                    + "dise_code" + " TEXT,"
                    + "cluster_id" + " integer,"
                    + "control_department" + " text,"
                    + "school_id" + " integer,"
                    + "block" + " TEXT"
                    + ")";
    public static final String question_category =
            "CREATE TABLE " + " question_categoryl3" + "("
                    + "qc" + " TEXT,"
                    + "qc_id" + " INTEGER"
                    + ")";

    private static String hostel_monitot_list =
            "create table hostel_monitot_listl3 (hmq_id integer , "+"is_image integer,"+"input_type_id integer,"+"hq_no integer,"
                    + "hmq_name_regional text ,"+"opg_id integer,"+"hmq_name text);";
    public static final String hostel_option_list =
            "CREATE TABLE " + "hostel_option_listl3" + "("
                    + "hmq_id" + " integer,"
                    + "opc_id" + " INTEGER,"
                    + "opc_name_regional" + " TEXT,"
                    + "input_type_id" + " integer,"
                    + "option_choices" + " text,"
                    + "opg_id" + " integer"
                    + ")";
    public static final String block_details =
            "CREATE TABLE " + " block_detailsl3" + "("
                    + "block_name" + " TEXT,"
                    + "block_id" + " INTEGER"
                    + ")";
    public Block_details3(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user_details);
        db.execSQL(monitot_list);
        db.execSQL(slot_list);
        db.execSQL(phone);
        db.execSQL(data);
        db.execSQL(teacher);
        db.execSQL(option_list);
        db.execSQL(cluster_details);
        db.execSQL(helpline);
        db.execSQL(class_list);
        db.execSQL(school_detail);
        db.execSQL(question_category);
        db.execSQL(hostel_monitot_list);
        db.execSQL(hostel_option_list);
        db.execSQL(block_details);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user_detailsl3");
        db.execSQL("DROP TABLE IF EXISTS monitot_listl3");
        db.execSQL("DROP TABLE IF EXISTS slot_listl3");
        db.execSQL("DROP TABLE IF EXISTS phonel3");
        db.execSQL("DROP TABLE IF EXISTS datal3");
        db.execSQL("DROP TABLE IF EXISTS teacherl3");
        db.execSQL("DROP TABLE IF EXISTS option_listl3");
        db.execSQL("DROP TABLE IF EXISTS cluster_detailsl3");
        db.execSQL("DROP TABLE IF EXISTS helplinel3");
        db.execSQL("DROP TABLE IF EXISTS class_listl3");
        db.execSQL("DROP TABLE IF EXISTS school_detaill3");
        db.execSQL("DROP TABLE IF EXISTS question_categoryl3");
        db.execSQL("DROP TABLE IF EXISTS "+ "hostel_monitot_listl3");
        db.execSQL("DROP TABLE IF EXISTS "+ "hostel_option_listl3");
        db.execSQL("DROP TABLE IF EXISTS "+ "block_detailsl3");

        // Create tables again
        onCreate(db);
    }
    public long inseruserdetails3(String names,String designations,String dob, int genders,
                                  String emails, String contacts,
                                  String role, long adhaar, String block){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", names);
        values.put("designation", designations);
        values.put("dob", dob);
        values.put("gender", genders);
        values.put("email", emails);
        values.put("contact", contacts);
        values.put("role", role);
        values.put("adhaar", adhaar);
        values.put("block",block);


        // insert row
        long id = db.insert(TABLE_NAME_user_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertmonitotdetail3(String mq_name,Integer is_image,Integer input_type_id,Integer opg_id,
                                     String mq_name_regional,Integer q_no,Integer question_type,Integer mq_id){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mq_name", mq_name);
        values.put("is_image",is_image);
        values.put("input_type_id",input_type_id);
        values.put("opg_id",opg_id);
        values.put("mq_name_regional", mq_name_regional);
        values.put("q_no",q_no);
        values.put("question_type",question_type);
        values.put("mq_id",mq_id);

        // insert row
        long id = db.insert(TABLE_NAME_monitor_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteslotlist3(String to_time,int slot_id,String from_time) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("to_time",to_time);
        values.put("slot_id",slot_id);
        values.put("from_time",from_time);

        long id = db.insert(TABLE_NAME_slot_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertNotephone3(String number) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number",number);
        // insert row
        long id = db.insert(TABLE_NAME_phone, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNotedata3(int role,int user_id,String name,int perm,int level) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("role",role);
        values.put("user_id",user_id);
        values.put("name",name);
        values.put("perm", perm);
        values.put("level",level);


        // insert row
        long id = db.insert(TABLE_NAME_data, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteteacher3(int teacher_id,String teacher_name,String designation, int gender,String contact,String role,Integer school_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("teacher_id",teacher_id);
        values.put("teacher_name", teacher_name);
        values.put("designation", designation);
        values.put("gender", gender);
        values.put("contact", contact);
        values.put("role", role);
        values.put("school_id", school_id);
        long id = db.insert(TABLE_NAME_teacher, null, values);
        db.close();
        return id;
    }
    public long insertNoteoptionlist3(int opc_id,String opc_name_regional,Integer input_type_id,String option_choices,Integer opg_id,Integer mq_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("opc_id",opc_id);
        values.put("opc_name_regional", opc_name_regional);
        values.put("input_type_id", input_type_id);
        values.put("option_choices", option_choices);
        values.put("opg_id", opg_id);
        values.put("mq_id", mq_id);

        long id = db.insert(TABLE_NAME_option_list, null, values);
        db.close();
        return id;
    }
    public long insertNoteclusterdetails3(String cluster_name,int cluster_id,int block_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("cluster_name",cluster_name);
        values.put("cluster_id",cluster_id);
        values.put("block_id",block_id );


        long id = db.insert(TABLE_NAME_cluster_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertNotehelpline3(String helpline_no,String helpline_name) {
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
    public long insertNoteclasslist3(int class_id, String class_name, int student_count,int class_value,Integer school_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("class_id",class_id);
        values.put("class_name",class_name);
        values.put("student_count",student_count);
        values.put("class_value",class_value);
        values.put("school_id",school_id);


        // insert row
        long id = db.insert(TABLE_NAME_class_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNoteschooldetail3(String is_coed, String school_category,String school_name, String cluster,
                                        String dise_code, Integer cluster_id,String control_department, int school_id, String block) {
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
        values.put("cluster_id", cluster_id);
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
    public long insertNoteblockdetails3(String block_name,Integer block_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("block_name",block_name);
        values.put("block_id",block_id);


        // insert row
        long id = db.insert(TABLE_NAME_block_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertNotehosteloptionlist3(int hmq_id,int opc_id,String opc_name_regional,Integer input_type_id,String option_choices,Integer opg_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("hmq_id", hmq_id);
        values.put("opc_id",opc_id);
        values.put("opc_name_regional", opc_name_regional);
        values.put("input_type_id", input_type_id);
        values.put("option_choices", option_choices);
        values.put("opg_id", opg_id);


        long id = db.insert(TABLE_NAME_hostel_option_list, null, values);
        db.close();
        return id;
    }
    public long inserthostelmonitotlist3(int hmq_id,Integer is_image,Integer input_type_id,Integer hq_no,
                                     String hmq_name_regional,Integer opg_id,String hmq_name){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       values.put("hmq_id", hmq_id);
        values.put("is_image",is_image);
        values.put("input_type_id",input_type_id);
        values.put("hq_no",hq_no);
        values.put("hmq_name_regional", hmq_name_regional);
        values.put("opg_id",opg_id);
        values.put("hmq_name", hmq_name);

        // insert row
        long id = db.insert(TABLE_NAME_hostel_monitot_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public Cursor user_detail_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user_detailsl3",null);
        return res;
    }
    public Cursor monitot_list() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_monitor_list,null);
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
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_teacher,null);
        return res;
    }
    public Cursor option_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_option_list,null);
        return res;
    }
    public Cursor cluster_details_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_cluster_details,null);
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
    public Cursor question_category_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_question_category,null);
        return res;
    }
    public Cursor block_details_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_block_details,null);
        return res;
    }
    public Cursor hostel_option_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_hostel_option_list,null);
        return res;
    }
    public Cursor hostel_monitot_list_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_hostel_monitot_list,null);
        return res;
    }
    public Cursor block_cluster(int block_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from cluster_detailsl3 where block_id = "+String.valueOf(block_id),null);
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
    public int deleteData (String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
        //db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }
}
