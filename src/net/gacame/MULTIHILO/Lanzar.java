package net.gacame.MULTIHILO;


public class Lanzar  {

    public static void main(String[] args) {
        HiloContador t = new HiloContador();
        t.run();
}
}

class HiloContador implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Counting " + i);
        }
    }
}


