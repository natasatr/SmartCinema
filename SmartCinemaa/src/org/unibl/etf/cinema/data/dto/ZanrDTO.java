package org.unibl.etf.cinema.data.dto;

public class ZanrDTO {
	
	private int ZanrID;
	private String Naziv;
	
	public ZanrDTO(int zanrID, String naziv) {
		super();
		ZanrID = zanrID;
		Naziv = naziv;
	}

	public ZanrDTO() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ZanrID;
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
		ZanrDTO other = (ZanrDTO) obj;
		if (ZanrID != other.ZanrID)
			return false;
		return true;
	}

	public int getZanrID() {
		return ZanrID;
	}

	public void setZanrID(int zanrID) {
		ZanrID = zanrID;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	
	
	

}
