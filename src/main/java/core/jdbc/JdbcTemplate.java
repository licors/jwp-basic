package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
	
	public void update(String query) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectionManager.getConnection();
        	String sql = query;
        	pstmt = con.prepareStatement(sql);
        	setValues(pstmt);
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
	
	public Object queryForObject(String query) throws SQLException {
        List<Object> results = query(query);
        if(results.isEmpty())
        	return null;
        return results.get(0);
    }
	
	public List query(String query) throws SQLException {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = query;
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);
            rs = pstmt.executeQuery();
            
            List<Object> result = new ArrayList<Object>();
        	while(rs.next()) {
        		result.add(mapRow(rs));
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
	
	abstract protected Object mapRow(ResultSet rs) throws SQLException;
	abstract protected void setValues(PreparedStatement pstmt) throws SQLException;
}
