package com.company.abc.management.projects.api.respositories.api;
import java.util.List;
import java.util.Optional;
import com.company.abc.management.projects.api.models.User;

public interface UserRepository {
	User save(User user);
	List<User> list();
	Optional<User> getById(Long id);
	Optional<User> getByUid(String uid);
	
}
