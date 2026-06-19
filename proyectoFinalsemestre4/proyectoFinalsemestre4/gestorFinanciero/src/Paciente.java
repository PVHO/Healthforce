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
