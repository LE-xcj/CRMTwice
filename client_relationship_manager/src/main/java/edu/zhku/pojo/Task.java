package edu.zhku.pojo;

import java.util.Date;

public class Task {
    private String tid;

    private String tname;

    private Float target;

    private Integer type;

    private Date deadtime;

    private Integer statue;

    private String indroduction;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Float getTarget() {
        return target;
    }

    public void setTarget(Float target) {
        this.target = target;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDeadtime() {
        return deadtime;
    }

    public void setDeadtime(Date deadtime) {
        this.deadtime = deadtime;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getIndroduction() {
        return indroduction;
    }

    public void setIndroduction(String indroduction) {
        this.indroduction = indroduction == null ? null : indroduction.trim();
    }
}