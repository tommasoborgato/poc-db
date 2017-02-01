package com.example.postgres;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by icttb0 on 20/01/2017.
 */
@Entity
public class PostgresNode {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public PostgresNode() {
    }

    public PostgresNode(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PostgresNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
