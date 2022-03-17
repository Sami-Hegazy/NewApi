package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.task2.Models.NewHeadLines;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageDetailView;
    TextView title , desc , content , url;
    Button btn;
    List<NewHeadLines> newHeadLinesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageDetailView = findViewById(R.id.img_details);
        title = findViewById(R.id.text_title);
        desc = findViewById(R.id.text_desc);
        content = findViewById(R.id.content_text);
        url = findViewById(R.id.text_link);
        btn = findViewById(R.id.btn_web);


        Intent i = getIntent();
        newHeadLinesList =  i.getParcelableArrayListExtra("news");
        int po = i.getIntExtra("position",-1);


        Picasso.get().load(newHeadLinesList.get(po).getUrlToImage()).into(imageDetailView);
        title.setText(newHeadLinesList.get(po).getTitle());
        desc.setText(newHeadLinesList.get(po).getDescription());
        content.setText(newHeadLinesList.get(po).getContent());

        btn.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newHeadLinesList.get(po).getUrl()));
            startActivity(browserIntent);
        });

    }
}