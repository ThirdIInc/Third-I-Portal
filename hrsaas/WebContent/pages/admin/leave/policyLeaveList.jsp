<!-- @author: Reeba Joseph @date: 06 OCT 2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeavePolicy" id="paraFrm" validate="true" theme="simple"
	target="main">
	<table width="100%">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					List</strong></td>
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
			<table width="100%" class="formbg">
				<tr>
					<s:hidden name="leaveCodeHidNext" />
					<s:hidden name="leaveAbbrHidNext" />
					<td align="center" width="60%" class="formth">Leave Name</td>
					<td align="center" width="20%" class="formth">Select</td>
					<td align="center" width="20%" class="formth">Priority</td>
				</tr>
				<%int i = 1;%>
				<%!int j = 0;%>
				<s:iterator value="leaveDataList">
					<tr>
						<input type="hidden" name="leaveAbbrv" id="leaveAbbrv<%=i%>"
							value='<s:property value="leaveAbbrv"/>' />
						<td class="border2"><input type="hidden" name="leaveNamev"
							value='<s:property value="leaveNamev"/>' id="leaveNamev<%=i%>" /> <s:property
							value="leaveNamev" /><input type="hidden" name="leaveCodev" id="leaveCodev<%=i%>"
							value='<s:property value="leaveCodev"/>' /></td>
						<td align="center" class="border2"><!-- <input type="checkbox"
							name="check" id="check<%=i%>" value='<s:property value="check"/>' /> -->
							<s:checkbox name="check" id="<%="checks"+i%>" onclick="callCheck();"/>
							<input type="hidden" id="check<%=i%>" value='<s:property value="check"/>' />
						<s:hidden name="checkFlag" id="checkFlag<%=i%>"/>
						</td>
						
						<td align="center" class="border2">
						<s:if test="checkFlag"></s:if><s:else>
						<input type="button"
							class="shuffleUp" onclick="setLeavePriority(<%=i %>, 'upWard')" />
						<input type="button" class="shuffleDown"
							onclick="setLeavePriority(<%=i %>, 'downWard')" /></s:else>
						</td>	
						
					</tr>
					<%i++;%>
				</s:iterator>
				<%j = i;%>
			</table>
			</td>
		</tr>
		<tr>
			<td><s:hidden name="srNo" /> <input type="button" class="token"
				name="Ok" value="   OK   " onclick="selectLeave();" /></td>
		</tr>
	</table>
</s:form>

<script>

var leaveCodev = document.getElementById('paraFrm_leaveCodeHidNext').value;
var leaveAbbrv = document.getElementById('paraFrm_leaveAbbrHidNext').value;

function callCheck(){
	try{
	var count = '<%=j%>'; 
	var isChecked = "";
	for(var i=1; i<count; i++){
		//alert(document.getElementById('checks'+i).checked);
		if(document.getElementById('checks'+i).checked){
			document.getElementById('check'+i).value="true";
		}else{
			document.getElementById('check'+i).value="false";
		}
	}
	}catch(e){
		alert(e);
	}
}

function selectLeave(){
	var count = '<%=j%>'; 
	var isChecked = "";
	var selectedLeaveCode = "";
	var selectedLeaveAbbr = "";
	
	try{
   		for(var i=1; i<count; i++){
   			isChecked = trim(document.getElementById('check'+i).value);
   			if(isChecked == "true"){
	   			selectedLeaveCode += document.getElementById('leaveCodev'+i).value+",";
	   			selectedLeaveAbbr += document.getElementById('leaveAbbrv'+i).value+",";
   			}
   		}
	   	selectedLeaveCode = selectedLeaveCode.substring(0, selectedLeaveCode.length-1);
	    selectedLeaveAbbr = selectedLeaveAbbr.substring(0, selectedLeaveAbbr.length-1);
		
		opener.document.getElementById('paraFrm_'+leaveCodev).value = selectedLeaveCode;
		opener.document.getElementById(leaveAbbrv).value = selectedLeaveAbbr;
	}
	catch(e){
   		alert(e);
   	}
	window.close();
}

function setInitialValue(){
	 var count  = '<%=j%>'; 
     var oldvalue = opener.document.getElementById(leaveAbbrv).value;
     fieldList  = oldvalue.split(",");
     
     for(k = 1; k <= count; k++){
     	for(i=0; i < fieldList.length;i++) {
      		if(document.getElementById('leaveAbbrv'+k).value == fieldList[i]){
      			document.getElementById('check'+k).checked = true;
     		 }
  		}
     }
}

function setLeavePriority(code, buttonType){
	document.getElementById('paraFrm_srNo').value = code;
	
	if(buttonType == 'upWard'){
		document.getElementById("paraFrm").target = '';
		document.getElementById("paraFrm").action = "LeavePolicy_setLeavePriority.action?type=up";
		document.getElementById("paraFrm").submit();
	}
	
	if(buttonType == 'downWard'){
		document.getElementById("paraFrm").target = '';
		document.getElementById("paraFrm").action = "LeavePolicy_setLeavePriority.action?type=down";
		document.getElementById("paraFrm").submit();
	}
}
setInitialValue();
</script>
