package com.company.abc.management.projects.api.services.api;
import com.company.abc.management.projects.api.dto.tasks.TaskProjectDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUpdateDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUserDTO;
import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
	Optional<Task> save(Task task);
	Optional<Task> update(Task task,TaskUpdateDTO taskDto,Long id);
	Optional<Task> updateStatus(Task task,Status status,Long id);
	void delete(Long id);
	List<Task> list();
	List<TaskUserDTO> listByUserId(Long userId);
	Optional<Task> getById(Long id);
	Optional<TaskProjectDTO> getByIdDetail(Long id);
}
