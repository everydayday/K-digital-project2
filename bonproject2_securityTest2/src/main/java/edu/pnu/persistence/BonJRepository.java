package edu.pnu.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.BonJ;

// 하이버네이트가 인터페이스 코드 만들면서 자동으로 어노테이션 올려줌
// 명시적으로 표시함
@Repository
public interface BonJRepository extends CrudRepository<BonJ, LocalDateTime> {

	List<BonJ> findBonJByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	BonJ findByDateIs(LocalDateTime date);
}
