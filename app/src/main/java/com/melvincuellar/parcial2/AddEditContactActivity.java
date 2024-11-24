package com.melvincuellar.parcial2;

import static com.melvincuellar.parcial2.MainActivity.lstContactos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddEditContactActivity extends AppCompatActivity {
    Button btnFecha, btnGuardar;
    EditText edtNombre, edtTelefono, edtCorreoElectronico, edtDireccion, edtFecha;
    int position = -1; // Para verificar si es una tarea nueva o se quiere modificar una existente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        btnFecha = findViewById(R.id.btnFecha);
        btnGuardar = findViewById(R.id.btnGuardar);
        edtNombre = findViewById(R.id.edtNombre);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtCorreoElectronico = findViewById(R.id.edtCorreoElectronico);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtFecha = findViewById(R.id.edtFecha);

        // Verificar si es una tarea existente que se va a modificar
        if (getIntent().hasExtra("position")) {
            position = getIntent().getIntExtra("position", -1);
            edtNombre.setText(getIntent().getStringExtra("nombre"));
            edtTelefono.setText(getIntent().getStringExtra("telefono"));
            edtFecha.setText(getIntent().getStringExtra("fecha"));
            edtCorreoElectronico.setText(getIntent().getStringExtra("correoelectronico"));
            edtDireccion.setText(getIntent().getStringExtra("direccion"));
            edtFecha.setText(getIntent().getStringExtra("fecha"));
        }

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener la fecha actual
                final Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEditContactActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String FechaSelec = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        edtFecha.setText(FechaSelec);
                    }

                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validarCampos()){
                    MyAdapter.Contactos contacto = new MyAdapter.Contactos();
                    contacto.setNombre(edtNombre.getText().toString());
                    contacto.setTelefono(edtTelefono.getText().toString());
                    contacto.setCorreoElectronico(edtCorreoElectronico.getText().toString());
                    contacto.setDireccion(edtDireccion.getText().toString());
                    contacto.setFecha(edtFecha.getText().toString());

                    if (position == -1) {
                        // Es un nuevo contacto
                        lstContactos.add(contacto);
                    } else {
                        // Es un contacto existente que ha sido modificado
                        lstContactos.set(position, contacto);
                    }
                    finish();
                }
            }
        });
    }

    private boolean validarCampos() {
        String nombre = edtNombre.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String correoelectronico = edtCorreoElectronico.getText().toString().trim();
        String direccion = edtDireccion.getText().toString().trim();
        String fecha = edtFecha.getText().toString().trim();

        if (nombre.isEmpty()) {
            edtNombre.setError("Campo obligatorio");
            return false;
        }
        if (telefono.isEmpty()) {
            edtTelefono.setError("Campo obligatorio");
            return false;
        }
        if (correoelectronico.isEmpty()) {
            edtCorreoElectronico.setError("Campo obligatorio");
            return false;
        }
        if (direccion.isEmpty()) {
            edtDireccion.setError("Campo obligatorio");
            return false;
        }
        if (fecha.isEmpty()) {
            edtFecha.setError("Campo obligatorio");
            return false;
        }
        return true;
    }
}