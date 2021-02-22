package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;

public interface DodatnaPonudaDAO {
	List<DodatnaPonudaDTO> sveDodatnePonude();
	DodatnaPonudaDTO dodatnaPonuda(String naziv);
	boolean dodajDodatnuPonudu(DodatnaPonudaDTO dp);
	boolean azurirajDodatnuPonudu(DodatnaPonudaDTO dp);
	boolean obrisiDodatnuPonudu(String naziv);

}
