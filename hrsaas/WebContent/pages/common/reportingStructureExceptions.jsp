<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.bean.common.ReportingStructure"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStr" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">
					Reporting Structure For <%
						String structureType = (String) request
									.getAttribute("structureType");
							out.println(structureType);
					%> </strong></td>
					<td width="3%" valign="top" class="txt">&nbsp;</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="structureFor" value="%{reportingType}"/>
		<s:hidden name="hiddenStructure" value="<%= structureType%>"/>
		<tr>
			<td width="100%" colspan="6"><strong class="formhead">
			Transfer Manager Reporting: </strong></td>
		</tr>

		<tr>
			<td width="100%" colspan="6">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="15%">From:</td>
					<td width="10%"><s:hidden theme="simple" name="fromEmpCode" />
					<s:textfield theme="simple" name="fromEmpToken" size="11"
						readonly="true" /></td>
					<td width="40%" colspan="2"><s:textfield theme="simple"
						name="fromEmpName" size="60" maxlength="30" readonly="true" /></td>
					<td width="35%" colspan="2"><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'ReportingStr_f9fromEmployee.action'); "></td>
				</tr>
				<tr>
					<td width="15%">To:</td>
					<td width="10%"><s:hidden theme="simple" name="toEmpCode" />
					<s:textfield theme="simple" name="toEmpToken" size="11"
						readonly="true" /></td>
					<td width="40%" colspan="2"><s:textfield theme="simple"
						name="toEmpName" size="60" maxlength="30" readonly="true" /></td>
					<td width="35%" colspan="2"><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'ReportingStr_f9toEmployee.action'); "></td>
				</tr>
				<tr>
					<td colspan="6"><input type="button" class="add"
						theme="simple" value=" Add Exceptions"
						onclick="return addExceptions();" /></td>
				</tr>

				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td class="formth" width="6%" height="22" valign="top"><label
								name="reporting.srno" class="set" id="reporting.srno1"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.srno")%></label></td>
							<td class="formth" width="9%" height="22" valign="top"><label
								name="employee" class="set" id="employee1"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td class="formth" width="19%" height="22" valign="top">Reporting
							To</td>

							<td class="formth" width="6%" height="22" valign="top"><label
								name="reporting.edit" class="set" id="reporting.edit1"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.edit")%></label></td>
							</td>
							<td class="formth" width="7%" height="22" valign="top"><label
								name="reporting.delete" class="set" id="reporting.delete1"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.delete")%></label></td>
							</td>

						</tr>

						<%
							int i = 1;
								int count = 0;
						%>
						<s:iterator value="exceptionEmpList" status="stat">
							<tr>
								<s:hidden name="fromEmpIdIt" id="<%= "fromEmpIdIt"+i %>" />
								<s:hidden name="fromEmpTokenIt" id="<%= "fromEmpTokenIt"+i %>" />
								<s:hidden name="fromEmpNameIt" id="<%= "fromEmpNameIt"+i %>" />
								<s:hidden name="excepSrNoIterator"
									id="<%= "excepSrNoIterator"+i %>" />
								<s:hidden name="toEmpIdIt" id="<%= "toEmpIdIt"+i %>" />
								<s:hidden name="toEmpTokenIt" id="<%= "toEmpTokenIt"+i %>" />
								<s:hidden name="toEmpNameIt" id="<%= "toEmpNameIt"+i %>" />

								<td width="6%" class="sortableTD"><s:property
									value="excepSrNoIterator" />&nbsp;</td>
								<td width="19%" class="sortableTD"><s:property
									value="fromEmpNameIt" />&nbsp;</td>
								<td width="15%" class="sortableTD"><s:property
									value="toEmpNameIt" />&nbsp;</td>
								<td width="6%" class="sortableTD"><input type="button"
									class="edit" value="   Edit" align="bottom"
									onclick="editEmployee(<%=i %>, '<s:property value="fromEmpIdIt"/>', '<s:property value="fromEmpTokenIt"/>',
															'<s:property value="fromEmpNameIt"/>', '<s:property value="toEmpIdIt"/>', 
															'<s:property value="toEmpTokenIt"/>', '<s:property value="toEmpNameIt"/>')" />&nbsp;</td>
								<td width="7%" class="sortableTD"><input type="button"
									class="delete" value="   Delete" align="bottom"
									onclick="deleteEmployee(<%=i %>, '<s:property value="fromEmpIdIt"/>')" />&nbsp;</td>

							</tr>
							<%
								i++;
										count++;
							%>
						</s:iterator>
						<s:hidden name="ReportingStr.Structure" />
						<s:hidden name="excepSrNo" />
						<input type="hidden" name="excepRowNum" id="excepRowNum"
							value="<%=count %>" />
					</table>
					</td>
				</tr>


			</table>

			</td>
		</tr>
		<s:hidden name = "fromEmpIdValues" /><s:hidden name = "toEmpIdValues" />
		<tr>
			<td width="100%" colspan="6" align="center"><input type="button" class="cancel"
						value=" Close " onclick="return callClose();" /></td>
		</tr>

	</table>
