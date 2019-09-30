package com.example.Favorite_country.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.Favorite_country.DB.DBHelper;
import com.example.Favorite_country.VO.CountryVO;
import java.util.ArrayList;


public class Favorite_country_m {

    private DBHelper DBHelper;
    private SQLiteDatabase db;
    private String TABLE_NAME = DBHelper.TABLE_NAME;

    public Favorite_country_m(Context context) {
        DBHelper = DBHelper.getInstance(context);
        db = DBHelper.getDb();
    }

    public void createTable() {
        DBHelper.onCreate(db);
        if(!isData()) {
            String sql = "insert into " + TABLE_NAME + "(country, country_eng, flag, capital) values('한국', 'korea', -700018, '서울');";
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
        Log.d("FC_model", "총 리스트 수 : " + cursor.getCount());
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            CountryVO CountryVO = new CountryVO(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4));
            list.add(CountryVO);
            Log.d("FC_model", "CountryVO { 국가명 : " + CountryVO.getCountry() + " / 국가 영문명 : " + CountryVO.getCountry_eng()
                    + " / 국기 : " + CountryVO.getFlag() + " / 수도 : " + CountryVO.getCapital() + " }");
        }
        cursor.close();
        return list;
    }
}
