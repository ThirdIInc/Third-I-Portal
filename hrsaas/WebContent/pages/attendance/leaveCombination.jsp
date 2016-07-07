<%--Bhushan Dasare--%><%--Oct 4, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" title="default-theme" href="../pages/common/css/default/default.css" />

<s:form action="AttendanceSettings" id="paraFrm" validate="true" theme="simple" target="main">
	<table width="100%">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td><strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Leave List</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>	
						<s:hidden name="leaveCodeHidNext"/><s:hidden name="leaveAbbrHidNext"/>						
						<td align="center" width="60%" class="formth">Leave Name</td>
						<td align="center" width="20%" class="formth">Select</td>
						<td align="center" width="20%" class="formth">Priority</td>
					</tr>					
					<%int i = 1;%>
					<%!int j = 0;%>
					<s:iterator value="leaveDataList" >
						<tr>		
							<input type="hidden" name="leaveCode" id="leaveCode<%=i%>" value='<s:property value="leaveCode"/>'/>
							<input type="hidden" name="leaveAbbr" id="leaveAbbr<%=i%>" value='<s:property value="leaveAbbr"/>'/>
							<td class="border2">
								<input type="hidden" name="leaveName" value='<s:property value="leaveName"/>' id="leaveName<%=i%>"/>
								<s:property value="leaveName"/>
							</td>
							<td align="center" class="border2">
								<input type="checkbox"  name="check" id="check<%=i%>" value='<s:property value="check"/>'/>	
							</td>
							<td align="center" class="border2">
								<input type="button" class="shuffleUp" onclick="setLeavePriority(<%=i %>, 'upWard')"/>
								<input type="button" class="shuffleDown" onclick="setLeavePriority(<%=i %>, 'downWard')"/>
							</td>
						</tr>
						<%i++;%>
					</s:iterator>						
					<%j = i;%>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<s:hidden name="srNo"/>
				<input type="button" class="token" name="Ok" value="   OK   " onclick="selectLeave();"/>
			</td>
		</tr>		
	</table>		
</s:form>

<script>

var leaveCode = document.getElementById('paraFrm_leaveCodeHidNext').value;
var leaveAbbr = document.getElementById('paraFrm_leaveAbbrHidNext').value;

function selectLeave(){
	var count = '<%=j%>'; 
	var isChecked = "";
	var selectedLeaveCode = "";
	var selectedLeaveAbbr = "";
	
	try{
   		for(var i=1; i<count; i++){
   			isChecked = document.getElementById('check'+i).checked;
   		
   			if(isChecked == true){
	   			selectedLeaveCode += document.getElementById('leaveCode'+i).value+",";
	   			selectedLeaveAbbr += document.getElementById('leaveAbbr'+i).value+",";
   			}
   		}
	   	selectedLeaveCode = selectedLeaveCode.substring(0, selectedLeaveCode.length-1);
	    selectedLeaveAbbr = selectedLeaveAbbr.substring(0, selectedLeaveAbbr.length-1);
	   
		opener.document.getElementById('paraFrm_'+leaveCode).value = selectedLeaveCode;
		opener.document.getElementById('paraFrm_'+leaveAbbr).value = selectedLeaveAbbr;
	}
	catch(e){
   		alert(e);
   	}
	window.close();
}

function setInitialValue(){
	 var count  = '<%=j%>'; 
     var oldvalue = opener.document.getElementById('paraFrm_'+leaveAbbr).value;
     fieldList  = oldvalue.split(",");
     
     for(k = 1; k <= count; k++){
     	for(i=0; i < fieldList.length;i++) {
      		if(document.getElementById('leaveAbbr'+k).value == fieldList[i]){
      			document.getElementById('check'+k).checked = true;
     		 }
  		}
     }
}

function setLeavePriority(code, buttonType){
	document.getElementById('paraFrm_srNo').value = code;
	
	if(buttonType == 'upWard'){
		document.getElementById("paraFrm").target = '';
		document.getElementById("paraFrm").action = "AttendanceSettings_setLeavePriority.action?type=up";
		document.getElementById("paraFrm").submit();
	}
	
	if(buttonType == 'downWard'){
		document.getElementById("paraFrm").target = '';
		document.getElementById("paraFrm").action = "AttendanceSettings_setLeavePriority.action?type=down";
		document.getElementById("paraFrm").submit();
	}
}
setInitialValue();
</script>
