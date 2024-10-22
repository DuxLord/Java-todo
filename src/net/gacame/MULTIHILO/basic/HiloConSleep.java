package net.gacame.MULTIHILO.basic;

public class HiloConSleep extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Hilo dormido por 2 segundos");
            Thread.sleep(2000);  // Duerme el hilo por 2 segundos
            System.out.println("Hilo despierto");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HiloConSleep hilo = new HiloConSleep();
        hilo.start();
    }
}
