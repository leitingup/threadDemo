package org.demo.thread.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareDateTwo {
    private int number = 1;//1:A 2:B 3:C
     private Lock lock = new ReentrantLock();
     private Condition c1 = lock.newCondition();
     private Condition c2 = lock.newCondition();
     private Condition c3 = lock.newCondition();

     public void print5(int totalLoopNumber) throws InterruptedException {
         lock.lock();
         try {
             while (number!=1){
                 c1.await();
             }
             for (int i = 0; i <5 ; i++) {
                 System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
             }
             number=2;
             c2.signal();
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
     }
     public void print10(int totalLoopNumber) throws InterruptedException {
         lock.lock();
         try {
             while (number!=2){
                 c1.await();
             }
             for (int i = 0; i <10 ; i++) {
                 System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
             }
             number=3;
             c3.signal();
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
     }
     public void print15(int totalLoopNumber) throws InterruptedException {
         lock.lock();
         try {
             while (number!=3){
                 c3.await();
             }
             for (int i = 0; i <15 ; i++) {
                 System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
             }
             number=1;
             c1.signal();
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
     }
}
