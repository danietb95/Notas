package com.example.notas.database;

import androidx.room.*;

@Database(entities = {Nota_DB.class}, version = 1)
public abstract class DataBase extends RoomDatabase{

    public abstract NotaDAO getNotaDao();

    //DataBase db = Room.databaseBuilder(getApplicationContext(),
    //        DataBase.class, "Notas_DataBase").build();
}
