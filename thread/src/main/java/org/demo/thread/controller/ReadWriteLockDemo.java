package org.demo.thread.controller;
/**
*@Description 一个线程写入,100 个线程读取
*/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();

        new Thread(()->{
            q.writeObj("ClassName1221");
        },"A").start();

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                q.readObj();
            },String.valueOf(i)).start();
        }
    }
}
