package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.VrstaSjedistaDAO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLVrstaSjedistaDAO implements VrstaSjedistaDAO{
	@Override
	public List<VrstaSjedistaDTO> sveVrsteSjedista(){
		List<VrstaSjedistaDTO> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select VrstaSjedistaID, Naziv, Uklonjeno from vrsta_sjedista "
				+ " order by VrstaSjedistaID asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new VrstaSjedistaDTO(rs.getInt(1),rs.getString(2), rs.getInt(3)));
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override
	public boolean dodajVrstuSjedista(VrstaSjedistaDTO vs) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO vrsta_sjedista VALUES "
				+ " (?, ?, ? ) on duplicate key update Uklonjeno=0 ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, vs.getVrstaSjedistaID());
			ps.setString(2, vs.getNaziv());
			ps.setInt(3, 0);
			

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
	public boolean azurirajVrstuSjedista(VrstaSjedistaDTO vs) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE vrsta_sjedista SET "
				+ " Naziv =? "
				+ " WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, vs.getVrstaSjedistaID());
			ps.setString(2, vs.getNaziv());
			ps.setInt(3, vs.isUklonjeno());
			

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
	public boolean obrisiVrstuSjedista(String naziv) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = " update vrsta_sjedista set Uklonjeno=1"
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
		MySQLVrstaSjedistaDAO vs=new MySQLVrstaSjedistaDAO();
		List<VrstaSjedistaDTO> vrste=vs.sveVrsteSjedista();
		
		/*VrstaSjedistaDTO vrsta=new VrstaSjedistaDTO(7, "VIP VIP sjedista", 0);
		System.out.println(vs.dodajVrstuSjedista(vrsta));
		*/
		for(VrstaSjedistaDTO v:vrste)
			System.out.println(v);
		
		
		
		
	}

}
