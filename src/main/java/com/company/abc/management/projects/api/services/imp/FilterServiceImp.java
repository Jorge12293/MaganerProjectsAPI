package com.company.abc.management.projects.api.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.abc.management.projects.api.dto.filters.FilterProjectsTasksDTO;
import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.respositories.api.ProjectRepository;
import com.company.abc.management.projects.api.respositories.api.TaskRepository;
import com.company.abc.management.projects.api.services.api.FilterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterServiceImp implements FilterService{
	
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	
	@Override
	public FilterProjectsTasksDTO filterProjectsTasksDTO(String name, List<Status> statusList) {
		FilterProjectsTasksDTO filterProjectsTasksDTO = new FilterProjectsTasksDTO();	
		filterProjectsTasksDTO.setListProject(projectRepository.listByName(name));
		filterProjectsTasksDTO.setListTask(taskRepository.listByNameStatus(name, statusList));
		return filterProjectsTasksDTO;
	}

	


}
