package com.example.task2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;

public class TopViewPagerAdapter extends RecyclerView.Adapter<TopViewPagerAdapter.ViewHolder> {

    private final Context ctx;
    private final int[] imagesList = {R.drawable.image_one, R.drawable.image_two, R.drawable.image_three};



    public TopViewPagerAdapter(Context context) {
        ctx = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.top_image_holder , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.images.setImageResource(imagesList[position]);
    }

    @Override
    public int getItemCount() {
        return imagesList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.top_image_pager);
            images.setOnClickListener(view -> {});
        }
    }
}
