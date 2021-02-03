package org.unibl.etf.cinema.entity;

public class Kino {
	public int KinoID;
	public String Naziv, Email, Telefon;
	public Adresa adresa;
	public int Uklonjeno;
	public Kino(int kinoID, String naziv, String email, String telefon, Adresa adresa, int uklonjeno) {
		super();
		KinoID = kinoID;
		Naziv = naziv;
		Email = email;
		Telefon = telefon;
		this.adresa = adresa;
		Uklonjeno = uklonjeno;
	}
	public Kino() {
		super();
	}
	public int getKinoID() {
		return KinoID;
	}
	public void setKinoID(int kinoID) {
		KinoID = kinoID;
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefon() {
		return Telefon;
	}
	public void setTelefon(String telefon) {
		Telefon = telefon;
	}
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	public int isUklonjeno() {
		return Uklonjeno;
	}
	public void setUklonjeno(int uklonjeno) {
		Uklonjeno = uklonjeno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + KinoID;
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		result = prime * result + ((Telefon == null) ? 0 : Telefon.hashCode());
		result = prime * result + Uklonjeno;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
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
		Kino other = (Kino) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (KinoID != other.KinoID)
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		if (Telefon == null) {
			if (other.Telefon != null)
				return false;
		} else if (!Telefon.equals(other.Telefon))
			return false;
		if (Uklonjeno != other.Uklonjeno)
			return false;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Kino [KinoID=" + KinoID + ", Naziv=" + Naziv + ", Email=" + Email + ", Telefon=" + Telefon + ", adresa="
				+ adresa + ", Uklonjeno=" + Uklonjeno + "]";
	}
	
	
}