package com.example.notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;

import com.example.notas.database.NotaSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CrearNota extends AppCompatActivity {
    private Nota nota = new Nota();
    private NotaSingleton notaSingleton;
    private boolean esNueva = true;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private Button botonGuardar;
    BottomNavigationItemView menuItemFavoritos;
    BottomNavigationItemView menuItemArchivados;


    @SuppressLint("RestrictedApi")
    private void getContext() {
        if (getIntent().hasExtra("Nota")) {
            nota = (Nota) getIntent().getSerializableExtra("Nota");
            esNueva = false;
            menuItemFavoritos = (BottomNavigationItemView) findViewById(R.id.item_favoritos);
            menuItemArchivados = (BottomNavigationItemView) findViewById(R.id.item_archivar);

            switch(nota.getTipo_nota()){
                case "Archivada":
                    menuItemArchivados.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_folder_special_black_24dp));
                    menuItemArchivados.setFocusable(true);
                    break;
                case "Favoritas":
                    menuItemFavoritos.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_star_black_24dp));
                    menuItemFavoritos.setFocusable(true);
                    break;
            }
        }
    }

    public void eliminarNota(MenuItem item) {
        notaSingleton.deleteNote(nota);
        Intent intent = new Intent(CrearNota.this, MainActivity.class);
        intent.putExtra("estado", "Favoritas");
        startActivity(intent);
    }

    @SuppressLint("RestrictedApi")
    public void agregarAFavoritos() {
        menuItemFavoritos = (BottomNavigationItemView) findViewById(R.id.item_favoritos);
        menuItemArchivados = (BottomNavigationItemView) findViewById(R.id.item_archivar);

        if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Archivada")){
            nota.setTipoNota("Favoritas");
            menuItemFavoritos.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_star_black_24dp));
            menuItemArchivados.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_folder_black_24dp));
        }else if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Favoritas")){
            nota.setTipoNota("Normal");
            menuItemFavoritos.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_star_border_black_24dp));
        }else{
            nota.setTipoNota("Favoritas");
            menuItemFavoritos.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_star_black_24dp));
        }
    }

    @SuppressLint("RestrictedApi")
    public void agregarAArchivados() {
        menuItemFavoritos = (BottomNavigationItemView) findViewById(R.id.item_favoritos);
        menuItemArchivados = (BottomNavigationItemView) findViewById(R.id.item_archivar);

        if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Archivada")){
            nota.setTipoNota("Normal");
            menuItemArchivados.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_folder_black_24dp));
        }else if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Favoritas")){
            nota.setTipoNota("Archivada");
            menuItemFavoritos.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_star_border_black_24dp));
            menuItemArchivados.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_folder_special_black_24dp));
        }else{
            nota.setTipoNota("Archivada");
            menuItemArchivados.setIcon(ContextCompat.getDrawable(CrearNota.this, R.drawable.ic_folder_special_black_24dp));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        getContext();
        textViewTitle = (TextView) findViewById(R.id.titulo);
        textViewDescription = (TextView) findViewById(R.id.descripcion);
        botonGuardar = (Button) findViewById(R.id.guardar);
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
                    notaSingleton.setNote(nota);
                } else {
                    notaSingleton.updateNote(nota);
                }
                Intent intent = new Intent(CrearNota.this, MainActivity.class);
                intent.putExtra("estado", "Favoritas");
                startActivity(intent);
            }
        });

        BottomNavigationView barraNavegacion = (BottomNavigationView) findViewById(R.id.bottom_nav);
        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_archivar:
                        agregarAArchivados();
                        break;
                    case R.id.item_favoritos:
                        agregarAFavoritos();
                        break;
                }
                return true;
            }
        });







    }
}
