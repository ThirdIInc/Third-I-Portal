package org.paradyne.model.Training;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.Competency.CompDesigMapping;
import org.paradyne.bean.Training.TrainingTypeMaster;
import org.paradyne.bean.admin.master.DivisionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TrainingTypeMasterModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TrainingTypeMasterModel.class);
	
	
	public void trainingTypeListData(TrainingTypeMaster trnBean,
			HttpServletRequest request,boolean search) {
		try {
			ArrayList<Object> list = null;
			Object[][] repData=null;
			if(search){
			 repData = getSqlModel().getSingleResult(getQuery(5));
			}else{
				 repData = getSqlModel().getSingleResult(getQuery(4));
			}
			if (repData != null && repData.length > 0) {
				trnBean.setModeLength("true");
				String[] pageIndex = Utility.doPaging(trnBean.getMyPage(),
						repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					trnBean.setMyPage("1");
				list = new ArrayList();
				if(repData!=null && repData.length>0){
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TrainingTypeMaster bean1 = new TrainingTypeMaster();
					bean1.setTrnCodeIt(String.valueOf(repData[i][0]));
					bean1.setTrnNameIt(String.valueOf(repData[i][1]));
					bean1.setTrnDescIt(Utility.checkNull(String.valueOf(repData[i][2])));
					bean1.setCreatedByIt(Utility.checkNull(String.valueOf(repData[i][4])));
					bean1.setCreatedOnIt(Utility.checkNull(String.valueOf(repData[i][3])));
					
					list.add(bean1);
				}
				trnBean.setTrnTypeList(list);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addTrnType(TrainingTypeMaster trnBean) {
		if (!checkDuplicate(trnBean)) {
			String query = "SELECT NVL(MAX(TRAINING_ID),0)+1 FROM TRN_TRAINING_TYPE";
			Object[][] rel = getSqlModel().getSingleResult(query);
			trnBean.setTrnCode(String.valueOf(rel[0][0]));
			trnBean.setHiddenTrnId(String.valueOf(rel[0][0]));
			
			Object[][] addObj = new Object[1][3];
			addObj[0][0] = trnBean.getTrnName().trim();   // Training name
			addObj[0][1] = trnBean.getTrndes().trim();  // Training descrition
			addObj[0][2] = trnBean.getUserEmpId();    // emp_id
						
			return getSqlModel().singleExecute(getQuery(1), addObj);

		}// end of if
		else {
			return false;
		}// end of else
	}
	
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TrainingTypeMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM TRN_TRAINING_TYPE WHERE UPPER(TRAINING_NAME) LIKE '"
				+ bean.getTrnName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	public boolean modTrnType(TrainingTypeMaster trnBean) {
	
			Object[][] addObj = new Object[1][4];
			addObj[0][0] = trnBean.getTrnName().trim();   // Training name
			addObj[0][1] = trnBean.getTrndes().trim();  // Training descrition
			addObj[0][2] = trnBean.getUserEmpId();    // emp_id
			addObj[0][3] = trnBean.getTrnCode(); 
				return getSqlModel().singleExecute(getQuery(2), addObj);
	
		
	}
	
	public boolean deleteTrn(TrainingTypeMaster trnBean) {
		
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = trnBean.getTrnCode(); 
			return getSqlModel().singleExecute(getQuery(3), delObj);

	
}
	
	

}
