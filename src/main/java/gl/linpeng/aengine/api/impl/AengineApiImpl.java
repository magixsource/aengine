package gl.linpeng.aengine.api.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import act.event.EventBus;
import gl.linpeng.aengine.api.IAengineApi;
import gl.linpeng.aengine.event.HealthPricipleSaveEvent;
import gl.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.aengine.service.IAnalyzerService;

/**
 * 引擎API实现类
 *
 * @author linpeng
 */
public class AengineApiImpl implements IAengineApi {

	@Inject
	IAnalyzerService analyzerService;

	@Inject
	EventBus eventBus;

	@Override
	public boolean readContent(String content) {
		// analyzer
		Map<String, List<PrincipleItem>> stashMap;
		try {
			stashMap = analyzerService.analyze(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		// batch save
		HealthPricipleSaveEvent event = new HealthPricipleSaveEvent();
		event.setData(stashMap);
		eventBus.emitAsync(event);

		return true;
	}

}
