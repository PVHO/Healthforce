import java.util.*;

// 1. Clase que representa al médico
class Medico {
    String id;
    String especialidad;
    int horasGuardia;
    int nivelFatiga;
    boolean disponible;

    public Medico(String id, String especialidad, int horasGuardia, int nivelFatiga) {
        this.id = id;
        this.especialidad = especialidad;
        this.horasGuardia = horasGuardia;
        this.nivelFatiga = nivelFatiga;
        this.disponible = true;
    }

    // Método para comparar basado en los criterios de carga y fatiga
    public int compareTo(Medico otro) {
        if (this.horasGuardia != otro.horasGuardia) {
            return Integer.compare(this.horasGuardia, otro.horasGuardia);
        }
        return Integer.compare(this.nivelFatiga, otro.nivelFatiga);
    }
}

// 2. Clase para manejar el Heap de médicos
class HeapMedico {
    private List<Medico> heap = new ArrayList<>();

    public void insertar(Medico m) {
        heap.add(m);
        bubbleUp(heap.size() - 1);
    }

    public Medico extraerMinimo() {
        if (heap.isEmpty()) return null;
        Medico min = heap.get(0);
        Medico ultimo = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            sinkDown(0);
        }
        return min;
    }

    // Necesario para reajustar cuando se modifican horas/fatiga (Tiempo O(log n))
    public void reajustar(Medico m) {
        int index = heap.indexOf(m);
        if (index != -1) {
            bubbleUp(index);
            sinkDown(index);
        }
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void sinkDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) smallest = left;
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            sinkDown(smallest);
        }
    }

    private void swap(int i, int j) {
        Medico temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

// 3. Clase principal que gestiona el Bosque de Heaps
public class GestorFuerzaLaboral {
    private Map<String, HeapMedico> bosque = new HashMap<>();
    private Map<String, Medico> indiceMedicos = new HashMap<>();

    public void agregarMedico(Medico m) {
        indiceMedicos.put(m.id, m);
        bosque.putIfAbsent(m.especialidad, new HeapMedico());
        bosque.get(m.especialidad).insertar(m);
    }

    public void asignarCirugia(String especialidad) {
        HeapMedico fila = bosque.get(especialidad);
        if (fila != null) {
            Medico mejor = fila.extraerMinimo();
            if (mejor != null) {
                mejor.disponible = false;
                System.out.println("Asignado: " + mejor.id);
            }
        }
    }

    public void actualizarEstadoMedico(String id, int horasExtra, int nuevaFatiga) {
        Medico m = indiceMedicos.get(id);
        if (m != null) {
            m.horasGuardia += horasExtra;
            m.nivelFatiga = nuevaFatiga;
            bosque.get(m.especialidad).reajustar(m);
        }
    }
}
