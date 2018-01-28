package gl.linpeng.aengine.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gl.linpeng.aengine.common.Constants;
import gl.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.aengine.service.IAnalyzerService;
import gl.linpeng.analyzer.core.IKSegmenter;
import gl.linpeng.analyzer.core.Lexeme;

/**
 * Health domain analyzer
 * 
 * @author linpeng
 *
 */
public class HealthAnalyzerServiceImpl implements IAnalyzerService {
	static final Set<Integer> supportedScopeSet;

	static {
		supportedScopeSet = new HashSet<>(10);
		supportedScopeSet.add(Lexeme.TYPE_ADVERB);
		supportedScopeSet.add(Lexeme.TYPE_DISEASE);
		supportedScopeSet.add(Lexeme.TYPE_FOOD);
		supportedScopeSet.add(Lexeme.TYPE_NATRIENT);
	}

	@Override
	public Map<String, List<PrincipleItem>> analyze(String content) throws IOException {
		byte[] bt = content.getBytes();
		InputStream ip = new ByteArrayInputStream(bt);
		Reader read = new InputStreamReader(ip);
		IKSegmenter iks = new IKSegmenter(read, true);
		Lexeme t;
		Lexeme pre = null;
		Lexeme preDisease = null;
		Map<String, List<PrincipleItem>> stashMap = new HashMap<>();

		while ((t = iks.next()) != null) {
			if (!isScopeSupported(t.getLexemeType())) {
				continue;
			}

			if (t.getLexemeType() == Lexeme.TYPE_DISEASE) {
				String diseaseName = t.getLexemeText().trim();
				preDisease = t;
				if (stashMap.containsKey(diseaseName)) {
					continue;
				} else {
					// is not in map
					stashMap.put(diseaseName, new ArrayList<PrincipleItem>());
				}
			} else if (t.getLexemeType() == Lexeme.TYPE_ADVERB) {
				Lexeme tmpPre = pre;
				pre = t;
				if (tmpPre != null && tmpPre.getLexemeType() == Lexeme.TYPE_ADVERB) {
					continue;
				}
			} else if (t.getLexemeType() == Lexeme.TYPE_FOOD || t.getLexemeType() == Lexeme.TYPE_NATRIENT) {
				if (preDisease == null) {
					continue;
				}
				List<PrincipleItem> principleItemList = stashMap.get(preDisease.getLexemeText().trim());

				PrincipleItem principleItem = new PrincipleItem();
				principleItem.setAdverb(Constants.Adverb.adverbValueOf(pre.getLexemeText()).getValue());
				if (t.getLexemeType() == Lexeme.TYPE_FOOD) {
					principleItem.setType(Constants.PrincipleItemType.FOOD.getValue());
				} else {
					principleItem.setType(Constants.PrincipleItemType.NATRIENT.getValue());
				}
				principleItem.setTarget(t.getLexemeText());
				principleItemList.add(principleItem);
			}

		}

		return stashMap;
	}

	/**
	 * Determine is lexeme type supported
	 *
	 * @param lexemeType
	 * @return TRUE if supported
	 */
	private boolean isScopeSupported(int lexemeType) {
		return supportedScopeSet.contains(lexemeType);
	}

}
