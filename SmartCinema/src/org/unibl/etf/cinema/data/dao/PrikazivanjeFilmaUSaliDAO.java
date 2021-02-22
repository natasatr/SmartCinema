package org.unibl.etf.cinema.data.dao;

import java.sql.Date;
import java.util.List;

import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;

public interface PrikazivanjeFilmaUSaliDAO {
	List<PrikazivanjeFilmaUSaliDTO> sviTerminizaFilm(FilmDTO film);
	List<SjedisteDTO> listaSjedistaZaTermin(int salaID, String termin) ;
	PrikazivanjeFilmaUSaliDTO terminFilma(String naziv, String termin);
}
