package org.unibl.etf.cinema.data.dto;

import java.util.Date;

public class FilmDTO {
	private int FilmID;
	private String Naziv;
	private String Trajanje;
	private int GodinaSnimanja;
	private String Reziser;
	private String opis;
	private String URepetoaru; //u repertoaru
	private String DatumPrvogPrikazivanja;
	private String Glumci;
	private String Zanr;
	public int getFilmID() {
		return FilmID;
	}
	public void setFilmID(int filmID) {
		FilmID = filmID;
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public String getTrajanje() {
		return Trajanje;
	}
	public void setTrajanje(String trajanje) {
		Trajanje = trajanje;
	}
	public int getGodinaSnimanja() {
		return GodinaSnimanja;
	}
	public void setGodinaSnimanja(int godinaSnimanja) {
		GodinaSnimanja = godinaSnimanja;
	}
	public String getReziser() {
		return Reziser;
	}
	public void setReziser(String reziser) {
		Reziser = reziser;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String isURepetoaru() {
		return URepetoaru;
	}
	public void setURepetoaru(String uRepertoaru) {
		URepetoaru = uRepertoaru;
	}
	public String getDatumPrvogPrikazivanja() {
		return DatumPrvogPrikazivanja;
	}
	public void setDatumPrvogPrikazivanja(String datumPrvogPrikazivanja) {
		DatumPrvogPrikazivanja = datumPrvogPrikazivanja;
	}

	public String getGlumci() {
		return Glumci;
	}
	public void setGlumci(String glumci) {
		Glumci = glumci;
	}
	public String getZanr() {
		return Zanr;
	}
	public void setZanr(String zanr) {
		Zanr = zanr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + FilmID;
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
		FilmDTO other = (FilmDTO) obj;
		if (FilmID != other.FilmID)
			return false;
		return true;
	}
	public FilmDTO() {
		super();
	}
	public FilmDTO(int filmID, String naziv, String trajanje, int godinaSnimanja, String reziser, String opis,
			String uRepertoaru, String datumPrvogPrikazivanja, String glumci, String zanr) {
		super();
		FilmID = filmID;
		Naziv = naziv;
		Trajanje = trajanje;
		GodinaSnimanja = godinaSnimanja;
		Reziser = reziser;
		this.opis = opis;
		URepetoaru = uRepertoaru;
		DatumPrvogPrikazivanja = datumPrvogPrikazivanja;
		Glumci = glumci;
		Zanr = zanr;
	}
	@Override
	public String toString() {
		return "FilmDTO [FilmID=" + FilmID + ", Naziv=" + Naziv + ", Trajanje=" + Trajanje + ", GodinaSnimanja="
				+ GodinaSnimanja + ", Reziser=" + Reziser + ", opis=" + opis + ", URepertoaru=" + URepetoaru
				+ ", DatumPrvogPrikazivanja=" + DatumPrvogPrikazivanja + ", Glumci="
				+ Glumci + ", Zanr=" + Zanr + "]";
	}
	
	

	

}