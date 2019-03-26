package fun.sprider;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fun.sprider.core.JDLinksPool;
import fun.sprider.core.Sprider;

@SpringBootApplication
public class TermiteAppSpriderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TermiteAppSpriderApplication.class, args);
		Sprider thread = new Sprider();
		try {
			
			new JDLinksPool().init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
