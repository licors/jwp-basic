package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	
	public void update(String query , PreparedStatementSetter pss) throws DataAccessException {
        try(Connection con = ConnectionManager.getConnection(); 
        		PreparedStatement pstmt = con.prepareStatement(query);) {
        	
        	pss.setValues(pstmt);
        	pstmt.executeUpdate();
        } catch(SQLException e) {
        	throw new DataAccessException(e);
        }
    }
	
	public void update(String query, Object...parameters) {
		update(query, createPreparedStatementSetter(parameters));
	}

	public <T> T queryForObject(String query, PreparedStatementSetter pss, RowMapper<T> rm) {
        List<T> results = query(query, pss, rm);
		if(results.isEmpty())
        	return null;
        return results.get(0);
    }
	
	public <T> T queryForObject(String query, RowMapper<T> rm, Object...parameters) {
		return queryForObject(query, createPreparedStatementSetter(parameters), rm);
	}
	
	public <T> List<T> query(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws DataAccessException {
        ResultSet rs = null;
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(query); ) {
        	
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            
            List<T> result = new ArrayList<T>();
        	while(rs.next()) {
        		result.add(rm.mapRow(rs));
        	}
            return result;
            
        } catch(SQLException e) {
        	throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException(e);
				}
            }
        }
	}
	
	public <T> List<T> query(String query, RowMapper<T> rm, Object...parameters) {
		return query(query, createPreparedStatementSetter(parameters), rm);
	}
	
	private PreparedStatementSetter createPreparedStatementSetter(Object...parameters) {
		// TODO Auto-generated method stub
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
	        	for(int i=0; i < parameters.length; i++) {
	        		pstmt.setObject(i+1, parameters[i]);
	        	}
			}
		};
	}
}
