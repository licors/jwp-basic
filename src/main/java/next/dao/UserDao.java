package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
			
			@Override
			protected void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getName());
		        pstmt.setString(4, user.getEmail()); 
			}

			@Override
			protected Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		insertJdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }
    
    public void update(User user) throws SQLException {
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
			
			@Override
			protected void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		    	pstmt.setString(2, user.getPassword());
		    	pstmt.setString(3, user.getName());
		    	pstmt.setString(4, user.getEmail());
		    	pstmt.setString(5, user.getUserId());
			}

			@Override
			protected Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		updateJdbcTemplate.update("UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? WHERE userId = ?");
    }

    public List<User> findAll() throws SQLException {
    	
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
			
			@Override
			protected void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
        				rs.getString("email"));
			}
		};
		return (List<User>) selectJdbcTemplate.query("SELECT userId, password, name, email FROM USERS");
    }
   
    

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
			
			@Override
			protected void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, userId);
			}
			
			@Override
			protected User mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
		                rs.getString("email"));
			}
		};
		return (User) selectJdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId = ?");
    }
}
