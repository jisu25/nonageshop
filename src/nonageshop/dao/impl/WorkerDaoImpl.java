package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageshop.dao.WorkerDao;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Worker;

public class WorkerDaoImpl implements WorkerDao {

	private static final WorkerDaoImpl instance = new WorkerDaoImpl();
	
	private WorkerDaoImpl() {}
	
	public static WorkerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int workerCheck(Worker worker) {
		String sql = "SELECT 1 FROM WORKER WHERE ID = ? AND PWD = ? ";
		
		try(Connection con = JndiDs.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, worker.getId());
			pstmt.setString(2, worker.getPwd());
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return rs.getInt(1);
				}
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return 0;
	}

}
