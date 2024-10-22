package net.gacame.MULTIHILO.basic;

public class HiloConYield extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            Thread.yield();  // Cede el procesador
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new HiloConYield();
        Thread hilo2 = new HiloConYield();
        hilo1.start();
        hilo2.start();
    }
}
