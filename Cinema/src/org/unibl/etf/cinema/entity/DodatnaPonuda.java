package org.unibl.etf.cinema.entity;

public class DodatnaPonuda {
	public int DodatnaPonudaID;
	public String Naziv;
	public double Cijena;
	public DodatnaPonuda(int dodatnaPonudaID, String naziv, double cijena) {
		super();
		DodatnaPonudaID = dodatnaPonudaID;
		Naziv = naziv;
		Cijena = cijena;
	}
	public DodatnaPonuda() {
		super();
	}
	public int getDodatnaPonudaID() {
		return DodatnaPonudaID;
	}
	public void setDodatnaPonudaID(int dodatnaPonudaID) {
		DodatnaPonudaID = dodatnaPonudaID;
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public double getCijena() {
		return Cijena;
	}
	public void setCijena(double cijena) {
		Cijena = cijena;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Cijena);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + DodatnaPonudaID;
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
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
		DodatnaPonuda other = (DodatnaPonuda) obj;
		if (Double.doubleToLongBits(Cijena) != Double.doubleToLongBits(other.Cijena))
			return false;
		if (DodatnaPonudaID != other.DodatnaPonudaID)
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DodatnaPonuda [DodatnaPonudaID=" + DodatnaPonudaID + ", Naziv=" + Naziv + ", Cijena=" + Cijena + "]";
	}
	
	
	
	
	

}
