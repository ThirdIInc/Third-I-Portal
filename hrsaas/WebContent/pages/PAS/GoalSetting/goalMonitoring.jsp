<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="GoalMonitoring" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
	    <tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        		<tr>
				         <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Goal Monitoring</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				     </tr>
	        	</table>
	        </td>
        </tr>
        <tr>
     		 <td colspan="3">
     		 	<table width="100%" border="0" cellpadding="2" cellspacing="2">
                  <tr>
            		<td width="78%"></td>
            		<td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          		  </tr>
        		</table>
          	</td>
   		 </tr>
   		 <tr>
	      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.goalName"  id="goalMonit.goalName" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.goalName")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:hidden name="goalId"/><s:hidden name="goalFromDate"/><s:hidden name="goalToDate"/><s:textfield
								name="goalName" theme="simple" readonly="true"
								maxlength="50" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callGoal();"></td>
	               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.reviewDate"  id="compMonit.reviewDate" ondblclick="callShowDiv(this);">&nbsp;</label> <font color="red">&nbsp;</font> </td>
				  <td width="30%" colspan="1" height="20">&nbsp;</td>

	            </tr>
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.goalSetting"  id="goalMonit.goalSetting" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.goalSetting")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><input type="radio" name="goalMonitoringType" value="S" id="goalSetting" onclick="showReviewDate()"/></td>
	               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.goalAssessment"  id="goalMonit.goalAssessment" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.goalAssessment")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><input type="radio" name="goalMonitoringType" value="A" id="goalAssessment" onclick="showReviewDate()"/></td>

	            </tr>
	            
	            
	         </table></td>
	         
	       </tr>
	       <tr>
	      <td colspan="3" id="reviewDateId" style="display:none;"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.reviewDate"  id="goalMonit.reviewDate" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.reviewDate")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield
								name="assessmentDate" theme="simple" readonly="true"
								maxlength="50" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callReviewDate();"></td>
	               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.reviewDate"  id="compMonit.reviewDate" ondblclick="callShowDiv(this);">&nbsp;</label> <font color="red">&nbsp;</font> </td>
				  <td width="30%" colspan="1" height="20">&nbsp;</td>
	            </tr>
	         </table></td>
	       </tr>
	       <tr>
	       <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.empName"  id="goalMonit.empName" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.empName")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="goalEmpName" size="20" maxlength="25" readonly="true" /><s:hidden name="goalEmpToken"/><s:hidden name="goalEmpId"/><s:hidden name="goalHeaderId"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callSelectEmployee();"></td>
	               <td width="50%" colspan="1" height="20" class="formtext">&nbsp;</td>
				  

	            </tr>
	         </table></td>
	       </tr>
	        <tr>
	      <td colspan="3" id="goalSettingId" style="display:none;">
	      
	      <table width="100%"  border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.sendbackto"  id="goalMonit.sendbackto" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.sendbackto")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="sendbackEmpName" size="20" maxlength="25" readonly="true" /><s:hidden name="sendbackEmpName"/><s:hidden name="sendbackEmpId"/><s:hidden name="sendbackEmpToken"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'GoalMonitoring_f9SendbackEmpAction.action');"></td>
	               <td width="50%" colspan="1" height="20" class="formtext"><input type="button" value="Process" onclick="callSendBackEmp();"></td>
				  

	            </tr>
   		 </table>
   		 
   		
   		 </td>
   		 </tr>
   		   <tr>
	      <td colspan="3" id="goalAssessmentId" style="display:none;">
   		  <table width="100%"  border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="goalMonit.sendbackto"  id="goalMonit.sendbackto" ondblclick="callShowDiv(this);"><%=label.get("goalMonit.sendbackto")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="sendbackAssmtEmpName" size="20" maxlength="25" readonly="true" /><s:hidden name="sendbackAssmtEmpName"/><s:hidden name="sendbackAssmtEmpId"/><s:hidden name="assessmentLevel"/><s:hidden name="sendbackAssmtEmpToken"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'GoalMonitoring_f9SendbackAssessmentEmpAction.action');"></td>
	               <td width="50%" colspan="1" height="20" class="formtext"><input type="button" value="Process" onclick="callSendBackEmp();"></td>
				  

	            </tr>
   		 </table>
   		 </td>
   		 </tr>
   		 </table>
   		 
   		 </s:form>
