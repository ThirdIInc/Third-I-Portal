<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="WorkersCompInjury" name="EmpTypeMaster" id="paraFrm" validate="true" target="main" theme="simple">


<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
				<tr>				

					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong>Workers Comp Injury/Illness Report Form</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>	</td>
				</tr>
			</table>			</td>
			</tr>


	<tr>
		<td colspan="4">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>

				<td width="78%" colspan="2"><s:hidden name="workersCode"/><s:hidden name="flag"/>
				<s:hidden name="reportingOfficer"/><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				
				
				 <input type="button" value="Submit" class="save" onclick="return resubmitFun();">				
				 <input type="submit" value="Back to List" class="back" onclick="backtolistFun();">
				  <input type="button" value="Print" class="print" onclick="printFun();">
				  <!--
				 
				  <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				  
				 -->
				</td>
				<td width="22%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
			</tr>
		</table>
		<label></label></td>

	</tr>
	
		
		<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="2" cellpadding="2" class="formbg" border="0">		
			
			<tr>
							<td width="25%" colspan="4"><B>Comments By Approver</B>							</td>
							
						</tr>
						
					<s:if test="listComment">		
						<tr>
						<td class="formth" width="05%">Sr No </td>
						<td class="formth" width="15%">Approver Name </td>
						<td class="formth" width="40%" align="center">Comments </td>
						<td class="formth" width="15%">Application Date </td>
						<td class="formth" width="10%">Status </td>						
						</tr>
						<%int tt=1; %>
						<s:iterator value="listComment">
						<tr>
						<td   class="sortableTD"><%=tt++ %> </td>
						<td   class="sortableTD"> <s:property value="ittApproverName"/></td>
						<td   class="sortableTD"><s:property value="ittComments"/> </td>
						<td   class="sortableTD"> <s:property value="ittApplicationDate"/></td>
						<td   class="sortableTD"> <s:property value="ittStatus"/></td>
						</tr>
						</s:iterator>
					</s:if>	
	</table>
	</td>
	</tr>		
			
<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="2" cellpadding="2" class="formbg" border="0">		
			
			<tr>
			<td width="15%" colspan="4"><B>Employee Information</B></td>
			</tr>
			
			<tr>
				<td width="25%" colspan="1">
				<label class="set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);">
				<%=label.get("employee.id")%></label>
				 :</td>
				<td width="20%" colspan="3">				
				<s:textfield name="empToken" readonly="true" size="15" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/>
				<s:hidden name="empId"  />
				<s:hidden name="applicationDate"/>
				
				<s:textfield name="empName" size="65" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/>
				
				</td>
             	 
				
				</tr>
			

        
		 
		 <tr>
			<td width="25%" colspan="1">
			<label class="set" name="emp.home" id="emp.home" ondblclick="callShowDiv(this);">
				<%=label.get("emp.home")%></label>
			 :</td>
			<td width="20"colspan="1">
			<s:textfield name="empHomeState" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/>
			</td>
			<td width="20%" colspan="1">&nbsp;</td>
           	<td width="25%" colspan="1">&nbsp;</td>		 
			</tr>

         
 


  <s:hidden name="empSSN" />
<s:hidden name="socialInsNo" />


    <tr>
			<td width="25%"  colspan="1"><label class="set" name="cworklocation" id="cworklocation" ondblclick="callShowDiv(this);">
				<%=label.get("cworklocation")%></label> :</td>
			<td width="20%" colspan="1"><s:textfield name="companyWork" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
					<td width="20%"> <label class="set" name="dep" id="dep" ondblclick="callShowDiv(this);">
				<%=label.get("dep")%></label> :</td>
					<td width="20%"><s:textfield name="department" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/> </td>
					 
	</tr>



    <tr>
			<td width="25%" colspan="1" ><label class="set" name="exe" id="exe" ondblclick="callShowDiv(this);">
				<%=label.get("exe")%></label> :</td>
			<td width="20%" colspan="1"><s:textfield name="executive" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
			
			<td width="20%" colspan="1"><label class="set" name="reg" id="reg" ondblclick="callShowDiv(this);">
				<%=label.get("reg")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="region" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
				
	</tr>

    

   <tr>
			<td width="25%" colspan="1"><label class="set" name="manName" id="manName" ondblclick="callShowDiv(this);">
				<%=label.get("manName")%></label>:</td>
			<td width="20%" colspan="1"><s:textfield name="managerName" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
				
			<td width="20%" colspan="1"><label class="set" name="manPhone" id="manPhone" ondblclick="callShowDiv(this);">
				<%=label.get("manPhone")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="managerPhone" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
				
  </tr>
	
   
