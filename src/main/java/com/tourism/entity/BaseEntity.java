package com.tourism.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by ppm on 2017/7/20.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    protected String id;
    @Column(name= "create_date") //创建时间
    Date createDate = new Date();


    @Column(name= "release_date")//发布时间
    Date releaseDate;//发布时间
    @Column(name= "update_date")//修改时间
    Date updateDate;
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



}
