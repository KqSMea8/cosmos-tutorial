package com.cosmos.base.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: CyclicBarrierDemo
 * @Author: keda
 * @Description: *
 * 同步屏障:
 * 类似于闭锁。与闭锁不同之处在于，所有的线程必须同时到达关卡点，才能继续处理。
 * * 闭锁等待的是事件；而同步屏障等待的是其他的线程。
 * * 比如：可将一个任务分割成多个子部分，然后再整合
 * @Date: 2019/6/25 14:01
 * @Version: 1.0
 */
public class CyclicBarrierDemo {

    public static void tast(int nThread) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(nThread);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i = 0; i < nThread - 1; i++) {
            new Thread(() -> {
                long beginTime = System.currentTimeMillis();
                try {
                    System.out.println("开始执行任务！");

                    Thread.sleep(1000 * atomicInteger.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + "任务执行完成！开始等待其他任务完成" + (System.currentTimeMillis() - beginTime));
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                }

            }).start();
        }
        cyclicBarrier.await();
        System.out.println("所有任务均已完成才可到达此处");
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrierDemo.tast(10);
    }
}