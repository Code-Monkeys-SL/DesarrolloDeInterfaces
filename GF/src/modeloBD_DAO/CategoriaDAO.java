package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.CategoriaDTO;

public class CategoriaDAO implements Patron_DAO<CategoriaDTO> {
	private static final String SQL_CREATE = "INSERT INTO Categoria (nombre, descripcion) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Categoria WHERE id_categoria = ?";
	private static final String SQL_UPDATE = "UPDATE Categoria SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
	private static final String SQL_READ  = "SELECT * FROM Categoria WHERE id_categoria = ?";
	private static final String SQL_READALL  = "SELECT * FROM Categoria";
	private static final String SQL_READBYNAME = "SELECT * FROM Categoria WHERE nombre = ?"; 
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

	@Override
	public boolean create(CategoriaDTO cat) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_CREATE);
			ps.setString(1, cat.getNombre());
			ps.setString(2, cat.getDescripcion());
			
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
	public boolean update(CategoriaDTO cat) {
		PreparedStatement ps = null;
		try {
			ps = conn.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, cat.getNombre());
			ps.setString(2, cat.getDescripcion());
			ps.setInt(3, cat.getIdCategoria());
			
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
	public CategoriaDTO read(Object pk) {
		CategoriaDTO Cat = null;
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READ);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true) {
				Cat = new CategoriaDTO(rs.getInt("id_categoria"), rs.getString("nombre"), rs.getString("descripcion"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Cat;
	}

	@Override
	public ArrayList<CategoriaDTO> readAll() {
		ArrayList<CategoriaDTO> listaCat = new ArrayList<CategoriaDTO>();
		try {
			PreparedStatement ps = conn.getCon().prepareStatement(SQL_READALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoriaDTO cat = new CategoriaDTO(rs.getInt("id_categoria"), rs.getString("nombre"), rs.getString("descripcion"));
				listaCat.add(cat);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCat;
	}

	public CategoriaDTO readByName(String name) {
	    CategoriaDTO cat = null;
	    PreparedStatement ps = null;
	    try {
	        ps = conn.getCon().prepareStatement(SQL_READBYNAME);
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            cat = new CategoriaDTO(rs.getInt("id_categoria"), rs.getString("nombre"), rs.getString("descripcion"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return cat;
	}
}
