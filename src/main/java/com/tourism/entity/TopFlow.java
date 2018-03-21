package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="top_flow")
public class TopFlow extends BaseEntity {

    //名称
    @Column(name= "title")
    private String title;

    //说明
    @Column(name= "detail")
    private String detail;

    //内容
    @Column(name= "content")
    private String content;

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
}
