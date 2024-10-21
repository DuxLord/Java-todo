package net.gacame.MULTIHILO;

class Hilo extends Thread {
    public Hilo() {
        // Hereda la prioridad del hilo padre
    }

    public Hilo(int prioridad) {
        // Establece la prioridad indicada
        this.setPriority(prioridad);
    }

    @Override
    public void run() {
        StringBuilder strCadena = new StringBuilder();
        // Agrega 20000 caracteres a la cadena
        for (int i = 0; i < 20000; ++i) {
            strCadena.append("A");
            // yield() sugiere al planificador Java seleccionar otro hilo
            Thread.yield();
        }
        System.out.println("Hilo de prioridad " + this.getPriority() + " termina ahora");
    }
}

public class Programa {
    public static void main(String[] args) {
        int contador = 5;
        // Arreglos para hilos de distintas prioridades
        Thread[] hiloMIN = new Thread[contador];
        Thread[] hiloNORM = new Thread[contador];
        Thread[] hiloMAX = new Thread[contador];

        // Crear hilos de prioridad mínima
        for (int i = 0; i < contador; i++) {
            hiloMIN[i] = new Hilo(Thread.MIN_PRIORITY);
        }

        // Crear hilos de prioridad normal
        for (int i = 0; i < contador; i++) {
            hiloNORM[i] = new Hilo(); // Prioridad normal por defecto
        }

        // Crear hilos de máxima prioridad
        for (int i = 0; i < contador; i++) {
            hiloMAX[i] = new Hilo(Thread.MAX_PRIORITY);
        }

        System.out.println("Los hilos de mayor prioridad deberían terminar antes...\n");

        // Iniciar hilos
        for (int i = 0; i < contador; i++) {
            hiloMIN[i].start();
            hiloNORM[i].start();
            hiloMAX[i].start();
        }
    }
}
