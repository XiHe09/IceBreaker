package com.example.icebreaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<WItem> OrList;
    private OnItemClickListener Listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        Listener = listener;
    }
    public class OrViewHolder1 extends RecyclerView.ViewHolder{
        Button left;
        Button right;
        //Button middle = itemView.findViewById(R.id.Or_row1_middle);
        public OrViewHolder1(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.left = itemView.findViewById(R.id.Or_row1_left);
            this.right = itemView.findViewById(R.id.Or_row1_right);

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

    public static class OrViewHolder2 extends RecyclerView.ViewHolder {
        Button left;
        Button right;
        //Button middle = itemView.findViewById(R.id.Or_row2_middle);
        public OrViewHolder2(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            left = itemView.findViewById(R.id.Or_row2_left);
            right = itemView.findViewById(R.id.Or_row2_right);
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

    public OrAdapter(ArrayList<WItem> OrList) {
        this.OrList = OrList;
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 1 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if the position is even, return the first layout type
        //if the position is odd, return the second layout type
        switch (viewType) {
            case 0:
                return new OrViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.or_row1,
                        parent, false), Listener);

        }
        return new OrViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.or_row2,
                parent, false), Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WItem currentItem = OrList.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                OrViewHolder1 viewHolder1 = (OrViewHolder1) holder;
                viewHolder1.left.setText(currentItem.getQuestion().split("; ")[0]);
                viewHolder1.right.setText(currentItem.getQuestion().split("; ")[1]);
                currentItem.setDone(true);
                break;
            case 1:

                OrViewHolder2 viewHolder2 = (OrViewHolder2) holder;
                viewHolder2.left.setText(currentItem.getQuestion().split("; ")[0]);
                viewHolder2.right.setText(currentItem.getQuestion().split("; ")[1]);
                currentItem.setDone(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return OrList.size();
    }
}
