package org.unibl.etf.cinema.entity;

public class Posjetilac {
	public int PosjetilacID;
	public String Ime, Prezime;
	public Posjetilac(int posjetilacID, String ime, String prezime) {
		super();
		PosjetilacID = posjetilacID;
		Ime = ime;
		Prezime = prezime;
	}
	public Posjetilac() {
		super();
	}
	public int getPosjetilacID() {
		return PosjetilacID;
	}
	public void setPosjetilacID(int posjetilacID) {
		PosjetilacID = posjetilacID;
	}
	public String getIme() {
		return Ime;
	}
	public void setIme(String ime) {
		Ime = ime;
	}
	public String getPrezime() {
		return Prezime;
	}
	public void setPrezime(String prezime) {
		Prezime = prezime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Ime == null) ? 0 : Ime.hashCode());
		result = prime * result + PosjetilacID;
		result = prime * result + ((Prezime == null) ? 0 : Prezime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posjetilac other = (Posjetilac) obj;
		if (Ime == null) {
			if (other.Ime != null)
				return false;
		} else if (!Ime.equals(other.Ime))
			return false;
		if (PosjetilacID != other.PosjetilacID)
			return false;
		if (Prezime == null) {
			if (other.Prezime != null)
				return false;
		} else if (!Prezime.equals(other.Prezime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Posjetilac [PosjetilacID=" + PosjetilacID + ", Ime=" + Ime + ", Prezime=" + Prezime + "]";
	}

	
	
	
}
