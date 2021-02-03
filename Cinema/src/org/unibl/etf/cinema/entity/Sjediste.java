package org.unibl.etf.cinema.entity;

public class Sjediste{
	public int SjedisteID;
	public int broj;
	public int Red;
	public int Zauzeto, Uklonjeno;
	public Sala sala;
	public VrstaSjedista vrstaSjedista;
	public Sjediste(int sjedisteID, int broj, int red, int zauzeto, int uklonjeno, Sala sala,
			VrstaSjedista vrstaSjedista) {
		super();
		SjedisteID = sjedisteID;
		this.broj = broj;
		Red = red;
		Zauzeto = zauzeto;
		Uklonjeno = uklonjeno;
		this.sala = sala;
		this.vrstaSjedista = vrstaSjedista;
	}
	public Sjediste() {
		super();
	}
	public int getSjedisteID() {
		return SjedisteID;
	}
	public void setSjedisteID(int sjedisteID) {
		SjedisteID = sjedisteID;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public int getRed() {
		return Red;
	}
	public void setRed(int red) {
		Red = red;
	}
	public int isZauzeto() {
		return Zauzeto;
	}
	public void setZauzeto(int zauzeto) {
		Zauzeto = zauzeto;
	}
	public int isUklonjeno() {
		return Uklonjeno;
	}
	public void setUklonjeno(int uklonjeno) {
		Uklonjeno = uklonjeno;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public VrstaSjedista getVrstaSjedista() {
		return vrstaSjedista;
	}
	public void setVrstaSjedista(VrstaSjedista vrstaSjedista) {
		this.vrstaSjedista = vrstaSjedista;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Red;
		result = prime * result + SjedisteID;
		result = prime * result + Uklonjeno;
		result = prime * result + Zauzeto;
		result = prime * result + broj;
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((vrstaSjedista == null) ? 0 : vrstaSjedista.hashCode());
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
		Sjediste other = (Sjediste) obj;
		if (Red != other.Red)
			return false;
		if (SjedisteID != other.SjedisteID)
			return false;
		if (Uklonjeno != other.Uklonjeno)
			return false;
		if (Zauzeto != other.Zauzeto)
			return false;
		if (broj != other.broj)
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (vrstaSjedista == null) {
			if (other.vrstaSjedista != null)
				return false;
		} else if (!vrstaSjedista.equals(other.vrstaSjedista))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sjediste [SjedisteID=" + SjedisteID + ", broj=" + broj + ", Red=" + Red + ", Zauzeto=" + Zauzeto
				+ ", Uklonjeno=" + Uklonjeno + ", sala=" + sala + ", vrstaSjedista=" + vrstaSjedista + "]";
	}
	
	
	
	

}
