package com.company.abc.management.projects.api.respositories.imp;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.respositories.api.TaskRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class TaskRepositoryImp implements TaskRepository{
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
    
	@Override
	public Optional<Task> save(Task task) {
		String sql = "INSERT INTO tasks (title, description, status, project_id) VALUES (?, ?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task.getTitle());
				ps.setString(2, task.getDescription());
				ps.setString(3, task.getStatus().toString());
				ps.setLong(4, task.getProjectId());
				return ps;
			}
		}, holder);
		task.setTaskId(holder.getKey().longValue());
		return Optional.of(task);
	}


	@Override
	public Optional<Task> update(Task task,Long id) {
	  if(task.getUserId() == null) {
		  String sql = "UPDATE tasks SET title = ?, description = ?, status = ? , project_id = ?  WHERE task_id = ?";
		  jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus().toString(), task.getProjectId(), id);
		  return Optional.of(task);		  
	  }else {
		  String sql = "UPDATE tasks SET title = ?, description = ?, status = ? , project_id = ? ,user_id = ? WHERE task_id = ?";
		  jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus().toString(), task.getProjectId(),task.getUserId(), id);
		  return Optional.of(task);		
	  }	
	}

	@Override
	public void delete(Long id) {
	    String sql = "DELETE FROM tasks WHERE task_id = ?";
        jdbcTemplate.update(sql, id);	
	}

	@Override
	public List<Task> list() {
	   String sql = "SELECT * FROM tasks";
	   return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
	}
	
	@Override
	public List<Task> listByProjectId(Long proyectId) {
	    String sql = "SELECT * FROM tasks WHERE project_id = ?";
	    List<Task> result = jdbcTemplate.query(sql, new Object[]{proyectId}, new BeanPropertyRowMapper<>(Task.class));
		return result;
	}

	@Override
	public List<Task> listByUserId(Long userId) {
	    String sql = "SELECT * FROM tasks WHERE user_id = ?";
	    List<Task> result = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Task.class));
		return result;
	}
	
	@Override
	public Optional<Task> getById(Long id) {
	    String sql = "SELECT * FROM tasks WHERE task_id = ?";
	    List<Task> result = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
	    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}
	


	@Override
	public List<Task> listByNameStatus(String name, List<Status> listStatus) {
	    String query = "";
	    if (name == null || name.isEmpty()) {
	        if (listStatus != null && !listStatus.isEmpty()) {
	            StringBuilder dataStatusBuilder = new StringBuilder();
	            listStatus.forEach(status -> {
	                dataStatusBuilder.append("'").append(status).append("',");
	            });
	            String dataStatus = dataStatusBuilder.substring(0, dataStatusBuilder.length() - 1);

	            query = "SELECT * FROM tasks WHERE status IN (" + dataStatus + ")";
	            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Task.class));
	        } else {
	            return Collections.emptyList();
	        }
	    } else {
	        if (listStatus != null && !listStatus.isEmpty()) {
	            StringBuilder dataStatusBuilder = new StringBuilder();
	            listStatus.forEach(status -> {
	                dataStatusBuilder.append("'").append(status).append("',");
	            });
	            String dataStatus = dataStatusBuilder.substring(0, dataStatusBuilder.length() - 1);
	            query = "SELECT * FROM tasks WHERE status IN (" + dataStatus + ") OR UPPER(title) LIKE ? ";
	            return jdbcTemplate.query(query, new Object[]{"%" + name.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(Task.class));
	        } else {
	            query = "SELECT * FROM tasks WHERE UPPER(title) LIKE ? ";
	            return jdbcTemplate.query(query, new Object[]{"%" + name.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(Task.class));
	        }
	    }
	}

}
