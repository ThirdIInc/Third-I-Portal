<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="PensionSlip" validate="true" id="paraFrm"
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
							<td width="93%" class="txt"><strong class="text_head">Pension Slip</strong></td>
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
					<td width="15%"><label
						name="pension.month" class="set" id="pension.month"
						ondblclick="callShowDiv(this);"><%=label.get("pension.month")%></label> </td>
					<td width="35%">
						<s:select name="sMonth" list="#{'01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" headerKey="" headerValue="--Select--" /></select>
					</td>
					<td width="15%"><label
						name="pension.year" class="set" id="pension.year"
						ondblclick="callShowDiv(this);"><%=label.get("pension.year")%></label> </td>
					<td width="35%"> 
						<s:textfield name="sYear" onkeypress="return numbersOnly()" maxlength="4" size="25"></s:textfield>
					</td>
				</tr>
				
				<s:if test="%{generalFlag}">
					<tr>
						<td width="15%"><label
							name="emp.name" class="set" id="emp.name"
							ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label> </td>
						<td width="85%" colspan="3" nowrap="nowrap"> 
							<input type="text" name="sEmpCode" size="10" readonly="true" value="<s:property value='sEmpCode' />" />
							<input type="text" name="sEmpName" size="25" readonly="true" value="<s:property value='sEmpName' />" />
							<s:hidden name="sEmpId" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="15%"><label
							name="emp.name" class="set" id="emp.name"
							ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label> 
						</td>
						<td width="85%" colspan="3" nowrap="nowrap"> 
							<s:textfield name="sEmpCode" size="25" readonly="true" />&nbsp;
							<s:textfield name="sEmpName" readonly="true" size="70" /> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Employee');">
							<s:hidden name="sEmpId" />
						</td>
					</tr>
					
					<tr>
						<td width="15%"><label
							name="emp.div" class="set" id="emp.div"
							ondblclick="callShowDiv(this);"><%=label.get("emp.div")%></label> </td>
						<td width="35%">
							<s:textfield name="sDivName" readonly="true" size="25" ></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Div');">
							<s:hidden name="sDivId" />
						</td>
						<td width="15%"><label
							name="emp.brch" class="set" id="emp.brch"
							ondblclick="callShowDiv(this);"><%=label.get("emp.brch")%></label> </td>
						<td width="35%"> 
							<s:textfield name="sBrchName" readonly="true" size="25"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Brch');">
							<s:hidden name="sBrchId" />
						</td>
					</tr>
					
					<tr>
						<td width="15%"><label
							name="emp.dept" class="set" id="emp.dept"
							ondblclick="callShowDiv(this);"><%=label.get("emp.dept")%></label> </td>
						<td width="35%">
							<s:textfield name="sDeptName" readonly="true" size="25"></s:textfield> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Dept');">
							<s:hidden name="sDeptId" />
						</td>
						<td width="15%"><label
							name="emp.designation" class="set" id="emp.designation"
							ondblclick="callShowDiv(this);"><%=label.get("emp.designation")%></label> </td>
						<td width="35%"> 
							<s:textfield name="sDesignation" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Designation');">
							<s:hidden name="sDesignationCode" />
						</td>
					</tr>
				</s:else>
				
				<tr>
					<td width="15%"><label
						name="report.type" class="set" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> </td>
					<td width="35%">
						<s:select name="sSelectedReportType" list="#{'Pdf':'PDF','Word':'Word'}" headerKey="" headerValue="--Select--" /></select>
					</td>
					<td width="15%">
						&nbsp;
					</td>
					<td width="35%"> 
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

<script type="text/javascript"><!--
	
	setFinancialYear('paraFrm_sFinancialYearFrm','paraFrm_sFinancialYearTo');
	
	function validateF9(action)
	{
		javascript:callsF9(500,325,'PensionSlip_'+action+'.action'); 
	}
	
	function reportFun()
	{
		
		if(document.getElementById('paraFrm_sMonth').value=="") {
			alert("Select Pension Month");
			document.getElementById('paraFrm_sMonth').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_sYear').value=="") {
			alert("Select Pension Year");
			document.getElementById('paraFrm_sYear').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_sSelectedReportType').value=="") {
			alert("Please select '"+document.getElementById('report.type').innerHTML + "'");
			document.getElementById('paraFrm_sSelectedReportType').focus();
			return false;
		}
		
		/*
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
		*/
		
		document.getElementById('paraFrm').action="PensionSlip_report.action";
    	document.getElementById("paraFrm").submit();
    	
    	/* After submit report action clear all controls. */
    	resetFun();
	}
	
	function resetFun()	{
		document.getElementById('paraFrm_sMonth').value='';
		document.getElementById('paraFrm_sYear').value='';
		//setFinancialYear('paraFrm_sFinancialYearFrm','paraFrm_sFinancialYearTo');
		try{
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
		}catch(e){
		//alert('error')
		}
		
		return false;
	}
	
--></script>