package com.company.abc.management.projects.api.respositories.imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.respositories.api.ProjectRepository;


@Repository
public class ProjectRepositoryImp implements ProjectRepository {
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
    
	@Override
	public Optional<Project> save(Project project) {
		  String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, project.getName());
				ps.setString(2, project.getDescription());
				return ps;
			}
		}, holder);
		project.setProjectId(holder.getKey().longValue());
		return Optional.of(project);
	}


	@Override
	public Optional<Project> update(Project project,Long id) {
	  String sql = "UPDATE projects SET name = ?, description = ? WHERE project_id = ?";
	  jdbcTemplate.update(sql, project.getName(), project.getDescription(),id);
	  return Optional.of(project);
	}

	@Override
	public void delete(Long id) {
	    String sql = "DELETE FROM projects WHERE project_id = ?";
        jdbcTemplate.update(sql, id);	
	}

	@Override
	public List<Project> list() {
	   String sql = "SELECT * FROM projects";
	   return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Project.class));
	}
	
	

	@Override
	public Optional<Project> getById(Long id) {
	    String sql = "SELECT * FROM projects WHERE project_id = ?";
	    List<Project> result = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Project.class));
	    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}

	@Override
	public List<Project> listByName(String name) {
		if(name != "" && name!=null) {
	        String query = "SELECT * FROM projects WHERE UPPER(name) LIKE ? ";
	        return jdbcTemplate.query(query, new Object[]{ "%" + name.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(Project.class));
		}
        return Collections.emptyList();
	}

}



