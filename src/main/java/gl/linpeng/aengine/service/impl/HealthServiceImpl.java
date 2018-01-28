package gl.linpeng.aengine.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.beetl.sql.core.db.KeyHolder;

import gl.linpeng.aengine.model.Disease;
import gl.linpeng.aengine.model.Principle;
import gl.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.aengine.service.IHealthService;

/**
 * Health service
 * 
 * @author linpeng
 *
 */
public class HealthServiceImpl implements IHealthService {

	@Inject
	private Disease.Mapper diseaseMapper;
	@Inject
	private Principle.Mapper principleMapper;
	@Inject
	private PrincipleItem.Mapper principleItemMapper;

	@Override
	public void batchSave(Map<String, List<PrincipleItem>> map) {
		List<Principle> principleList = new ArrayList<>();
		for (Map.Entry<String, List<PrincipleItem>> entry : map.entrySet()) {
			Disease query = new Disease(entry.getKey());
			List<Disease> diseaseList = diseaseMapper.template(query);
			Disease disease = null;
			if (diseaseList != null && diseaseList.size() > 0) {
				disease = diseaseList.get(0);
			} else {
				disease = new Disease(entry.getKey());
				KeyHolder keyHolder = diseaseMapper.insertReturnKey(disease);
				disease.setId(keyHolder.getLong());
			}

			for (PrincipleItem principleItem : entry.getValue()) {
				// PrincipleItem dbPrincipleItem =
				// principleItemMapper.templateOne(principleItem);
				List<PrincipleItem> principleItemList = principleItemMapper.template(principleItem);
				if (principleItemList == null || principleItemList.isEmpty()) {
					Long id = principleItemMapper.insertReturnKey(principleItem).getLong();
					principleItem.setId(id);
				} else {
					principleItem.setId(principleItemList.get(0).getId());
				}
				// wrap to principle
				Principle principle = new Principle(disease.getId(), principleItem.getId());
				principleList.add(principle);
			}

		}
		// insert batch
		if (!principleList.isEmpty()) {
			principleMapper.insertBatch(principleList);
		}
	}

}
