package com.atguigu.juc;

import com.atguigu.LambdaExpressTest.LambdaDemo;

public class JucTest {

    public static void main(String[] args) {
        //3个售票员卖30张票
        SaleP saleP = new SaleP();
        Thread thread1 = new Thread(saleP);
        Thread thread2 = new Thread(saleP);
        Thread thread3 = new Thread(saleP);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
class SaleP implements Runnable{
    //规定票数
    // private static final int MAX_VALUE = 30;
    private int tickers = 30;
    @Override
    public void run(){
        while(true) {
            synchronized(this){
                if(tickers>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(Thread.currentThread().getName() + "---" + tickers--);
                    } catch (Exception e) {
                                e.printStackTrace();
                    }

                }else{
                    break;
                }
            }
        }
    }

}


