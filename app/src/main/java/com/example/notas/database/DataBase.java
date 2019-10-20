package com.example.notas.database;

import androidx.room.*;

import com.example.notas.Nota;

@Database(entities = {Nota.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase{

    public abstract NotaDAO getNotaDao();

    //DataBase db = Room.databaseBuilder(getApplicationContext(),
    //        DataBase.class, "Notas_DataBase").build();
}
