package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;

import com.example.notas.database.NotaSingleton;

public class CrearNota extends AppCompatActivity {
    private Nota nota = new Nota();
    private NotaSingleton notaSingleton;
    private boolean esNueva = true;

    private void getContext() {
        if (getIntent().hasExtra("Nota")) {
            nota = (Nota) getIntent().getSerializableExtra("Nota");
            esNueva = false;
        }
    }

    public void eliminarNota(MenuItem item) {
        notaSingleton.deleteNote(nota);
        Intent intent = new Intent(CrearNota.this, MainActivity.class);
        intent.putExtra("estado", "TodasSinArchivadas");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        getContext();
        TextView textViewTitle = (TextView) findViewById(R.id.titulo);
        TextView textViewDescription = (TextView) findViewById(R.id.descripcion);
        Button botonGuardar = (Button) findViewById(R.id.guardar);
        textViewTitle.setText(nota.getTitulo());
        textViewDescription.setText(nota.getDescripcion());
        notaSingleton = NotaSingleton.getNotaSingleton(this);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch testViewReminder = (Switch) findViewById(R.id.recordatorio);
                TextView textViewTitle = (TextView) findViewById(R.id.titulo);
                TextView textViewDescription = (TextView) findViewById(R.id.descripcion);
                nota.setTitulo(textViewTitle.getText().toString());
                nota.setDescripcion(textViewDescription.getText().toString());
                nota.setRecordatorio(testViewReminder.isActivated());
                if (esNueva) {
                    nota.setTipo_nota("normal");
                    notaSingleton.setNote(nota);
                } else {
                    notaSingleton.updateNote(nota);
                }
                Intent intent = new Intent(CrearNota.this, MainActivity.class);
                intent.putExtra("estado", "TodasSinArchivadas");
                startActivity(intent);
            }
        });

    }
}
