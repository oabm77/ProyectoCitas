package controlador;

// Importación de paquetes para el manejo del evento click del botón
// Registrar formulario Registro de Pacientes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Importación de la clase SimpleDataFormat para dar formato
// a la fecha de nacimiento recibida desde la vista
import java.text.SimpleDateFormat;
/**
 * 
 * @author Omar Augusto Bautista Mora
 */
// ActionListener es la interfaz que se debe implementar para
// escuchar eventos
public class PacienteControl implements ActionListener {
    // Declaración de variables asociadas a la vista y al modelo.
    // El controlador debe conocer a la vista y al modelo para poder
    // comunicarse con ellos
    vista.RegPacienteInternalFrame pacienteVista;
    modelo.Paciente pacienteModelo;
    modelo.GestorPaciente gestorPacienteModelo;
    
    // El constructor de la clase controladora recibe como parámetro
    // la vista que debe controlar
    public PacienteControl(vista.RegPacienteInternalFrame pacienteVista) {
        // Creación de las variables asociadas a la vista y el modelo
        this.pacienteVista = pacienteVista;
        gestorPacienteModelo = new modelo.GestorPaciente();
    }

    // Anotación que indica que el método actionPerformed se está
    // sobreescribiendo
    @Override
    // Método de la interfaz ActionListener que se debe implementar.
    // Este método se ejecuta cuando se hace click en el botón Registrar
    // del formulario Registro de Pacientes
    public void actionPerformed(ActionEvent e) {
        // Verifica si fue el botón Registrar del formulario Registro de
        // Pacientes el que generó el evento
        if(e.getSource().equals(pacienteVista.RegistrarBtn)) {
            // Captura de la información ingresada por el usuario en el formulario
            // Registro de Pacientes
            String identificacion = pacienteVista.IdentificacionTxt.getText();
            String nombres = pacienteVista.NombresTxt.getText();
            String apellidos = pacienteVista.AppellidosTxt.getText();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaNacimiento = formato.format(pacienteVista.FechaNacimientoDtc.getDate());
            String sexo = null;
            if(pacienteVista.MasculinoOpt.isSelected())
                sexo = "m";
            else
                sexo = "f";
            // Creación de un objeto de tipo Paciente a partir de los datos
            // ingresados por el usuario
            pacienteModelo = new modelo.Paciente(identificacion, nombres, apellidos, fechaNacimiento, sexo);
            // Registro del paciente creado a través de la clase GestorPaciente
            // del modelo
            gestorPacienteModelo.registrarPaciente(pacienteModelo);
        }
        // Verifica si fue el botón Nuevo del formulario Registro de
        // Pacientes el que generó el evento
        if(e.getSource().equals(pacienteVista.NuevoBtn)) {
            // Borra los contenidos de los controles del formulario Registro de
            // Pacientes
            pacienteVista.IdentificacionTxt.setText(null);
            pacienteVista.NombresTxt.setText(null);
            pacienteVista.AppellidosTxt.setText(null);
            pacienteVista.FechaNacimientoDtc.setDate(null);
            pacienteVista.MasculinoOpt.setSelected(false);
            pacienteVista.FemeninoOpt.setSelected(false);
            pacienteVista.IdentificacionTxt.requestFocus();
        }
    }
    
}
