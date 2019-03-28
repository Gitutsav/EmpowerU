package com.example.lenovo.empoweru.notelevel4;


public class Noteuserdetails4 {
    public static final String DATABASE_NAME = "crp.db";
    public static final String TABLE_NAME_user_details = "user_details";
    public static final String TABLE_NAME_monitor_list = "monitor_list";
    public static final String TABLE_NAME_slot_list = "slot_list";
    public static final String TABLE_NAME_phone = "phone";
    public static final String TABLE_NAME_data = "data";
    public static final String TABLE_NAME_teacher = "teacher";
    public static final String TABLE_NAME_option_list = "option_list";
    public static final String TABLE_NAME_cluster_details = "cluster_details";
    public static final String TABLE_NAME_helpline = "helpline";
    public static final String TABLE_NAME_class_list = "class_list";
    public static final String TABLE_NAME_school_details = "school_details";
    public static final String TABLE_NAME_question_category = "question_category";

    public static final String COL_1 = "doj";
    public static final String COL_2 = "school";
    public static final String COL_3 = "name";
    public static final String COL_4 = "degree";
    public static final String COL_5 = "dob";
    public static final String COL_6 = "gender";
    public static final String COL_7 = "pro_quali";
    public static final String COL_8 = "noa";
    public static final String COL_9 = "email";
    public static final String COL_10 = "cluster";
    public static final String COL_11 = "designation";
    public static final String COL_12 = "contact";
    public static final String COL_13 = "working_status";
    public static final String COL_14 = "village";
    public static final String COL_15 = "appointed_for";
    public static final String COL_16 = "panchayat";
    public static final String COL_17 = "adhaar";
    public static final String COL_18= "block";


    //  private int id1;
    //  private String name1,surname1,age1,username1,password1;
//    private String timestamp;
    private int genderi,adhaari;
    private String namei,degreei,dobi,pro_qualii,noai,emaili,clusteri,designationi,contacti,schooli,doji,working_statusi;
    private  String villagei,appointed_fori,panchayati,blocki;


    // Create table SQL query
    public static final String DELETE_TABLE = "DROP TABLE userdetails;";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME_user_details + "("
                    + COL_1 + " TEXT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT,"
                    + COL_4 + " TEXT,"
                    + COL_5 + " TEXT,"
                    + COL_6 + " INTEGER,"
                    + COL_7 + " TEXT,"
                    + COL_8 + " TEXT,"
                    + COL_9 + " TEXT,"
                    + COL_10 + " TEXT,"
                    + COL_11 + " TEXT,"
                    + COL_12+ " TEXT,"
                    + COL_13 + " TEXT,"
                    + COL_14 + " TEXT,"
                    + COL_15 + " TEXT,"
                    + COL_16+ " TEXT,"
                    + COL_17+ " TEXT,"
                    + COL_18+ " TEXT"
                    + ")";


    public Noteuserdetails4(String dojs, String schools, String names, String degrees,
                            String dobs, int genders, String pro_qualis, String noas,
                            String emails, String clusters, String designations, String contacts,
                            String working_statuss, String villages, String appointed_fors, String panchayats,
                            int adhaars, String blocks) {
        this.doji=dojs;
        this.schooli=schools;
        this.namei=names;
        this.degreei=degrees;
        this.dobi=dobs;
        this.genderi=genders;
        this.pro_qualii=pro_qualis;
        this.noai=noas;
        this.emaili=emails;
        this.clusteri=clusters;
        this.designationi=designations;
        this.contacti=contacts;
        this.working_statusi=working_statuss;
        this.villagei=villages;
        this.appointed_fori=appointed_fors;
        this.panchayati=panchayats;
        this.adhaari=adhaars;
        this.blocki=blocks;


    }

    public void setGenderi(int genderi) {
        this.genderi = genderi;
    }

    public void setAdhaari(int adhaari) {
        this.adhaari = adhaari;
    }

    public void setNamei(String namei) {
        this.namei = namei;
    }

    public void setDegreei(String degreei) {
        this.degreei = degreei;
    }

    public void setDobi(String dobi) {
        this.dobi = dobi;
    }

    public void setPro_qualii(String pro_qualii) {
        this.pro_qualii = pro_qualii;
    }

    public void setNoai(String noai) {
        this.noai = noai;
    }

    public void setEmaili(String emaili) {
        this.emaili = emaili;
    }

    public void setClusteri(String clusteri) {
        this.clusteri = clusteri;
    }

    public void setDesignationi(String designationi) {
        this.designationi = designationi;
    }

    public void setContacti(String contacti) {
        this.contacti = contacti;
    }

    public void setSchooli(String schooli) {
        this.schooli = schooli;
    }

    public void setDoji(String doji) {
        this.doji = doji;
    }

    public void setWorking_statusi(String working_statusi) {
        this.working_statusi = working_statusi;
    }

    public void setVillagei(String villagei) {
        this.villagei = villagei;
    }

    public void setAppointed_fori(String appointed_fori) {
        this.appointed_fori = appointed_fori;
    }

    public void setPanchayati(String panchayati) {
        this.panchayati = panchayati;
    }

    public void setBlocki(String blocki) {
        this.blocki = blocki;
    }

    public static String getCol1() {
        return COL_1;
    }

    public static String getCol2() {
        return COL_2;
    }

    public static String getCol3() {
        return COL_3;
    }

    public static String getCol4() {
        return COL_4;
    }

    public static String getCol5() {
        return COL_5;
    }

    public static String getCol6() {
        return COL_6;
    }

    public static String getCol7() {
        return COL_7;
    }

    public static String getCol8() {
        return COL_8;
    }

    public static String getCol9() {
        return COL_9;
    }

    public static String getCol10() {
        return COL_10;
    }

    public static String getCol11() {
        return COL_11;
    }

    public static String getCol12() {
        return COL_12;
    }

    public static String getCol13() {
        return COL_13;
    }

    public static String getCol14() {
        return COL_14;
    }

    public static String getCol15() {
        return COL_15;
    }

    public static String getCol16() {
        return COL_16;
    }

    public static String getCol17() {
        return COL_17;
    }

    public static String getCol18() {
        return COL_18;
    }
}

