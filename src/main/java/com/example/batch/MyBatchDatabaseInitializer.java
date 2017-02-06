package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDatabaseInitializer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by icttb0 on 03/02/2017.
 */
@Component
public class MyBatchDatabaseInitializer extends BatchDatabaseInitializer {

    private static final Logger log = LoggerFactory.getLogger(MyBatchDatabaseInitializer.class);

    public MyBatchDatabaseInitializer() {
        log.info("================================================================");
        log.info("MyBatchDatabaseInitializer");
        log.info("================================================================");
    }

    @Autowired
    @Qualifier("batchDataSource")
    private DataSource dataSource;

}
