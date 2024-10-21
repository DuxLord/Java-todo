package net.gacame.MULTIHILO;

public class MultiplierThreadsJoin extends Thread {
    public static void main(String[] args) {

        Thread[] threads = new Thread[1000000];
        for (int i = 0; i < 1000000; i++) {
            threads[i] = new MultiplierThreadsJoin();
            threads[i].start();

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


//        Thread hiloA = new MultiplierThreadsJoin();
//        Thread hiloB = new MultiplierThreadsJoin();
//        Thread hiloC = new MultiplierThreadsJoin();
//
//
//        try {
//            hiloA.start();
//            hiloA.join();
//
//            hiloB.start();
//            hiloB.join();
//
//            hiloC.start();
//            hiloC.join();
//
//        } catch (InterruptedException e) {
//            System.err.println("A thread was interrupted: " + e.getMessage());
//        }
//
//        System.out.println("All threads have finished execution.");
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
        }
    }
}

