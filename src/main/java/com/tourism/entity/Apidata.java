package com.tourism.entity;

import java.util.ArrayList;
import java.util.List;

public class Apidata{

    //
    private String name;

    //
    private String cityName;

    //
    private String areaName;

    //
    private String proName;

    //
    private String summary;

    //
    private String star;

    //
    private String content;

    //
    private String price;

    //
    private String address;

    //
    private String coupon;

    //
    private String attention;

    //
    private String opentime;

    //
    private List<Price> priceList = new ArrayList();

    //
    private List<Pic> picList = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public List<Pic> getPicList() {
        return picList;
    }

    public void setPicList(List<Pic> picList) {
        this.picList = picList;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}
