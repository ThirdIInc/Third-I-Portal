/**
 * 
 */
package org.struts.action.portal;

import java.util.HashMap;

import org.paradyne.bean.portal.MyFavourites;
import org.paradyne.bean.portal.MyNotes;
import org.paradyne.model.portal.MyFavouritesModel;
import org.paradyne.model.portal.MyNotesModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1439
 *
 */
public class MyFavouritesAction extends ParaActionSupport{

	MyFavourites bean;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new MyFavourites();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * @return the bean
	 */
	public MyFavourites getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(MyFavourites bean) {
		this.bean = bean;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			MyFavouritesModel model = new MyFavouritesModel();
			model.initiate(context, session);
			Object[][] dataObj = model.setData(bean);
			model.catData(bean);
			request.setAttribute("favData", dataObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void reset() {
		bean.setHiddenFavId("");
		bean.setLinkName("");
		bean.setLinkUrl("");
		bean.setCategory("");
		bean.setCategoryOther("");
	}
	
	public String addMyFavourites() {
		try {
			MyFavouritesModel model = new MyFavouritesModel();
			model.initiate(context, session);
			boolean result = false;

			String favCode = request.getParameter("favCode");
			if (favCode.equals("")) {
				result = model.addMyFavourites(bean, favCode);
				reset();
			} else {
				result = model.updateMyFavouritesData(bean, favCode);
				reset();
			}

			Object[][] dataObj = model.setData(bean);
			request.setAttribute("favData", dataObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			MyFavouritesModel model = new MyFavouritesModel();
			model.initiate(context, session);
			boolean result = model.deleteRecord(bean);
			Object[][] dataObj = model.setData(bean);
			request.setAttribute("favData", dataObj);
			prepare_withLoginProfileDetails();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	} 

	
}
