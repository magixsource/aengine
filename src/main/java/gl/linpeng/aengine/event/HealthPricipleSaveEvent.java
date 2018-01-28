package gl.linpeng.aengine.event;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import act.event.OnEvent;
import act.util.Async;
import gl.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.aengine.service.IHealthService;

/**
 * Health principle save async event
 * 
 * @author linpeng
 *
 */
@Async
public class HealthPricipleSaveEvent {

	@Inject
	IHealthService healthService;

	Map<String, List<PrincipleItem>> data;

	public HealthPricipleSaveEvent() {
	}

	public Map<String, List<PrincipleItem>> getData() {
		return data;
	}

	public void setData(Map<String, List<PrincipleItem>> data) {
		this.data = data;
	}

	@OnEvent
	public void handleEvent(HealthPricipleSaveEvent event) {
		healthService.batchSave(event.getData());
	}

}
