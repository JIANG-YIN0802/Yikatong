package com.example.yikatong;

public class User {
    private Integer id;
    private String xuehao;
    private String name;
    private String gender;
    private String age;
    private String phone;
    private String sushe;
    private String balance;
    private String cardstate;
    private String password;



    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }


    public String getXuehao(){
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSushe() {
        return sushe;
    }

    public void setSushe(String sushe) {
        this.sushe = sushe;
    }

    public String getPassword() {
        return password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCardstate() {
        return cardstate;
    }

    public void setCardstate(String cardstate) {
        this.cardstate = cardstate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



