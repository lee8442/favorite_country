package com.example.Favorite_country;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Favorite_country.API.ExchangeRateApi;
import com.example.Favorite_country.Adapter.Country_info_item_adapter;
import com.example.Favorite_country.VO.CountryInfoVO;
import com.example.Favorite_country.VO.CountryVO;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CountryInfoActivity extends AppCompatActivity {

    private CountryVO CountryVO;
    private CountryInfoVO CountryInfoVO;
    private Country_info_item_adapter adapter;

    private TextView country; // 나라명
    private ImageView flag; // 국기
    private TextView capital; // 수도
    private TextView continent; // 대륙
    private TextView language; // 공용어
    private TextView exchange_rate; // 환율
    private TextView country_warning; // 국가 여행경보
    private Button country_add_btn; // 선호 국가 추가 버튼
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
        language = findViewById(R.id.language);
        country_warning = findViewById(R.id.country_warning);
        country_add_btn = findViewById(R.id.country_add_btn);
        exchange_rate = findViewById(R.id.exchange_rate);

        Log.d("CountryInfoAct", "선택된 국가 : " + CountryVO.getCountry());

        int drawableResourceId = getImage(CountryVO.getFlag());

        // Glide
        Glide.with(this).load(drawableResourceId).into(flag);
        country.setText(CountryVO.getCountry() + "(" + CountryVO.getCountry_eng() + ")");
        capital.setText(capital.getText() + CountryVO.getCapital());
        continent.setText(continent.getText() + CountryVO.getContinent());
        language.setText(language.getText() + CountryVO.getLanguage());
        exchange_rate.setText(exchange_rate.getText() + getExchangeRate());

        country_warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.0404.go.kr/dev/country.mofa?group_idx=&stext=" + CountryVO.getCountry()));
                startActivity(intent);
            }
        });

        country_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String country = CountryVO.getCountry();
                Toast.makeText(getApplicationContext(),"선호 국가(" + country + ") 추가되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Glide drawable 이미지 경로 가져오기
    public int getImage(String imageName) {
        // drawable 이미지 리소스 ID 찾기
        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return drawableResourceId;
    }

    // 환율 정보 가져오기 한국 수출입은행 API
    public String getExchangeRate() {
        String result = "";
        try {
            result = new ExchangeRateApi().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void setRecyclerView() {
        // recyclerView에 표시할 데이터 리스트 생성
        ArrayList<CountryInfoVO> list = new ArrayList<CountryInfoVO>();
        for(int i = 0; i < 6; i++){
            //CountryInfoVO CountryInfoVO = new CountryInfoVO(CountryVO.getCountry_eng() + "_img_" + i);
            switch(i) {
                case 0:
                    CountryInfoVO = new CountryInfoVO("australia");
                    list.add(CountryInfoVO);
                    break;
                case 1:
                    CountryInfoVO = new CountryInfoVO("brazil");
                    list.add(CountryInfoVO);
                    break;
                case 2:
                    CountryInfoVO = new CountryInfoVO("japan");
                    list.add(CountryInfoVO);
                    break;
                case 3:
                    CountryInfoVO = new CountryInfoVO("usa");
                    list.add(CountryInfoVO);
                    break;
                case 4:
                    CountryInfoVO = new CountryInfoVO("ghana");
                    list.add(CountryInfoVO);
                    break;
                case 5:
                    CountryInfoVO = new CountryInfoVO("france");
                    list.add(CountryInfoVO);
                    break;
            }
        }

        // recyclerView에 LinearLayoutManager 객체 지정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4)) ;

        // recyclerView에 Country_info_item_adapter 객체 지정
        adapter = new Country_info_item_adapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Country_info_item_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Toast.makeText(getApplicationContext(),"해당 게시글로 이동.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
