<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="ApprFormStart" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><label
						name="appraisal.list" class="set" id="appraisal.list"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.list")%></label></strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%">
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><strong class="formhead">Pending List </strong></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formth" width="5%"><label class="set"
						name="appraisal.start.srno" id="appraisal.start.srno1"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.srno")%></label></td>
					<td class="formth" width="25%" align="left"><label
						name="appraisal.start.period" class="set"
						id="appraisal.start.period1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.period")%></label></td>
					<td class="formth" width="20%"><label
						name="appraisal.start.sdate" class="set"
						id="appraisal.start.sdate1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.sdate")%></label></td>
					<td class="formth" width="20%"><label
						name="appraisal.start.edate" class="set"
						id="appraisal.start.edate1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.edate")%></label></td>
					<td class="formth" width="20%"><label
						name="appraisal.start.view" class="set" id="appraisal.start.view1"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.view")%></label></td>
				</tr>

				<%
				int m = 1, v = 0;
				%>
				<s:iterator value="dataList">

					<tr>
						<td width="5%" align="center" class="td_bottom_border"><%=m%></td>
						<td width="25%" align="left" class="td_bottom_border"><s:property
							value="apprCode" /></td>
						<td width="20%" align="center" class="td_bottom_border"><s:property
							value="apprStartDate" /></td>
						<td width="20%" align="center" class="td_bottom_border"><s:property
							value="apprEndDate" /><s:hidden name="happrId" /></td>
						<td width="15%" align="center" class="td_bottom_border"><input
							type="button" value=" View" class="token"
							onclick="callHidden('<s:property value="happrId"/>')" /></td>
						<%
							m++;
							v++;
						%>
					</tr>
				</s:iterator>
				<tr>
					<td><s:hidden name="apprId" /><s:hidden name="apprCode" /><s:hidden
						name="apprStartDate" /><s:hidden name="apprEndDate" /><s:hidden
						name="apprValidTillDate" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test='%{finalData=="false"}'>
			<tr>
				<td colspan="3"><strong	class="formhead">Appraisal Results</strong></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td class="formth" width="5%"><label class="set"
							name="appraisal.start.srno" id="appraisal.start.srno"
							ondblclick="callShowDiv(this);"><%=label.get("appraisal.start.srno")%></label></td>
						<td class="formth" width="25%" align="left"><label
							name="appraisal.start.period" class="set"
							id="appraisal.start.period" ondblclick="callShowDiv(this);"><%=label.get("apprLetter")%></label></td>
						<td class="formth" width="20%"><label
							name="appr.from.date" class="set"
							id="appr.from.date2" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label></td>
						<td class="formth" width="20%"><label
							name="appr.to.date" class="set"
							id="appr.to.date2" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label></td>
					</tr>

					<%
					int j = 1, c = 0;
					%>
					<s:iterator value="finalizedList">
						<tr>
							<td width="5%" align="center"><%=j%></td>
							<td width="25%" align="left">
							<s:hidden name="finalApprEmpIdItt" /> 
							<s:hidden name="finalApprPromoCodeItt" /> 
							<s:hidden name="finalTemplateCodeItt" /> 
							<s:hidden name="finalApprEmpNameItt" /> 
								
								<a href="javascript:void(0);" 
								onclick="callPreviewLetter('<s:property value="finalApprEmpIdItt" />','<s:property value="finalApprPromoCodeItt"/>','<s:property value="finalApprCodeItt" />','<s:property value="finalTemplateCodeItt" />','<s:property value="finalApprEmpNameItt" />');">
								<s:property	value="finalApprCodeItt" /></a>
								</td>
							<td width="20%" align="center" ><s:property	value="finalStartDateItt" /></td>
							<td width="20%" align="center" ><s:property	value="finalEndDateItt" /></td>
							<%
								j++;
								c++;
							%>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
	</table>
</s:form>


<script type="text/javascript">

	function searchFun(){
			
			callsF9(500,325,'ApprFormStart_searchAppraisal.action');
							
	}

	function callHidden(apprCode){
	
		document.getElementById('paraFrm_apprId').value=apprCode;
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').action = "ApprFormGeneralInfo_getApprStartDetails.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}

	function callPreviewLetter(empId, promCode, apprCode, templateCode,empName){
	 	actionName="ApprFormStart_previewLetter.action?empId="+empId+"&promCode="+promCode+"&apprCode="+apprCode+"&templateCode="+templateCode+"&empName="+empName;
		document.getElementById('paraFrm').action =actionName;
		document.getElementById('paraFrm').submit();
	}
 
  
    
</script>
