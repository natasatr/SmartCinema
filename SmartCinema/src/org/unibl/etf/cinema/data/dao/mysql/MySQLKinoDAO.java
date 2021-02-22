package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLKinoDAO implements KinoDAO {

	@Override
	public List<KinoDTO> svaKina() {
		List<KinoDTO> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select KinoID, Naziv, Email, Telefon, AdresaID, Mjesto, Ulica, Broj from kino k"
				+ " inner join adresa a on a.AdresaID=k.ADRESA_AdresaID "
				+ "where k.Uklonjeno = false order by k.Naziv asc ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new KinoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new AdresaDTO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8))));

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}

	@Override
	public KinoDTO kino(String naziv) {
		KinoDTO retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select KinoID, Naziv, Email, Telefon, AdresaID, Mjesto, Ulica, Broj from kino k "
				+ "inner join adresa a on a.AdresaID=k.ADRESA_AdresaID where Naziv like ? AND k.Uklonjeno = false";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);
			rs = ps.executeQuery();

			if (rs.next()) {
				retVal = new KinoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new AdresaDTO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
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
	public boolean dodajKino(KinoDTO kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "INSERT INTO kino(Naziv, Email, Telefon, ADRESA_AdresaID) VALUES(?, ?, ?, ?)";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, kino.getNaziv());
			ps.setString(2, kino.getEmail());
			ps.setString(3, kino.getTelefon());
			ps.setInt(4, kino.getAdresa().getAdresaID());

			retVal = ps.executeUpdate() == 1;

			if (retVal) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					kino.setKinoID(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		return retVal;
	}

	@Override
	public boolean azurirajKino(KinoDTO kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE kino SET Naziv = ?, Email = ?, Telefon = ? WHERE KinoID = ?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, kino.getNaziv());
			ps.setString(2, kino.getEmail());
			ps.setString(3, kino.getTelefon());
			ps.setInt(4, kino.getKinoID());

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
	public boolean obrisiKino(String naziv) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update kino set Uklonjeno=1" + " where Naziv = ? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
		}
		return retVal;

	}

	public static void main(String args[]) {
		MySQLKinoDAO kino = new MySQLKinoDAO();
		List<KinoDTO> ki = kino.svaKina();

		for (KinoDTO k : ki)
			System.out.println(k);

	}

}
