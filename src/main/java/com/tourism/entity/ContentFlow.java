package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="content_flow")
public class ContentFlow extends BaseEntity {

    //名称
    @Column(name= "title")
    private String title;

    //说明
    @Column(name= "detail")
    private String detail;

    //内容
    @Column(name= "content")
    private String content;

    //图片
    @Column(name= "img")
    private String img;

    //星级
    @Column(name= "star")
    private String star;

    //时间
    @Column(name= "time")
    private String time;

    //价格
    @Column(name= "price")
    private String price;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
