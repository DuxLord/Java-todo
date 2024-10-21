package net.gacame.ej01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibretaContactos {
    private static Map<String, Contacto> contactos = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean validar = true;
        int numero = 0;

        do {
            System.out.println("LIBRETA DE CONTACTOS");
            System.out.println("1. Consultar Contacto");
            System.out.println("2. Crear Contacto");
            System.out.println("3. Modificar Contacto");
            System.out.println("4. Salir");
            System.out.println("Introduzca un número válido (1-4)");

            try {
                numero = Integer.parseInt(sc.nextLine());
                if (numero >= 1 && numero <= 4) {
                    System.out.println("Ha seleccionado la opción: " + numero);
                    if (numero == 4) {
                        validar = false; // Salir del bucle si elige la opción 4
                    }
                } else {
                    System.err.println("Número inválido");
                }

            } catch (NumberFormatException e) {
                System.err.println("Por favor, introduzca un número válido.");
            }

            switch (numero) {
                case 1:
                    consultarContactos(contactos);
                    break;
                case 2:
                    crearContacto();
                    break;
                case 3:
                    modificarContacto();
                    break;
                case 4:
                    salir();
                    break;

            }
        } while (validar);

        sc.close();
    }

    private static void consultarContactos(Map<String, Contacto> contactos) {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la libreta.");
        } else {
            for (Map.Entry<String, Contacto> entry : contactos.entrySet()) {
                Contacto temp = entry.getValue();
                System.out.println("ID: " + temp.getId() + ", Nombre: " + temp.getNombre() + ", Teléfono: " + temp.getTelefono());
            }
        }
    }

    private static void crearContacto() {
        System.out.println("Introduce el nombre del contacto:");
        String nombre = sc.nextLine();
        System.out.println("Introduce el teléfono del contacto:");
        String telefono = sc.nextLine();

        Contacto nuevoContacto = new Contacto(nombre, telefono);
        contactos.put(nombre, nuevoContacto);

        System.out.println("Contacto añadido: " + nombre);
    }

    private static void modificarContacto() {
        System.out.println("Introduce el nombre del contacto que deseas modificar:");
        String nombre = sc.nextLine();

        if (contactos.containsKey(nombre)) {
            System.out.println("Introduce el nuevo teléfono:");
            String nuevoTelefono = sc.nextLine();

            Contacto contacto = contactos.get(nombre);
            contacto.setTelefono(nuevoTelefono);

            System.out.println("Contacto actualizado.");
        } else {
            System.out.println("El contacto no existe.");
        }
    }

    private static void salir() {
        System.out.println("Saliendo...");
        System.exit(0);
    }

}

class Contacto {
    private static long contadorId = 0;
    private final long id;
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.id = ++contadorId;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }
}
