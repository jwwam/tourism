package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="line_info")
public class LineInfo extends BaseEntity {

    //名称
    @Column(name= "title")
    private String title;
    //价格
    @Column(name= "price")
    private String price;
    //地址
    @Column(name= "address")
    private String address;
    //图片
    @Column(name= "image")
    private String image;
    //说明
    @Column(name= "detail")
    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