<tr>
			<td width="25%" colspan="1"><label class="set" name="no18" id="no18" ondblclick="callShowDiv(this);">
				<%=label.get("no18")%></label>:</td>
			<td width="20%" colspan="1"><s:textfield maxlength="5" name="noOfDependancy" onkeypress="return numbersWithDot();" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
			<td width="20%" colspan="1"><label class="set" name="ms" id="ms" ondblclick="callShowDiv(this);">
				<%=label.get("ms")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="maritalStatus" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/></td>
			
					 
</tr>

   </table>
  </td>
</tr>

  <tr>
    <td colspan="4">
      <table width="100%" cellspacing ="2" cellpadding="2" class="formbg" border="0">
  
  
   <tr>
	 <td width="15%" colspan="4"><B>Injury/Illness Information</B></td>
	</tr>
   
   
   <tr>
  
   <td><label class="set" name="doi" id="doi" ondblclick="callShowDiv(this);">
				<%=label.get("doi")%></label> :</td>
  <td width="12%" colspan="1"><s:textfield name="doi" onkeypress="return numbersWithHiphen();"  cssStyle="border:none;" readonly="true"/></td>
	<td><label class="set" name="toi" id="toi" ondblclick="callShowDiv(this);">
				<%=label.get("toi")%></label></td>			
	
	<td colspan="1"><s:textfield name="toi" onkeypress="return numbersWithColon();"  maxlength="5" size="6"  cssStyle="border:none;" readonly="true"/> (HH24:MM)</td>
		</tr>
	
	
	
	
	<tr>
			<td width="13%"><label class="set" name="hwd" id="hwd" ondblclick="callShowDiv(this);">
				<%=label.get("hwd")%></label> :</td>
			<td colspan="3">
			<s:textfield name="hrsWorkedDate"  size="12" onkeypress="return numbersWithColon();"  maxlength="5"  cssStyle="border:none;" readonly="true"/>(HH24:MM)
			</td>
				 

</tr>

	<tr>
		<td width="25%" colspan="1"><label class="set" name="from" id="from" ondblclick="callShowDiv(this);">
				<%=label.get("from")%></label> :</td>
		<td width="20%" colspan="1"><s:textfield name="normalWorkingHrsFrom" size="6" onkeypress="return numbersWithColon();"   cssStyle="border:none;" readonly="true" maxlength="5" />(HH24:MM) </td>  
		 			
        <td width="20%" colspan="1"><label class="set" name="to" id="to" ondblclick="callShowDiv(this);">
				<%=label.get("to")%></label> :</td>
        <td width="25%" colspan="1"><s:textfield name="normalWorkingHrsTo" onkeypress="return numbersWithColon();"   maxlength="5" size="6"  cssStyle="border:none;" readonly="true"/>(HH24:MM) </td> 
</tr>
	
	 
	

	<tr>
  
   <td><label class="set" name="dek" id="dek" ondblclick="callShowDiv(this);">
				<%=label.get("dek")%></label></td>
    <td width="12%" colspan="1"><s:textfield name="dke" onkeypress="return numbersWithHiphen();"  cssStyle="border:none;" readonly="true"/></td>

</tr>

<tr>
			<td width="20%" colspan="1"><label class="set" name="iname" id="iname" ondblclick="callShowDiv(this);">
				<%=label.get("iname")%></label> :</td>
			<td width="20%" colspan="1"><s:textfield name="injuryReportedName"  cssStyle="border:none;" readonly="true"/></td>
<td width="20%" colspan="1"><label class="set" name="title" id="title" ondblclick="callShowDiv(this);">
				<%=label.get("title")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="title"  cssStyle="border:none;" readonly="true"/></td>


</tr>

