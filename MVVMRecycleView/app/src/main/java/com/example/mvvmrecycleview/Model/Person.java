package com.example.mvvmrecycleview.Model;

public class Person {
    private int id;
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
