package servidor.modelo;

public class Estudiante {
    private String cedula;
    private String nombre;
    private String apellido;
    private String carrera;

    public Estudiante(String cedula, String nombre, String apellido, String carrera) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
    }

    public String getCedula() {
        return cedula;
    }

    public String getInfo() {
        return "Nombre: " + nombre + " " + apellido + " - Carrera: " + carrera;
    }
}
