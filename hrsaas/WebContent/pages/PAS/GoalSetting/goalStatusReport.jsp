<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="GoalSettingStatusMisReport" validate="true" id="paraFrm"
	theme="simple">
	
	<s:hidden name="reportAction" value='GoalSettingStatusMisReport_genReport.action'/>
<s:hidden name="report" />
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
   
   
    	<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Goal Status Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    
    
  		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    <tr>
      <td colspan="4"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">

						<tr>
							<td colspan="1" width="20%"><label class="set" id="goal"
								name="goal" ondblclick="callShowDiv(this);"><%=label.get("goal")%></label> : <font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:hidden name="goalId"/><s:hidden name="goalFromDate"/><s:hidden name="goalToDate"/><s:textfield
								name="goalName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9GoalAction.action');">

							</td>
							
					
							<td colspan="1" width="20%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td colspan="1" width="80%"><s:hidden name="empId" />
							<s:textfield
								theme="simple" readonly="true" size="10" name="empToken" />
							 <s:textfield
								theme="simple" readonly="true" size="30" name="empName" /> 
								<img src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9employee.action');">


							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : </td>
							<td colspan="1" width="30%"><s:hidden name="divisionId"></s:hidden><s:textfield
								name="divisionName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9Division.action');">

							</td>


							<td colspan="1" width="20%"><label class="set" id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="branchId" />
							<s:textfield name="branchName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9Branch.action');">

							</td>
						</tr>

						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:hidden name="deptId" /> <s:textfield
								name="deptName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9Dept.action');">
							</td>


							<td colspan="1" width="20%"><label class="set"
								id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="desgId" />
							<s:textfield name="desgName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'GoalSettingStatusMisReport_f9Desg.action');">

							</td>
						</tr>
			

					



					</table></td>
          </tr>
      </table></td>
    </tr>
     <tr>
      <td><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
          <td width="20%"  ><strong><label class="set" id="goalSetting"
								name="goalSetting" ondblclick="callShowDiv(this);"><%=label.get("goalSetting")%></label> :</strong></td>
			<td width="20%" ><s:checkbox  id="isGoalSetting" name="showGoalSettingReport"  onclick="isGoalSettingDet()"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
	</tr>
	
	<tr id="showGoalSetting">
				<td colspan="1" width="20%"><label class="set" id="show.selfAssessment"
								name="show.selfAssessment" ondblclick="callShowDiv(this);"><%=label.get("show.selfAssessment")%></label> :</td>
							<td colspan="1" width="20%"><s:checkbox   value="true" name="showGoalSettingselfReport" /></td>
			<td colspan="1" width="20%"><label class="set" id="show.managerAssessment"
								name="show.managerAssessment" ondblclick="callShowDiv(this);"><%=label.get("show.managerAssessment")%></label> :</td>
							<td colspan="1" width="20%"><s:checkbox value="true" name="showGoalSettingManagerReport" /></td>
			<td colspan="1" width="40%"><label class="set" id="show.pendingEmployeeDetails"
								name="show.pendingEmployeeDetails" ondblclick="callShowDiv(this);"><%=label.get("show.pendingEmployeeDetails")%></label> :</td>
							<td colspan="1" width="20%"><s:checkbox name="showPendingEmployee" /></td>				
				
			
	</tr>
	
      </table></td>
    </tr>
     <tr>
      <td><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
          <td width="20%"  ><strong><label class="set" id="goalAssesment"
								name="goalAssesment" ondblclick="callShowDiv(this);"><%=label.get("goalAssesment")%></label> :</strong></td>
			<td width="20%" ><s:checkbox name="showGoalAssesmentReport" id="isgoalAsses"
						onclick="isAsses()" /></td>
			<td></td>
			
			</tr>
			<tr id="showAssesment">
				<td colspan="1" width="20%"><label class="set" id="show.selfAssessment"
								name="show.selfAssessment" ondblclick="callShowDiv(this);"><%=label.get("show.selfAssessment")%></label> :</td>
							<td colspan="1" width="20%"><s:checkbox   value="true" name="showGoalAssesmentSelfReport" /></td>
			<td colspan="1" width="20%"><label class="set" id="show.managerAssessment"
								name="show.managerAssessment" ondblclick="callShowDiv(this);"><%=label.get("show.managerAssessment")%></label> :</td>
							<td colspan="1" width="20%"><s:checkbox value="true" name="showGoalAssesmentManagerReport" /></td>
			
			
			</tr>
      </table></td>
    </tr>
    
    
    <tr>
			<td>
			<div id="bottomButtonTable"></div>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td nowrap="nowrap" width="4%"><a href="#"
						onclick="callReset();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>
	</table>
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script><!--
function callReport(type){

	var fields=["paraFrm_goalName"];
	var labels=["goal"];
	var flags=["select"];
	if(!validateBlank(fields,labels,flags)){
		return false;
	}
		document.getElementById('paraFrm_report').value=type; 
		var goalsetting=document.getElementById('isGoalSetting').checked;
		var goalasses=document.getElementById('isgoalAsses').checked;
		if(goalsetting==true || goalasses==true){
		
		}else{
		alert("Please select at least one checkbox");
		return false;
		}
		callReportCommon(type);		
}
function mailReportFun(type){
var fields=["paraFrm_goalName"];
	var labels=["goal"];
	var flags=["select"];
	if(!validateBlank(fields,labels,flags)){
		return false;
	}
		document.getElementById('paraFrm_report').value=type;
		var goalsetting=document.getElementById('isGoalSetting').checked;
		var goalasses=document.getElementById('isgoalAsses').checked;
		if(goalsetting==true || goalasses==true){
		
		}else{
		alert("Please select at least one checkbox");
		return false;
		}
		document.getElementById('paraFrm').action='GoalSettingStatusMisReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
function callReset(){

	document.getElementById('paraFrm_divisionId').value="";
	document.getElementById('paraFrm_divisionName').value="";
	document.getElementById('paraFrm_branchId').value="";
	document.getElementById('paraFrm_branchName').value="";
	document.getElementById('paraFrm_deptId').value="";
	document.getElementById('paraFrm_deptName').value="";
	document.getElementById('paraFrm_desgId').value=""; 
	document.getElementById('paraFrm_desgName').value="";
	document.getElementById('paraFrm_empToken').value="";
	document.getElementById('paraFrm_empId').value="";
	document.getElementById('paraFrm_empName').value="";
	
	document.getElementById('paraFrm_report').value="Xls";
	document.getElementById('isGoalSetting').checked=false;
	document.getElementById('paraFrm_showGoalSettingManagerReport').checked=true;
	document.getElementById('paraFrm_showGoalSettingselfReport').checked=true;
	document.getElementById('paraFrm_showPendingEmployee').checked=false;
	document.getElementById('isgoalAsses').checked=false;
	document.getElementById('paraFrm_showGoalAssesmentSelfReport').checked=true;
	document.getElementById('paraFrm_showGoalAssesmentManagerReport').checked=true;
	document.getElementById('paraFrm_goalName').value="";
	document.getElementById('paraFrm_goalId').value="";
	isAsses();
	isGoalSettingDet();
}

	isAsses();
		function isAsses(){	
	  try{
	
		   if(document.getElementById('isgoalAsses').checked){
		  		document.getElementById('showAssesment').style.display="";   
		   }
		   else {
		   		document.getElementById('showAssesment').style.display="none";
		   }		   
		 }catch(e){} 
		 
	}
	
	isGoalSettingDet();
	
	function isGoalSettingDet(){	
	  try{
		   if(document.getElementById('isGoalSetting').checked){
		  		document.getElementById('showGoalSetting').style.display="";   
		   }
		   else {
		   		document.getElementById('showGoalSetting').style.display="none";
		   }		   
		 }catch(e){} 
		 
	}
--></script>