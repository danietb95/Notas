package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.example.notas.database.NotaSingleton;

public class CrearNota extends AppCompatActivity {
    private String titulo;
    private String descripcion;
    private boolean recordatorio;
    private NotaSingleton notaSingleton;

    private void getContext() {
        if (getIntent().hasExtra("titulo")) {
            descripcion = getIntent().getStringExtra("descripcion");
        }
        if (getIntent().hasExtra("descripcion")) {
            descripcion = getIntent().getStringExtra("descripcion");
        }
        if (getIntent().hasExtra("recordatorio")) {
            recordatorio = getIntent().getBooleanExtra("descripcion", false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        getContext();
        TextView textViewTitle = (TextView) findViewById(R.id.titulo);
        TextView textViewDescription = (TextView) findViewById(R.id.descripcion);
        textViewTitle.setText(titulo);
        textViewDescription.setText(descripcion);
        notaSingleton = NotaSingleton.getNotaSingleton(this);
    }

    public void crearNota(){
        Nota nota = new Nota();
        EditText titulo = (EditText) findViewById(R.id.titulo);
        EditText descripcion = (EditText) findViewById(R.id.descripcion);
        Switch recordatorio = (Switch) findViewById(R.id.recordatorio);

        nota.setTitulo(titulo.getText().toString());
        nota.setDescripcion(descripcion.getText().toString());
        nota.setRecordatorio(recordatorio.isActivated());

        notaSingleton.setNote(nota);


        //startActivity(new Intent(getApplicationContext(), CrearNota.class));
    }
}
