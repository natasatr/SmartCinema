package org.unibl.etf.cinema.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class Nalog implements Serializable {
	private Integer nalogID;
	private String korisnickoIme;
	private Rola rola;

	public Nalog() {
		
	}
	
	public Nalog(String korisnickoIme, Rola rola) {
		this.korisnickoIme = korisnickoIme;
		this.rola = rola;
	}
	
	public Nalog(Integer nalogID, String korisnickoIme, Rola rola) {
		this.nalogID = nalogID;
		this.korisnickoIme = korisnickoIme;
		this.rola = rola;
	}

	public Integer getNalogID() {
		return nalogID;
	}
	
	public void setNalogID(Integer nalogID) {
		this.nalogID = nalogID;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public Rola getRola() {
		return rola;
	}

	public void setRola(Rola rola) {
		this.rola = rola;
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(nalogID, korisnickoIme);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Nalog))
            return false;
        Nalog nalog = (Nalog)obj;
        return Objects.equals(nalogID, nalog.nalogID);
    }
    
    @Override
    public String toString() {
        return "ID: " + nalogID + "\nKorisnickoIme: " + korisnickoIme + "\n";
    }

}
