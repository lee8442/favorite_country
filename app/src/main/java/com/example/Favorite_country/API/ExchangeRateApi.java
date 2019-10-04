package com.example.Favorite_country.API;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeRateApi extends AsyncTask<String, String, String> {

    String authkey = "DaUUI00uJPjFJAvnwYNHiUs0omniTP3U"; // 인증키
    String searchdate = ""; // 요청 날짜 ex) 2015-01-01, 20150101, (DEFAULT)현재일
    String dataType = "AP01"; // 요청데이터 형식(AP01:환율, AP02:대출금리, AP03:국제금리)
    String apiURL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authkey + "&searchdate=" + searchdate + "&data=" + dataType;
    String str, receiveMsg;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
