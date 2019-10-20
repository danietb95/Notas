package com.example.notas.database;

import androidx.room.*;

import com.example.notas.Nota;

import java.util.List;

public interface NotaDAO {
    @Query("SELECT * FROM Nota")
    List<Nota> getAll();

    @Query("SELECT * FROM Nota WHERE id IN (:notaIds)")
    List<Nota> loadAllByIds(int[] notaIds);

    @Query("SELECT * FROM Nota WHERE tipo_nota = :typeOfNote")
    List<Nota> loadAllTypeOfNote(String typeOfNote);

    @Query("SELECT * FROM Nota WHERE tipo_nota <> :typeOfNote")
    List<Nota> loadAllTypeWithOutArchived(String typeOfNote);

    @Query("SELECT * FROM Nota WHERE recordatorio = :reminder")
    List<Nota> loadAllReminder (boolean reminder);

    @Query("SELECT * FROM Nota WHERE titulo LIKE :first AND " +
            "tipo_nota LIKE :last LIMIT 1")
    Nota findByName(String first, String last);

    @Insert
    void insert(com.example.notas.Nota nota);

    @Delete
    void delete(Nota nota);

    @Update
    void update(Nota nota);
}
