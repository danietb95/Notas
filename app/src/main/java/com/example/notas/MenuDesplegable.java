package com.example.notas;

import android.content.Intent;
import android.os.Bundle;

import com.example.notas.database.NotaSingleton;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuDesplegable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NotaSingleton notaSingleton;
    List<Nota> notas = new ArrayList<Nota>();
    String estado;
    boolean recordatorio;

    private void getContext() {
        if (getIntent().hasExtra("estado")) {
            estado = getIntent().getStringExtra("estado");

            switch (estado) {
                case "Archivadas":
                    notas = (ArrayList) notaSingleton.getAllTypeOfNote("Archivadas");
                    break;
                case "Favoritas":
                    notas = (ArrayList) notaSingleton.getAllTypeOfNote("Favoritas");
                    break;
                case "TodasSinArchivadas":
                    notas = (ArrayList) notaSingleton.getAllWithOutArchived("Archivadas");
                    break;
                default:
                    notas = (ArrayList) notaSingleton.getAllNotes();
            }
        } else
            notas = (ArrayList) notaSingleton.getAllNotes();
        if (getIntent().hasExtra("recordatorio")) {
            recordatorio = getIntent().getBooleanExtra("recordatorio", false);
            notas = (ArrayList) notaSingleton.getAllReminder(recordatorio);
        }
    }

    public void nuevaNota(View view) {
        startActivity(new Intent(getApplicationContext(), CrearNota.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        notaSingleton = NotaSingleton.getNotaSingleton(this);
        getContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_desplegable);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvNotes);
        NotesAdapter adapter = new NotesAdapter(notas);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.todas) {
            Intent intent = new Intent(this, MenuDesplegable.class);
            intent.putExtra("estado", "Todas");
            startActivity(intent);
        } else if (id == R.id.favoritas) {
            Intent intent = new Intent(this, MenuDesplegable.class);
            intent.putExtra("estado", "Favoritas");
            startActivity(intent);
        } else if (id == R.id.archivadas) {
            Intent intent = new Intent(this, MenuDesplegable.class);
            intent.putExtra("estado", "Archivadas");
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
