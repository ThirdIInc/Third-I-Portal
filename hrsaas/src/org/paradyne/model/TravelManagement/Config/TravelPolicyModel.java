/**
 * 
 */
package org.paradyne.model.TravelManagement.Config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Config.TravelPolicy;
import org.paradyne.lib.ModelBase;

import common.Logger;
 
/**
 * @author aa0417
 *
 */
public class TravelPolicyModel extends ModelBase {
	
	public void callEmpGrade(TravelPolicy bean)
	{
		Object[][] data = getSqlModel().getSingleResult(getQuery(1));
		if(data != null && data.length>0)
		{
		ArrayList list = new ArrayList();
			for(int i=0;i<data.length;i++)
			{
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setEmpGrade(""+data[i][0]);
				bean1.setEmpGradeId(""+data[i][1]); 
				 if(bean.getEditFlag().equals("true"))	{  
					 bean1.setEditFlag("true");
					}else
					{	 bean1.setEditFlag("false"); 
					}
				list.add(bean1);
			}
			bean.setGradeList(list);   
		}
	}
	
	public void callSetEmpGrade(TravelPolicy bean,HttpServletRequest request)
	{
		 String [] empGrade =(String[])request.getParameterValues("empGrade");
		 String [] empGradeId =(String[])request.getParameterValues("empGradeId");
		 String [] gradeStatus =(String[])request.getParameterValues("hidGradeStatus");  
          if(empGrade!=null && empGrade.length>0)
           {
			  ArrayList list = new ArrayList();
				for(int i=0;i<empGradeId.length;i++)
				{
					TravelPolicy bean1 = new TravelPolicy();
					bean1.setEmpGrade(""+empGrade[i]);
					bean1.setEmpGradeId(""+empGradeId[i]); 
					
					 if(gradeStatus[i].equals("Y"))
					 {
						bean1.setGradeStatus("checked");
						bean1.setHidGradeStatus("Y");
					 }
					list.add(bean1);
				}
				bean.setGradeList(list);  
          }
			 
	}
	 
	public void callForSecondSett(TravelPolicy bean)
	{  
		// for Expense Category
		Object[][] expData = getSqlModel().getSingleResult(getQuery(2));
		if(expData != null && expData.length>0)
		{
		ArrayList expnseList = new ArrayList();
			for(int i=0;i<expData.length;i++)
			{ 
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setExCategory(""+expData[i][0]);
				bean1.setExpCatUnit(""+expData[i][1]); 
				bean1.setExCategoryId(""+expData[i][2]); 
				bean1.setExpCatUnitCode(""+expData[i][3]);  
				bean1.setExpValuewithBill("0");
				bean1.setExpValuewithoutBill("0");
				// for edit purpose only
				 if(bean.getEditFlag().equals("true"))	{  
					 bean1.setEditFlag("true");
					}else
					{	 bean1.setEditFlag("false"); 
					}
				expnseList.add(bean1);
			} 
			bean.setExpList(expnseList);  
		}
		// for travel mode
		Object[][] trModeData = getSqlModel().getSingleResult(getQuery(3));
		if(trModeData != null && trModeData.length>0)
		{    String chkTrMode = "";
		    ArrayList trModeList = new ArrayList(); 
			for(int i=0;i<trModeData.length;i++)
			{ 
				TravelPolicy bean1 = new TravelPolicy();
				if(!chkTrMode.equals(""+trModeData[i][0]))
				{
					bean1.setTravelMode(""+trModeData[i][0]);
				}
				bean1.setTravelClass(""+trModeData[i][1]); 
				bean1.setTravelModeId(""+trModeData[i][2]);
			//	bean1.setTravelClassId(""+trModeData[i][3]); 
				chkTrMode = ""+trModeData[i][0];
				// for edit purpose only
				 if(bean.getEditFlag().equals("true"))	{  
					 bean1.setEditFlag("true");
					}else
					{	 bean1.setEditFlag("false"); 
					}
				trModeList.add(bean1);
			}
			System.out.println("Travel List====== "+trModeList.size());
			bean.setTravelModeList(trModeList);  
		}
			// for Hotel Type
			String hotelSql="SELECT  NVL(HOTEL_TYPE_NAME,' '), HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS ='A'"
				            +"  ORDER BY HOTEL_LEVEL ";
			Object[][] hotelData = getSqlModel().getSingleResult(hotelSql);
			
			String roomSql=" SELECT NVL(ROOM_TYPE_NAME,' ') ,ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS ='A' "
				+"	ORDER BY ROOM_LEVEL"; 
	      Object[][] roomData = getSqlModel().getSingleResult(roomSql);
	
			if(hotelData != null && hotelData.length>0)
			{
				String hotelTypeChk="";
			ArrayList hotelList = new ArrayList();
				for(int i=0;i<hotelData.length;i++)
				{  
					for(int j=0;j<roomData.length;j++)
					{ 
						TravelPolicy bean1 = new TravelPolicy();
						if(!(hotelTypeChk.equals(hotelData[i][0])))
						{
							bean1.setHotelType(""+hotelData[i][0]);
							System.out.println("in iffffffffff");
						} else
						{
							System.out.println("in elseeeeeee");
						}
						 
						bean1.setHotelTypeId(""+hotelData[i][1]); 
						bean1.setRoomType(""+roomData[j][0]); 
						bean1.setRoomTypeId(""+roomData[j][1]);
						
						// for edit purpose only
						 if(bean.getEditFlag().equals("true"))	{  
							 bean1.setEditFlag("true");
							}else
							{	 bean1.setEditFlag("false"); 
							}
						 
						hotelList.add(bean1);
						hotelTypeChk=""+hotelData[i][0];
				   } 
			   } 
				bean.setHotelTypeList(hotelList); 
			}
	}
	public void callSecondPrevious(TravelPolicy bean,HttpServletRequest request)
	{
		 String [] empGrade =(String[])request.getParameterValues("empGrade");
		 String [] empGradeId =(String[])request.getParameterValues("empGradeId");
		 String [] gradeStatus =(String[])request.getParameterValues("hidGradeStatus");  
		 
		 if(empGrade!=null && empGrade.length>0)
		 {  ArrayList list = new ArrayList();
			for(int i=0;i<empGradeId.length;i++)
			{
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setEmpGrade(""+empGrade[i]);
				bean1.setEmpGradeId(""+empGradeId[i]); 
				 System.out.println("in gradeStatus============="+gradeStatus[i]);
				 if(gradeStatus[i].equals("Y"))
				 {
					 System.out.println("in true=============");
					bean1.setGradeStatus("checked");
					bean1.setHidGradeStatus("Y");
				 }
				// for edit purpose only
				 if(bean.getEditFlag().equals("true"))	{  
					 bean1.setEditFlag("true");
					}else
					{	 bean1.setEditFlag("false"); 
					}
				list.add(bean1);
			}
			bean.setGradeList(list);  
		 }
		
	}
	
