package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.paradyne.bean.ApplicationStudio.MyDownloadsBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class MyDownloadsModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(MyDownloadsModel.class);
	
	public int saveDownloads(MyDownloadsBean bean, String status) {
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][7];								
				System.out.println("bean.getCategoryNameDropdown().trim()  "+bean.getCategoryNameDropdown().trim());
				if(bean.getCategoryName() != "" && bean.getCategoryNameDropdown().trim().equals("Other")){
					addLink[0][0]=bean.getCategoryName();
				}
				else{
					addLink[0][0]=bean.getCategoryNameDropdown().trim();
				}
				if(bean.getSubCategoryName() != "" && bean.getSubCategoryNameDropDown().trim().equals("Other")){
					addLink[0][1]=bean.getSubCategoryName();
				}
				else{
					addLink[0][1]=bean.getSubCategoryNameDropDown();
				}
				addLink[0][2] = String.valueOf(bean.getLinkName());
				if (bean.getUploadDocument().equals("")) {
					addLink[0][3] = bean.getLink();
					addLink[0][4] = "L";
				} 
				else {
					addLink[0][3] = "../pages/upload/"+ bean.getUploadDocument();
					addLink[0][4] = "D";
				}
				if (bean.getCheckActive().equals("true")){
					addLink[0][5] = "Y";
				}else{
					addLink[0][5] = "N";
				}
				addLink[0][6] = checkNull(bean.getDownloadDivCode());
				if (bean.getHiddenCode().equals("")) {
					String query=" INSERT INTO HRMS_DASHLET_DOWNLOAD(AUTO_CODE,DOWNLOAD_CATEGORY,DOWNLOAD_SUBCATEGORY,"+
					             " DOWNLOAD_LINKNAME, DOWNLOAD_LINK, "+
					             " DOWNLOAD_FLAG,DOWNLOAD_STATUS,DOWNLOAD_DIVISION) " +
					             " VALUES((SELECT NVL(MAX(AUTO_CODE),0) + 1 FROM HRMS_DASHLET_DOWNLOAD),?,?,?,?,?,?,?) ";		
					getSqlModel().singleExecute(query, addLink);
					flagValue = 1;
				} else {
					Object[][] updateObj = new Object[1][8];
					
					if(bean.getCategoryName() != "" && bean.getCategoryNameDropdown().trim().equals("Other")){
						updateObj[0][0]=bean.getCategoryName();
					}
					else{
						updateObj[0][0]=bean.getCategoryNameDropdown().trim();
					}
					if(bean.getSubCategoryName() != "" && bean.getSubCategoryNameDropDown().trim().equals("Other")){
						updateObj[0][1]=bean.getSubCategoryName();
					}
					else{
						updateObj[0][1]=bean.getSubCategoryNameDropDown();
					}					
					  /*  updateObj[0][0] = bean.getCategoryName();
					    updateObj[0][1] = bean.getSubCategoryName();*/					  					
					if (String.valueOf(addLink[0][4]).equals("D")) {
						updateObj[0][2] ="../pages/upload/"+ bean.getUploadDocument();
					} else {
						updateObj[0][2] = bean.getLink();
					}
                     System.out.println("bean.getCheckActive()  "+bean.getCheckActive());
					if (bean.getCheckActive().equals("true")) {
						updateObj[0][3] = "Y";
					} else {
						updateObj[0][3] = "N";
					}
					if (bean.getUploadDocument().equals("")) {
						updateObj[0][2] = bean.getLink();
						updateObj[0][4] = "L";
					} else {
						updateObj[0][2] =  "../pages/upload/"+bean.getUploadDocument();
						updateObj[0][4] = "D";
					}					   		
					    updateObj[0][5] = checkNull(bean.getDownloadDivCode());
					    updateObj[0][6] = bean.getLinkName(); 					    
					    updateObj[0][7] = bean.getHiddenCode();					
					String query1= " UPDATE HRMS_DASHLET_DOWNLOAD  SET DOWNLOAD_CATEGORY = ?,DOWNLOAD_SUBCATEGORY= ?, "+
					               " DOWNLOAD_LINK = ?,DOWNLOAD_STATUS = ? ,DOWNLOAD_FLAG = ? ," +
					               " DOWNLOAD_DIVISION=?,DOWNLOAD_LINKNAME=?  WHERE AUTO_CODE = ? " ;					               
					getSqlModel().singleExecute(query1, updateObj);
					flagValue = 2;
				}// END else y
			}// END if "save"			
			return flagValue;
		} catch (Exception e) {
			logger.error("Exception Caught in Save Downloads : " + e);
			return 0;
		}
	}
	
	/*public Document buildDocument(String head, String subHead, Object[][] hrHome) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(head);

		Element header;
		Element data;

		header = root.addElement("COMMUNICATION").addAttribute("name", subHead);
		for (int i = 0; i < hrHome.length; i++)
			data = header.addElement("Link").addAttribute("id",
					"" + hrHome[i][0].toString()).addAttribute("name",
					"" + hrHome[i][1].toString()).addAttribute("value",
					"" + hrHome[i][2].toString());
		return document;
	}*/
	
	public void edit(MyDownloadsBean bean, String editCode) {
		String code="";
		String getName="";
		try {
			String query = " SELECT DOWNLOAD_CATEGORY,DOWNLOAD_SUBCATEGORY,DOWNLOAD_LINKNAME, "
				         + " NVL(DOWNLOAD_LINK,' '), DOWNLOAD_STATUS , "
				         + " DOWNLOAD_FLAG,DOWNLOAD_DIVISION FROM HRMS_DASHLET_DOWNLOAD WHERE AUTO_CODE = " + editCode;
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setCategoryName(String.valueOf(resultData[0][0]));
			bean.setSubCategoryName(String.valueOf(resultData[0][1]));
			bean.setCategoryNameDropdown(String.valueOf(resultData[0][0]));
			bean.setSubCategoryNameDropDown(String.valueOf(resultData[0][1]));
			bean.setLinkName(String.valueOf(resultData[0][2]));
			bean.setLink(String.valueOf(resultData[0][3]));
			bean.setCheckActive(String.valueOf(resultData[0][4]));
			String status = (String.valueOf(resultData[0][5]));			
			if (status.equals("D")) {
				bean.setCheckFlag("true");
				String link = String.valueOf(resultData[0][3]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadDocument(complete);
			} else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][3]);
				bean.setCheckFlag("false");
				bean.setLink(linkName);
			}// END else if
			String view = bean.getCheckActive();
			if (view.equals("Y"))
				bean.setCheckActive("true");
			else
				bean.setCheckActive("false");
			System.out.println("DIVITT---------"+bean.getDivCodeItt());
			bean.setDownloadDivCode(String
					.valueOf(resultData[0][6]));
			if(!(resultData[0][6]==null || String.valueOf(resultData[0][6]).equals("") || String.valueOf(resultData[0][6]).equals("null"))){
				String divQuery="SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("+String.valueOf(resultData[0][6])+")";
				Object [][]obj= getSqlModel().getSingleResult(divQuery);
				code= String.valueOf(resultData[0][6]);
				getName=Utility.getNameByKey(obj, code);						
				bean.setDownloadDivName(getName);
			}
			listDetails(bean);
			setCategoryName(bean);
			setSubCategoryName(bean);
		} catch (Exception e) {
			logger
					.error("Exception caught in editng download Info Settings : "
							+ e);
			e.printStackTrace();
		}
	}
	
	public boolean delete(MyDownloadsBean bean) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode();
		    String query=" DELETE FROM HRMS_DASHLET_DOWNLOAD  WHERE AUTO_CODE = ?";
			boolean result = getSqlModel().singleExecute(query, delCode);
			bean.setHiddenCode("");
			listDetails(bean);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void listDetails(MyDownloadsBean bean) {
		String code="";
		String getName="";
			String query3 = " SELECT AUTO_CODE,DOWNLOAD_CATEGORY,DOWNLOAD_SUBCATEGORY,DOWNLOAD_LINKNAME, "
					+ " NVL(DOWNLOAD_LINK,'No File Associated'), "
					+ " DOWNLOAD_FLAG,Decode(DOWNLOAD_STATUS,'Y','YES','N','NO') ,"
					+ " NVL(DOWNLOAD_DIVISION,' ') "
					+ " FROM HRMS_DASHLET_DOWNLOAD ORDER BY AUTO_CODE DESC";
			Object[][] getLinkData = getSqlModel().getSingleResult(query3);
			ArrayList<Object> list = new ArrayList<Object>();
			if(getLinkData!=null && getLinkData.length > 0){
				for (int i = 0; i < getLinkData.length; i++) {// loop x
					MyDownloadsBean beanLink = new MyDownloadsBean();
					
					beanLink.setLinkCode(checkNull(String.valueOf(getLinkData[i][0])));
					beanLink.setCatName(checkNull(String.valueOf(getLinkData[i][1])));
					beanLink.setSubCatName(checkNull(String.valueOf(getLinkData[i][2])));
					beanLink.setDLinkName(checkNull(String.valueOf(getLinkData[i][3])));
	
					//beanLink.setDLinkFile(checkNull(String.valueOf(getLinkData[i][4])));					
					 	String link = checkNull(String.valueOf(getLinkData[i][4]));					
					 if (String.valueOf(getLinkData[i][5]).equals("L")) {
						logger.info("Inside if" + link);
						beanLink.setDLinkFile(link);
					}// END if
					else {						
						beanLink.setDLinkFile(link.substring(
								link.lastIndexOf("/") + 1, link.length()));
					} 
					beanLink.setDLinkActive(checkNull(String.valueOf(getLinkData[i][6])));
					beanLink.setDivCodeItt(checkNull(String.valueOf(getLinkData[i][7])));
					if(!(getLinkData[i][7]==null || String.valueOf(getLinkData[i][7]).equals(" ") || String.valueOf(getLinkData[i][7]).equals("null"))){
						String divQuery="SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("+String.valueOf(getLinkData[i][7])+")";
						Object [][]obj= getSqlModel().getSingleResult(divQuery);
						code= String.valueOf(getLinkData[i][7]);
						getName=Utility.getNameByKey(obj, code);						
						beanLink.setDivNameItt(getName);
					}
					list.add(beanLink);
				}// END of loop s
			}			
			bean.setListLink(list);
	}
	
	/**
	 * to check null value
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void setCategoryName(MyDownloadsBean bean) {
		 try {
			 LinkedHashMap<String, String> map =new LinkedHashMap<String, String>();
			 String query = " SELECT DISTINCT DOWNLOAD_CATEGORY FROM HRMS_DASHLET_DOWNLOAD";			 
			/* if(!editCode.equals(""))
			 {
				 query +=" where AUTO_CODE="+editCode;
				 
			 }*/			
			 Object data[][] =getSqlModel().getSingleResult(query);			
			 if(data!=null && data.length>0){
				 for (int i = 0; i < data.length; i++) {
					 map.put(String.valueOf(data[i][0]), String.valueOf(data[i][0]));
				}
			 }
			 map.put("Other", "Other");
			 bean.setTmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void setSubCategoryName(MyDownloadsBean bean) {
		 try {
			 LinkedHashMap<String, String> subcategorymap =new LinkedHashMap<String, String>();
			 String query = " SELECT DISTINCT DOWNLOAD_SUBCATEGORY FROM HRMS_DASHLET_DOWNLOAD";			 
			/* if(!editCode.equals("")){
				 query +=" where AUTO_CODE="+editCode;
			 }*/
			 Object data[][] =getSqlModel().getSingleResult(query);			 
			 if(data!=null && data.length>0){
				 for (int i = 0; i < data.length; i++) {
					 subcategorymap.put(String.valueOf(data[i][0]), String.valueOf(data[i][0]));
				}
			 }
			 subcategorymap.put("Other", "Other");
			 bean.setSubcategorymap(subcategorymap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
