package com.example.securitytest.nettytest.componentstest;

import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.*;

public class AttrubuteMapTest {
//    NioServerSocketChannel
//    AbstractNioByteChannel
    public static void main(String[] args){

    }

    void test1(){
        ConstantPool<Person> pool = new ConstantPool() {
            @Override
            protected Constant newConstant(int id, String name) {
                return new Person(id, name);
            }
        };
        Person p1 = pool.valueOf("first");
        Person p2 = pool.valueOf("second");
        Person p3 = pool.valueOf(Person.class, "second");
        System.out.println(p1.compareTo( p2));
        System.out.println(p3.id());
    }
    void test2(){
        DefaultAttributeMap map = new DefaultAttributeMap();
        Attribute<String> attr =  map.attr(AttributeKey.newInstance("first"));
        attr.set("hello");
        System.out.println(attr.get());
    }
}
