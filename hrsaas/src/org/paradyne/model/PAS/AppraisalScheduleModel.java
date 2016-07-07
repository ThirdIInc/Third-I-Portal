package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.AppraisalSchedule;
import org.paradyne.lib.ModelBase;

public class AppraisalScheduleModel extends ModelBase {
	
	
	public void getSavedSchedule(AppraisalSchedule bean, HttpServletRequest request){
			
		Object[][] repData = getSqlModel().getSingleResult(getQuery(7));
		
		
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = repData.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) repData.length / 20.0;
			java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());
			ArrayList<Object> obj = new ArrayList<Object>();
			request.setAttribute("abc", rowCount1);
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;
				if (To_TOT > repData.length) {
					To_TOT = repData.length;
				}//end of if
				bean.setMyPage("1");
			}//end of if

			else {
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				}//end of if
				else {
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}//end of else
				if (To_TOT > repData.length) {
					To_TOT = repData.length;
				}//end of if
			}//end of else
			request.setAttribute("xyz", PageNo1);
			for (int i = From_TOT; i < To_TOT; i++) {
				AppraisalSchedule bean1 = new AppraisalSchedule();
				bean1.setApprId(checkNull(String.valueOf(repData[i][0])));
				bean1.setApprCode(checkNull(String.valueOf(repData[i][1])));
				bean1.setStartDate(checkNull(String.valueOf(repData[i][2])));
				bean1.setEndDate(checkNull(String.valueOf(repData[i][3])));
				bean1.setLockAppr(String.valueOf(repData[i][4]));
				obj.add(bean1);
			}//end of for loop
			bean.setReadFlag("true");
			bean.setDataList(obj);
	
		
	}
	public void calforedit(AppraisalSchedule bean) throws Exception {
		
		Object []apprId = new Object [1];
		apprId [0] = bean.getHiddencode();
		
		Object [][]data = getSqlModel().getSingleResult(getQuery(8), apprId);
		
		bean.setApprId(checkNull(String.valueOf(apprId [0])));
		bean.setApprCode(checkNull(String.valueOf(data[0][0])));
		bean.setStartDate(checkNull(String.valueOf(data[0][1])));
		bean.setEndDate(checkNull(String.valueOf(data[0][2])));
		bean.setAppStarted(checkNull(String.valueOf(data[0][3])));
		getSchedule(bean);
		
		
	}
	
	public void getEditData(AppraisalSchedule bean, Object []phaseCode,Object [] phaseName, Object [] phaseStartDate,Object [] phaseEndDate,Object [] phaseLock){
		
		ArrayList<Object> list=new ArrayList<Object>();
		
		if(phaseCode != null && phaseCode.length !=0){
			for (int i = 0; i < phaseCode.length; i++) {
				AppraisalSchedule bean1 = new AppraisalSchedule();
				bean1.setPhaseCode(checkNull(String.valueOf(phaseCode[i])));
				bean1.setPhaseName(checkNull(String.valueOf(phaseName[i])));
				bean1.setPhaseStartDate(checkNull(String.valueOf(phaseStartDate[i])));
				bean1.setPhaseEndDate(checkNull(String.valueOf(phaseEndDate[i])));
				bean1.setPhaseLockFlag(checkNull(String.valueOf(phaseLock[i])));
				
				list.add(bean1);

			}
		} // end of if
		bean.setPhaseList(list);
	}
	public boolean addNewSchedule(String apprCode, Object []phaseCode,Object [] phaseStartDate,Object [] phaseEndDate,Object [] phaseLock){
		boolean result= false;
		
		Object [][]scheduleData = new Object[phaseCode.length][5];
				
		for(int i= 0; i< phaseCode.length ; i++){
			
			scheduleData[i][0]= apprCode;
			scheduleData[i][1]= phaseCode[i];
			scheduleData[i][2]= phaseStartDate[i];
			scheduleData[i][3]= phaseEndDate[i];
			scheduleData[i][4]= phaseLock[i];
			
		}
		
		result = getSqlModel().singleExecute(getQuery(2),scheduleData);
		
		
		return result; 
	}
	
	public boolean saveSchedule(String apprCode, Object []phaseCode,Object [] phaseStartDate,Object [] phaseEndDate,Object [] phaseLock){
		boolean result= false;
		
		
		return result; 
	}
	public void getPhases(AppraisalSchedule bean){
		Object []apprId = new Object [1];
		apprId [0] = bean.getApprId();
		
		try{
			Object [][] phaseData = getSqlModel().getSingleResult(getQuery(1), apprId);
			ArrayList<Object> list=new ArrayList<Object>();
			if(phaseData != null && phaseData.length !=0){
				for (int i = 0; i < phaseData.length; i++) {
					AppraisalSchedule bean1 = new AppraisalSchedule();
					bean1.setPhaseCode(checkNull(String.valueOf(phaseData[i][0])));
					bean1.setPhaseName(checkNull(String.valueOf(phaseData[i][1])));
					
					list.add(bean1);

				}
			} // end of if
		bean.setPhaseList(list);
			}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
		
		public void getSchedule(AppraisalSchedule bean){
			Object []apprId = new Object [1];
			apprId [0] = bean.getApprId();
			
						
			try{
				Object [][] scheduleData = getSqlModel().getSingleResult(getQuery(3), apprId);
				ArrayList<Object> list=new ArrayList<Object>();
				if(scheduleData != null && scheduleData.length !=0){
					for (int i = 0; i < scheduleData.length; i++) {
						AppraisalSchedule bean1 = new AppraisalSchedule();
						
						bean1.setPhaseCode(checkNull(String.valueOf(scheduleData[i][0])));
						
						bean1.setPhaseName(checkNull(String.valueOf(scheduleData[i][1])));
						bean1.setPhaseStartDate(checkNull(String.valueOf(scheduleData[i][2])));
						bean1.setPhaseEndDate(checkNull(String.valueOf(scheduleData[i][3])));
						bean1.setPhaseLockFlag(checkNull(String.valueOf(scheduleData[i][4])));
						
						list.add(bean1);

					}
				} // end of if
				bean.setAddFlag("false");
				bean.setReadFlag("false");
				
			bean.setPhaseList(list);
				}catch (Exception e) {
				e.printStackTrace();
				
			}
		
	}
		public boolean updateSchedule(String apprCode, Object []phaseCode,Object [] phaseStartDate,Object [] phaseEndDate,Object [] phaseLock){
			boolean updateResult = false;
			boolean newResult = false;
			try{
			Object []apprId = new Object [1];
			apprId [0] = apprCode;
			
			Object [][] resultData = getSqlModel().getSingleResult(getQuery(5),apprId);
							
			Object [][]updateSchedule = new Object[resultData.length][5];
				
			Object [][]newSchedule = new Object[phaseCode.length - resultData.length][5]; 
			int k=0;
			int l=0;
			
			boolean flag=false;
			for(int i=0;i<phaseCode.length;i++){
				abc:	for(int j=0;j<resultData.length;j++){
						if(String.valueOf(resultData[j][0]).equals(String.valueOf(phaseCode[i]))){	
							flag=true;
							updateSchedule[l][0]= phaseStartDate[j];
							updateSchedule[l][1]= phaseEndDate[j];
							updateSchedule[l][2]= phaseLock[j];
							updateSchedule[l][3]= phaseCode[j];
							updateSchedule[l][4]= apprCode;	
							
							l++;
							break abc;
						}else
						{
							flag=false;	
						}
					}
					 
					if(flag==false)
					{					
						newSchedule[k][0]= apprCode;
						newSchedule[k][1]= phaseCode[i];
						newSchedule[k][2]= phaseStartDate[i];
						newSchedule[k][3]= phaseEndDate[i];
						newSchedule[k][4]= phaseLock[i];
						k++;
					}
			}
			
			updateResult = getSqlModel().singleExecute(getQuery(4), updateSchedule);
			
			newResult = getSqlModel().singleExecute(getQuery(2),newSchedule);	
						
			
			return updateResult;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		public boolean deleteSchedule(Object[][] apprCode){
			boolean result=false;
			
			
			result = getSqlModel().singleExecute(getQuery(6),apprCode);
			
			return result;
		}
		
		
		public boolean deletecheckedRecords(AppraisalSchedule bean, String[] code) {
			boolean result = false;
			int count = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {

					if (!code[i].equals("")) {

						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result = getSqlModel().singleExecute(getQuery(6), delete);
						if (!result)
							count++;

					}//end of if
				}//end of for loop
			}//end of if
			if (count != 0) {
				result = false;
				return result;
			}//end of if 
			else {
				result = true;
				return result;
			}//end of else
		}
		
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}

}
