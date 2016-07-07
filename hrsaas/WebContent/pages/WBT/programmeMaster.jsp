 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"></script>
<%
			int moduleListLength = request.getAttribute("moduleLtLength") == null ? 0
			: Integer.parseInt(request.getAttribute("moduleLtLength")
			.toString());
%>
</script>
<s:form action="ProgrammeMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="programeId" />

	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg" align="right">
		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Program
					Master </strong></td>
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
					<td width="25%" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				<tr>
					<td>
					<table width="100%" cellpadding="1" cellspacing="2" border="0">
						<tr>
							<td><input type="button" name="saveBtn" value="Save"
								onclick="javaScript:return saveFun();" /></td>
							<td><s:submit name="cancelBtn" value="Back"
								onclick="cancel();" /></td>
							<!--<td><input type="button" name="previewBtn" value="Preview" onclick="" /></td>
							-->
							<td width="90%"></td>
						</tr>
					</table>
					</td>
				</tr>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="4" cellspacing="1"
				class="formbg">
				<tr>
					<td width="20%"><label class="set" name="programe"
						id="programe" ondblclick="callShowDiv(this);"><%=label.get("programe")%></label>:<font
						color="red">*</font></td>
					<td width="20%"><s:textfield theme="simple"
						name="programeName" size="20" /></td>
					<td width="20%"></td>
					<td width="20%"></td>
				</tr>
				<tr>
					<td><label class="set" name="typeLbl" id="typeLbl"
						ondblclick="callShowDiv(this);"><%=label.get("typeLbl")%></label>:<font
						color="red">*</font></td>
					<td><label><span class="text1" /><s:select
						theme="simple" name="type" headerKey="-1"
						list="#{'R':'Training', 'T':'Test'}" onchange="typeCheck();" /></span></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr id="durTr">
					<td><label class="set" name="dur" id="dur"
						ondblclick="callShowDiv(this);"><%=label.get("dur")%></label>:</td>
					<td><s:textfield theme="simple" name="duration"
						size="15" maxlength="5" onkeypress="return numbersWithColon();" /><font
						color="blue">HH:MM</font></td>
					<td>
						<s:if test="programeId==''">
						</s:if>
						<s:else>
						<a
							href="ProgrammeMaster_setInstruction.action?programeId=<s:property value="programeId"/>&from=programedit"
							onclick="javascript:return addInstruction();"> <font
							color="blue"><u>Manage Instructions</u></font></a>
						</s:else>
					</td>
					<td></td>
				</tr>
				<tr id="daysTr">
					<td><label class="set" name="day" id="day"
						ondblclick="callShowDiv(this);"><%=label.get("day")%></label>:</td>
					<td><s:textfield theme="simple" name="days"
						size="15" maxlength="3" onkeypress="return numbersOnly();" /></td>
					<td>
						<s:if test="programeId==''">
						</s:if>
						<s:else>
						<a
							href="ProgrammeMaster_setInstruction.action?programeId=<s:property value="programeId"/>&from=programedit"
							onclick="javascript:return addInstruction();"> <font
							color="blue"><u>Manage Instructions</u></font></a>
						</s:else>
					</td>
					<td></td>
				</tr>
				
				<tr>
					<td><label class="set" name="addMod" id="addMod"
						ondblclick="callShowDiv(this);"><%=label.get("addMod")%></label>:</td>
				  <td colspan="2" nowrap="nowrap"><s:textfield theme="simple" name="module"
						size="20" readonly="true" /><s:hidden name="moduleCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ProgrammeMaster_f9Module.action');">
				    <input name="button" type="button" id="addButton"
						onclick="return addModule();" value="Add" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="set" name="active" id="active"
						ondblclick="callShowDiv(this);"><%=label.get("active")%></label>:</td>
					<s:if test="programeId==''">
						<td><s:checkbox name="isActive" id="isActive" value="true" /></td>
					</s:if>
					<s:else>
						<td><s:checkbox name="isActive" id="isActive" /></td>
					</s:else>
					<td></td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<s:if test="moduleList==null">
			</s:if>
			<s:else>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortableTD">
						<tr>
							<td><b>Modules</b></td>
						</tr>
						<tr>
							<td class="formth" align="center" width="5%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<td class="formth" align="center" width="30%"><label
								class="set" name="moduleNm" id="moduleNm"
								ondblclick="callShowDiv(this);"><%=label.get("moduleNm")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="orderNot" id="orderNot"
								ondblclick="callShowDiv(this);"><%=label.get("orderNot")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="programorder" id="programorder"
								ondblclick="callShowDiv(this);"><%=label.get("programorder")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="section" id="section"
								ondblclick="callShowDiv(this);"><%=label.get("section")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="enableContentLbl" id="enableContentLbl"
								ondblclick="callShowDiv(this);"><%=label.get("enableContentLbl")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="enableQuestion" id="enableQuestion"
								ondblclick="callShowDiv(this);"><%=label.get("enableQuestion")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="noOfAttempts" id="noOfAttempts"
								ondblclick="callShowDiv(this);"><%=label.get("noOfAttempts")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="quesManage" id="quesManage"
								ondblclick="callShowDiv(this);"><%=label.get("quesManage")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="passMarkMod" id="passMarkMod"
								ondblclick="callShowDiv(this);"><%=label.get("passMarkMod")%></label></td>
							
						</tr>
						<%!int count = 0;%>
						<%
						int i = 1;
						%>
						<s:iterator value="moduleList">
							<tr>
								<td class="sortableTD"><%=i%> <input type="hidden"
									name="SrNo" id="SrNo<%=i %>" value="<%=i%>">&nbsp; <input
									type="hidden" name="rownum" size="10" id="rownum<%=i %>"
									value="<s:property value="rownum" />"></td>
								<td class="sortableTD" align="left" id="modCodeTD<%=i%>"
									title="<s:property value="moduleNameAbbrItt" />"><input
									type="hidden" name="moduleItt" size="15" id="moduleItt"
									value="<s:property value="moduleItt" />"><s:property
									value="moduleItt" /><s:hidden name="moduleCodeItt" /></td>
								<td class="sortableTD" align="center" id="modOrderTD<%=i%>">
								<input type="hidden"
									value="<s:property value="hiddenOrderCheck"/>"
									name="hiddenOrderCheck" id="hiddenOrderCheck<%=i%>" /> <input
									type="checkbox" class="checkbox" id="order<%=i%>" name="order"
									onclick="setCheckBoxOrder('<%=i%>')" /> <script>
									if(document.getElementById('hiddenOrderCheck<%=i%>').value=='Y'){
										document.getElementById('order<%=i%>').checked =true;
									}									
									</script></td>
								<td align="center"><input type="hidden" name="moduleOrder"
									id="moduleOrder<%=i%>" value="<%=i%>" />
								<table width="100%">
									<tr width="100%">
										<td align="right" width="50%">
										<%
										if (i != 1) {
										%> <a href="javascript:void(0);" onclick="orderUp(<%=i%>,<%=i%>);"> <img border="0" 
											src="../pages/common/css/default/images/up.GIF" width="10"
											height="10"> </a>
										<%
 										}
 										%>
										</td>
										<td align="left" width="50%">
										<%
										if (i != moduleListLength) {
										%> <a href="javascript:void(0);" onclick="orderDown(<%=i%>,<%=i%>);"> <img border="0" 
											src="../pages/common/css/default/images/down.GIF" width="10"
											height="10"> </a>
										<%
 										}
 										%>
 										<% if (i == 1 && i == moduleListLength) { %>
 										-
										<%} %>
										</td>
									</tr>
								</table>
								</td>
								<td class="sortableTD" align="center" id="modManageSecTD<%=i%>">
								<s:hidden name="manageSectionItt" />
								<s:if test="manageSectionItt=='true'">
								</s:if>
								<s:else>
								<a
									href="#"
									onclick="return manageSection('<s:property value="moduleCodeItt" />','<s:property
									value="moduleItt" />');">
								<font color="blue"><u>Manage</u></font></a>
								</s:else>
								
								</td>
								<td class="sortableTD" align="center" id="modEnableContTD<%=i%>">
								<input type="hidden"
									value="<s:property value="hiddenContentCheck"/>"
									name="hiddenContentCheck" id="hiddenContentCheck<%=i%>" /> <input
									type="checkbox" class="checkbox" id="enableContent<%=i%>"
									name="enableContent" onclick="setCheckBoxContent('<%=i%>')" />
								<script>
									if(document.getElementById('hiddenContentCheck<%=i%>').value=='Y'){
									document.getElementById('enableContent<%=i%>').checked =true;
									}									
									</script></td>
								<td class="sortableTD" align="center" id="modEnableQuesTD<%=i%>"><input
									type="hidden" value="<s:property value="hiddenQuesCheck"/>"
									name="hiddenQuesCheck" id="hiddenQuesCheck<%=i%>" /> <input
									type="checkbox" class="checkbox" id="enableQues<%=i%>"
									name="enableQues" onclick="setCheckBoxQues('<%=i%>')" /> <script>
									if(document.getElementById('hiddenQuesCheck<%=i%>').value=='Y'){
									document.getElementById('enableQues<%=i%>').checked =true;
									}									
									</script></td>
								
								<td class="sortableTD" align="center" id="noOfAttemptsTD<%=i%>">
									<input type="text" name="noOfAttempts" id="noOfAttempts<%=i%>" maxlength="3"
										onkeypress="return numbersOnly();" onkeyup="setAttemptValue();" size="5"
										style="text-align: right" />
									<input type="hidden" name="noOfAttemptsHidden" id="noOfAttemptsHidden<%=i%>"
										value="<s:property value="noOfAttempts" />" />	
									<script>
										document.getElementById('noOfAttempts<%=i%>').value = document.getElementById('noOfAttemptsHidden<%=i%>').value; 
									</script>
								</td>
								<td class="sortableTD" align="center" id="modQuesTD<%=i%>">
								<a id="modQuesHyper<%=i %>"
									href="#" 
									onclick="return manageQuestion('<s:property value="moduleCodeItt" />','<s:property
																		value="moduleItt" />');">
								<font color="blue"><u>Manage</u></font></a></td>
								<td class="formth" align="center" id="modMarksTD<%=i%>"><input
									type="hidden" name="passMarksMod" size="15" id="passMarksMod"
									value="<s:property value="passMarksMod" />"><s:property
									value="passMarksMod" /></td>
								
							</tr>
							<%
							i++;
							%>
						</s:iterator>
						<%
						count = i;
						%>
					</table>
					
					</td>
				</tr>
			</table>
			</s:else>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td><input type="button" name="saveBtn" value="Save"
						onclick="javaScript:return saveFun();" /></td>
					<td><s:submit name="cancelBtn" value="Back"
						onclick="cancel();" /></td>
					<!--<td><input type="button" name="previewBtn" value="Preview" onclick="" /></td>
					-->
					<td width="90%"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!--<s:hidden name="isSaveClick" id="isSaveClick" value="N" />-->
