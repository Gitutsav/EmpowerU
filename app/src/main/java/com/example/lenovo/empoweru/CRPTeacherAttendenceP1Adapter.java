package com.example.lenovo.empoweru;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class CRPTeacherAttendenceP1Adapter extends RecyclerView.Adapter<PruebaAdapter.PruebaViewHolder> {

    List<String> lista;
    List<String> no_of_students;
    String[] escrito;

    public CRPTeacherAttendenceP1Adapter(List<String> lista, List<String> no_of_students) {
        this.lista = lista;
        this.no_of_students=no_of_students;
        escrito = new String[lista.size()];
    }


    @Override
    public PruebaAdapter.PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PruebaAdapter.PruebaViewHolder holder, int position) {
        String producto = lista.get(position);
        String nos= no_of_students.get(position);
        holder.bindProducto(producto,nos);
    }



    @Override
    public int getItemCount() {
        return lista.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{

        EditText etCantidad;
        TextView tvProducto, nos;

        public PruebaViewHolder(View itemView) {
            super(itemView);
            etCantidad = (EditText) itemView.findViewById(R.id.etCantidad);
            tvProducto = (TextView) itemView.findViewById(R.id.tvProducto);
            nos=(TextView) itemView.findViewById(R.id.tvProduct);

        }

        public void bindProducto(String nombre, String nost){
            tvProducto.setText(nombre);
            nos.setText(nost);
        }

    }
}
