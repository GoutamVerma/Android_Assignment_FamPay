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

import com.goutam.fampay.models.hc6_model;
import com.squareup.picasso.Picasso;
import com.goutam.fampay.R;
import com.goutam.fampay.models.hc3_model;

import java.util.ArrayList;

public class hc6_adapter extends RecyclerView.Adapter<hc6_adapter.Viewholder> {

    private Context context;
    private ArrayList<hc6_model> courseModelArrayList;

    // Constructor
    public hc6_adapter(Context context, ArrayList<hc6_model> hc6ModelArrayList) {
        this.context = context;
        this.courseModelArrayList = hc6ModelArrayList;
    }

    @NonNull
    @Override
    public hc6_adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hc6_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hc6_adapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        hc6_model model = courseModelArrayList.get(position);
        holder.tv_title.setText(model.get_title());
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
            bigimage = itemView.findViewById(R.id.small_card_with_arrow_image);
            tv_title = itemView.findViewById(R.id.small_card_with_arrow_title);
            cardView = itemView.findViewById(R.id.small_card_with_arrow_view);
        }
    }
}