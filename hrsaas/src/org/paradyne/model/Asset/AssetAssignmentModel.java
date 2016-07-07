
package org.paradyne.model.Asset;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Asset.AssetAssignment;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Mangesh Jadhav
 * 12-08-2008
 * AssetAssignmentModel class to write business logic to show the asset 
 * application for the assignment & asset requests from other warehouse
 */
public class AssetAssignmentModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	Object[][] to_mailIds =new Object[1][1];	
	Object[][] from_mailIds =new Object[1][1];
	/* 
	 * method name : showRecords 
	 * purpose     : show the approved asset applications which comes under the 
	 * 				Employee who is logged in.
	 * return type : void
	 * parameter   : AssetAssignment instance
	 */
	public void showRecords(AssetAssignment bean, HttpServletRequest request){
		/*
		 * get the employee codes which are coming under warehouse for 
		 * which the employee who is logged in is responsible
		 * 
		 */
		String empIdQuery="SELECT EMP_ID FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_WAREHOUSE_BRANCH ON (HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
			+" INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
			+" WHERE WAREHOUSE_RESPONSIBLE_PERSON="+bean.getUserEmpId();

		
		try{
			String query = "SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
				+ " ASSET_APPL_CODE, ASSET_EMP_ID FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " WHERE ASSET_STATUS ='A' AND ASSET_EMP_ID IN ("+empIdQuery+")";

			Object[][] data = getSqlModel().getSingleResult(query);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length,20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			
			
			 if(pageIndex[4].equals("1"))
				   bean.setMyPage("1");
			  			
				
							ArrayList<Object> list = new ArrayList<Object>();
							if (data != null && data.length > 0) {
				
							//	for (int i = 0; i < obj.length; i++) {
								 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				
									 AssetAssignment bean1 = new AssetAssignment();
									     
									 bean1.setEmpToken(String.valueOf(data[i][0]));
										bean1.setEmpName(String.valueOf(data[i][1]));
										bean1.setAssetCode(String.valueOf(data[i][2]));
										bean1.setAvailability(checkInventoryAvail(String.valueOf(data[i][2]), bean,String.valueOf(data[i][3])));
										list.add(bean1);
								}
								
							
							 bean.setTotalRecords(""+data.length);
							 
					}
					else{
					
						bean.setShowFlag("false");
						
					}
					//bean.setEmpList(list);
							System.out.println("The length of data ====="+data.length);
							System.out.println("The list size : -----: "+list.size());
					bean.setTotalRecords(String.valueOf(list.size()));
					bean.setTableList(String.valueOf(list.size()));
				    if(list.size()>0)
				    	bean.setShowFlag("true");
				    else
				    	bean.setShowFlag("false");
			
			
			bean.setRecordList(list);
		}catch (Exception e) {
			e.printStackTrace();
			bean.setNoData("true");
			bean.setListLength("0");
			logger.info("exception in showRecords()=="+e);
		}

	}
	/* 
	 * method name : checkInventoryAvail 
	 * purpose     : Checks whether the applied Assets are available or not in the warehouse
	 * return type : String
	 * parameter   : String assetCode, AssetAssignment instance
	 */
	public String checkInventoryAvail(String assetCode,AssetAssignment bean,String empCode){
		String warehouseQuery ="SELECT WAREHOUSE_CODE FROM HRMS_EMP_OFFC"
			+" INNER JOIN HRMS_WAREHOUSE_BRANCH ON(HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
			+" INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE =HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE AND WAREHOUSE_RESPONSIBLE_PERSON="+bean.getUserEmpId()+")"
			+" WHERE EMP_ID=" +empCode;
		
		Object[][]warehouseCode=getSqlModel().getSingleResult(warehouseQuery);
		String availability="Not Available";
		String query ="SELECT HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE,HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE," 
			+" (ASSET_APPL_QTY-ASSET_ASSIGN_QTY),ASSET_INVENTORY_TYPE  FROM HRMS_ASSET_APPL_DETAILS "
			+" LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE) "
			+" WHERE ASSET_APPL_CODE ="+assetCode +"AND (ASSET_APPL_QTY-ASSET_ASSIGN_QTY)>0";
		Object [][]assetDetails = getSqlModel().getSingleResult(query);
		
		for (int j= 0; j < warehouseCode.length; j++) {
			String checkQuery="";
			String appendQuery="";
			Object checkObject [][]=null;
			boolean partial=false;
			try{

				for (int i = 0; i < assetDetails.length; i++) {
					logger.info("subtype code["+i+"]==="+checkNull(String.valueOf(assetDetails[i][1])));

					appendQuery=" WHERE ASSET_CATEGORY_CODE= "+checkNull(String.valueOf(assetDetails[i][0]))+" AND ASSET_SUBTYPE_CODE= "+String.valueOf(assetDetails[i][1])+" AND ASSET_WAREHOUSE_CODE ="+String.valueOf(warehouseCode[j][0])+" AND ASSET_ASSISGN_FLAG ='U'";
					if(checkNull(String.valueOf(assetDetails[i][3])).equals("I")){
						checkQuery ="SELECT COUNT(HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE) FROM  HRMS_ASSET_MASTER_DTL "
							+" INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "+appendQuery ;
					}  // end of if
					else {
						checkQuery ="SELECT NVL(SUM(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE),0) FROM HRMS_ASSET_MASTER_DTL "
							+" LEFT JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "+appendQuery ;
					}  // end of else
					checkObject =getSqlModel().getSingleResult(checkQuery);
					logger.info("out side if availability["+i+"]=="+availability);

					logger.info("assetDetails["+i+"][2]=="+assetDetails[i][2]);
					logger.info("checkObject.length======"+checkObject.length);
					if(checkObject !=null && checkObject.length!=0){
						logger.info("checkObject[0][0])["+i+"]=="+checkObject[0][0]);
						/*
						 * if available quantity i.e. checkObject[0][0] is less than required quantity not equal to '0'
						 * then availability will be 'Partially Available'
						 */
						if(Float.parseFloat(String.valueOf(checkObject[0][0]))>= Float.parseFloat(String.valueOf(assetDetails[i][2])))
						{
							logger.info("partial["+i+"]=="+partial);
							if(i !=0 && availability.equals("Not Available"))
								partial = true;
							if(partial)
								availability ="Partially Available";
							/*
							 * if available quantity i.e. checkObject[0][0] is equal to required quantity 
							 * then availability will be 'Available'
							 */
							else
								availability="Available";
						}  // end of if
						else if( (String.valueOf(checkObject[0][0])).equals("0")){
							logger.info("availability["+i+"]=="+availability);
							logger.info("partial["+i+"]=="+partial);
							/*
							 * if available quantity i.e. checkObject[0][0] is equal to zero
							 * then availability will be 'Not Available'
							 */
							if(partial || availability.equals("Available")){
								availability ="Partially Available";
								partial =true;
							}  // end of if
							else
								availability ="Not Available";
							
						}  // end of else if
						else {
							logger.info("partial["+i+"]=="+partial);
							availability ="Partially Available";
							partial =true;
						}  // end of else
					}  // end of if
					else {
						logger.info("availability in last else"+availability);
						if(availability.equals("Available"))
							availability ="Partially Available";
						else
							availability ="Not Available";

					}  // end of else
				}  // end of inner for loop
			}catch (Exception e) {
				e.printStackTrace();
				logger.info("exception in checkInventoryAvail()=="+e);
			}
		}  // end of for loop
		logger.info("last availability==="+availability);
		return availability;
	}

	/* 
	 * method name : getWarehouseCodes 
	 * purpose     : get the warehouse codes
	 * return type : String
	 * parameter   : AssetAssignment instance
	 */

	public String getWarehouseCodes(AssetAssignment bean){
		String wareHouseReturn = "";
		String warehouseQuery="SELECT WAREHOUSE_CODE FROM HRMS_WAREHOUSE_MASTER WHERE WAREHOUSE_RESPONSIBLE_PERSON="+bean.getUserEmpId();

		Object [][]warehouseCode=getSqlModel().getSingleResult(warehouseQuery);
		/*append the warehouse codes in a single string.
		 * 
		 */
		try{
			for (int i = 0; i < warehouseCode.length; i++) 
				wareHouseReturn += ""+warehouseCode[i][0]+",";
			wareHouseReturn = wareHouseReturn.substring(0,wareHouseReturn.length()-1);
		}catch (Exception e) {
			wareHouseReturn ="0";
			logger.info("exception in getWarehouseCodes()=="+e);
		}
		return wareHouseReturn;
	}
	/* 
	 * method name : requestFromOther 
	 * purpose     : get the list of assets requests from other warehouse
	 * return type : void
	 * parameter   : AssetAssignment instance
	 */
	public void requestFromOther(AssetAssignment bean, HttpServletRequest request){
		String  query="SELECT ASSET_REQ_MASTERCODE,ASSET_REQ_INVENTORY,ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME,(ASSET_QUANTITY_REQUIRED-ASSET_QUANTITY_ASSIGNED), " 
			+" ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_SOURCE_WAREHOUSE,WAREHOUSE_NAME,ASSET_REQUEST_CODE,ASSET_DESTI_WAREHOUSE FROM HRMS_ASSET_WAREHOUSE_REQ"
			+" INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_WAREHOUSE_REQ.ASSET_REQ_MASTERCODE)"
			+" INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE)"
			+" INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE)"
			+" INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_WAREHOUSE_REQ.ASSET_SOURCE_WAREHOUSE) "
			+" WHERE ASSET_DESTI_WAREHOUSE IN("+getWarehouseCodes(bean)+") AND (ASSET_QUANTITY_REQUIRED-ASSET_QUANTITY_ASSIGNED > 0) "
			+" AND ASSET_IS_CANCELED ='N'";
		Object [][]data= getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length,20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		
		
		 if(pageIndex[4].equals("1"))
			   bean.setMyPage("1");
		  			
			
						ArrayList<Object> list = new ArrayList<Object>();
						if (data != null && data.length > 0) {
			
						//	for (int i = 0; i < obj.length; i++) {
							 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
			
								 AssetAssignment bean1 = new AssetAssignment();
								     
								 bean1.setCategory(String.valueOf(data[i][2]));
									bean1.setSubType(String.valueOf(data[i][3]));
									bean1.setQuantityRequired(String.valueOf(data[i][4]));
									bean1.setCategoryCode(String.valueOf(data[i][5]));
									bean1.setSubTypeCode(String.valueOf(data[i][6]));
									bean1.setSourceWarehouse(String.valueOf(data[i][7]));
									bean1.setSourceWarehouseName(String.valueOf(data[i][8]));
									bean1.setReqCode(String.valueOf(data[i][9]));
									bean1.setDestWarehouse(String.valueOf(data[i][10]));
									list.add(bean1);
							}
							
						
						 bean.setTotalRecords(""+data.length);
						 
				}
				else{
				
					bean.setShowFlag("false");
					
				}
				//bean.setEmpList(list);
						System.out.println("The length of data ====="+data.length);
						System.out.println("The list size : -----: "+list.size());
				bean.setTotalRecords(String.valueOf(list.size()));
				bean.setTableList(String.valueOf(list.size()));
			    if(list.size()>0)
			    	bean.setShowFlag("true");
			    else
			    	bean.setShowFlag("false");
		
		
		bean.setRecordList(list);
		
		
	}

	/* 
	 * method name : updateWarehouseCode 
	 * purpose     : updates the warehouse codes after transferring the assets
	 * return type : void
	 * parameter   : String []inventoryCode,String []masterCode,String []quantityAssigned,String []sourceWarehouse,String []reqCode,String []destWarehouse
	 */

	public boolean updateWarehouseCode( String []inventoryCode,String []masterCode,String []quantityAssigned,String []sourceWarehouse,String []reqCode,String []destWarehouse){
		String addInvForSourcequery="INSERT INTO HRMS_ASSET_MASTER_DTL(ASSET_MASTER_CODE,ASSET_INVENTORY_CODE,ASSET_WAREHOUSE_CODE,ASSET_ASSISGN_FLAG,ASSET_QUANTITY,ASSET_AVAILABLE,ASSET_ITEM_CODE)"
			+" VALUES(?,?,?,'U',?,?,(SELECT NVL(MAX(ASSET_ITEM_CODE),0)+1 FROM HRMS_ASSET_MASTER_DTL))";
		String updateRequestQuery="UPDATE HRMS_ASSET_WAREHOUSE_REQ SET ASSET_QUANTITY_ASSIGNED=ASSET_QUANTITY_ASSIGNED+? WHERE ASSET_REQUEST_CODE=?";
		boolean result=false;
		
		for (int i = 0; i < inventoryCode.length; i++) {

			if(!String.valueOf(quantityAssigned[i]).equals("")){
				updateInventoryForReq(masterCode[i], inventoryCode[i], quantityAssigned[i],destWarehouse [i]);						// UPDATE THE QUANTITY & STATUS IN THE DESTINATION WAREHOUSE

				/*
				 * check previous present inventory 
				 * 
				 */
				String subTypeQuery = "SELECT HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE,ASSET_INVENTORY_TYPE FROM HRMS_ASSET_MASTER  "
					+" INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE)"
					+" WHERE ASSET_MASTER_CODE="+masterCode[i];
				Object [][] subType=getSqlModel().getSingleResult(subTypeQuery);
				if(String.valueOf(subType[0][1]).equals("S")){
					
					String subTypeCheckQuery="SELECT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE, ASSET_INVENTORY_CODE FROM HRMS_ASSET_MASTER_DTL"
						+" INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
						+" WHERE ASSET_SUBTYPE_CODE="+subType[0][0]+" AND ASSET_WAREHOUSE_CODE="+sourceWarehouse[i];
					Object [][] subTypeCheck= getSqlModel().getSingleResult(subTypeCheckQuery);

					if(subTypeCheck == null || subTypeCheck.length==0){
						Object [][]addInvForSource = new Object [1][5];
						addInvForSource [0][0] = masterCode[i];
						addInvForSource [0][1] = inventoryCode[i];
						addInvForSource [0][2] = sourceWarehouse [i];
						addInvForSource [0][3] = quantityAssigned [i];
						addInvForSource [0][4] = quantityAssigned [i];

						result=getSqlModel().singleExecute(addInvForSourcequery, addInvForSource);
					}
					else{
						String updatePreviousInv="UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_QUANTITY = ASSET_QUANTITY + "+quantityAssigned[i]+", ASSET_AVAILABLE= ASSET_AVAILABLE + "+quantityAssigned[i]
						                        +" ,ASSET_ASSISGN_FLAG ='U' WHERE ASSET_MASTER_CODE="+subTypeCheck[0][0]+" AND ASSET_INVENTORY_CODE ='"+subTypeCheck[0][1]+"' AND ASSET_WAREHOUSE_CODE="+sourceWarehouse[i];
						result =getSqlModel().singleExecute(updatePreviousInv);
					}		// END OF ELSE
				}			// END OF IF
				else {


					Object [][]addInvForSource = new Object [1][5];
					addInvForSource [0][0] = masterCode[i];
					addInvForSource [0][1] = inventoryCode[i];
					addInvForSource [0][2] = sourceWarehouse [i];
					addInvForSource [0][3] = quantityAssigned [i];
					addInvForSource [0][4] = quantityAssigned [i];
					
					result=getSqlModel().singleExecute(addInvForSourcequery, addInvForSource);							// ADD INVENTORY IN THE SOURCE WAREHOUSE
					
				}  // END OF ELSE
				/*
				 * query to update the quantity in the master
				 * 
				 */
				String updateMaster = "UPDATE HRMS_ASSET_MASTER SET ASSET_QUANTITY = ASSET_QUANTITY + "+quantityAssigned[i]+" , ASSET_AVAILABLE= ASSET_AVAILABLE +"+quantityAssigned[i]
				                       +" WHERE ASSET_MASTER_CODE = "+masterCode[i];                                                                                                                                              	
				getSqlModel().singleExecute(updateMaster);
				
				Object [][]updateRequest =new Object [1][2];
				updateRequest [0][0] = quantityAssigned[i];
				updateRequest [0][1] = reqCode [i];
				logger.info("updateRequest [0][0]="+updateRequest [0][0]);
				logger.info("updateRequest [0][1]="+updateRequest [0][1]);
				result=getSqlModel().singleExecute(updateRequestQuery, updateRequest);

				if(result){
					try {
						to_mailIds[0][0]= getAssignerCode(sourceWarehouse[i]);
						from_mailIds[0][0]=getAssignerCode(destWarehouse[i]);

						MailUtility mail=new MailUtility();
						mail.initiate(context, session);
						logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
						logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
						mail.sendMail(to_mailIds, from_mailIds,"AssetTransfer", "", "TR");

						mail.terminate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}  // end of if
		}  // end of for loop

		return result;
	}

	/* 
	 * method name : updateInventoryForReq 
	 * purpose     : updates the quantity after transferring the assets
	 * return type : void
	 * parameter   : String masterCode,String inventoryCode,String quantityAssigned,String warehouse
	 */
	public void updateInventoryForReq(String masterCode,String inventoryCode,String quantityAssigned,String warehouse){


		/*
		 * 
		 *  UPDATE QUANTITY AVAILABLE IN MASTER_DTL
		 * 
		 */
		String query="UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_QUANTITY = ASSET_QUANTITY- ?,ASSET_AVAILABLE=ASSET_AVAILABLE- ? WHERE ASSET_MASTER_CODE=? AND ASSET_INVENTORY_CODE=? AND ASSET_WAREHOUSE_CODE=?";
		Object[][] invCodeDtlUpdate = new Object[1][5];
		invCodeDtlUpdate[0][0] = quantityAssigned;
		invCodeDtlUpdate[0][1] = quantityAssigned;
		invCodeDtlUpdate[0][2] = masterCode;
		invCodeDtlUpdate[0][3] = inventoryCode;
		invCodeDtlUpdate[0][4] = warehouse;
		getSqlModel().singleExecute(query,invCodeDtlUpdate);

		/*
		 * 
		 *  UPDATE QUANTITY AVAILABLE IN HRMS_ASSET_MASTER
		 * 
		 */
		String updateMaster = "UPDATE HRMS_ASSET_MASTER SET ASSET_QUANTITY = ASSET_QUANTITY - "+quantityAssigned+" , ASSET_AVAILABLE= ASSET_AVAILABLE -"+quantityAssigned
							 +" WHERE ASSET_MASTER_CODE = "+masterCode;
		getSqlModel().singleExecute(updateMaster);
		/*
		 * CHECK THE AVAILABLE QUANTITY OF INVENTORY OF 
		 * TYPE SAME FOR ALL & ACCORDINGLY CHANGE THE STATUS TO ASSIGN OR UNASSIGNED
		 * 
		 */	
		String invAvailableQuery="SELECT ASSET_INVENTORY_CODE,ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_AVAILABLE=0 AND ASSET_ASSISGN_FLAG NOT IN ('L','D') AND ASSET_MASTER_CODE ="+masterCode+" AND ASSET_WAREHOUSE_CODE="+warehouse;
		Object [][]invDtlAvailability=getSqlModel().getSingleResult(invAvailableQuery);

		for (int j = 0; j < invDtlAvailability.length; j++) {
			String invStatusUpdate="DELETE HRMS_ASSET_MASTER_DTL WHERE ASSET_INVENTORY_CODE='"+String.valueOf(invDtlAvailability[j][0])+"' AND ASSET_MASTER_CODE="+String.valueOf(invDtlAvailability[j][1])+" AND ASSET_WAREHOUSE_CODE="+warehouse;
			getSqlModel().singleExecute(invStatusUpdate);
		}  // end of for loop



	}
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}   // end of if
		else {
			return result;
		}
	}
	/* 
	 * method name : getAssignerCode 
	 * purpose     : get the Asset assigner code for particular warehouse
	 * return type : String
	 * parameter   : String warehouseCode
	 */
	public String getAssignerCode(String warehouseCode){
		String assignerCode = "";

		String query="SELECT WAREHOUSE_RESPONSIBLE_PERSON FROM HRMS_WAREHOUSE_MASTER "
			+" WHERE WAREHOUSE_CODE="+warehouseCode;

		Object [][]assignerObj=getSqlModel().getSingleResult(query);

		assignerCode = String.valueOf(assignerObj[0][0]);
		return assignerCode;
	}

	public boolean cancelRequest(String reqCode){
		boolean result=false;
		String query="UPDATE HRMS_ASSET_WAREHOUSE_REQ SET ASSET_IS_CANCELED='Y' WHERE ASSET_REQUEST_CODE="+reqCode;
		result = getSqlModel().singleExecute(query);
		if(result){
			String reqWarehouseQuery="SELECT ASSET_SOURCE_WAREHOUSE,ASSET_DESTI_WAREHOUSE FROM HRMS_ASSET_WAREHOUSE_REQ"
									+" WHERE ASSET_REQUEST_CODE ="+reqCode;
			Object reqWarehouse [][] =getSqlModel().getSingleResult(reqWarehouseQuery);
			
			try {
				to_mailIds[0][0]= getAssignerCode(String.valueOf(reqWarehouse[0][0]));
				from_mailIds[0][0]=getAssignerCode(String.valueOf(reqWarehouse[0][1]));

				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds,"AssetTransfer", "", "CL");

				mail.terminate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	public void showSelectedRecords(AssetAssignment bean){
		/*
		 * get the employee codes which are coming under warehouse for 
		 * which the employee who is logged in is responsible
		 * 
		 */
		String empIdQuery="SELECT EMP_ID FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_WAREHOUSE_BRANCH ON (HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
			+" INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
			+" WHERE WAREHOUSE_RESPONSIBLE_PERSON="+bean.getUserEmpId();

		Object [][]empObject=getSqlModel().getSingleResult(empIdQuery);

		String empCode = "";
		
		try{
			logger.info("empObject.length====="+empObject.length);
			logger.info("empObject====="+bean.getSelectedEmpCode());
			if(empObject!=null && empObject.length!=0)
			{	
				for (int i = 0; i < empObject.length; i++) {
					
					logger.info("empObject===== ii"+empObject[i][0]);
					
					if(String.valueOf(empObject[i][0]).equals(bean.getSelectedEmpCode())){
						empCode=bean.getSelectedEmpCode();
						break;
					}
					else
						empCode="0";
				}						
			}// end of if
			else
				empCode="0";
			
		}catch (Exception e) {
			empCode="0";
			e.printStackTrace();
			logger.info("exception in showRecords()=="+e);
		}
		logger.info("empcode string========="+empCode);
		try{
			String query = "SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
				+ " ASSET_APPL_CODE, ASSET_EMP_ID FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " WHERE ASSET_STATUS ='A' AND ASSET_EMP_ID IN ("+empCode+")";

			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> appList = new ArrayList<Object>();
			/*
			 * set the retrieved data in the list
			 * 
			 */
			if (data != null && data.length != 0) {
				for (int i = 0; i < data.length; i++) {
					AssetAssignment bean1 = new AssetAssignment();
					bean1.setEmpToken(String.valueOf(data[i][0]));
					bean1.setEmpName(String.valueOf(data[i][1]));
					bean1.setAssetCode(String.valueOf(data[i][2]));
					bean1.setAvailability(checkInventoryAvail(String.valueOf(data[i][2]), bean,String.valueOf(data[i][3])));
					appList.add(bean1);
				} // end of for loop
				bean.setNoData("false");
			}  // end of if
			else {
				bean.setNoData("true");

			}// end of else
			bean.setListLength(String.valueOf(appList.size()));
			bean.setRecordList(appList);
		}catch (Exception e) {
			e.printStackTrace();
			bean.setNoData("true");
			bean.setListLength("0");
			logger.info("exception in showRecords()=="+e);
		}

	}
	
	
}