	public void callOnlod(TravelPolicy bean)
	{ 
	// for Grade
		String sysSql ="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] sysData = getSqlModel().getSingleResult(sysSql);
		bean.setPolicySysDate(""+sysData[0][0]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(1));
		if(data != null && data.length>0)
		{
		ArrayList list = new ArrayList();
			for(int i=0;i<data.length;i++)
			{
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setEmpGrade(""+data[i][0]);
				bean1.setEmpGradeId(""+data[i][1]); 
				list.add(bean1);
			}
			bean.setGradeList(list);  
		}
	
	// for Expense Category
		Object[][] expData = getSqlModel().getSingleResult(getQuery(2));
		if(expData != null && expData.length>0)
		{
		ArrayList expnseList = new ArrayList();
			for(int i=0;i<expData.length;i++)
			{ 
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setExCategory(""+expData[i][0]);
				bean1.setExpCatUnit(""+expData[i][1]); 
				bean1.setExCategoryId(""+expData[i][2]); 
				bean1.setExpCatUnitCode(""+expData[i][3]);  
				bean1.setExpValuewithBill("0");
				bean1.setExpValuewithoutBill("0");
				expnseList.add(bean1);
			} 
			bean.setExpList(expnseList);  
		}
			
			// for Travel Mode
			Object[][] trModeData = getSqlModel().getSingleResult(getQuery(3));
			if(trModeData != null && trModeData.length>0)
			{
			ArrayList trModeList = new ArrayList();
			System.out.println("trModeData List====== "+trModeData.length);
			trModeList.clear();
				for(int i=0;i<trModeData.length;i++)
				{ 
					TravelPolicy bean1 = new TravelPolicy();
					bean1.setTravelMode(""+trModeData[i][0]);
					bean1.setTravelClass(""+trModeData[i][1]); 
					bean1.setTravelModeId(""+trModeData[i][2]);
				//	bean1.setTravelClassId(""+trModeData[i][3]); 
					trModeList.add(bean1);
				}
				System.out.println("Travel List====== "+trModeList.size());
				bean.setTravelModeList(trModeList);  
			}
				// for Hotel Type
				String hotelSql="SELECT  NVL(HOTEL_TYPE_NAME,' '), HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS ='A'"
					            +"  ORDER BY HOTEL_LEVEL,HOTEL_TYPE_NAME ";
				Object[][] hotelData = getSqlModel().getSingleResult(hotelSql);
				
				String roomSql=" SELECT NVL(ROOM_TYPE_NAME,' ') ,ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS ='A' "
					+"	ORDER BY ROOM_LEVEL,ROOM_TYPE_NAME"; 
		      Object[][] roomData = getSqlModel().getSingleResult(roomSql);
		
				if(hotelData != null && hotelData.length>0)
				{
					String hotelTypeChk="";
				ArrayList hotelList = new ArrayList();
					for(int i=0;i<hotelData.length;i++)
					{  
						for(int j=0;j<roomData.length;j++)
						{ 
							TravelPolicy bean1 = new TravelPolicy();
							if(!(hotelTypeChk.equals(hotelData[i][0])))
							{
								bean1.setHotelType(""+hotelData[i][0]);
								System.out.println("in iffffffffff");
							} else
							{
								System.out.println("in elseeeeeee");
							}
							bean1.setHotelTypeId(""+hotelData[i][1]); 
							bean1.setRoomType(""+roomData[j][0]); 
							bean1.setRoomTypeId(""+roomData[j][1]);  
							hotelList.add(bean1);
							hotelTypeChk=""+hotelData[i][0];
							System.out.println("hotelTyp============= "+hotelTypeChk);
					   } 
				   }
					  
					bean.setHotelTypeList(hotelList); 
				}
			
	   }  // end of callOnlod
	
