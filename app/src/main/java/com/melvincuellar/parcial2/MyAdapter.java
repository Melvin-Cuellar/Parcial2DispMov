package com.melvincuellar.parcial2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Contactos> list;
    private Context context;

    public MyAdapter(Context context, List<Contactos> list) {
        this.context = context;
        this.list = list;
    }

    public  void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvNombre, tvTelefono, tvCorreoElectronico, tvDireccion, tvFecha;

        public  MyViewHolder(View v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvNombre);
            tvTelefono = v.findViewById(R.id.tvTelefono);
            tvCorreoElectronico = v.findViewById(R.id.tvCorreoElectronico);
            tvDireccion = v.findViewById(R.id.tvDireccion);
            tvFecha = v.findViewById(R.id.tvCumpleaños);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Contactos contacto = list.get(position);

            // Crear el diálogo para Modificar
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Modificar Contacto");
            builder.setMessage("¿Quieres modificar este contacto?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Enviar a AddEditContactActivity para modificar el contacto
                    Intent intent = new Intent(context, AddEditContactActivity.class);
                    intent.putExtra("position", position); // Enviar la posición del contacto
                    intent.putExtra("nombre", contacto.getNombre());
                    intent.putExtra("telefono", contacto.getTelefono());
                    intent.putExtra("correoelectronico", contacto.getCorreoElectronico());
                    intent.putExtra("direccion", contacto.getDireccion());
                    intent.putExtra("fecha", contacto.getFecha());
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            Contactos contacto = list.get(position);

            // Crear el diálogo para Eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Eliminar Contacto");
            builder.setMessage("¿Seguro que quieres eliminar este contacto?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    removeItem(position); // Eliminar la tarea
                    Toast.makeText(view.getContext(), "Contacto eliminado", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();

            return true;
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tvNombre.setText(list.get(position).getNombre().toString());
        holder.tvTelefono.setText(list.get(position).getTelefono().toString());
        holder.tvCorreoElectronico.setText(list.get(position).getCorreoElectronico().toString());
        holder.tvDireccion.setText(list.get(position).getDireccion().toString());
        holder.tvFecha.setText(list.get(position).getFecha().toString());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class Contactos {
        String nombre;
        String telefono;
        String correoelectronico;
        String direccion;
        String fecha;

        public Contactos() {
        }

        public Contactos(String nombre, String telefono, String correoelectronico, String direccion, String fecha) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.correoelectronico = correoelectronico;
            this.direccion = direccion;
            this.fecha = fecha;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreoElectronico() {
            return correoelectronico;
        }

        public void setCorreoElectronico(String correoelectronico) {
            this.correoelectronico = correoelectronico;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString();
        }
    }
}