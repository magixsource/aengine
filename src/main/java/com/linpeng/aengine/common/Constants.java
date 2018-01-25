package com.linpeng.aengine.common;

/**
 * Aengine constant
 * @author linpeng
 *
 */
public final class Constants {
	
	public static final int COUNT_TYPE_PRICIPLE = 1;
	public static final int COUNT_TYPE_PRICIPLE_ITEM = 2;

	public enum PrincipleItemType {
		FOOD(1, "FOOD"), NATRIENT(2, "NATRIENT");

		public String name;
		public Integer value;

		PrincipleItemType(Integer value, String name) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

	public enum Adverb {

		NO(1, "NO"), MORE(2, "MORE"), LESS(3, "LESS");

		public String name;
		public Integer value;

		Adverb(Integer value, String name) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

}
