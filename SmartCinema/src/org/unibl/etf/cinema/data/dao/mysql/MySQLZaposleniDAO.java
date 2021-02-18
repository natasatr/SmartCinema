package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.data.dto.Rola;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLZaposleniDAO implements ZaposleniDAO {

	@Override
	public List<Zaposleni> sviZaposleni() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlQuery = "SELECT ZaposleniID, JMB, Ime, Prezime, Plata, Email, NalogID, KorisnickoIme, RolaID, Naziv, AdresaID, Mjesto, Ulica, Broj "
				+ "FROM zaposleni z " + "INNER JOIN nalog n ON z.NALOG_NalogID = n.NalogID "
				+ "INNER JOIN rola r ON n.ROLA_RolaID = r.RolaID "
				+ "INNER JOIN adresa a ON z.ADRESA_AdresaID = a.AdresaID "
				+ "WHERE z.Uklonjeno = false "
				+ "ORDER BY ZaposleniID ASC";

		List<Zaposleni> resultList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				resultList.add(new Zaposleni(rs.getInt("ZaposleniID"), rs.getString("JMB"), rs.getString("Ime"),
						rs.getString("Prezime"), rs.getDouble("Plata"), rs.getString("Email"),
						new AdresaDTO(rs.getInt("AdresaID"), rs.getString("Mjesto"), rs.getString("Ulica"),
								rs.getInt("Broj")),
						new Nalog(rs.getInt("NalogID"), rs.getString("KorisnickoIme"),
								new Rola(rs.getInt("RolaID"), rs.getString("Naziv")))));
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return resultList;
	}

	@Override
	public boolean dodajZaposlenog(Zaposleni zaposleni) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlQuery = "INSERT INTO zaposleni VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		int retVal = 0;

		try {
			conn = DBUtil.getConnection();
			Object values[] = { zaposleni.getJmb(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getPlata(),
					zaposleni.getEmail(), false, zaposleni.getNalog().getNalogID(), zaposleni.getAdresa().getAdresaID()};
			ps = DBUtil.prepareStatement(conn, sqlQuery, true, values);
			retVal = ps.executeUpdate();
			if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					zaposleni.setZaposleniID(rs.getInt(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLNalogDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return retVal == 1;
	}

	@Override
	public boolean azurirajZaposlenog(Zaposleni zaposleni) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        String sqlQuery = "UPDATE zaposleni SET JMB = ?, Ime = ?, Prezime = ?, Plata = ?, Email = ?, NALOG_NalogID = ?, ADRESA_AdresaID = ? " +
                          "WHERE ZaposleniID = ?";
        int retVal = 0;
        
        try {
            conn = DBUtil.getConnection();
            Object values[] = { zaposleni.getJmb(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getPlata(), zaposleni.getEmail(),
            		zaposleni.getNalog().getNalogID(), zaposleni.getAdresa().getAdresaID(), zaposleni.getZaposleniID() };
            ps = DBUtil.prepareStatement(conn, sqlQuery, false, values);
            retVal = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.close(ps, conn);
        }
        return retVal == 1;
	}

	@Override
	public boolean obrisiZaposlenog(Zaposleni zaposleni) {
		Connection conn = null;
		PreparedStatement ps = null;

	        String sqlQuery = "UPDATE zaposleni SET Uklonjeno = true WHERE ZaposleniID = ?";
	        boolean retVal = false;   
	        
	        try {
	            conn = DBUtil.getConnection();
	            ps = DBUtil.prepareStatement(conn, sqlQuery, false, zaposleni.getZaposleniID());
	            retVal = ps.executeUpdate() == 1 && DAOFactory.getDAOFactory().getNalogDAO().obrisiNalog(zaposleni.getNalog())
	            		&& DAOFactory.getDAOFactory().getAdresaDAO().obrisiAdresu(zaposleni.getAdresa());
	        } catch (SQLException ex) {
	            Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
			DBUtil.close(ps, conn);
		}
	        return retVal; 
	}

	public static void main(String args[]) {
		MySQLZaposleniDAO kino = new MySQLZaposleniDAO();
		AdresaDTO a = new AdresaDTO();
		a.setAdresaID(1);
		Rola r = new Rola();
		r.setRolaID(1);
		Nalog n = new Nalog();
		n.setNalogID(2);
		kino.obrisiZaposlenog(new Zaposleni(6, "0505123456898", "Srednji", "Marmat", 10000.0, "srednji@gmail.com", a, n));


		List<Zaposleni> ki = kino.sviZaposleni();
		for (Zaposleni k : ki)
			System.out.println(k);

	}

}
