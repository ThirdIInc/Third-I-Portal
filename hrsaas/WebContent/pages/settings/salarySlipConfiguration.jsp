<!--@author Vishwambhar_Deshpande @date  18 Aug 2010 -->
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="SalarySlipConfiguration" validate="true" id="paraFrm"
	theme="simple">

	<!-- Main Table Starts -->
	<%
		String salHeader = "";
			try {
				salHeader = (String) request.getAttribute("headerStatus");
			} finally {
				if (salHeader == null) {
					salHeader = "";
				}
			}
	%>
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
					<td width="93%" class="txt"><strong class="text_head">Salary
					Slip Configuration </strong></td>
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
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input name="button" type="button"
						value="  Save  " class="save" onclick="save()" /></td>
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
			<td colspan="8" >
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="2" class="formtext"><b>Salary Slip Header</b></td>
				</tr>
				<tr>
					<td class="formtext"><input type="radio" value="Company"
						name="salHeader" <%=salHeader.equals("Company")?"checked":"" %> />
					Company Name</td>
					<td class="formtext"><input type="radio" value="Division"
						name="salHeader" <%=salHeader.equals("Division")?"checked":"" %> />
					Division Name</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				
					
				
				<tr>
					<td colspan="8" class="formtext"><b>Select fields to be
					displayed on salary slip</b></td>
				</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="name" id="name" ondblclick="callShowDiv(this);"><%=label.get("name")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="empName" disabled="true" checked="true" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="empId" id="empId1" ondblclick="callShowDiv(this);"><%=label.get("empId")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="empId" disabled="true" checked="true" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="doj" id="doj1" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="dateOfjoin" disabled="true" checked="true" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="pfno" id="pfno1" ondblclick="callShowDiv(this);"><%=label.get("pfno")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="pfNo" disabled="true" checked="true" /></td>
				</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="pan" id="pan1" ondblclick="callShowDiv(this);"><%=label.get("pan")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="misreport.loanAmountFlag" disabled="true" checked="true" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="paymode" id="paymode1" ondblclick="callShowDiv(this);"><%=label.get("paymode")%></label>:</td>
					<td colspan="1"><input type="checkbox" theme="simple"
						name="misreport.loanTypeFlag" disabled="true" checked="true" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="department" id="department1" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="departmentChk" id="paraFrm_departmentChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="branch" id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="branchChk"
						id="paraFrm_branchChk" /></td>
				</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="designation" id="designation1"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="designationChk" id="paraFrm_designationChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="emptype" id="emptype1" ondblclick="callShowDiv(this);"><%=label.get("emptype")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="empTypeChk"
						id="paraFrm_empTypeChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="salgrade" id="salgrade1" ondblclick="callShowDiv(this);"><%=label.get("salgrade")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="salaryGradeChk" id="paraFrm_salaryGradeChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="bank" id="bank1" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="bankIdChk"
						id="paraFrm_bankIdChk" /></td>
				</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="accno" id="accno" ondblclick="callShowDiv(this);"><%=label.get("accno")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="accountNoChk"
						id="paraFrm_accountNoChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="attendance" id="attendance" ondblclick="callShowDiv(this);"><%=label.get("attendance")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="attendanceDetailChk" id="paraFrm_attendanceDetailChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="leavedetails" id="leavedetails1"
						ondblclick="callShowDiv(this);"><%=label.get("leavedetails")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="leaveDetailChk" id="paraFrm_leaveDetailChk" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="showlogo" id="showlogo1" ondblclick="callShowDiv(this);"><%=label.get("showlogo")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="showLogoChk"
						id="paraFrm_showLogoChk" /></td>
				</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="gradeChk"
						id="paraFrm_gradeChk" /></td>
					<!-- Added checkbox(roleChk Y/N) button by abhijit -->
					<td width="20%" align="left" colspan="1"><label class="set"
						name="role" id="role" ondblclick="callShowDiv(this);"><%=label.get("role")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="roleChk"
						id="paraFrm_roleChk" /></td>
					<!-- Ended by abhijit -->
					<td width="20%" align="left" colspan="1"><label class="set"
						name="payBill" id="payBill" ondblclick="callShowDiv(this);"><%=label.get("payBill")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="payBill"
						id="paraFrm_payBill" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="trade" id="trade" ondblclick="callShowDiv(this);"><%=label.get("trade")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="trade"
						id="paraFrm_trade" /></td>

				</tr>
				<!-- added 10/01/2012 ganesh start -->

				<tr>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="april.to.date.salary" id="april.to.date.salary"
						ondblclick="callShowDiv(this);"><%=label.get("april.to.date.salary")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="aprilToDateSalaryChk" id="paraFrm_aprilToDateSalaryChk" /></td>

					<td width="20%" align="left" colspan="1"><label class="set"
						name="recovery.days" id="recovery.days"
						ondblclick="callShowDiv(this);"><%=label.get("recovery.days")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple" name="recoveryDays"
						id="paraFrm_recoveryDays" /></td>
					<td width="20%" align="left" colspan="1"><label class="set"
						name="net.pay.in.words" id="net.pay.in.words"
						ondblclick="callShowDiv(this);"><%=label.get("net.pay.in.words")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="netPayInWords" id="paraFrm_netPayInWords" /></td>
						
					<td width="20%" align="left" colspan="1"><label class="set"
						name="signature" id="signature"
						ondblclick="callShowDiv(this);"><%=label.get("signature")%></label>:</td>
					<td colspan="1"><s:checkbox theme="simple"
						name="signature" id="paraFrm_signature" /></td>

				</tr>
				<!-- added 10/01/2012 ganesh end -->
			</table>
	
		<tr>
			<td width="100%"><input name="button" type="button"
				value="  Save  " class="save" onclick="save()" /></td>
		</tr>
	</table>
</s:form>
<script>
	 
	function save(){
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = 'SalarySlipConfiguration_saveSettings.action';
		document.getElementById('paraFrm').submit();
	}
	


</script>