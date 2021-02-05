package org.unibl.etf.cinema.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class Administrator extends Zaposleni implements Serializable {
	public Administrator() {

	}

	public Administrator(String jmb, String ime, String prezime, Double plata, String email, AdresaDTO adresa, Nalog nalog,
			BankovniRacun bankovniRacun, Banka banka) {
		super(jmb, ime, prezime, plata, email, adresa, nalog, bankovniRacun, banka);
	}

	public Administrator(Integer zaposleniID, String jmb, String ime, String prezime, Double plata, String email,
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
		if (!(obj instanceof Administrator))
			return false;
		Administrator zaposleni = (Administrator) obj;
		return Objects.equals(zaposleniID, zaposleni.zaposleniID);
	}

	@Override
	public String toString() {
		return "ID: " + zaposleniID + "\nJMB: " +jmb + "\n";
	}
}
