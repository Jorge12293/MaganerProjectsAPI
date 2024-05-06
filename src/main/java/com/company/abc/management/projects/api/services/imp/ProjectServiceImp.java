package com.company.abc.management.projects.api.services.imp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.company.abc.management.projects.api.dto.projects.ProjectTasksDTO;
import com.company.abc.management.projects.api.dto.projects.ProjectUpdateDTO;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.respositories.api.ProjectRepository;
import com.company.abc.management.projects.api.respositories.api.TaskRepository;
import com.company.abc.management.projects.api.respositories.api.UserRepository;
import com.company.abc.management.projects.api.services.api.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

	private final ProjectRepository projectRepository;
	private final TaskRepository taskRepository;
	private final UserRepository  userRepository;

	@Override
	public Optional<Project> save(Project project) {
		Project newProyect = new Project();
		newProyect.setName(project.getName());
		newProyect.setDescription(project.getDescription());
		return projectRepository.save(newProyect);
	}

	@Override
	public Optional<Project> update(Project project, ProjectUpdateDTO projectDto, Long id) {
		Project updateProject = new Project(project.getProjectId(), projectDto.getName(), projectDto.getDescription());
		return projectRepository.update(updateProject, id);
	}

	@Override
	public void delete(Long id) {
		projectRepository.delete(id);
	}

	@Override
	public List<Project> list() {
		return projectRepository.list();
	}

	@Override
	public Optional<Project> getById(Long id) {
		return projectRepository.getById(id);
	}

	@Override
	public Optional<ProjectTasksDTO> getByIdDetail(Long id) {
		Optional<Project> project = projectRepository.getById(id);
		if (project.isPresent()) {
			List<Task> listTask = taskRepository.listByProjectId(id);
			
			 for (Task task : listTask) {
		            Optional<User> user = userRepository.getById(task.getUserId());
		            if (user.isPresent()) {
		                task.setUser(user.get());
		            }
		        }
		        
			
			ProjectTasksDTO projectDto = new ProjectTasksDTO(project.get(), listTask);
			return Optional.of(projectDto);
		}
		return Optional.empty();
	}

}
