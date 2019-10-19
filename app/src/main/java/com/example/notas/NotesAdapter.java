package com.example.notas;

import android.content.Context;
import android.content.Intent;
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
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleNote;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            titleNote = (TextView) itemView.findViewById(R.id.titleNote);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recicle_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        final Nota nota = notas.get(position);
        TextView titleNote = holder.titleNote;
        titleNote.setText(nota.getTitulo());
        Button button = holder.messageButton;
        button.setText("Ingresar");
        button.setEnabled(true);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CrearNota.class);
                        intent.putExtra("titulo", nota.getTitulo());
                        intent.putExtra("descripcion", nota.getDescripcion());
                        context.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public NotesAdapter(List<Nota> notas) {
        this.notas = notas;
    }
}
