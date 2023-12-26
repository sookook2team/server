package sookook.daybyday.repository;

import org.springframework.data.repository.CrudRepository;
import sookook.daybyday.entity.User;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {
}
