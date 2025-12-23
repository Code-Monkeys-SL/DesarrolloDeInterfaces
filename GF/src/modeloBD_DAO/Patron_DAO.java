package modeloBD_DAO;

import java.util.ArrayList;

public interface Patron_DAO <T> {
	public boolean create(T t); //Insertar un registro (del tipo que sea)
	public boolean delete(Object pk); //Eliminar un registro referenciado por su PK
	public boolean update(T t); //Actualizar un registro
	
	public T read (Object pk); //Devuelve el registro cuya PK se le pasa
	public ArrayList<T> readAll();//Devuelve la lista de todos los registros de la tabla
}
