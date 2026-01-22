package modeloBD_DAO;

import java.util.ArrayList;

/**
 * Interfaz genérica que define las operaciones básicas para acceder a datos de un tipo específico.
 *
 * @param <T> El tipo de objeto que esta interfaz manejará
 */
public interface Patron_DAO <T> {
	
	/**
     * Inserta un nuevo registro en la base de datos.
     *
     * @param t El objeto que se desea insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
	public boolean create(T t); //Insertar un registro (del tipo que sea)
	
	/**
     * Elimina un registro de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del registro que se desea eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
	public boolean delete(Object pk); //Eliminar un registro referenciado por su PK
	
	/**
     * Actualiza un registro en la base de datos.
     *
     * @param t El objeto con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
	public boolean update(T t); //Actualizar un registro
	
	/**
     * Lee un registro específico de la base de datos utilizando su clave primaria.
     *
     * @param pk La clave primaria del registro que se desea leer
     * @return El objeto correspondiente al registro leído, o null si no se encontró
     */
	public T read (Object pk); //Devuelve el registro cuya PK se le pasa
	
	/**
     * Lee todos los registros de la tabla.
     *
     * @return Una lista con todos los registros
     */
	public ArrayList<T> readAll();//Devuelve la lista de todos los registros de la tabla
}
