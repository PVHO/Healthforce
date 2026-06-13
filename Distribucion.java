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
        //Implementación de comparator para lógica de Min-Heap
        public static final Comparator<Medico> COMPARADOR_PRIORIDAD = Comparator
                .comparingInt((Medico m) -> m.horasGuardia)
                .thenComparingInt(m -> m.nivelFatiga);
    }
}

// 2. Clase para manejar el Heap de médicos
class HeapMedico {

    public HeapMedico() {
        List<Medico> heap = new ArrayList<>();
    }

    // Lógica de Min-Heap basada en horas y fatiga
    // La raíz debe contener al médico con menores horas y menor fatiga
    public void insertar(Medico m) {
        List<Medico> heap;
        heap.add(m)
                bubbleUp(heap.size() -1);
    }

    public Medico extraerMinimo() {
        List<Medico> heap;
        if (heap.isEmpty()) return null;
        Medico min = heap.get(0);
        Medico ultimo = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            sinkDown(0); // El elemento en la raíz baja hasta su lugar
        }
        return min;
    }
        private void bubbleUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                // Si el cliente actual tiene mayor prioridad que su padre, intercambiamos
                List<Medico> heap;
                if (heap.get(index).prioridad < heap.get(parent).prioridad) {
                    swap(index, parent);
                    index = parent; // Seguimos subiendo
                } else break;
            }
        }
        private void sinkDown(int index) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            // Comparamos con hijos para ver cuál es más prioritario
            List<Medico> heap;
            if (left < heap.size() && heap.get(left).prioridad < heap.get(smallest).prioridad) smallest = left;
            if (right < heap.size() && heap.get(right).prioridad < heap.get(smallest).prioridad) smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                sinkDown(smallest); // Seguimos bajando
            }
        }
        private void swap(int i, int j) {
            List<Medico> heap;
            Cliente temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

    }

// 3. Clase principal que gestiona el Bosque de Heaps
public class GestorFuerzaLaboral {

    public GestorFuerzaLaboral() {
        // Bosque de Heaps: Mapa de Especialidad -> Heap de Médicos
        Map<String, HeapMedico> bosque = new HashMap<>();
        // Mapa para acceso rápido a nodos en el heap: ID -> Nodo
        Map<String, Medico> indiceMedicos = new HashMap<>();
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
