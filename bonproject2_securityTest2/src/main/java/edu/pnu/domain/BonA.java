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
public class BonA {
	
	@Id
	@Column(name="date")
	private LocalDateTime date ;
	
	private Double in_flow;
	private Double out_flow;
	private Double height;

	
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name = "user_login_id", unique = true)
	private String userLoginId;	// 필드명 명시적으로 지정하지 않을 시, 기본 네이밍 전략에 따라 snake_case 칼럼명으로 변환됨
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_pw")
	private String userPw;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "user_purpose")
	private String userPurpose;
	
//	@Builder.Default
//	@OneToMany
//	@JoinColumn(name = "user_id")
	@Builder.Default
	@OneToMany
//	@JoinColumn(name = "user_id")
	private List<Interest_job> interestJobs = new ArrayList<>();
	
	@Column(name = "is_active")
	@ColumnDefault("true")
	private boolean isActive;
	
	public User(String userLoginId, String userPw) {
		this.userLoginId = userLoginId;
		this.userPw = userPw;
	}
	*/
	
	
}
