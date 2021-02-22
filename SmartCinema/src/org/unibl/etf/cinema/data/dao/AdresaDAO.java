package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.AdresaDTO;

public interface AdresaDAO {
	List<AdresaDTO> sveAdrese();

	AdresaDTO adresa(int AdresaID);

	boolean dodajAdresu(AdresaDTO adresa);

	boolean azurirajAdresu(AdresaDTO adresa);

	boolean obrisiAdresu(AdresaDTO adresa);

}
