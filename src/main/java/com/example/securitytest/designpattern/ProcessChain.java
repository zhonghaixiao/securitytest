package com.example.securitytest.designpattern;

public interface ProcessChain {

    void next(Request request, Response response);

}
