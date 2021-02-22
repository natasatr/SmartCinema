package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.cinema.data.dao.NalogDAO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.data.dto.Rola;
import org.unibl.etf.cinema.util.CryptoUtil;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLNalogDAO implements NalogDAO {
	@Override
	public boolean dodajNalog(Nalog nalog, String hash) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlQuery = "INSERT INTO nalog VALUES(null, ?, ?, ?, ?)";
		int retVal = 0;

		try {
			conn = DBUtil.getConnection();
			Object values[] = { nalog.getKorisnickoIme(), hash, false, nalog.getRola().getRolaID() };
			ps = DBUtil.prepareStatement(conn, sqlQuery, true, values);
			retVal = ps.executeUpdate();
			if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					nalog.setNalogID(rs.getInt(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySQLNalogDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return retVal == 1;
	}

	@Override
	public boolean azurirajNalog(Nalog nalog, String hash) {
		
		Connection conn = null;
		PreparedStatement ps = null;

		String sqlQuery = "UPDATE nalog SET KorisnickoIme = ?, ROLA_RolaID = ?";
		int retVal = 0;

		try {
			conn = DBUtil.getConnection();
			List<Object> values = new ArrayList<>();
			values.add(nalog.getKorisnickoIme());
			values.add(nalog.getRola().getRolaID());
			if (hash != null) {
				sqlQuery += ", Lozinka = ?";
				values.add(hash);
			}
			sqlQuery += " WHERE NalogID = ?";
			values.add(nalog.getNalogID());

			ps = DBUtil.prepareStatement(conn, sqlQuery, false, values.toArray());
			retVal = ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(MySQLNalogDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}
		return retVal == 1;

	}

	@Override
	public boolean obrisiNalog(Nalog nalog) {
		Connection conn = null;
		PreparedStatement ps = null;

		String sqlQuery = "UPDATE nalog SET Uklonjeno = true WHERE NalogID=?";
		int retVal = 0;

		try {
			conn = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(conn, sqlQuery, false, nalog.getNalogID());
			retVal = ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(MySQLNalogDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}
		return retVal == 1;
	}

	@Override
	public Nalog prijava(String korisnickoIme, String lozinka) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlQuery = "SELECT NalogID, KorisnickoIme, Lozinka, RolaID, Naziv FROM Nalog n "
				+ "INNER JOIN Rola r ON n.ROLA_RolaID = r.RolaID " + "WHERE KorisnickoIme = ?";

		Nalog nalog = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, korisnickoIme);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (CryptoUtil.verify(lozinka, rs.getString("Lozinka"))) {
					nalog = new Nalog(rs.getInt("NalogID"), rs.getString("KorisnickoIme"),
							new Rola(rs.getInt("RolaID"), rs.getString("Naziv")));
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLNalogDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return nalog;
	}

}