</s:form>

<script>
function typeCheck(){
	var tp = document.getElementById('paraFrm_type').value;
	if(tp=='T'){
		document.getElementById('durTr').style.display = "";
		document.getElementById('daysTr').style.display = "none";
		if(document.getElementById('paraFrm_duration').value==null || document.getElementById('paraFrm_duration').value=="null"){
			document.getElementById('paraFrm_duration').value = "";
		}
	} else {
		document.getElementById('durTr').style.display = "none";
		document.getElementById('daysTr').style.display = "";
	}
}
typeCheck();

function setCheckBoxContent(i){
	 if(document.getElementById('enableContent'+i).checked){	  
	    document.getElementById('hiddenContentCheck'+i).value='Y';
	  }
	  else{
	   document.getElementById('hiddenContentCheck'+i).value='N';
	  }
}
  
  function setCheckBoxQues(i){
	 if(document.getElementById('enableQues'+i).checked){	  
	    document.getElementById('hiddenQuesCheck'+i).value='Y';
	   document.getElementById('modQuesHyper'+i).style.visibility = "visible";
	  }
	  else{
	   document.getElementById('hiddenQuesCheck'+i).value='N';
	   document.getElementById('modQuesHyper'+i).style.visibility = "hidden";
	  }
}
function setMngQuesTd(){
	var ele = document.getElementsByName("enableQues");
	for(i=1;i<=ele.length;i++){
		if(document.getElementById('enableQues'+i).checked){	  
			document.getElementById('hiddenQuesCheck'+i).value='Y';
			document.getElementById('modQuesHyper'+i).style.visibility = "visible";
		} else {
			document.getElementById('hiddenQuesCheck'+i).value='N';
			document.getElementById('modQuesHyper'+i).style.visibility = "hidden";
		}
	}
}
setMngQuesTd();
 function setCheckBoxOrder(i){
 	//alert("i----"+i);
	   if(document.getElementById('order'+i).checked){	  
	   //alert( document.getElementById('hiddenOrderCheck'+i).value);
	    document.getElementById('hiddenOrderCheck'+i).value='Y';
	   }
	   else{
	   document.getElementById('hiddenOrderCheck'+i).value='N';
	   }
}
    

