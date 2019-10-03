package com.example.Favorite_country.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.Favorite_country.R;
import com.example.Favorite_country.VO.CountryVO;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView country; // 나라명
    private TextView country_eng; // 영문 나라명
    private ImageView flag; // 국기
    private TextView capital; // 수도

    OnItemClickListener listener; //클릭이벤트처리관련 변수

    public ViewHolder(@NonNull final View itemView) { //뷰홀더는 각각의 아이템을 위한 뷰를 담고있다.
        super(itemView);

        country = itemView.findViewById(R.id.country);
        country_eng = itemView.findViewById(R.id.country_eng);
        flag = itemView.findViewById(R.id.flag);
        capital = itemView.findViewById(R.id.capital);

        // 아이템 클릭이벤트처리
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if(listener != null ){
                   // listener.onItemClick(ViewHolder.this, itemView, position);
                }
            }
        });
    }

    // setItem 메소드는 CountryVO 객체를 전달받아 뷰홀더 안에 있는 뷰에 데이터를 설정하는 역할을 합니다.
    public void setItem(CountryVO item) {
        country.setText(item.getCountry());
        country_eng.setText(item.getCountry_eng());
        capital.setText(item.getCapital());
    }

    // 클릭이벤트처리
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
