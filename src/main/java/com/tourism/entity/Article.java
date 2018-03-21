package com.tourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="article")
public class Article extends BaseEntity{

    //文章类别（原创，转载，翻译）
    @Column(name= "wzlb")
    private String wzlb;

    //文章标题
    @Column(name= "wzbt")
    private String wzbt;

    //文章分类
    @Column(name= "wzfl")
    private String wzfl;

    //摘要
    @Column(name= "zy")
    private String zy;

    //状态（已发，草稿，回收）
    @Column(name= "zt")
    private String zt;

    //阅读次数
    @Column(name= "ydcs")
    private String ydcs;

    //是否评论
    @Column(name= "sfpl")
    private String sfpl;

    //点赞次数
    @Column(name= "dzcs")
    private String dzcs;

    //点踩次数
    @Column(name= "dccs")
    private String dccs;

    //发布人员
    @Column(name= "fbry")
    private String fbry;

    //发布时间
    @Column(name= "fbsj")
    private Date fbsj;

    //审核状态
    @Column(name= "shzt")
    private String shzt;

    //blogid
    @Column(name= "blogid")
    private String blogid;

    //删除状态
    @Column(name= "delflag")
    private String delflag;

    @Column(name= "bak1")
    private String bak1;

    @Column(name= "bak2")
    private String bak2;

    @Column(name= "bak3")
    private String bak3;

    @Column(name= "bak4")
    private String bak4;

    @Column(name= "bak5")
    private String bak5;

    //文章内容
    @Column(name= "wznr")
    private String wznr;

    public String getWzlb() {
        return wzlb;
    }

    public void setWzlb(String wzlb) {
        this.wzlb = wzlb;
    }

    public String getWzbt() {
        return wzbt;
    }

    public void setWzbt(String wzbt) {
        this.wzbt = wzbt;
    }

    public String getWzfl() {
        return wzfl;
    }

    public void setWzfl(String wzfl) {
        this.wzfl = wzfl;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYdcs() {
        return ydcs;
    }

    public void setYdcs(String ydcs) {
        this.ydcs = ydcs;
    }

    public String getSfpl() {
        return sfpl;
    }

    public void setSfpl(String sfpl) {
        this.sfpl = sfpl;
    }

    public String getDzcs() {
        return dzcs;
    }

    public void setDzcs(String dzcs) {
        this.dzcs = dzcs;
    }

    public String getDccs() {
        return dccs;
    }

    public void setDccs(String dccs) {
        this.dccs = dccs;
    }

    public String getFbry() {
        return fbry;
    }

    public void setFbry(String fbry) {
        this.fbry = fbry;
    }

    public Date getFbsj() {
        return fbsj;
    }

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1;
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2;
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3;
    }

    public String getBak4() {
        return bak4;
    }

    public void setBak4(String bak4) {
        this.bak4 = bak4;
    }

    public String getBak5() {
        return bak5;
    }

    public void setBak5(String bak5) {
        this.bak5 = bak5;
    }

    public String getWznr() {
        return wznr;
    }

    public void setWznr(String wznr) {
        this.wznr = wznr;
    }
}
