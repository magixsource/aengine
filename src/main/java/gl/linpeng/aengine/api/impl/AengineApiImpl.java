package gl.linpeng.aengine.api.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import gl.linpeng.aengine.api.IAengineApi;
import gl.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.aengine.service.IAnalyzerService;
import gl.linpeng.aengine.service.IHealthService;

/**
 * 引擎API实现类
 *
 * @author linpeng
 */
public class AengineApiImpl implements IAengineApi {

	@Inject
	IAnalyzerService analyzerService;
	@Inject
	IHealthService healthService;

	@Override
	public boolean readContent(String content) {
		try {
			// analyzer
			Map<String, List<PrincipleItem>> stashMap = analyzerService.analyze(content);
			// batch save
			healthService.batchSave(stashMap);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
