package com.example.sqlitenoteapp.view;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitenoteapp.R;
import com.example.sqlitenoteapp.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private Context context;
    private List<Note> noteList;

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Note note=noteList.get(position);

        holder.noteTV.setText(note.getNote());

        // Displaying dot from HTML character code
        holder.dotTV.setText(Html.fromHtml("&#8226;"));

        //Formatting and displaying timestamp
        holder.timeStampTV.setText(formatDate(note.getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTV,dotTV,timeStampTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTV=itemView.findViewById(R.id.note);
            dotTV=itemView.findViewById(R.id.dot);
            timeStampTV =itemView.findViewById(R.id.timestamp);
        }
    }

    private String formatDate(String dateStr){
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=fmt.parse(dateStr);
            SimpleDateFormat fmtOut =new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        }catch (ParseException e){

        }
        return "";
    }
}