</s:form>

<script>
function addExceptions(){
	try{
	var fromId = trim(document.getElementById('paraFrm_fromEmpCode').value);
	var toId = trim(document.getElementById('paraFrm_toEmpCode').value);
	/*if(!document.getElementById('managerRecord').checked){
		alert("Please check Apply Reporting Manager under Official Details as reporting hierarchy");
		return false;
	}*/
	if(fromId == ""){
		alert("Please select employee");
		document.getElementById('paraFrm_fromEmpName').focus();
		return false;
	}
	if(toId == ""){
		alert("Please select reporting to");
		document.getElementById('paraFrm_toEmpName').focus();
		return false;
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "ReportingStr_addExceptions.action";
	document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}

function callClose(){
	try{
		var count = document.getElementById('excepRowNum').value;
		var finalValueFrom="";
		var finalValueTo="";
		for(var i=1; i<=count;i++){
			finalValueFrom += document.getElementById('fromEmpIdIt'+i).value+",";
			finalValueTo   += document.getElementById('toEmpIdIt'+i).value+",";
		}
		finalValueFrom = finalValueFrom.substring(0, finalValueFrom.length-1);
		finalValueTo   = finalValueTo.substring(0, finalValueTo.length-1);
		opener.document.getElementById('paraFrm_fromEmpIdValues').value = finalValueFrom;
		opener.document.getElementById('paraFrm_toEmpIdValues').value = finalValueTo;
	}catch(e){alert(e);}
	window.close();
	document.getElementById('paraFrm').target = "main";
}

function editEmployee(srNo, fromEmpId, fromEmpToken, fromEmpName, toEmpId, toEmpToken, toEmpName){
	document.getElementById('paraFrm_excepSrNo').value = srNo;
	document.getElementById('paraFrm_fromEmpCode').value = fromEmpId;
	document.getElementById('paraFrm_fromEmpToken').value = fromEmpToken;
	document.getElementById('paraFrm_fromEmpName').value = fromEmpName;
	document.getElementById('paraFrm_toEmpCode').value = toEmpId;
	document.getElementById('paraFrm_toEmpToken').value = toEmpToken;
	document.getElementById('paraFrm_toEmpName').value = toEmpName;
}

function deleteEmployee(srNo, id){
	document.getElementById('paraFrm_excepSrNo').value = srNo;
	
	 var conf=confirm("Do you really want to delete this record ?");
  		if(conf){
  			document.getElementById("paraFrm").action="ReportingStr_deleteExceptionEmp.action";
			document.getElementById('paraFrm_fromEmpCode').value=id;
		    document.getElementById("paraFrm").submit();
  			return true;
  		}
	  	else{
	  		 return false;
	  	}
	    return true;
}
</script>