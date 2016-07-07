<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TemplateDefination" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" cellpadding="2" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Template
					Definition</strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.code" id="appraisal.code"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:if
								test="calupdateFlag">
								<s:property value="apprCode" />
								<s:hidden name="apprCode"></s:hidden>
							</s:if> <s:else>
								<s:textfield name="apprCode" size="20" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="javascript:callsF9(500,325,'TemplateDefination_f9SelectAppraisal.action'); "
									id="ctrlHide">
							</s:else></td>
							<td width="50%" colspan="2" height="20" nowrap="nowrap"
								class="formtext"><label class="set"
								name="template.appr.start.date" id="template.appr.start.date"
								ondblclick="callShowDiv(this);"><%=label.get("template.appr.start.date")%></label>:
							<s:property value="startDate"></s:property>&nbsp;&nbsp; <label
								class="set" name="template.appr.end.date"
								id="template.appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.end.date")%></label>:
							<s:property value="endDate"></s:property></td>

						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="template.name" id="template.name"
								ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="templateName" size="50" maxlength="49" /></td>
							<td width="50%" colspan="2" height="20" class="formtext"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
			<table class="formbg" width="100%">
				<tr>
					<td colspan="7"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Appraisee
					Group List</label></strong></td>
				</tr>
				<tr>
					<!--   srNo -->
					<td align="center" class="formth" width="15%"><label
						class="set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>

					<td align="center" class="formth" width="25%"><label
						class="set" name="group.name" id="group.name1"
						ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
					</td>
					<td align="center" class="formth" width="35%"><label
						class="set" id="tempEdit" name="tempEdit"
						ondblclick="callShowDiv(this);"> Template Design </label></td>
					<td align="center" class="formth" width="25%"><label
						class="set" id="tempEdit" name="tempEdit"
						ondblclick="callShowDiv(this);"> Rating Definition </label></td>
				</tr>
				<%
							int count1 = 0;
						%>
				<%!int d1 = 0;%>
				<%
							int ii = 0;
						%>
				<%!int val = 0;%>
				<s:iterator value="temList">

					<tr <%if(count1%2==0){
															%> class="tableCell1"
						<%}else{%> class="tableCell2" <%	}count1++; %>
						onmouseover="javascript:newRowColor(this);"
						onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
						ondblclick="javascript:callForEdit('<s:property value="templateCode" />','<s:property value="templateName"/>');" title="Double Click to edit">
						<!--   srNo -->
						<td width="15%" align="center" class="sortableTD"><%=++ii%><input
							type="hidden" name="srNo" value="<%=ii%>" /></td>

						<td align="center" class="sortableTD" width="25%">&nbsp; <s:property
							value="templateName" /></td>
						<td class="formth" class="sortableTD" width="35%" id="ctrlHide">
						<input type="button" name="Template Design " id="Template Design "
							value="Template Design "
							onclick="saveandnextFun('<s:property value="templateCode" />','<s:property value="templateName"/>')" />

						</td>
						<td class="formth" class="sortableTD" width="25%" id="ctrlHide">
						<input type="button" name="Rating Defination"
							id="Rating Defination" value="Rating Definition"
							onclick="ratingDefinationFun('<s:property value="templateCode" />','<s:property value="templateName"/>')" />

						</td>
					</tr>

				</s:iterator>
				<input type="hidden" id="rowCount" value="<%=ii %>">
				<%if(ii==0){ %>
				<tr>
					<td colspan="7" class="sortableTD" align="center"><font
						color="red">No Data To Display</font></td>
				</tr>
				<%} %>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="apprId" />
	<s:hidden name="startDate" />
	<s:hidden name="endDate" />
	<s:hidden name="templateCode" />
	<s:hidden name="saveFlag" />
	<s:hidden name="calupdateFlag" />
</s:form>


<script type="text/javascript">
//var fieldName  = ["paraFrm_apprCode", "paraFrm_templateName"]; 
//var lableName  = ["appraisal.code","template.name"];
//var flag	   = ["select", "enter"];
// var f9Fields=["paraFrm_templateName"];
 
function searchFun(){
	document.getElementById("paraFrm_saveFlag").value='true';
	callsF9(500,325,'TemplateDefination_search.action');
}

function saveFun(){
		if(trim(document.getElementById("paraFrm_templateName").value)==""){
		alert("Please enter "+document.getElementById("template.name").innerHTML.toLowerCase());
		return false;
		}
	   var tempName=trim(document.getElementById("paraFrm_templateName").value);
	   
		document.getElementById("paraFrm").action="TemplateDefination_save.action";
		document.getElementById("paraFrm").submit();
}

function saveandnextFun(){		
		document.getElementById("paraFrm").action="TemplateDefination_saveAndNext.action";
		document.getElementById("paraFrm").submit();		
}

function saveandnextFun(tempCode,tempName){
		document.getElementById("paraFrm_templateCode").value=tempCode;
		document.getElementById("paraFrm_templateName").value=tempName;
		document.getElementById("paraFrm").action="TemplateDefination_saveAndNext.action";
		document.getElementById("paraFrm").submit();
}

function ratingDefinationFun(tempCode,tempName){
		document.getElementById("paraFrm_templateCode").value=tempCode;
		document.getElementById("paraFrm_templateName").value=tempName;
		document.getElementById("paraFrm").action="ApprRatingDefinition_getSavedPhaseRatingFormula.action";
		document.getElementById("paraFrm").submit();
}

function cancelFun(){
		document.getElementById("paraFrm").action="TemplateDefination_input.action";
	    document.getElementById("paraFrm").submit();				
}

function resetFun(){
		document.getElementById("paraFrm").action="TemplateDefination_reset.action";
		document.getElementById("paraFrm").submit();
}

function nextFun(){
		var flag = document.getElementById("paraFrm_saveFlag").value;
		var tempCode = document.getElementById("paraFrm_templateCode").value;
		if(flag == 'false'){
			alert("Please select the saved template or save new template.");
			return false;
		}
		if(flag == 'false' && tempCode == ''){
			alert("Please save the template and then go to Next.");
			return false;
		}
		document.getElementById("paraFrm").action="AppraisalInstruction_input.action";
		document.getElementById("paraFrm").submit();	
}

function backFun(){
		document.getElementById("paraFrm").action="AppraisalCalendar_input.action?menuCode=748";
		document.getElementById("paraFrm").submit();	
}

function callForEdit(code,name){
document.getElementById("paraFrm").action="TemplateDefination_callForEdit.action?templateCode="+code+"&templateName="+name;
document.getElementById("paraFrm").submit();	
}
</script>
