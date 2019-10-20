package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    private TextView textViewTitle;
    private TextView textViewDescription;
    private Button botonGuardar;

    private void getContext() {
        if (getIntent().hasExtra("Nota")) {
            nota = (Nota) getIntent().getSerializableExtra("Nota");
            esNueva = false;
        }
    }

    public void eliminarNota(MenuItem item) {
        notaSingleton.deleteNote(nota);
        Intent intent = new Intent(CrearNota.this, MainActivity.class);
        intent.putExtra("estado", "Favoritas");
        startActivity(intent);
    }

    public void agregarAFavoritos(MenuItem item) {
        MenuItem menuItemFavoritos = (MenuItem) findViewById(R.id.item_favoritos);
        MenuItem menuItemArchivados = (MenuItem) findViewById(R.id.item_archivar);

        if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Archivada")){
            nota.setTipoNota("Favoritas");
            menuItemFavoritos.setIcon(Drawable.createFromPath("@android:drawable/ic_star_black_24dp"));
            menuItemArchivados.setIcon(Drawable.createFromPath("@android:drawable/ic_folder_open_black_24dp"));


        }else if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Favoritas")){
            nota.setTipoNota("Normal");
            menuItemFavoritos.setIcon(Drawable.createFromPath("@android:drawable/ic_star_border_black_24dp"));
        }else{
            nota.setTipoNota("Favoritas");
            menuItemFavoritos.setIcon(Drawable.createFromPath("@android:drawable/ic_star_black_24dp"));
        }
    }

    public void agregarAArchivados(MenuItem item) {
        MenuItem menuItemFavoritos = (MenuItem) findViewById(R.id.item_favoritos);
        MenuItem menuItemArchivados = (MenuItem) findViewById(R.id.item_archivar);

        if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Archivada")){
            nota.setTipoNota("Normal");
            menuItemArchivados.setIcon(Drawable.createFromPath("@android:drawable/ic_folder_open_black_24dp"));
        }else if (!textViewTitle.getText().equals("") && nota.getTipo_nota().equalsIgnoreCase("Favoritas")){
            nota.setTipoNota("Archivada");
            menuItemFavoritos.setIcon(Drawable.createFromPath("@android:drawable/ic_star_border_black_24dp"));
            menuItemArchivados.setIcon(Drawable.createFromPath("@android:drawable/ic_folder_special_black_24dp"));
        }else{
            nota.setTipoNota("Archivada");
            menuItemFavoritos.setIcon(Drawable.createFromPath("@android:drawable/ic_star_black_24dp"));
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
                intent.putExtra("estado", "TodasSinArchivadas");
                startActivity(intent);
            }
        });

    }
}
