package org.unibl.etf.cinema.data.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.sql.Date;

public class KartaDTO {
	private int KartaID;
	private double cijena;
	private Date vrijemeKupovine;
	private boolean prodano;
	private SjedisteDTO sjediste;
	private Zaposleni zaposleni;
	private PrikazivanjeFilmaUSaliDTO pfus;
	
	public KartaDTO() {
		super();
	}
	
	public KartaDTO(double cijena, Date vrijemeKupovine, boolean prodano, SjedisteDTO sjediste, Zaposleni zaposleni, PrikazivanjeFilmaUSaliDTO pfus) {
	//	this.KartaID = KartaID;
		this.cijena = cijena;
		this.setSjediste(sjediste);
		this.vrijemeKupovine = vrijemeKupovine;
		this.prodano = prodano;
		
		this.zaposleni = zaposleni;
		this.setPfus(pfus);
	}
	
	public KartaDTO(int KartaID, double cijena, Date vrijemeKupovine, boolean prodano, SjedisteDTO sjediste, Zaposleni zaposleni, PrikazivanjeFilmaUSaliDTO pfus) {
		this.KartaID = KartaID;
		this.cijena = cijena;
		this.setSjediste(sjediste);
		this.vrijemeKupovine = vrijemeKupovine;
		this.prodano = prodano;
		this.zaposleni = zaposleni;
		this.setPfus(pfus);
	}
	
	public int getKartaID() {
		return KartaID;
	}
	
	public void setKartaID(int kartaID) {
		KartaID = kartaID;
	}
	
	public double getCijena() {
		return cijena;
	}
	
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	

	public Date getVrijemeKupovine() {
		return vrijemeKupovine;
	}
	
	public void setVrijemeKupovine(Date vrijemeKupovine) {
		this.vrijemeKupovine = vrijemeKupovine;
	}
	
	public boolean isProdano() {
		return prodano;
	}
	
	public void setProdano(boolean prodano) {
		this.prodano = prodano;
	}
	
	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}
	
	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	@Override
	public String toString() {
		return "ID KARTE: " + getKartaID()+" PRODANA? "+isProdano()+"\n"+getPfus().getSala()+"\n"+"  Cijena: "+getCijena() +"nesto jos malo\n"+ getZaposleni().toString(); 
	}

	public PrikazivanjeFilmaUSaliDTO getPfus() {
		return pfus;
	}

	public void setPfus(PrikazivanjeFilmaUSaliDTO pfus) {
		this.pfus = pfus;
	}

	public SjedisteDTO getSjediste() {
		return sjediste;
	}

	public void setSjediste(SjedisteDTO sjediste) {
		this.sjediste = sjediste;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KartaDTO other = (KartaDTO) obj;
		if(this.getSjediste() == null) {
			if(other.getSjediste()!=null)
				return false;
		}
		else if(!this.getSjediste().equals(other.getSjediste()))
			return false;
		
		if(this.getPfus().getSala() == null) {
			if(other.getPfus().getSala()!=null)
				return false;
		}
		else if(!this.getPfus().getSala().equals(other.getPfus().getSala()))
			return false;
		if(this.getPfus().termin == null) {
			if(other.getPfus().termin!=null)
				return false;
		}
		else if(!this.getPfus().termin.equals(other.getPfus().termin))
			return false;
		
		return true;
		
	}
	
}
