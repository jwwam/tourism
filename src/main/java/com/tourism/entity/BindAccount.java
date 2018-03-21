package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ppm on 2017/8/28.
 */
@Entity
@Table(name="bind_account")
public class BindAccount extends BaseEntity{
    @Column(name= "account")
    private String account;
    @Column(name= "password")
    private String password;
    @Column(name= "open_id")
    private  String openId;

    @Column(name= "company_name")
   private String  companyName;

    @Column(name= "id_card_number")
    private String  idCardNumber;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

}
