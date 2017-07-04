package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public abstract class UpdateJdbcTemplate {
	public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectionManager.getConnection();
        	String sql = createQueryforUpdate();
        	pstmt = con.prepareStatement(sql);
        	setValueForUpdate(user, pstmt);
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

	abstract protected void setValueForUpdate(User user, PreparedStatement pstmt) throws SQLException;
	abstract protected String createQueryforUpdate();
}
