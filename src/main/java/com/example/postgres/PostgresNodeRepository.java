package com.example.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by icttb0 on 20/01/2017.
 */
@Transactional("postgresTransactionManager")
public interface PostgresNodeRepository extends JpaRepository<PostgresNode,Long> {
    Collection<PostgresNode> findByName(String name);
}