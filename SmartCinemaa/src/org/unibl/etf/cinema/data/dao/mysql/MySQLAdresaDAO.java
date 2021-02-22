package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLAdresaDAO implements AdresaDAO {

	@Override
	public List<AdresaDTO> sveAdrese() {
		List<AdresaDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select AdresaID, Mjesto, Ulica, Broj from adresa "
				     + "where Uklonjeno = false order by Ulica asc";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new AdresaDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}

	@Override
	public AdresaDTO adresa(int AdresaID) {
		AdresaDTO retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select AdresaID, Mjesto, Ulica, Broj from adresa "
				     + "where AdresaID = ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, AdresaID);
			rs = ps.executeQuery();

			if (rs.next()) {
				retVal = new AdresaDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}

	@Override
	public boolean dodajAdresu(AdresaDTO adresa) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "INSERT INTO adresa(Mjesto, Ulica, Broj) VALUES (?, ?, ?)";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, adresa.getMjesto());
			ps.setString(2, adresa.getUlica());
			ps.setInt(3, adresa.getBroj());

			retVal = ps.executeUpdate() == 1;

			if (retVal) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					adresa.setAdresaID(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}

	@Override
	public boolean azurirajAdresu(AdresaDTO adresa) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE adresa SET Mjesto = ?, Ulica = ?, Broj = ? WHERE AdresaID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, adresa.getMjesto());
			ps.setString(2, adresa.getUlica());
			ps.setInt(3, adresa.getBroj());
			ps.setInt(4, adresa.getAdresaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}

	@Override
	public boolean obrisiAdresu(AdresaDTO adresa) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update adresa set Uklonjeno=1" + " where AdresaID = ? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, adresa.getAdresaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;

	}
}
