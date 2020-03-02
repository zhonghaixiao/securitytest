package com.example.securitytest.designpattern;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args){
        ProcessChainImpl processChain = new ProcessChainImpl();

        List<Processor> processors = new ArrayList<>();
        processors.add(new ProcessorImpl());
        processors.add(new ProcessorImpl());
        processors.add(new ProcessorImpl());
        processors.add(new ProcessorImpl());

        processChain.setProcessorList(processors);
        Request request = new Request();
        Response response = new Response();
        processChain.next(request, response);
    }

}
