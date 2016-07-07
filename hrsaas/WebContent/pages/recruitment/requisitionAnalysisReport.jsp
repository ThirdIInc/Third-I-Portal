<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="RequisitionAnalysis" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong class="formhead">
		         	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
		               <td width="93%" class="txt"><strong class="text_head">Requisition
					Analysis</strong></td>
			             <td width="3%" valign="top" class="txt">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="78%" colspan="3"><input type="button" class="token"
						onclick="return reportValidation();" value=" Show Report" /> <s:submit
						cssClass="reset" action="RequisitionAnalysis_reset" theme="simple"
						value="    Reset"  /></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="common"/>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="20%" class="formtext"><label class="set"
						id="reqs.code" name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>:
					</td>
					<td width="25%" ><s:hidden name="requisitionHiddenCode" /><s:textfield
						name="requisitionCode" size="25" readonly="true" theme="simple" maxlength="50" /></td>
						<td width="20%"><img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'RequisitionAnalysis_f9RequisitionCode.action');">
					</td>
					
					<td width="25%" class="formtext"><label class="set"
						id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
					</td>
					<td width="25%"><s:hidden name="divisonCode" /><s:textfield
						name="divison" size="25" readonly="true" theme="simple" maxlength="50" /></td>
					<td> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'RequisitionAnalysis_f9Divison.action');">
					</td>
				</tr>

				<tr>
					<td width="25%" class="formtext"><label class="set"
						id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
					</td>
					<td width="25%"><s:hidden name="branchCode" /><s:textfield
						name="branch" size="25" theme="simple" readonly="true" maxlength="70" /></td><td width="25%"> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'RequisitionAnalysis_f9Branch.action');">
					</td>
				
					<td width="25%" class="formtext"><label class="set"
						id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
					</td>
					<td width="25%"><s:hidden name="deptCode" /><s:textfield
						name="department" size="25" readonly="true"  theme="simple" maxlength="100" /></td><td> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'RequisitionAnalysis_f9Department.action');">
					</td>
				</tr>

				<tr>
					<td width="25%" class="formtext"><label class="set"
						id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
					</td>
					<td width="25%"><s:hidden name="designationCode" /><s:textfield
						name="designation" size="25" readonly="true" theme="simple" maxlength="100" /></td><td width="25%"> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'RequisitionAnalysis_f9Designation.action');">
					</td>
					<td width="25%" class="formtext"><label class="set"
						id="reqs.date" name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> :
					</td>
					<td width="25%"><s:textfield name="reqDate" size="25"
						theme="simple" maxlength="10" onkeypress="return numbersWithHiphen();"/></td><td> <s:a
						href="javascript:NewCal('paraFrm_reqDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				
				<tr>
					<td width="25%" nowrap="nowrap" class="formtext"><label class="set"
						id="noOfPersons" name="noOfPersons"
						ondblclick="callShowDiv(this);"><%=label.get("noOfPersons")%></label>:
					</td>
					<td width="25%"><s:textfield name="noOfPersons" size="25"
						theme="simple" maxlength="10" onkeypress="return numbersOnly();"/></td>
					<td width="25%"></td>	
					<td width="25%" nowrap="nowrap" class="formtext"><label class="set"
						id="totalCTC" name="totalCTC" ondblclick="callShowDiv(this);"><%=label.get("totalCTC")%></label>:
					</td>
					<td width="25%"><s:textfield name="totalCTC" size="25"
						theme="simple" maxlength="14" onkeypress="return numbersWithDot();" /></td>
				</tr>  
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="105%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="78%" colspan="3"><input type="button" class="token"
						onclick="return reportValidation();" value=" Show Report" /> <s:submit
						cssClass="reset" action="RequisitionAnalysis_reset" theme="simple"
						value="    Reset"  /></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function reportValidation(){
	var reqDate=document.getElementById("paraFrm_reqDate").value;
	if(reqDate!="") {
    	if(!validateDate('paraFrm_reqDate','reqs.date')){
		    return false;
		}
	}
	
	document.getElementById('paraFrm').action='RequisitionAnalysis_callJspView.action';	
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main"; 	
}

</script>