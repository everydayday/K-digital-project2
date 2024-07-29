package edu.pnu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Role;
import edu.pnu.domain.User2;
import edu.pnu.persistence.UserRepository;
import edu.pnu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController@RequiredArgsConstructor 
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "10.125.120.250:3000", "*"})
public class UserController {
	private final UserRepository userRepo;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	// @RequestParam String userLoginId, @RequestParam String userPw
		// 회원가입
		@PostMapping("/signup")
		public ResponseEntity<Map<String, String>> signup(@RequestBody User2 user) {
			//User user = new User(userLoginId, userPw);
			System.out.println("here in signup");
			Map<String, String> response = new HashMap<>();
			if(userService.registerUser(user) != null) {	// 회원가입 성공했을 때
				//return "forward:main";
				response.put("status", "success");
				return ResponseEntity.ok(response);
			}
			response.put("status", "failure");
			return ResponseEntity.ok(response);		
		}
		
		
		@PostMapping("/signin")
		public ResponseEntity<Map<String, String>> login(@RequestBody User2 user) {
			// id 존재 확인
			Map<String, String> response = new HashMap<>();
			
			if(userRepo.findByUserLoginId(user.getUserLoginId()) == null) {
				response.put("status", "noid");
				return ResponseEntity.ok(response);
			}

			// id 존재 확인 되었을 시, 비밀번호 맞는지 확인		
			try {
				String answerUserPw = user.getUserPw();
				User2 encodeUser = userRepo.findUserPwByUserLoginId(user.getUserLoginId());	
				String encodeUserPw =  encodeUser.getUserPw();
				
//				logger.debug("here after encodeUserPw {}:", encodeUserPw);
				if(passwordEncoder.matches(answerUserPw,encodeUserPw)){	// 여기 문제
					
					String userLoginid = user.getUserLoginId();
					Role userRole = user.getUserRole();
					
					
					response.put("status", "success");
					response.put("userLoginid", userLoginid);
//					response.put("userRole", userRole);
				
//					logger.debug("here user {}:", user);
					return ResponseEntity.ok(response);	// json형식으로 반환
				}else {
					
					response.put("status", "failure");	// 페이지 렌더링은 react에서 처리
//					logger.debug("here in failure");
					return ResponseEntity.ok(response);	
				}
			}
			catch(Exception e) {
//				logger.error("Exception occurred during login: {} ", e.getMessage());
				System.out.println("error : " + e);
			}
			
			return null;
			
		}
		
		
		// 존재하는 id인지 
		@PostMapping("/checkid")
		public ResponseEntity<Map<String, String>> checkidByPhoneNumber(@RequestBody User2 user){
			System.out.println("here in checkid");
			Map<String, String> response = new HashMap<>();
			String phoneNum = user.getUserPhoneNumber();
			System.out.println("phoneNumb : " + phoneNum);
			if(userService.existByPhoneNumber(user)) {
				response.put("status", "success");
			}
			else {
				response.put("status", "failure");
			}
			
			return ResponseEntity.ok(response);
		}
		
		@PostMapping("/reset")
		public ResponseEntity<Map<String, String>> resetPassword(@RequestBody User2 user){
			Map<String, String> response = new HashMap<>();
			System.out.println("user in resetPassword controller :" + user.getUserLoginId());
			if(userService.updateUserPw(user.getUserLoginId(), user.getUserPw())!=null) {
				response.put("status", "success");
			}
			else {
				response.put("status", "failure");
			}
			
			return ResponseEntity.ok(response);
		}
		
		@GetMapping("/test")
		public String test() {
			System.out.println("Test");
			return "Test";
		}
	
}
