package com.example;

import com.example.batch.MyBatchDatabaseInitializer;
import com.example.mysql.MysqlNode;
import com.example.mysql.MysqlNodeRepository;
import com.example.postgres.PostgresNode;
import com.example.postgres.PostgresNodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchDatabaseInitializer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
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

			/* Postgres */
			System.out.println("=================== Postgres INS ALL =============================");
			Date date1= new Date();
			for (int i = 1; i<100; i++){
				pnr.save(new PostgresNode("node"+i));
			}
			//Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> pnr.save(new PostgresNode(n)));
			Date date2= new Date();
			System.out.println("Postgres INSERT" + new Date((date2.getTime()-date1.getTime())) );

			System.out.println("=================== Postgres FIND ALL =============================");
			pnr.findAll(); //.forEach(System.out::println);
			Date date3 = new Date();
			System.out.println("Postgres SELECT" + new Date((date3.getTime()-date2.getTime())) );

			/* MySQL */
			System.out.println("=================== Mysql INS ALL =============================");
			Date date4= new Date();
			for (int i = 1; i<100; i++){
				mnr.save(new MysqlNode("node"+i));
			}
			Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> mnr.save(new MysqlNode(n)));
			Date date5= new Date();
			System.out.println("Mysql INSERT" + new Date((date5.getTime()-date4.getTime())) );

			System.out.println("=================== Mysql FIND ALL =============================");
			mnr.findAll(); //.forEach(System.out::println);
			Date date6= new Date();
			System.out.println("Mysql SELECT" + new Date((date5.getTime()-date6.getTime())) );

		};
	}
}
