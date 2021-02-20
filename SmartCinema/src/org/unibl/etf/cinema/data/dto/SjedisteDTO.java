package org.unibl.etf.cinema.data.dto;



public class SjedisteDTO {
	public int SjedisteID;
	public int broj;
	public int Red;
	public int Zauzeto;
	public SalaDTO sala;
	public VrstaSjedistaDTO vrstaSjedista;
	public SjedisteDTO(int sjedisteID, int broj, int red, int zauzeto, SalaDTO sala, VrstaSjedistaDTO vrstaSjedista) {
		super();
		SjedisteID = sjedisteID;
		this.broj = broj;
		Red = red;
		Zauzeto = zauzeto;
		this.sala = sala;
		this.vrstaSjedista = vrstaSjedista;
	}
	public SjedisteDTO() {
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
	public SalaDTO getSala() {
		return sala;
	}
	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}
	public VrstaSjedistaDTO getVrstaSjedista() {
		return vrstaSjedista;
	}
	public void setVrstaSjedista(VrstaSjedistaDTO vrstaSjedista) {
		this.vrstaSjedista = vrstaSjedista;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Red;
		result = prime * result + SjedisteID;
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
		SjedisteDTO other = (SjedisteDTO) obj;
		if (Red != other.Red)
			return false;
		if (SjedisteID != other.SjedisteID)
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
		return "SjedisteDTO [SjedisteID=" + SjedisteID + ", broj=" + broj + ", Red=" + Red + ", Zauzeto=" + Zauzeto
				+ ", sala=" + sala + ", vrstaSjedista=" + vrstaSjedista + "]";
	}
	
	
	
	

}
