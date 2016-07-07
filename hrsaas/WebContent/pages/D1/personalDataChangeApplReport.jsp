

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="PersonalDataChangeApplReport" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="loanAppStatus" />
	<table width="100%" class="formbg">
		<!-- Start header Table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Personal
					Data Change Report </strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="5">
					<div id="">
					<table width="100%" border="0" class="formbg">
						
						<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="frmdate" id="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield name="fromDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="20%" class="formtext"><label
						name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield name="toDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
						
						
						<!-- Division & Departmnet -->
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="division" id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="divId" /><s:textfield
								size="25" name="divName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'PersonalDataChangeApplReport_f9divaction.action');"></td>
							<td width="20%" colspan="1"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="deptId" /><s:textfield
								size="25" name="deptName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'PersonalDataChangeApplReport_f9department.action');"></td>

							<td width="23%" colspan="1"></td>
						</tr>
						<!-- branch & designation -->
						<tr>
							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="branchCode" /><s:textfield
								size="25" name="branchName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'PersonalDataChangeApplReport_f9branch.action');"></td>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="designationCode" /><s:textfield
								size="25" name="designationName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'PersonalDataChangeApplReport_f9designation.action');"></td>

							<td width="23%" colspan="1"></td>
						</tr>

						<!-- employee & loan type-->
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="empCode" /> <s:hidden
								name="empToken" /> <s:textfield size="25" name="empName"
								readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'PersonalDataChangeApplReport_f9employee.action');"></td>


							<td width="20%" colspan="1"><label class="set"
								name="loan.status" id="loan.status"
								ondblclick="callShowDiv(this);"><%=label.get("loan.status")%></label>:
							</td>
							<td width="23%" colspan="1" class="formtext"><s:select
								headerValue="All" headerKey="T" name="status"
								list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}"  cssStyle="width:150" /></td>

							<td width="23%" colspan="1"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="loan.status"
								id="loan.status" ondblclick="callShowDiv(this);"><%=label.get("loan.report")%></label><font
								color="red">*</font>:</td>
							<td width="23%" colspan="1" class="formtext">
							<div id="reportTypeDiv"><s:select headerKey=""
								headerValue="--Select--" name="reportType"
								list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
							</td>
						</tr>

						<tr>

							<td align="center" colspan="5"><input type="button"
								class="token" theme="simple" value="  Generate Report"
								onclick=" return generateReport();" /> <input type="button"
								class="reset" theme="simple" value=" Reset"
								onclick="return calReset();" /></td>
						</tr>


					</table>
					</div>
					</td>
				</tr>



			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	
	 
	 function calReset(){
	 		 document.getElementById('paraFrm_branchName').value="";
	 		 document.getElementById("paraFrm_divName").value="";
		     document.getElementById('paraFrm_deptName').value="";
		     document.getElementById('paraFrm_empName').value="";
		     document.getElementById('paraFrm_status').value="T";
		     document.getElementById('paraFrm_reportType').value="";
		     document.getElementById('paraFrm_designationName').value="";
		     
		  //   document.getElementById('paraFrm').target='main';
		//document.getElementById('paraFrm').action="NewLoanIssuedRpt_reset.action";
		//document.getElementById('paraFrm').submit();
		     
	 }
	 
	 

	function generateReport()
	{
		try{ 
				//var mode=document.getElementById('reportType').value;
	
	if(document.getElementById('paraFrm_reportType').value=='')
	
		{
		    alert('Please select ReportType');
		    document.getElementById('paraFrm_reportType').focus();
		     return false;
		    }
		    
		    var brName = document.getElementById('paraFrm_branchName').value;
	var divisionName = document.getElementById('paraFrm_divName').value;
	var departmentName = document.getElementById('paraFrm_deptName').value;
	var employeeName = document.getElementById('paraFrm_empName').value;
	var status = document.getElementById('paraFrm_status').value;
	var designationName = document.getElementById('paraFrm_designationName').value;
		//alert("brName=" + brName);
		//	alert("divisionName=" + divisionName);
		//		alert("departmentName=" + departmentName);
			//		alert("employeeName=" + employeeName);
				//	alert("status=" + status);
			//		alert("designationName=" + designationName);
		    
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="PersonalDataChangeApplReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="_self"; 
		}catch(e)
		{ 
		alert("e------------"+e);
		}
	}	
</script>