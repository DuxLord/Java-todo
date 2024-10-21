package net.gacame.ej_gestion_procesos;

public class IsAliveProgram {


    public static void main(String[] args) throws InterruptedException {

        String path = "C:\\Windows\\notepad.exe";
        Process process;
        try {
            process = Runtime.getRuntime().exec(path);
            while (true) {
            Thread.sleep(3000);

            if (process.isAlive()) {
                System.out.println("Proceso está vivo");
            } else {
                System.out.println("Proceso ha terminado");
                break;  // Salimos del bucle cuando el proceso ha terminado
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}