	public void callForExTrMode(TravelPolicy bean,HttpServletRequest request)
	{
		 String [] exCategory =(String[])request.getParameterValues("exCategory");
		 String [] exCategoryId =(String[])request.getParameterValues("exCategoryId");
		 String [] expCatUnit =(String[])request.getParameterValues("expCatUnit"); 
		 
/*
 * 
 * Added by manish sakpal
 */		 
		 String [] expValuewithBill =(String[])request.getParameterValues("expValuewithBill");  
		 String [] expValuewithoutBill =(String[])request.getParameterValues("expValuewithoutBill");
		 
		//String [] expValue =(String[])request.getParameterValues("expValue");
		 String [] expCatUnitCode =(String[])request.getParameterValues("expCatUnitCode");  
		 String [] hidActualAtt =(String[])request.getParameterValues("hidActualAtt");  
	 
		 if(exCategory!=null && exCategory.length>0)
		 {
			 ArrayList expnseList = new ArrayList();
				for(int i=0;i<exCategoryId.length;i++)
				{ 
					TravelPolicy bean1 = new TravelPolicy();
					bean1.setExCategory(""+exCategory[i]);
					bean1.setExCategoryId(""+exCategoryId[i]); 
					bean1.setExpCatUnit(""+expCatUnit[i]); 
				/**
				 * Added by manish sakpal
				 */	
					bean1.setExpValuewithBill(""+expValuewithBill[i]);
					bean1.setExpValuewithoutBill(""+expValuewithoutBill[i]); 
					
					
					bean1.setExpCatUnitCode(""+expCatUnitCode[i]);  
					if(hidActualAtt[i].equals("Y"))
					{
						bean1.setActualAtt("checked"); 
						bean1.setReadOnlyFlag("readOnly"); 
						bean1.setHidActualAtt("Y"); 
					}
					
					// for edit purpose only
					 if(bean.getEditFlag().equals("true"))	{  
						 bean1.setEditFlag("true");
						}else
						{	 bean1.setEditFlag("false"); 
						}
					 
					expnseList.add(bean1);
				} 
				bean.setExpList(expnseList);   
		 } 
// for Travel Mode  
		 String [] travelMode =(String[])request.getParameterValues("travelMode");
		 String [] travelModeId =(String[])request.getParameterValues("travelModeId");
		 String [] travelClass =(String[])request.getParameterValues("travelClass");  
		// String [] travelClassId =(String[])request.getParameterValues("travelClassId");  
		// String [] trModeStatus =(String[])request.getParameterValues("trModeStatus");
		 String [] hidtrModeStatus =(String[])request.getParameterValues("hidtrModeStatus");   
		 if(travelMode !=null && travelMode.length>0)
		 {   ArrayList trModeList = new ArrayList();
				for(int i=0;i<travelModeId.length;i++)
				{ 	TravelPolicy bean1 = new TravelPolicy();
					bean1.setTravelMode(""+travelMode[i]);
					bean1.setTravelClass(""+travelClass[i]); 
					bean1.setTravelModeId(""+travelModeId[i]);
				//	bean1.setTravelClassId(""+travelClassId[i]); 
					System.out.println("hidtrModeStatus[i]================"+hidtrModeStatus[i]);
					System.out.println("bean1.getTrModeStatus()================="+bean1.getTrModeStatus());
					 if(hidtrModeStatus[i].equals("Y")) 
					 {
						bean1.setTrModeStatus("checked"); 
						bean1.setHidtrModeStatus("Y");
					 }
						// for edit purpose only
					 if(bean.getEditFlag().equals("true"))	{  
						 bean1.setEditFlag("true");
						}else
						{	 bean1.setEditFlag("false"); 
						}
					 
					trModeList.add(bean1);
				} 
				bean.setTravelModeList(trModeList); 
		 }
 
	 // for Hotel type
	  
		 String [] hotelType =(String[])request.getParameterValues("hotelType");
		 String [] hotelTypeId =(String[])request.getParameterValues("hotelTypeId");
		 String [] roomType =(String[])request.getParameterValues("roomType");  
		 String [] roomTypeId =(String[])request.getParameterValues("roomTypeId");   
		 String [] hidHotelStatus =(String[])request.getParameterValues("hidHotelStatus");   
		 if(hotelTypeId!=null && hotelTypeId.length>0)
		 {
			 ArrayList hotelList = new ArrayList();
				for(int j=0;j<hotelTypeId.length;j++)
				{   	TravelPolicy bean1 = new TravelPolicy();
						bean1.setHotelType(""+hotelType[j]);
						bean1.setHotelTypeId(""+hotelTypeId[j]); 
						bean1.setRoomType(""+roomType[j]); 
						bean1.setRoomTypeId(""+roomTypeId[j]);  
						if(hidHotelStatus[j].equals("Y"))
						 {
							bean1.setHotelStatus("checked"); 
							bean1.setHidHotelStatus("Y"); 
						 }
						// for edit purpose only
						 if(bean.getEditFlag().equals("true"))	{  
							 bean1.setEditFlag("true");
							}else
							{	 bean1.setEditFlag("false"); 
							}
						 
						hotelList.add(bean1); 
				} 
				bean.setHotelTypeList(hotelList); 
		 }
		
	}
	public boolean save(TravelPolicy bean,HttpServletRequest request)
	{
		Object [][]polObj= new Object[1][10]; 
		polObj[0][0]=bean.getPolicyName(); 
		polObj[0][1]=bean.getEffectDate();
		polObj[0][2]=bean.getSettleDays();
		polObj[0][3]=bean.getPolicyAbbr(); 
		polObj[0][4]=bean.getDesc();
		polObj[0][5]=bean.getStatus();
		polObj[0][6]=bean.getGuidLines(); 
		polObj[0][7]=bean.getExpCateTravelId(); 
		polObj[0][8]=bean.getExpCateHotelId(); 
		polObj[0][9]=bean.getTotExpAmt(); 
		boolean flag ;
		flag = getSqlModel().singleExecute(getQuery(4),polObj);  
		String polSql="SELECT MAX(POLICY_ID) FROM   HRMS_TMS_TRAVEL_POLICY";
		Object[][] dataPol = getSqlModel().getSingleResult(polSql);
		if(dataPol != null && dataPol.length>0)
		{
			
			bean.setPolicyId(""+dataPol[0][0]); 
		}
		saveDtl(bean,request,flag);
		return flag;
	}
	
