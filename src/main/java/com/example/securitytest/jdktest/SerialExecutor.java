package com.example.securitytest.jdktest;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {

    private Executor executor;
    private Queue<Runnable> queue;

    public SerialExecutor(Executor executor){
        this.executor = executor;
        this.queue = new ArrayDeque<>(10);
    }

    @Override
    public void execute(Runnable command) {

    }
}
