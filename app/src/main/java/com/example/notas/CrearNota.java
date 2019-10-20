package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Switch;
import com.example.notas.database.NotaSingleton;

public class CrearNota extends AppCompatActivity {
    private String titulo;
    private String descripcion;
    private boolean recordatorio;
    private NotaSingleton notaSingleton;

    private void getContext() {
        if (getIntent().hasExtra("titulo")) {
            titulo = getIntent().getStringExtra("titulo");
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
        Button botonGuardar = (Button)findViewById(R.id.guardar);
        textViewTitle.setText(titulo);
        textViewDescription.setText(descripcion);
        notaSingleton = NotaSingleton.getNotaSingleton(this);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nota nota = new Nota("","",false);
                Switch testViewReminder = (Switch) findViewById(R.id.recordatorio);
                TextView textViewTitle = (TextView) findViewById(R.id.titulo);
                TextView textViewDescription = (TextView) findViewById(R.id.descripcion);

                nota.setTitulo(textViewTitle.getText().toString());
                nota.setDescripcion(textViewDescription.getText().toString());
                nota.setRecordatorio(testViewReminder.isActivated());
                notaSingleton.setNote(nota);

                Intent intent = new Intent(CrearNota.this, MainActivity.class);
                intent.putExtra("estado", "dd");
                startActivity(intent);
            }
        });

    }
}
