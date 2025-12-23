package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.FichajeDTO;

public class FichajeDAO implements Patron_DAO<FichajeDTO> {
	private static final String SQL_CREATE = "INSERT INTO Fichaje (accion, fecha_inicial, fecha_final, id_personal) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Fichaje WHERE id_fichaje = ?";
	private static final String SQL_UPDATE = "UPDATE Fichaje SET accion = ?, fecha_inicial = ?, fecha_final = ?, id_personal = ? WHERE id_fichaje = ?";
	private static final String SQL_READ  = "SELECT * FROM Fichaje WHERE id_fichaje = ?";
	private static final String SQL_READALL  = "SELECT * FROM Fichaje";
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

	@Override
	public boolean create(FichajeDTO fich) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_CREATE);
			ps.setString(1, fich.getAccion());
			ps.setDate(2, fich.getFechaInicial());
			if (fich.getFechaFinal() != null) {
				ps.setDate(3, fich.getFechaFinal());
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
	public boolean update(FichajeDTO fich) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, fich.getAccion());
			ps.setDate(2, fich.getFechaInicial());
			if (fich.getFechaFinal() != null) {
				ps.setDate(3, fich.getFechaFinal());
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

	@Override
	public FichajeDTO read(Object pk) {
		FichajeDTO Fich = null;
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READ);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true) {
				Fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getDate("fecha_inicial"), rs.getDate("fecha_final"), rs.getInt("id_personal"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Fich;
	}

	@Override
	public ArrayList<FichajeDTO> readAll() {
		ArrayList<FichajeDTO> listaFich = new ArrayList<FichajeDTO>();
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FichajeDTO fich = new FichajeDTO(rs.getInt("id_fichaje"), rs.getString("accion"), rs.getDate("fecha_inicial"), rs.getDate("fecha_final"), rs.getInt("id_personal"));
				listaFich.add(fich);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFich;
	}

}
