package com.company.abc.management.projects.api.respositories.api;

import java.util.List;
import java.util.Optional;
import com.company.abc.management.projects.api.models.Project;

public interface ProjectRepository {
	Optional<Project> save(Project project);
	Optional<Project> update(Project projectDto,Long id);
	void delete(Long id);
	List<Project> list();
	List<Project> listByName(String name);
	Optional<Project> getById(Long id);
}
