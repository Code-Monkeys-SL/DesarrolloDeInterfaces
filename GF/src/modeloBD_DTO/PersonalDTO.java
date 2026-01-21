package modeloBD_DTO;

import java.util.Comparator;

import modeloBD_DAO.CategoriaDAO;

public class PersonalDTO implements Comparable <PersonalDTO> {
	private int idPersonal;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contrasenia;
    private boolean admin;
    private int idCategoria;
    
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

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

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
	
	public static class Comparadores {
	    // Comparadores Ascendentes
	    public static Comparator<PersonalDTO> ID_PERSONAL_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p1.getIdPersonal(), p2.getIdPersonal());
	        }
	    };

	    public static Comparator<PersonalDTO> NOMBRE_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p1.getNombre().compareTo(p2.getNombre());
	        }
	    };

	    public static Comparator<PersonalDTO> APELLIDOS_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p1.getApellidos().compareTo(p2.getApellidos());
	        }
	    };

	    public static Comparator<PersonalDTO> ID_CATEGORIA_ASC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p1.getIdCategoria(), p2.getIdCategoria());
	        }
	    };

	    public static Comparator<PersonalDTO> CATEGORIA_ASC = new Comparator<PersonalDTO>() {
	    	@Override
	    	public int compare(PersonalDTO p1, PersonalDTO p2) {
	    		String nombreCategoria1 = obtenerNombreCategoria(p1.getIdCategoria());
	    		String nombreCategoria2 = obtenerNombreCategoria(p2.getIdCategoria());
	    		return nombreCategoria1.compareTo(nombreCategoria2);
	    	}
	    };

	    // Comparadores Descendentes
	    public static Comparator<PersonalDTO> ID_PERSONAL_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p2.getIdPersonal(), p1.getIdPersonal());
	        }
	    };

	    public static Comparator<PersonalDTO> NOMBRE_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p2.getNombre().compareTo(p1.getNombre());
	        }
	    };

	    public static Comparator<PersonalDTO> APELLIDOS_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return p2.getApellidos().compareTo(p1.getApellidos());
	        }
	    };

	    public static Comparator<PersonalDTO> ID_CATEGORIA_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            return Integer.compare(p2.getIdCategoria(), p1.getIdCategoria());
	        }
	    };
	    

	    public static Comparator<PersonalDTO> CATEGORIA_DESC = new Comparator<PersonalDTO>() {
	        @Override
	        public int compare(PersonalDTO p1, PersonalDTO p2) {
	            String nombreCategoria1 = obtenerNombreCategoria(p1.getIdCategoria());
	            String nombreCategoria2 = obtenerNombreCategoria(p2.getIdCategoria());
	            return nombreCategoria2.compareTo(nombreCategoria1);
	        }
	    };
	    
	    // Metodo para obtener nombres de categorias
	    private static String obtenerNombreCategoria(int idCategoria) {
	        CategoriaDAO Opcat = new CategoriaDAO();
	        for (CategoriaDTO categoria : Opcat.readAll()) {
	            if (categoria.getIdCategoria() == idCategoria) {
	                return categoria.getNombre();
	            }
	        }
	        return "Desconocida";
	    }
	}
}
