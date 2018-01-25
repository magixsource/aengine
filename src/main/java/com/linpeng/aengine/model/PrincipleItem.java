package com.linpeng.aengine.model;

import com.linpeng.aengine.common.Constants.Adverb;
import com.linpeng.aengine.common.Constants.PrincipleItemType;

public class PrincipleItem {

	private Long id;
	private Adverb adverb;
	private PrincipleItemType type;
	private Long targetId;

	public PrincipleItem() {
	}

	public PrincipleItem(Adverb adverb, PrincipleItemType type, Long targetId) {
		this.adverb = adverb;
		this.type = type;
		this.targetId = targetId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Adverb getAdverb() {
		return adverb;
	}

	public void setAdverb(Adverb adverb) {
		this.adverb = adverb;
	}

	public PrincipleItemType getType() {
		return type;
	}

	public void setType(PrincipleItemType type) {
		this.type = type;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

}
