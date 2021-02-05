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
	protected BankovniRacun bankovniRacun;
	protected Banka banka;

	public Zaposleni() {

	}

	public Zaposleni(String jmb, String ime, String prezime, Double plata, String email, AdresaDTO adresa, Nalog nalog,
			BankovniRacun bankovniRacun, Banka banka) {
		this.jmb = jmb;
		this.ime = ime;
		this.prezime = prezime;
		this.plata = plata;
		this.email = email;
		this.adresa = adresa;
		this.nalog = nalog;
		this.bankovniRacun = bankovniRacun;
		this.banka = banka;
	}

	public Zaposleni(Integer zaposleniID, String jmb, String ime, String prezime, Double plata, String email,
			AdresaDTO adresa, Nalog nalog, BankovniRacun bankovniRacun, Banka banka) {
		this.zaposleniID = zaposleniID;
		this.jmb = jmb;
		this.ime = ime;
		this.prezime = prezime;
		this.plata = plata;
		this.email = email;
		this.adresa = adresa;
		this.nalog = nalog;
		this.bankovniRacun = bankovniRacun;
		this.banka = banka;
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
		return "ID: " + zaposleniID + "\nJMB: " +jmb + "\n";
	}

}
