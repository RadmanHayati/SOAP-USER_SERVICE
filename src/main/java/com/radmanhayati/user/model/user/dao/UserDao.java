package com.radmanhayati.user.model.user.dao;


import com.radmanhayati.user.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
