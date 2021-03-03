package com.example.sensorgpsservice;

public class Users {

    private String uni;
    private  String gender;
    private String age;

    public Users(String uni, String gender, String age) {
        this.uni = uni;
        this.gender = gender;
        this.age = age;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
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
}
