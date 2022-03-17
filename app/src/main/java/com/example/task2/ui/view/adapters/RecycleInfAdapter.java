package com.example.task2.ui.view.adapters;

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

import com.example.task2.ui.view.activities.DetailsActivity;
import com.example.task2.data.models.NewHeadLines;
import com.example.task2.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecycleInfAdapter extends RecyclerView.Adapter<RecycleInfAdapter.ViewHolder> {

    List<NewHeadLines> headLines = new ArrayList<>();
    Context ctx;

    public RecycleInfAdapter(Context ctx) {
        this.ctx = ctx;
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

        });
    }


    public void setList(List<NewHeadLines> newsModelsList) {
        this.headLines = newsModelsList;
        notifyDataSetChanged();
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
        }
    }
}
