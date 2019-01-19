package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 *
 * @author Omar Augusto Bautista Mora
 */
// La clase debe poder escchar eventos de la vista, en este caso el
// evento click del botón Aceptar del formulario Consulta de Pacientes
public class GestorPacienteControl implements ActionListener {
    // Declaración de variables asociadas a la vista y al modelo. El controlador
    // debe conocer a la vista y al modelo para poder comunicarse con ellos.
    modelo.GestorPaciente pacientesModelo;
    vista.ConsPacienteInternalFrame consultarPacienteVista;
    
    // El constructor de la clase controladora recibe como parámetro la vista
    // que debe controlar.
    public GestorPacienteControl(vista.ConsPacienteInternalFrame consultarPacienteVista) {
        // Creación de las variables asociadas a la vista y el modelo
        this.consultarPacienteVista = consultarPacienteVista;
        pacientesModelo = new modelo.GestorPaciente();
    }

    // Anotación que indica que el método actionPerformed se está
    // sobreescribiendo
    @Override
    // Método que se ejecuta cuando el usuario hace click en el botón Aceptar
    // del formulario Consulta de Pacientes
    public void actionPerformed(ActionEvent e) {
        // Captura del valor introducido por el usuario en la caja de texto del
        // formulario Consulta de Pacientes
        String valor = consultarPacienteVista.ValorTxt.getText();
        // Declaración e inicialización de la variable que va a indicar el criterio de
        // búsqueda seleccionado por el usuario (Identificación, Nombres, Apellidos
        // o Sexo) en el formulario Consulta de Pacientes
        int parametro = 0;
        // Borrado de todas las filas del TableModel asociado al JTable donde
        // se presentan los resultados de la consulta en el formulario Consulta
        // de Pacientes
        consultarPacienteVista.getTableModel().setRowCount(0);
        // Actualización del JTable, esto hace que el JTable quede vacío para
        // poder ser cargado posteriormente con los resultados de la consulta
        consultarPacienteVista.getTableModel().fireTableDataChanged();
        // Captura de la opción de búsqueda seleccionada por el usuario en el
        // formulario Consulta de Pacientes, de acuerdo a esa opción se asigna
        // un valor a la variable parámetro
        if(consultarPacienteVista.IdentificacionOpt.isSelected())
            parametro = 1;
        if(consultarPacienteVista.NombresOpt.isSelected())
            parametro = 2;
        if(consultarPacienteVista.ApellidosOpt.isSelected())
            parametro = 3;
        if(consultarPacienteVista.SexoOpt.isSelected())
            parametro = 4;
        // Captura de los pacientes devueltos por el modelo de acuerdo con los
        // criterios de búsqueda proporcionados por el usuario
        LinkedList<modelo.Paciente> pacientes = pacientesModelo.getPacientesBy(parametro, valor);
        String registro[] = new String[5];
        // Recorrido de cada uno de los pacientes retornados por el modelo a
        // partir de la consulta realizada
        for(modelo.Paciente p: pacientes) {
            // Asignación de los valores de cada paciente al arreglo que se cargará
            // en el TableModel
            registro[0]=p.getIdentificacion();
            registro[1]=p.getNombres();
            registro[2]=p.getApellidos();
            registro[3]=p.getFechaNacimiento();
            registro[4]=p.getSexo();
            // Adición de una nueva fila al TableModel cuyo contenido es la
            // información del arreglo
            consultarPacienteVista.getTableModel().addRow(registro);
            // Actualización del JTable, esto hace que el JTable quede vacío para
            // poder ser cargado posteriormente con los resultados de la consulta
            consultarPacienteVista.getTableModel().fireTableDataChanged();
        }
    }
    
    
}
