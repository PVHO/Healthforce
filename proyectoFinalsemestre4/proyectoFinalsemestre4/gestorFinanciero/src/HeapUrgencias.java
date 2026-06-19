import java.util.ArrayList;

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
