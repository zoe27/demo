package cn.com.learn.mybatisDemo.vo;

import java.io.Serializable;

public class ProductM implements Serializable {
    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer premain;

    private Integer mid;

    private static final long serialVersionUID = 1L;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    public Integer getPremain() {
        return premain;
    }

    public void setPremain(Integer premain) {
        this.premain = premain;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductM other = (ProductM) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getPname() == null ? other.getPname() == null : this.getPname().equals(other.getPname()))
            && (this.getPprice() == null ? other.getPprice() == null : this.getPprice().equals(other.getPprice()))
            && (this.getPremain() == null ? other.getPremain() == null : this.getPremain().equals(other.getPremain()))
            && (this.getMid() == null ? other.getMid() == null : this.getMid().equals(other.getMid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getPname() == null) ? 0 : getPname().hashCode());
        result = prime * result + ((getPprice() == null) ? 0 : getPprice().hashCode());
        result = prime * result + ((getPremain() == null) ? 0 : getPremain().hashCode());
        result = prime * result + ((getMid() == null) ? 0 : getMid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", pname=").append(pname);
        sb.append(", pprice=").append(pprice);
        sb.append(", premain=").append(premain);
        sb.append(", mid=").append(mid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}