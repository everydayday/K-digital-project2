package edu.pnu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Bon1;
import edu.pnu.persistence.BonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BonServiceImpl implements BonService {

	
	private final BonRepository bonRepo;
	
	@Override
	public List<Bon1> getFlow() {
		List<Bon1> BonFlows = new ArrayList<>();
		
		BonFlows = (List<Bon1>) bonRepo.findAll(); 
//		BonFlows = bonRepo.flow();
				
		return BonFlows;
	}
	
	@Override
	public List<Bon1> getFlowByDateContaining(String date, char jeosuji){
		// format 형식 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate start = LocalDate.parse(date, formatter);
        LocalDate end = LocalDate.parse(date, formatter);

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);
        System.out.println("startDateTime in serviceImple"+ startDateTime);
		return bonRepo.findByDateBetweenAndJusujiEquals(startDateTime, endDateTime, jeosuji);
	}
	
	
//	private final PasswordEncoder passwordEncoder;
	
//	@Override
//	public User getUser(String userLogInId) {
//		// TODO Auto-generated method stub
//		User findUser = userRepo.findByUserLoginId(userLogInId);
////		if(findUser.isPresent())
////			return findUser.get();
////		return null;
//		// null 일 시 null 값 return
//		return findUser;
//	}
//	
//	@Override
//	public User registerUser(User user) {
//		String userloginid = user.getUserLoginId();
//		if(userRepo.findByUserLoginId(userloginid) == null) {	// 찾으니깐 없을 때 값을 저장
////			User encodedUser =  User.builder()
////									.userLoginId(user.getUserLoginId())
////									.userName(user.getUserName())
////									.userPw(passwordEncoder.encode(user.getUserPw())) // 암호화해서 저장
////									.userPurpose(user.getUserPurpose())
////									.build();
//			User encodedUser =  User.builder()
//					.userLoginId(user.getUserLoginId())
//					.userName(user.getUserName())
//					.userPw(passwordEncoder.encode(user.getUserPw())) // 암호화해서 저장
//					.build();
//			// 될 법 한데 오류 발생
////			user.setUserPw(passwordEncoder.encode(user.getUserPw()));	
//			return userRepo.save(encodedUser);
//		}
//		
//		// 어떤 값을 return해야 할까?
//		return null;
//	}

}
