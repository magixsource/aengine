package com.linpeng.aengine.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.mapper.BaseMapper;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Disease {

    @Id
    private Long id;
    private String name;

    public Disease() {
    }

    public Disease(String name) {
        this.name = name;
    }

    public Disease(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @AutoID
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

    public interface Mapper extends BaseMapper<Disease> {
    }
}
