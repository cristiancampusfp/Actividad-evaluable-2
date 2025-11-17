package Menu;


import config.DatabaseConfig;
import dao.LibroDAO;
import modelo.Libro;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final LibroDAO libroDAO = new LibroDAO();
    private final Scanner scanner = new Scanner(System.in);

    private int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, intente de nuevo.");
            }
        }
    }

    public void iniciar() {
            System.out.println("=== BIENVENIDO AL SISTEMA  ===");
            boolean salir = false;


            while (!salir) {
                System.out.println("\n--- MENÚ PRINCIPAL ---");
                System.out.println("1. Gestionar Libros");
                System.out.println("0. Salir");


                int opcion = leerEntero("Seleccione opción: ");


                switch (opcion) {
                    case 1 -> menuLibro();
                    case 0 -> salir = true;
                    default -> System.out.println("Opción no válida.");
                }
            }


            // Cerrar pool al finalizar
            DatabaseConfig.cerrarPool();
            System.out.println("=== FIN DEL SISTEMA ===");
        }


        // ------------------- MENÚ LibroS -------------------
        public void menuLibro() {
            boolean volver = false;
            while (!volver) {
                System.out.println("\n--- MENÚ LibroS ---");
                System.out.println("1. Crear Libro");
                System.out.println("2. Listar Libros");
                System.out.println("3. Actualizar Libro");
                System.out.println("4. Eliminar Libro");
                System.out.println("0. Volver");


                int opcion = leerEntero("Seleccione opción: ");


                switch (opcion) {
                    case 1 -> crearLibro();
                    case 2 -> listarLibros();
                    case 3 -> actualizarLibro();
                    case 4 -> eliminarLibro();
                    case 0 -> volver = true;
                    default -> System.out.println("Opción no válida.");
                }
            }
        }


        private void crearLibro() {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("genero_id: ");
            int genero_id = scanner.nextInt();



             Libro libro = new Libro(0, nombre, genero_id);
            int id = LibroDAO.crear(libro);
            if (id != -1) {
                libro.setId(id);
                System.out.println("Libro creado con ID: " + id);
            } else {
                System.out.println("Error al crear libro.");
            }
        }


        private void listarLibros() {
            List<Libro> Libros = LibroDAO.obtenerTodos();
            Libros.forEach(System.out::println);
        }




        private void actualizarLibro() {
            int id = leerEntero("Ingrese ID a actualizar: ");
            Optional<Libro> libroOpt = LibroDAO.obtenerPorId(id);
            if (libroOpt.isPresent()) {
                Libro libro = libroOpt.get();
                System.out.print("Nombre (" + libro.getNombre() + "): ");
                String nombre = scanner.nextLine();


                if (libroDAO.actualizar(libro)) {
                    System.out.println("libro actualizado.");
                } else {
                    System.out.println("Error al actualizar.");
                }
            } else {
                System.out.println("Libro no encontrado.");
            }
        }


        private void eliminarLibro() {
            int id = leerEntero("Ingrese ID a eliminar: ");
            if (libroDAO.eliminar(id)) {
                System.out.println("Libro eliminado.");
            } else {
                System.out.println("Error al eliminar o ID no encontrado.");
            }
        }




    }
