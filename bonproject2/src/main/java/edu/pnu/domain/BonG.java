package edu.pnu.domain;

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
public class BonG {
	
	@Id
	@Column(name="date")
	private LocalDateTime date ;
	
	private Double in_flow;
	private Double out_flow;
	private Double height;
	
	
}
