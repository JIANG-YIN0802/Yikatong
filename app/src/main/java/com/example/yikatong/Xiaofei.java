package com.example.yikatong;

import java.io.Serializable;

public class Xiaofei implements Serializable {
    String shanghu,time,money;
    public Xiaofei(String shanghu,String time,String money){
        this.shanghu = shanghu;
        this.time = time;
        this.money = money;
    }
    //访问器和修改器
    public String getShanghu(){
        return shanghu;
    }
    public void setShanghu(String shanghu){
        this.shanghu = shanghu;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getMoney(){
        return money;
    }
    public void setMoney(String money){
        this.money = money;
    }
}
