<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CashBalanceReport_" validate="true" id="paraFrm"
	theme="simple">
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
					Cash Balance Report </strong></td>
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



				<!-- DIVISION -->
				<!-- BRANCH -->

				<tr>
					<td width="15%" colspan="1"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="35%" colspan="1"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'CashBalanceReport_f9division.action');" />
					</td>

					<td width="15%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'CashBalanceReport_f9branch.action');" />
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
						onclick="javascript:callsF9(500,325,'CashBalanceReport_f9department.action');" />
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
						onclick="javascript:callsF9(500,325,'CashBalanceReport_f9designation.action');" />
					</td>

					<!-- EMPLOYEE NAME -->
				<tr>
					<td colspan="5">
					<table>
						<tr>
							<td width="15%" colspan="1" class="formtext"><label
								class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:<s:hidden name="empId" /></td>
							<td width="10%" colspan="1"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /></td>
							<td width="75%" colspan="3"><s:textfield name="employeeName"
								size="50" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="14"
								align="absmiddle" width="14"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9emp.action');">
							</td>

						</tr>


					</table>
					</td>
				</tr>
				<!-- REPORT  TYPE -->
				<!-- REPORT  TYPE -->
				<tr>



					<td width="15%" colspan="1"><label class="set"
						name="creditName" id="creditName" ondblclick="callShowDiv(this);"><%=label.get("creditName")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="creditName" readonly="true" /> <s:hidden name="creditId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'CashBalanceReport_f9creditName.action');" />
					</td>



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

  		document.getElementById('paraFrm').action = 'CashBalanceReport_generateReport.action';
		document.getElementById('paraFrm').submit();
      	

}


</script>