<script>
	function callGoal(){
			//document.getElementById("paraFrm_assessmentDate").value="";
			//document.getElementById("paraFrm_assessmentId").value="";
			//document.getElementById("paraFrm_compEmpName").value="";
			//document.getElementById("paraFrm_compEmpId").value="";
			//document.getElementById("paraFrm_compEmpToken").value="";
			document.getElementById("paraFrm_sendbackAssmtEmpName").value="";
			document.getElementById("paraFrm_sendbackAssmtEmpId").value="";
			document.getElementById("paraFrm_sendbackAssmtEmpToken").value="";
			document.getElementById("paraFrm_assessmentDate").value="";
			document.getElementById("paraFrm_assessmentLevel").value="";
			document.getElementById("paraFrm_sendbackEmpName").value="";
			document.getElementById("paraFrm_sendbackEmpToken").value="";
			document.getElementById("paraFrm_sendbackEmpId").value="";
			document.getElementById("paraFrm_goalEmpToken").value="";
			document.getElementById("paraFrm_goalEmpName").value="";
			document.getElementById("paraFrm_goalEmpId").value="";
			document.getElementById("paraFrm_goalHeaderId").value="";
			
			javascript:callsF9(500,325,'GoalMonitoring_f9GoalAction.action'); 
	}
	function callSelectEmployee(){
			if(document.getElementById("paraFrm_goalId").value=="")
			{
				alert("Please select goal");
				return false;
			}			
			
			
			javascript:callsF9(500,325,'GoalMonitoring_f9GoalEmpAction.action'); 
	}
	function showReviewDate()
	{
		//alert("radio :: "+document.getElementById('goalAssessment').checked);
		if(document.getElementById('goalAssessment').checked)
		{
			document.getElementById('reviewDateId').style.display="inline";
			document.getElementById('goalAssessmentId').style.display="inline";
			document.getElementById('goalSettingId').style.display="none";
			document.getElementById("paraFrm_sendbackEmpName").value="";
			document.getElementById("paraFrm_sendbackEmpToken").value="";
			document.getElementById("paraFrm_sendbackEmpId").value="";
			
		}
		else
		{
			document.getElementById("paraFrm_sendbackAssmtEmpName").value="";
			document.getElementById("paraFrm_sendbackAssmtEmpId").value="";
			document.getElementById("paraFrm_sendbackAssmtEmpToken").value="";
			document.getElementById("paraFrm_assessmentDate").value="";
			document.getElementById("paraFrm_assessmentLevel").value="";
			document.getElementById('reviewDateId').style.display="none";
			document.getElementById('goalSettingId').style.display="inline";
			document.getElementById('goalAssessmentId').style.display="none";
		}
		//alert(" radio button value :: "+obj.value);
	}
	function callReviewDate(){
			if(document.getElementById("paraFrm_goalId").value=="")
			{
				alert("Please select goal");
				return false;
			}			
			document.getElementById("paraFrm_goalEmpName").value="";
			document.getElementById("paraFrm_goalEmpId").value="";
			document.getElementById("paraFrm_goalEmpToken").value="";
			
			javascript:callsF9(500,325,'GoalMonitoring_f9AssessmentDate.action'); 
	}
	function callSendBackEmp()
	{
		if(document.getElementById("paraFrm_goalId").value=="")
			{
				alert("Please select goal");
				return false;
			}
			if((!document.getElementById('goalAssessment').checked)&&(!document.getElementById('goalSetting').checked))
			{
				alert("Please select type");
				return false;
			}
			if(document.getElementById('goalAssessment').checked)
			{
				if(document.getElementById("paraFrm_assessmentDate").value=="")
				{
					alert("Please select review date");
					return false;
				}
			}
			
			if(document.getElementById("paraFrm_goalEmpId").value=="")
			{
				alert("Please select employee");
				return false;
			}
			if(document.getElementById('goalAssessment').checked)
			{
				if(document.getElementById("paraFrm_sendbackAssmtEmpId").value=="")
				{
					alert("Please select send back goal to");
					return false;
				}
			}
			else
			{
				if(document.getElementById("paraFrm_sendbackEmpId").value=="")
				{
					alert("Please select send back goal to");
					return false;
				}
			}
			
	
		var conf = confirm('Do you really want to add selected employee to send back process ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="GoalMonitoring_sendBackGoal.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
</script>   		 