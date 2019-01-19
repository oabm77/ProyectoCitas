/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurso;

// Importación del paquete sql para hacer uso de las clases que permiten
// hacer la conexión con la base de datos
import java.sql.*;
// Importación de la clase JOptionPane empleada para presentar un mensaje
// al usuario cuando la conexión es incorrecta
import javax.swing.JOptionPane;

/**
 *
 * @author Omar Augusto Bautista Mora
 */
public class Conexion {
    // Atributos requeridos para hacer la conexión con la base de datos
    // driver y url son cadenas de conexión usadas de manera particular
    // para conexiones con bases de datos oracle
    private String driver, url, ip, bd, usr, pass;
    private Connection conexion;
    
    /**
     * Constructor
     * @param ip    indica la dirección ip donde reside el
                    servidor de bases de datos, si es local, se define como “localhost”
     * @param bd    nombre de la base de datos
     * @param usr   usuario de acceso al esquema
     * @param pass  password de acceso al esquema
     */
    public Conexion(String ip, String bd, String usr, String pass) {
        driver = "oracle.jdbc.driver.OracleDriver";
        this.bd = bd;
        this.usr = usr;
        this.pass = pass;
        url = "jdbc:oracle:thin:@" + ip + ":1521:" + bd;
        try {
            // Registro del driver en la aplicación
            Class.forName(driver).newInstance();
            // Creación de la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usr, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos");
        }
    }
    
    /**
     * 
     * @return Retorna la conexión establecida
     */
    public Connection getConexion() {
        return conexion;
    }
    
    /**
     * 
     * @return Cierre de la conexión creada
     * @throws SQLException 
     */
    public Connection cerrarConexion() throws SQLException {
        conexion.close();
        conexion = null;
        return conexion;
    }
}
