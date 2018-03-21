package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="desktop")
public class Desktop extends BaseEntity {

    //邮箱
    @Column(name= "email")
    private String email;

    //联系方式
    @Column(name= "iphone")
    private String iphone;

    //图片
    @Column(name= "pic")
    private String pic;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
