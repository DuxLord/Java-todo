package net.gacame.MULTIHILO.basic;

public class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("Hilo ejecutándose");
    }

    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();  // Lanza el hilo
    }
}
