package it.jac.corsojava.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Timbro 
{
	private long idTimbro;
	private ZonedDateTime dataOra;
	private long idDipendente;
	private long idAzienda;
	
	private String utenteIns;
	private String utenteMod;
	private ZonedDateTime dataIns;
	private ZonedDateTime dataMod;
	public long getIdTimbro() {
		return idTimbro;
	}
	public void setIdTimbro(long idTimbro) {
		this.idTimbro = idTimbro;
	}
	public ZonedDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(ZonedDateTime dataOra) {
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
	public String getUtenteIns() {
		return utenteIns;
	}
	public void setUtenteIns(String utenteIns) {
		this.utenteIns = utenteIns;
	}
	public String getUtenteMod() {
		return utenteMod;
	}
	public void setUtenteMod(String utenteMod) {
		this.utenteMod = utenteMod;
	}
	public ZonedDateTime getDataIns() {
		return dataIns;
	}
	public void setDataIns(ZonedDateTime dataIns) {
		this.dataIns = dataIns;
	}
	public ZonedDateTime getDataMod() {
		return dataMod;
	}
	public void setDataMod(ZonedDateTime dataMod) {
		this.dataMod = dataMod;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataIns, dataMod, dataOra, idAzienda, idDipendente, idTimbro, utenteIns, utenteMod);
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
		return Objects.equals(dataIns, other.dataIns) && Objects.equals(dataMod, other.dataMod)
				&& Objects.equals(dataOra, other.dataOra) && idAzienda == other.idAzienda
				&& idDipendente == other.idDipendente && idTimbro == other.idTimbro
				&& Objects.equals(utenteIns, other.utenteIns) && Objects.equals(utenteMod, other.utenteMod);
	}
	@Override
	public String toString() {
		return "Timbro [idTimbro=" + idTimbro + ", dataOra=" + dataOra + ", idDipendente=" + idDipendente
				+ ", idAzienda=" + idAzienda + ", utenteIns=" + utenteIns + ", utenteMod=" + utenteMod + ", dataIns="
				+ dataIns + ", dataMod=" + dataMod + "]";
	}
	
	
}
