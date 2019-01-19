package modelo;

// Importación de la clase LinkedList para el almacenamiento de Pacientes
import java.util.LinkedList;

/**
 *
 * @author Omar Augusto Bautista Mora
 */
public class GestorPaciente {
    // Declaración de la variable pacientes que será la responsable 
    // de almacenar todos los pacientes registrados
    private static LinkedList<Paciente> pacientes;
    
    // Constructor de la clase
    public GestorPaciente() {
        // En el constructor de la clase se instancia el LinkedList determinando
        // que en cada posición se almacenará un objeto de tipo Paciente
        pacientes = new LinkedList<Paciente>();
    }
    
    /**
     * Método que recibe un objeto de tipo Paciente y lo guarda dentro
     * del LinkedList
     * @param paciente 
     */
    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
    
    /**
     * Método que recibe un criterio de búsqueda y un valor y retorna una
     * lista de pacientes que cumplen con los parámetros recibidos
     * @param parametro
     * @param valor
     * @return resultado con la lista de pacientes 
     */
    public LinkedList<Paciente> getPacientesBy(int parametro, String valor) {
        // Se crea un LinkedList interno para almacenar los pacientes que
        // cumplen con los criterios de la consulta
        LinkedList<Paciente> resultado = new LinkedList<Paciente>();
        // Recorrido de cada uno de los pacientes registrados
        for(Paciente pac: pacientes) {
            /*  Evalúa el valor del parámetro para conocer si se desea hacer la
                búsqueda por Identificación 1, Nombres 2, Apellidos 3, Sexo 4
                De acuerdo con el parámetro de búsqueda, verifica si coincide cada
                paciente con el valor recibido. En ese caso, se agrega al LinkedList
                interno resultado */
            switch(parametro) {
                case 1: if(pac.getIdentificacion().equals(valor))
                    resultado.add(pac);
                break;
                case 2: if(pac.getNombres().equals(valor))
                    resultado.add(pac);
                break;
                case 3: if(pac.getApellidos().equals(valor))
                    resultado.add(pac);
                break;
                case 4: if(pac.getSexo().equals(valor))
                    resultado.add(pac);
                break;
            }
        }
        // Retorna el LinkedList interno resultado con los pacientes encontrados
        return resultado;
    }
}
