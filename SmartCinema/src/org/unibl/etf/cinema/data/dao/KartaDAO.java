package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.KartaDTO;


public interface KartaDAO {
	List<KartaDTO> Karte();
	
	KartaDTO getByID(int KartaID);
	
	List<KartaDTO> getByMovieName(String name, String termin);
	KartaDTO getKartu(String name, int termin, int sjediste);
	
	boolean dodajKartu(KartaDTO karta);
	boolean azurirajKartu(KartaDTO karta);
	boolean obrisiKartu(int KartaID);

}
