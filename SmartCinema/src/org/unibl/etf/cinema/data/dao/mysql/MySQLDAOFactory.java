package org.unibl.etf.cinema.data.dao.mysql;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.DodatnaPonudaDAO;
import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.KartaDAO;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.NalogDAO;
import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dao.RolaDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.VrstaSjedistaDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public AdresaDAO getAdresaDAO() {
		return new MySQLAdresaDAO();
	}

	@Override
	public KinoDAO getKinoDAO() {
		return new MySQLKinoDAO();
	}

	@Override
	public KartaDAO getKartaDAO() {
		return new MySQLKartaDAO();
	}
	
	@Override
	public NalogDAO getNalogDAO() {
		return new MySQLNalogDAO();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return new MySQLSalaDAO();
	}

	@Override
	public SjedisteDAO getSjedisteDAO() {
		return new MySQLSjedisteDAO();
	}

	@Override
	public DodatnaPonudaDAO getDodatnaPonudaDAO() {
		return new MySQLDodatnaPonudaDAO();
	}

	@Override
	public VrstaSjedistaDAO getVrstaSjedistaDAO() {
		return new MySQLVrstaSjedistaDAO();
	}

	@Override
	public ZaposleniDAO getZaposleniDAO() {
		return new MySQLZaposleniDAO();
	}

	@Override
	public RolaDAO getRolaDAO() {
		return new MySQLRolaDAO();
	}
	
	@Override
	public FilmDAO getFilmDAO(){
		return new MySQLFilmDAO();
	}
	
	@Override
	public PrikazivanjeFilmaUSaliDAO getPrikazivanjeFilmaUSaliDAO(){
		return new MySQLPrikazivanjeFilmaUSaliDAO();
	}
	
	@Override
	public RezervacijaDAO getRezervacijaDAO(){
		return new MySQLRezervacijaDAO();
	}

}
