package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;

public interface VrstaSjedistaDAO {
	List<VrstaSjedistaDTO> sveVrsteSjedista();
	boolean dodajVrstuSjedista(VrstaSjedistaDTO vs);
	boolean azurirajVrstuSjedista(VrstaSjedistaDTO vs);
	boolean obrisiVrstuSjedista(String naziv);

}
