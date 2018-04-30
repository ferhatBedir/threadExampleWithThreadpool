package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Object key1 = new Object();
        Object key2 = new Object();
        Object key3 = new Object();
        Object key4 = new Object();


        // method sonundaki 2 aynı anda sadece 2 thread birlikte calışacağını gösteriyor.
        // bu şekil thread kullanımı pc kaynak tuketimi açısından da onemli ve eğer uygulamada
        // çok fazla thread varsa bunların kontrolu zor ve karmaşık hale gelecektir.
        // bu sebeplerden kullanılmaktadır.
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (key1) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("1.thread calısiyor thread pool clısıyor.");
                    }
                    System.out.println("1. thread bitirdi.");
                }
            }
        });

        executorService.submit(thread);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (key2) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("2.thread calısiyor thread pool clısıyor.");
                    }
                    System.out.println("2. thread bitirdi.");
                }
            }
        });

        executorService.submit(thread1);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (key3) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("3.thread calısiyorthread pool clısıyor.");
                    }
                    System.out.println("3. thread bitirdi.");
                }
            }
        });

        executorService.submit(thread2);


        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (key4) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("4.thread calısiyorthread pool clısıyor.");
                    }
                    System.out.println("4. thread bitirdi.");
                }
            }
        });

        executorService.submit(thread3);


        //Threadleri sonlandırmak için kullanık.
        //eğer bunu kullanamzsak uygulama sonsuza kadar çalışır.
        executorService.shutdown();


        //Bu method parametre olarak aldığı 1 rakamı;
        //eğer uygulama en geç 1 gün içerisinde kapanmaz ise bu method uygulamayı kapatıyor.
        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}