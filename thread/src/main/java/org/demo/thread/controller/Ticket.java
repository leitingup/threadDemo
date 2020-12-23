package org.demo.thread.controller;

import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Ticket {

    private int number = 30;
//    public synchronized void sale()
    /*synchronized(this){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出"+(number--)+"张，剩余"+number+"张");
        }
    }*/

    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出"+(number--)+"张，剩余"+number+"张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
     /*   new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                ticket.sale();
            }
        },"C").start();*/



    }
}
