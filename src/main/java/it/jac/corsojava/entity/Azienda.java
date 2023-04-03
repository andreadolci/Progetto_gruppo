package it.jac.corsojava.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Azienda {

	private long idAzienda;
	private String ragionesociale;
	private String partitaIva;
	private String settore;
	private String email;

	private String utenteIns;
	private String utenteMod;
	private ZonedDateTime dataIns;
	private ZonedDateTime dataMod;

	

	public long getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}

	public String getRagionesociale() {
		return ragionesociale;
	}

	public void setRagionesociale(String ragionesociale) {
		this.ragionesociale = ragionesociale;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return Objects.hash(dataIns, dataMod, email, idAzienda, partitaIva, ragionesociale, settore, utenteIns,
				utenteMod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Azienda other = (Azienda) obj;
		return Objects.equals(dataIns, other.dataIns) && Objects.equals(dataMod, other.dataMod)
				&& Objects.equals(email, other.email) && idAzienda == other.idAzienda
				&& Objects.equals(partitaIva, other.partitaIva) && Objects.equals(ragionesociale, other.ragionesociale)
				&& Objects.equals(settore, other.settore) && Objects.equals(utenteIns, other.utenteIns)
				&& Objects.equals(utenteMod, other.utenteMod);
	}

	@Override
	public String toString() {
		return "User [idAzienda=" + idAzienda + ", ragionesociale=" + ragionesociale + ", partitaIva=" + partitaIva
				+ ", settore=" + settore + ", email=" + email + ", utenteIns=" + utenteIns + ", utenteMod=" + utenteMod
				+ ", dataIns=" + dataIns + ", dataMod=" + dataMod + "]";
	}

}
