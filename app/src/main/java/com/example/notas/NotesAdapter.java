package com.example.notas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Nota> notas;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleNote;
        public TextView descriptionNote;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleNote = (TextView) itemView.findViewById(R.id.titleNote);
            descriptionNote = (TextView) itemView.findViewById(R.id.descriptionNote);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recicle_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Nota nota = notas.get(position);
        TextView titleNote = holder.titleNote;
        titleNote.setText(nota.getTitulo());
        TextView descriptionNOte = holder.descriptionNote;
        titleNote.setText(nota.getTitulo());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public NotesAdapter(List<Nota> notas) {
        this.notas = notas;
    }
}
