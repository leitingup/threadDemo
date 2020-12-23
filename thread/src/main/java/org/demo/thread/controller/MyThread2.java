package org.demo.thread.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread2 implements Callable{
    @Override
    public Object call() throws Exception {
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"     ****come in call");
        return 200;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());

        //适用场景：运算较复杂的。先起个线程运行，等需要的时候get();

        /**
         * 原理: 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，
        可以把这些作业交给 Future 对象在后台完成， 当主线程将来需要时，
        就可以通过 Future 对象获得后台作业的计算结果或者执行状态。

        一般 FutureTask 多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
        仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。
        一旦计算完成， 就不能再重新开始或取消计算。get 方法而获取结果只有在计算完成时获取，
        否则会一直阻塞直到任务转入完成状 态，然后会返回结果或者抛出异常。
         只计算一次 get 方法放到最后*/


    }
}
