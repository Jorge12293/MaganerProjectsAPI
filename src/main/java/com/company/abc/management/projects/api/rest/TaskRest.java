package com.company.abc.management.projects.api.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.abc.management.projects.api.dto.tasks.TaskProjectDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUpdateDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUpdateStatusDTO;
import com.company.abc.management.projects.api.dto.tasks.TaskUserDTO;
import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.services.api.TaskService;
import com.company.abc.management.projects.api.services.api.UserService;
import com.company.abc.management.projects.api.utils.ResponseResult;
import com.company.abc.management.projects.api.utils.ValidationsApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
@Tag(name = "Tasks")
public class TaskRest {

	private final TaskService taskService;
	private final UserService userService;

	@Operation(summary = "Retorna una lista de tareas")
	@CrossOrigin(origins = "*")
	@GetMapping()
	private ResponseEntity<ResponseResult<?>> listTasks() {
		List<Task> listTasks = taskService.list();
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", listTasks, null), HttpStatus.OK);
	}

	@Operation(summary = "Retorna una lista de tareas por el id del usuario")
	@GetMapping(value = "/user/{id}")
	private ResponseEntity<ResponseResult<?>> listTasksByUserId(@PathVariable Long id) {
		Optional<User> user = userService.getById(id);
		// Verificar si el usuario existe y guardar la tarea si es válido
		if (!user.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Usuario no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
		}
		List<TaskUserDTO> listTasks = taskService.listByUserId(id);
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", listTasks, null), HttpStatus.OK);
	}

	@Operation(summary = "Retorna una tarea")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> findByIdWithProject(@Valid @PathVariable Long id) {
		Optional<TaskProjectDTO> taskOptional = taskService.getByIdDetail(id);
		if (!taskOptional.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Tarea no encontrada", null, null),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", taskOptional.get(), null), HttpStatus.OK);

	}

	@Operation(summary = "Guarda una tarea")
	@PostMapping()
	public ResponseEntity<ResponseResult<?>> save(@Valid @RequestBody Task task, BindingResult resultBinding) {
		if (resultBinding.hasErrors()) {
			Map<String, String> errors = ValidationsApi.validate(resultBinding);
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear tarea", null, errors),
					HttpStatus.BAD_REQUEST);
		}
		// Verificar si el usuario existe
		if (task.getUserId() != null) {
			Optional<User> user = userService.getById(task.getUserId());
			if (!user.isPresent()) {
				return new ResponseEntity<>(new ResponseResult<>(false, "Usuario no encontrado", null, null),
						HttpStatus.BAD_REQUEST);
			}
		}
		Optional<Task> taskSave = taskService.save(task);
		if (!taskSave.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear tarea", null, null),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseResult<>(true, "Tarea creada exitosamente", taskSave, null),
				HttpStatus.OK);
	}

	@Operation(summary = "Actualiza una tarea")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> update(@Valid @RequestBody TaskUpdateDTO taskDto,
			BindingResult resultBinding, @PathVariable Long id) {
		if (resultBinding.hasErrors()) {
			Map<String, String> errors = ValidationsApi.validate(resultBinding);
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al actualizar tarea", null, errors),
					HttpStatus.BAD_REQUEST);
		}
		// Verificar si el id existe
		Optional<Task> taskFound = taskService.getById(id);
		if (!taskFound.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Tarea no encontrada", null, null),
					HttpStatus.BAD_REQUEST);
		}

		Optional<Task> taskUpdate = taskService.update(taskFound.get(), taskDto, id);
		return new ResponseEntity<>(new ResponseResult<>(true, "Tarea actualizada exitosamente", taskUpdate, null),
				HttpStatus.OK);
	}

	@Operation(summary = "Actualiza una tarea")
	@PutMapping(value = "/status/{id}")
	public ResponseEntity<ResponseResult<?>> updateStatus(@RequestBody TaskUpdateStatusDTO statusDTO, @PathVariable Long id) {
		Optional<Task> taskFound = taskService.getById(id);
		if (!taskFound.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Tarea no encontrada", null, null),
					HttpStatus.BAD_REQUEST);
		}
		if(ValidationsApi.isValidStatus(statusDTO.getStatus())) {				
			Status newStatus = Status.valueOf(statusDTO.getStatus());
			Optional<Task> newTask = taskService.updateStatus(taskFound.get(),newStatus,id);
			return new ResponseEntity<>(
					new ResponseResult<>(true, "Tarea actualizada status exitosamente", newTask, null), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResponseResult<>(false, "Error: Valor del status inválido", null, null),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@Operation(summary = "Elimina una tarea")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> delete(@PathVariable Long id) {
		Optional<Task> taskFound = taskService.getById(id);
		// Verificar si el id existe
		if (!taskFound.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Tarea no encontrada", null, null),
					HttpStatus.BAD_REQUEST);
		}
		taskService.delete(id);
		return new ResponseEntity<>(new ResponseResult<>(true, "Tarea eliminada exitosamente", taskFound, null),
				HttpStatus.OK);
	}

}
