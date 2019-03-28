package com.example.lenovo.empoweru;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import  android.widget.Toast;
import java.util.List;


public class BlockPruebaAdapterStudent extends RecyclerView.Adapter<BlockPruebaAdapterStudent.PruebaViewHolder> {

    List<String> class_number;
    List<String> no_of_students;
    String[] presenties_list;

    public BlockPruebaAdapterStudent(List<String> class_number, List<String> no_of_students) {
        this.class_number = class_number;
        this.no_of_students=no_of_students;
        presenties_list = new String[class_number.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_student_item_row,parent,false);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
        String class_nos = class_number.get(position);
        String total_st= no_of_students.get(position);
        holder.bindProducto(class_nos,total_st);
    }

    @Override
    public int getItemCount() {
        return class_number.size();
    }

    public String[] getPresenties_list() {
        return presenties_list;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{

        EditText presenties;
        TextView class_nums, total_st;

        public PruebaViewHolder(View itemView) {
            super(itemView);
            presenties = (EditText) itemView.findViewById(R.id.present_st);
            class_nums = (TextView) itemView.findViewById(R.id.class_no);
            total_st=(TextView) itemView.findViewById(R.id.tot_st);
            presenties.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    presenties_list[getAdapterPosition()] = s.toString();

                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });
        }

        public void bindProducto(String class_num, String total_stt){
            class_nums.setText(class_num);
            total_st.setText("/ "+total_stt);
        }

    }
}
