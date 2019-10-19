package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CrearNota extends AppCompatActivity {
    private String titulo = "";
    private String descripcion = "";

    private void getContext() {
        if (getIntent().hasExtra("titulo")) {
            titulo = getIntent().getStringExtra("titulo");
            descripcion = getIntent().getStringExtra("descripcion");
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

    }
}
