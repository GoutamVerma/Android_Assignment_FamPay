package com.goutam.fampay.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.goutam.fampay.R;
import com.goutam.fampay.models.hc3_model;

import java.util.ArrayList;

public class hc3_adapter extends RecyclerView.Adapter<hc3_adapter.Viewholder> {

    private Context context;
    private ArrayList<hc3_model> courseModelArrayList;

    // Constructor
    public hc3_adapter(Context context, ArrayList<hc3_model> hc3ModelArrayList) {
        this.context = context;
        this.courseModelArrayList = hc3ModelArrayList;
    }

    @NonNull
    @Override
    public hc3_adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hc3_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hc3_adapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        hc3_model model = courseModelArrayList.get(position);
        holder.tv_title.setText(model.get_title());
        holder.description.setText( model.get_description());
        Picasso.get().load(model.get_url()).resize(100,100).into(holder.bigimage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(model.get_image());
                context.startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }
        });
        
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView bigimage;
        private TextView tv_title, description;
        private CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            bigimage = itemView.findViewById(R.id.image_big_card);
            tv_title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            cardView = itemView.findViewById(R.id.big_card_view);
        }
    }
}