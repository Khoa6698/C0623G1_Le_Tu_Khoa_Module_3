package com.example.demo.modol;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String birthDay;
    private String address;
    private String img;

    public Customer(){}

    public Customer(int id, String name, String birthDay, String address, String img) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
