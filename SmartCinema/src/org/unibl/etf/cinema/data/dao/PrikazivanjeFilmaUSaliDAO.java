package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;

public interface PrikazivanjeFilmaUSaliDAO {
	List<PrikazivanjeFilmaUSaliDTO> sviTerminizaFilm(FilmDTO film);
	List<SjedisteDTO> listaSjedistaZaTermin(int salaID, String termin) ;
	PrikazivanjeFilmaUSaliDTO terminFilma(String naziv, String termin);
	PrikazivanjeFilmaUSaliDTO getByTerminID(int id);
	List<PrikazivanjeFilmaUSaliDTO> termini();
	
	boolean dodaj(PrikazivanjeFilmaUSaliDTO pfusDTO);
	boolean obrisi(int TerminID);
}
