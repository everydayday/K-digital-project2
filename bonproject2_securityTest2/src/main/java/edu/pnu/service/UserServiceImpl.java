package edu.pnu.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Role;
import edu.pnu.domain.User2;
import edu.pnu.persistence.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public User2 getUser(String userLogInId) {
		// TODO Auto-generated method stub
		User2 findUser = userRepo.findByUserLoginId(userLogInId);
//		if(findUser.isPresent())
//			return findUser.get();
//		return null;
		// null 일 시 null 값 return
		return findUser;
	}
	
	@Override
	public User2 registerUser(User2 user) {
		String userloginid = user.getUserLoginId();
		if(userRepo.findByUserLoginId(userloginid) == null) {	// 찾으니깐 없을 때 값을 저장
			System.out.println("userLoginid : " + user.getUserLoginId());
			System.out.println("userpw" + user.getUserPw());
			System.out.println("userRole" + user.getUserRole());
			User2 encodedUser =  User2.builder()
					.userLoginId(user.getUserLoginId())
					.userName(user.getUserName())
					.userPw(passwordEncoder.encode(user.getUserPw())) // 암호화해서 저장
					.userPhoneNumber(user.getUserPhoneNumber())
					.userRole(user.getUserRole())
					.build();
			// 될 법 한데 오류 발생
//			user.setUserPw(passwordEncoder.encode(user.getUserPw()));	
			return userRepo.save(encodedUser);
		}
		
		// 어떤 값을 return해야 할까?
		return null;
	}
	
	@Override
	public boolean existByPhoneNumber(User2 user) {
		
		
		List<User2> savedUser = userRepo.findUsersByUserPhoneNumber(user.getUserPhoneNumber());
		
		System.out.println("savedUser[0]" + savedUser.get(0).getUserLoginId());
		System.out.println("userLoginId : " + user.getUserLoginId());
		System.out.println("userPhoneNumber " + user.getUserPhoneNumber());
		
		String userId = user.getUserLoginId();

		for(int i = 0; i < savedUser.size(); i++) {
			User2 oneUser = savedUser.get(i);
			String savedId = oneUser.getUserLoginId();
			if(savedId.equals(userId)) return true;	
		}
		
		return false;

		
	}
	
	@Override
	public User2 updateUserPw(String userLoginId, String userPw) {
		User2 user = userRepo.findByUserLoginId(userLoginId);
		System.out.println("user in updateUserPw : " + user);
		if(user == null)
			return null;
		else {
			System.out.println("before encode the password : " + userPw);
			user.setUserPw(passwordEncoder.encode(userPw));
			userRepo.save(user);
			return user;
		}
	}

}
