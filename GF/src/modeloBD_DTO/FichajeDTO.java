package modeloBD_DTO;

import java.sql.Timestamp;
import java.util.Comparator;

public class FichajeDTO implements Comparable <FichajeDTO> {
	private int idFichaje;
    private String accion;
    private Timestamp fechaInicial;
    private Timestamp fechaFinal;
    private int idPersonal;
    
	public FichajeDTO(int idFichaje, String accion, Timestamp fechaInicial, Timestamp fechaFinal, int idPersonal) {
		super();
		this.idFichaje = idFichaje;
		this.accion = accion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idPersonal = idPersonal;
	}

	public int getIdFichaje() {
		return idFichaje;
	}

	public void setIdFichaje(int idFichaje) {
		this.idFichaje = idFichaje;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

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
	
	public static class Comparadores {
	    // Comparadores Ascendentes
	    public static Comparator<FichajeDTO> ACCION_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getAccion().compareTo(f2.getAccion());
	        }
	    };

	    public static Comparator<FichajeDTO> FECHA_INICIAL_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getFechaInicial().compareTo(f2.getFechaInicial());
	        }
	    };

	    public static Comparator<FichajeDTO> FECHA_FINAL_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f1.getFechaFinal().compareTo(f2.getFechaFinal());
	        }
	    };
	    
	    public static Comparator<FichajeDTO> DIFERENCIA_HORAS_ASC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            long diff1 = calcularDiferenciaHoras(f1);
	            long diff2 = calcularDiferenciaHoras(f2);
	            return Long.compare(diff1, diff2);
	        }
	    };

	    // Comparadores Descendentes
	    public static Comparator<FichajeDTO> ACCION_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getAccion().compareTo(f1.getAccion());
	        }
	    };

	    public static Comparator<FichajeDTO> FECHA_INICIAL_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getFechaInicial().compareTo(f1.getFechaInicial());
	        }
	    };

	    public static Comparator<FichajeDTO> FECHA_FINAL_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            return f2.getFechaFinal().compareTo(f1.getFechaFinal());
	        }
	    };

	    public static Comparator<FichajeDTO> DIFERENCIA_HORAS_DESC = new Comparator<FichajeDTO>() {
	        @Override
	        public int compare(FichajeDTO f1, FichajeDTO f2) {
	            long diff1 = calcularDiferenciaHoras(f1);
	            long diff2 = calcularDiferenciaHoras(f2);
	            return Long.compare(diff2, diff1);
	        }
	    };
	    
	    // Metodo para calcular la diferencia de horas
	    private static long calcularDiferenciaHoras(FichajeDTO ficha) {
	        Timestamp fechaInicial = ficha.getFechaInicial();
	        Timestamp fechaFinal = ficha.getFechaFinal();
	        
	        if (fechaInicial == null || fechaFinal == null) {
	            return Long.MAX_VALUE;
	        }
	        
	        long diferenciaMilisegundos = fechaFinal.getTime() - fechaInicial.getTime();
	        return diferenciaMilisegundos / (1000 * 60 * 60);
	    }
	}

}
