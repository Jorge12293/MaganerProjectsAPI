package com.company.abc.management.projects.api.services.api;
import com.company.abc.management.projects.api.dto.projects.ProjectTasksDTO;
import com.company.abc.management.projects.api.dto.projects.ProjectUpdateDTO;
import com.company.abc.management.projects.api.models.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
	Optional<Project> save(Project project);
	Optional<Project> update(Project project,ProjectUpdateDTO projectDto,Long id);
	void delete(Long id);
	List<Project> list();
	Optional<Project> getById(Long id);
	Optional<ProjectTasksDTO> getByIdDetail(Long id);
}
