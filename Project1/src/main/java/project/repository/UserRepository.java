package project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import project.domain.User;

@Repository
public class UserRepository{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	 public User create(final User user) {
	        final String sql = "insert into user (email,password,name,surname) values (?,?,?,?)";

	        KeyHolder holder = new GeneratedKeyHolder();
	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, user.getEmail());
	                ps.setString(2, user.getPassword());
	                ps.setString(3, user.getName());
	                ps.setString(4, user.getSurname());
	                return ps;
	            }
	        }, holder);
	        return user;
	 	}
}

