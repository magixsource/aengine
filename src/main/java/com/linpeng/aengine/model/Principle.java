package com.linpeng.aengine.model;

public class Principle {

	private Long id;

	private Long diseaseId;

	private Long principleItemId;

	public Principle() {
	}

	public Principle(Long diseaseId, Long principleItemId) {
		this.diseaseId = diseaseId;
		this.principleItemId = principleItemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
