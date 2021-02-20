package org.unibl.etf.cinema.data.dto;



public class SalaDTO {
	public int SalaID;
	public int Broj, Kapacitet;
	public KinoDTO kino;
	public SalaDTO(int salaID, int broj, int kapacitet, KinoDTO kino) {
		super();
		SalaID = salaID;
		Broj = broj;
		Kapacitet = kapacitet;
		this.kino = kino;
	}
	public SalaDTO() {
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
	public KinoDTO getKino() {
		return kino;
	}
	public void setKino(KinoDTO kino) {
		this.kino = kino;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Broj;
		result = prime * result + Kapacitet;
		result = prime * result + SalaID;
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
		SalaDTO other = (SalaDTO) obj;
		if (Broj != other.Broj)
			return false;
		if (Kapacitet != other.Kapacitet)
			return false;
		if (SalaID != other.SalaID)
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
		return "Sala " + Broj ;
	}
	
	
	
	

}
