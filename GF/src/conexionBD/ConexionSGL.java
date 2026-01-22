package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Esta clase se utiliza para gestionar la conexión a una base de datos MySQL.
 */
public class ConexionSGL {
	private static ConexionSGL instancia = null; // Instancia
	private static Connection con; // Conexión a la base de datos
	
	/**
     * Constructor privado para inicializar la conexión a la base de datos.
     * Los parámetros de conexión pueden ser modificados si es necesario.
     */
	private ConexionSGL () {
		String host = "127.0.0.1"; // Host de la base de datos, también se puede usar "localhost
		String user = "root"; // Usuario de la base de datos, cambiar si se utiliza un usuario diferente
		String pass = "root"; // Contraseña de la base de datos, cambiar si se utiliza una contraseña diferente
		String dtbs = "cafeteria"; // Nombre de la base de datos a la que conectars
		
		try{
//			Class.forName("com.mysql.jdbc.Driver"); // Inicializar el driver para versiones antiguas, descomentar si falla la conexión
			String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
		}catch (Exception e) {
			System.out.println("Error al abrir la conexión.");
		}
	}
	
	/**
     * Método estático que proporciona la instancia única de la clase.
     * 
     * @return La instancia única de ConexionSGL.
     */
	public static ConexionSGL getInstancia(){
		if (instancia == null) instancia = new ConexionSGL();
		return instancia;
	}
	
	/**
     * Método que retorna la conexión a la base de datos.
     * 
     * @return La conexión a la base de datos.
     */
	public Connection getCon (){
		return con;
	}
	
	/**
     * Método para cerrar la conexión a la base de datos.
     */
	public void cerrarConexion() {
		try {
			con.close();
		}catch (Exception e) {
			System.out.println("Error al cerrar la conexión.");
		}
	}
}
