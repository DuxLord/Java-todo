package net.gacame.MULTIHILO;

public class HiloFinalizable extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hilo en ejecución...");
        }
    }

    public void stopThread() {
        running = false;
    }

    public static void main(String[] args) {
        HiloFinalizable hilo = new HiloFinalizable();
        hilo.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hilo.stopThread();  // Detiene el hilo
    }
}
