package com.example.tiku2.bean;

public class ZlInfor {
    private String cp;
    private  int wcl;
    private   int kf,fk;

    public ZlInfor() {
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getWcl() {
        return wcl;
    }

    public void setWcl(int wcl) {
        this.wcl = wcl;
    }

    public int getKf() {
        return kf;
    }

    public void setKf(int kf) {
        this.kf = kf;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    public ZlInfor(String cp, int wcl, int kf, int fk) {
        this.cp = cp;
        this.wcl = wcl;
        this.kf = kf;
        this.fk = fk;
    }
}
