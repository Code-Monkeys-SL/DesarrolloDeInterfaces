package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.PersonalDTO;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad Personal.
 * Esta clase gestiona las operaciones CRUD.
 */
public class PersonalDAO implements Patron_DAO<PersonalDTO> {
	private static final String SQL_CREATE = "INSERT INTO Personal (nombre, apellidos, telefono, correo, contraseña, admin, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Personal WHERE id_personal = ?";
	private static final String SQL_UPDATE = "UPDATE Personal SET nombre = ?, apellidos = ?, telefono = ?, correo = ?, contraseña = ?, admin = ?, id_categoria = ? WHERE id_personal = ?";
	private static final String SQL_READ  = "SELECT * FROM Personal WHERE id_personal = ?";
	private static final String SQL_READALL  = "SELECT * FROM Personal";
	private static final String SQL_READLOGIN  = "SELECT * FROM Personal WHERE correo = ? AND contraseña = ?";
	private static final String SQL_CHECK_ADMIN = "SELECT p.admin, c.nombre FROM Personal p JOIN Categoria c ON p.id_categoria = c.id_categoria WHERE p.correo = ?";
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

	/**
     * Inserta un nuevo personal en la base de datos.
     *
     * @param per El objeto PersonalDTO que representa el personal a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
	@Override
	public boolean create(PersonalDTO per) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_CREATE);
			ps.setString(1, per.getNombre());
			ps.setString(2, per.getApellidos());
			ps.setString(3, per.getTelefono());
			ps.setString(4, per.getCorreo());
			ps.setString(5, per.getContrasenia());
			ps.setBoolean(6, per.isAdmin());
			ps.setInt(7, per.getIdCategoria());
			
			if (ps.executeUpdate()>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	/**
     * Elimina un personal de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del personal a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
	@Override
	public boolean delete(Object pk) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_DELETE);
			ps.setInt(1, (int)pk);
			int filas = ps.executeUpdate();
			
			if (filas>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	/**
     * Actualiza un personal en la base de datos.
     *
     * @param per El objeto PersonalDTO que contiene los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
	@Override
	public boolean update(PersonalDTO per) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, per.getNombre());
			ps.setString(2, per.getApellidos());
			ps.setString(3, per.getTelefono());
			ps.setString(4, per.getCorreo());
			ps.setString(5, per.getContrasenia());
			ps.setBoolean(6, per.isAdmin());
			ps.setInt(7, per.getIdCategoria());
			ps.setInt(8, per.getIdPersonal());
			
			if (ps.executeUpdate()>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	/**
     * Lee un personal de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del personal a leer
     * @return Un objeto PersonalDTO representando el personal, o null si no se encontró
     */
	@Override
	public PersonalDTO read(Object pk) {
		PersonalDTO Per = null;
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READ);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true) {
				Per = new PersonalDTO(rs.getInt("id_personal"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getString("correo"), rs.getString("contraseña"), rs.getBoolean("admin"), rs.getInt("id_categoria"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Per;
	}

	/**
     * Lee todos los personales de la base de datos.
     *
     * @return Una lista de objetos PersonalDTO representando todos los personales
     */
	@Override
	public ArrayList<PersonalDTO> readAll() {
		ArrayList<PersonalDTO> listaPer = new ArrayList<PersonalDTO>();
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PersonalDTO per = new PersonalDTO(rs.getInt("id_personal"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getString("correo"), rs.getString("contraseña"), rs.getBoolean("admin"), rs.getInt("id_categoria"));
				listaPer.add(per);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPer;
	}

	/**
     * Inicia sesión para un personal verificando el correo y la contraseña.
     *
     * @param mail El correo electrónico del personal
     * @param pass La contraseña del personal
     * @return Un objeto PersonalDTO representando el personal, o null si las credenciales son incorrectas
     */
	public PersonalDTO login(String mail, String pass) {
		PersonalDTO Per = null;
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READLOGIN);
			ps.setString(1, mail);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true) {
				Per = new PersonalDTO(rs.getInt("id_personal"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getString("correo"), rs.getString("contraseña"), rs.getBoolean("admin"), rs.getInt("id_categoria"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Per;
	}
	
	/**
     * Verifica si un personal es administrador basado en su correo.
     *
     * @param correo El correo del personal a verificar
     * @return true si es administrador, false en caso contrario
     */
	public boolean isAdmin(String correo) {
        boolean isAdmin = false;
        try {
            PreparedStatement ps = conn.getCon().prepareStatement(SQL_CHECK_ADMIN);
            ps.setString(1, correo);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                isAdmin = rs.getBoolean("admin") || "Gerente".equalsIgnoreCase(rs.getString("nombre"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmin;
    }
}
