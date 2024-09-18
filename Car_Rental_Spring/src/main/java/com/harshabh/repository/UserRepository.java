package com.harshabh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshabh.entity.User;
import com.harshabh.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findFirstByEmail(String email);

	User findByUserRole(UserRole userRole);

}
