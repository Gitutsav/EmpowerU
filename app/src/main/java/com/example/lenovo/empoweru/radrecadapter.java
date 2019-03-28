package com.example.lenovo.empoweru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class radrecadapter extends RecyclerView.Adapter<radrecadapter.ViewHolder> {
int count=0;
private List<PackageModel> packageList;
private Context context;
public String string ;
List<Integer> rg=new ArrayList<>();
List<Integer> present=new ArrayList<>();
String rb1,rb2,rb3,rb4;
public int[] arr; LinearLayout.LayoutParams layoutParams;
RadioGroup rgr;
String test="";SharedPreferences.Editor editor; SharedPreferences loginData;
    private RadioGroup lastCheckedRadioGroup = null;
    private RadioButton rba,rbb,rbc;
    LinearLayout ll;
    public radrecadapter(List<PackageModel> packageListIn
            , Context ctx) {
        packageList = packageListIn;
        context = ctx;
    }

    public radrecadapter(Context context) {
    }


    @Override
    public radrecadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.use_in_radrec, parent, false);
       loginData = context.getSharedPreferences("presenties", Context.MODE_PRIVATE);
        editor = loginData.edit();
        ll=(LinearLayout) view.findViewById(R.id.ll);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        radrecadapter.ViewHolder viewHolder =
                new radrecadapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        rgr=new RadioGroup(radrecadapter.this.context);
        PackageModel packageModel = packageList.get(position);
        holder.packageName.setText(packageModel.getPackageName());
        int idrb=(1)*100;
        holder.priceGroup.setId((position+1)*100);

        rg.clear();
        present.clear();
        arr=new int[packageList.size()];
      //  holder.priceGroup.setId();

        for(int i=0;i<packageList.size();i++){
            int idrg = (i+1)*100;
          //  present.add(1);
            arr[i]=2;
          //  holder.priceGroup.setId(idrg);
            rg.add(idrg);}
        count++;

        if(count==1){
            ll.removeAllViews();
            ll.setVisibility(View.GONE);
            ll.setVisibility(View.INVISIBLE);
            layoutParams.setMargins(0, 0, 0, 0);;
            //ll.setMargins(0,0,0,0);
        }

        holder.priceGroup.removeAllViews();
        for(String price : packageModel.getPriceList()){
            RadioButton rb = new RadioButton(radrecadapter.this.context);
            rba= new RadioButton(radrecadapter.this.context);
            rbb= new RadioButton(radrecadapter.this.context);
            rbc= new RadioButton(radrecadapter.this.context);
            rb.setId(idrb++);
            if(idrb==100){rb.setChecked(true);}
            if(idrb==100){rba=rb;}
            else if(idrb==101){rbb=rb;}
            else if(idrb==102){rbc=rb;}
            rb.setText(price);
            holder.priceGroup.addView(rb);
        }
        holder.priceGroup.check(101);
        packageModel.setPresenties(Arrays.toString(arr));
test=Arrays.toString(arr);
    }

    public void clearRadioChecked() {
        rba.setChecked(false);
        rbb.setChecked(false);
        rbc.setChecked(false);

    }
    @Override
    public int getItemCount() {
        return packageList.size();
    }
    public radrecadapter(radrec radrec) {
    }
    public String presenties() {
        string=Arrays.toString(arr);
        return string;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;private Button submit;
        public RadioGroup priceGroup;

        public ViewHolder(View view) {
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
                        count=1;


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
    arr[((radioGroup.getId()/100)-1)]=1;
rba.setChecked(true);
}
if(radioGroup.getCheckedRadioButtonId()==101){
    //present.set(((radioGroup.getId()/100)-1),2);
    arr[((radioGroup.getId()/100)-1)]=2;
    rbb.setChecked(true);
}
if(radioGroup.getCheckedRadioButtonId()==102){
   // present.set(((radioGroup.getId()/100)-1),3);
    arr[((radioGroup.getId()/100)-1)]=3;
    rbc.setChecked(true);
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
          arr[((radioGroup.getId()/100)-1)]=1;
          rba.setChecked(true);
      }
      if(radioGroup.getCheckedRadioButtonId()==101){
          //present.set(((radioGroup.getId()/100)-1),2);
          arr[((radioGroup.getId()/100)-1)]=2;
          rbb.setChecked(true);
      }
      if(radioGroup.getCheckedRadioButtonId()==102){
          // present.set(((radioGroup.getId()/100)-1),3);
          arr[((radioGroup.getId()/100)-1)]=3;
          rbc.setChecked(true);
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