<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PAProcessReport" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="empId" />
	<s:hidden name="branchId" />
	<s:hidden name="divisionId" />
	<s:hidden name="componentId" />
	<s:hidden name="departmentId" />
	<s:hidden name="designationId" />
	<s:hidden name="empTypeId" />
	<s:hidden name="paId" />
	<s:hidden name="tempo" />
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Periodic Allowance Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						onclick="return callReport()" value="   Report   "> <s:submit
						cssClass="reset" action="PAProcessReport_reset" theme="simple"
						value="    Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"  
				class="formbg">
			     <tr >
							<td colspan="2"    height="22" class="formtext"><label  class = "set"  id="allowance.duration" name="allowance.duration" ondblclick="callShowDiv(this);"><%=label.get("allowance.duration")%></label> :</td>
							<td height="22" colspan="2" ><s:textfield name="paDate" size="18" maxlength="4"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9PaDate.action');">
							</td>
						</tr>

						<tr>
							<td colspan="1"height="22" class="formtext"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>  <font
								color="red">*</font>:</td>
							<td colspan="1" height="22"><s:textfield name="division" size="30"
								theme="simple" maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Division.action');">
							</td>
							<td height="22" colspan="1" class="formtext"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td height="22" colspan="1" ><s:textfield name="branch" size="30"
								theme="simple" maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Branch.action');">
							</td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>  :</td>
							<td><s:textfield name="department" size="30" theme="simple"
								maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Department.action');">
							</td>
							<td height="22" class="formtext"><label  class = "set"  id="component" name="component" ondblclick="callShowDiv(this);"><%=label.get("component")%></label>  :</td>
							<td><s:textfield name="component" size="30" theme="simple"
								maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Component.action');">
							</td>
						</tr>


						<tr>
							<td height="22" class="formtext"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>  :</td>
							<td height="22"><s:textfield name="empType" size="30"
								theme="simple" maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9EmpType.action');">
							</td>
							<td height="22" class="formtext"><label  class = "set"  id="allowance.duration" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>  :</td>
							<td height="22"><s:textfield name="designation" size="30"
								theme="simple" maxlength="70" readonly="true"
								onkeypress="return allCharacters();"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Designation.action');">
							</td>
						</tr>
						<tr>
							<td colspan="1" height="22" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> 
							:</td>
							<td colspan="3" height="22"><s:textfield name="empCode" size="25"
								theme="simple" maxlength="70" readonly="true" /> <s:textfield
								name="empName" size="74" theme="simple" maxlength="70"
								readonly="true"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PAProcessReport_f9Emp.action');">


							</td>
						</tr>

						<tr>
							<td height="22" colspan="1" class="formtext"><label  class = "set"  id="modePay" name="modePay" ondblclick="callShowDiv(this);"><%=label.get("modePay")%></label>  :</td>
							<td  colspan="3" height="22"><s:select name="payMode" headerKey="-1"
								headerValue="--Select--"
								list="#{'B':'Salary','C':'Cash','Q':'Cheque','T':'Transfer'}" />
							</td>
						</tr>

						<tr>
							<td colspan="1" height="22" class="formtext"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>  <font
								color="red">*</font>:</td>
							<td colspan="3" height="22"><s:select name="rptType" headerKey="-1"
								headerValue="--Select--" list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
						</tr>

					</table>
					</td></tr></table></td></tr></table>
</s:form> 
					<script>

 

function callReport(){

		if(document.getElementById('paraFrm_division').value==""){
		  alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
		  return false;
		}if(document.getElementById('paraFrm_rptType').value=="-1"){
		  alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_rptType').focus();
		  return false;
		}
		
		/*
		if(document.getElementById('paraFrm_paDate').value==""){
		 alert('Please select PA Duration: ');
		 return false;
		}
		
		if(document.getElementById('paraFrm_frmMonth').value=="-1"){
		   alert('Please select from month');
		   document.getElementById('paraFrm_frmMonth').focus();
		   return false;
		}
		if(document.getElementById('paraFrm_frmYear').value=="-1"){
		   alert('Please select from year');
		   document.getElementById('paraFrm_frmYear').focus();
		   return false;
		}						  						  
		if(document.getElementById('paraFrm_toMonth').value=="-1"){
		   alert('Please select to month');
		   document.getElementById('paraFrm_toMonth').focus();
		   return false;
		}
		if(document.getElementById('paraFrm_toYear').value=="-1"){
		   alert('Please select to year');
		   document.getElementById('paraFrm_toYear').focus();
		   return false;
		}*/
		
		document.getElementById('paraFrm').action="PAProcessReport_report.action";
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}
</script>