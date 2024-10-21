package net.gacame.MULTIHILO;

public class MiRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hilo con Runnable");
    }

    public static void main(String[] args) {
        Thread hilo = new Thread(new MiRunnable());
        hilo.start();
    }
}
