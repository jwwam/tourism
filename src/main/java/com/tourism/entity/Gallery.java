package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gallery")
public class Gallery extends BaseEntity  {
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
}
