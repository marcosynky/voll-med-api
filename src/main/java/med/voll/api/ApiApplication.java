package med.voll.api;

// Importação necessária para o Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Anotação que indica que esta é uma classe de aplicação Spring Boot
public class ApiApplication {

	// Método principal para iniciar a aplicação
	public static void main(String[] args) {
		// Executa a aplicação Spring Boot
		SpringApplication.run(ApiApplication.class, args);
	}

}
