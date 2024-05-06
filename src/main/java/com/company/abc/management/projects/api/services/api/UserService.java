package com.company.abc.management.projects.api.services.api;
import com.company.abc.management.projects.api.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	User save(User user);
	List<User> list();
	Optional<User> getById(Long uid);
	Optional<User> getByUid(String uid);
}
