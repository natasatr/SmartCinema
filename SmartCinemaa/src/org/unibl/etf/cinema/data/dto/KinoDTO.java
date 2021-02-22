package org.unibl.etf.cinema.data.dto;

import java.util.Objects;

public class KinoDTO {
	private Integer kinoID;
	private String naziv, email, telefon;
	private AdresaDTO adresa;

	public KinoDTO(String naziv, String email, String telefon, AdresaDTO adresa) {
		this.naziv = naziv;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
	}
	
	public KinoDTO(Integer kinoID, String naziv, String email, String telefon, AdresaDTO adresa) {
		super();
		this.kinoID = kinoID;
		this.naziv = naziv;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
	}

	public Integer getKinoID() {
		return kinoID;
	}

	public void setKinoID(Integer kinoID) {
		this.kinoID = kinoID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((kinoID == null) ? 0 : kinoID.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof KinoDTO))
			return false;
		KinoDTO kino = (KinoDTO) obj;
		return Objects.equals(kinoID, kino.kinoID);
	}
	
	@Override
	public String toString() {
		return "Kino [KinoID=" + kinoID + ", Naziv=" + naziv + ", Email=" + email + ", Telefon=" + telefon
				+ ", adresa=" + adresa + "]";
	}
}
