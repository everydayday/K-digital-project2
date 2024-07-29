package edu.pnu.service;

import org.springframework.stereotype.Service;

import edu.pnu.domain.User2;


public interface UserService {
	
	User2 getUser(String username);
	User2 registerUser(User2 user);
	boolean existByPhoneNumber(User2 user);
	User2 updateUserPw(String userLoginId, String userPw);
}
