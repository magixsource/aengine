package gl.linpeng.aengine.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import act.inject.AutoBind;
import gl.linpeng.aengine.model.PrincipleItem;

/**
 * Text analyzer service interface
 * 
 * @author linpeng
 *
 */
@AutoBind
public interface IAnalyzerService {

	/**
	 * analyze content and return analyze result
	 * 
	 * @param content
	 *            origin text content
	 * @return analyze result
	 * @throws IOException
	 *             exception
	 */
	Map<String, List<PrincipleItem>> analyze(String content) throws IOException;

}
