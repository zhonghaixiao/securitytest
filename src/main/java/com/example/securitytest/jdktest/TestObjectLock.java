package com.example.securitytest.jdktest;

/**
 * Object锁实现生产者消费者
 */
public class TestObjectLock {

    static class Storage<T>{
        private T[] data;
        private int count;
        public Storage(int capacity){
            data = (T[]) new Object[capacity];
            count = 0;
        }
        public synchronized boolean put(T val){
            while(count == data.length - 1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("thread " + Thread.currentThread().getName() + "被中断");
                    return false;
                }
            }
            data[++count] = val;
            notify();
            return true;
        }
        public synchronized T get(){
            while (count == 0){
                try{
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("thread " + Thread.currentThread().getName() + "被中断");
                    return null;
                }
            }
            T val = data[count];
            data[count--] = null;
            notify();
            return val;
        }
    }

    public static void main(String[] args){
        Storage<Integer> storage = new Storage<>(10);
        Runnable producer = () -> {
            for (int i = 0; i < 10; i++){
                storage.put(i);
                System.out.println(Thread.currentThread().getName() + " produce " + i);
            }
        };
        Runnable consumer = () ->{
            for (;!Thread.interrupted();){
                int val = storage.get();
                System.out.println(Thread.currentThread().getName() + " consume " + val);
            }
        };
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        Thread t3 = new Thread(producer);
        Thread t4 = new Thread(consumer);
        Thread t5 = new Thread(consumer);
        Thread t6 = new Thread(consumer);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.interrupt();
            t5.interrupt();
            t6.interrupt();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final result = " + storage.count);
    }

}
