package com.example.batch;

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

    @Autowired
    @Qualifier("batchDataSource")
    private DataSource dataSource;

}
