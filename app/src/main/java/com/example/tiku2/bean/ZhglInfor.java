package com.example.tiku2.bean;

public class ZhglInfor {
    private String pinpai;
    private String name;
    private String cp;
    private boolean check;
    private int yz;

    public int getYz() {
        return yz;
    }

    public void setYz(int yz) {
        this.yz = yz;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public ZhglInfor(String pinpai, String name, String cp, int yue) {
        this.pinpai = pinpai;
        this.name = name;
        this.cp = cp;
        this.yue = yue;
    }

    private int yue;

    public ZhglInfor() {
    }

    public String getPinpai() {
        return pinpai;
    }

    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYue() {
        return yue;
    }

    public void setYue(int yue) {
        this.yue = yue;
    }

}
