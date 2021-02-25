package org.unibl.etf.cinema.data.dao;

import java.util.List;
import org.unibl.etf.cinema.data.dto.RezervacijaDTO;

public interface RezervacijaDAO {

	List<RezervacijaDTO> rezervacijeNaIme(String ime);
	RezervacijaDTO getByID(int id);
	boolean dodajRezervaciju(RezervacijaDTO Rezervacija);
	boolean azurirajRezervaciju(RezervacijaDTO Rezervacija);
	boolean obrisiRezervaciju(int rezervacijaID);
	List<RezervacijaDTO> rezervacije();
	boolean obrisiRezervacijeZaTermin(int rezervacijaID);
}
