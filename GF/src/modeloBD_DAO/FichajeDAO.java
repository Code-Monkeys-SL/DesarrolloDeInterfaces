package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.FichajeDTO;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad Fichaje.
 * Esta clase gestiona las operaciones CRUD.
 */
public class FichajeDAO implements Patron_DAO<FichajeDTO> {
	private static final String SQL_CREATE = "INSERT INTO Fichaje (accion, fecha_inicial, fecha_final, id_personal) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Fichaje WHERE id_fichaje = ?";
	private static final String SQL_UPDATE = "UPDATE Fichaje SET accion = ?, fecha_inicial = ?, fecha_final = ?, id_personal = ? WHERE id_fichaje = ?";
	private static final String SQL_READ  = "SELECT * FROM Fichaje WHERE id_fichaje = ?";
	private static final String SQL_READALL  = "SELECT * FROM Fichaje";
	private static final String SQL_READPER  = "SELECT * FROM Fichaje WHERE id_personal = ?";
	private static final String SQL_READPERTODAY = "SELECT * FROM Fichaje WHERE id_personal = ? AND DATE(fecha_inicial) = CURDATE() AND fecha_final IS NULL";
	private static final String SQL_READPERPAST = "SELECT * FROM Fichaje WHERE id_personal = ? AND DATE(fecha_inicial) < CURDATE() AND fecha_final IS NULL";
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

	/**
     * Inserta un nuevo fichaje en la base de datos.
     *
     * @param fich El objeto FichajeDTO que representa el fichaje a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
	@Override
	public boolean create(FichajeDTO fich) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_CREATE);
			ps.setString(1, fich.getAccion());
			ps.setTimestamp(2, fich.getFechaInicial());
			if (fich.getFechaFinal() != null) {
				ps.setTimestamp(3, fich.getFechaFinal());
			} else {
				ps.setNull(3, Types.DATE);
			}
			ps.setInt(4, fich.getIdPersonal());
			
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
     * Elimina un fichaje de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del fichaje a eliminar
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
     * Actualiza un fichaje en la base de datos.
     *
     * @param fich El objeto FichajeDTO que contiene los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
	@Override
	public boolean update(FichajeDTO fich) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, fich.getAccion());
			ps.setTimestamp(2, fich.getFechaInicial());
			if (fich.getFechaFinal() != null) {
				ps.setTimestamp(3, fich.getFechaFinal());
			} else {
				ps.setNull(3, Types.DATE);
			}
			ps.setInt(4, fich.getIdPersonal());
			ps.setInt(5, fich.getIdFichaje());
			
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
     * Lee un fichaje de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del fichaje a leer
     * @return Un objeto FichajeDTO representando el fichaje, o null si no se encontró
     */
	@Override
	public FichajeDTO read(Object pk) {
		FichajeDTO Fich = null;
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READ);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true) {
				Fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getTimestamp("fecha_inicial"), rs.getTimestamp("fecha_final"), rs.getInt("id_personal"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Fich;
	}

	/**
     * Lee todos los fichajes de la base de datos.
     *
     * @return Una lista de objetos FichajeDTO representando todos los fichajes
     */
	@Override
	public ArrayList<FichajeDTO> readAll() {
		ArrayList<FichajeDTO> listaFich = new ArrayList<FichajeDTO>();
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FichajeDTO fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getTimestamp("fecha_inicial"), rs.getTimestamp("fecha_final"), rs.getInt("id_personal"));
				listaFich.add(fich);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFich;
	}
	
	/**
     * Lee todos los fichajes asociados a un personal específico.
     *
     * @param idPersonal El identificador del personal cuyas entradas se van a leer
     * @return Una lista de objetos FichajeDTO asociados al personal
     */
	public ArrayList<FichajeDTO> readPer(int idPersonal) {
		ArrayList<FichajeDTO> listaFich = new ArrayList<FichajeDTO>();
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READPER);
			ps.setInt(1, idPersonal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FichajeDTO fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getTimestamp("fecha_inicial"), rs.getTimestamp("fecha_final"), rs.getInt("id_personal"));
				listaFich.add(fich);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFich;
	}
	
	/**
     * Lee el fichaje del día actual para un personal específico.
     *
     * @param idPersonal El identificador del personal
     * @return Un objeto FichajeDTO representando el fichaje del día actual, o null si no se encontró
     */
	public FichajeDTO readPerToday(int idPersonal) {
	    FichajeDTO fich = null;
	    try {
	        PreparedStatement ps = conn.getCon().prepareStatement(SQL_READPERTODAY);
	        ps.setInt(1, idPersonal);
	        
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getTimestamp("fecha_inicial"), rs.getTimestamp("fecha_final"), rs.getInt("id_personal"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return fich;
	}
	
	/**
     * Lee todos los fichajes pasados para un personal específico.
     *
     * @param idPersonal El identificador del personal
     * @return Una lista de objetos FichajeDTO representando los fichajes pasados
     */
	public ArrayList<FichajeDTO> readPastRecords(int idPersonal) {
	    ArrayList<FichajeDTO> listaFich = new ArrayList<>();
	    
	    try {
	        PreparedStatement ps = conn.getCon().prepareStatement(SQL_READPERPAST);
	        ps.setInt(1, idPersonal);
	        
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            FichajeDTO fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getTimestamp("fecha_inicial"), rs.getTimestamp("fecha_final"), rs.getInt("id_personal"));
	            listaFich.add(fich);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaFich;
	}

}
