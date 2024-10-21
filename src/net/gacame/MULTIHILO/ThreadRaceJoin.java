package net.gacame.MULTIHILO;

public class ThreadRaceJoin extends Thread {
    public static void main(String[] args) {
        Thread hiloA = new ThreadRaceJoin();
        Thread hiloB = new ThreadRaceJoin();
        Thread hiloC = new ThreadRaceJoin();

        Thread[] hilos = {hiloA, hiloB, hiloC};

        hiloA.setName("A");
        hiloB.setName("B");
        hiloC.setName("C");

        for (Thread hilo : hilos) {
            hilo.start();
        }
        try {
            hiloC.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //try {
        //    hiloA.start();
        //    hiloA.join();

        //    hiloB.start();
        //    hiloB.join();

        //    hiloC.start();
        //    hiloC.join();

        //} catch (InterruptedException e) {
        //    System.err.println("A thread was interrupted: " + e.getMessage());
        //}

        System.out.println("All threads have finished execution.");
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
    }
}
