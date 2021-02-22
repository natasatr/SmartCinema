package org.unibl.etf.cinema.data.dto;

public class VrstaSjedistaDTO {

	public int VrstaSjedistaID;
	public String Naziv;
	public int uklonjeno;
	public VrstaSjedistaDTO(int vrstaSjedistaID, String naziv, int uklonjeno) {
		super();
		VrstaSjedistaID = vrstaSjedistaID;
		Naziv = naziv;
		this.uklonjeno = uklonjeno;
	}
	public VrstaSjedistaDTO() {
		super();
	}
	public int getVrstaSjedistaID() {
		return VrstaSjedistaID;
	}
	public void setVrstaSjedistaID(int vrstaSjedistaID) {
		VrstaSjedistaID = vrstaSjedistaID;
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public int isUklonjeno() {
		return uklonjeno;
	}
	public void setUklonjeno(int uklonjeno) {
		this.uklonjeno = uklonjeno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		result = prime * result + VrstaSjedistaID;
		result = prime * result + uklonjeno;
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
		VrstaSjedistaDTO other = (VrstaSjedistaDTO) obj;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		if (VrstaSjedistaID != other.VrstaSjedistaID)
			return false;
		if (uklonjeno != other.uklonjeno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VrstaSjedista [VrstaSjedistaID=" + VrstaSjedistaID + ", Naziv=" + Naziv + ", uklonjeno=" + uklonjeno
				+ "]";
	}
	
	
}
