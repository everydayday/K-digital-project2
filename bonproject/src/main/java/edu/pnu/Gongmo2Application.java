package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 엔티티의 이벤트를 감시하는 auditing
@EnableJpaAuditing
@SpringBootApplication
public class Gongmo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Gongmo2Application.class, args);
	}

}
