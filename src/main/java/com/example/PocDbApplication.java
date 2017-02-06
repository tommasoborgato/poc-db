package com.example;

import com.example.mysql.MysqlNode;
import com.example.mysql.MysqlNodeRepository;
import com.example.postgres.PostgresNode;
import com.example.postgres.PostgresNodeRepository;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
@EnableTransactionManagement
public class PocDbApplication {

	private static final Logger log = LoggerFactory.getLogger(PocDbApplication.class);

	@Autowired
	JobLauncher jobLauncher;

	public static void main(String[] args) {
		SpringApplication.run(PocDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			PostgresNodeRepository pnr,
			MysqlNodeRepository mnr
	) {
		return args -> {

			StopWatch stopWatch = new StopWatch();

			/* Postgres */
			stopWatch.start();
			List<PostgresNode> nodeListPostgres = new ArrayList<>();
			for (int i = 1; i<100; i++){
				nodeListPostgres.add(new PostgresNode("node"+i));
				//pnr.save(new PostgresNode("node"+i));
			}
			pnr.save(nodeListPostgres);
			stopWatch.stop();
			//Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> pnr.save(new PostgresNode(n)));
			log.info("=================== Postgres INS ALL =============================");
			log.info("Postgres INSERT " + stopWatch.toString() );
			log.info("================================================================");


			stopWatch.reset();
			stopWatch.start();
			pnr.findAll(); //.forEach(System.out::println);
			stopWatch.stop();
			log.info("=================== Postgres FIND ALL =============================");
			log.info("Postgres SELECT " + stopWatch.toString() );
			log.info("================================================================");


			/* MySQL */
			stopWatch.reset();
			stopWatch.start();
			List<MysqlNode> nodeListMysql = new ArrayList<>();
			for (int i = 1; i<100; i++){
				//mnr.save(new MysqlNode("node"+i));
				nodeListMysql.add(new MysqlNode("node"+i));
			}
			mnr.save(nodeListMysql);
			stopWatch.stop();
			//Arrays.asList("node1,node2,node3,node4,node5,node6".split(",")).forEach(n -> mnr.save(new MysqlNode(n)));
			log.info("=================== Mysql INS ALL =============================");
			log.info("Mysql INSERT " + stopWatch.toString() );
			log.info("================================================================");


			stopWatch.reset();
			stopWatch.start();
			mnr.findAll(); //.forEach(System.out::println);
			stopWatch.stop();
			log.info("=================== Mysql FIND ALL =============================");
			log.info("Mysql SELECT " + stopWatch.toString() );
			log.info("================================================================");

		};
	}
}
