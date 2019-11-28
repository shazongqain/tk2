package com.example.tiku2.bean;

public class ZdglInfor {

    private String cp;
    private String jine;
    private String name;
    private String time;
    private String yue;

    public String getYue() {
        return yue;
    }

    public void setYue(String yue) {
        this.yue = yue;
    }

    public ZdglInfor(String cp, String jine, String name, String time, String yue) {
        this.cp = cp;
        this.jine = jine;
        this.name = name;
        this.time = time;
        this.yue = yue;
    }

    public ZdglInfor() {
    }



    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
