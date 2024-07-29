package edu.pnu.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.BonB;
import edu.pnu.domain.BonE;

// 하이버네이트가 인터페이스 코드 만들면서 자동으로 어노테이션 올려줌
// 명시적으로 표시함
@Repository
public interface BonERepository extends CrudRepository<BonE, LocalDateTime> {
	
//	User findByUserLoginId(String userLogInId);
//	@Query("SELECT u.userPw FROM USER u WHERE u.userLoginId = ?1 ")	// ?1 첫번째 매개변수
//	User findUserPwByUserLoginId(String userLoginId);	// User로 들어옴  * (String으로 해도 메서드 자체가 User가 들어옴)
	
//	@Query("SELECT b FROM Bon1 b")
//	List<Bon1> Flow();
	// data type 에 민감. -> query 문으로 개별적으로 주는거 잘 안 돼.
	
	
	// findAll 은 기본으로 존재하는 method
	// data base 데이터 타입에 민감하다.
	
//	@Query("SELECT * FROM Bon1 b ORDER BY DATE DESC")
//	List<Bon1> flow();
	
//	List<Bon1> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
//	List<BonA> findBonAByDateBetweenAndJeosujiEquals(LocalDateTime startDate, LocalDateTime endDate, char jeosuji);
	
	List<BonE> findBonEByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	//	@Query("SELECT b.in_flow, b.out_flow FROM Bon1 b WHERE b.date ")
}
