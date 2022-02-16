package com.wdw.pojo;

public class Person {
    private String username;
    private String name;
    private Integer age;
    private String teleno;

    public Person() {
    }

    public Person(String username, String name, Integer age, String teleno) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.teleno = teleno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeleno() {
        return teleno;
    }

    public void setTeleno(String teleno) {
        this.teleno = teleno;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", teleno='" + teleno + '\'' +
                '}';
    }
}
