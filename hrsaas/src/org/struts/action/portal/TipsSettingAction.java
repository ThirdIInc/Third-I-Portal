package org.struts.action.portal;

import org.paradyne.bean.portal.TipsSettingBean;
import org.paradyne.model.portal.TipsSettingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381 25th Jan 2012.
 *
 */
public class TipsSettingAction extends ParaActionSupport {

	TipsSettingBean bean;

	public void prepare_local() throws Exception {
		bean = new TipsSettingBean();

	}

	public Object getModel() {
		return bean;
	}

	/**
	 * @return the bean
	 */
	public TipsSettingBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(TipsSettingBean bean) {
		this.bean = bean;
	}

	/**
	 * Method : homePageTips();
	 * @return String.
	 */
	public String homePageTips() {

		try {
			String categoryCode = request.getParameter("categoryCode");
			System.out.println("categoryCode   " + categoryCode);
			getTipsData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**
	 * Method : getTipsData().
	 * @return String.
	 */
	public String getTipsData() {

		System.out.println("In Tips Action ----------------------------");

		try {
			TipsSettingModel model = new TipsSettingModel();
			model.initiate(context, session);
			String categoryCode = request.getParameter("categoryCode");
			System.out.println("categoryCode                " + categoryCode);
			model.getTipsData(request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

}
