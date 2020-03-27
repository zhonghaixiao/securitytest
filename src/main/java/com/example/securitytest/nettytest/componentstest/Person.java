package com.example.securitytest.nettytest.componentstest;

import io.netty.util.Constant;

public class Person implements Constant<Person> {
    private int id;
    private String name;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int compareTo(Person o) {
        return id < o.id() ? -1 : id == o.id ? 0 : 1;
    }
}
