package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.KartaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.RezervacijaDTO;
import org.unibl.etf.cinema.data.dto.Rola;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLRezervacijaDAO implements RezervacijaDAO {

	public static final String q = "SELECT r.RezervacijaID, r.Ime, r.Prezime,r.odDatuma, r.doDatuma, "
					+ "k.KartaID, k.Cijena, k.VrijemeKupovine, k.Prodano, "
					
					+ " sj.SjedisteID, sj.Broj, sj.Red, sj.Zauzeto, sj.Uklonjeno, s.SalaID, s.Broj, "
					+ " s.Kapacitet, s.Uklonjeno, ki.KinoID, ki.Naziv, ki.Email, ki.Telefon, "
					+ " a.AdresaID, a.Mjesto, a.Ulica, a.Broj, "
					+ " v.VrstaSjedistaID, v.Naziv, "
					
					+ " z.ZaposleniID, z.JMB, z.Ime, z.Prezime, z.Plata, z.Email, "
					+ " n.NalogID, n.KorisnickoIme, "
					+ " ro.RolaID, ro.Naziv, "
					+ " ad.AdresaID, ad.Mjesto, ad.Ulica, ad.Broj, "  

					+ " p.TerminID, ps.SALA_SalaID, pf.FILM_FilmID, "
					+ " sa.SalaID, sa.Broj, sa.Kapacitet, sa.Uklonjeno, "
					+ " kin.KinoID, kin.Naziv, kin.Email, kin.Telefon, "
					+ " adr.AdresaID, adr.Mjesto, adr.Ulica, adr.Broj, "
					+ " f.FilmID, f.Naziv, f.Trajanje, f.GodinaSnimanja, f.Reziser, f.Opis, f.URepetoaru, "
					+ "f.DatumPrvogPrikazivanja, f.Glumci, f.Zanr, "

					+ " p.Termin, p.Uklonjeno "
					
			+ " from rezervacija r "
			+ " INNER JOIN karta k on r.KARTA_KartaID = k.KartaID"		
			+ " INNER JOIN sjediste sj on k.SJEDISTE_SjedisteID = sj.SjedisteID"
			+ " INNER JOIN sala s on sj.SALA_SalaID = s.SalaId "
			+ " INNER JOIN kino ki on s.KINO_KinoID = ki.KinoID "
			+ " INNER JOIN adresa a on ki.ADRESA_AdresaID = a.AdresaID "
			+ " INNER JOIN vrsta_sjedista  v on sj.VRSTA_SJEDISTA_VrstaSjedistaID = v.VrstaSjedistaID"
			
			+ " INNER JOIN Zaposleni z ON k.ZAPOSLENI_ZaposleniID = z.ZaposleniID"
			+ " INNER JOIN nalog n ON z.NALOG_NalogID = n.NalogID "
			+ "	INNER JOIN rola ro ON n.ROLA_RolaID = ro.RolaID "
			+ "	INNER JOIN adresa ad ON z.ADRESA_AdresaID = ad.AdresaID "
			
			+ " INNER JOIN prikazivanje_filma_u_sali p ON k.PRIKAZIVANJE_FILMA_U_SALI_TerminID = p.TerminID"
			+ " INNER JOIN prikazivanje_filma_u_sali ps ON k.PRIKAZIVANJE_FILMA_U_SALI_SALA_SalaID = ps.SALA_SalaID"
			+ " INNER JOIN prikazivanje_filma_u_sali pf ON k.PRIKAZIVANJE_FILMA_U_SALI_FILM_FilmID = pf.FILM_FilmID"
			+ " INNER JOIN sala sa ON ps.SALA_SalaID = sa.SalaID"
			+ " INNER JOIN kino kin on sa.KINO_KinoID = kin.KinoID "
			+ " INNER JOIN adresa adr on kin.ADRESA_AdresaID = adr.AdresaID "
			+ " INNER JOIN film f ON pf.FILM_FilmID = f.FilmID ";
			
	
	@Override
	public List<RezervacijaDTO> rezervacije(){
		List<RezervacijaDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movie.rezervacija WHERE Uklonjeno = false"
				+ " ORDER BY RezervacijaID ASC";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.add(this.getByID(rs.getInt("RezervacijaID")));  
			}


		} catch (SQLException ex) {
			Logger.getLogger(MySQLRezervacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;

	}
	
	
	@Override
	public List<RezervacijaDTO> rezervacijeNaIme(String ime) {
		List<RezervacijaDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movie.rezervacija WHERE Uklonjeno = false and Ime = ?"
				+ " ORDER BY RezervacijaID ASC";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1,ime);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.add(this.getByID(rs.getInt("RezervacijaID")));  
			}


		} catch (SQLException ex) {
			Logger.getLogger(MySQLRezervacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return retVal;
	}
	
	@Override
	public RezervacijaDTO getByID(int id) {
		RezervacijaDTO retVal = new RezervacijaDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q + " WHERE r.RezervacijaID=?";
				
				try {
					conn = ConnectionPool.getInstance().checkOut();
					ps = conn.prepareStatement(query);
					
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					if (rs.next())
						
						retVal = (new RezervacijaDTO( rs.getInt("r.RezervacijaID"), rs.getTimestamp("r.odDatuma"), rs.getTimestamp("r.doDatuma"), rs.getString("r.Ime"), rs.getString("r.Prezime"),
								new KartaDTO(rs.getInt("k.KartaID"), rs.getDouble("k.Cijena"), rs.getTimestamp("k.VrijemeKupovine"), rs.getBoolean("k.Prodano"),
										new SjedisteDTO(rs.getInt("sj.SjedisteID"),rs.getInt("sj.Broj"),rs.getInt("sj.red"),rs.getBoolean("sj.Zauzeto"),
												new SalaDTO(rs.getInt("s.SalaID"),rs.getInt("s.Broj"), rs.getInt("s.Kapacitet"),
														new KinoDTO(rs.getInt("ki.KinoID"),rs.getString("ki.Naziv"),rs.getString("ki.Email"),rs.getString("ki.Telefon"),
																new AdresaDTO(rs.getInt("a.AdresaID"), rs.getString("a.Mjesto"), rs.getString("a.Ulica"),
																		rs.getInt("a.Broj")))),
												new VrstaSjedistaDTO(rs.getInt("v.VrstaSjedistaID"),rs.getString("v.Naziv"))),
											new Zaposleni(rs.getInt("z.ZaposleniID"), rs.getString("z.JMB"), rs.getString("z.Ime"),
													rs.getString("z.Prezime"), rs.getDouble("z.Plata"), rs.getString("z.Email"),
													new AdresaDTO(rs.getInt("ad.AdresaID"), rs.getString("ad.Mjesto"), rs.getString("ad.Ulica"),
															rs.getInt("ad.Broj")),
													new Nalog(rs.getInt("n.NalogID"), rs.getString("n.KorisnickoIme"),
															new Rola(rs.getInt("ro.RolaID"), rs.getString("ro.Naziv")))),
											new PrikazivanjeFilmaUSaliDTO(new FilmDTO(rs.getInt("f.FilmID"),
														rs.getString("f.Naziv"),rs.getString("f.Trajanje"),
														rs.getInt("f.GodinaSnimanja"),rs.getString("f.Reziser"), 
														rs.getString("f.Opis"), rs.getString("f.URepetoaru"), 
														rs.getString("f.DatumPrvogPrikazivanja"), rs.getString("f.Glumci"),rs.getString("f.Zanr")),
													new SalaDTO(rs.getInt("sa.SalaID"),rs.getInt("sa.Broj"), rs.getInt("sa.Kapacitet"),
													new KinoDTO(rs.getInt("kin.KinoID"),rs.getString("kin.Naziv"),rs.getString("kin.Email"),rs.getString("kin.Telefon"),
															new AdresaDTO(rs.getInt("adr.AdresaID"), rs.getString("adr.Mjesto"), rs.getString("adr.Ulica"),
																	rs.getInt("adr.Broj")))),rs.getString("p.Termin")))));
						
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					ConnectionPool.getInstance().checkIn(conn);
					DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	

	@Override
	public boolean dodajRezervaciju(RezervacijaDTO rezervacija) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO Rezervacija(odDatuma, doDatuma, "
				+ "ime, prezime, Uklonjeno, KARTA_KartaID) "
				+ "VALUES (?, ?, ?, ?, ?, ? ) ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, rezervacija.getOdDatuma().toString());
			ps.setTimestamp(2, rezervacija.getOdDatuma());
			ps.setString(3,  rezervacija.getIme());
			ps.setString(4,  rezervacija.getPrezime());
			ps.setBoolean(5, false);
			ps.setInt(6, rezervacija.getKarta().getKartaID());
			

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
	public boolean azurirajRezervaciju(RezervacijaDTO Rezervacija) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "UPDATE rezervacija, karta SET  rezervacija.Uklonjeno = true, "
				+ "karta.Prodano = true "
				+ " WHERE RezervacijaID = ? and karta.KartaID = ?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Rezervacija.getRezervacijaID());
			ps.setInt(2, Rezervacija.getKarta().getKartaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		
		return retVal;
	}

	@Override
	public boolean obrisiRezervaciju(int rezervacijaID) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update rezervacija set Uklonjeno=true" + " where RezervacijaID = ? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, rezervacijaID);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		return retVal;
	}

	@Override
	public boolean obrisiRezervacijeZaTermin(int terminID) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = q+ /*" update rezervacija set Uklonjeno=true" +*/ " where p.TerminID = ? ";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, terminID);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				RezervacijaDTO r = this.getByID(rs.getInt("RezervacijaID"));
				new MySQLKartaDAO().obrisiKartu(r.getKarta().getKartaID());
				retVal=(this.obrisiRezervaciju(rs.getInt("RezervacijaID")));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		return retVal;
	}



}
