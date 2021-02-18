package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;

public interface DodatnaPonudaDAO {
	List<DodatnaPonudaDTO> sveDodatnePonude();
	List<DodatnaPonudaDTO> dodatnaPonuda(DodatnaPonudaDTO dp);
	boolean dodajDodatnuPonudu(DodatnaPonudaDTO dp);
	boolean azurirajDodatnuPonudu(DodatnaPonudaDTO dp);
	boolean obrisiDodatnuPonudu(String naziv);

}
