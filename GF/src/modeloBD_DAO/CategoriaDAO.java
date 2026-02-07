package modeloBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionSGL;
import modeloBD_DTO.CategoriaDTO;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad Categoria.
 * 
 * Esta clase gestiona las operaciones CRUD.
 */
public class CategoriaDAO implements Patron_DAO<CategoriaDTO> {
	private static final String SQL_CREATE = "INSERT INTO Categoria (nombre, descripcion) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Categoria WHERE id_categoria = ?";
	private static final String SQL_UPDATE = "UPDATE Categoria SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
	private static final String SQL_READ  = "SELECT * FROM Categoria WHERE id_categoria = ?";
	private static final String SQL_READALL  = "SELECT * FROM Categoria";
	private static final String SQL_READBYNAME = "SELECT * FROM Categoria WHERE nombre = ?"; 
	
	private ConexionSGL conn = ConexionSGL.getInstancia();

	/**
	 * Inserta una nueva categoría en la base de datos.
	 * 
	 * @param cat El objeto CategoriaDTO que representa la categoría a insertar
	 * @return true si la inserción fue exitosa, false en caso contrario
     */
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

	/**
	 * Elimina una categoría de la base de datos utilizando su clave primaria.
	 * 
	 * @param pk La clave primaria de la categoría a eliminar
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
	 * Actualiza una categoría en la base de datos.
	 * 
	 * @param cat El objeto CategoriaDTO que contiene los datos actualizados
	 * @return true si la actualización fue exitosa, false en caso contrario
     */
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

	/**
	 * Lee una categoría de la base de datos utilizando su clave primaria.
	 * 
	 * @param pk La clave primaria de la categoría a leer
	 * @return Un objeto CategoriaDTO representando la categoría, o null si no se encontró
     */
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

	/**
	 * Lee todas las categorías de la base de datos.
	 * 
	 * @return Una lista de objetos CategoriaDTO representando todas las categorías
     */
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

	/**
     * Lee una categoría de la base de datos utilizando su nombre.
     *
     * @param name El nombre de la categoría a leer
     * @return Un objeto CategoriaDTO representando la categoría, o null si no se encontró
     */
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
