package org.unibl.etf.cinema.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class Rola implements Serializable {
	private Integer rolaID;
	private String naziv;

	public Rola() {
		
	}
	
	public Rola(Integer rolaID, String naziv) {
		this.rolaID = rolaID;
		this.naziv = naziv;
	}

	public Integer getRolaID() {
		return rolaID;
	}

	public void setRolaID(Integer rolaID) {
		this.rolaID = rolaID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rolaID, naziv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rola))
			return false;
		Rola rola = (Rola) obj;
		return Objects.equals(rolaID, rola.rolaID);
	}

	@Override
	public String toString() {
		return "ID: " + rolaID + "\nNaziv: " + naziv + "\n";
	}
}
