package com.example.lenovo.empoweru;

/**
 * Created by ravi on 20/02/18.
 */

public class Note {
    public static final String DATABASE_NAME = "teacherattendencep.db";
    public static final String TABLE_NAME = "showstatush";
    public static final String COL_1 = "attendence_id";
    public static final String COL_2 = "present";
    public static final String COL_3 = "absent";
    public static final String COL_4 = "leave";
    public static final String COL_5 = "datet";
    public static final String COL_6 = "remark";
    public static final String COL_7 = "flag";



    private int attendence_id ,present,absent,leave,flag;
    private String remark,datet;
    private String timestamp;



    // Create table SQL query
    private static  String DATABASE_CREATE3 =
            "create table showstatush (attendence_id integer,"+"present integer, "
                    + "absent integer ,"+"leave integer,"+"datet text,"+"remark text,"+"flag integer);";
    public Note() {
    }

    public Note(int attendence_id, int present, int absent, int leave,String datet, String remark, int flag) {
this.absent=absent;
this.attendence_id=attendence_id;
this.datet=datet;
this.flag=flag;
this.leave=leave;this.present=present;
this.remark=remark;
          }
    public int getAttendence_id() {
        return attendence_id;
    }

    public void setAttendence_id(int attendence_id) {
        this.attendence_id = attendence_id;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDatet() {
        return datet;
    }

    public void setDatet(String datet) {
        this.datet = datet;
    }


}
