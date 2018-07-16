package edu.zhku.pojo;

/***
 * 
* <p>Title: Bill.java</p>  
* <p>Description:
* 	state 状态值有5个
* 	2 ： 完成
* 	1 ： 进行中
* 	0 ： 未处理
* 	-1： 拒绝
* 	-2： 取消
* </p>  
* 
* @author xcj
* @date 2018年6月19日
 */
public class Bill {
    private String bid;

    private Float money;

    private String createtime;

    private Integer statue;

    private String sid;

    private String cid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}