package com.linpeng.aengine.model;

import javax.persistence.Entity;

import org.beetl.sql.core.mapper.BaseMapper;

@Entity
public class PrincipleItem extends AbstractModel {

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
