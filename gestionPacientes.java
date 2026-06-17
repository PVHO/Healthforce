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


//Clase para manejar el Heap para las Urgencias
public class HeapUrgencias {

    private ArrayList<Paciente> heap;

    public HeapUrgencias() {
        heap = new ArrayList<>();
    }

    public boolean estaVacio() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void insertar(Paciente paciente) {

        heap.add(paciente);

        heapifyUp(heap.size() - 1);
    }


    public Paciente extraerMaximo() {

        if (heap.isEmpty()) {
            return null;
        }

        Paciente raiz = heap.get(0);

        Paciente ultimo =
                heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {

            heap.set(0, ultimo);

            heapifyDown(0);
        }

        return raiz;
    }

    private void heapifyUp(int indice) {

        while (indice > 0) {

            int padre = (indice - 1) / 2;

            if (heap.get(indice).getGravedad()
                    > heap.get(padre).getGravedad()) {

                intercambiar(indice, padre);

                indice = padre;

            } else {
                break;
            }
        }
    }

    private void heapifyDown(int indice) {

        int mayor = indice;

        int izquierda = 2 * indice + 1;
        int derecha = 2 * indice + 2;

        if (izquierda < heap.size()
                && heap.get(izquierda).getGravedad()
                > heap.get(mayor).getGravedad()) {

            mayor = izquierda;
        }

        if (derecha < heap.size()
                && heap.get(derecha).getGravedad()
                > heap.get(mayor).getGravedad()) {

            mayor = derecha;
        }

        if (mayor != indice) {

            intercambiar(indice, mayor);

            heapifyDown(mayor);
        }
    }

    private void intercambiar(int i, int j) {

        Paciente temporal = heap.get(i);

        heap.set(i, heap.get(j));

        heap.set(j, temporal);
    }

    public void mostrarUrgencias() {

        if (heap.isEmpty()) {

            System.out.println(
                    "No existen urgencias pendientes."
            );

            return;
        }

        for (Paciente paciente : heap) {

            System.out.println(paciente);
        }
    }
}

//Clase que representa al paciente subdividido en paciente de urgencia o con cita
public class Paciente {

    private String id;
    private String nombre;
    private int edad;
    private String motivo;

    // Programado o urgencia
    private String tipo;

    // urgencias 1-10 / programado 0
    private int gravedad;

    public Paciente(String id,
                    String nombre,
                    int edad,
                    String motivo,
                    String tipo,
                    int gravedad) {

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.motivo = motivo;
        this.tipo = tipo;
        this.gravedad = gravedad;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getGravedad() {
        return gravedad;
    }

    @Override
    public String toString() {

        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Gravedad: " + gravedad;
    }
}