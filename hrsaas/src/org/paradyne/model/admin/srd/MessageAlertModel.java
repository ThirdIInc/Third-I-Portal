package org.paradyne.model.admin.srd;
import org.paradyne.bean.admin.srd.MessageAlert;
import org.paradyne.bean.admin.transfer.TransferApproval;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;


public class MessageAlertModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
public void getEmpChkList(MessageAlert msgAlert,HttpServletRequest request){
		
		String SQL=" SELECT MESSAGE_ID,MESSAGE_TO,NVL(MESSAGE_SUBJECT,''),NVL(MESSAGE_DETAILS,''),NVL(TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY'),''),NVL(MESSAGE_TYPE,''), " 
				+" MESSAGE_APPLICATION_CODE,MESSAGE_STATUS FROM HRMS_MESSAGES "
				//+" WHERE MESSAGE_STATUS='P' AND HRMS_MESSAGES.MESSAGE_TO="+msgAlert.getUserEmpId()+" ORDER BY MESSAGE_ID desc";
		+"  WHERE MESSAGE_STATUS='P' and  rownum < 10 ORDER BY MESSAGE_ID desc ";
		Object data[][]=getSqlModel().getSingleResult(SQL);
		
		HashMap afdata=new HashMap();	
		ArrayList<MessageAlert> alertList=new ArrayList<MessageAlert>();
		
	
		
	
			for(int i=0; i<data.length;i++)
			{
				MessageAlert bean=new MessageAlert();
				bean.setMsgId(String.valueOf(data[i][0]));
				bean.setMsgTo(String.valueOf(data[i][1]));
				bean.setMsgSub(String.valueOf(data[i][2]));
				String str= String.valueOf(data[i][3]);
				logger.info("---------------------------------------------------------------------value"+String.valueOf(data[i][3]));
				String[] abc=str.split("#");
				logger.info("-----------------------------------------"+str);
				logger.info("---------------------"+abc.length+"---"+abc[0]);
				if(abc.length==3)
				{
					bean.setChkFlag("true");
				}
				bean.setMsgDet(String.valueOf(data[i][3]));
				if(String.valueOf(data[i][4]).equals("null")) {
					bean.setMsgDate("");								
				}else {
				bean.setMsgDate(String.valueOf(data[i][4]));
				}
				bean.setMsgType(String.valueOf(data[i][5]));
				bean.setAppCode(String.valueOf(data[i][6]));
				afdata.put(""+i,String.valueOf(String.valueOf(data[i][7])));
				alertList.add(bean);
				
			request.setAttribute("data",afdata);
			
		   }
			msgAlert.setMsgList(alertList);
	      
		}

public boolean saveByApprover(MessageAlert msgAlert,String msgId)
{
		
	Object[][] update = new Object[1][1];
		
	update[0][0] = msgId;
		
	String query="UPDATE HRMS_MESSAGES SET MESSAGE_STATUS='C' WHERE MESSAGE_ID= ?";
	
	return getSqlModel().singleExecute(query, update);
	
	
  }

public void getDetail(MessageAlert msgAlert,String msgId,HttpServletRequest request)
{
	String query="select MESSAGE_DETAILS from HRMS_MESSAGES where MESSAGE_ID="+msgId;
	
	Object data[][]=getSqlModel().getSingleResult(query);
	
	String str=String.valueOf(data[0][0]);
//	msgAlert.setMsg1(messag);
	request.setAttribute("msg1", str);
	/*if(strSpilt.length==3)
	{
		msgAlert.setMsg1(String.valueOf(strSpilt[0]));
		msgAlert.setMsg2(String.valueOf(strSpilt[1]));
		msgAlert.setMsg3(String.valueOf(strSpilt[2]));
	}
	else
	{
		msgAlert.setMsg1(String.valueOf(strSpilt[0]));
		msgAlert.setMsg2(String.valueOf(strSpilt[1]));
		msgAlert.setMsg3(String.valueOf(strSpilt[2]));
		msgAlert.setMsg4(String.valueOf(strSpilt[3]));
		msgAlert.setMsg5(String.valueOf(strSpilt[4]));
		
	}*/
}
}

	
	


