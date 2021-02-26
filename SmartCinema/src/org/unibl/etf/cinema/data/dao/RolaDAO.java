package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.Rola;

public interface RolaDAO {
	List<Rola> sveRole();
	Rola rola(String naziv);
}
