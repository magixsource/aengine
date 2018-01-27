package com.linpeng.aengine.controller;

import javax.inject.Inject;

import com.linpeng.aengine.model.PrincipleItem;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import com.linpeng.aengine.api.IAengineApi;
import act.controller.Controller;

import java.util.ArrayList;
import java.util.List;

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

    @Inject
    PrincipleItem.Mapper mapper;

    @GetAction("/new")
    public boolean create(){
        String content = "心脏病不能吃的食物,忌食饱和脂肪酸含量极高的肉类及肉类加工品例如牛、猪、羊肉，五花肉，肥肉，香肠、腊肠、熏肉等。因为这类食品含脂肪高，虽然是高蛋白，但饱和脂肪酸含量很高，容易造成血液中血脂过高，诱发冠心病。2.忌食咸菜、辣椒等，忌含酒精的饮料和咖啡、浓的红茶例如辣椒、芥末、咖喱、酒、咖啡、浓茶、碳酸饮料;腌咸菜、各种酱菜......因为辛辣的食物会诱使神经兴奋是导致血压升高";
        aengineApi.readContent(content);
        return true;
    }
    @GetAction("/list")
    public List list(){
        return mapper.all();
    }

	@PostAction("/read")
	public boolean read(String content) {
		return aengineApi.readContent(content);
	}

}
