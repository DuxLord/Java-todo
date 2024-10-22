package net.gacame.MULTIHILO.basic;

public class HiloInterrumpido extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Hilo en ejecución...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Marca el hilo como interrumpido
            }
        }
        System.out.println("Hilo interrumpido");
    }

    public static void main(String[] args) {
        HiloInterrumpido hilo = new HiloInterrumpido();
        hilo.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hilo.interrupt();  // Interrumpe el hilo
    }
}
