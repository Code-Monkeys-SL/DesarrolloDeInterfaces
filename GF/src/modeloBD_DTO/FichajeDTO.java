package modeloBD_DTO;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Clase que representa un fichaje de un personal de la cafeteria.
 */
public class FichajeDTO implements Comparable <FichajeDTO> {
	private int idFichaje; // Identificador único del fichaje
    private String accion; // Acción registrada en el fichaje (ejemplo: Trabajo, Baja o Vacaciones)
    private Timestamp fechaInicial; // Marca de tiempo del inicio del fichaje
    private Timestamp fechaFinal; // Marca de tiempo del final del fichaje
    private int idPersonal; // Identificador del personal correspondiente
    
    /**
     * Constructor que inicializa una nueva instancia de FichajeDTO.
     * 
     * @param idFichaje Identificador único del fichaje
     * @param accion Acción realizada en el fichaje
     * @param fechaInicial Marca de tiempo del inicio del fichaje
     * @param fechaFinal Marca de tiempo del final del fichaje
     * @param idPersonal Identificador del personal asociado
     */ 
	public FichajeDTO(int idFichaje, String accion, Timestamp fechaInicial, Timestamp fechaFinal, int idPersonal) {
		super();
		this.idFichaje = idFichaje;
		this.accion = accion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idPersonal = idPersonal;
	}

	/**
	 * Obtiene el identificador del fichaje.
	 * 
	 * @return El identificador del fichaje
     */
	public int getIdFichaje() {
		return idFichaje;
	}

	/**
	 * Establece el identificador del fichaje.
	 * 
	 * @param idFichaje El identificador del fichaje a establecer
	 */
	public void setIdFichaje(int idFichaje) {
		this.idFichaje = idFichaje;
	}

	/**
	 * Obtiene la acción registrada en el fichaje.
	 * 
	 * @return La acción del fichaje 
     */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece la acción registrada en el fichaje.
	 * 
	 * @param accion La acción del fichaje a establecer
     */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene la fecha inicial del fichaje.
	 * 
	 * @return La fecha inicial del fichaje
     */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Establece la fecha inicial del fichaje.
	 * 
	 * @param fechaInicial La fecha inicial del fichaje a establecer 
     */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Obtiene la fecha final del fichaje.
	 * 
	 * @return La fecha final del fichaje
     */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Establece la fecha final del fichaje.
	 * 
	 * @param fechaFinal La fecha final del fichaje a establecer
     */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Obtiene el identificador del personal asociado al fichaje.
	 * 
	 * @return El identificador del personal
     */
	public int getIdPersonal() {
		return idPersonal;
	}

	/**
	 * Establece el identificador del personal asociado al fichaje.
	 * 
	 * @param idPersonal El identificador del personal a establecer
     */
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	/**
	 * Compara este fichaje con otro fichaje basado en su identificador.
	 * 
	 * @param f FichajeDTO a comparar
	 * @return 0 si son iguales, un valor negativo si este fichaje es menor, y un valor positivo si es mayor
     */
	@Override
	public int compareTo(FichajeDTO f) {
		if (this == f)
			return 0;
		if (f == null)
			return 1;
		if (getClass() != f.getClass())
			return 1;
		
		if (this.idFichaje == 0) {
			if (f.idFichaje != 0) return -1;
				else return 0;
		} else
			return Integer.compare(this.idFichaje, f.idFichaje);
	}
	
	/**
	 * Clase interna que contiene comparadores para FichajeDTO.
     */
	public static class Comparadores {
	    // Comparadores Ascendentes
		/**
         * Comparador ascendente para las acciones de fichaje.
         */
	    public static Comparator<FichajeDTO> ACCION_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getAccion().compareTo(f2.getAccion());
	        }
	    };

	    /**
         * Comparador ascendente para las fechas iniciales de fichaje.
         */
	    public static Comparator<FichajeDTO> FECHA_INICIAL_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getFechaInicial().compareTo(f2.getFechaInicial());
	        }
	    };

	    /**
         * Comparador ascendente para las fechas finales de fichaje.
         */
	    public static Comparator<FichajeDTO> FECHA_FINAL_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getFechaFinal().compareTo(f2.getFechaFinal());
	        }
	    };
	    
	    /**
         * Comparador ascendente para la diferencia de horas entre las fechas del fichaje.
         */
	    public static Comparator<FichajeDTO> DIFERENCIA_HORAS_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            long diff1 = calcularDiferenciaHoras(f1);
	            long diff2 = calcularDiferenciaHoras(f2);
	            return Long.compare(diff1, diff2);
	        }
	    };

	    // Comparadores Descendentes
	    /**
         * Comparador descendente para las acciones de fichaje.
         */
	    public static Comparator<FichajeDTO> ACCION_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getAccion().compareTo(f1.getAccion());
	        }
	    };

	    /**
         * Comparador descendente para las fechas iniciales de fichaje.
         */
	    public static Comparator<FichajeDTO> FECHA_INICIAL_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getFechaInicial().compareTo(f1.getFechaInicial());
	        }
	    };

	    /**
         * Comparador descendente para las fechas finales de fichaje.
         */
	    public static Comparator<FichajeDTO> FECHA_FINAL_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getFechaFinal().compareTo(f1.getFechaFinal());
	        }
	    };

	    /**
         * Comparador descendente para la diferencia de horas entre las fechas del fichaje.
         */
	    public static Comparator<FichajeDTO> DIFERENCIA_HORAS_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            long diff1 = calcularDiferenciaHoras(f1);
	            long diff2 = calcularDiferenciaHoras(f2);
	            return Long.compare(diff2, diff1);
	        }
	    };
	    
	    // Metodo para calcular la diferencia de horas
	    /**
         * Calcula la diferencia de horas entre las fechas inicial y final del fichaje.
         *
         * @param ficha FichajeDTO del que se calculará la diferencia de horas
         * @return La diferencia de horas entre las fechas inicial y final
         */
	    private static long calcularDiferenciaHoras(FichajeDTO ficha) {
	        Timestamp fechaInicial = ficha.getFechaInicial();
	        Timestamp fechaFinal = ficha.getFechaFinal();
	        
	        if (fechaInicial == null || fechaFinal == null) {
	            return Long.MAX_VALUE; // Retorna un valor máximo si las fechas son nulas
	        }
	        
	        long diferenciaMilisegundos = fechaFinal.getTime() - fechaInicial.getTime();
	        return diferenciaMilisegundos / (1000 * 60 * 60); // Convierte milisegundos a horas
	    }
	}

}
