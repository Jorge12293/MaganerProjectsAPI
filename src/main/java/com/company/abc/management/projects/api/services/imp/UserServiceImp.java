package com.company.abc.management.projects.api.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.company.abc.management.projects.api.models.User;
import com.company.abc.management.projects.api.respositories.api.UserRepository;
import com.company.abc.management.projects.api.services.api.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
	private final UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> list() {
		return userRepository.list();
	}

	@Override
	public Optional<User> getByUid(String uid) {
		return userRepository.getByUid(uid);
	}

	@Override
	public Optional<User> getById(Long uid) {
		return userRepository.getById(uid);
	}

}
