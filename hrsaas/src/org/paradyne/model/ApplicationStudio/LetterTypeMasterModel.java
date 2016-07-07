package org.paradyne.model.ApplicationStudio;


import org.paradyne.lib.ModelBase;

public class LetterTypeMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.model.ApplicationStudio.LetterTypeMasterModel.class);

	
	public boolean save(String letterTypeId, String letterTypeName) {
		boolean flag = false;
		try {
			// TODO Auto-generated method stub
			
			String insertQuery = "  INSERT INTO HRMS_LETTERTYPE(LETTERTYPE_ID,LETTERTYPE) "
					+ " VALUES((SELECT NVL(MAX(LETTERTYPE_ID),0)+1 FROM  HRMS_LETTERTYPE ),'"
					+ letterTypeName + "')";
			flag = getSqlModel().singleExecute(insertQuery);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in save method in model------------"+e);
		}
		return flag;
	}

	public boolean update(String letterTypeId, String letterTypeName) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		try {
			String updateQuery = " UPDATE HRMS_LETTERTYPE SET LETTERTYPE='"
					+ letterTypeName + "' WHERE LETTERTYPE_ID=" + letterTypeId;
			flag = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in update method in model------------"+e);
		}
		return flag;
	}

	public boolean delete(String letterTypeId) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			 
			String delQuery=" DELETE FROM HRMS_LETTERTYPE  WHERE LETTERTYPE_ID ="+letterTypeId;
			flag=getSqlModel().singleExecute(delQuery);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in delete method in model------------"+e);
		}
		return flag;
	}

}
