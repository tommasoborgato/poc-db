package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDatabaseInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by icttb0 on 03/02/2017.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration
{
    private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Primary
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() throws SQLException {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new org.hsqldb.jdbcDriver());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public BatchDatabaseInitializer myBatchDatabaseInitializer() {
        return new MyBatchDatabaseInitializer();
    }

    @Bean
    protected Tasklet taskletPostgres() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution,
                                        ChunkContext context) {
                log.info("================================================================");
                log.info("taskletPostgres");
                log.info("================================================================");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    protected Tasklet taskletMysql() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution,
                                        ChunkContext context) {
                log.info("================================================================");
                log.info("taskletMysql");
                log.info("================================================================");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public JobCompletionNotificationListener getJobCompletionNotificationListener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public Job jobPostgres(JobCompletionNotificationListener listener) throws Exception {
        return this.jobs.get("job-postgres").listener(listener).start(stepPostgres()).build();
    }

    @Bean
    public Job jobMysql(JobCompletionNotificationListener listener) throws Exception {
        return this.jobs.get("job-mysql").listener(listener).start(stepMysql()).build();
    }

    @Bean
    protected Step stepPostgres() throws Exception {
        return this.steps.get("step-postgres").tasklet(taskletPostgres()).build();
    }

    @Bean
    protected Step stepMysql() throws Exception {
        return this.steps.get("step-mysql").tasklet(taskletMysql()).build();
    }

}
