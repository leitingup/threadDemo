package org.demo.thread.controller;

public class ShareDataOne {

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1判断
        while(number!=0){
            this.wait();
        }
        //2干活
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {

        //1判断
        while(number==0){
            this.wait();
        }
        //2干活
        --number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3通知
        this.notifyAll();
    }
}
