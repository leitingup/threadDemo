package org.demo.thread.controller;

/*** @Description:  多线程之间按顺序调用，实现 A->B->C
 * 三个线程启动，要求如下：
 *  AA 打印 5 次，BB 打印 10 次，CC 打印 15 次
 *  接着 * AA 打印 5 次，BB 打印 10 次，CC 打印 15 次
 *  ......来 10 轮
 * **/
public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareDateTwo sr = new ShareDateTwo();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    sr.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    sr.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    sr.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
