package fun.sprider;

import java.io.IOException;

import org.apache.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import fun.sprider.core.JDLinksPool;
@SpringBootApplication
@EnableAsync
public class TermiteAppSpriderApplication {
	private JDLinksPool pool;
	public static void main(String[] args) throws ParseException, IOException {
	 ConfigurableApplicationContext ctx=SpringApplication.run(TermiteAppSpriderApplication.class, args);
	 JDLinksPool pool = ctx.getBean(JDLinksPool.class);
	 pool.init(ctx);
	}

}
