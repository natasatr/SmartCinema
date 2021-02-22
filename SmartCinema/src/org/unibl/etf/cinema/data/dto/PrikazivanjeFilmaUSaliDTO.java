package org.unibl.etf.cinema.data.dto;

import java.sql.Date;

public class PrikazivanjeFilmaUSaliDTO {
	private FilmDTO film;
	private SalaDTO sala;
	public String termin;
	private boolean uklonjeno;
	
	public PrikazivanjeFilmaUSaliDTO() {
		super();
	}
	
	public PrikazivanjeFilmaUSaliDTO(FilmDTO film, SalaDTO sala, String termin) {
		this.film = film;
		this.sala = sala;
		this.termin = termin;
	}

	public FilmDTO getFilm() {
		return film;
	}

	public void setFilm(FilmDTO film) {
		this.film = film;
	}

	public SalaDTO getSala() {
		return sala;
	}

	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}

	public boolean isUklonjeno() {
		return uklonjeno;
	}

	public void setUklonjeno(boolean uklonjeno) {
		this.uklonjeno = uklonjeno;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  termin;
	}
}
