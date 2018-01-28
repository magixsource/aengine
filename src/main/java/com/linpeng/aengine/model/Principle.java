package com.linpeng.aengine.model;

import javax.persistence.Entity;

import org.beetl.sql.core.mapper.BaseMapper;

@Entity
public class Principle extends AbstractModel {

	private Long diseaseId;

	private Long principleItemId;

	public Principle() {
	}

	public Principle(Long diseaseId, Long principleItemId) {
		this.diseaseId = diseaseId;
		this.principleItemId = principleItemId;
	}

	public Long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public Long getPrincipleItemId() {
		return principleItemId;
	}

	public void setPrincipleItemId(Long principleItemId) {
		this.principleItemId = principleItemId;
	}

	public interface Mapper extends BaseMapper<Principle> {
	}
}
