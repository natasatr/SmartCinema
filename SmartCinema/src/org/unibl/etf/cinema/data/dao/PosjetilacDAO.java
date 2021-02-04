package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.PosjetilacDTO;

public interface PosjetilacDAO {
	List<PosjetilacDTO> sviPosjetioci();
	boolean dodajPosjetioca(PosjetilacDTO p);
	boolean obrisiPosjetioca(String ime, String prezime);

}
