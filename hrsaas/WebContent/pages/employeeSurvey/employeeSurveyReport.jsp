<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmployeeSurveyReport" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Survey Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" class="token"
						onclick="return callReport();" value=" Report"  />
					<s:submit cssClass="reset" action="EmployeeSurveyReport_reset"
						theme="simple" value="    Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="1" width="20%"><label class="set" id="survey"
						name="survey" ondblclick="callShowDiv(this);"><%=label.get("survey")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:hidden name="surveyCode" /><s:textfield
						theme="simple" readonly="true" name="surveyName" size="25" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeSurveyReport_f9survey.action');"></td>

					<td colspan="1" width="20%"><label class="set" id="section"
						name="section" ondblclick="callShowDiv(this);"><%=label.get("section")%></label>
					:</td>


					<td colspan="1" width="30%"><s:hidden name="sectionCode" /><s:textfield
						theme="simple" readonly="true" name="sectionName" size="25" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeSurveyReport_f9section.action');"></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" id="employee"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>




					<td colspan="1" width="30%"><s:hidden name="employeeCode" />
					<s:hidden name="employeeToken" /> <s:textfield theme="simple"
						readonly="true" name="employeeName" size="25" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:getEmployee();"></td>
						
						<td width="20%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchId" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeSurveyReport_f9branch.action');" />
					</td>

					
				</tr>
				
				
				<!-- DIVISION -->
					<!-- DEPARTMENT-->

				<tr>
					<td width="20%" colspan="1"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="35%" colspan="1"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionId" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeSurveyReport_f9division.action');" />
					</td>

					
<td width="20%" colspan="1"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="35%" colspan="1"><s:textfield size="25"
						name="departmentName" readonly="true" /> <s:hidden
						name="departmentId" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeSurveyReport_f9department.action');" />
					</td>


				</tr>
			
				<!--Report Type -->

				<tr>
					

					<td colspan="1" width="20%"><label class="set"
						id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					<font color="red">*</font>:</td>

					<td colspan="1" width="30%"><s:select theme="simple"
						name="reportType" cssStyle="width:155"
						list="#{'0':'--Select--','Pdf':'Pdf','Xls':'Xls','Txt':'Txt'}" /></td>

				</tr>


			</table>
	</table>

</s:form>
<script type="text/javascript">

function callReport(){
	 
	 var surveyCode = document.getElementById('paraFrm_surveyCode').value;
	 
	 if(surveyCode=="")
	 {
		 alert('Please select survey');
		return false;
	 }
	 
	 if(document.getElementById('paraFrm_reportType').value=='0'){
		alert('Please select '+document.getElementById('report.type').innerHTML);
		document.getElementById('paraFrm_reportType').focus();
		return false;
	}
	
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='EmployeeSurveyReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	 
	}
	
	
	
	function getEmployee()
	{
	try{
  
		var surveyCode=document.getElementById('paraFrm_surveyCode').value;
	 
			 if(surveyCode==""){
				alert("Please select survey ");
				return false;
			}
			else{
			callsF9(500,325,'EmployeeSurveyReport_f9employee.action');
		 	}
	
	}
	catch(e)
	{
			alert("Value e--------------"+e);
	}
	
	
	 return true ;
	}
	
	 


</script>


