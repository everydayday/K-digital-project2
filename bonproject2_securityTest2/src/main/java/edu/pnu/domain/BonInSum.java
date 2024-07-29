package edu.pnu.domain;
// 유출 유량 일 단위 합 for 하루당 비교
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Entity	// Entity로 사용해서 JPA(sql문 작성 안 해도 되는)로 사용 가능
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Builder패턴 .setUser.build 사용 가능
@EntityListeners(AuditingEntityListener.class) // Auditing 사용 하기 위해
@Table(name = "inflow_view")
public class BonInSum {
	
	@Id
	@Column(name="inflow_date")
	private LocalDateTime date ;
	
	private Double a;
	private Double b;
	private Double e;
	private Double f;
	private Double g;
	private Double h;
	private Double i;
	private Double j;
	private Double l;
	private Double m;
	private Double n;
	private Double o;
	private Double p;
	private Double q;
	private Double s;
	private Double t;
	private Double u;
	private Double v;
	private Double w;

	
	
	
	
}
