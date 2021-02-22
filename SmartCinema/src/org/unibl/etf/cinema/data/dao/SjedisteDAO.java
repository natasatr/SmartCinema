package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.SjedisteDTO;

public interface SjedisteDAO {
	List<SjedisteDTO> svaSjedista();
	 List<SjedisteDTO> svaSlobodnaSjedistaUSaliUKinu(int SalaID);
	List<SjedisteDTO> svaSjedistaUSaliUKinu(int SalaID);
	SjedisteDTO sjediste(int SjedisteID);
	boolean dodajSjedisteUSaluUKinu(SjedisteDTO sjediste);
	boolean azurirajSjedisteUSaliUKinu(SjedisteDTO sjediste);
	boolean obrisiSjedisteIzSaleKina(int broj, int red,int SalaID);

}
