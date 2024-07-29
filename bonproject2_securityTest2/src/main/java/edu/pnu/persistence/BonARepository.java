package edu.pnu.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.BonA;
import edu.pnu.domain.BonB;

// 하이버네이트가 인터페이스 코드 만들면서 자동으로 어노테이션 올려줌
// 명시적으로 표시함
@Repository
public interface BonARepository extends CrudRepository<BonA, LocalDateTime> {
	
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
	
	
	List<BonA> findBonAByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	
	
//	@Query("SELECT SUM(out_flow) FROM bona WHERE DATE BETWEEN startDate AND endDate ")
}
