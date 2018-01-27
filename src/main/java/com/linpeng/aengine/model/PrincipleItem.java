package com.linpeng.aengine.model;

import com.linpeng.aengine.common.Constants.Adverb;
import com.linpeng.aengine.common.Constants.PrincipleItemType;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.mapper.BaseMapper;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrincipleItem {

    @Id
    private Long id;
    private Integer adverb;
    private Integer type;
    private String target;

    public PrincipleItem() {
    }

    public PrincipleItem(Integer adverb, Integer type, String target) {
        this.adverb = adverb;
        this.type = type;
        this.target = target;
    }

    @AutoID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdverb() {
        return adverb;
    }

    public void setAdverb(Integer adverb) {
        this.adverb = adverb;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public interface Mapper extends BaseMapper<PrincipleItem> {
    }
}
