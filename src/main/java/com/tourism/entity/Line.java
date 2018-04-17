package com.tourism.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="line")
public class Line extends BaseEntity  {
    //名称
    @Column(name= "title")
    private String title;

    //说明
    @Column(name= "detail")
    private String detail;

    //价格
    @Column(name= "price")
    private String price;

    //日期
    @Column(name= "day")
    private String day;

    //地址
    @Column(name= "address")
    private String address;

    //图片
    @Column(name= "img")
    private String img;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="line_id")
    private List<LineInfo> lineInfoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
