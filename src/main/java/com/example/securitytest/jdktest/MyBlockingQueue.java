package com.example.securitytest.jdktest;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyBlockingQueue<T> extends AbstractQueue<T> implements BlockingQueue<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(T t) throws InterruptedException {

    }

    @Override
    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public T take() throws InterruptedException {
        return null;
    }

    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
