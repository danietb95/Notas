package com.example.notas.database;

import androidx.room.*;

@Entity
public class Nota {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "tipo_nota")
    public String tipo_nota;

    @ColumnInfo(name = "recordatorio")
    public boolean recordatorio;
}
