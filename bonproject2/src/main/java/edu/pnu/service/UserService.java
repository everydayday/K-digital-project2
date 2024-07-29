package edu.pnu.service;

import org.springframework.stereotype.Service;

import edu.pnu.domain.User;


public interface UserService {
	
	User getUser(String username);
	User registerUser(User user);
	boolean existByPhoneNumber(User user);
	User updateUserPw(String userLoginId, String userPw);
}
