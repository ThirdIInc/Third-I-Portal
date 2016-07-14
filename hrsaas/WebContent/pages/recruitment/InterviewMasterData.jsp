<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="InterviewAssessMasterAction" validate="true"	id="paraFrm"  theme="simple">
	<s:hidden name="groupCode" />
	<s:hidden name="parameterCode" />
	<s:hidden name="myPage" id="myPage"/>
	<s:hidden name="paraCode"/>
	<s:hidden name="parameterListLength"></s:hidden>
	<table width="100%" align="right" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview
					Assessment Group</strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" height="22"><label class="set"
						name="groupNameLabel" id="groupNameLabel"
						ondblclick="callShowDiv(this);"><%=label.get("groupNameLabel")%></label>
					:</td>
					<td width="80%"><s:property value="groupName" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="20%"><label name="descriptionLabel" class="set"
						id="descriptionLabel" ondblclick="callShowDiv(this);"><%=label.get("descriptionLabel")%>
					</label>:</td>
					<td width="80%"><s:textarea name="groupDescription"
						disabled="true" cols="68" rows="3" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_groupDescription','descriptionLabel','readonly','200','200');">
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="20%" height="22"><label class="set" name="paraName"
						id="paraName" ondblclick="callShowDiv(this);"><%=label.get("paraname")%></label>
					<font color="red">*</font> &nbsp;:</td>
					<s:hidden name="parameterCode"/>
					<td width="80%"><s:textfield size="25" theme="simple"
						maxlength="50" name="paraName"
						onkeypress="return allCharacters();" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="20%"><label name="paraDes" class="set" id="paraDes"
						ondblclick="callShowDiv(this);"><%=label.get("parades")%>
					</label>:<font color="red">*</font></td>
					<td width="80%" height="22"><s:textarea
						name="paraDes" cols="68" rows="3"
						onkeyup="callLength();" /><img src="../pages/images/zoomin.gif"
						height="12" align="absmiddle" id='ctrlHide' width="12"
						theme="simple"
						onclick="javascript:callWindow('paraFrm_paraDes','parades','','500','500');">
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="isActive"
						id="isActive" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td width="80%" height="22"><s:select cssStyle="width:170"
						list=" #{'Y':'Yes','N':'No'}" name="paraIsActive" id="paraIsActive"/></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="button"
						value="Add Parameter" class="token" onclick="addParameterToList()">
					</td>
				</tr>
			</table>
			</td>
		<tr>
		<tr>
			<td colspan="3">
			<table border="0" width="100%" cellpadding="3" cellspacing="3">
				<tr>
					<td class="formth" align="center" width="5%"><label
						class="set" name="serial.no" id="serial.no"
						ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
					</td>
					<td class="formth" align="center" width="20%"><label
						class="set" name="paraname" id="paraname"
						ondblclick="callShowDiv(this);"><%=label.get("paraname")%></label>
					</td>
					<td class="formth" align="center" width="70%"><label
						class="set" name="parades" id="parades"
						ondblclick="callShowDiv(this);"><%=label.get("parades")%></label>
					</td>
					<td class="formth" align="center" width="5%"><label
						class="set" name="is.active" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					</td>
				</tr>
				<%
				int count = 0;
				%>
				<%!int d = 0;%>
				<%
					int i = 0;
					int cn = 0;
				%>

				<s:if test="dataPresent">
					<s:iterator value="parameterList">
					<s:hidden name="parameterIsActiveItr" />
					<s:hidden name="parameterDescriptionItr" />
					<s:hidden name="parameterNameItr" />  
						<s:hidden name="parameterCodeItr" />
						<tr <%if(count%2==0){
										%> class="tableCell1" <%}else{%>
							class="tableCell2" <%	}count++; %>
							onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
							ondblclick="javascript:callForEdit(<%=++cn%>,'<s:property
								value="parameterNameItr" />','<s:property
								value="parameterDescriptionItr" />','<s:property
								value="parameterIsActiveItr" />');">
							<s:hidden name="srNo" />
							<td align="right"><%=cn%> <%
 ++i;
 %>
							</td>
							<s:hidden value="%{parameterCodeItr}"
								id='<%="parameterCodeItr" + i%>'></s:hidden>
							<script type="text/javascript">
											records[<%=i%>] = document.getElementById('parameterCodeItr' + '<%=i%>').value;
										</script>
							<td><s:property
								value="parameterNameItr" /></td>
							<td><s:property
								value="parameterDescriptionItr" /></td>
							<td> <s:property
								value="parameterIsActiveItr" /></td>
						</tr>
					</s:iterator>
					<%
					d = i;
					%>
				</s:if>
				<s:else>
					<tr>
						<td colspan="4" align="center"><font color="red"><b>No
						data to display</b></font></td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function saveFun() {
	var paraName=document.getElementById('paraFrm_paraName').value;
	if(!paraName==""){
	alert("Please click on Add Parameter!");
	return false;
	}
	var con = confirm("Do you really want to add these parameters?");
	if (con) {
		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_saveParameters.action';
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}
	
function resetFun() {
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_resetParameterData.action';
    document.getElementById('paraFrm').submit(); 
}
	
function backFun() {
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_backToGroupList.action';
	document.getElementById('paraFrm').submit();
}
	
function callLength(){ 
	var cmt = document.getElementById('paraFrm_paraDes').value;
	var remain = 500 - eval(cmt.length);
	if(eval(remain)< 0){
		document.getElementById('paraFrm_paraDes').style.background = '#FFFF99';
	} else {
	 	document.getElementById('paraFrm_paraDes').style.background = '#FFFFFF';
	} 	
}

function addParameterToList() {
	var para = trim(document.getElementById('paraFrm_paraName').value); 
    if(para==""){
	  alert("Please enter "+document.getElementById('paraName').innerHTML.toLowerCase());
	  document.getElementById('paraFrm_paraName').focus();
	  return false;
	}

 	var parameterDesc = trim(document.getElementById('paraFrm_paraDes').value);
	if(parameterDesc=="") {
		alert("Please enter "+document.getElementById('paraDes').innerHTML.toLowerCase());
		document.getElementById('paraFrm_paraDes').focus();
		 return false;
	} 
	
	if (parameterDesc.length > 500) {
		alert("Only 500 characters are allowed.");
		document.getElementById('paraFrm_paraDes').focus();
		return false;
	}
	document.getElementById("paraFrm").target = '_self';
	document.getElementById("paraFrm").action = 'InterviewAssessMasterAction_addParameterToList.action';
	document.getElementById("paraFrm").submit();
} 

// @AUTHOR Prajakta B
//Function to edit parameter record
function callForEdit(id,name,desc,isActive){
	
	document.getElementById('paraFrm_paraCode').value=id;
	callButton('NA', 'Y', 2);
  	document.getElementById('paraFrm_paraName').value=name;
  	document.getElementById('paraFrm_paraDes').value=desc;
  	if(isActive=='Yes'){
  	document.getElementById('paraIsActive').value='Y';
  	}else{
  	document.getElementById('paraIsActive').value='N';
  	}
  	
}

</script>
