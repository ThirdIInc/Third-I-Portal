package org.struts.action.common;

import org.paradyne.model.common.HomePageModel;

import org.struts.lib.ParaActionSupport;

import org.paradyne.bean.common.TrainingHome;

/*
 * author:Pradeep Kumar Sahoo
 * Date:31-01-2008
 */

import de.laures.cewolf.DatasetProducer;

public class TrainingHomeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainingHomeAction.class);
	
	
	
	TrainingHome trainingHome;
	
	
	
	public TrainingHome getTrainingHome() {
		return trainingHome;
	}

	public void setHrm(TrainingHome trainingHome) {
		this.trainingHome = trainingHome;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		trainingHome=new TrainingHome();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trainingHome;
	}
	
	
	


public String getTrainingMenu() throws Exception {
	HomePageModel trainingHomeModel=new HomePageModel();
	String menuType=request.getParameter("menuType");
	trainingHomeModel.initiate(context, session);
	trainingHomeModel.getMenuList(request, trainingHome, menuType);
	trainingHomeModel.terminate();
	return "input";
}

public String input() throws Exception {
	
	return "input";
}
}

