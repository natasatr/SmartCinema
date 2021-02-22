package org.unibl.etf.cinema.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class Zaposleni implements Serializable {
	protected Integer zaposleniID;
	protected String jmb;
	protected String ime;
	protected String prezime;
	protected Double plata;
	protected String email;
	protected AdresaDTO adresa;
	protected Nalog nalog;

	public Zaposleni() {

	}

	public Zaposleni(String jmb, String ime, String prezime, Double plata, String email, AdresaDTO adresa,
			Nalog nalog) {
		this.jmb = jmb;
		this.ime = ime;
		this.prezime = prezime;
		this.plata = plata;
		this.email = email;
		this.adresa = adresa;
		this.nalog = nalog;
	}

	public Zaposleni(Integer zaposleniID, String jmb, String ime, String prezime, Double plata, String email,
			AdresaDTO adresa, Nalog nalog) {
		this.zaposleniID = zaposleniID;
		this.jmb = jmb;
		this.ime = ime;
		this.prezime = prezime;
		this.plata = plata;
		this.email = email;
		this.adresa = adresa;
		this.nalog = nalog;
	}

	public Integer getZaposleniID() {
		return zaposleniID;
	}

	public void setZaposleniID(Integer zaposleniID) {
		this.zaposleniID = zaposleniID;
	}

	public String getJmb() {
		return jmb;
	}

	public void setJmb(String jmb) {
		this.jmb = jmb;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Double getPlata() {
		return plata;
	}

	public void setPlata(Double plata) {
		this.plata = plata;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	public Nalog getNalog() {
		return nalog;
	}

	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}

	@Override
	public int hashCode() {
		return Objects.hash(zaposleniID, jmb);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Zaposleni))
			return false;
		Zaposleni zaposleni = (Zaposleni) obj;
		return Objects.equals(zaposleniID, zaposleni.zaposleniID);
	}

	@Override
	public String toString() {
		return "ID: " + zaposleniID + "\nJMB: " + jmb + "\n";
	}

}
