package modeloBD_DTO;

import java.sql.Timestamp;

public class FichajeDTO {
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
}
