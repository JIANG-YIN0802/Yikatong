package com.example.yikatong.book;

import java.io.Serializable;

public class Book2 implements Serializable {
    String name,author,borrow_time,due_time,actual_time;

    public Book2(String name, String author, String borrow_time, String due_time, String actual_time) {
        this.name = name;
        this.author = author;
        this.borrow_time = borrow_time;
        this.due_time = due_time;
        this.actual_time = actual_time;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getBorrow_time(){
        return borrow_time;
    }
    public void setBorrow_time(String borrow_time){
        this.borrow_time = borrow_time;
    }
    public String getDue_time(){
        return due_time;
    }
    public void setDue_time(String due_time){
        this.due_time = due_time;
    }
    public String getActual_time(){
        return actual_time;
    }
    public void setActual_time(String actual_time){
        this.actual_time = actual_time;
    }
}
