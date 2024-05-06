package com.company.abc.management.projects.api.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.services.api.UserService;
import com.company.abc.management.projects.api.utils.ResponseResult;
import com.company.abc.management.projects.api.utils.ValidationsApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name="Users")
public class UserRest {
	
	private final UserService userService;
	
	@Operation(summary="Retorna una lista de usuarios")
	@GetMapping()
	private ResponseEntity<ResponseResult<?>> listTasks() {
		List<User> listUsers = userService.list();
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", listUsers, null), HttpStatus.OK);
	}
 

	@Operation(summary="Retorna una usuario por id")
	@GetMapping(value= "/{id}")
	public ResponseEntity<ResponseResult<?>> findById(@PathVariable Long id) {
	    Optional<User> userOptional = userService.getById(id);
	    if (!userOptional.isPresent()) {
	    	return new ResponseEntity<>(new ResponseResult<>(false, "Usuario no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
	    } 
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", userOptional.get(), null), HttpStatus.OK);
	}
	
	@Operation(summary="Retorna una usuario por uid")
	@GetMapping(value= "/uid/{uid}")
	public ResponseEntity<ResponseResult<?>> findById(@PathVariable String uid) {
	    Optional<User> userOptional = userService.getByUid(uid);
	    if (!userOptional.isPresent()) {
	    	return new ResponseEntity<>(new ResponseResult<>(false, "Usuario no encontrado", null, null),
					HttpStatus.BAD_REQUEST);
	    } 
		return new ResponseEntity<>(new ResponseResult<>(true, "OK", userOptional.get(), null), HttpStatus.OK);
	    

	}

	@Operation(summary="Guarda un usuario")
	@PostMapping()
	public ResponseEntity<ResponseResult<?>> save(@Valid @RequestBody User user, BindingResult resultBinding){
		if (resultBinding.hasErrors()) {
			Map<String, String> errors = ValidationsApi.validate(resultBinding);
			return new ResponseEntity<>(new ResponseResult<>(false, "Error al crear usuario", null, errors),
					HttpStatus.BAD_REQUEST);
		}
		User newUser = userService.save(user);
		return new ResponseEntity<>(new ResponseResult<>(true, "Usuario creado exitosamente", newUser, null),
				HttpStatus.OK);
	}
	

}
