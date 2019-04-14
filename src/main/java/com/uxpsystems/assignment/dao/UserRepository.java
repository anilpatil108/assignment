package com.uxpsystems.assignment.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.entity.UserStatus;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.status = ?1 and u.userName = ?2")
	public User findUserByStatusAndName(UserStatus status, String userName);

}
