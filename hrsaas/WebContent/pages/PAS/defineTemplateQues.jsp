<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript"
	src="../pages/common/employeeTreeMapping.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<%
	String[][] temp = null;
	Object[][] items = null;
	Object[][] phaseList = null;
%>
<s:form action="SectionMapping" theme="simple" method="post"
	name="paraFrm" id="paraFrm">
	<%
				String calupdateflag = (String) request
				.getAttribute("calupdateFlag");
	%>
	<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
	<s:hidden name="apprId" />
	<s:hidden name="startDate"></s:hidden>
	<s:hidden name="endDate"></s:hidden>
	<s:hidden name="apprCode"></s:hidden>
	<s:hidden name="templateCode" />
	<s:hidden name="templateName"></s:hidden>
	<s:hidden name="addFLag"></s:hidden>
	<s:hidden name="moveGroupName" />
	<s:hidden name="generateListFlag" />
	<s:hidden name="modifiedListFlag" />
	<s:hidden name="empTypeFlag" />
	<s:hidden name="addQuestionFlag" />
	<s:hidden name="isSectionDefinedFlag" />
	<s:hidden name="check" value="%{check}" />
	<s:hidden name="groupId" />
	<s:hidden name="groupName"></s:hidden>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/recruitment/review_shared.gif" width="25"
								height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Define
							Template Question </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img
								src="../pages/images/recruitment/help.gif" width="16"
								height="16" /></div>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="apprCode" />
							<td width="50%" colspan="2" height="20" nowrap="nowrap"
								class="formtext"><label name="template.appr.start.date"
								class="set" id="template.appr.start.date"
								ondblclick="callShowDiv(this);"><%=label.get("template.appr.start.date")%></label>:
							<s:property value="startDate" />&nbsp;&nbsp;<label
								name="template.appr.end.date" class="set"
								id="template.appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.end.date")%></label>
							: <s:property value="endDate" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="template.name" id="template.name"
								ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="templateName" /></td>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="group.name" class="set" id="group.name"
								ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="groupName" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="section.name" class="set" id="section.name"
								ondblclick="callShowDiv(this);"><%=label.get("section.name")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:hidden
								name="sectionId" /><s:textfield name="sectionName" size="20"
								readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'SectionMapping_f9Section.action')">
							<td width="25%" colspan="1" height="20" class="formtext"></td>
							<td width="25%" colspan="1" height="20"></td>
						</tr>
					</table>
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
								name="parameter" class="set" id="parameter"
								ondblclick="callShowDiv(this);"><%=label.get("parameter")%></label>
							<font color="red">*</font> :</td>
							<td width="50%" colspan="2" height="20"><s:hidden
								name="parameterId" /><s:textarea name="parameter" cols="50"
								rows="2" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'SectionMapping_f9Parameter.action')">
							<td width="25%" colspan="1" height="20"><s:hidden
								name="category" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="weightage" class="set" id="weightage"
								ondblclick="callShowDiv(this);"><%=label.get("weightage")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="weightage" size="20"
								onkeypress=" return numbersWithDot();" maxlength="6" /></td>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="question.order" class="set" id="question.order"
								ondblclick="callShowDiv(this);"><%=label.get("question.order")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:select
								theme="simple" name="questionOrder" cssStyle="width:130"
								list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10',
							'11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20'}" /></td>
						</tr>
						<tr height="30">
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="applicable.phase" class="set" id="applicable.phase"
								ondblclick="callShowDiv(this);"><%=label.get("applicable.phase")%></label>
							<font color="red">*</font> :</td>
							<td width="75%" colspan="3" height="20" class="formtext">
							<%
							try {
							%> <%
 phaseList = (Object[][]) request.getAttribute("phaseList");
 %>
							<%
							if (phaseList != null && phaseList.length != 0) {
							%> <%
 for (int l = 0; l < phaseList.length; l++) {
 %> <input
								type="checkbox" id="phaseCode<%=l%>" name="phaseCode"
								value="<%=""+phaseList[l][0] %>" /> <%=String.valueOf(phaseList[l][1])%>
							<%
							}							
 } 
 		Object[][] checkFlag = (Object[][]) request
 		.getAttribute("checkFlag");
 %>
							<%
							System.out.println("checkFlaglength==" + checkFlag.length);
							%> <%
 for (int k = 0; k < checkFlag.length; k++) {
 %> <%
 if (String.valueOf(checkFlag[k][0]).equals("Y")) {
 %>

							<script>
				document.getElementById('phaseCode<%=k%>').checked =true;
		</script> <%
 }
 %> <%
 }
 %> <%
 } catch (Exception e) {
 %> <%
 	e.printStackTrace();
 	}
 %> <input
								type="hidden" name="phaseCount" id="phaseCount"
								value="<%=phaseList.length%>" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="4" height="30" align="center"
								class="formtext"><input type="button" class="add"
								value="  Add" onclick="return callAdd();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td colspan="6"><strong class="forminnerhead"><label
								class="set" id="list" name="list"
								ondblclick="callShowDiv(this);">Question List</label></strong></td>
						</tr>
						<tr class="td_bottom_border">
							<td width="5%" class="formth" nowrap="nowrap"><label
								name="section.sr.no" class="set" id="section.sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("section.sr.no")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label
								name="section.name" class="set" id="section.name1"
								ondblclick="callShowDiv(this);"><%=label.get("section.name")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label
								name="parameter" class="set" id="parameter1"
								ondblclick="callShowDiv(this);"><%=label.get("parameter")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label
								name="weightage" class="set" id="weightage1"
								ondblclick="callShowDiv(this);"><%=label.get("weightage")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label
								name="question.order" class="set" id="question.order1"
								ondblclick="callShowDiv(this);"><%=label.get("question.order")%></label></td>
							<%
							for (int l = 0; l < phaseList.length; l++) {
							%>
							<td width="10%" class="formth" nowrap="nowrap"><%=String.valueOf(phaseList[l][1])%>
							</td>
							<%
							}
							%>
							<td width="10%" class="formth" nowrap="nowrap"><input
								type="button" value="   Remove" class="del"
								onclick="callRemove();"></td>
						</tr>
						<%
						int i = 0;
						%>
						<s:iterator value="sectionList">
							<%
										Object checkedPhaseList[][] = (Object[][]) request
										.getAttribute("checkedPhaseList" + i);
							%>
							<tr class="sortableTD">
								<td class="sortableTD" width="5%"><%=i + 1%></td>
								<td class="sortableTD" width="5%"><s:property
									value="sectionNameList" /><s:hidden name="sectionNameList"></s:hidden><s:hidden
									name="sectionIdList" id='<%="sectionId"+i%>'></s:hidden></td>
								<td class="sortableTD" width="50%"><s:property
									value="parameterList" /><s:hidden name="parameterList" /><s:hidden
									name="parameterIdList" id='<%="parameterIdList"+i%>' /></td>
								<td class="sortableTD" width="5%"><s:property
									value="weightageList" /><s:hidden name="weightageList" /></td>
								<td class="sortableTD" width="5%"><s:property
									value="questionOrderList" /><s:hidden name="questionOrderList"
									id='<%="questionOrder"+i%>' /></td>
								<%
										if (checkedPhaseList != null) {
										System.out.println("checkedPhaseList length==="
										+ checkedPhaseList.length);

										for (int l = 0; l < checkedPhaseList.length; l++) {
								%>
								<td width="10%" class="sortableTD" nowrap="nowrap"><input
									type="hidden" name="row<%=i%>"
									value="<%=String.valueOf(checkedPhaseList[l][0]) %>" /> <%=String.valueOf(checkedPhaseList[l][0])%><input
									type="hidden" name="value<%=i%>"
									value="<%=String.valueOf(checkedPhaseList[l][0])+ String.valueOf(phaseList[l][0]) %>" />
								</td>

								<%
									}
									}
								%>
								<td class="sortableTD" width="5%"><input type="checkbox"
									id="<%="removeSection"+i%>" name="removeSection"></td>
							</tr>
							<%
								request.setAttribute("checkedPhaseList" + i, checkedPhaseList);
								i++;
							%>
						</s:iterator>
						<input type="hidden" name="count" id="count" value="<%=i%>" />
						<s:hidden name="paraId" />
					</table>

					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function callRemove(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("removeSection"+j).checked){
						counter += j+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one record to remove.");
			return false;
			}
		var conf=confirm("Do you really want to remove these record ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
			  		document.getElementById("paraFrm_addQuestionFlag").value = "true";
					document.getElementById("paraFrm").action="SectionMapping_removeMultiple.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
function backFun(){
	document.getElementById("paraFrm").action="SectionMapping_input.action";
				document.getElementById("paraFrm").submit();
}

function saveFun(){
	var count = document.getElementById("count").value;
	/*if(count=="0"){
					alert("Please add atleast one Section in list");
					return false;
				}*/
				document.getElementById("paraFrm").action="SectionMapping_saveSectionQuestion.action";
				document.getElementById("paraFrm").submit();
}

function callAdd(){
		var countCheck = document.getElementById("count").value;
		var fields=["paraFrm_sectionName","paraFrm_parameter","paraFrm_weightage"];
		var labels=[document.getElementById("section.name").innerHTML,document.getElementById("parameter").innerHTML,
		document.getElementById("weightage").innerHTML];
		var types=["select","select","enter"];		
		if(!(checkMandatory(fields,labels,types))){
			return false;
		}
		if(isNaN(document.getElementById("paraFrm_weightage").value)) {
	 // if(!amount.match(amountpat)) { 
	   		alert("Only numbers are allowed in "+document.getElementById("weightage").innerHTML+" field.");
		 	document.getElementById('paraFrm_weightage').focus();
			return false;
		}		
		var phaseCount = document.getElementById("phaseCount").value ;
		var checked = false;
		for(j=0;j< phaseCount;j++){
				if(document.getElementById("phaseCode"+j).checked){
						checked= true;
						break ;
				}
		}
		if(!checked){
				alert("Please select atleast one "+document.getElementById("applicable.phase").innerHTML);
				return false;
		}		
		var count = document.getElementById("count").value;
		if(count!="0"){
			var sectionId= document.getElementById("paraFrm_sectionId").value;
			var questionOrder = document.getElementById("paraFrm_questionOrder").value;
			var questionId = document.getElementById("paraFrm_parameterId").value;		
				for (i=0;i<count;i++){
					var sectionIdList =document.getElementById("sectionId"+i).value;
					var questionOrderList = document.getElementById("questionOrder"+i).value;
					var questionIdList = document.getElementById("parameterIdList"+i).value;					
					if(sectionIdList == sectionId && questionIdList==questionId){
						alert("This "+document.getElementById("parameter").innerHTML+" is already added for this "+document.getElementById("section.name").innerHTML);
						return false;					
					}					
					if(sectionIdList == sectionId && questionOrderList==questionOrder){
						alert(document.getElementById("question.order").innerHTML +" "+ questionOrder+" is already added for this "+document.getElementById("section.name").innerHTML);
						return false;					
					}
				}
			}
			document.getElementById("paraFrm_addQuestionFlag").value="true";
			document.getElementById("paraFrm").action ="SectionMapping_addQuestion.action";
			document.getElementById("paraFrm").submit();
		}
</script>