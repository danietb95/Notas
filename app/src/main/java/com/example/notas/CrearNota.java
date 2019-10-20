package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        Button botonGuardar = (Button)findViewById(R.id.guardar);
        textViewTitle.setText(titulo);
        textViewDescription.setText(descripcion);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewTitle = (TextView) findViewById(R.id.titulo);
                TextView textViewDescription = (TextView) findViewById(R.id.descripcion);
                Intent intent = new Intent(CrearNota.this, MainActivity.class);
                intent.putExtra("estado", "dd");
                startActivity(intent);
            }
        });

    }
}
