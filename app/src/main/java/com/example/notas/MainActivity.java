package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Nota> notas = new ArrayList<Nota>();
    Nota nota = new Nota("Nota 1", "descripcion 1");
    Nota nota2 = new Nota("Nota 2", "descripcion 2");
    Nota nota3 = new Nota("Nota 3", "descripcion 3");

    private void getContext() {
        if (getIntent().hasExtra("estado")) {
            String estado = getIntent().getStringExtra("estado");
            //realizar consulta a base de datos con el estado seleccionado
            notas.add(nota);
            notas.add(nota2);
        } else {
            notas.add(nota3);
            //retornar todas las notas menos las archivadas
        }
    }

    public void nuevaNota(View view){
        startActivity(new Intent(getApplicationContext(), CrearNota.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvNotes);
        NotesAdapter adapter = new NotesAdapter(notas);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
