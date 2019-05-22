package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="score")
public class Score extends BaseEntity implements Comparable<Score>{
    @Column(name= "grade")
    private  String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(String sceneryId) {
        this.sceneryId = sceneryId;
    }

    @Column(name="user_id")
    private String userId;
    @Column(name="scenery_id")
    private String sceneryId;

    @Override
    public int compareTo(Score o) {
        int thisId = Integer.parseInt(this.id);
        int i = thisId - Integer.parseInt(o.id);
        return i;
    }
}