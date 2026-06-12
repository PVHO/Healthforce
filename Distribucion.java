import java.util.*;

// 1. Clase que representa al médico y su estado
class Medico {
    String id;
    String especialidad;
    int horasGuardia; // Horas acumuladas
    int nivelFatiga;  // Tiempo desde última cirugía
    boolean disponible;

    public Medico(String id, String especialidad, int horasGuardia, int nivelFatiga) {
        this.id = id;
        this.especialidad = especialidad;
        this.horasGuardia = horasGuardia;
        this.nivelFatiga = nivelFatiga;
        this.disponible = true;
    }
}

// 2. Clase para manejar el Heap de médicos
class HeapMedico {
    private List<Medico> heap;

    public HeapMedico() {
        this.heap = new ArrayList<>();
    }

    // Lógica de Min-Heap basada en horas y fatiga
    // La raíz debe contener al médico con menores horas y menor fatiga
    public void insertar(Medico m) { /* Lógica de inserción */ }
    public Medico extraerMinimo() { /* Lógica de extracción de raíz */ }
}

// 3. Clase principal que gestiona el Bosque de Heaps
public class GestorFuerzaLaboral {
    // Bosque de Heaps: Mapa de Especialidad -> Heap de Médicos
    private Map<String, HeapMedico> bosque;
    // Mapa para acceso rápido a nodos en el heap: ID -> Nodo
    private Map<String, Medico> indiceMedicos;

    public GestorFuerzaLaboral() {
        bosque = new HashMap<>();
        indiceMedicos = new HashMap<>();
    }

    public void asignarCirugia(String especialidad) {
        // 1. Localizar el heap de la especialidad
        // 2. Extraer médico con menor carga (raíz)
        // 3. Marcar como ocupado/actualizar fatiga
    }

    public void actualizarEstadoMedico(String id, int nuevasHoras) {
        // 1. Acceso en O(1) vía HashMap
        // 2. Actualizar datos
        // 3. Reestructurar heap en O(log n)
    }
}
