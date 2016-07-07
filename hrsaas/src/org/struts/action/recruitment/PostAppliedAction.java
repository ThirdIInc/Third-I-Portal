package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.PostApplied;
import org.paradyne.model.recruitment.PostAppliedModel;
import org.struts.lib.ParaActionSupport;

public class PostAppliedAction extends ParaActionSupport {

	PostApplied post;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	@Override
	public void prepare_local() throws Exception {
		post=new  PostApplied();
		post.setMenuCode(487);
	}
	
public void prepare_withLoginProfileDetails() throws Exception {
		
	PostAppliedModel model =new PostAppliedModel();
				
				model.initiate(context,session);
				
				model.postData(post,request);
				model.terminate();
		}

public String  input() {
	return "input";
}
	

	public Object getModel() {
		// TODO Auto-generated method stub
		return post;
	}

	public PostApplied getPost() {
		return post;
	}

	public void setPost(PostApplied post) {
		this.post = post;
	}
	public String save()
	{
		System.out.println("\\1111///");
		PostAppliedModel model =new PostAppliedModel();
		/*model.initiate(context);
		boolean flag;
		if(post.getPostCode().equals(""))
			{
					flag=model.addData(post);
		
						if(flag)
							{
								addActionMessage("record saved successfully");
							}
						else
						{
								addActionMessage("record is not  saved successfully");
						}
		
		
			}
		
		else{
			
			System.out.println("else modCity");
	     flag = model.modPost(post);
		 if(flag) {
				addActionMessage("city modified successfully");
			} else {
				addActionMessage("City can not be modified");
			}
		}
		model.terminate();*/
		model.initiate(context,session);
		logger.info("Context initiated");
		boolean result;

		if (post.getPostCode().equals("")) {
			logger.info("else addDept");
			result = model.addData(post);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				post.setPostCode("");
				post.setPostName("");

			} else {
				addActionMessage("Post can not be added");
			}
		} else {
			logger.info("else modCity");
			
			result = model.modPost(post);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				post.setPostCode("");
				post.setPostName("");

			} else {
				addActionMessage("Post can not be added");
			}
		}
		logger.info("Model Terminated");
		model.postData(post,request);
		model.terminate();
		return "success";
	}
	public String reset()
	
		{
			post.setPostCode("");
			post.setPostName("");
			return "success";
		}
	
	public String calforedit() throws Exception{
		PostAppliedModel model =new PostAppliedModel();
		model.initiate(context,session);
		model.calforedit(post);
		
	
		model.postData(post,request);
		
		model.terminate();
		return "success";
	}
	
	
	
	
	public String delete()
	{
		PostAppliedModel model =new PostAppliedModel();
		model.initiate(context,session);
		boolean flag=model.deleteData(post);
		if(flag){
		addActionMessage("record deleted successfully");
		post.setPostCode("");
		post.setPostName("");

		}else
		{
			addActionMessage("record cannot be deletedc");
		}
		
		model.postData(post,request);
		model.terminate();
		return "success";
		
	}
	
	public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		
		PostAppliedModel model =new PostAppliedModel();
		
		model.initiate(context,session);
		boolean result =model.deletePost(post,code);
		if(result)
		{
			addActionMessage(getText("Record deleted Successfully"));
					
		}
			else {
				addActionMessage("One or more records can't be deleted /n as they are associated with some other record(s) .");
				
			}
		

		model.postData(post,request);
		
		model.terminate();
		
		return "success";

	}
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT POST_CODE,POST_NAME FROM  HRMS_POST_APP ORDER BY POST_CODE";	
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Post Code","Post Name" };
		
		String[] headerWidth={"50","50"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"postCode","postName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

}
