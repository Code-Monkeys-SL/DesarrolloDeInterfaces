package modeloBD_DTO;

import java.sql.Date;

public class FichajeDTO {
	private int idFichaje;
    private String accion;
    private Date fechaInicial;
    private Date fechaFinal;
    private int idPersonal;
    
	public FichajeDTO(int idFichaje, String accion, Date fechaInicial, Date fechaFinal, int idPersonal) {
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

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}
}
