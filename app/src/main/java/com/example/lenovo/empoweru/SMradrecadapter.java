package com.example.lenovo.empoweru;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SMradrecadapter extends RecyclerView.Adapter<SMradrecadapter.ViewHolder> {
int count=0;
private List<PackageModel> packageList;
private Context context;AlertDialog.Builder builder1;
public String string ;EditText remark;    ImageButton imgbtn;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView; String myBase64Image="imbase64";
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;
    private SMBlockSchoolMonitor sbsm;
List<Integer> rg=new ArrayList<>();Context cont;
List<Integer> present=new ArrayList<>();
String rb1,rb2,rb3,rb4;
LinearLayout flcross;
public int[] arr;
public String[] m_answer;String datesmc;
    LinearLayout.LayoutParams layoutParams;
RadioGroup rgr;
static String test="";SharedPreferences.Editor editor; SharedPreferences loginData;
    private RadioGroup lastCheckedRadioGroup = null;
    private Block_details3 bd;
    private RadioButton rba,rbb,rbc;
ImageButton img;
    LinearLayout ll;int mYear,mMonth,mDay;
    private RadioButton rbd;
    String datet;FrameLayout fl;
    TextView dateqq,datew;
    private String stringm; SharedPreferences loginDatat;
    SharedPreferences.Editor editorsmcdate;
   SMradrecadapter(List<PackageModel> packageListIn
            , Context ctx) {
        packageList = packageListIn;
        context = ctx;
    }

    public SMradrecadapter(Context context) {
    }



    @Override
    public SMradrecadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.smuse_in_radrec, parent, false);
       loginData = context.getSharedPreferences("presenties", Context.MODE_PRIVATE);
       loginDatat=context.getSharedPreferences("SMCdate",Context.MODE_PRIVATE );
       editorsmcdate = loginDatat.edit();
        editor = loginData.edit();
        cont=view.getContext();
        dateqq= view.findViewById(R.id.date);
      //  datew= view.findViewById(R.id.datew);
        remark=view.findViewById(R.id.remark);
        remark.setVisibility(View.GONE);
        fl=view.findViewById(R.id.framelayoutx);
        fl.setVisibility(View.GONE);
        flcross=view.findViewById(R.id.framelayoutcrossx);
        flcross.setVisibility(View.GONE);
        img=view.findViewById(R.id.imagebuttonx);
        sbsm=new SMBlockSchoolMonitor();
        bd=new Block_details3(view.getContext());
        ll=(LinearLayout) view.findViewById(R.id.ll);

        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SMradrecadapter.ViewHolder viewHolder =new SMradrecadapter.ViewHolder(view);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

      //  date.setText(mDay+"/"+mMonth+"/"+mYear);

      img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(SMradrecadapter.this.context," datet",Toast.LENGTH_SHORT ).show();
               // sbsm.action();
                builder1 = new AlertDialog.Builder(view.getContext());
                builder1.setTitle("Can't submit!!");
                builder1.setMessage("Enter all fields");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                    // cont.startActivity(new Intent(cont,Block_profile.class));
                                dialog.cancel();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (cont.checkSelfPermission(Manifest.permission.CAMERA)
                                            != PackageManager.PERMISSION_GRANTED) {
                                        /*requestPermissions(new String[]{Manifest.permission.CAMERA},
                                                MY_CAMERA_PERMISSION_CODE);*/
                                        Toast.makeText(SMradrecadapter.this.context," denied",Toast.LENGTH_SHORT ).show();
                                    } else {
                                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        Toast.makeText(SMradrecadapter.this.context," Gramnted",Toast.LENGTH_SHORT ).show();
                                        if (context instanceof Activity) {
                                            ((Activity) context).startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                            ((Activity) context).setResult(Activity.RESULT_OK, cameraIntent);
                                        } else {
                                            Toast.makeText(SMradrecadapter.this.context," denied",Toast.LENGTH_SHORT ).show();

                                        }
                                       // startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                    }
                                }
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        /*date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
                datet=(mDay+"/"+mMonth+"/"+mYear);
                date.setText(datet);
                DatePickerDialog mDatePicker=new DatePickerDialog(SMradrecadapter.this.context,

                        new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                       // date.setText(selectedday+"/"+(selectedmonth+1)+"/"+selectedyear);
                     /*   mDay=selectedday;
                        mMonth=selectedmonth;
                        mYear=selectedyear;
                        datet=(selectedday+"/"+(selectedmonth+1)+"/"+selectedyear);

                        Toast.makeText(SMradrecadapter.this.context, datet,Toast.LENGTH_SHORT ).show();
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }


        });*/
        dateqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                 datesmc =year+"-"+month+"-"+day;
              //  Toast.makeText(view.getContext(), datesmc, Toast.LENGTH_SHORT).show();
               // editorsmcdate.putString("smcdate", date);
                dateqq.setText(datesmc);
                editorsmcdate.putString("smcdate", datesmc);
                editorsmcdate.apply();

            }
        };
        return viewHolder;
    }
  // @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CAMERA_REQUEST:
                if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                    Toast.makeText(SMradrecadapter.this.context, "Got it",Toast.LENGTH_SHORT ).show();
                   /* Bitmap photo = (Bitmap) data.getExtras().get("data");
                    //   imageView.setImageBitmap(photo);

                    Bitmap converetdImage = getResizedBitmap(photo, 500);
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
                    imageView.setImageBitmap(photo);
                    fl.setVisibility(View.GONE);
                    flcross.setVisibility(View.VISIBLE);
                    // imageView.setImageBitmap(bitmap);
                    myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 100);*/
                }
                break;
        }
    }


   /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode == 0){
           Toast.makeText(SMradrecadapter.this.context, "Got it",Toast.LENGTH_SHORT ).show();
       }
       //do here anything
   }
