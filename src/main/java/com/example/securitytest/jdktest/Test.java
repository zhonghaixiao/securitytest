package com.example.securitytest.jdktest;

import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.*;

public class Test {



    public static void main(String[] args) throws IOException {
        RandomAccessFile afile = new RandomAccessFile("D://fromfile.txt", "rw");
        FileChannel fromChannel = afile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
        afile.close();
        bFile.close();
        System.out.println("over");


//        PlatformTransactionManager


//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("thread wake up");
//            int i = 0;
//            while (!Thread.interrupted()) {
//                // do more work.
//                System.out.println("current = " + i++);
//            }
//        });
//        thread.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

// 一段时间以后
//        thread.interrupt();
//        ExecutorService service = Executors.newCachedThreadPool();
//        Future<Integer> future = service.submit(simpleCompute);
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    static Callable<Integer> simpleCompute = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < 100000; i++){
                sum += i;
            }
            return sum;
        }
    };

}
