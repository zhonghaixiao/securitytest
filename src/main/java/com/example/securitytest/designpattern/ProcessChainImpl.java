package com.example.securitytest.designpattern;

import java.util.ArrayList;
import java.util.List;

public class ProcessChainImpl implements ProcessChain {

    List<Processor> processorList;
    int current;

    public ProcessChainImpl(){
        current = 0;
        processorList = new ArrayList<>();
    }

    public void setProcessorList(List<Processor> processorList){
        this.processorList = processorList;
    }

    @Override
    public void next(Request request, Response response) {
        if (++current > processorList.size()){
            System.out.println(" end ");
        }else{
            processorList.get(current-1).process(request, response, this);
        }
    }
}