<tr>
	<td width="13%"><label class="set" name="incidentResult" id="incidentResult" ondblclick="callShowDiv(this);">
				<%=label.get("incidentResult")%></label> :</td>
	<td colspan="1">
	<s:select list="#{'Y':'Yes','N':'No'}" disabled="true" name="incidentResult" onchange="callLossWorkDay('incidentResult')"  cssStyle="border:none;" />
	</td>
	
   <td width="20%" colspan="1" id="L1"><label class="set" name="probable" id="probable" ondblclick="callShowDiv(this);">
				<%=label.get("probable")%></label> :</td>
   <td width="25%" colspan="1" id="L2">
   <s:textfield name="probableLengthDisability" onkeypress="return numbersWithDot();" size="6"  cssStyle="border:none;" readonly="true"/> Days</td>
</tr>
	
<tr>
  
   <td><label class="set" name="lostwork" id="lostwork" ondblclick="callShowDiv(this);">
				<%=label.get("lostwork")%></label></td>
   <td width="12%" colspan="3"><s:textfield name="lostWorkDayDate" onkeypress="return numbersWithHiphen();"  cssStyle="border:none;" readonly="true"/></td>	
</tr>	
	
<tr>
	<td width="13%"><label class="set" name="hinjurework" id="hinjurework" ondblclick="callShowDiv(this);">
				<%=label.get("hinjurework")%></label> :</td>
	<td colspan="1"><s:select list="#{'Y':'Yes'}"   name="injuredReturnWork" onchange="callReturnWork()"   /></td>	
	
	 <td id="W1"><label class="set" name="datereturn" id="datereturn" ondblclick="callShowDiv(this);">
				<%=label.get("datereturn")%></label> :<font
					color="red">*</font></td>
    <td width="12%" colspan="1" id="W2"><s:textfield name="dor"  onkeypress="return numbersWithHiphen();"   readonly="true"/>
    <s:a
								href="javascript:NewCal('paraFrm_dor','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a>
		</td>
</tr>
 
 <tr>
	<td width="13%"></td>
	<td colspan="1"></td>
	 <td id="W4">Time :<font
					color="red">*</font></td>
    <td width="12%" colspan="1" id="W3">
    <s:textfield  maxlength="5" name="tor"   onkeypress="return numbersWithColon();" size="6"   />(HH24:MM)</td>
</tr>
 
 

    	</table>
	  </td>
	</tr>
	
<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">
	<tr>
	 <td width="15%"><B>Additional Information</B></td>
	</tr>
	
	<tr>
	<td colspan ="1" width="50%"><label class="set" name="addressOfAcc" id="addressOfAcc" ondblclick="callShowDiv(this);">
				<%=label.get("addressOfAcc")%></label> :</td>
	<td colspan ="3" width="50%">
	<s:textarea name="addressAccident" cols="50" rows="3"  cssStyle="border:none;" readonly="true"/>
	 </td>
	</tr>
   
   <tr><td colspan ="1" width="50%"><label class="set" name="addressphone" id="addressphone" ondblclick="callShowDiv(this);">
				<%=label.get("addressphone")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="addressPhone" cols="50" rows="3"  cssStyle="border:none;" readonly="true"/></td>
	</tr>
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="deschow" id="deschow" ondblclick="callShowDiv(this);">
				<%=label.get("deschow")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="describeInjury" cols="50" rows="3"  cssStyle="border:none;" readonly="true"/></td>
	</tr>
   
   <tr><td colspan ="1" width="50%"><label class="set" name="partofbody" id="partofbody" ondblclick="callShowDiv(this);">
				<%=label.get("partofbody")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea  name="partOfBodyAffected" cols="50" rows="3"    cssStyle="border:none;" readonly="true"  /></td>
	</tr>
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="nameaddrr" id="nameaddrr" ondblclick="callShowDiv(this);">
				<%=label.get("nameaddrr")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea  name="nameAddressPhy" cols="50" rows="3"    cssStyle="border:none;" readonly="true" /></td>
	</tr>
   
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="typeoftrement" id="typeoftrement" ondblclick="callShowDiv(this);">
				<%=label.get("typeoftrement")%></label>  :</td>
	<td colspan ="3" width="50%"><s:textarea name="typeOfTretment" cols="50" rows="3"  cssStyle="border:none;" readonly="true"/></td>
	</tr>
   
   <tr><td></td>
	</tr>
   
    <tr><td colspan ="1" width="50%"><label class="set" name="reason" id="reason" ondblclick="callShowDiv(this);">
				<%=label.get("reason")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="reasonToDoubt" cols="50" rows="3" cssStyle="border:none;" readonly="true"/></td>
	</tr>
   
 
   
   
   
   
   </table>
  </td>
