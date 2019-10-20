package com.example.notas;

import androidx.annotation.*;
import androidx.room.*;

@Entity(tableName = "nota")
public class Nota {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "tipo_nota")
    private String tipo_nota;

    @ColumnInfo(name = "recordatorio")
    private boolean recordatorio;

    public Nota() {
        this.titulo = "";
        this.descripcion = "";
    }

    public Nota(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Nota(String titulo, String descripcion, boolean recordatorio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.recordatorio = recordatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean getRecordatorio() {
        return recordatorio;
    }

    public int getId() { return id; }

    public String getTipo_nota() { return tipo_nota; }


    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public void setRecordatorio(boolean recordatorio) { this.recordatorio = recordatorio; }

    public void setId(int id) { this.id = id; }

    public void setTipo_nota(String tipo_nota) { this.tipo_nota = tipo_nota; }

    public boolean isRecordatorio() { return recordatorio; }
}
