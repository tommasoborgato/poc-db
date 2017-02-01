package com.example.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by icttb0 on 20/01/2017.
 */
@Transactional("mysqlTransactionManager")
public interface MysqlNodeRepository extends JpaRepository<MysqlNode,Long> {
    Collection<MysqlNode> findByName(String name);
}