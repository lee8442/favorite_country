package com.example.Favorite_country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Favorite_country.Adapter.Country_item_adapter;
import com.example.Favorite_country.Model.Country_m;
import com.example.Favorite_country.VO.CountryVO;
import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    private Country_m Country_m;
    private ArrayList<CountryVO> list;
    private Country_item_adapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        // model 세팅
        setModel();

        // RecyclerView 세팅
        setRecyclerView();
    }

    public void setModel() {
        Country_m = new Country_m(CountryActivity.this);
        Country_m.createTable();
    }

    public void setRecyclerView() {
        // recyclerView에 표시할 데이터 리스트 생성.
        list = Country_m.getList();

        // recyclerView에 LinearLayoutManager 객체 지정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // recyclerView에 Country_item_adapter 객체 지정
        adapter = new Country_item_adapter(list) ;
        recyclerView.setAdapter(adapter) ;

        adapter.setOnItemClickListener(new Country_item_adapter.OnItemClickListener() {
            // 선호 국가 추가
            @Override
            public void onItemClick(View view, int position){
                String country = list.get(position).getCountry();
                Toast.makeText(getApplicationContext(),"선호 국가(" + country + ") 추가되었습니다.",Toast.LENGTH_SHORT).show();
            }

            // 상세 정보 페이지 전환
            @Override
            public void onBtnClick(int position) {
                CountryVO selectCountry = list.get(position);
                Intent intent = new Intent(getApplicationContext(), CountryInfoActivity.class);
                intent.putExtra("selectCountry", selectCountry); // 선택된 국가 정보 전달
                startActivity(intent);
            }
        });
    }
}
