package com.example.securitytest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void placeOrder() {
        boolean issuccess = orderService.placeOrder(1L, 10001L, "haixiao");
        System.out.println(issuccess);

    }

    @Test
    public void testBatch() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(30);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 30; i++){
            service.submit(new PlaceOrderRunnable(barrier, i));
        }
        Thread.sleep(5000);
    }

    class PlaceOrderRunnable implements Runnable{
        CyclicBarrier barrier;
        final int id;
        public PlaceOrderRunnable(CyclicBarrier barrier, int id){
            this.barrier = barrier;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                boolean isSuccess = orderService.placeOrder(1L, 10001L, "haixiao" + id);
                System.out.println("haixiao" + id + " : " + isSuccess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
