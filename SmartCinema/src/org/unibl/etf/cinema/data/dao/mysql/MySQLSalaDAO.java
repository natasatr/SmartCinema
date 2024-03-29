package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLSalaDAO implements SalaDAO {

	@Override
	public List<SalaDTO> sveSale() {
		List<SalaDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select SalaID, s.Broj, Kapacitet, KinoID, k.Naziv, Email, Telefon, AdresaID,"
				+ " a.Mjesto, a.Ulica, a.Broj from sala s" + " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID=AdresaID " + "  where s.Uklonjeno=0" + " and KinoID=1 "
				+ " order by SalaID asc ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new SalaDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						new KinoDTO(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
								new AdresaDTO(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11)))));

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}

	@Override
	public List<SalaDTO> sveSaleUKinu(String nazivKina) {
		List<SalaDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select SalaID, s.Broj, Kapacitet, KinoID, k.Naziv, Email, Telefon, AdresaID,"
				+ " a.Mjesto, a.Ulica, a.Broj from sala s" + " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID=AdresaID " + " where k.Naziv= ? " + " and s.Uklonjeno=0"
				+ " order by SalaID asc ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, nazivKina);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new SalaDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						new KinoDTO(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
								new AdresaDTO(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11)))));

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}

	@Override
	public boolean dodajSalu(SalaDTO sala) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO sala (Broj, Kapacitet, Uklonjeno, KINO_KinoID ) VALUES " + " ( ?, ?, ?, ? ) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);

			ps.setInt(1, sala.getBroj());
			ps.setInt(2, sala.getKapacitet());
			ps.setInt(3, 0);
			ps.setInt(4, sala.getKino().getKinoID());

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
	public boolean azurirajSalu(SalaDTO sala) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE sala SET " + "Broj =?, " + " Kapacitet= ? " + " WHERE SalaID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sala.getBroj());
			ps.setInt(2, sala.getKapacitet());
			ps.setInt(3, sala.getSalaID());

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
	public boolean obrisiSalu(int SalaID) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL obrisi_salu( ? )}";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, SalaID);

			retVal = cs.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(cs, conn);
		}
		return retVal;
}
}
