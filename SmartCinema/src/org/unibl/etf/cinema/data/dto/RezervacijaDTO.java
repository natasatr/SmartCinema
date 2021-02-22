package org.unibl.etf.cinema.data.dto;

import java.sql.Timestamp;

public class RezervacijaDTO {
	private int rezervacijaID;
	private String ime;
	private String prezime;
	private Timestamp odDatuma;
	private Timestamp doDatuma;
	private KartaDTO karta;
	
	public RezervacijaDTO() {
		super();
	}
	
	public RezervacijaDTO(Timestamp odDatuma, Timestamp doDatuma,String ime, String prezime, KartaDTO karta) {
		this.odDatuma=odDatuma;
		this.doDatuma = doDatuma;
		this.ime = ime;
		this.prezime = prezime;
		this.karta = karta;
	}
	
	public RezervacijaDTO(int rezervacijaID, Timestamp odDatuma, Timestamp doDatuma, String ime, String prezime, KartaDTO karta) {
		this.rezervacijaID = rezervacijaID;
		this.odDatuma=odDatuma;
		this.doDatuma = doDatuma;
		this.ime = ime;
		this.prezime = prezime;
		this.karta = karta;
	}
	
	public int getRezervacijaID() {
		return rezervacijaID;
	}
	
	public void setRezervacijaID(int rezervacijaID) {
		this.rezervacijaID = rezervacijaID;
	}
	
	public Timestamp getOdDatuma() {
		return odDatuma;
	}
	
	public void setOdDatuma(Timestamp odDatuma) {
		this.odDatuma = odDatuma;
	}
	
	public Timestamp getDoDatuma() {
		return doDatuma;
	}
	
	public void setDoDatuma(Timestamp doDatuma) {
		this.doDatuma = doDatuma;
	}
	
	public KartaDTO getKarta() {
		return karta;
	}
	
	public void setKarta(KartaDTO karta) {
		this.karta = karta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		try {
		return  getRezervacijaID()+" "+getIme()+" "+getPrezime()+" "+getOdDatuma()+" "+getDoDatuma()+" "+getKarta().isProdano()+"\n";
		}catch(NullPointerException e) {return "rezervacija ne postoji!!!";}
		}
}
