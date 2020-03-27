package com.example.securitytest.nettytest.componentstest;

import io.netty.util.ConstantPool;

public class PersonConstantPool extends ConstantPool<Person> {
    @Override
    protected Person newConstant(int id, String name) {
        return null;
    }
}
