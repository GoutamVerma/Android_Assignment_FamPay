package com.goutam.fampay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.goutam.fampay.adapter.hc1_adapter;
import com.goutam.fampay.adapter.hc3_adapter;
import com.goutam.fampay.adapter.hc5_adapter;
import com.goutam.fampay.adapter.hc6_adapter;
import com.goutam.fampay.adapter.hc9_adapter;
import com.goutam.fampay.models.hc1_model;
import com.goutam.fampay.models.hc3_model;
import com.goutam.fampay.models.hc5_model;
import com.goutam.fampay.models.hc6_model;
import com.goutam.fampay.models.hc9_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView hc3recycle,hc6recycle,hc5recycle,hc1recycle,hc9recycle;
    private ArrayList<hc3_model> hc3_model_array;
    private ArrayList<hc6_model> hc6_model_array;
    private ArrayList<hc5_model> hc5_model_array;
    private ArrayList<hc1_model> hc1_model_array;
    private ArrayList<hc9_model> hc9_model_array;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    get_data.store();
                }
            });
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updatehc3();
            updatehc6();
            updatehc5();
            updatehc9();
            updatehc1();
            progressDialog.dismiss();
        }
        else{
            AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();
            alertDialog1.setTitle("Alert");
            alertDialog1.setMessage("No Internet Connection!");
            alertDialog1.show();
        }

      }

    public void updatehc3(){

        hc3recycle = findViewById(R.id.idhc3);
        hc3_model_array = new ArrayList<>();
        HashMap<String,List<List<String>>> packets = get_data.packets;
        for(int i=0;i<packets.get("HC3").size();i++){
             hc3_model_array.add(new hc3_model(packets.get("HC3").get(i).get(0), packets.get("HC3").get(i).get(1), packets.get("HC3").get(i).get(3),packets.get("HC3").get(i).get(2)));
         }
        hc3_adapter courseAdapter = new hc3_adapter(this, hc3_model_array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        hc3recycle.setLayoutManager(linearLayoutManager);
        hc3recycle.setAdapter(courseAdapter);
        hc3recycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    public void updatehc6(){

        hc6recycle = findViewById(R.id.idhc6);
        hc6_model_array = new ArrayList<>();

        HashMap<String,List<List<String>>> packets = get_data.packets;
        for(int i=0;i<packets.get("HC6").size();i++){
            hc6_model_array.add(new hc6_model(packets.get("HC6").get(i).get(0), packets.get("HC6").get(i).get(1), packets.get("HC6").get(i).get(3),packets.get("HC6").get(i).get(2)));
        }
        hc6_adapter courseAdapter = new hc6_adapter(this, hc6_model_array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        hc6recycle.setLayoutManager(linearLayoutManager);
        hc6recycle.setAdapter(courseAdapter);
        hc6recycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }

    public void updatehc5(){

        hc5recycle = findViewById(R.id.idhc5);
        hc5_model_array = new ArrayList<>();

        HashMap<String,List<List<String>>> packets = get_data.packets;
        for(int i=0;i<packets.get("HC5").size();i++){
            hc5_model_array.add(new hc5_model( packets.get("HC5").get(i).get(1),packets.get("HC5").get(i).get(2)));
        }
        hc5_adapter courseAdapter = new hc5_adapter(this, hc5_model_array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        hc5recycle.setLayoutManager(linearLayoutManager);
        hc5recycle.setAdapter(courseAdapter);
        hc5recycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }
    public void updatehc1(){

        hc1recycle = findViewById(R.id.idhc1);
        hc1_model_array = new ArrayList<>();

        HashMap<String,List<List<String>>> packets = get_data.packets;
        for(int i=0;i<packets.get("HC1").size();i++){
            hc1_model_array.add(new hc1_model(packets.get("HC1").get(i).get(0), packets.get("HC1").get(i).get(1), packets.get("HC1").get(i).get(2)));
        }
        hc1_adapter courseAdapter = new hc1_adapter(this, hc1_model_array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        hc1recycle.setLayoutManager(linearLayoutManager);
        hc1recycle.setAdapter(courseAdapter);

    }
    public void updatehc9(){

        hc9recycle = findViewById(R.id.idhc9);
        hc9_model_array = new ArrayList<>();

        HashMap<String,List<List<String>>> packets = get_data.packets;
        for(int i=0;i<packets.get("HC9").size();i++){
            hc9_model_array.add(new hc9_model(packets.get("HC9").get(i).get(1),packets.get("HC9").get(i).get(2)));
        }
        hc9_adapter courseAdapter = new hc9_adapter(this, hc9_model_array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        hc9recycle.setLayoutManager(linearLayoutManager);
        hc9recycle.setAdapter(courseAdapter);
        hc9recycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }


}