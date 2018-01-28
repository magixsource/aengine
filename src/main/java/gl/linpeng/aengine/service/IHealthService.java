package gl.linpeng.aengine.service;

import java.util.List;
import java.util.Map;

import act.inject.AutoBind;
import gl.linpeng.aengine.model.PrincipleItem;

/**
 * Health domain service
 * 
 * @author linpeng
 *
 */
@AutoBind
public interface IHealthService {

	/**
	 * Batch save disease and principle item
	 * 
	 * @param map
	 *            disease and principle item map
	 */
	void batchSave(Map<String, List<PrincipleItem>> map);
}