	public boolean update(TravelPolicy bean,HttpServletRequest request)
	{
		Object [][]polObj= new Object[1][11]; 
		polObj[0][0]=bean.getPolicyName(); 
		polObj[0][1]=bean.getEffectDate();
		polObj[0][2]=bean.getSettleDays();
		polObj[0][3]=bean.getPolicyAbbr(); 
		polObj[0][4]=bean.getDesc();
		polObj[0][5]=bean.getStatus();
		polObj[0][6]=bean.getGuidLines(); 
		polObj[0][7]=bean.getExpCateTravelId(); 
		polObj[0][8]=bean.getExpCateHotelId(); 
		polObj[0][9]=bean.getTotExpAmt(); 
		polObj[0][10]=bean.getPolicyId();
		
		boolean flag ;
		flag = getSqlModel().singleExecute(getQuery(5),polObj);   
		
		 String delHotel="DELETE FROM HRMS_TMS_POLICY_HOTEL_TYPE WHERE PHT_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delHotel);
		 if(flag)
		 {
		 String delTrMode="DELETE FROM HRMS_TMS_POLICY_TRAVEL_MODE WHERE PTM_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delTrMode); 
		 }
		 if(flag)
		 {		 
		 String delExp="DELETE FROM   HRMS_TMS_POLICY_EXPENSE WHERE PE_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delExp); 
		 }
		 if(flag)
		 {
		 String delGrade="DELETE FROM HRMS_TMS_POLICY_GRADE WHERE  PG_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delGrade); 
		 } 
		saveDtl(bean,request,flag);
		return flag;
	}
	
	
	public boolean saveDtl(TravelPolicy bean,HttpServletRequest request,boolean flag)
	{
		 if(flag)
		  {
			 String [] empGrade =(String[])request.getParameterValues("empGrade");
			 String [] empGradeId =(String[])request.getParameterValues("empGradeId");
			 String [] gradeStatus =(String[])request.getParameterValues("hidGradeStatus");  
			 String[] empStatusGradeId= new String[empGradeId.length];
			 int j=1;
			 for(int i=0;i<empGradeId.length;i++)
			 {    
				 if(gradeStatus[i].equals("Y"))
				 {
					 empStatusGradeId[i]= empGradeId[i];
				 }else
				 {
					 empStatusGradeId[i]=empGradeId[i];
				 }
				 String status =gradeStatus[i].equals("Y")?"Y":"N";  
				 String gradeSql ="  INSERT INTO HRMS_TMS_POLICY_GRADE ( PG_ID, PG_POLICY_ID,PG_EMP_GRADE, PG_STATUS)  "
						          +" VALUES (( SELECT NVL(MAX(PG_ID),0)+1 FROM HRMS_TMS_POLICY_GRADE),"+bean.getPolicyId()+" ,"+empStatusGradeId[i]+" ,'"+status+"')";
				 flag= getSqlModel().singleExecute(gradeSql); 
				 j++;
			   
			 }
			 
			    ArrayList list = new ArrayList();
				for(int i=0;i<empGradeId.length;i++)
				{
					TravelPolicy bean1 = new TravelPolicy();
					bean1.setEmpGrade(""+empGrade[i]);
					bean1.setEmpGradeId(""+empGradeId[i]); 
					
					 if(gradeStatus[i].equals("Y"))
					 {
						bean1.setGradeStatus("checked");
					 }
					list.add(bean1);
				}
				bean.setGradeList(list);   
			  
		  }
		 // for expense Category 
		 
		 if (flag)
		 	{   
			 String [] exCategory =(String[])request.getParameterValues("exCategory");
			 String [] exCategoryId =(String[])request.getParameterValues("exCategoryId");
			 String [] expCatUnit =(String[])request.getParameterValues("expCatUnit");  
			
/*
 * 
 * Added by manish sakpal
*/		 
	 		 String [] expValuewithBill =(String[])request.getParameterValues("expValuewithBill");  
	 		 String [] expValuewithoutBill =(String[])request.getParameterValues("expValuewithoutBill");	 		 			 
			 
			 
			 //String [] expValue =(String[])request.getParameterValues("expValue");  
			 String [] expCatUnitCode =(String[])request.getParameterValues("expCatUnitCode");  
			 String [] hidActualAtt =(String[])request.getParameterValues("hidActualAtt");   
			 int j=1;
			 for(int i=0;i<exCategoryId.length;i++)
			 {   

/*
 * Changes into the expSql
 * Add expense with bill and expense without bill in table HRMS_TMS_POLICY_EXPENSE
 */				 
				 String expSql =" INSERT INTO HRMS_TMS_POLICY_EXPENSE ( PE_ID, PE_POLICY_ID, PE_EXP_CAT_ID,PE_EXP_CAT_UNIT, PE_EXP_CAT_VALUE_WITH_BILL,PE_EXP_CAT_VALUE_WITHOUT_BILL,PE_EXP_ACTUAL_ATT)  "
                                +" VALUES ( (SELECT NVL(MAX(PE_ID),0)+1 FROM HRMS_TMS_POLICY_EXPENSE),"+bean.getPolicyId()+" ," 
                                +""+exCategoryId[i]+" ,'"+expCatUnitCode[i]+"',"+expValuewithBill[i]+","+expValuewithoutBill[i]+",'"+hidActualAtt[i]+"')";
				 flag= getSqlModel().singleExecute(expSql); 
				 j++; 
			 }
			 
			 ArrayList expnseList = new ArrayList();
			for (int i = 0; i < exCategoryId.length; i++)
			 {
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setExCategory("" + exCategory[i]);
				bean1.setExCategoryId("" + exCategoryId[i]);
				bean1.setExpCatUnit("" + expCatUnit[i]);
				
				/*
				 * Added by manish sakpal
				 * 
				 */
				
				//bean1.setExpValue("" + expValue[i]);
				bean1.setExpValuewithBill(""+expValuewithBill[i]);
				bean1.setExpValuewithoutBill(""+expValuewithoutBill[i]);
				
				bean1.setExpCatUnitCode("" + expCatUnitCode[i]);
				if (hidActualAtt[i].equals("Y")) {
					bean1.setActualAtt("checked");
					bean1.setReadOnlyFlag("readOnly"); 
					bean1.setHidActualAtt("Y");
				}
				expnseList.add(bean1);
			}
			bean.setExpList(expnseList);
		}
		 
 // for Travel Mode
		 
		 if(flag)
		  { 
		 
			 String [] travelMode =(String[])request.getParameterValues("travelMode");
			 String [] travelModeId =(String[])request.getParameterValues("travelModeId");
			 String [] travelClass =(String[])request.getParameterValues("travelClass");  
			// String [] travelClassId =(String[])request.getParameterValues("travelClassId");  
			// String [] trModeStatus =(String[])request.getParameterValues("trModeStatus");
			 String [] hidtrModeStatus =(String[])request.getParameterValues("hidtrModeStatus");  
		 
			 for(int i=0;i<travelModeId.length;i++)
			 {  
				 String status =hidtrModeStatus[i].equals("Y")?"Y":"N";  
				 
				 String expSql =" INSERT INTO HRMS_TMS_POLICY_TRAVEL_MODE ( PTM_ID, PTM_POLICY_ID, PTM_TRAVEL_MODE_ID, PTM_STATUS)" 
				 		+"  VALUES ((SELECT NVL(MAX(PTM_ID),0)+1 FROM HRMS_TMS_POLICY_TRAVEL_MODE),(SELECT NVL(MAX(POLICY_ID),0) FROM  HRMS_TMS_TRAVEL_POLICY) ,"+travelModeId[i] 
				 		+",'"+status+"')";
				 flag= getSqlModel().singleExecute(expSql);  
			 }
			 
			 ArrayList trModeList = new ArrayList();
				for(int i=0;i<travelModeId.length;i++)
				{ 
					TravelPolicy bean1 = new TravelPolicy();
					bean1.setTravelMode(""+travelMode[i]);
					bean1.setTravelClass(""+travelClass[i]); 
					bean1.setTravelModeId(""+travelModeId[i]);
				//	bean1.setTravelClassId(""+travelClassId[i]); 
					 if(hidtrModeStatus[i].equals("Y"))
					 {
						bean1.setTrModeStatus("checked"); 
					 }
					trModeList.add(bean1);
				}
				 
				bean.setTravelModeList(trModeList); 
				 
		  } 
		 // for Hotel type
		 if(flag)
		  { 
		 
			 String [] hotelType =(String[])request.getParameterValues("hotelType");
			 String [] hotelTypeId =(String[])request.getParameterValues("hotelTypeId");
			 String [] roomType =(String[])request.getParameterValues("roomType");  
			 String [] roomTypeId =(String[])request.getParameterValues("roomTypeId");   
			 String [] hidHotelStatus =(String[])request.getParameterValues("hidHotelStatus");  
			 
			 for(int i=0;i<hotelTypeId.length;i++)
			 {  
				 String status =hidHotelStatus[i].equals("Y")?"Y":"N";  
				 
				 String HotelSql =" INSERT INTO HRMS_TMS_POLICY_HOTEL_TYPE (PHT_ID, PHT_POLICY_ID, PHT_HOTEL_TYPE_ID,PHT_ROOM_ID, PHT_STATUS) "
                               +" VALUES ( (SELECT NVL(MAX(PHT_ID),0)+1 FROM HRMS_TMS_POLICY_HOTEL_TYPE),(SELECT NVL(MAX(POLICY_ID),0) FROM  HRMS_TMS_TRAVEL_POLICY) ," 
                               	+""+hotelTypeId[i]+","+roomTypeId[i]+",'"+status+"')";
				 flag= getSqlModel().singleExecute(HotelSql); 				 
			 } 
			 
			 ArrayList hotelList = new ArrayList();
				for(int j=0;j<hotelTypeId.length;j++)
				{   	TravelPolicy bean1 = new TravelPolicy();
						bean1.setHotelType(""+hotelType[j]);
						bean1.setHotelTypeId(""+hotelTypeId[j]); 
						bean1.setRoomType(""+roomType[j]); 
						bean1.setRoomTypeId(""+roomTypeId[j]);  
						if(hidHotelStatus[j].equals("Y"))
						 {
							bean1.setHotelStatus("checked"); 
						 }
						hotelList.add(bean1); 
			   }
				  
				bean.setHotelTypeList(hotelList);  
		  }  
		 return flag;
	} // end of save
	
	
	public void savedPolicyDetails(TravelPolicy bean)
	{
		 String sql ="SELECT POLICY_NAME,POLICY_ABBR,TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), POLICY_STATUS ,NVL(POLICY_DESC,' '),DECODE(POLICY_STATUS,'A','Active','D','Deactive') "
			 	   +" FROM HRMS_TMS_TRAVEL_POLICY  WHERE POLICY_ID ="+bean.getPolicyId();
		 Object[][] polData= getSqlModel().getSingleResult(sql);
		 if(polData!=null && polData.length >0)
		 {
			bean.setPolicyName(""+polData[0][0]);
			bean.setPolicyAbbr(""+polData[0][1]);
			bean.setEffectDate(""+polData[0][2]);
			bean.setStatus(""+polData[0][3]);
			bean.setDesc(""+polData[0][4]);
			bean.setEditStatus(""+polData[0][5]);
		 }
	}
	
