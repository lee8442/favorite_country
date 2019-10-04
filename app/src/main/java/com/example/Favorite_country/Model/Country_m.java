package com.example.Favorite_country.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.Favorite_country.DB.DBHelper;
import com.example.Favorite_country.VO.CountryVO;
import java.util.ArrayList;


public class Country_m {

    private DBHelper DBHelper;
    private SQLiteDatabase db;
    private String TABLE_NAME = DBHelper.TABLE_NAME;

    public Country_m(Context context) {
        DBHelper = DBHelper.getInstance(context);
        db = DBHelper.getDb();
    }

    public void createTable() {
        DBHelper.onCreate(db);
        if(!isData()) {
            String sql = "insert into " + TABLE_NAME + "(country, country_eng, flag, capital, continent, language, currency, religion) select '일본', 'Japan', 'japan', '도쿄', '아시아', '일본어', '엔', '신토' " +
                    "union all select '프랑스', 'France', 'france', '파리', '유럽', '불어', '유로', '가톨릭교' union all select '미국', 'USA', 'usa', '워싱턴 D.C.', '북아메리카', '영어', '달러', '개신교' " +
                    "union all select '브라질', 'Brazil', 'brazil', '브라질리아', '남아메리카', '포르투갈어', '헤알', '천주교' union all select '가나', 'Ghana', 'ghana', '아크라', '아프리카', '영어', '세디', '기독교' " +
                    "union all select '호주', 'Australia', 'australia', '캔버라', '오세아니아', '영어', '호주 달러', '기독교';";
            db.execSQL(sql);
        }
    }

    public boolean isData(){
        boolean isData = false;
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor result = db.rawQuery(sql, null);
        // result(Cursor)객체가 비어있으면 false 리턴
        if(result.moveToFirst()){
            isData = true;
        }
        result.close();
        return isData;
    }

    public ArrayList<CountryVO> getList() {
        ArrayList<CountryVO> list = new ArrayList<CountryVO>();
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.d("C_model", "총 리스트 수 : " + cursor.getCount());
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            CountryVO CountryVO = new CountryVO(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
            list.add(CountryVO);
            Log.d("C_model", i + "번 쨰 CountryVO { 국가명 : " + CountryVO.getCountry() + " / 국가 영문명 : " + CountryVO.getCountry_eng()
                    + " / 국기 : " + CountryVO.getFlag() + " / 수도 : " + CountryVO.getCapital() + " / 대륙 : " + CountryVO.getContinent()
                    + " / 언어 : " + CountryVO.getLanguage()+ " / 통화 : " + CountryVO.getCurrency() + " / 종교 : " + CountryVO.getReligion() + " }");
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
