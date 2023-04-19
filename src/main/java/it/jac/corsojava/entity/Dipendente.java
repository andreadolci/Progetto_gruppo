package it.jac.corsojava.entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Dipendente 
{
	private long idDipendente;
	private String sesso;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private LocalDate dataNascita;
	private long idAzienda;
	private long idBusinessUnit;
	
	private String utenteIns;
	private String utenteMod;
	private ZonedDateTime dataIns;
	private ZonedDateTime dataMod;
	public long getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(long idDipendente) {
		this.idDipendente = idDipendente;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public long getIdAzienda() {
		return idAzienda;
	}
	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}
	public long getIdBusinessUnit() {
		return idBusinessUnit;
	}
	public void setIdBusinessUnit(long idBusinessUnit) {
		this.idBusinessUnit = idBusinessUnit;
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
		return Objects.hash(cognome, dataIns, dataMod, dataNascita, idAzienda, idBusinessUnit, idDipendente, nome,
				sesso, utenteIns, utenteMod);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataIns, other.dataIns)
				&& Objects.equals(dataMod, other.dataMod) && Objects.equals(dataNascita, other.dataNascita)
				&& idAzienda == other.idAzienda && idBusinessUnit == other.idBusinessUnit
				&& idDipendente == other.idDipendente && Objects.equals(nome, other.nome)
				&& Objects.equals(sesso, other.sesso) && Objects.equals(utenteIns, other.utenteIns)
				&& Objects.equals(utenteMod, other.utenteMod);
	}
	@Override
	public String toString() {
		return "Dipendente [idDipendente=" + idDipendente + ", sesso=" + sesso + ", nome=" + nome + ", cognome="
				+ cognome + ", dataNascita=" + dataNascita + ", idAzienda=" + idAzienda + ", idBusinessUnit="
				+ idBusinessUnit + ", utenteIns=" + utenteIns + ", utenteMod=" + utenteMod + ", dataIns=" + dataIns
				+ ", dataMod=" + dataMod + "]";
	}
	
	
}