	public void savedGradeDetails(TravelPolicy bean)
	{
		String gradeSql="  SELECT CADRE_NAME, PG_EMP_GRADE, PG_STATUS FROM HRMS_TMS_POLICY_GRADE "
			+" LEFT JOIN HRMS_CADRE ON (CADRE_ID=PG_EMP_GRADE)"
			+" WHERE PG_POLICY_ID ="+bean.getPolicyId()
			+" ORDER BY PG_ID";
     Object[][] gradeData= getSqlModel().getSingleResult(gradeSql);
		if(gradeData != null && gradeData.length>0)
		{
		ArrayList list = new ArrayList();
		for(int i=0;i<gradeData.length;i++)
		{
			TravelPolicy bean1 = new TravelPolicy();
			bean1.setEmpGrade(""+gradeData[i][0]);
			bean1.setEmpGradeId(""+gradeData[i][1]); 
			 if(gradeData[i][2].equals("Y"))
			 { 
				bean1.setGradeStatus("checked"); 
				bean1.setHidGradeStatus("Y");
			 }
			 if(bean.getEditFlag().equals("true"))	{  
				 bean1.setEditFlag("true");
				}else
				{	 bean1.setEditFlag("false"); 
				}
			list.add(bean1);
		}
		bean.setGradeList(list);   
		}
	}
	
