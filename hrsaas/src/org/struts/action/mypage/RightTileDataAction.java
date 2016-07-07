package org.struts.action.mypage;

import java.io.File;
import org.paradyne.bean.mypage.RightTileDataBean;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.EmployeePortalModel;
import org.paradyne.model.mypage.RightTileDataModel;
import org.struts.lib.ParaActionSupport;

public class RightTileDataAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(RightTileDataAction.class);
	String poolName ="";
	RightTileDataBean bean =null;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean =new RightTileDataBean();
		poolName = String.valueOf(session.getAttribute("session_pool"));
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	
	
	public void prepare_withLoginProfileDetails()
	{
		try {
			getAnnouncementData();
			getPoll();
		} catch (Exception e) {
			e.printStackTrace();
		// TODO: handle exception
		}
	}
	
	
	public String pollSave() throws Exception {
		try {
			System.out
					.println("In savepoll method---------------------------------------");
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/xml/poll/poll.xml";
			RightTileDataModel model = null;
			model = new RightTileDataModel();
			model.initiate(context, session);
			model.savePoll(request, response, path, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getAnnouncementData()
	{
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getHRComm(request, bean.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT ;
	}
	
	public void getPoll() {

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		RightTileDataModel model = null;
		try {
			model = new RightTileDataModel();
			model.initiate(context, session);
			String[][] pollList = null;
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "/" + poolName;
			}// end of if

			// READING FROM XML
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/xml/poll/poll.xml";

			File file = new File(path);

			final Object[][] result = model
					.processPollInfo(new XMLReaderWriter()
							.parse(new File(path)), bean);
			/*logger.info("Poll Code-----" + bean.getPollCode());
			
			logger.info("result.length" + result.length);
*/			
			
			Object optionObj[][] = new Object[result.length][4];
			for (int i = 0; i < optionObj.length; i++) {
				optionObj[i][0] = result[i][0];
				optionObj[i][2] = result[i][2];
				optionObj[i][3] = result[i][3];
				optionObj[i][1] = String.valueOf(result[i][1]).replace(
						"%26apos;", "'").replace("%26", "&");
			}
			request.setAttribute("pollData", result);
			request.setAttribute("optionObj", optionObj);

			model.terminate();

			model.terminate();
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}
	

}
