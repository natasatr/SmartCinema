package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLPrikazivanjeFilmaUSaliDAO implements PrikazivanjeFilmaUSaliDAO {

	public static final String q = "select s.TerminID, s.SALA_SalaID, s.FILM_FilmID, "
			+ " sa.SalaID, sa.Broj, sa.Kapacitet, sa.Uklonjeno, "
			+ " kin.KinoID, kin.Naziv, kin.Email, kin.Telefon, "
			+ " adr.AdresaID, adr.Mjesto, adr.Ulica, adr.Broj, "
			+ " f.FilmID, f.Naziv, f.Trajanje, f.GodinaSnimanja, f.Reziser, f.Opis, f.URepetoaru, "
			+ "f.DatumPrvogPrikazivanja, f.Glumci, f.Zanr, "
			
			+ " s.Termin, s.Uklonjeno "
			+ "from Prikazivanje_filma_u_sali s"
			+ " INNER JOIN sala sa ON s.SALA_SalaID = sa.SalaID"
			+ " INNER JOIN kino kin on sa.KINO_KinoID = kin.KinoID "
			+ " INNER JOIN adresa adr on kin.ADRESA_AdresaID = adr.AdresaID "
			+ " INNER JOIN film f ON s.FILM_FilmID = f.FilmID ";
	
	@Override
	public List<PrikazivanjeFilmaUSaliDTO> sviTerminizaFilm(FilmDTO film) {
		List<PrikazivanjeFilmaUSaliDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q + " WHERE s.Uklonjeno = false and s.FILM_FilmID = ?";
		

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,film.getFilmID());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				retVal.add(new PrikazivanjeFilmaUSaliDTO( rs.getInt("s.TerminID"), new FilmDTO(rs.getInt("f.FilmID"),
						rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
						rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
						rs.getString("f.Opis"), rs.getString("f.URepetoaru"), 
						rs.getString("f.DatumPrvogPrikazivanja"), rs.getString("f.Glumci"),rs.getString("f.Zanr")),
									new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
									new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
											new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
													rs.getInt("adr.Broj")))),rs.getString("s.Termin")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

	}

	@Override
	public List<SjedisteDTO> listaSjedistaZaTermin(int salaID, String termin) {
		List<SjedisteDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q + " WHERE s.Uklonjeno = false and s.SALA_SalaID = ? and s.Termin = ?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(2,salaID);
			ps.setString(1,termin);
			rs = ps.executeQuery();
			
			
				retVal = new MySQLSjedisteDAO().svaSjedistaUSaliUKinu(salaID);
		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

		
	}



	@Override
	public PrikazivanjeFilmaUSaliDTO terminFilma(String naziv, String termin) {
		PrikazivanjeFilmaUSaliDTO retVal = new PrikazivanjeFilmaUSaliDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q + " WHERE f.Naziv = ? and s.Termin = ? ";
		

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);
			ps.setString(2, termin);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal= (new PrikazivanjeFilmaUSaliDTO( rs.getInt("s.TerminID"), new FilmDTO(rs.getInt("f.FilmID"),
						rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
						rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
						rs.getString("f.Opis"), rs.getString("f.URepetoaru"), 
						rs.getString("f.DatumPrvogPrikazivanja"), rs.getString("f.Glumci"),rs.getString("f.Zanr")),
									new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
									new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
											new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
													rs.getInt("adr.Broj")))),rs.getString("s.Termin")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

		
	}
	
	@Override
	public PrikazivanjeFilmaUSaliDTO getByTerminID(int id) {
		PrikazivanjeFilmaUSaliDTO retVal = new PrikazivanjeFilmaUSaliDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q + " WHERE s.TerminID = ? ";
		

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal = (new PrikazivanjeFilmaUSaliDTO( rs.getInt("s.TerminID"), new FilmDTO(rs.getInt("f.FilmID"),
						rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
						rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
						rs.getString("f.Opis"), rs.getString("f.URepetoaru"), 
						rs.getString("f.DatumPrvogPrikazivanja"), rs.getString("f.Glumci"),rs.getString("f.Zanr")),
									new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
									new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
											new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
													rs.getInt("adr.Broj")))),rs.getString("s.Termin")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

		
	}
	
	public List<PrikazivanjeFilmaUSaliDTO> termini() {
		List<PrikazivanjeFilmaUSaliDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movie.prikazivanje_filma_u_sali WHERE Uklonjeno = false";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.add(this.getByTerminID(rs.getInt("TerminID")));  
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;
	}
	
	public int poslednjiTermin() {
		int retVal = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT TerminID FROM movie.prikazivanje_filma_u_sali ORDER BY TerminID desc limit 1";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal = rs.getInt("TerminID");  
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLPrikazivanjeFilmaUSaliDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;
	}

	@Override
	public boolean dodaj(PrikazivanjeFilmaUSaliDTO pfusDTO) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO prikazivanje_filma_u_sali(TerminID, SALA_SalaID, FILM_FilmID, Termin, Uklonjeno) VALUES "
				+ " (?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);

			ps.setInt(1, this.poslednjiTermin()+1);
			ps.setInt(2,  pfusDTO.getSala().getSalaID());
			ps.setInt(3, pfusDTO.getFilm().getFilmID());
			ps.setString(4, pfusDTO.termin);
			ps.setBoolean(5,false);
			
			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close( ps, conn);
		}
		return retVal;
	}

	@Override
	public boolean obrisi(int TerminID) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update prikazivanje_filma_u_sali set Uklonjeno=true" + " where TerminID = ? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, TerminID);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		return retVal;
	}
	
}
