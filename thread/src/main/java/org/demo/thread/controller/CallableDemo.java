package org.demo.thread.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
*@Description Callable 接口获得多线程
*/
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<Integer>(new MyThread2());
        new Thread(futureTask,"A").start();
        System.out.println(Thread.currentThread().getName()+"------main");
        Integer result = (Integer) futureTask.get();

        System.out.println("result:"+result);
    }
}
