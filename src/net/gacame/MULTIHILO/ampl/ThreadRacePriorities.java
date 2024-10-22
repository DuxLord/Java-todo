package net.gacame.MULTIHILO.ampl;

public class ThreadRacePriorities extends Thread {

    public static void main(String[] args) {
        Thread hiloA = new ThreadRacePriorities();
        Thread hiloB = new ThreadRacePriorities();
        Thread hiloC = new ThreadRacePriorities();

        hiloA.setName("A");
        hiloB.setName("B");
        hiloC.setName("C");

        hiloA.setPriority(Thread.MAX_PRIORITY);
        hiloB.setPriority(Thread.NORM_PRIORITY);
        hiloC.setPriority(Thread.MIN_PRIORITY);

        hiloA.start();
        hiloB.start();
        hiloC.start();

        System.out.println("All threads have finished execution.");
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is at iteration " + i);
        }
    }
}
