package com.hcl.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailIdAndPasscode(String emailId, String passcode);
	User findByEmailId(String emailId);

}
