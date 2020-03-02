package com.example.securitytest.designpattern;

public interface Processor {

    void process(Request request, Response response,ProcessChain chain);

}
