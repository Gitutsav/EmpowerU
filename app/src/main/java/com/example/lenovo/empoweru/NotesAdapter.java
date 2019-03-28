package com.example.lenovo.empoweru;

/**
 * Created by ravi on 20/02/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Note> notesList;
    String a="aasf";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView attendence_id,dot,flag;
        public TextView present,absent,leave,remark,datet;
        public TextView age,username,password,syncbutton;


        public MyViewHolder(View view) {
            super(view);
            attendence_id = view.findViewById(R.id.attendence_id);
            present = view.findViewById(R.id.present);
            absent=view.findViewById(R.id.absent);
            dot = view.findViewById(R.id.dot);
            leave = view.findViewById(R.id.leave);
            datet = view.findViewById(R.id.datet);
            remark = view.findViewById(R.id.remark);
            flag = view.findViewById(R.id.flag);
            syncbutton=view.findViewById(R.id.syncbutton);

            syncbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              //   click();
                }


            });

}
    }
   /* public void click() {
        Toast.makeText(this,a,Toast.LENGTH_LONG).show();
        Intent intent=new Intent(NotesAdapter.this,SQLite.class);*/
    //}

    public NotesAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Note note = notesList.get(position);
      /*  names=note.getName1();
        ages=note.getAge1();
        surnames=note.getSurname1();
        usernames=note.getUsername1();
        passwords=note.getPassword1();*/
      holder.attendence_id.setText(String.valueOf(note.getAttendence_id()));
      holder.present.setText(String.valueOf(note.getPresent()));
      holder.absent.setText(String.valueOf(note.getAbsent()));
      holder.leave.setText(String.valueOf(note.getLeave()));
      holder.datet.setText(note.getDatet());
      holder.remark.setText(note.getRemark());
      holder.flag.setText(String.valueOf(note.getFlag()));


        // Displaying dot from HTML character code
       holder.dot.setText(Html.fromHtml("&#8256;"));

        // Formatting and displaying timestamp
       //// holder.timestamp.setText(formatDate(note.getTimestamp()));
        holder.syncbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                a=position;
                Note note1 = notesList.get(a);
                int attendence_id,present,absent,leave,flag;
               // String datet,remark;
                List<Integer> user_id=new ArrayList<>();
                List<Integer> attendancestatus=new ArrayList<>();
                Toast.makeText(context,String.valueOf(position)+"\n"+ note1.getAttendence_id()+"\n"+note1.getDatet(),Toast.LENGTH_LONG).show();
                int school_id=2;
                double lattitude=202.22;
                double longitude=5.252;
                int accuracy=23;
                String date="05-08-2018";
                int markedtype=1;
                int marked_by_id=255;
                String markedon="10:56";
                int slot_id=2;
                String remark="ufgeu";
                user_id.add(125);
                user_id.add(238);
                attendancestatus.add( 2);
                attendancestatus.add(1);

               /*attendence_id = note1.getAttendence_id();
               present=note1.getPresent();
               absent=note1.getAbsent();
               leave=note1.getLeave();
               datet=note1.getDatet();
               remark=note1.getRemark();
               flag=note1.getFlag();*/
                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                backgroundWorker.execute(type,String.valueOf(45),String.valueOf(78.25),String.valueOf(450.2),String.valueOf(78),"gffuf",String.valueOf(1)
                        ,String.valueOf(4528),"datet",String.valueOf(7963),"gfyf",String.valueOf(71236),String.valueOf(75));

                //Intent intent=new Intent(context,SQLite.class);
               // OnLogin(attendence_id,present,absent,leave,datet,remark,flag);
            }
        });


    }
   /* public void OnLogin(int attendence_id,int present, int absent, int leave, String datet, String remark, int flag) {
        //String username = UsernameEt.getText().toString();
        //String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(context);
        backgroundWorker.execute(type,String.valueOf(attendence_id),String.valueOf(present),String.valueOf(absent)
                ,String.valueOf(leave),datet,remark,String.valueOf(flag));
    }*/
    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
  /*  private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }*/


}
