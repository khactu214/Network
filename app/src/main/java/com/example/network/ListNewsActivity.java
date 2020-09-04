package com.example.network;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.network.adapter.NewsAdapter;
import com.example.network.interfaces.NewsOnClick;
import com.example.network.model.Item;
import com.example.network.network.APIManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListNewsActivity extends AppCompatActivity {
    private NewsAdapter adapter;
    private List<Item> listData;
    private RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //B1: Data source
        getListData();

        //B2: Adapter
        adapter = new NewsAdapter(this, listData);

        //B3: Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                (this,RecyclerView.VERTICAL,false);

        rvNews = (RecyclerView) findViewById(R.id.rvNews);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setAdapter(adapter);

    }

    private void getListData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);
        service.getListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.body() != null){
                    listData = response.body();
                    adapter.reloadData(listData);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(ListNewsActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}