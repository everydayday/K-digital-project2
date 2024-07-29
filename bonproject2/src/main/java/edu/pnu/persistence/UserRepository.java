package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.User;


// 하이버네이트가 인터페이스 코드 만들면서 자동으로 어노테이션 올려줌
// 명시적으로 표시함
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {	// CRUD 기능 제공하는 인터페이스 // <T, ID> // T:Entity / @ID로 매핑한 식별자 변수의 타입 //JPARepository: 페이징 기능
	
	User findByUserLoginId(String userLogInId);
	@Query("SELECT u.userPw FROM USER u WHERE u.userLoginId = ?1 ")	// ?1 첫번째 매개변수
	User findUserPwByUserLoginId(String userLoginId);	// User로 들어옴  * (String으로 해도 메서드 자체가 User가 들어옴)
	User findUserByUserPhoneNumber(String userPhoneNumber);
	List<User> findUsersByUserPhoneNumber(String userPhoneNumber);

	
	
} 
