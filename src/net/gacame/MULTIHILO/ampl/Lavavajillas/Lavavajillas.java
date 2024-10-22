package net.gacame.MULTIHILO.ampl.Lavavajillas;

import java.util.ArrayDeque;
import java.util.Deque;

public class Lavavajillas {
    public static void main(String[] args) {
        PilaPlatos pilaPlatos = new PilaPlatos();
        Thread friega = new Thread(new Friega(20, pilaPlatos));
        Thread seca = new Thread(new Seca(20, pilaPlatos));

        friega.start();
        seca.start();

        try {
            friega.join();
            seca.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Plato {
    private int id;

    public Plato(int id) {
        this.id = id;
    }

    public int getNum() {
        return id;
    }

    @Override
    public String toString() {
        return "Plato #" + id;
    }
}

class PilaPlatos {
    private final int MAX_PLATOS = 5;
    private final Deque<Plato> pila;

    public PilaPlatos() {
        pila = new ArrayDeque<>();
    }

    public synchronized void lavar(Plato plato) {
        while (pila.size() >= MAX_PLATOS) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        pila.push(plato);
        System.out.println("Plato lavado #" + plato.getNum() + ", total en pila: " + pila.size());
        notifyAll();
    }

    public synchronized void secar() {
        while (pila.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Plato platoSecado = pila.pop();
        System.out.println("Plato secado #" + platoSecado.getNum() + ", total en pila: " + pila.size());
        notifyAll();
    }
}

class Friega implements Runnable {
    private final int numPlatos;
    private final PilaPlatos pilaPlatos;

    public Friega(int numPlatos, PilaPlatos pilaPlatos) {
        this.numPlatos = numPlatos;
        this.pilaPlatos = pilaPlatos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numPlatos; i++) {
            pilaPlatos.lavar(new Plato(i));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Seca implements Runnable {
    private final int numPlatos;
    private final PilaPlatos pilaPlatos;

    public Seca(int numPlatos, PilaPlatos pilaPlatos) {
        this.numPlatos = numPlatos;
        this.pilaPlatos = pilaPlatos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numPlatos; i++) {
            pilaPlatos.secar();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
