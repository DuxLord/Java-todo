package net.gacame.ej_gestion_procesos;

public class ProcessLauncher {
    public void execute(String path) {
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(path);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Windows\\notepad.exe";
        ProcessLauncher lp = new ProcessLauncher();
        lp.execute(path);
        System.out.println("Terminated.");

    }
}
