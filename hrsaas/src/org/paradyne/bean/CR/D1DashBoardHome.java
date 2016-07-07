package org.paradyne.bean.CR;

import java.util.HashMap;
import org.paradyne.lib.BeanBase;

/**@purpose : Create Home page for DashBoard Module
 * @author AA1711
 * @Date : 21-Jan-2013
 */
public class D1DashBoardHome extends BeanBase {
	
	private HashMap<String, String> dashMenuDropMap = new HashMap<String, String>();
	
	/**Method Name : getDashMenuDropMap()
	 * @purpose Used to get HashMap Value 
	 * @return HashMap
	 */
	public HashMap<String, String> getDashMenuDropMap() {
		return dashMenuDropMap;
	}

	/**Method Name : setDashMenuDropMap()
	 * @purpose Used to set HashMap Value 
	 * @param dashMenuDropMap
	 */
	public void setDashMenuDropMap(HashMap<String, String> dashMenuDropMap) {
		this.dashMenuDropMap = dashMenuDropMap;
	}

	
}
