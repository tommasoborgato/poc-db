package com.example.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by icttb0 on 20/01/2017.
 * Spring Data can create implementations for you of @Repository interfaces of various flavors. Spring Boot will handle all of that for you as long as those @Repositories are included in the same package (or a sub-package) of your @EnableAutoConfiguration class.
 */
@Transactional("mysqlTransactionManager")
public interface MysqlNodeRepository extends JpaRepository<MysqlNode,Long> {
    Collection<MysqlNode> findByName(String name);
}