package com.example.Favorite_country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.Favorite_country.Adapter.Country_item_adapter;
import com.example.Favorite_country.Model.Favorite_country_m;
import com.example.Favorite_country.VO.CountryVO;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Favorite_country_m Favorite_country_m;
    private CountryVO CountryVO;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Favorite_country_m = new Favorite_country_m(MainActivity.this);
        Favorite_country_m.createTable();

        // recyclerView에 표시할 데이터 리스트 생성.
        ArrayList<CountryVO> list = Favorite_country_m.getList();

        // recyclerView에 LinearLayoutManager 객체 지정.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // recyclerView에 SimpleTextAdapter 객체 지정.
        Country_item_adapter adapter = new Country_item_adapter(list) ;
        recyclerView.setAdapter(adapter) ;


    }
}
