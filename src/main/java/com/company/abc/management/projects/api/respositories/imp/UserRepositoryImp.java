package com.company.abc.management.projects.api.respositories.imp;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.respositories.api.TaskRepository;
import com.company.abc.management.projects.api.respositories.api.UserRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class UserRepositoryImp implements UserRepository{
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
   
	@Override
	public User save(User user) {
		String sql = "INSERT INTO users (uid, username, email) VALUES (?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUid());
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getEmail());
				return ps;
			}
		}, holder);
		user.setUserId(holder.getKey().longValue());
		return user;
	}

	@Override
	public List<User> list() {
	   String sql = "SELECT * FROM users";
	   return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}
	
	@Override
	public Optional<User> getByUid(String uid) {
	    String sql = "SELECT * FROM users WHERE uid = ?";
	    List<User> result = jdbcTemplate.query(sql, new Object[]{uid}, new BeanPropertyRowMapper<>(User.class));
	    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}


	@Override
	public Optional<User> getById(Long id) {
	    String sql = "SELECT * FROM users WHERE user_id = ?";
	    List<User> result = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
	    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}


}
