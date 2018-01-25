package com.linpeng.aengine.controller;

import javax.inject.Inject;

import org.osgl.mvc.annotation.PostAction;

import com.linpeng.aengine.api.IAengineApi;
import act.controller.Controller;

/**
 * 引擎控制器
 * 
 * @author linpeng
 *
 */
@Controller("/aengine")
public class AengineController {

	@Inject
	IAengineApi aengineApi;

	@PostAction("/read")
	public boolean read(String content) {
		return aengineApi.readContent(content);
	}

}
