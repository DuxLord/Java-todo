package net.gacame.MULTIHILO;

public class HilosMultiplicadores extends Thread {
    int num;

    public HilosMultiplicadores(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " * " + num + " = " + (num * i));
        }
    }

    public static void main(String[] args) throws InterruptedException {
       HilosMultiplicadores hilosMultiplicadores = new HilosMultiplicadores(10);
       hilosMultiplicadores.start();
       hilosMultiplicadores.join();
       
       HilosMultiplicadores hilosMultiplicadores2 = new HilosMultiplicadores(10);
       hilosMultiplicadores2.start();
    }
}
