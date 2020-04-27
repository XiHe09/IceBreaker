package com.example.icebreaker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    public class OrViewHolder extends RecyclerView.ViewHolder{
        public Button left;
        public Button right;
        public Button middle;
        public OrViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.left = itemView.findViewById(R.id.or_row_left);
            this.right = itemView.findViewById(R.id.or_row_right);
            this.middle = itemView.findViewById(R.id.or_row_middle);

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
        return new OrViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.or_row1,
                        parent, false), Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WItem currentItem = OrList.get(position);
        holder.getAdapterPosition();
        OrViewHolder orViewHolder = (OrViewHolder) holder;
        orViewHolder.left.setText(currentItem.getQuestion().split("; ")[0]);
        orViewHolder.right.setText(currentItem.getQuestion().split("; ")[1]);
        currentItem.setDone(true);
        System.out.println(holder.getItemViewType());
        switch (holder.getItemViewType()) {
            case 0:
                orViewHolder.left.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                orViewHolder.left.setTextColor(Color.parseColor("#FFD185"));
                orViewHolder.right.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                orViewHolder.right.setTextColor(Color.parseColor("#FFD185"));
                orViewHolder.middle.setBackgroundColor(Color.parseColor("#B8ADD4D2"));
                break;
            case 1:
                orViewHolder.left.setBackgroundColor(Color.parseColor("#B8ADD4D2"));
                orViewHolder.left.setTextColor(Color.parseColor("#FF9052"));
                orViewHolder.right.setBackgroundColor(Color.parseColor("#B8ADD4D2"));
                orViewHolder.right.setTextColor(Color.parseColor("#FF9052"));
                orViewHolder.middle.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return OrList.size();
    }
}
