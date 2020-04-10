package com.example.icebreaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WAdapter extends RecyclerView.Adapter<WAdapter.WViewHolder> {

    private ArrayList<WItem> WList;
    private OnItemClickListener Listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        Listener = listener;
    }

    public static class WViewHolder extends RecyclerView.ViewHolder{
        public CheckBox question;

        public WViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            question = itemView.findViewById(R.id.w_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public WAdapter(ArrayList<WItem> WList) {
        this.WList = WList;
    }

    @NonNull
    @Override
    public WViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.w_rows, parent, false);
        WViewHolder vh = new WViewHolder(v, Listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WViewHolder holder, int position) {
        WItem currentItem = WList.get(position);

        holder.question.setText(currentItem.getQuestion());
    }

    @Override
    public int getItemCount() {
        return WList.size();
    }
}