</tr>		
		
<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">
	
	
	  <tr>
			<td width="25%" colspan="1">
			<strong><label class="set" name="completed.by" id="completed.by" ondblclick="callShowDiv(this);">
				<%=label.get("completed.by")%></label></strong>
			 :</td>
			<td width="20%" colspan="1">
			<s:hidden name="completedBy"/> 
			<s:hidden name="completedOn"/> 
			<s:property value="completedBy"/></td>
			


			<td width="20%"  colspan="1">
			<strong><label class="completed.on" name="completed.on" id="sin" ondblclick="callShowDiv(this);">
				<%=label.get("completed.on")%></label></strong> :</td>
			<td width="25%"  colspan="1"><s:property value="completedOn"/></td>
				
 </tr>
	
	
</table>
</td>
</tr>	
		
   <tr><td colspan="2">
   
  
				
				 <input type="button" value="Submit" class="save" onclick="return resubmitFun();">
				 
				
				 <input type="button" value="Back to List" class="back" onclick="backtolistFun();">
				 <input type="button" value="Print" class="print" onclick="printFun();">
   </td>
	</tr>
	
	
	
	</table>
		

</s:form>
<script>

function printFun() {	
	window.print();
	}


onloadData();
function onloadData(){
callLossWorkDay('incidentResult');
callReturnWork();

}

		function callLossWorkDay(id){
		var data=document.getElementById('paraFrm_'+id).value;
		document.getElementById('L1').style.display='none';
		document.getElementById('L2').style.display='none';
		if(data=='Y'){
		document.getElementById('L1').style.display='';
		document.getElementById('L2').style.display='';
		}
		}
		
		function callReturnWork(){
		var data=document.getElementById('paraFrm_injuredReturnWork').value;
		document.getElementById('W1').style.display='none';
		document.getElementById('W2').style.display='none';
		document.getElementById('W3').style.display='none';
		document.getElementById('W4').style.display='none';
		if(data=='Y'){
		document.getElementById('W1').style.display='';
		document.getElementById('W2').style.display='';
		document.getElementById('W3').style.display='';
		document.getElementById('W4').style.display='';
		}
		}


