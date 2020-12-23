package org.demo.thread.controller;

public class Phone {

    public synchronized void sendSMS()throws Exception{
        System.out.println("-------sendSMS");
    }

    public synchronized void sendEmail()throws Exception{
        System.out.println("-------sendEmail");
    }
    public void getHello(){
        System.out.println("-----getHello");
    }
}
