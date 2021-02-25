package org.unibl.etf.cinema.data.dao;
import java.util.List;

import org.unibl.etf.cinema.data.dto.FilmDTO;


public interface FilmDAO {
	public List<FilmDTO> selectAll();
	public List<FilmDTO> searchMovie(String name);
	public boolean insert(FilmDTO i);
	public int update(FilmDTO i);
	boolean delete(int i);

}
