package com.example.Favorite_country.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Favorite_country.R;
import com.example.Favorite_country.VO.CountryInfoVO;

import java.util.ArrayList;


public class Country_info_item_adapter extends RecyclerView.Adapter<Country_info_item_adapter.ViewHolder> {

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<CountryInfoVO> items;

    // 아이템뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView recent_visitor; // 국기

        ViewHolder(View itemView) {
            super(itemView) ;

            recent_visitor = itemView.findViewById(R.id.recent_visitor);

            // 클릭 이벤트 리스너
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if(listener != null) {
                            listener.onItemClick(view, position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Country_info_item_adapter(ArrayList<CountryInfoVO> list) {
        items = list;
    }

    // onCreateViewHolder() - 아이템뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public Country_info_item_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.country_info_item, parent,false);
        Country_info_item_adapter.ViewHolder VH = new Country_info_item_adapter.ViewHolder(view);

        return VH;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(Country_info_item_adapter.ViewHolder holder, int position) {
        CountryInfoVO CountryInfoVO = items.get(position);

        int drawableResourceId = getImage(holder, CountryInfoVO.getImg());

        // Glide
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.recent_visitor);
    }

    // getItemCount() - 전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Glide drawable 이미지 경로 가져오기
    public int getImage(Country_info_item_adapter.ViewHolder holder, String imageName) {
        // drawable 이미지 리소스 ID 찾기
        int drawableResourceId = holder.itemView.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
}
