package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Asset.AssetAssignment;
import org.paradyne.bean.Asset.AssetDirectAssignment;
import org.paradyne.bean.admin.master.TaskMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.MealVoucherModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ApplCodeTemplateModel;


public class AssetDirectAssignmentModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AssetDirectAssignmentModel.class);

	public boolean save(AssetDirectAssignment assetAssign) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			
			

			Object[][] add = new Object[1][9];
			add[0][0] = assetAssign.getAssetcategorycode();
			add[0][1] = assetAssign.getAssetsubtypecode();
			add[0][2] = assetAssign.getInventorycode();
			add[0][3] = assetAssign.getEmployeecode();
			add[0][4] = assetAssign.getVendorid();
			add[0][5] = assetAssign.getUsertypeRadioOptionValue();
			add[0][6] = assetAssign.getAssignDate();
			add[0][7] = assetAssign.getAssignquantity();
			
			add[0][8] = assetAssign.getAssetautoid();

			String insertQuery = " INSERT INTO HRMS_ASSET_APP_ASSIGNEMENT(ASSET_CATEGORY_CODE, ASSET_SUBTYPE_CODE, ASSET_INVENTORY_CODE, ASSET_EMP_ID, ASSET_VENDOR_ID, ASSET_OWNED_BY, ASSET_ASSIGNMENT_DATE, ASSET_ASSIGNED_QUANTITY,ASSET_RETURN_FLAG ,ASSET_ITEM_CODE) "
					+ "VALUES(?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,'N',?)";

			result = getSqlModel().singleExecute(insertQuery, add);

			if (result) {
				if (assetAssign.getEmployeecode().equals("")) {
					assetAssign.setEmployeecode("0");
				}

				if (assetAssign.getVendorid().equals("")) {
					assetAssign.setVendorid("0");
				}

				String updateQuery = " UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_AVAILABLE=ASSET_AVAILABLE-"
						+ assetAssign.getAssignquantity().trim()
						+ ",ASSET_ASSISGN_FLAG='A',ASSET_ASSIGNED_EMP = "
						+ assetAssign.getEmployeecode()
						+ ",ASSET_OWNED_BY = '"
						+ assetAssign.getUsertypeRadioOptionValue()
						+ "',ASSET_ASSIGNED_VENDOR = "
						+ assetAssign.getVendorid()
						+ " WHERE ASSET_ITEM_CODE="+ assetAssign.getAssetautoid().trim() + " ";
				result = getSqlModel().singleExecute(updateQuery);
				
				if(assetAssign.getVendorid().equals("0"))
				{
				
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				
				template
						.setEmailTemplate("ASSET DIRECT ASSIGN - MAIL TO APPLICANT");

				String module = "Asset Assignment";
				String msgType = "A";
				String alertLevel = "1";
				
				
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, assetAssign.getUserEmpId());

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, assetAssign.getEmployeecode());

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, assetAssign.getEmployeecode());

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				
				templateQuery4.setParameter(1, assetAssign.getEmployeecode());
				
				templateQuery4.setParameter(2, assetAssign.getAssetautoid());

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, assetAssign.getUserEmpId());

				

				template.configMailAlert();
				try {
					
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
				}
				else if(assetAssign.getEmployeecode().equals("0"))
				{
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					
					template
							.setEmailTemplate("ASSET DIRECT ASSIGN - VENDOR MAIL TO APPLICANT");

					String module = "Asset Assignment";
					String msgType = "A";
					String alertLevel = "1";
					
					
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, assetAssign.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, assetAssign.getVendorid());

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, assetAssign.getVendorid());
					templateQuery3.setParameter(2, assetAssign.getAssetautoid());

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, assetAssign.getVendorid());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, assetAssign.getUserEmpId());

					

					template.configMailAlert();
					try {
						
						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		} else {
			return result;
		}
	}

	public void showList(AssetDirectAssignment bean) {
		// TODO Auto-generated method stub
		try {
			String query = " SELECT " 
					 + " CASE WHEN ASSET_EMP_ID!=0 THEN EMP_FNAME||''||EMP_LNAME ELSE VENDOR_NAME END "
					 + "  AS EMP_VENDOR ,HRMS_ASSET_CATEGORY.ASSET_CATEGORY_NAME,HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_NAME, " 
					 + " ASSET_INVENTORY_CODE ,TO_CHAR(ASSET_ASSIGNMENT_DATE,'DD-MM-YYYY') , "
					 + "  ASSET_ASSIGNED_QUANTITY "
					 + " FROM HRMS_ASSET_APP_ASSIGNEMENT " 
					 + " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APP_ASSIGNEMENT.ASSET_EMP_ID) " 
					 + " LEFT JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_APP_ASSIGNEMENT.ASSET_VENDOR_ID) " 
					 + " INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_APP_ASSIGNEMENT.ASSET_CATEGORY_CODE) "
					 + " INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE = HRMS_ASSET_APP_ASSIGNEMENT.ASSET_SUBTYPE_CODE) "
					 + " where ASSET_APPL_CODE is null  ";

			if (!bean.getEmployeecode().equals("0")) {
				query += " and ASSET_EMP_ID=" + bean.getEmployeecode();
			}

			if (!bean.getVendorid().equals("0")) {
				query += " and ASSET_VENDOR_ID=" + bean.getVendorid();
				;
			}

			query += " and ASSET_RETURN_FLAG='N' ";

			Object dataObj[][] = getSqlModel().getSingleResult(query);

			if (dataObj != null && dataObj.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = 0; i < dataObj.length; i++) {
					AssetDirectAssignment localassetdirassign = new AssetDirectAssignment();

					localassetdirassign.setIttremployeename(String
							.valueOf(dataObj[i][0]));
					localassetdirassign.setIttrassetcategory(String.valueOf(dataObj[i][1]));
					localassetdirassign.setIttrassetsubtype(String.valueOf(dataObj[i][2]));

					localassetdirassign.setIttrinventory(String
							.valueOf(dataObj[i][3]));
					localassetdirassign.setIttrassigndate(String
							.valueOf(dataObj[i][4]));
					localassetdirassign.setIttrassignquantity(String
							.valueOf(dataObj[i][5]));

					list.add(localassetdirassign);

				}
				bean.setAssetassignmentList(list);

			}
			
			bean.setUsertypeRadioOptionValue(bean.getUsertypeRadioOptionValue());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
