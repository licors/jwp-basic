package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getName());
		        pstmt.setString(4, user.getEmail()); 
			}
		};
    	JdbcTemplate insertJdbcTemplate = new JdbcTemplate();
		insertJdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", pss);
    }
    
    public void update(User user) throws SQLException {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getUserId());
		    	pstmt.setString(2, user.getPassword());
		    	pstmt.setString(3, user.getName());
		    	pstmt.setString(4, user.getEmail());
		    	pstmt.setString(5, user.getUserId());
			}
		};
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate();
		updateJdbcTemplate.update("UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? WHERE userId = ?", pss);
    }

    public List<User> findAll() throws SQLException {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
			}
		};
    	RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
        				rs.getString("email"));
			}
		};
    	
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		return (List<User>) selectJdbcTemplate.query("SELECT userId, password, name, email FROM USERS", pss, rm);
    }
   
    

    public User findByUserId(String userId) throws SQLException {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, userId);
			}
		};
		
		RowMapper rm = new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
		                rs.getString("email"));
			}
		};
        JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		return (User) selectJdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId = ?", pss, rm);
    }
}