function draftFun() {
 
		if(!validate()){
	return false;
	}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_draft.action';
		document.getElementById('paraFrm').submit();
	}
	function submitFun() {
	
	if(!validate()){
	return false;
	}
	
	var vv=confirm('Do you want to submit');
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_submit.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	
	
	
	function resubmitFun() {
	if(document.getElementById('paraFrm_dor').value==''){
	alert('Please enter/select date returned');
	return false;
	}
	
	var fromDate = document.getElementById('paraFrm_doi').value;
		var toDate = document.getElementById('paraFrm_dor').value;
		
		if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_dor', 'doi', 'datereturn')) {
			return false;
		}
	
	if(document.getElementById('paraFrm_tor').value==''){
	alert('Please enter returned time');
	return false;
	}
	if(IsValidTime(document.getElementById('paraFrm_tor').value)){
			}
			else{
			document.getElementById('paraFrm_tor').focus();
			return false;
			}
	
	
	
	
	
	
	
	var vv=confirm("Do you really want to submit this application?");
	if(vv){
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_resubmit.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	
	
	function validate(){
		
		if(document.getElementById('paraFrm_empId').value==''){
		alert('Please select employee');
		return false;
		}
		
		if(document.getElementById('paraFrm_doi').value==''){
		alert('Please enter/select date of injury');
		return false;
		}
		if(document.getElementById('paraFrm_toi').value==''){
		alert('Please enter time of injury');
		return false;
		}		
		
		if(IsValidTime(document.getElementById('paraFrm_toi').value)){
			}
			else{
			return false;
			}
		if(document.getElementById('paraFrm_hrsWorkedDate').value==''){
		alert('Please enter Hours Worked Date Of Injury');
		return false;
		}	
	if(document.getElementById('paraFrm_normalWorkingHrsFrom').value==''){
		alert('Please enter Normal Working Hours From');
		return false;
		}
		if(IsValidTime(document.getElementById('paraFrm_normalWorkingHrsFrom').value)){
			}
			else{
			return false;
			}
		if(document.getElementById('paraFrm_normalWorkingHrsTo').value==''){
		alert('Please enter Normal Working Hours To');
		return false;
		}
		if(IsValidTime(document.getElementById('paraFrm_normalWorkingHrsTo').value)){
			}
			else{
			return false;
			}
			
			if(document.getElementById('paraFrm_tor').value==''){		
			
			}
			else{
			if(IsValidTime(document.getElementById('paraFrm_tor').value)){
			}
			else{
			return false;
			}
			}
		
		return true;
	}
	
	function sendBackFun() {
		var vv=confirm('Do you want to Send Back');
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_sendBack.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	function approveFun() {
	var vv=confirm('Do you want to Approve');
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_approve.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	function rejectFun() {
	var vv=confirm('Do you want to Reject');
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_reject.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	
	function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function IsValidTime(id) {		
				
				if(id==''){
				return true;
				}
			var timeStr=id;//document.getElementById('fromTime'+id).value;
				
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			
			var matchArray = timeStr.match(timePat);
		
			if (matchArray == null) {
			alert("Please enter time in a valid format(HH24:MM)");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];
			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }
			
			if (hour < 0  || hour > 23) {
			alert("Hour must be between 0 and 23");
			return false;
			}
		
			
			if (minute<0 || minute > 59) {
			alert ("Minute must be between 0 and 59.");
			return false;
			}
			if (second != null && (second < 0 || second > 59)) {
			alert ("Second must be between 0 and 59.");
			return false;
			}
			return true;
			}
	
	
	

	function makeFieldsDisabled() {
		//var readOnlyDetails = document.getElementById('paraFrm_readOnlyDetails').value;
		//var applnStatus = document.getElementById('paraFrm_applnStatus').value;
		
		
			disableFormFields();
		

		
	}
	
	
	function disableFormFields() {
		var labelCount = 0;
				
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id != 'comments') {
				if(document.all[i].id == 'ctrlHide') {
					document.all[i].style.display = 'none';
				} else if((document.all[i].type == 'checkbox' || document.all[i].type == 'radio') 
				&& document.all[i].id != 'ctrlShow' && document.all[i].parentNode.id != 'ctrlShow') {
					document.all[i].disabled = true;
				} else if((document.all[i].type == 'text' || document.all[i].type == 'password' || document.all[i].type == 'textarea' 
				|| document.all[i].type == 'select-one' || document.all[i].type == 'select-multiple' || document.all[i].type == 'button') 
				&& document.all[i].name != 'navigationButtons' && document.all[i].id != 'ctrlShow' && document.all[i].name != 'label' 
				&& document.all[i].name != 'newLabel' && document.all[i].name != 'changeLabelButtons' && document.all[i].id != 'authPassword') {
					hideFields(document.all[i], labelCount);
				}
			}
		}
		
		document.getElementById('labelCount').value = labelCount;
	}
	
	function hideFields(field, labelCount) {
		if(field.parentNode.id != 'ctrlShow') {
			if(field.type != 'button') {
				var lbl = document.createElement('label');
				lbl.id = 'lbl' + labelCount;
				labelCount++;
				
				if(field.type == 'select-one') {
					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									lbl.innerHTML = field.childNodes[j].innerHTML;
								}
							}
						}
					}
				} else if(field.type == 'select-multiple') {
					var recordSelected = 0;

					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									recordSelected += 1;
									
									if(recordSelected == 1) {
										lbl.innerHTML += field.childNodes[j].innerHTML;
									} else {
										lbl.innerHTML += ', ' + field.childNodes[j].innerHTML;
									}
								}
							}
						}
					}
				} else {
					lbl.innerHTML = field.value + ' ';
				}
	
				var cell = field.parentNode;
				cell.appendChild(lbl);
			}
			
			field.style.display = 'none';
		}
	}
	
	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>