package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notas.database.NotaSingleton;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    NotaSingleton notaSingleton;
    List<Nota> notas = new ArrayList<Nota>();
    String estado;
    boolean recordatorio;

    private void getContext() {
        if (getIntent().hasExtra("estado")) {
            estado = getIntent().getStringExtra("estado");

            switch (estado) {
                case "Archivada":
                    notas = (ArrayList) notaSingleton.getAllTypeOfNote("Archivadas");
                    break;
                case "Favoritas":
                    notas = (ArrayList) notaSingleton.getAllTypeOfNote("Favoritas");
                    break;
                case "TodasSinArchivadas":
                    notas = (ArrayList) notaSingleton.getAllWithOutArchived("Favoritas");
                    break;
                default:
                    notas = (ArrayList) notaSingleton.getAllNotes();
            }
        }else
            notas = (ArrayList) notaSingleton.getAllNotes();

        if (getIntent().hasExtra("recordatorio")) {
            recordatorio = getIntent().getBooleanExtra("recordatorio", false);
                notas = (ArrayList) notaSingleton.getAllReminder(recordatorio);
            }
    }

    public void nuevaNota(View view){
            startActivity(new Intent(getApplicationContext(), CrearNota.class));
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        notaSingleton = NotaSingleton.getNotaSingleton(this);
        getContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvNotes);
        NotesAdapter adapter = new NotesAdapter(notas);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
}
