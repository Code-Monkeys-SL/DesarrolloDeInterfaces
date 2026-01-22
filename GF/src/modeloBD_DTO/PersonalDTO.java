package modeloBD_DTO;

import java.util.Comparator;

import modeloBD_DAO.CategoriaDAO;

/**
 * Clase que representa a un personal de la cafeteria.
 */
public class PersonalDTO implements Comparable <PersonalDTO> {
	private int idPersonal; // Identificador único del personal
    private String nombre; // Nombre del personal
    private String apellidos; // Apellidos del personal
    private String telefono; // Teléfono de contacto del personal
    private String correo; // Correo electrónico del personal
    private String contrasenia; // Contraseña del personal
    private boolean admin; // Indica si el personal tiene permisos administrador excepcionales
    private int idCategoria; // Identificador de la categoría del personal
    
    /**
     * Constructor que inicializa una nueva instancia de PersonalDTO.
     * 
     * @param idPersonal Identificador único del personal
     * @param nombre Nombre del personal
     * @param apellidos Apellidos del personal
     * @param telefono Teléfono de contacto del personal
     * @param correo Correo electrónico del personal
     * @param contrasenia Contraseña del personal
     * @param admin Indica si el personal tiene permisos administrador excepcionales
     * @param idCategoria Identificador de la categoría del personal
     */
	public PersonalDTO(int idPersonal, String nombre, String apellidos, String telefono, String correo,
			String contrasenia, boolean admin, int idCategoria) {
		super();
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.admin = admin;
		this.idCategoria = idCategoria;
	}

	/**
	 * Obtiene el identificador del personal.
	 * 
	 * @return El identificador del personal
     */
	public int getIdPersonal() {
		return idPersonal;
	}

	/**
	 * Establece el identificador del personal.
	 * 
	 * @param idPersonal El identificador del personal a establecer
     */
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	/**
	 * Obtiene el nombre del personal.
	 * 
	 * @return El nombre del personal
     */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del personal.
	 * 
	 * @param nombre El nombre del personal a establecer
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene los apellidos del personal.
	 * 
	 * @return Los apellidos del personal
     */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del personal.
	 * 
	 * @param apellidos Los apellidos del personal a establecer
     */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene el teléfono de contacto del personal.
	 * 
	 * @return El teléfono de contacto del personal
     */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono de contacto del personal.
	 * 
	 * @param telefono El teléfono de contacto del personal a establecer
     */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtiene el correo electrónico del personal.
	 * 
	 * @return El correo electrónico del personal
     */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Establece el correo electrónico del personal.
	 * 
	 * @param correo El correo electrónico del personal a establecer
     */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Obtiene la contraseña del personal.
	 * 
	 * @return La contraseña del personal
     */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Establece la contraseña del personal.
	 * 
	 * @param contrasenia La contraseña del personal a establecer
     */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Indica si el personal es administrador.
	 * 
	 * @return true si es administrador, false en caso contrario
     */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Establece si el personal es administrador.
	 * 
	 * @param admin true si se desea establecer como administrador, false en caso contrario
     */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Obtiene el identificador de la categoría asociada al personal.
	 *
	 * @return El identificador de la categoría
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Establece el identificador de la categoría asociada al personal.
	 *
	 * @param idCategoria El identificador de la categoría a establecer
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Compara este personal con otro personal basado en su identificador.
	 *
	 * @param p PersonalDTO a comparar
	 * @return 0 si son iguales, un valor negativo si este personal es menor, y un valor positivo si es mayor
	 */
	@Override
	public int compareTo(PersonalDTO p) {
		if (this == p)
			return 0;
		if (p == null)
			return 1;
		if (getClass() != p.getClass())
			return 1;
		
		if (this.idPersonal == 0) {
			if (p.idPersonal != 0) return -1;
				else return 0;
		} else
			return Integer.compare(this.idPersonal, p.idPersonal);
	}
	
	/**
	 * Clase interna que contiene comparadores para PersonalDTO.
	 */
	public static class Comparadores {
	    // Comparadores Ascendentes
		/**
	     * Comparador ascendente para los identificadores de personal.
	     */
	    public static Comparator<PersonalDTO> ID_PERSONAL_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p1.getIdPersonal(), p2.getIdPersonal());
	        }
	    };

	    /**
	     * Comparador ascendente para los nombres del personal.
	     */
	    public static Comparator<PersonalDTO> NOMBRE_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p1.getNombre().compareTo(p2.getNombre());
	        }
	    };

	    /**
	     * Comparador ascendente para los apellidos del personal.
	     */
	    public static Comparator<PersonalDTO> APELLIDOS_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p1.getApellidos().compareTo(p2.getApellidos());
	        }
	    };

	    /**
	     * Comparador ascendente para los identificadores de categoría del personal.
	     */
	    public static Comparator<PersonalDTO> ID_CATEGORIA_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p1.getIdCategoria(), p2.getIdCategoria());
	        }
	    };

	    /**
	     * Comparador ascendente para los nombres de categorías del personal.
	     */
	    public static Comparator<PersonalDTO> CATEGORIA_ASC = new Comparator<PersonalDTO>() {
	    	@Override
	    	public int compare(PersonalDTO p1, PersonalDTO p2) {
	    		String nombreCategoria1 = obtenerNombreCategoria(p1.getIdCategoria());
	    		String nombreCategoria2 = obtenerNombreCategoria(p2.getIdCategoria());
	    		return nombreCategoria1.compareTo(nombreCategoria2);
	    	}
	    };

	    // Comparadores Descendentes
	    /**
	     * Comparador descendente para los identificadores de personal.
	     */
	    public static Comparator<PersonalDTO> ID_PERSONAL_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p2.getIdPersonal(), p1.getIdPersonal());
	        }
	    };

	    /**
	     * Comparador descendente para los nombres del personal.
	     */
	    public static Comparator<PersonalDTO> NOMBRE_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p2.getNombre().compareTo(p1.getNombre());
	        }
	    };

	    /**
	     * Comparador descendente para los apellidos del personal.
	     */
	    public static Comparator<PersonalDTO> APELLIDOS_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p2.getApellidos().compareTo(p1.getApellidos());
	        }
	    };

	    /**
	     * Comparador descendente para los identificadores de categoría del personal.
	     */
	    public static Comparator<PersonalDTO> ID_CATEGORIA_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p2.getIdCategoria(), p1.getIdCategoria());
	        }
	    };
	    
	    /**
         * Comparador descendente para los nombres de categoría del personal.
         */
	    public static Comparator<PersonalDTO> CATEGORIA_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            String nombreCategoria1 = obtenerNombreCategoria(p1.getIdCategoria());
	            String nombreCategoria2 = obtenerNombreCategoria(p2.getIdCategoria());
	            return nombreCategoria2.compareTo(nombreCategoria1);
	        }
	    };
	    
	    // Metodo para obtener nombres de categorias
	    /**
         * Método para obtener el nombre de una categoría a partir de su identificador.
         *
         * @param idCategoria El identificador de la categoría
         * @return El nombre de la categoría correspondiente, o "Desconocida" si no se encuentra
         */
	    private static String obtenerNombreCategoria(int idCategoria) {
	        CategoriaDAO Opcat = new CategoriaDAO();
	        for (CategoriaDTO categoria : Opcat.readAll()) {
	            if (categoria.getIdCategoria() == idCategoria) {
	                return categoria.getNombre();
	            }
	        }
	        return "Desconocida"; // Retorna "Desconocida" si la categoría no se encuentra
	    }
	}
}
