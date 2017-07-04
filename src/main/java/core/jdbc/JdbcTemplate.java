package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	
	public void update(String query , PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectionManager.getConnection();
        	String sql = query;
        	pstmt = con.prepareStatement(sql);
        	pss.setValues(pstmt);
        	pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
	
	public Object queryForObject(String query, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
        List results = query(query, pss, rm);
        if(results.isEmpty())
        	return null;
        return results.get(0);
    }
	
	public List query(String query, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = query;
            pstmt = con.prepareStatement(sql);
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            
            List<Object> result = new ArrayList<Object>();
        	while(rs.next()) {
        		result.add(rm.mapRow(rs));
        	}
            return result;
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
	}
	
	
	
}
