package edu.pnu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.persistence.BonRepository;
import edu.pnu.service.BonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 메서드가 반환하는 값을 JSON 또는 XML 형식으로 변환하여 HTTP 응답 본문에 직접 작성
@RestController@RequiredArgsConstructor @Slf4j
@CrossOrigin(origins = "http://localhost:3000") // port가 달라서 발생하는 오류 해결책
public class BonController {
	
	private final BonService bonService;
	private final BonRepository bonRepo;
//	private final PasswordEncoder passwordEncoder;
//	private final Logger logger = LoggerFactory.getLogger(UserController.class);
//	
	
	
	@GetMapping("/flows")
	public ResponseEntity<?> getFlows(){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
		
		return ResponseEntity.ok(bonService.getFlow());
	}
	
	@GetMapping("/flowsbydate")
	public ResponseEntity<?> getFlowsByDate(String date, char jeosuji){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
//		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
//		// String 타입을 Date 타입으로 변환
//		System.out.println("here");
//		Date formatDate;
//		try {
//			formatDate = dtFormat.parse(date);
//			// Date 타입의 변수를 새롭게 지정한 포맷으로 변환
//			String strNewDtFormat = newDtFormat.format(formatDate);			
//			return ResponseEntity.ok(bonService.getFlowByDateContaining(strNewDtFormat));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		return ResponseEntity.ok(bonService.getFlowByDateContaining(date, jeosuji));
		
		
		
	}
		
//	@PostMapping("/userlogin")
//	public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
//		logger.debug("here user {}:", user);
//		// id 존재 확인
//		
//		if(userRepo.findByUserLoginId(user.getUserLoginId()) == null)
//			return null;
//		logger.debug("here after if statement :");	// ok
//		logger.debug("user.getUserLoginId() {} :", user.getUserLoginId()); // ok
//		// id 존재 확인 되었을 시, 비밀번호 맞는지 확인		
//		try {
//			String answerUserPw = user.getUserPw();
//			logger.debug("here after answerUserPw {} :" , answerUserPw); // ok
//			
//			User encodeUser = userRepo.findUserPwByUserLoginId(user.getUserLoginId());	// 여기가 문제
//			String encodeUserPw =  encodeUser.getUserPw();
//			logger.debug("here after encodeUserPw {}:", encodeUserPw);
//			
//			Map<String, String> response = new HashMap<>();
//			
//			if(passwordEncoder.matches(answerUserPw,encodeUserPw)){
//				response.put("status", "success");
//				logger.debug("here user {}:", user);
//				return ResponseEntity.ok(response);	// json형식으로 반환
//			}else {
//				response.put("status", "failure");	// 페이지 렌더링은 react에서 처리
//				logger.debug("here in failure");
//				return ResponseEntity.ok(response);	
//			}
//		}
//		catch(Exception e) {
//			logger.error("Exception occurred during login: {} ", e.getMessage());
//		
//		}
//		return null;
//		
//	}
	
	// @RequestParam String userLoginId, @RequestParam String userPw
	
//	@PostMapping("/signup")
//	public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
//		//User user = new User(userLoginId, userPw);
//		Map<String, String> response = new HashMap<>();
//		if(userService.registerUser(user) != null) {	// 회원가입 성공했을 때
//			//return "forward:main";
//			response.put("status", "success");
//			return ResponseEntity.ok(response);
//		}
//		
//		response.put("status", "failure");
//		return ResponseEntity.ok(response);		
//	}

}
