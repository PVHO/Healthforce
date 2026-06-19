import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// Inicialización de módulos
        GestorPacientes gestorPacientes = new GestorPacientes();
        InventarioHospital inventario = new InventarioHospital();
        GestorFuerzaLaboral gestorLaboral = new GestorFuerzaLaboral();
        GestorPagos gestorPagos = new GestorPagos();
        boolean salir = false;
        System.out.println("=== Bienvenido a Health Force System ===");

        while (!salir) {
            System.out.println("\nSeleccione un módulo:");
            System.out.println("1. Gestión de Pacientes");
            System.out.println("2. Inventario de Quirófano");
            System.out.println("3. Distribución de Fuerza Laboral");
            System.out.println("4. Gestor Económico de Pagos");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
// Lógica para interactuar con GestorPacientes
                    System.out.println("Módulo de Pacientes activo.");
                    break;
                case 2:
// Lógica para interactuar con InventarioHospital
                    System.out.println("Módulo de Inventario activo.");

                    break;
                case 3:
// Lógica para interactuar con GestorFuerzaLaboral

                    System.out.println("Módulo de Fuerza Laboral activo.");

                    break;
                case 4:
// Lógica para interactuar con GestorPagos
                    System.out.println("Módulo de Pagos activo.");
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");

            }
        }
        System.out.println("Sistema cerrado correctamente.");
        scanner.close();
    }
}