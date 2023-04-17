package it.jac.corsojava.entity;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Timbro 
{
	private long idTimbro;
	private LocalDateTime dataOra;
	private String tipo;
	private long idDipendente;
	private long idAzienda;
	
	public long getIdTimbro() {
		return idTimbro;
	}
	public void setIdTimbro(long idTimbro) {
		this.idTimbro = idTimbro;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public long getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(long idDipendente) {
		this.idDipendente = idDipendente;
	}
	public long getIdAzienda() {
		return idAzienda;
	}
	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataOra, idAzienda, idDipendente, idTimbro, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timbro other = (Timbro) obj;
		return Objects.equals(dataOra, other.dataOra) && idAzienda == other.idAzienda
				&& idDipendente == other.idDipendente && idTimbro == other.idTimbro && Objects.equals(tipo, other.tipo);
	}
	@Override
	public String toString() {
		return "Timbro [idTimbro=" + idTimbro + ", dataOra=" + dataOra + ", tipo=" + tipo + ", idDipendente="
				+ idDipendente + ", idAzienda=" + idAzienda + "]";
	}
	
	
	
}
