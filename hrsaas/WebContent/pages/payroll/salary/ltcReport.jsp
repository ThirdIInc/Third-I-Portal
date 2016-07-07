<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="LTCReport_" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					LTC Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL ENDS -->



		<tr>
			<td colspan="4">
			<table border="0" cellpadding="1" cellspacing="1" class="formbg"
				width="100%">

				<!-- MONTH -->
				<!-- YEAR -->
				<tr>
					<td width="15%" colspan="1">Month <font color="red">*</font> :</td>
					<td width="35%" colspan="1"><s:select name="month"
						title="Select a month"
						list="#{'0':'--Select--','1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
						'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" />

					</td>

					<td width="15%" colspan="1">Year <font color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield size="25" name="year" />

					</td>



				</tr>


				<!-- DIVISION -->
				<!-- BRANCH -->

				<tr>
					<td width="15%" colspan="1"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font color="red">*</font> :</td>
					<td width="35%" colspan="1"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'LTCReport_f9division.action');" />
					</td>

					<td width="15%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'LTCReport_f9branch.action');" />
					</td>



				</tr>
				<!-- DEPARTMENT-->
				<!--DESIGNATION -->

				<tr>
					<td width="15%" colspan="1"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="35%" colspan="1"><s:textfield size="25"
						name="departmentName" readonly="true" /> <s:hidden
						name="departmentId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'LTCReport_f9department.action');" />
					</td>

					<td width="15%" colspan="1"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="designation" readonly="true" /> <s:hidden
						name="designationId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'LTCReport_f9designation.action');" />
					</td>

					<!-- EMPLOYEE NAME -->
				<tr>
					<td width="15%"><label class="set" id="employee"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
					</td>
					<s:hidden name="empCode" />
					<td width="85%" colspan="3"><s:textfield name="empToken" size="15"
						theme="simple" readonly="true" /> <s:textfield name="employeeName" size="50"
						theme="simple" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="14"
						align="absmiddle" width="14"
						onclick="javascript:callsF9(500,325,'LTCReport_f9emp.action');">
					</td>

				</tr>


				<!-- REPORT  TYPE -->
				<tr>
					<td width="15%" class="formtext">Report Type</td>
					<td width="35%" colspan="2" class="formtext">
					<div id="reportTypeDiv"><s:select name="reportType"
						list="#{'P':'Pdf','X':'Xls'}" /></div>
					</td>

				</tr>

			</table>
			</td>
		</tr>
	</table>


</s:form>


<script>
function generateReport(){
	if(document.getElementById('paraFrm_month').value=="0"){
		alert("Please select month");
		return false;
	}
	if(document.getElementById('paraFrm_year').value==""){
		alert("Please enter year");
		return false;
	}
	if(document.getElementById('paraFrm_divisionId').value==""){
		alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCReport_generateReport.action';
	document.getElementById('paraFrm').submit();
}


</script>