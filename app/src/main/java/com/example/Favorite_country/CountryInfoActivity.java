package com.example.Favorite_country;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Favorite_country.Adapter.Country_item_adapter;
import com.example.Favorite_country.Model.Country_m;
import com.example.Favorite_country.VO.CountryVO;

import java.util.ArrayList;

public class CountryInfoActivity extends AppCompatActivity {

    private CountryVO CountryVO;

    private TextView country; // 나라명
    private ImageView flag; // 국기
    private TextView capital; // 수도
    private TextView continent; // 대륙
    private TextView language; // 공용어
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        // intent에서 선택된 CountryVO 객체 받기
        getIntentExtra();

        // View 초기화 및 설정
        setView();

        // RecyclerView 세팅
        setRecyclerView();
    }

    public void getIntentExtra() {
        Intent intent = getIntent();
        CountryVO = (CountryVO) intent.getSerializableExtra("selectCountry");
    }

    public void setView() {
        country = findViewById(R.id.country);
        flag = findViewById(R.id.flag);
        capital = findViewById(R.id.capital);
        continent = findViewById(R.id.continent);

        int drawableResourceId = getImage(CountryVO.getFlag());

        // Glide
        Glide.with(this).load(drawableResourceId).into(flag);
        country.setText(CountryVO.getCountry() + "(" + CountryVO.getCountry_eng() + ")");
        capital.setText(CountryVO.getCapital());
        continent.setText(CountryVO.getContinent());
        language.setText(CountryVO.getLanguage());
    }

    // Glide drawable 이미지 경로 가져오기
    public int getImage(String imageName) {
        // drawable 이미지 리소스 ID 찾기
        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return drawableResourceId;
    }

    public void setRecyclerView() {
        // recyclerView에 표시할 데이터 리스트 생성.
        //ArrayList<CountryVO> list = Country_m.getList();

        // recyclerView에 LinearLayoutManager 객체 지정.
        /*recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // recyclerView에 SimpleTextAdapter 객체 지정.
        Country_item_adapter adapter = new Country_item_adapter(list) ;
        recyclerView.setAdapter(adapter) ;

        adapter.setOnItemClickListener(new Country_item_adapter.OnItemClickListener() {
            @Override
            public  void onItemClick(View view, int position){
                // Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                // startActivity(intent);
            }
        });*/
    }
}