*/
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 final int position) {
        rgr=new RadioGroup(SMradrecadapter.this.context);
        PackageModel PackageModel = packageList.get(position);
        holder.packageName.setText(PackageModel.getPackageName());
        int idrb=(1)*100;
        holder.priceGroup.setId((position+1)*100);

        rg.clear();
        present.clear();
        arr=new int[packageList.size()];
        m_answer=new String[packageList.size()];
      //  holder.priceGroup.setId();

        for(int i=0;i<packageList.size();i++){
            int idrg = (i+1)*100;
          //  present.add(1);
            arr[i]=(i*10)+1;
            m_answer[i]="k";
          //  holder.priceGroup.setId(idrg);
            rg.add(idrg);

        }


        if((count==0)){
            ll.removeAllViews();
            ll.setVisibility(View.GONE);
            ll.setVisibility(View.INVISIBLE);
            layoutParams.setMargins(0, 0, 0, 0);;
            //ll.setMargins(0,0,0,0);
        }
        if((count==packageList.size()-1))
        {
            ll.removeAllViews();
            ll.setVisibility(View.GONE);
            ll.setVisibility(View.INVISIBLE);
            layoutParams.setMargins(0, 0, 0, 0);;
        }
        count++;
        holder.priceGroup.removeAllViews();
        for(String price : PackageModel.getPriceList()){
            RadioButton rb = new RadioButton(SMradrecadapter.this.context);
            rba= new RadioButton(SMradrecadapter.this.context);
            rbb= new RadioButton(SMradrecadapter.this.context);
            rbc= new RadioButton(SMradrecadapter.this.context);
            rbd=new RadioButton(SMradrecadapter.this.context);
            rb.setId(idrb++);
            if(idrb==100){rb.setChecked(true);}
            if(idrb==100){rba=rb;}
            else if(idrb==101){rbb=rb;}
            else if(idrb==102){rbc=rb;}
            else if(idrb==103){rbd=rb;}
            rb.setText(price.substring(0,(price.length() - 1)));
            if(!((price.substring(0,(price.length() - 1))).equals("Date"))){

           if(((price.substring(price.length() - 1)).equals("0"))){
               rb.setText(price.substring(0,(price.length() - 1)));
                holder.priceGroup.addView(rb);
               fl.setVisibility(View.VISIBLE);
            }
            else{
               holder.priceGroup.addView(rb);
           }
            }

            if(((price.substring(0,(price.length() - 1))).equals("Date"))){
                holder.priceGroup.addView(dateqq);

                }
        }
        holder.priceGroup.check(100);
        PackageModel.setPresenties(Arrays.toString(arr));
        test=Arrays.toString(arr);
    }

    public void clearRadioChecked() {
        rba.setChecked(false);
        rbb.setChecked(false);
        rbc.setChecked(false);
        rbd.setChecked(false);

    }
    @Override
    public int getItemCount() {
        return packageList.size();
    }

    public String presenties() {
        string=Arrays.toString(arr);
        return string;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;private Button submit;
        public RadioGroup priceGroup;

        public ViewHolder(final View view) {
            super(view);
            packageName = (TextView) view.findViewById(R.id.package_name);
            priceGroup = (RadioGroup) view.findViewById(R.id.price_grp);
            submit=view.findViewById(R.id.submit);
            priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //since only one package is allowed to be selected
                    //this logic clears previous selection
                    //it checks state of last radiogroup and
                    // clears it if it meets conditions
                    if (lastCheckedRadioGroup != null){
                            //) {
                      //  lastCheckedRadioGroup.clearCheck();
                     //   count=1;


  String a=String.valueOf(priceGroup.getCheckedRadioButtonId());
 // RadioGroup rg=
  /*  int l=priceGroup.getId() ;

    if(l==100){ rb1= String.valueOf(radioGroup.getCheckedRadioButtonId());
    }
    else if(l==200){rb2= String.valueOf(radioGroup.getCheckedRadioButtonId());
    }
    else if(l==300){rb3= String.valueOf(radioGroup.getCheckedRadioButtonId());
    }
    else if(l==400){rb4= String.valueOf(radioGroup.getCheckedRadioButtonId());
    }
*/
/*  if(lastCheckedRadioGroup.getId()==100){
    //  clearRadioChecked();
      radioGroup.clearCheck();
      if(radioGroup.getCheckedRadioButtonId()==100){
          rba.setChecked(true);
      }
      else  if(radioGroup.getCheckedRadioButtonId()==100){
          rbb.setChecked(true);
      }
      else if(radioGroup.getCheckedRadioButtonId()==100){
          rbc.setChecked(true);
      }
  }*/

  if(lastCheckedRadioGroup.getCheckedRadioButtonId()
          == radioGroup.getCheckedRadioButtonId()){
     // radioGroup.clearCheck();
clearRadioChecked();
if(radioGroup.getCheckedRadioButtonId()==100){
   // present.set(((radioGroup.getId()/100)-1),1);
    int selectedId = radioGroup.getCheckedRadioButtonId();

    // find the radiobutton by returned id
  RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

   
    arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+1;
rba.setChecked(true);
}
if(radioGroup.getCheckedRadioButtonId()==101){
    //present.set(((radioGroup.getId()/100)-1),2);
    int selectedId = radioGroup.getCheckedRadioButtonId();

    // find the radiobutton by returned id
    RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

   
    arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+2;
    rbb.setChecked(true);
}
if(radioGroup.getCheckedRadioButtonId()==102){
   // present.set(((radioGroup.getId()/100)-1),3);
    int selectedId = radioGroup.getCheckedRadioButtonId();

    // find the radiobutton by returned id
    RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

   
    arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+3;
    rbc.setChecked(true);
}
      if(radioGroup.getCheckedRadioButtonId()==103){
          // present.set(((radioGroup.getId()/100)-1),3);
          int selectedId = radioGroup.getCheckedRadioButtonId();

          // find the radiobutton by returned id
          RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

         
          arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+4;
          rbd.setChecked(true);
      }
      editor.putString("presenttt", presenties());

      editor.apply();

  /*    Toast.makeText(radrecadapter.this.context,
               rg.toString()+radioGroup.getId()+
              ""+ radioGroup.getCheckedRadioButtonId()+"//"+Arrays.toString(arr),
              //+l,
              Toast.LENGTH_SHORT).show();*/
  }
          else if(lastCheckedRadioGroup.getCheckedRadioButtonId()
          != radioGroup.getCheckedRadioButtonId()){
      clearRadioChecked();
      if(radioGroup.getCheckedRadioButtonId()==100){
          // present.set(((radioGroup.getId()/100)-1),1);
          int selectedId = radioGroup.getCheckedRadioButtonId();

          // find the radiobutton by returned id
          RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

         

          arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+1;
          rba.setChecked(true);
      }
      if(radioGroup.getCheckedRadioButtonId()==101){
          //present.set(((radioGroup.getId()/100)-1),2);
          int selectedId = radioGroup.getCheckedRadioButtonId();

          // find the radiobutton by returned id
          RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

         

          arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+2;
          rbb.setChecked(true);
      }
      if(radioGroup.getCheckedRadioButtonId()==102){
          // present.set(((radioGroup.getId()/100)-1),3);
          int selectedId = radioGroup.getCheckedRadioButtonId();

          // find the radiobutton by returned id
          RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

         

          arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+3;
          rbc.setChecked(true);
      }
      if(radioGroup.getCheckedRadioButtonId()==103){
          // present.set(((radioGroup.getId()/100)-1),3);
          int selectedId = radioGroup.getCheckedRadioButtonId();

          // find the radiobutton by returned id
          RadioButton  radioSexButton = (RadioButton) view.findViewById(selectedId);

         

          arr[((radioGroup.getId()/100)-1)]=(((radioGroup.getId()/100)-1)*10)+4;
          rbd.setChecked(true);
      }

      editor.putString("presenttt", presenties());
      editor.apply();
   /*   Toast.makeText(radrecadapter.this.context,
               rg.toString()+radioGroup.getId()+
              ""+ radioGroup.getCheckedRadioButtonId()+"//"+ Arrays.toString(arr),
              //+l,
              Toast.LENGTH_SHORT).show();*/
  }


                    }
                    lastCheckedRadioGroup = radioGroup;

                }
            });
        }
    }



}