package com.example.Favorite_country.VO;

public class CountryVO {
    private String country; // 나라명
    private String country_eng; // 영문 나라명
    private String flag; // 국기
    private String capital; // 수도

    public CountryVO(String country, String country_eng, String flag, String capital) {
        this.country = country;
        this.country_eng = country_eng;
        this.flag = flag;
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_eng() {
        return country_eng;
    }

    public void setCountry_eng(String country_eng) {
        this.country_eng = country_eng;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
