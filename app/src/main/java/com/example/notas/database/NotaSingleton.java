package com.example.notas.database;

import android.annotation.*;
import android.content.*;
import androidx.room.*;

import com.example.notas.Nota;

import java.util.List;

public class NotaSingleton {

    @SuppressLint("StaticFieldLeak")
    private static NotaSingleton notaSingleton;

    private NotaDAO notaDAO;

    private NotaSingleton(Context context) {

        Context appContext = context.getApplicationContext();
        DataBase database = Room.databaseBuilder(appContext, DataBase.class, "nota")
                .allowMainThreadQueries().build();
        notaDAO = database.getNotaDao();
    }

    public static NotaSingleton getNotaSingleton(Context context) {
        if (notaSingleton == null) {
            notaSingleton = new NotaSingleton(context);
        }
        return notaSingleton;
    }

    public List<Nota> getAllNotes(){
        return notaDAO.getAll();
    }

    public List<Nota> getAllNotesById(int[] notaIds){
        return notaDAO.loadAllByIds(notaIds);
    }

    public List<Nota> getAllReminder(boolean reminder ){
        return notaDAO.loadAllReminder(reminder);
    }

    public List<Nota> getAllTypeOfNote(String typeOfNote ){
        return notaDAO.loadAllTypeOfNote(typeOfNote);
    }

    public List<Nota> getAllWithOutArchived(String typeOfNote ){
        return notaDAO.loadAllTypeWithOutArchived(typeOfNote);
    }

    public void setNote(Nota nota ){
        notaDAO.insert(nota);
    }

    public void deleteNote(Nota nota ){
        notaDAO.insert(nota);
    }

    public void updateNote(Nota nota ){
        notaDAO.update(nota);
    }
}
