package by.skillmatrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("by.skillmatrix")
@EntityScan("by.skillmatrix.entity")
@EnableJpaRepositories("by.skillmatrix.dao.impl.springdata")
@SpringBootApplication
public class SkillMatrixApplication {
	public static void main(String[] args) {
		SpringApplication.run(SkillMatrixApplication.class, args);
	}
}