function manageQuestion(moduleCode,modulName){
	var programCode =document.getElementById('paraFrm_programeId').value;  
	var proNm = document.getElementById('paraFrm_programeName').value;
	
	if(programCode==""){
		alert("Please Save Record First Then Procced.");
		return false;
	}
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ProgrammeMaster_callQuesManage.action?programID='+programCode+'&moduleID='+moduleCode+'&progName='+proNm+'&modName='+modulName+'&questionFlag=module';
    document.getElementById('paraFrm').submit();
}

function manageSection(moduleCode,modulName){
	   
	var proNm = document.getElementById('paraFrm_programeName').value;
var programCode =document.getElementById('paraFrm_programeId').value;  
  //var isSaveClick =document.getElementById('isSaveClick').value;

	 
	if(programCode==""){
		alert("Please Save Record First Then Procced.");
		return false;
	}
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ProgrammeMaster_callManage.action?programID='+programCode+'&moduleID='+moduleCode+'&progName='+proNm+'&modName='+modulName;
    document.getElementById('paraFrm').submit();
}

function validate(){

  var proName = document.getElementById('paraFrm_programeName').value;
  var tp = document.getElementById('paraFrm_type').value;
  proName = trim(proName);
  tp = trim(tp);
  var fields= ["paraFrm_programeName"];
	if(!f9specialchars(fields)) {
		alert("Special characters not allowed");
		return false;
	}
  if(proName== ""){
	  alert("Please enter "+document.getElementById('programe').innerHTML.toLowerCase());
	  document.getElementById('paraFrm_programeName').value = "";
	  return false;
  } else if(tp== ""){
	  alert("Please enter "+document.getElementById('typeLbl').innerHTML.toLowerCase());
	  return false;
  } else if (document.getElementById('paraFrm_duration').value !='NA' && document.getElementById('paraFrm_duration').value !='N/A'){
  		if (document.getElementById('paraFrm_duration').value == "00:00") {
  			alert(document.getElementById('dur').innerHTML.toLowerCase() + " should be greater than 00:00");
  			return false;
  		}
		if(validateDuration('paraFrm_duration','dur')) {
	 		return true;
    	} else     
   			return false;
   		
  } else {
     return true;
  }
}

