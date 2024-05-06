package com.company.abc.management.projects.api.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.abc.management.projects.api.dto.projects.ProjectTasksDTO;
import com.company.abc.management.projects.api.dto.projects.ProjectUpdateDTO;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.services.api.ProjectService;
import com.company.abc.management.projects.api.utils.ResponseResult;
import com.company.abc.management.projects.api.utils.ValidationsApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Tag(name = "Projects")
public class ProjectRest {
	private final ProjectService projectService;

	@Operation(summary = "Retorna una lista de proyectos")
	@GetMapping()
	private ResponseEntity<ResponseResult<?>> list() {
		List<Project> listProjects = projectService.list();
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", listProjects, null), HttpStatus.OK);
	}

	@Operation(summary = "Retorna un proyecto")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> findByIdWithTasks(@PathVariable Long id) {
		Optional<ProjectTasksDTO> projectOptional = projectService.getByIdDetail(id);
		if (!projectOptional.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Proyecto no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", projectOptional.get(), null), HttpStatus.OK);
	}

	@Operation(summary = "Crea un proyecto")
	@PostMapping()
	public ResponseEntity<ResponseResult<?>> save(@Valid @RequestBody Project project, BindingResult resultBinding) {
		if (resultBinding.hasErrors()) {
			Map<String, String> errors = ValidationsApi.validate(resultBinding);
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear proyecto", null, errors),
					HttpStatus.BAD_REQUEST);
		}
		Optional<Project> projectSave = projectService.save(project);
		if (!projectSave.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear proyecto", null, null),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseResult<>(true, "Proyecto creado exitosamente", projectSave, null),
				HttpStatus.OK);
	}

	@Operation(summary = "Actualiza un proyecto")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> update(@Valid @RequestBody ProjectUpdateDTO projectDto,
			BindingResult resultBinding, @PathVariable Long id) {
		if (resultBinding.hasErrors()) {
			Map<String, String> errors = ValidationsApi.validate(resultBinding);
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear proyecto", null, errors),
					HttpStatus.BAD_REQUEST);
		}
		Optional<Project> projectFound = projectService.getById(id);
		if (!projectFound.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Proyecto no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
		}
		Optional<Project> proyectoUpdate = projectService.update(projectFound.get(), projectDto, id);
		return new ResponseEntity<>(new ResponseResult<>(true, "Proyecto actualizada exitosamente", proyectoUpdate, null),
				HttpStatus.OK);
	}

	@Operation(summary = "Elimina un proyecto")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseResult<?>> delete(@PathVariable Long id) {
		Optional<Project> projectFound = projectService.getById(id);
		// Verificar si el id existe
		if (!projectFound.isPresent()) {
			return new ResponseEntity<>(new ResponseResult<>(false, "Proyecto no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
		}
		projectService.delete(id);
		return new ResponseEntity<>(new ResponseResult<>(true, "Proyecto eliminado exitosamente", projectFound, null),
				HttpStatus.OK);
	}

}
