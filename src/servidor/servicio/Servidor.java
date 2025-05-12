package servidor.servicio;

import servidor.modelo.Estudiante;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Servidor {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public Servidor() {
        // Datos quemados
        estudiantes.add(new Estudiante("123", "Ana", "Pérez", "Ingeniería"));
        estudiantes.add(new Estudiante("456", "Luis", "Gómez", "Medicina"));
        estudiantes.add(new Estudiante("789", "María", "López", "Derecho"));
    }

    public void servicio() {
        int puerto = 5000;
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor UDP corriendo en el puerto " + puerto + "...");

            byte[] bufferEntrada = new byte[1024];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String cedula = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Consulta recibida para cédula: " + cedula);

                String respuesta = buscarEstudiante(cedula);

                byte[] bufferSalida = respuesta.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida,
                        bufferSalida.length,
                        paqueteEntrada.getAddress(),
                        paqueteEntrada.getPort()
                );
                socket.send(paqueteSalida);
                System.out.println("Respuesta enviada: " + respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buscarEstudiante(String cedula) {
        for (Estudiante est : estudiantes) {
            if (est.getCedula().equals(cedula)) {
                return est.getInfo();
            }
        }
        return "Estudiante no encontrado.";
    }
}
