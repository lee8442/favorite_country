package com.example.Favorite_country;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Favorite_country.Adapter.Country_item_adapter;
import com.example.Favorite_country.Model.Country_m;
import com.example.Favorite_country.VO.CountryVO;

import java.util.ArrayList;

public class CountryInfoActivity extends AppCompatActivity {

    private Country_m Country_m;
    private com.example.Favorite_country.VO.CountryVO CountryVO;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        // model 세팅
        setModel();

        // RecyclerView 세팅
        setRecyclerView();
    }

    public void setModel() {
        Country_m = new Country_m(CountryInfoActivity.this);
        Country_m.createTable();
    }

    public void setRecyclerView() {
        // recyclerView에 표시할 데이터 리스트 생성.
        ArrayList<CountryVO> list = Country_m.getList();

        // recyclerView에 LinearLayoutManager 객체 지정.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // recyclerView에 SimpleTextAdapter 객체 지정.
        Country_item_adapter adapter = new Country_item_adapter(list) ;
        recyclerView.setAdapter(adapter) ;

        /*adapter.setOnItemClickListener(new Country_item_adapter.OnItemClickListener() {
            @Override
            public  void onItemClick(View view, int position){
                // Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                // startActivity(intent);
            }
        });*/
    }
}
