package com.example.securitytest.designpattern;

import java.util.concurrent.atomic.AtomicInteger;

public class ProcessorImpl implements Processor {
    private static AtomicInteger count = new AtomicInteger(0);
    private final int id;

    public ProcessorImpl(){
        id = count.getAndIncrement();
    }

    @Override
    public void process(Request request, Response response, ProcessChain chain) {
        System.out.println("request processed by " + id);
        chain.next(request, response);
        System.out.println("response processed by " + id);
    }

}
