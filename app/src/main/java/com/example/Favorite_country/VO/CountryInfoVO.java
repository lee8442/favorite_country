package com.example.Favorite_country.VO;

import java.io.Serializable;

public class CountryInfoVO implements Serializable {
    private String img;

    public CountryInfoVO(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
