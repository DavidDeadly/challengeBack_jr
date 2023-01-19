package co.sofka.challenge_jr;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger ChallengeJR", version = "1.0", description = "Documentation Inventory API"))
public class ChallengeJrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeJrApplication.class, args);
	}

}
