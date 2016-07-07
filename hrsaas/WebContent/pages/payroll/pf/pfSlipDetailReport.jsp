<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="NewLoansIssuedReport" validate="true" id="paraFrm"
	theme="simple">
	
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<!-- Header Start -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">

						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">PF Trust Summary Report</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/help.gif"
								width="16" height="16" /></div>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<s:if test="isApprove"></s:if>
				<s:else>
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /> </td>
						<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
		<!-- Header End -->
		
		<!-- Body Start -->
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				
				<tr>
					<td width="20%"><label
						name="financial.year.from" class="set" id="financial.year.from"
						ondblclick="callShowDiv(this);"><%=label.get("financial.year.from")%></label> </td>
					<td width="30%">
						<s:textfield name="sFinancialYearFrm" onkeypress="return numbersOnly()" maxlength="4" onkeyup="getEndYear();"></s:textfield>
					</td>
					<td width="20%"><label
						name="financial.year.to" class="set" id="financial.year.to"
						ondblclick="callShowDiv(this);"><%=label.get("financial.year.to")%></label> </td>
					<td width="30%"> 
						<s:textfield name="sFinancialYearTo" onkeypress="return numbersOnly()" maxlength="4" readonly="true"></s:textfield>
					</td>
				</tr>
				
				<s:if test="%{generalFlag}">
					<tr>
						<td width="20%"><label
							name="emp.name" class="set" id="emp.name"
							ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label> </td>
						<td width="30%"> 
							<input type="text" name="sEmpCode" size="10" readonly="true" value="<s:property value='sEmpCode' />" />
							<input type="text" name="sEmpName" size="25" readonly="true" value="<s:property value='sEmpName' />" />
							<s:hidden name="sEmpId" />
						</td>
						<td width="20%">&nbsp;</td>
						<td width="30%">&nbsp;</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="20%"><label
							name="emp.name" class="set" id="emp.name"
							ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label> 
						</td>
						<td width="30%" nowrap="nowrap"> 
							<s:textfield name="sEmpCode" size="10" readonly="true" /><s:textfield name="sEmpName" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Employee');">
							<s:hidden name="sEmpId" />
						</td>
						<td width="20%"> &nbsp; </td>
						<td width="30%"> &nbsp; </td>
					</tr>
					
					
					<tr>
						<td width="20%"><label
							name="emp.div" class="set" id="emp.div"
							ondblclick="callShowDiv(this);"><%=label.get("emp.div")%></label> </td>
						<td width="30%">
							<s:textfield name="sDivName" readonly="true"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Div');">
							<s:hidden name="sDivId" />
						</td>
						<td width="20%"><label
							name="emp.brch" class="set" id="emp.brch"
							ondblclick="callShowDiv(this);"><%=label.get("emp.brch")%></label> </td>
						<td width="30%"> 
							<s:textfield name="sBrchName" readonly="true"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Brch');">
							<s:hidden name="sBrchId" />
						</td>
					</tr>
					
					
					<tr>
						<td width="20%"><label
							name="emp.dept" class="set" id="emp.dept"
							ondblclick="callShowDiv(this);"><%=label.get("emp.dept")%></label> </td>
						<td width="30%">
							<s:textfield name="sDeptName" readonly="true"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Dept');">
							<s:hidden name="sDeptId" />
						</td>
						<td width="20%"><label
							name="emp.designation" class="set" id="emp.designation"
							ondblclick="callShowDiv(this);"><%=label.get("emp.designation")%></label> 
						</td>
						<td width="30%"> 
							<s:textfield name="sDesignation" readonly="true"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Designation');">
							<s:hidden name="sDesignationCode" />
						</td>
					</tr>
				</s:else>
				
				<tr>
					<td width="20%"><label
						name="report.type" class="set" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> </td>
					<td width="30%">
						<s:select name="sSelectedReportType" list="#{'Pdf':'PDF','Txt':'Word'}" headerKey="" headerValue="--Select--" /></select>
					</td>
					<td width="20%">
						&nbsp;
					</td>
					<td width="30%"> 
						&nbsp;
					</td>
				</tr>
				 
				
			</table>
			</td>
		</tr>
		<!-- Body End -->
		
		<!-- Footer Start -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<s:if test="isApprove"></s:if>
				<s:else>
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%">&nbsp;</td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
		<!-- Footer End -->
		</table>
	
</s:form>	

<script type="text/javascript">
	
	setFinancialYear('paraFrm_sFinancialYearFrm','paraFrm_sFinancialYearTo');
	
	function validateF9(action)
	{
		javascript:callsF9(500,325,'PFSlipDetailReport_'+action+'.action'); 
	}
	
	function reportFun()
	{
		if(document.getElementById('paraFrm_sFinancialYearFrm').value=="")
		{
			alert("Financial year should not blank.");
			document.getElementById('paraFrm_sFinancialYearFrm').focus();
			return false;
		}
		
		var fromYear = document.getElementById('paraFrm_sFinancialYearFrm').value;
		
		if(fromYear.length < 4)
		{
			alert("Financial year should be in 'YYYY' format");
			document.getElementById('paraFrm_sFinancialYearFrm').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_sSelectedReportType').value=="")
		{
			alert("Please select '"+document.getElementById('report.type').innerHTML + "'");
			document.getElementById('paraFrm_sSelectedReportType').focus();
			return false;
		}
		
		
		document.getElementById('paraFrm').action="PFSlipDetailReport_report.action";
    	document.getElementById("paraFrm").submit();
    	
    	/* After submit report action clear all controls. */
    	resetFun();
	}
	
	function resetFun()
	{
		document.getElementById('paraFrm_sFinancialYearFrm').value='';
		document.getElementById('paraFrm_sFinancialYearTo').value='';
		
		try
		{
		document.getElementById('paraFrm_sDivName').value='';
		document.getElementById('paraFrm_sDivId').value='';
		document.getElementById('paraFrm_sBrchName').value='';
		document.getElementById('paraFrm_sBrchId').value='';
		document.getElementById('paraFrm_sDeptName').value='';
		document.getElementById('paraFrm_sDeptId').value='';
		document.getElementById('paraFrm_sEmpName').value='';
		document.getElementById('paraFrm_sEmpId').value='';
		document.getElementById('paraFrm_sEmpCode').value='';
		document.getElementById('paraFrm_sSelectedReportType').value='';
		document.getElementById('paraFrm_sDesignation').value='';
		document.getElementById('paraFrm_sDesignationCode').value='';
		} catch(e) {
		//alert('error')
		}
		
		setFinancialYear('paraFrm_sFinancialYearFrm','paraFrm_sFinancialYearTo');
		
		return false;
	}
	
	function getEndYear ()
	{
		var fromYear = document.getElementById('paraFrm_sFinancialYearFrm').value;
    
   		if(fromYear == "")
    	{
    	 	document.getElementById('paraFrm_sFinancialYearTo').value="";
    	}
    	else
    	{
   	 		var toYear = eval(fromYear) + 1;
	  		document.getElementById('paraFrm_sFinancialYearTo').value = toYear;
	  	}
	}
	
</script>