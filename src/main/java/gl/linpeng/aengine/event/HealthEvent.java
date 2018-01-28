package gl.linpeng.aengine.event;

import javax.inject.Inject;

import act.event.OnEvent;
import act.util.Async;
import gl.linpeng.aengine.api.IAengineApi;

/**
 * Health event
 * 
 * @author linpeng
 *
 */
@Async
public class HealthEvent {

	@Inject	
	IAengineApi aengineApi;

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@OnEvent
	public void handleEvent(HealthEvent event) {
		aengineApi.readContent(event.getData());
	}

}
