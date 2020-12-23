package org.demo.thread.controller;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyQueue {

    private Object obj;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void readObj(){
        rwLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeObj(Object obj){
        rwLock.writeLock().lock();

        try {
            this.obj = obj;
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
