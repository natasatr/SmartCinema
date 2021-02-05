package org.unibl.etf.cinema.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class Sluzbenik extends Zaposleni implements Serializable {
	public Sluzbenik() {

	}

	public Sluzbenik(String jmb, String ime, String prezime, Double plata, String email, AdresaDTO adresa, Nalog nalog,
			BankovniRacun bankovniRacun, Banka banka) {
		super(jmb, ime, prezime, plata, email, adresa, nalog, bankovniRacun, banka);
	}

	public Sluzbenik(Integer zaposleniID, String jmb, String ime, String prezime, Double plata, String email,
			AdresaDTO adresa, Nalog nalog, BankovniRacun bankovniRacun, Banka banka) {
		super(zaposleniID, jmb, ime, prezime, plata, email, adresa, nalog, bankovniRacun, banka);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Sluzbenik))
			return false;
		Sluzbenik zaposleni = (Sluzbenik) obj;
		return Objects.equals(zaposleniID, zaposleni.zaposleniID);
	}

	@Override
	public String toString() {
		return "ID: " + zaposleniID + "\nJMB: " +jmb + "\n";
	}
}