function validateDuration(name, labName){
	var time        = document.getElementById(name).value;
	if(time=="")return true;
	var timeExp 	= /^[0-9]{2}[:]?[0-9]{2}$/
	var timeArray 	= time.split(":");
	var hour	    = timeArray[0];
	var min         = timeArray[1];
	if(!(time.match(timeExp)) || time.length<5){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in 24Hours HH:MM format");
		document.getElementById(name).focus();
		return false;
	}
	
	if(hour>23){
		alert("Hour "+hour+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	
	if(min>59){
		alert("Minute "+min+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	return true ;
}

function saveFun(){
  if(validate()){
  //document.getElementById('isSaveClick').value='Y';
  
  	var tp = document.getElementById('paraFrm_type').value;
	if(tp=='T'){
		if(document.getElementById('paraFrm_duration').value==null || document.getElementById('paraFrm_duration').value=="null"){
			document.getElementById('paraFrm_duration').value = "";
		}
	} else {
		document.getElementById('paraFrm_duration').value = "";
	}
  
	var loopCount ='<%=count%>';
	if(loopCount>0)
	{
	for(i=1;i<loopCount;i++)
	{
	var noOfAttempts =document.getElementById('noOfAttempts'+i).value;
	 
	if(noOfAttempts==0)
	{
		alert("No. of attempts should be grater than zero.");
		return false;
	}
	 
	}
	}
	
	 document.getElementById('paraFrm').target = "_self";
     document.getElementById('paraFrm').action = 'ProgrammeMaster_save.action';
     document.getElementById('paraFrm').submit();
 
	
    
  }
  else{ 
	return false;
  }
}

function addModule(){
	var programCode =document.getElementById('paraFrm_programeId').value;  
	if(programCode==""){
		if(validate()){			
		}else{
			return false;
		}
	}
	var mod = document.getElementById('paraFrm_module').value;
	
	if(mod == ""){
	   alert("Please select module");
	   return false;
	}
	document.getElementById('addButton').disabled = "disabled";
	   document.getElementById('paraFrm').action='ProgrammeMaster_addModule.action';
	   document.getElementById('paraFrm').submit(); 
}

function cancel(){
	document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action = 'ProgrammeMaster_input.action';
  	document.getElementById('paraFrm').submit();
}

function addInstruction(){
	var programeId=document.getElementById('paraFrm_programeId').value;
	if(programeId=="")	{
		alert('Please save Program first');
		return false;		
	}  else {
		return true;
	}
}

function orderUp(order, i){
		var temp = ""; 
		//alert(document.getElementById('paraFrm_moduleOrder').value); 
		if(document.getElementById('modCodeTD' +(i-1)) == null ){
			alert("It's First Module You cann't go Up");
		} else {
		temp = document.getElementById('modOrderTD'+i).innerHTML;
		document.getElementById('modOrderTD'+i).innerHTML = document.getElementById('modOrderTD' +(i-1)).innerHTML;
		document.getElementById('modOrderTD' +(i-1)).innerHTML = temp;
	 
		temp = document.getElementById('modManageSecTD'+i).innerHTML;
		document.getElementById('modManageSecTD'+i).innerHTML = document.getElementById('modManageSecTD' +(i-1)).innerHTML;
		document.getElementById('modManageSecTD' +(i-1)).innerHTML = temp;
				
		temp = document.getElementById('modEnableContTD'+i).innerHTML;
		document.getElementById('modEnableContTD'+i).innerHTML = document.getElementById('modEnableContTD' +(i-1)).innerHTML;
		document.getElementById('modEnableContTD' +(i-1)).innerHTML = temp;
		
		temp = document.getElementById('modEnableQuesTD'+i).innerHTML;
		document.getElementById('modEnableQuesTD'+i).innerHTML = document.getElementById('modEnableQuesTD' +(i-1)).innerHTML;
		document.getElementById('modEnableQuesTD' +(i-1)).innerHTML = temp;
		
		temp = document.getElementById('modMarksTD'+i).innerHTML;
		document.getElementById('modMarksTD'+i).innerHTML = document.getElementById('modMarksTD' +(i-1)).innerHTML;
		document.getElementById('modMarksTD' +(i-1)).innerHTML = temp;
		
		temp = document.getElementById('modQuesTD'+i).innerHTML;
		document.getElementById('modQuesTD'+i).innerHTML = document.getElementById('modQuesTD' +(i-1)).innerHTML;
		document.getElementById('modQuesTD' +(i-1)).innerHTML = temp;
		
		temp = document.getElementById('modCodeTD'+i).innerHTML;
		document.getElementById('modCodeTD'+i).innerHTML = document.getElementById('modCodeTD' +(i-1)).innerHTML;
		document.getElementById('modCodeTD' +(i-1)).innerHTML = temp;
		
		temp = document.getElementById('noOfAttemptsTD'+i).innerHTML;
		document.getElementById('noOfAttemptsTD'+i).innerHTML = document.getElementById('noOfAttemptsTD' +(i-1)).innerHTML;
		document.getElementById('noOfAttemptsTD' +(i-1)).innerHTML = temp;
		}
		setcheck();
}


function orderDown(order, i){
 		var temp = "";
 		//alert(document.getElementById('paraFrm_moduleOrder').value); 
		if(document.getElementById('modCodeTD' +(i+1)) == null ){		
			alert("It's Last Module You cann't go Down");
		} else {
		temp = document.getElementById('modOrderTD'+i).innerHTML;
		document.getElementById('modOrderTD'+i).innerHTML = document.getElementById('modOrderTD' +(i+1)).innerHTML;
		document.getElementById('modOrderTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('modManageSecTD'+i).innerHTML;
		document.getElementById('modManageSecTD'+i).innerHTML = document.getElementById('modManageSecTD' +(i+1)).innerHTML;
		document.getElementById('modManageSecTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('modEnableContTD'+i).innerHTML;
		document.getElementById('modEnableContTD'+i).innerHTML = document.getElementById('modEnableContTD' +(i+1)).innerHTML;
		document.getElementById('modEnableContTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('modEnableQuesTD'+i).innerHTML;
		document.getElementById('modEnableQuesTD'+i).innerHTML = document.getElementById('modEnableQuesTD' +(i+1)).innerHTML;
		document.getElementById('modEnableQuesTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('modMarksTD'+i).innerHTML;
		document.getElementById('modMarksTD'+i).innerHTML = document.getElementById('modMarksTD' +(i+1)).innerHTML;
		document.getElementById('modMarksTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('modQuesTD'+i).innerHTML;
		document.getElementById('modQuesTD'+i).innerHTML = document.getElementById('modQuesTD' +(i+1)).innerHTML;
		document.getElementById('modQuesTD' +(i+1)).innerHTML = temp;		
		
		temp = document.getElementById('modCodeTD'+i).innerHTML;
		document.getElementById('modCodeTD'+i).innerHTML = document.getElementById('modCodeTD' +(i+1)).innerHTML;
		document.getElementById('modCodeTD' +(i+1)).innerHTML = temp;
		
		temp = document.getElementById('noOfAttemptsTD'+i).innerHTML;
		document.getElementById('noOfAttemptsTD'+i).innerHTML = document.getElementById('noOfAttemptsTD' +(i+1)).innerHTML;
		document.getElementById('noOfAttemptsTD' +(i+1)).innerHTML = temp;
		}
		setcheck();
}	

function setcheck(){
	var eleOrder = document.getElementsByName("hiddenOrderCheck");
	var eleOrderChk = document.getElementsByName("order");
	for(i=0;i<eleOrder.length;i++){
		if(eleOrder[i].value=="Y") {
	 		eleOrderChk[i].checked =true; 
		}
		else{
		  eleOrderChk[i].checked =false;
		  }
	}
	var eleCont = document.getElementsByName("hiddenContentCheck");
	var eleContChk = document.getElementsByName("enableContent");
	for(i=0;i<eleCont.length;i++){
		if(eleCont[i].value=="Y") {
	 		eleContChk[i].checked =true; 
		}		
	}
	var eleQues = document.getElementsByName("hiddenQuesCheck");
	var eleQuesChk = document.getElementsByName("enableQues");
	for(i=0;i<eleQues.length;i++){
		if(eleQues[i].value=="Y") {
	 		eleQuesChk[i].checked =true; 
		}		
	}
	
	var eleAttemptHidden = document.getElementsByName("noOfAttemptsHidden");
	var eleAttempt = document.getElementsByName("noOfAttempts");
	for(i=1;i<=eleAttemptHidden.length;i++){
		document.getElementById('noOfAttempts'+i).value = document.getElementById('noOfAttemptsHidden'+i).value;
 		//eleAttempt[i].value=eleAttemptHidden[i].value; 
	}
}

function setAttemptValue(){
	var eleAttemptHidden = document.getElementsByName("noOfAttemptsHidden");
	var eleAttempt = document.getElementsByName("noOfAttempts");
	for(i=1;i<=eleAttemptHidden.length;i++){
		document.getElementById('noOfAttemptsHidden'+i).value = document.getElementById('noOfAttempts'+i).value;
 		//eleAttemptHidden[i].value = eleAttempt[i].value; 
	}
}

 </script>