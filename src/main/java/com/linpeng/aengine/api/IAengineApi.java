package com.linpeng.aengine.api;

import act.inject.AutoBind;

/**
 * 引擎API：描述引擎相关的写操作,如数据录入
 * 
 * @author linpeng
 *
 */
@AutoBind
public interface IAengineApi {

	/**
	 * 读取文本内容并尝试解析文中语义原则。当解析原则成功时，将持久化到引擎知识库中。
	 * 
	 * @param content
	 *            原始文本
	 * @return 读取成功返回TRUE；发生异常返回FALSE
	 */
	boolean readContent(String content);
}
