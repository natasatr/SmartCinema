package org.unibl.etf.cinema.data.dto;

import java.util.Objects;

public class AdresaDTO {

	private Integer adresaID;
	private String mjesto;
	private String ulica;
	private Integer broj;
	
	public AdresaDTO(String mjesto, String ulica, Integer broj) {
		this.mjesto = mjesto;
		this.ulica = ulica;
		this.broj = broj;
	}
	

	public AdresaDTO(Integer adresaID, String mjesto, String ulica, Integer broj) {
		this.adresaID = adresaID;
		this.mjesto = mjesto;
		this.ulica = ulica;
		this.broj = broj;
	}

	public Integer getAdresaID() {
		return adresaID;
	}

	public void setAdresaID(Integer adresaID) {
		this.adresaID = adresaID;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public Integer getBroj() {
		return broj;
	}

	public void setBroj(Integer broj) {
		this.broj = broj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresaID == null) ? 0 : adresaID.hashCode());
		result = prime * result + ((broj == null) ? 0 : broj.hashCode());
		result = prime * result + ((mjesto == null) ? 0 : mjesto.hashCode());
		result = prime * result + ((ulica == null) ? 0 : ulica.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AdresaDTO))
			return false;
		AdresaDTO adresa = (AdresaDTO) obj;
		return Objects.equals(adresaID, adresa.adresaID);
	}

	@Override
	public String toString() {
		return "Adresa [AdresaID=" + adresaID + ", Mjesto=" + mjesto + ", Ulica=" + ulica + ", Broj=" + broj + "]";
	}	
}