	public void savedExpTravelDetails(TravelPolicy bean)
	{
		 // for general details
		String genSql ="SELECT t1.EXP_CATEGORY_NAME,POLICY_EXPCAT_TRAVEL, h1.EXP_CATEGORY_NAME, POLICY_EXPCAT_HOTEL ,"
				+" POLICY_SETTELMENT_DAYS,NVL(POLICY_GUIDLINES,' '),POLICY_TOTAL_EXPENSE FROM HRMS_TMS_TRAVEL_POLICY "
				+" LEFT JOIN HRMS_TMS_EXP_CATEGORY t1 ON(t1.EXP_CATEGORY_ID =POLICY_EXPCAT_TRAVEL) "
			    +" LEFT JOIN HRMS_TMS_EXP_CATEGORY h1 ON(h1.EXP_CATEGORY_ID=POLICY_EXPCAT_HOTEL) "
				+"WHERE POLICY_ID ="+bean.getPolicyId();
		Object[][] genData = getSqlModel().getSingleResult(genSql);
		
		if(genData!=null && genData.length>0)
		{ 
			bean.setExpCateTravel(""+genData[0][0]);
			bean.setExpCateTravelId(""+genData[0][1]);
			bean.setExpCateHotel(""+genData[0][2]);
			bean.setExpCateHotelId(""+genData[0][3]);
			bean.setSettleDays(""+genData[0][4]); 
			bean.setGuidLines(""+genData[0][5]); 
			bean.setTotExpAmt(""+genData[0][6]); 
		}
			 
		// for Expense Category
		//added by manish sakpal
		//Add two columns PE_EXP_CAT_VALUE_WITH_BILL and PE_EXP_CAT_VALUE_WITHOUT_BILL
		String expSql =" SELECT  EXP_CATEGORY_NAME,  DECODE(PE_EXP_CAT_UNIT,'D','Per Day','T','Per Travel','K','Per Kilometer') , "
					+"	PE_EXP_CAT_VALUE_WITH_BILL,PE_EXP_CAT_VALUE_WITHOUT_BILL ,PE_EXP_CAT_UNIT,PE_EXP_CAT_ID ,PE_EXP_ACTUAL_ATT FROM HRMS_TMS_POLICY_EXPENSE "
					+"	LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(EXP_CATEGORY_ID=PE_EXP_CAT_ID) "
					+"	WHERE PE_POLICY_ID ="+bean.getPolicyId()+" ORDER BY PE_ID "; 
		Object[][] expData = getSqlModel().getSingleResult(expSql);
		if(expData != null && expData.length>0)
		{
		ArrayList expnseList = new ArrayList();
			for(int i=0;i<expData.length;i++)
			{   
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setExCategory(""+expData[i][0]);
				bean1.setExpCatUnit(""+expData[i][1]); 
				
				//bean1.setExpValue(""+expData[i][2]);  
				
				bean1.setExpValuewithBill(""+expData[i][2]);
				bean1.setExpValuewithoutBill(""+expData[i][3]);
				bean1.setExpCatUnitCode(""+expData[i][4]);  
				bean1.setExCategoryId(""+expData[i][5]); 
				if((""+expData[i][5]).equals("Y"))
				{
					bean1.setActualAtt("checked"); 
					bean1.setReadOnlyFlag("readOnly"); 
					bean1.setHidActualAtt("Y"); 
				}else
				{
					bean1.setHidActualAtt("N"); 
				} 
				// for edit purpose only
				 if(bean.getEditFlag().equals("true"))	{  
					 bean1.setEditFlag("true");
					}else
					{	 bean1.setEditFlag("false"); 
					}
				expnseList.add(bean1);
			} 
			bean.setExpList(expnseList); 
		}
			
		// FOR TRAVEL MODE
			
		String trModeSql ="  SELECT  JOURNEY_NAME,JOURNEY_CLASS_NAME, PTM_TRAVEL_MODE_ID, "
						+"  PTM_STATUS FROM HRMS_TMS_POLICY_TRAVEL_MODE "
						+"	LEFT JOIN HRMS_TMS_JOURNEY ON(JOURNEY_ID = PTM_TRAVEL_MODE_ID) "
					//	+"	LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON( JOURNEY_CLASS_ID = PTM_TRAVEL_CLASS_ID) " 
						+"	WHERE PTM_POLICY_ID ="+bean.getPolicyId()+" ORDER BY PTM_ID ";
		Object[][] trModeData = getSqlModel().getSingleResult(trModeSql); 
		if(trModeData != null && trModeData.length>0)
		{
			String chkTrmode="";
			 ArrayList trModeList = new ArrayList();
				for(int i=0;i<trModeData.length;i++)
				{ 
					TravelPolicy bean1 = new TravelPolicy();
					if(!chkTrmode.equals(""+trModeData[i][0]))
					{
						bean1.setTravelMode(""+trModeData[i][0]);
					}
					bean1.setTravelClass(""+trModeData[i][1]); 
					bean1.setTravelModeId(""+trModeData[i][2]);
					//bean1.setTravelClassId(""+trModeData[i][3]); 
					 if(trModeData[i][3].equals("Y"))
					 {
						bean1.setTrModeStatus("checked"); 
						bean1.setHidtrModeStatus("Y"); 
					 }
						//for edit purpose only
					 if(bean.getEditFlag().equals("true"))	{  
						 bean1.setEditFlag("true");
						}else
						{	 bean1.setEditFlag("false"); 
						}
					 
					trModeList.add(bean1);
					chkTrmode=""+trModeData[i][0];
				
				} 
				bean.setTravelModeList(trModeList);  
		}
				 
		// FOR HOTEL TYPE
		
 String hotelTypeSql =" SELECT HOTEL_TYPE_NAME,ROOM_TYPE_NAME,PHT_HOTEL_TYPE_ID, "
					 +" PHT_ROOM_ID, PHT_STATUS FROM HRMS_TMS_POLICY_HOTEL_TYPE "
					 +"	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HOTEL_TYPE_ID=PHT_HOTEL_TYPE_ID) "
					+"	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(ROOM_TYPE_ID=PHT_ROOM_ID) " 
					+"	WHERE PHT_POLICY_ID ="+bean.getPolicyId()+"	ORDER BY PHT_ID ";
		Object[][] hotelData = getSqlModel().getSingleResult(hotelTypeSql); 
		if(hotelData != null && hotelData.length>0)
		{ String hotelTypeChk="";
			 ArrayList hotelList = new ArrayList();
				for(int j=0;j<hotelData.length;j++)
				{   	TravelPolicy bean1 = new TravelPolicy();
						 if(!(hotelTypeChk.equals(""+hotelData[j][0])))
						 {
								bean1.setHotelType(""+hotelData[j][0]);
						 }
						bean1.setRoomType(""+hotelData[j][1]); 
						bean1.setHotelTypeId(""+hotelData[j][2]);  
						bean1.setRoomTypeId(""+hotelData[j][3]);  
						if(hotelData[j][4].equals("Y"))
						 {
							bean1.setHotelStatus("checked"); 
							bean1.setHidHotelStatus("Y");
						 }
						// for edit purpose only
						 if(bean.getEditFlag().equals("true"))	{  
							 bean1.setEditFlag("true");
							}else
							{	 bean1.setEditFlag("false"); 
							}
						 
						hotelList.add(bean1); 
						hotelTypeChk=""+hotelData[j][0]; 
					
			   }
				bean.setHotelTypeList(hotelList); 
				
		}
	 
	}
	public void callEdit(TravelPolicy bean,HttpServletRequest request)
	{
 String policySql=" SELECT POLICY_ID, POLICY_NAME, POLICY_TRAVEL_GRADE,TRAVEL_GRADE_NAME, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), POLICY_SETTELMENT_DAYS, POLICY_ABBR, "
			      +" TO_CHAR(POLICY_CREATE_DATE,'DD-MM-YYYY'), NVL(POLICY_DESC,' '), POLICY_STATUS, NVL(POLICY_GUIDLINES,' ') " 
			      +"  ,t1.EXP_CATEGORY_NAME, POLICY_EXPCAT_TRAVEL, h1.EXP_CATEGORY_NAME, POLICY_EXPCAT_HOTEL ,NVL(POLICY_TOTAL_EXPENSE,0) FROM HRMS_TMS_TRAVEL_POLICY "
			      +" LEFT JOIN HRMS_TMS_TRAVEL_GRADE ON (TRAVEL_GRADE_ID= HRMS_TMS_TRAVEL_POLICY.POLICY_TRAVEL_GRADE) "
			      +" LEFT JOIN HRMS_TMS_EXP_CATEGORY t1 ON(t1.EXP_CATEGORY_ID =POLICY_EXPCAT_TRAVEL) "
                  +" LEFT JOIN HRMS_TMS_EXP_CATEGORY h1 ON(h1.EXP_CATEGORY_ID=POLICY_EXPCAT_HOTEL) "
			      +" WHERE POLICY_ID ="+bean.getPolicyId();
		Object[][] polData= getSqlModel().getSingleResult(policySql);
		bean.setPolicyId(""+polData[0][0]);
		bean.setPolicyName(""+polData[0][1]);
		bean.setTravelGradeId(""+polData[0][2]);
		bean.setTravelGrade(""+polData[0][3]);
		bean.setEffectDate(""+polData[0][4]);
		bean.setSettleDays(""+polData[0][5]);
		bean.setPolicyAbbr(""+polData[0][6]);
		bean.setPolicySysDate(""+polData[0][7]);
		bean.setDesc(""+polData[0][8]);
		bean.setStatus(""+polData[0][9]);
		bean.setGuidLines(""+polData[0][10]); 
		bean.setExpCateTravel(""+polData[0][11]);
		bean.setExpCateTravelId(""+polData[0][12]);
		bean.setExpCateHotel(""+polData[0][13]);
		bean.setExpCateHotelId(""+polData[0][14]);
		bean.setTotExpAmt(""+polData[0][15]);
		// FOR GARDE
	String gradeSql="  SELECT CADRE_NAME, PG_EMP_GRADE, PG_STATUS FROM HRMS_TMS_POLICY_GRADE "
					+" LEFT JOIN HRMS_CADRE ON (CADRE_ID=PG_EMP_GRADE)"
					+" WHERE PG_TRAVEL_GRADE ="+bean.getTravelGradeId()+" AND PG_POLICY_ID ="+bean.getPolicyId()
					+" ORDER BY PG_ID";
	Object[][] gradeData= getSqlModel().getSingleResult(gradeSql);
	if(gradeData != null && gradeData.length>0)
	{
	 ArrayList list = new ArrayList();
		for(int i=0;i<gradeData.length;i++)
		{
			TravelPolicy bean1 = new TravelPolicy();
			bean1.setEmpGrade(""+gradeData[i][0]);
			bean1.setEmpGradeId(""+gradeData[i][1]); 
			 if(gradeData[i][2].equals("Y"))
			 { 
				bean1.setGradeStatus("checked"); 
				bean1.setHidGradeStatus("Y");
			 }
			list.add(bean1);
		}
		bean.setGradeList(list);   
	}
		
	// for Expense Category
	//Modified by manish sakpal
	//add two column names  PE_EXP_CAT_VALUE_WITH_BILL and PE_EXP_CAT_VALUE_WITHOUT_BILL
		String expSql =" SELECT  EXP_CATEGORY_NAME,  DECODE(PE_EXP_CAT_UNIT,'D','Per Day','T','Per Travel','K','Per Kilometer') , "
					+"	PE_EXP_CAT_VALUE_WITH_BILL,PE_EXP_CAT_VALUE_WITHOUT_BILL,PE_EXP_CAT_UNIT,PE_EXP_CAT_ID FROM HRMS_TMS_POLICY_EXPENSE "
					+"	LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(EXP_CATEGORY_ID=PE_EXP_CAT_ID) "
					+"	WHERE PE_POLICY_ID ="+bean.getPolicyId()+" ORDER BY PE_ID "; 
		Object[][] expData = getSqlModel().getSingleResult(expSql);
		if(expData != null && expData.length>0)
		{
		ArrayList expnseList = new ArrayList();
			for(int i=0;i<expData.length;i++)
			{   
				TravelPolicy bean1 = new TravelPolicy();
				bean1.setExCategory(""+expData[i][0]);
				bean1.setExpCatUnit(""+expData[i][1]); 
				//bean1.setExpValue(""+expData[i][2]);  
				
				
				bean1.setExpValuewithBill(""+expData[i][2]);
				bean1.setExpValuewithoutBill(""+expData[i][3]);
				
				
				bean1.setExpCatUnitCode(""+expData[i][4]);  
				bean1.setExCategoryId(""+expData[i][5]); 
				expnseList.add(bean1);
			} 
			bean.setExpList(expnseList); 
		}
			
		// FOR TRAVEL MODE
			
		String trModeSql ="  SELECT  JOURNEY_NAME,JOURNEY_CLASS_NAME, PTM_TRAVEL_MODE_ID, "
						+"  PTM_STATUS FROM HRMS_TMS_POLICY_TRAVEL_MODE "
						+"	LEFT JOIN HRMS_TMS_JOURNEY ON(JOURNEY_ID = PTM_TRAVEL_MODE_ID) "
					//	+"	LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON( JOURNEY_CLASS_ID = PTM_TRAVEL_CLASS_ID) " 
						+"	WHERE PTM_POLICY_ID ="+bean.getPolicyId()+" ORDER BY PTM_ID ";
		Object[][] trModeData = getSqlModel().getSingleResult(trModeSql); 
		if(trModeData != null && trModeData.length>0)
		{
		  String chkTrmode="";
			 ArrayList trModeList = new ArrayList();
				for(int i=0;i<trModeData.length;i++)
				{ 
					TravelPolicy bean1 = new TravelPolicy();
					if(!chkTrmode.equals(""+trModeData[i][0])){
					bean1.setTravelMode(""+trModeData[i][0]);
					} 
					bean1.setTravelClass(""+trModeData[i][1]); 
					bean1.setTravelModeId(""+trModeData[i][2]);
					//bean1.setTravelClassId(""+trModeData[i][3]); 
					 if(trModeData[i][3].equals("Y"))
					 {
						bean1.setTrModeStatus("checked"); 
						bean1.setHidtrModeStatus("Y"); 
					 }
					trModeList.add(bean1);
					chkTrmode=""+trModeData[i][0];
				} 
				bean.setTravelModeList(trModeList);  
		}
				 
		// FOR HOTEL TYPE
		
 String hotelTypeSql =" SELECT HOTEL_TYPE_NAME,ROOM_TYPE_NAME,PHT_HOTEL_TYPE_ID, "
					 +" PHT_ROOM_ID, PHT_STATUS FROM HRMS_TMS_POLICY_HOTEL_TYPE "
					 +"	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HOTEL_TYPE_ID=PHT_HOTEL_TYPE_ID) "
					+"	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(ROOM_TYPE_ID=PHT_ROOM_ID) " 
					+"	WHERE PHT_POLICY_ID ="+bean.getPolicyId()+"	ORDER BY PHT_ID ";
		Object[][] hotelData = getSqlModel().getSingleResult(hotelTypeSql); 
		if(hotelData != null && hotelData.length>0)
		{ 
			 ArrayList hotelList = new ArrayList();
				for(int j=0;j<hotelData.length;j++)
				{   	TravelPolicy bean1 = new TravelPolicy();
						bean1.setHotelType(""+hotelData[j][0]);
						bean1.setRoomType(""+hotelData[j][1]); 
						bean1.setHotelTypeId(""+hotelData[j][2]);  
						bean1.setRoomTypeId(""+hotelData[j][3]);  
						if(hotelData[j][4].equals("Y"))
						 {
							bean1.setHotelStatus("checked"); 
							bean1.setHidHotelStatus("Y");
						 }
						hotelList.add(bean1); 
			   }
				bean.setHotelTypeList(hotelList); 
				
		}
	
	} 
	public void callSearchDtl(TravelPolicy bean)
	{
	String sql ="SELECT  POLICY_NAME, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),POLICY_ABBR,DECODE(POLICY_STATUS,'A','Active','D','Deactive') , "
              +"  POLICY_ID FROM HRMS_TMS_TRAVEL_POLICY"; 
	Object[][] polData = getSqlModel().getSingleResult(sql); 
		if(polData != null && polData.length>0)
		{ 
			 ArrayList polList = new ArrayList();
				for(int i=0;i<polData.length;i++)
				{   	TravelPolicy bean1 = new TravelPolicy();
						bean1.setItPolicyName(""+polData[i][0]);
						bean1.setItEffectDate(""+polData[i][1]);
						bean1.setItPolicyAbbr(""+polData[i][2]);
						bean1.setItStatus(""+polData[i][3]);
						bean1.setItPolicyId(""+polData[i][4]); 
						polList.add(bean1); 
			   }
				bean.setPolicyList(polList);  
		} 
	}
	
	public boolean callDelete(TravelPolicy bean)
	{ 
		bean.setLoadFlag("true");
		bean.setAddNewFlag("false"); 
		bean.setSaveFlag("false"); 
		boolean flag=false; 
		
	   String delPolicy="DELETE FROM HRMS_TMS_TRAVEL_POLICY WHERE POLICY_ID="+bean.getPolicyId();
	   flag= getSqlModel().singleExecute(delPolicy);  
	 
	    if(flag)
	    {
		 String delHotel="DELETE FROM HRMS_TMS_POLICY_HOTEL_TYPE WHERE PHT_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delHotel);
	     }
		 if(flag)
		 {
		 String delTrMode="DELETE FROM HRMS_TMS_POLICY_TRAVEL_MODE WHERE PTM_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delTrMode); 
		 }
		 if(flag)
		 {		 
		 String delExp="DELETE FROM   HRMS_TMS_POLICY_EXPENSE WHERE PE_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delExp); 
		 }
		 if(flag)
		 {
		 String delGrade="DELETE FROM HRMS_TMS_POLICY_GRADE WHERE  PG_POLICY_ID ="+bean.getPolicyId();
		 flag= getSqlModel().singleExecute(delGrade); 
		 }
	
		 return flag;
		
	}
	
}
