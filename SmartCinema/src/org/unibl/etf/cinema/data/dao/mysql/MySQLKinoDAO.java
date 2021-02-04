package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLKinoDAO implements KinoDAO{
	
	@Override
	public List<KinoDTO> svaKina(){
		List<KinoDTO> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select KinoID, k.Naziv, Email, Telefon, AdresaID, a.Naziv, Broj, a.Uklonjeno, k.Uklonjeno from kino k"
				+ " inner join adresa a on a.AdresaID=k.ADRESA_AdresaID  "
				+ " order by k.Naziv asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new KinoDTO(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
						new AdresaDTO(rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)),rs.getInt(9)));
			
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
		KinoDTO retVal = new KinoDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select KinoID, k.Naziv, Email, Telefon, AdresaID, a.Naziv, Broj, a.Uklonjeno, k.Uklonjeno from kino k\"\r\n" + 
				"				+ \" inner join adresa a on a.AdresaID=k.ADRESA_AdresaID  " + 
				" where k.Naziv like ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					naziv);
			rs = ps.executeQuery();

			
				retVal=new KinoDTO(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
						new AdresaDTO(rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)),rs.getInt(9));
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override // radi na duplicate key, false je jer se ne doda, nego azurira
	public boolean dodajKino(KinoDTO kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO kino VALUES "
				+ " (?, ?, ?, ?, ? ,? ) on duplicate key update Uklonjeno=0 ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kino.getKinoID());
			ps.setString(2, kino.getNaziv());
			ps.setString(3, kino.getEmail());
			ps.setString(4, kino.getTelefon());
			ps.setInt(5, kino.getAdresa().AdresaID);
			ps.setBoolean(6, false);

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
	public boolean azurirajKino(KinoDTO kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE kino SET "
				+ "Naziv =? ,"
				+ "Email = ? ,"
				+ "Telefon = ? "
				+ "WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, kino.getNaziv());
			ps.setString(2, kino.getEmail());
			ps.setString(3, kino.getTelefon());
			ps.setString(4, kino.getNaziv());

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
	public boolean obrisiKino(String naziv) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = " update kino set Uklonjeno=1"
					+ " where Naziv = ? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, naziv);

				retVal = ps.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				DBUtil.close( ps, conn);
			}
			return retVal;
			
		}
	public static void main(String args[]) {
		MySQLKinoDAO kino=new MySQLKinoDAO();
		List<KinoDTO> ki=kino.svaKina();
		
		for(KinoDTO k:ki)
			System.out.println(k);
		
		
			
	}

}
