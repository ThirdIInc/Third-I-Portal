<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="RehireProcess" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="applDivisionCode"/>
	<s:hidden name="applDivisionName"/>
	<s:hidden name="applBranchCode"/>
	<s:hidden name="applBranchName"/>
	<s:hidden name="applDeptCode"/>
	<s:hidden name="applDeptName"/>
	<s:hidden name="applDesgCode"/>
	<s:hidden name="applDesgName"/>
	<s:hidden name="applEmpName"/>
	<s:hidden name="applEmpCode"/>
	<s:hidden name="applEmpTypeCode"/>
	<s:hidden name="applEmpTypeName"/>
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Rehire Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="save" value='save'
				onclick="callSave();" /> <input type="button" class="back"
				value='back' onclick="callback();" /></td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="10%" nowrap="nowrap" valign="top" class="formth"><label
								class="set" id="oldemployee.id" name="oldemployee.id"
								ondblclick="callShowDiv(this);"><%=label.get("oldemployee.id")%></label></td>
							<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
								class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
								class="set" id="newemployee.id" name="newemployee.id"
								ondblclick="callShowDiv(this);"><%=label.get("newemployee.id")%></label></td>
							<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
								class="set" id="doj" name="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
							<td width="15%" nowrap="nowrap" valign="top" class="formth">Select</td>
						</tr>
						<%
							int i = 0;
							int s = 1;
						%>
						<%!int p = 0, t = 0, count = 0;%>
						<s:iterator value="processList">
							<tr class="sortableTD" bgcolor='<s:property value="colour" />'>
								<td class="sortableTD" width="10%" nowrap="nowrap"><input
									type="hidden" name="processEmpId" id="processEmpId<%=i%>"
									value="<s:property value="processEmpId" />" /><s:property
									value="oldEmpToken" /><input type="hidden" name="oldEmpToken"
									id="oldEmpToken<%=i%>"
									value="<s:property value="oldEmpToken" />" /></td>
								<td class="sortableTD" width="25%" nowrap="nowrap"><s:property
									value="processEmpName" /><input type="hidden"
									name="processEmpName" id="processEmpName<%=i%>"
									value="<s:property value="processEmpName" />" /></td>
								<td class="sortableTD" width="20%" nowrap="nowrap"><input
									type="text" size="10" name="newEmpToken" id="newEmpToken<%=i%>"
									value="<s:property value="newEmpToken" />" />
									
								 
										<input type="hidden" name="newEmpTokenHidden" id="newEmpTokenHidden<%=i%>"
									
									value="<s:property value="newEmpTokenHidden" />" />								
									
									<!-- 	<s:textfield name="newEmpTokenHidden" id="newEmpTokenHidden ">
									</s:textfield> -->
								
								
									
									<input class="token" type="button" id="genButton<%=i%>" value='Generate Emp Id' onclick="generateEmpToken(<%=i%>)"; /></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><input
									type="text" size="10" name="processDoj" id="processDoj<%=i%>"  onkeypress="return numbersWithHiphen();"
									value="<s:property value="processDoj" />" maxlength="12" /><a
									href="javascript:NewCal('processDoj<%=i%>','DDMMYYYY');"> <img
									src="../pages/images/Date.gif" class="iconImage" height="16"
									align="absmiddle" width="16"> </a></td>
								<td class="sortabletd" width="10%" align="center">
								<table width="100%">
									<tr>
										<td nowrap="nowrap"><input type="radio"
											name="<%="sepPay"+i %>" id="<%="updateRadio"+i %>" value="Y"
											onclick="callRadio(<%=i%>,'U');" />Update Record</td>
									</tr>
									<tr>
										<td nowrap="nowrap"><input type="radio"
											name="<%="sepPay"+i %>" id="<%="insertRadio"+i %>" value="N"
											onclick="callRadio(<%=i%>,'N');" />Create New Record</td>
									</tr>
								</table>
								<input type="hidden" name="updOrInsert" id="updOrInsert<%=i%>"
									value="<s:property value="updOrInsert" />" />
							</tr>
							<%
								i++;
								s++;
								p++;
							%>
						</s:iterator>
						<%
							t = p;
							p = 0;
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="save" value='save'
				onclick="callSave();" /> <input type="button" class="back"
				value='back' onclick="callback();" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
callOnload();
function callOnload(){
try{
	var totalRows = <%=t%>;
	for(var i=0;i<totalRows;i++){
		var updateOrIns =document.getElementById('updOrInsert'+i).value;
		document.getElementById(updateOrIns+"Radio"+i).checked=true;
		if(document.getElementById('newEmpToken'+i).value == 'Field is Disabled')
		{
			document.getElementById('newEmpToken'+i).disabled = true;
			document.getElementById('genButton'+i).disabled = true;
		}
	}
	}catch(e){alert(e);}
} 
function callRadio(id,radioType){
//alert(1);
//alert(radioType);
	if(radioType == 'U'){
		document.getElementById('newEmpToken'+id).value = 'Field is Disabled';
		document.getElementById('newEmpToken'+id).disabled = true;
		document.getElementById('genButton'+id).disabled = true;
		document.getElementById('updOrInsert'+id).value = 'update';
	} //end of if
	else{
		document.getElementById('newEmpToken'+id).value = '';
		document.getElementById('newEmpToken'+id).disabled = false;
		document.getElementById('genButton'+id).disabled = false;
		document.getElementById('updOrInsert'+id).value = 'insert';
	} //end of else
} //end of callRadio method

function callSave(){
try{
var totalRows = <%=t%>;
var count = 0;
//alert(totalRows);
	for(var i=0;i<totalRows;i++){
	var newempToken=document.getElementById('newEmpToken'+i).value;
		if(trim(newempToken) ==''){
			alert("New employee id field cannot be left blank");
			return false;
		} //end of if
		if(document.getElementById('processDoj'+i).value ==''){
			alert("Please enter/select Date of Joining");
			return false;
		} //end of if
		if(document.getElementById('processDoj'+i).value !=''){
			var check= validateDate('processDoj'+i,'doj' );
			if(!check){
			return false;
		}
		} //end of if
		if((i<eval(totalRows)-1)){
			//alert(1);
			for(var j=i+1;j<totalRows;j++){
			//alert("Hello : -----"+document.getElementById('newEmpToken'+i).value);
			//alert("Hi : --------"+document.getElementById('newEmpToken'+j).value);
				if(document.getElementById('newEmpToken'+i).value == document.getElementById('newEmpToken'+j).value){
					if(document.getElementById('newEmpToken'+i).value!='Field is Disabled' && document.getElementById('newEmpToken'+j).value !='Field is Disabled')
					{
						alert("Employee id entered should be unique");
						document.getElementById('newEmpToken'+j).focus();
						return false;
					}
				} //end of if
			}  //end of loop
		} //end of if
	} //end of loop
	document.getElementById('paraFrm').action ="RehireProcess_saveProcess.action";
 	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
   	}catch(e){alert(e);}
} //end of callSave method
function generateEmpToken(rowId){
			try{
				document.getElementById('newEmpToken'+rowId).value=document.getElementById('newEmpTokenHidden'+rowId).value ;
				
			}catch(e)
			{
				alert("Error Accour "+e);
			}
	}
function callback(){
	document.getElementById('paraFrm').action ="RehireProcess_getEmployeeList.action";
 	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
}
</script>