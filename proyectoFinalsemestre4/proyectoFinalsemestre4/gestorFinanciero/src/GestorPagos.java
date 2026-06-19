import java.util.LinkedList;
import java.util.Queue;


public class GestorPagos {

    private Queue<Paciente> colaPagos;

    public GestorPagos() {
        colaPagos = new LinkedList<>();
    }

    // Agregar paciente a la cola
    public void ingresarPaciente(Paciente paciente) {
        colaPagos.offer(paciente);
        System.out.println(paciente.getNombre() + " agregado a la cola.");
    }

    // Atender al siguiente paciente
    public void atenderPaciente() {
        if (colaPagos.isEmpty()) {
            System.out.println("No hay pacientes en espera.");
            return;
        }

        Paciente atendido = colaPagos.poll();
        System.out.println("Atendiendo a: " + atendido.getNombre());
    }

    // Mostrar cola
    public void mostrarCola() {
        if (colaPagos.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }

        System.out.println("=== PACIENTES EN ESPERA ===");
        for (Paciente p : colaPagos) {
            System.out.println(
                    "ID: " + p.getId() +
                            " | Nombre: " + p.getNombre() +
                            " | Valor a pagar: $" + p.getMonto()
            );
        }
    }
}
