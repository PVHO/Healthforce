import java.util.*;

/**
 * Módulo de Inventario Health Force
 * Gestión optimizada de activos críticos hospitalarios.
 */
public class InventarioHospital {
    // HashMap para acceso instantáneo O(1) a cualquier insumo
    private Map<String, Integer> stockMap = new HashMap<>();

    // Cola de prioridad para atención de desabastecimiento (Alertas críticas)
    private PriorityQueue<String> colaCritica = new PriorityQueue<>();

    // Stack para auditoría de movimientos (LIFO) para trazabilidad
    private Stack<String> historialMovimientos = new Stack<>();

    // Agregar producto al inventario con validación de integridad
    public void agregarProducto(String nombre, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: Cantidad no válida para " + nombre);
            return; // Prevenir corrupción de estado
        }

        stockMap.put(nombre, stockMap.getOrDefault(nombre, 0) + cantidad);
        historialMovimientos.push("AGREGADO: " + nombre + " | Cantidad: " + cantidad);

        // Si el stock se recupera, lo sacamos de la cola crítica
        if (stockMap.get(nombre) >= 5) {
            colaCritica.remove(nombre);
        }
        System.out.println("Producto '" + nombre + "' registrado correctamente.");
    }

    // Despacho de insumos con control de stock
    public boolean despacharInsumo(String nombre, int cantidad) {
        if (stockMap.containsKey(nombre) && stockMap.get(nombre) >= cantidad) {
            stockMap.put(nombre, stockMap.get(nombre) - cantidad);
            historialMovimientos.push("DESPACHO: " + nombre + " | Cantidad: " + cantidad);

            // Lógica de triaje: si baja de 5 unidades, es prioridad crítica
            if (stockMap.get(nombre) < 5) {
                colaCritica.add(nombre);
            }
            return true;
        }
        System.out.println("Alerta: Stock insuficiente para " + nombre);
        return false;
    }

    // Consulta de stock (Búsqueda eficiente)
    public int consultarStock(String nombre) {
        return stockMap.getOrDefault(nombre, 0);
    }

    // Auditoría para mostrar movimientos realizados
    public void mostrarAuditoria() {
        System.out.println("\n--- Historial de Auditoría ---");
        Stack<String> copia = (Stack<String>) historialMovimientos.clone();
        while(!copia.isEmpty()) {
            System.out.println(copia.pop());
        }
    }

}