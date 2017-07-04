package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

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

	abstract protected void setValues(PreparedStatement pstmt) throws SQLException;
}
