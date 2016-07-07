<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="InvestmentDeclarationReport" validate="true" id="paraFrm" validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Investment
					Declaration Report</strong></td>
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
					<td><input type="button" class="token"
						onclick="return generateReport();" value=" Investment Report " />&nbsp;
						<input type="button" class="token"
							   onclick="return generateHRAReport();" value=" HRA Report " />&nbsp;
						<input type="button" class="token"
						onclick="return reset();" value=" Reset " /></td>
					<td width="22%" align="right">
					<font color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<!--<tr>
					<td><label class="set" id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3">
					<s:hidden name="empID" />
					<s:textfield name="empTokenNo" size="25" readonly="true" /> 
					<s:textfield name="empName" size="73" readonly="true" />
					<img src="../pages/images/search2.gif" height="16"
							align="absmiddle" width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'InvestmentDeclarationReport_f9empaction.action'); ">
					</td>
				</tr>
				-->
				<tr>
					<td><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font color="red">*</font>:</td>
					<td><s:hidden
						name="divisionId" /><s:textfield name="divisionName"
						theme="simple" readonly="true" maxlength="50" size="25" />  
					<img src="../pages/images/search2.gif" height="16"
							align="absmiddle" width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'InvestmentDeclarationReport_f9division.action'); ">
					</td>
				</tr>
				<tr>
					<td><label class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:hidden name="desigID" />
					<s:textfield size="25" name="desigName"
						readonly="true" /><img src="../pages/images/search2.gif" height="16"
							align="absmiddle" width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'InvestmentDeclarationReport_f9designation.action'); "></td>
					<td><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> 
					:</td>
					<td><s:hidden name="centerID" />
					<s:textfield size="25" name="centerName"
						readonly="true" /><img src="../pages/images/search2.gif" height="16"
							align="absmiddle" width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'InvestmentDeclarationReport_f9branch.action'); "></td>
				</tr>
				<tr>
					<td><label class="set" id="taxFinYearFrm" name="taxFinYearFrm"
						ondblclick="callShowDiv(this);"><%=label.get("taxFinYearFrm")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield size="25" name="fromYear"
						maxlength="4" onkeypress="return numbersOnly();" onblur="changeYear()" /></td>

					<td><label class="set" id="taxFinYearTo" name="taxFinYearTo"
						ondblclick="callShowDiv(this);"><%=label.get("taxFinYearTo")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield size="25" name="toYear"
						maxlength="4" readonly="true" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	function generateReport(){
		if(!validateData()){
			return false;
		}else{
			document.getElementById('paraFrm').target='_self';
			document.getElementById('paraFrm').action='InvestmentDeclarationReport_generateReport.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function generateHRAReport(){
		if(!validateData()){
			return false;
		}else{
			document.getElementById('paraFrm').target='_self';
			document.getElementById('paraFrm').action='InvestmentDeclarationReport_generateHouseRentReport.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function validateData(){
		if(document.getElementById('paraFrm_divisionName').value == ""){
			alert("Please select division");
			return false;
		}
		if(document.getElementById('paraFrm_fromYear').value == ""){
			alert("Please select financial year from");
			return false;
		}
		return true;	
	}
	
	function changeYear(){
    	var from = document.getElementById('paraFrm_fromYear').value;
    	if(from==""){
    		document.getElementById('paraFrm_toYear').value="";
    	}else{
   	 		var x=eval(from) +1;
	  		document.getElementById('paraFrm_toYear').value=x;
	  	}
	}
</script>