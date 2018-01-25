package com.linpeng.aengine.model;

public class Count {

	private Long id;
	private Integer countType;
	private Long targetId;
	private Long count;

	public Count() {
	}

	public Count(Integer countType, Long targetId) {
		this.countType = countType;
		this.targetId = targetId;
		this.count = 1L;
	}

	public Count(Integer countType, Long targetId, Long count) {
		this.countType = countType;
		this.targetId = targetId;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCountType() {
		return countType;
	}

	public void setCountType(Integer countType) {
		this.countType = countType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
