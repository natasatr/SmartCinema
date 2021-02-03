package org.unibl.etf.cinema.entity;
public class Sala {
	public int SalaID;
	public int Broj, Kapacitet;
	public int Uklonjeno;
	public Kino kino;
	public Sala(int salaID, int broj, int kapacitet, int uklonjeno, Kino kino) {
		super();
		SalaID = salaID;
		Broj = broj;
		Kapacitet = kapacitet;
		Uklonjeno = uklonjeno;
		this.kino = kino;
	}
	public Sala() {
		super();
	}
	public int getSalaID() {
		return SalaID;
	}
	public void setSalaID(int salaID) {
		SalaID = salaID;
	}
	public int getBroj() {
		return Broj;
	}
	public void setBroj(int broj) {
		Broj = broj;
	}
	public int getKapacitet() {
		return Kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		Kapacitet = kapacitet;
	}
	public int isUklonjeno() {
		return Uklonjeno;
	}
	public void setUklonjeno(int uklonjeno) {
		Uklonjeno = uklonjeno;
	}
	public Kino getKino() {
		return kino;
	}
	public void setKino(Kino kino) {
		this.kino = kino;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Broj;
		result = prime * result + Kapacitet;
		result = prime * result + SalaID;
		result = prime * result + Uklonjeno;
		result = prime * result + ((kino == null) ? 0 : kino.hashCode());
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
		Sala other = (Sala) obj;
		if (Broj != other.Broj)
			return false;
		if (Kapacitet != other.Kapacitet)
			return false;
		if (SalaID != other.SalaID)
			return false;
		if (Uklonjeno != other.Uklonjeno)
			return false;
		if (kino == null) {
			if (other.kino != null)
				return false;
		} else if (!kino.equals(other.kino))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sala [SalaID=" + SalaID + ", Broj=" + Broj + ", Kapacitet=" + Kapacitet + ", Uklonjeno=" + Uklonjeno
				+ ", kino=" + kino + "]";
	}
	
	
	
}
