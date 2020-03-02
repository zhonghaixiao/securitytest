package com.example.securitytest.jdktest;

import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {


    static class ReactorTask implements  Runnable{
//        NioServerSocketChannel
//        Channel

        Executor

        private Selector selector;
        public ReactorTask() throws IOException {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 2000));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        @Override
        public void run() {
            while (true){
                try{
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    SelectionKey key = null;
                    while(it.hasNext()){
                        key = it.next();
                        it.remove();
                        try{
                            if (key.isValid()){
                                if (key.isAcceptable()){
                                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                    SocketChannel sc = ssc.accept();
                                    //设置连接参数
                                    sc.configureBlocking(false);
                                    sc.socket().setReuseAddress(true);
                                }
                                if (key.isReadable()){
                                    SocketChannel sc = (SocketChannel) key.channel();
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                    int readbytes = sc.read(readBuffer);
                                    if (readbytes>0){
                                        readBuffer.flip();
                                        byte[] bytes = new byte[readBuffer.remaining()];
                                        readBuffer.get(bytes);
                                        String body = new String(bytes, "utf-8");
                                        System.out.println("the time server receive order : " + body);
                                        String currentTime = "query time order".equals(body) ? new Date(System.currentTimeMillis()).toString() : "bad order";
                                        byte[] bytes2 = currentTime.getBytes();
                                        ByteBuffer writeBuffer = ByteBuffer.wrap(bytes2);
                                        sc.write(writeBuffer);
                                    }else if (readbytes < 0){
                                        // 读到-1,关闭链路
                                        key.cancel();
                                        sc.close();
                                    }else{
                                    }
                                }
                            }
                        }catch (Exception e){
                            if (key != null){
                                key.cancel();
                                if (key.channel() != null){
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) throws IOException {

        new Thread(new ReactorTask()).start();










//        SelectableChannel
//        Executor
//        PriorityBlockingQueue
//        PriorityQueue
//        DelayQueue
//        FutureTask<Integer> task = new FutureTask<>(()->{
//           Thread.sleep(5000);
//           return 1;
//        });
//        new Thread(task).start();
//        new Thread(() -> {
//            try {
//                System.out.println(task.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                System.out.println(task.get(1000, TimeUnit.MILLISECONDS));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            task.cancel(true);
//        }).start();
//        try {
//            Thread.sleep(1000);
//            task.cancel(true);
//            System.out.println(task.get(2000, TimeUnit.MILLISECONDS));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        Future
//        ArrayBlockingQueue、。。，
//        LinkedBlockingQueue
//        BlockingQueue
//        AtomicReferenceFieldUpdater
//        ReentrantLock
//        Thread.interrupted()
//        AbstractQueuedSynchronizer sync =
//        Lock lock = new MyMutext();
//        CountDownLatch latch = new CountDownLatch(2);
//        Runnable simpletask = () -> {
//            latch.countDown();
//            lock.lock();
//            try{
//                for (int i = 0; i < 1000; i++){
//                    System.out.println(Thread.currentThread().getName() + "       " + i);
//                }
//            }finally {
//                lock.unlock();
//            }
//
//        };
//        Thread thread1 = new Thread(simpletask);
//        thread1.setName("thread1");
//        Thread thread2 = new Thread(simpletask);
//        thread2.setName("thread2");
//        thread1.start();
//        thread2.start();
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
