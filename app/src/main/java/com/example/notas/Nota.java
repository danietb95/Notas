package com.example.notas;

import androidx.annotation.*;
import androidx.room.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "nota")
public class Nota implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "tipo_nota")
    private String tipo_nota;

    @ColumnInfo(name = "recordatorio")
    private boolean recordatorio;

    @Ignore
    public Nota() {
        this.titulo = "";
        this.descripcion = "";
        this.recordatorio = false;
        this.id = UUID.randomUUID().toString();
        this.tipo_nota = "Normal";
    }

    public Nota(String titulo, String descripcion, boolean recordatorio, String tipo_nota) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.recordatorio = recordatorio;
        this.id = UUID.randomUUID().toString();
        this.tipo_nota = tipo_nota;
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

    public String getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRecordatorio(boolean recordatorio) {
        this.recordatorio = recordatorio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipoNota(String tipo_nota) {
        this.tipo_nota = tipo_nota;
    }

    public String getTipo_nota() {
        return tipo_nota;
    }
}
