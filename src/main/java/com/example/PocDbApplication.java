package com.example;

import com.example.mysql.MysqlNode;
import com.example.mysql.MysqlNodeRepository;
import com.example.postgres.PostgresNode;
import com.example.postgres.PostgresNodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class PocDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			PostgresNodeRepository pnr,
			MysqlNodeRepository mnr
	) {
		return args -> {
			Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> pnr.save(new PostgresNode(n)));
			System.out.println("=================== FIND ALL =============================");
			pnr.findAll().forEach(System.out::println);
			Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> mnr.save(new MysqlNode(n)));
			System.out.println("=================== FIND ALL =============================");
			mnr.findAll().forEach(System.out::println);
		};
	}
}
