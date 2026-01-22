package modeloBD_DTO;

/**
 * Clase que representa una categoría en la cafeteria.
 */
public class CategoriaDTO {
	private int idCategoria; // Identificador único para la categoría
    private String nombre; // Nombre de la categoría
    private String descripcion; // Descripción de la categoría
    
    /**
     * Constructor que inicializa una nueva instancia de CategoriaDTO.
     *
     * @param idCategoria Identificador único de la categoría
     * @param nombre Nombre de la categoría
     * @param descripcion Descripción de la categoría
     */
	public CategoriaDTO(int idCategoria, String nombre, String descripcion) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
     * Obtiene el identificador de la categoría.
     *
     * @return El identificador de la categoría
     */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
     * Establece el identificador de la categoría.
     *
     * @param idCategoria El identificador de la categoría a establecer
     */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
     * Obtiene el nombre de la categoría.
     *
     * @return El nombre de la categoría
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Establece el nombre de la categoría.
     *
     * @param nombre El nombre de la categoría a establecer
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
     * Obtiene la descripción de la categoría.
     *
     * @return La descripción de la categoría
     */
	public String getDescripcion() {
		return descripcion;
	}

	/**
     * Establece la descripción de la categoría.
     *
     * @param descripcion La descripción de la categoría a establecer
     */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
