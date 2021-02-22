package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.cinema.data.dao.RolaDAO;
import org.unibl.etf.cinema.data.dto.Rola;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLRolaDAO implements RolaDAO {

	@Override
	public Rola rola(String naziv) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlQuery = "SELECT RolaID, Naziv FROM Rola " + "WHERE Naziv = ? " + "ORDER BY Naziv ASC";

		Rola rola = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, naziv);
			rs = ps.executeQuery();

			if (rs.next()) {
				rola = new Rola(rs.getInt("RolaID"), rs.getString("Naziv"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLRolaDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return rola;
	}

}
