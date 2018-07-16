package edu.zhku.pojo;

public class Group {
    private String gid;

    private String gname;

    private String indroduction;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getIndroduction() {
        return indroduction;
    }

    public void setIndroduction(String indroduction) {
        this.indroduction = indroduction == null ? null : indroduction.trim();
    }
}