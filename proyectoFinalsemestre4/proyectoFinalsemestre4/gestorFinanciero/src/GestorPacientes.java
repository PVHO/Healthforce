import java.util.*;

//Clase para organizar a los pacientes
public class GestorPacientes {

    // FIFO
    private Queue<Paciente> pacientesProgramados;

    // Max Heap
    private HeapUrgencias pacientesUrgencia;

    // Búsqueda
    private HashMap<String, Paciente> indicePacientes;

    // Historial
    private Stack<Paciente> historialAtendidos;

    public GestorPacientes() {

        pacientesProgramados =
                new LinkedList<>();

        pacientesUrgencia =
                new HeapUrgencias();

        indicePacientes =
                new HashMap<>();

        historialAtendidos =
                new Stack<>();
    }

    // Registrar paciente
    public void registrarPaciente(
            Paciente paciente) {

        if (paciente.getTipo()
                .equalsIgnoreCase("URGENCIA")) {

            pacientesUrgencia.insertar(
                    paciente
            );

        } else {

            pacientesProgramados.offer(
                    paciente
            );
        }

        indicePacientes.put(
                paciente.getId(),
                paciente
        );
    }

    // Buscar
    public Paciente buscarPaciente(
            String id) {

        return indicePacientes.get(id);
    }

    // Atiende primero urgencias
    public Paciente atenderSiguiente() {

        Paciente atendido;

        if (!pacientesUrgencia.estaVacio()) {

            atendido =
                    pacientesUrgencia
                            .extraerMaximo();

        } else {

            atendido =
                    pacientesProgramados.poll();
        }

        if (atendido != null) {

            historialAtendidos.push(
                    atendido
            );

            indicePacientes.remove(
                    atendido.getId()
            );
        }

        return atendido;
    }

    public void mostrarPacientesPendientes() {

        System.out.println(
                "\n===== URGENCIAS ====="
        );

        pacientesUrgencia
                .mostrarUrgencias();

        System.out.println(
                "\n===== PROGRAMADOS ====="
        );

        if (pacientesProgramados.isEmpty()) {

            System.out.println(
                    "No existen pacientes programados."
            );

            return;
        }

        for (Paciente paciente :
                pacientesProgramados) {

            System.out.println(
                    paciente
            );
        }
    }

    public void mostrarHistorial() {

        System.out.println(
                "\n===== HISTORIAL ====="
        );

        if (historialAtendidos.isEmpty()) {

            System.out.println(
                    "No existen pacientes atendidos."
            );

            return;
        }

        for (Paciente paciente :
                historialAtendidos) {

            System.out.println(
                    paciente
            );
        }
    }

    public int totalUrgencias() {

        return pacientesUrgencia.size();
    }

    public int totalProgramados() {

        return pacientesProgramados.size();
    }

    public int totalAtendidos() {

        return historialAtendidos.size();
    }
}


