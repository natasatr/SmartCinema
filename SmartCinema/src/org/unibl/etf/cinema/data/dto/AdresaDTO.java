package org.unibl.etf.cinema.data.dto;



public class AdresaDTO {

	public int AdresaID;
	public String Naziv;
	public int Broj;
	public int Uklonjeno;
	public AdresaDTO(int adresaID, String naziv, int broj, int uklonjeno) {
		super();
		AdresaID = adresaID;
		Naziv = naziv;
		Broj = broj;
		Uklonjeno = uklonjeno;
	}
	public AdresaDTO() {
		super();
	}
	public int getAdresaID() {
		return AdresaID;
	}
	public void setAdresaID(int adresaID) {
		AdresaID = adresaID;
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public int getBroj() {
		return Broj;
	}
	public void setBroj(int broj) {
		Broj = broj;
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
		result = prime * result + AdresaID;
		result = prime * result + Broj;
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		result = prime * result + Uklonjeno;
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
		AdresaDTO other = (AdresaDTO) obj;
		if (AdresaID != other.AdresaID)
			return false;
		if (Broj != other.Broj)
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		if (Uklonjeno != other.Uklonjeno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Adresa [AdresaID=" + AdresaID + ", Naziv=" + Naziv + ", Broj=" + Broj + ", Uklonjeno=" + Uklonjeno
				+ "]";
	}
	
	
}
