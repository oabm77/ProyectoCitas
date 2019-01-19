package modelo;

import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar Augusto Bautista Mora
 */
public class GestorPaciente {
    // Declaración de un objeto de tipo Connection que recibe la conexión
    // con la base de datos
    private static Connection conn;

    /**
     * Constructor de la clase que envía a la clase Conexion los parámetros
     * específicos de la base de datos y obtiene la conexión con la misma
     */
    public GestorPaciente() {
        recurso.Conexion conexion = new recurso.Conexion("localhost", "XE", "citas", "citas");
        conn = conexion.getConexion();
    }

    public void registrarPaciente(Paciente paciente) {
        try {
            // Se crea un objeto de tipo PreparedStatement, a partir de la conexión
            // creada en el constructor y se establece la sentencia insert, dejando los
            // valores de la misma para ser establecidos posteriormente
            PreparedStatement pst = conn.prepareStatement("insert into PACIENTES values (?,?,?,?,?)");
            // Definición de los valores que se deben insertar a través de la sentencia
            // insert
            pst.setString(1, paciente.getIdentificacion());
            pst.setString(2, paciente.getNombres());
            pst.setString(3, paciente.getApellidos());
            pst.setString(4, paciente.getFechaNacimiento());
            pst.setString(5, paciente.getSexo());
            // Ejecuta la sentencia insert sobre la base de datos
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente Registrado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /**
     * 
     * @param parametro
     * @param valor
     * @return LinkedList para retornar los pacientes consultados de la base de datos
     */
    public LinkedList<Paciente> getPacientesBy(int parametro, String valor) {
        // Se crea un LinkedList interno para almacenar los pacientes que
        // cumplen con los criterios de la consulta
        LinkedList<Paciente> resultado = new LinkedList<Paciente>();
        String sql = "";
        // De acuerdo con el parámetro de búsqueda se configura la consulta específica
        // (buscar por la identificación, por el nombre, por el apellido o por el sexo)
        switch (parametro) {
            case 1:
                sql = "select * from PACIENTES where pacIdentificacion='" + valor + "'";
                break;
            case 2:
                sql = "select * from PACIENTES where pacNombres='" + valor + "'";
                break;
            case 3:
                sql = "select * from PACIENTES where pacApellidos='" + valor + "'";
                break;
            case 4:
                sql = "select * from PACIENTES where pacSexo='" + valor + "'";
                break;
        }
        try {
            Statement st = conn.createStatement();
            // Se ejecuta la consulta
            ResultSet rs = st.executeQuery(sql);
            // Recorre los resultados de la consulta, con cada registro devuelto se crea un
            // paciente nuevo , el cual se almacena en el LinkedList que será retornado
            while (rs.next()) {
                resultado.add(new Paciente(rs.getString("pacIdentificacion"),
                        rs.getString("pacNombres"),
                        rs.getString("pacApellidos"),
                        rs.getString("pacFechaNacimiento"),
                        rs.getString("pacSexo")));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Retorno del LinkedList que posee a todos los pacientes que cumplen con
            // el criterio de búsqueda enviado al método
            return resultado;
        }
    }
}
