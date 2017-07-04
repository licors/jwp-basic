package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.InsertJdbcTemplate;
import core.jdbc.UpdateJdbcTemplate;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        InsertJdbcTemplate insertJdbcTemplate = new InsertJdbcTemplate() {
			
			@Override
			protected void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getName());
		        pstmt.setString(4, user.getEmail()); 
			}
			
			@Override
			protected String createQueryForInsert() {
				// TODO Auto-generated method stub
				return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
			}
		};
		insertJdbcTemplate.insert(user);
    }
    
    public void update(User user) throws SQLException {
        UpdateJdbcTemplate updateJdbcTemplate = new UpdateJdbcTemplate() {
			
			@Override
			protected void setValueForUpdate(User user, PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		    	pstmt.setString(2, user.getPassword());
		    	pstmt.setString(3, user.getName());
		    	pstmt.setString(4, user.getEmail());
		    	pstmt.setString(5, user.getUserId());
			}
			
			@Override
			protected String createQueryforUpdate() {
				// TODO Auto-generated method stub
				return "UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? WHERE userId = ?";
			}
		};
		updateJdbcTemplate.update(user);
    }

    public List<User> findAll() throws SQLException {
        Connection con = null;
        Statement stmt = null;
//        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con = ConnectionManager.getConnection();
        	String sql = "SELECT userId, password, name, email FROM USERS";
        	stmt = con.createStatement();
        	rs = stmt.executeQuery(sql);
        	
        	List<User> users = new ArrayList<User>();
        	if(rs.next()) {
        		users.add(new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
        				rs.getString("email")));
        	}
        	return users;
        } finally {
        	if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
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
