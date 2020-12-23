package org.demo.thread.controller;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
*@Description
 * 现在两个线程，
 *可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，
 * 一个线程对该变量减 1，
 * 交替，来 10 轮。
*/
public class NotifyWaitDemoOne {
    /*换成 4 个线程会导致错误，虚假唤醒
    原因：在 java 多线程判断时，不能用 if，程序出事出在了判断上面，
     突然有一天加的线程进到 if 了，突然中断了交出控制权，
      没有进行验证，而是直接走下去了，加了两次，甚至多次*/

    public static void main(String[] args) {
        ShareDataOne shareDataOne = new ShareDataOne();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

    class BoundedBuffer{
        final Lock lock =new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

        final Object[]items = new Object[100];
        int putptr,taktptr,count;

        public void put(Object x) throws InterruptedException {
            lock.lock();
            while (count==items.length){
                try {
                    notFull.await();
                    items[putptr]=x;
                    if(++putptr==items.length)putptr=0;
                    ++count;
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
