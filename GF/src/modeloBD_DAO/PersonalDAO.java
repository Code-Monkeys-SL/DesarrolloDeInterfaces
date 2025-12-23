package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.PersonalDTO;

public class PersonalDAO implements Patron_DAO<PersonalDTO> {
	private static final String SQL_CREATE = "INSERT INTO Personal (nombre, apellidos, telefono, correo, contraseña, admin, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Personal WHERE id_personal = ?";
	private static final String SQL_UPDATE = "UPDATE Personal SET nombre = ?, apellidos = ?, telefono = ?, correo = ?, contraseña = ?, admin = ?, id_categoria = ? WHERE id_personal = ?";
	private static final String SQL_READ  = "SELECT * FROM Personal WHERE id_personal = ?";
	private static final String SQL_READALL  = "SELECT * FROM Personal";
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

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

}
