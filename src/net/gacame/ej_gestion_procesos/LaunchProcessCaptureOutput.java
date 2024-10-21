package net.gacame.ej_gestion_procesos;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LaunchProcessCaptureOutput {
    public static void main (String[] args) {
        if (args.length <= 0) {
            System.out.println("Type the correct command...");
            System.exit(1);
        }
        ProcessBuilder pb = new ProcessBuilder(args);
        try {
            Process p = pb.start();
            try (InputStream is = p.getInputStream();
                 InputStream es = p.getErrorStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 InputStreamReader esr = new InputStreamReader(es);
                 BufferedReader br = new BufferedReader(isr);
                 BufferedReader ebr = new BufferedReader(esr)) {

                int codRet = p.waitFor();
                System.out.println("Execution of " + Arrays.toString(args)
                        + " returns " + codRet
                        + " " + (codRet == 0 ? "(execution OK)" : "(ERROR!!!)")
                );
                System.out.println("Output of process:");
                System.out.println("------------------");
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                System.out.println("------------------");

                System.out.println("Error output of process (if any):");
                System.out.println("------------------");
                String errLine = null;
                while ((errLine = ebr.readLine()) != null) {
                    System.out.println(errLine);
                }
                System.out.println("------------------");

            }
        } catch (IOException e) {
            System.err.println("Error during process execution.");
            e.printStackTrace();
            System.exit(2);
        } catch (InterruptedException ex) {
            System.err.println("Process interrupted...");
            System.exit(3);
        }
    }
}
