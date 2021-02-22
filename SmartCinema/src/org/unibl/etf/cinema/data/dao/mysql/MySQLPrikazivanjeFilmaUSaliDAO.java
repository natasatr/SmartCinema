package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
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
import org.unibl.etf.cinema.data.dto.KartaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLPrikazivanjeFilmaUSaliDAO implements PrikazivanjeFilmaUSaliDAO {

	@Override
	public List<PrikazivanjeFilmaUSaliDTO> sviTerminizaFilm(FilmDTO film) {
		List<PrikazivanjeFilmaUSaliDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select" + " s.SALA_SalaID, s.FILM_FilmID, "
				+ " sa.SalaID, sa.Broj, sa.Kapacitet, sa.Uklonjeno, "
				+ " kin.KinoID, kin.Naziv, kin.Email, kin.Telefon, "
				+ " adr.AdresaID, adr.Mjesto, adr.Ulica, adr.Broj, "
				+ " f.FilmID, f.Naziv, f.Trajanje, f.GodinaSnimanja, f.Reziser, f.Opis, f.URepetoaru, f.DatumPrvogPrikazivanja, f.Uklonjeno, "

				+ " s.Termin, s.Uklonjeno "
				+ "from Prikazivanje_filma_u_sali s"
				+ " INNER JOIN sala sa ON s.SALA_SalaID = sa.SalaID"
				+ " INNER JOIN kino kin on sa.KINO_KinoID = kin.KinoID "
				+ " INNER JOIN adresa adr on kin.ADRESA_AdresaID = adr.AdresaID "
				+ " INNER JOIN film f ON s.FILM_FilmID = f.FilmID "
		
				+ " WHERE s.Uklonjeno = false and s.FILM_FilmID = ?";
		

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,film.getFilmID());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				retVal.add(new PrikazivanjeFilmaUSaliDTO(new FilmDTO(rs.getInt("f.FilmID"),
										rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
										rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
										rs.getString("f.Opis"), rs.getBoolean("f.URepetoaru"), 
										rs.getDate("f.DatumPrvogPrikazivanja"), rs.getBoolean("f.Uklonjeno")),
									new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
									new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
											new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
													rs.getInt("adr.Broj")))),rs.getString("s.Termin")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLKartaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
		
		String query = "select" + " s.SALA_SalaID, s.FILM_FilmID, "
				+ " sa.SalaID, sa.Broj, sa.Kapacitet, sa.Uklonjeno, "
				+ " kin.KinoID, kin.Naziv, kin.Email, kin.Telefon, "
				+ " adr.AdresaID, adr.Mjesto, adr.Ulica, adr.Broj, "
				+ " f.FilmID, f.Naziv, f.Trajanje, f.GodinaSnimanja, f.Reziser, f.Opis, f.URepetoaru, f.DatumPrvogPrikazivanja, f.Uklonjeno, "

				+ " s.Termin, s.Uklonjeno "
				+ "from Prikazivanje_filma_u_sali s"
				+ " INNER JOIN sala sa ON s.SALA_SalaID = sa.SalaID"
				+ " INNER JOIN kino kin on sa.KINO_KinoID = kin.KinoID "
				+ " INNER JOIN adresa adr on kin.ADRESA_AdresaID = adr.AdresaID "
				+ " INNER JOIN film f ON s.FILM_FilmID = f.FilmID "
		
				+ " WHERE s.Uklonjeno = false and s.SALA_SalaID = ? and s.Termin = ?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(2,salaID);
			ps.setString(1,termin);
			rs = ps.executeQuery();
			
			
				retVal = new MySQLSjedisteDAO().svaSjedistaUSaliUKinu(salaID);
		} catch (SQLException ex) {
			Logger.getLogger(MySQLKartaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
		
		String query = "select s.SALA_SalaID, s.FILM_FilmID, "
				+ " sa.SalaID, sa.Broj, sa.Kapacitet, sa.Uklonjeno, "
				+ " kin.KinoID, kin.Naziv, kin.Email, kin.Telefon, "
				+ " adr.AdresaID, adr.Mjesto, adr.Ulica, adr.Broj, "
				+ " f.FilmID, f.Naziv, f.Trajanje, f.GodinaSnimanja, f.Reziser, f.Opis, f.URepetoaru, f.DatumPrvogPrikazivanja, f.Uklonjeno, "

				+ " s.Termin, s.Uklonjeno "
				+ "from Prikazivanje_filma_u_sali s"
				+ " INNER JOIN sala sa ON s.SALA_SalaID = sa.SalaID"
				+ " INNER JOIN kino kin on sa.KINO_KinoID = kin.KinoID "
				+ " INNER JOIN adresa adr on kin.ADRESA_AdresaID = adr.AdresaID "
				+ " INNER JOIN film f ON s.FILM_FilmID = f.FilmID "
		
				+ " WHERE f.Naziv = ? and s.Termin = ? ";
		

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);
			ps.setString(2, termin);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal = (new PrikazivanjeFilmaUSaliDTO(new FilmDTO(rs.getInt("f.FilmID"),
										rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
										rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
										rs.getString("f.Opis"), rs.getBoolean("f.URepetoaru"), 
										rs.getDate("f.DatumPrvogPrikazivanja"), rs.getBoolean("f.Uklonjeno")),
									new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
									new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
											new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
													rs.getInt("adr.Broj")))),rs.getString("s.Termin")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLKartaDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

		
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		MySQLPrikazivanjeFilmaUSaliDAO p = new MySQLPrikazivanjeFilmaUSaliDAO();
		MySQLFilmDAO f = new MySQLFilmDAO();
		List<PrikazivanjeFilmaUSaliDTO> d = p.sviTerminizaFilm(f.searchMovie("milovan").get(0));
		//System.out.println(d.get(0).termin.equals(new Date(1).valueOf("1995-8-3").toString().trim()));
		PrikazivanjeFilmaUSaliDTO ps = p.terminFilma("milovan",d.get(0).termin);
		//System.out.println(new Date(1).valueOf("1983-8-3"));
		System.out.println(ps);
		
	}
	
}
