package com.company.abc.management.projects.api.services.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.abc.management.projects.api.dto.projects.ProjectTasksDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskProjectDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUpdateDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUserDTO;
import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.respositories.api.ProjectRepository;
import com.company.abc.management.projects.api.respositories.api.TaskRepository;
import com.company.abc.management.projects.api.services.api.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;

	@Override
	public Optional<Task> save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Optional<Task> update(Task task, TaskUpdateDTO newTask, Long id) {
		Task taskUpdate = new Task();
		taskUpdate.setTaskId(task.getTaskId());
		taskUpdate.setProjectId(task.getProjectId());
		// Atributos Actualizar
		taskUpdate.setTitle(newTask.getTitle());
		taskUpdate.setDescription(newTask.getDescription());
		taskUpdate.setStatus(newTask.getStatus());
		if (newTask.getUserId() != null) {
			taskUpdate.setUserId(newTask.getUserId());
		} else {
			taskUpdate.setUserId(task.getUserId());
		}

		return taskRepository.update(taskUpdate, id);
	}

	@Override
	public void delete(Long id) {
		taskRepository.delete(id);
	}

	@Override
	public List<Task> list() {
		return taskRepository.list();
	}

	@Override
	public Optional<Task> getById(Long id) {
		return taskRepository.getById(id);
	}

	@Override
	public Optional<TaskProjectDTO> getByIdDetail(Long id) {

		Optional<Task> task = taskRepository.getById(id);
		if (!task.isPresent()) {
			return Optional.empty();
		}
		Optional<Project> project = projectRepository.getById(id);
		if (!project.isPresent()) {
			return Optional.empty();
		}
		TaskProjectDTO taskDto = new TaskProjectDTO(task.get(), project.get());
		return Optional.of(taskDto);
	}

	@Override
	public List<TaskUserDTO> listByUserId(Long userId) {
	
		List<Task> listTask = taskRepository.listByUserId(userId);
		if(listTask.isEmpty()) {
			return Collections.emptyList();
		}
		List<TaskUserDTO> listTaskUserDTO = new ArrayList<>(); 
		for (Task task : listTask) {
			TaskUserDTO taskUserDTO = new TaskUserDTO();
		    taskUserDTO.setTaskId(task.getTaskId());
		    taskUserDTO.setTitle(task.getTitle());
		    taskUserDTO.setDescription(task.getDescription());
		    taskUserDTO.setStatus(task.getStatus());
	        if(task.getProjectId() != null) {
	        	taskUserDTO.setProjectId(task.getProjectId());
	        	Optional<Project> project = projectRepository.getById(task.getProjectId());
	        	if (project.isPresent()) {
	        		 taskUserDTO.setProject(project.get());
				}
	        }
	        listTaskUserDTO.add(taskUserDTO);
		}
		return listTaskUserDTO;
	}

	@Override
	public Optional<Task> updateStatus(Task task, Status status, Long id) {
		task.setStatus(status);
		return taskRepository.update(task, id);
	}

}
