package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.bean.Recruitment.OnlinePaper;


import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.report.CrystalReport;
import org.struts.action.recruitment.OnlinePaperPriviewAction;



import sun.misc.Request;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnlinePaperModel extends ModelBase {static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
static int  count=0;
static String[][]testObj=null;
static Object finalObject[][]=null;


public String submitnewuser(OnlinePaper paperBean) {
	String result="";
	String encryptPwd = "";
	Object data[][] = null;
	String query="select * from   HRMS_REC_CAND_LOGIN where CAND_USERNAME='"+paperBean.getLoginName()+"'";
	data=getSqlModel().getSingleResult(query);
	if(data.length>0){
		try {
			encryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(paperBean
					.getLoginPassword());
		} catch (Exception e) {
		logger.error("exception in encrypt PWD",e);
		}
	query = "select * from   HRMS_REC_CAND_LOGIN where CAND_PWD='"+encryptPwd+"'";
	data=getSqlModel().getSingleResult(query);
	
	if(data.length>0){
		paperBean.setCandidateLoginCode(String.valueOf(data[0][0]));
		result="true";
	}
	else
	{
		result="false";
	}
	return result;
}
	else
	{
		result="false";
		return result;
	}
 }
}



















