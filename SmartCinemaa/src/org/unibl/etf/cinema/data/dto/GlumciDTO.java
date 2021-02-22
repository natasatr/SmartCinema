package org.unibl.etf.cinema.data.dto;

public class GlumciDTO {
	
	private int GlumciID;
	private String ime;
	private String prezime;
	
	public GlumciDTO() {
		super();
	}
	
	public GlumciDTO(int glumciID, String ime, String prezime) {
		super();
		GlumciID = glumciID;
		this.ime = ime;
		this.prezime = prezime;
	}

	public int getGlumciID() {
		return GlumciID;
	}

	public void setGlumciID(int glumciID) {
		GlumciID = glumciID;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GlumciID;
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
		GlumciDTO other = (GlumciDTO) obj;
		if (GlumciID != other.GlumciID)
			return false;
		return true;
	}
	
	
	
	

}
