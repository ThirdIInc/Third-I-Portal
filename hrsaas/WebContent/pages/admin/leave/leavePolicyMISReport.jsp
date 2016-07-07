<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeavePolicyMIS" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Leave
					Policy MIS Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<s:hidden name="hiddenCode" />
					<s:hidden name="hiddenDivCode" />
					<s:hidden name="newPolicyFlag" />
					<s:hidden name="newExceptionFlag" />
					<s:hidden name="nextFlag" />
					<!--
					<td width="78%"><input type="submit" name="report"
						value="Leave Policy Report" class="report"
						onclick="return callReport();"> <s:submit cssClass="reset"
						action="LeavePolicyMIS_reset" value=" Reset" /></td>
					-->
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;
								</td>
								<td width="100%"><%@ include
									file="/pages/common/reportButtonPanel.jsp"%>
								</td>
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
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<s:if test="leavePolicyMIS.generalFlag">
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="division" id="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:<font color="red">* </font></td>
						<td colspan="3" width="80%"><s:textfield size="25"
							name="divisionName" readonly="true" /> <s:hidden
							name="divisionCode" /></td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="employee" id="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="3" width="80%"><s:textfield name="tokenNo"
							size="25" readonly="true" /><s:textfield size="75"
							name="employeeName" readonly="true" /> <s:hidden
							name="employeeCode" /></td>


					</tr>

					<%--
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="report.type" id="report.type"
							ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
						:</td>
						<td colspan="3" width="80%"><s:select theme="simple"
							name="reportType"
							list="#{'Pdf':'Pdf','Xls':'Xls','Txt':'Text'}" /></td>
					</tr>
					--%>
				</s:if>

				<s:else>
				
				
				<tr>
					<td height="15" class="formhead" nowrap="nowrap"><strong
						class="forminnerhead">Select a filter:-</strong></td>
				</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="division" id="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:<font color="red">* </font></td>
						<td colspan="3" width="80%"><s:textfield size="25"
							name="divisionName" readonly="true" /> <s:hidden
							name="divisionCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDivision();" /></td>
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="department" id="department"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="deptName" readonly="true" /> <s:hidden name="deptCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDept();" /></td>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="branch" id="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="branchName" readonly="true" /> <s:hidden name="branchCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callBranch();" /></td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="employee.type" id="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="empTypeName" readonly="true" /> <s:hidden
							name="empTypeCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmpType();" /></td>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="desgName" readonly="true" /> <s:hidden name="desgCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDesignation();" /></td>
					</tr>

					<tr>
						<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
						&nbsp;</td>
					</tr>


					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="employee" id="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="3" width="80%"><s:textfield name="tokenNo"
							size="25" readonly="true" /><s:textfield size="75"
							name="employeeName" readonly="true" /> <s:hidden
							name="employeeCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmployee();" /></td>


					</tr>

					<%--
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="report.type" id="report.type"
							ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
						:</td>
						<td colspan="3" width="80%"><s:select theme="simple"
							name="reportType"
							list="#{'Pdf':'Pdf','Xls':'Xls','Txt':'Text'}" /></td>
					</tr>
					--%>
				</s:else>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	id='topButtonTable'>
					<tr valign="middle">
						<td nowrap="nowrap"><a href="#" onclick="resetFun();">
						<img src="../pages/images/buttonIcons/Refresh.png"
							class="iconImage" align="absmiddle" title="Reset"> Reset
						</a>&nbsp;&nbsp;</td>
						<td width="100%"><%@ include
							file="/pages/common/reportButtonPanelBottom.jsp"%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='LeavePolicyMIS_report.action' />
</s:form>

<script>

function callReport(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}	
} 

function resetFun(){
	document.getElementById('paraFrm').action='LeavePolicyMIS_reset.action';
	document.getElementById('paraFrm').submit();
}

function mailReportFun(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='LeavePolicyMIS_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}
function validation() {
	var empCOde=document.getElementById('paraFrm_employeeCode').value ;
	var divCode=document.getElementById('paraFrm_divisionCode').value ;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	var divName=document.getElementById('division').innerHTML.toLowerCase();

	if(divCode=='' ){
		alert("Please select " + divName + " ");
		return false;		
	}
	return true;
}

	function nextFun(){
		var divCode = document.getElementById('paraFrm_divisionCode').value;
		document.getElementById('paraFrm_hiddenDivCode').value = divCode;
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").action="LeavePolicySetting_next.action";
		document.getElementById("paraFrm").submit();
	}
	
	
	
	function callDivision(){
		callsF9(500,325,'LeavePolicyMIS_f9division.action');
	}
	
	function callEmpType(){
		callsF9(500,325,'LeavePolicyMIS_f9empType.action');
	}
	
	function callDept(){
		callsF9(500,325,'LeavePolicyMIS_f9dept.action');
	}
	
	function callBranch(){
		callsF9(500,325,'LeavePolicyMIS_f9branch.action');
	}
	
	function callDesignation(){
		callsF9(500,325,'LeavePolicyMIS_f9designation.action');
	}
	
	function callEmployee(){
	var divCode=document.getElementById('paraFrm_divisionCode').value ;
	var divName=document.getElementById('division').innerHTML.toLowerCase();
		
		
		
	if(divCode=='' ){
	alert("Please select  "+divName+" ");
	return false;		
	}
	
		callsF9(500,325,'LeavePolicyMIS_f9employee.action');
	}
	
	function callPolicy()
	{
		callsF9(500,325,'LeavePolicyMIS_f9policy.action');
	}
		
	

</script>