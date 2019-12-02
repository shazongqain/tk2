package com.example.tiku2.bean;

public class XqInfor {
    private String cp;
    private String time;
    private String road;
    private String infor;
    private int kf,fk;

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
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

    public XqInfor(String cp, String time, String road, String infor, int kf, int fk) {
        this.cp = cp;
        this.time = time;
        this.road = road;
        this.infor = infor;
        this.kf = kf;
        this.fk = fk;
    }
}
