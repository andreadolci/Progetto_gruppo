package it.jac.corsojava.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class BusinessUnit 
{
	private long idBuusinessUnit;
	private String area;
	private long idAzienda;
	
	private String utenteIns;
	private String utenteMod;
	private ZonedDateTime dataIns;
	private ZonedDateTime dataMod;
	
	public long getIdBuusinessUnit() {
		return idBuusinessUnit;
	}
	public void setIdBuusinessUnit(int idBuusinessUnit) {
		this.idBuusinessUnit = idBuusinessUnit;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	
	public long getIdAzienda() {
		return idAzienda;
	}
	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}
	public void setIdBuusinessUnit(long idBuusinessUnit) {
		this.idBuusinessUnit = idBuusinessUnit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(area, dataIns, dataMod, idAzienda, idBuusinessUnit, utenteIns, utenteMod);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessUnit other = (BusinessUnit) obj;
		return Objects.equals(area, other.area) && Objects.equals(dataIns, other.dataIns)
				&& Objects.equals(dataMod, other.dataMod) && idAzienda == other.idAzienda
				&& idBuusinessUnit == other.idBuusinessUnit && Objects.equals(utenteIns, other.utenteIns)
				&& Objects.equals(utenteMod, other.utenteMod);
	}
	@Override
	public String toString() {
		return "BusinessUnit [idBuusinessUnit=" + idBuusinessUnit + ", area=" + area + ", idAzienda=" + idAzienda
				+ ", utenteIns=" + utenteIns + ", utenteMod=" + utenteMod + ", dataIns=" + dataIns + ", dataMod="
				+ dataMod + "]";
	}
	
	
}
