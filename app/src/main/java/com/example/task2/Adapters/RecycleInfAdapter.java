package com.example.task2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.DetailsActivity;
import com.example.task2.Models.NewHeadLines;
import com.example.task2.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecycleInfAdapter extends RecyclerView.Adapter<RecycleInfAdapter.ViewHolder> {

    List<NewHeadLines> headLines;

    private static ListItemClickListener mOnClickListener;
    Context ctx;


//    public interface ListItemClickListener{
//        void OnListItemClickListener(int clickItemIndex);
//    }

    public interface ListItemClickListener{
        void OnListItemClickListener(NewHeadLines headLines);
    }

    public RecycleInfAdapter(Context context, ListItemClickListener listener ,List<NewHeadLines> headLines ) {
        ctx = context;
        mOnClickListener = listener;
        this.headLines = headLines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_card_item ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardImage.setImageResource(R.drawable.ic_alert);
        holder.firstCardText.setText(headLines.get(position).getSource().getName());
        holder.secondCardText.setText(headLines.get(position).getAuthor());
        if (headLines.get(position).getUrlToImage() != null){
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.cardImage);
        }
        holder.cardImage.setOnClickListener(view -> {
            Intent intent = new Intent(ctx , DetailsActivity.class);

            intent.putParcelableArrayListExtra("news" , (ArrayList<? extends Parcelable>) headLines);
            intent.putExtra("position",position);
            ctx.startActivity(intent);

            mOnClickListener.OnListItemClickListener(headLines.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return headLines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView firstCardText , secondCardText;
        ImageView cardImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCardText = itemView.findViewById(R.id.first_card_text);
            secondCardText = itemView.findViewById(R.id.second_card_text);
            cardImage = itemView.findViewById(R.id.card_image);
//            itemView.setOnClickListener(this);
        }
//
//        @Override
//        public void onClick(View view) {
//            int clickedPosition = getBindingAdapterPosition();
//            mOnClickListener.OnListItemClickListener(clickedPosition);
//        }
    }
}
