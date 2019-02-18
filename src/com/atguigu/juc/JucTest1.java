package com.atguigu.juc;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JucTest1 {
    public static void main(String[] args) {
        Sale sale = new Sale();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=40; i++) {
                    sale.saleP();
                }

            }
        },"1").start();*/
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=40; i++) {
                    sale.saleP();
                }

            }
        },"2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=40; i++) {
                    sale.saleP();
                }

            }
        },"3").start();*/
        //lambda表达式，拷贝小括号，写死右箭头，保留大括号

        new Thread(()->{for (int i=0; i<=40; i++) sale.saleP();},"1").start();
        new Thread(()->{for (int i=0; i<=40; i++) sale.saleP();},"2").start();
        new Thread(()->{for (int i=0; i<=40; i++) sale.saleP();},"3").start();
    }
}

class Sale{
    Lock lock = new ReentrantLock();
    private int num = 30;
    public void saleP(){
        lock.lock();
        try{
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"------>"+num--+"还剩"+num);
            }
        }finally {
            lock.unlock();
        }
    }
}
