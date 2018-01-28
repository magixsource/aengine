package gl.linpeng.aengine.controller;

import javax.inject.Inject;

import org.osgl.mvc.annotation.PostAction;

import act.controller.Controller;
import act.event.EventBus;
import gl.linpeng.aengine.event.HealthEvent;

/**
 * 引擎控制器
 * 
 * @author linpeng
 *
 */
@Controller("/aengine")
public class AengineController {

	@Inject
	EventBus eventBus;

	@PostAction("/read")
	public boolean read(String content) {
		HealthEvent event = new HealthEvent();
		event.setData(content);
		eventBus.emitAsync(event);
		return true;
	}

}
