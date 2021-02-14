package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;

public class MySQLSalaDAO implements SalaDAO {
	
	@Override
	public List<SalaDTO> sveSaleUKinu(String nazivKina){
		List<SalaDTO> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select SalaID, s.Broj, Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID,"
				+ " a.Mjesto, a.Ulica, a.Broj from sala s"
				+ " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID=AdresaID "
				+ " where k.Naziv= ? "
				+ " order by SalaID asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, nazivKina);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new SalaDTO(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4),
						new KinoDTO(rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),
								new AdresaDTO(rs.getInt(9),rs.getString(10), rs.getString(11), rs.getInt(12)))));
			
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

		String query = "INSERT INTO sala VALUES "
				+ " (?, ?, ?, ?, ? ) on duplicate key update Uklonjeno=0 ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sala.getSalaID());
			ps.setInt(2, sala.getBroj());
			ps.setInt(3, sala.getKapacitet());
			ps.setInt(4, 0);
			ps.setInt(5, sala.getKino().getKinoID());

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
	public boolean azurirajSalu(SalaDTO sala) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE sala SET "
				+ "Broj =?, "
				+ " Kapacitet= ? "
				+ " WHERE SalaID=? ";
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
	public boolean obrisiSalu(int KinoID, int broj) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = " update sala set Uklonjeno=1 "
				+ " where Broj = ? "
				+ " and KINO_KinoID= ?  ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, broj);
			ps.setInt(2, KinoID);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close( ps, conn);
		}
		return retVal;
	}
}
