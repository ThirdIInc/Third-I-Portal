<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetApplicationMisReport" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="empId" />

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset  
					Application MIS</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1">
					<s:if	test="%{assetAppMis.viewFlag}">
						<input type="button" class="token"	onclick="return callMisReport();"
							value=" Asset Application MIS Report"  />
					</s:if> <s:submit cssClass="reset" action="AssetApplicationMisReport_clear"
						theme="simple" value=" Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<s:if test="assetAppMis.generalFlag">
				
				<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3"><s:hidden name="divCode" /> <s:textfield
							name="divsion" theme="simple" size="25" readonly="true" /> 
						</td>
					</tr>
					<td width="12%"><label name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td><s:hidden theme="simple" name="assetAppMis.token" /><s:textfield
						theme="simple" name="assetAppMis.empName" size="25"
						readonly="true" /></td>

					<td><label name="report.type" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td><s:select theme="simple" name="assetAppMis.reportType"
						headerKey="Pdf" headerValue="Pdf"
						list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>


				</s:if>
				<s:else>
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3"><s:hidden name="divCode" /> <s:textfield
							name="divsion" theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							align="absmiddle" width="18" class="iconImage"
							onclick="javascript:callsF9(500,325,'AssetApplicationMisReport_f9div.action');">
						</td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden
							name="assetAppMis.deptCode" value="%{assetAppMis.deptCode}"
							theme="simple" /> <s:textfield name="assetAppMis.deptName"
							theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'AssetApplicationMisReport_f9dept.action');">
						</td>
						<td colspan="1" width="20%" class="formtext"><label
							name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden
							name="assetAppMis.centerNo" value="%{assetAppMis.centerNo}"
							theme="simple" /> <s:textfield name="assetAppMis.centerName"
							theme="simple" size="25" readonly="true" /><img
							src="../pages/images/recruitment/search2.gif" height="18"
							align="absmiddle" width="18" class="iconImage"
							onclick="javascript:callsF9(500,325,'AssetApplicationMisReport_f9center.action');">

						</td>

					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden
							name="assetAppMis.desgCode" value="%{assetAppMis.desgCode}"
							theme="simple" /> <s:textfield name="assetAppMis.desgName"
							theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'AssetApplicationMisReport_f9desg.action');">
						</td>
						<td colspan="1" width="20%" class="formtext"><label
							name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>
						:</td>
						<td colspan="1" width="30%"><s:select theme="simple"
							list="#{'':'Select','P':'Pending','R':'Rejected','A':'Approved','S':'Assigned'}"
							name="assetAppMis.status"></s:select></td>
					</tr>
	
					<tr>
						<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
						</td>
					</tr>

					<tr>

						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="3"><s:textfield theme="simple"  readonly="true"
							name="token" size="25"/><s:textfield theme="simple"
							name="empName" size="75" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callEmployee();"></td>
						<s:hidden theme="simple" name="assetAppMis.center" />

						<s:hidden theme="simple" name="assetAppMis.rank" />


					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="report.type" id="report.type"
							ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
						:</td>
						<td colspan="3"><s:select theme="simple"
							name="assetAppMis.reportType" headerKey="Pdf" headerValue="Pdf"
							list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
					</tr>
					</s:else>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript">

	function callMisReport(){
		if(document.getElementById('paraFrm_divsion').value==""){
				alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
				return false;
			}
	  	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action='AssetApplicationMisReport_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}
	
	function callEmployee(){
		var divCode=document.getElementById('paraFrm_divCode').value ;
		var divName=document.getElementById('division').innerHTML.toLowerCase();
		if(divCode=='' ){
			alert("Please select "+divName);
			return false;
		}
		callsF9(500,325,'AssetApplicationMisReport_f9action.action');
	}
	

</script>
