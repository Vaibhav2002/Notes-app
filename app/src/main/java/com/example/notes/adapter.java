package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewHol> {
    Context context;
    ArrayList<not> ar;

    @NonNull
    @Override
    public viewHol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note, parent, false);
        return new viewHol(view);
    }

    public adapter(Context context, ArrayList<not> ar) {
        this.context = context;
        this.ar = ar;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHol holder, final int position) {
        holder.title.setText(ar.get(position).getTitle());
        holder.description.setText(ar.get(position).getDescr());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddNote.class);
                intent.putExtra("NO", position);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return ar.size();
    }


    static class viewHol extends RecyclerView.ViewHolder {
        TextView title, description;

        public viewHol(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.descr);
        }
    }
}
