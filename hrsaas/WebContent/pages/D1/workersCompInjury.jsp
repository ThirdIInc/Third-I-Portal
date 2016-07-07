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
					<td width="100%" class="txt"><strong>Workers Comp Injury/Illness Report </strong></td>
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

				<td width="78%" colspan="2">
				<s:hidden name="f9Flage"/>
				<s:hidden name="workersCode"/><s:hidden name="flag"/><s:hidden name="flagSendBack"/>
				<s:hidden name="trackingNo"/>
				<s:hidden name="reportingOfficer"/><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				<s:if test="%{flag ==''}">
				 <input type="button" value="Draft" class="save" onclick="return draftFun();">
				 <input type="button" value="Submit" class="save" onclick="return submitFun();">
				 <input type="button" value="Reset" class="reset" onclick="resetFun();">
				
				 <s:if test="%{workersCode ==''}">
				
				</s:if>
				 <s:else>
				  <input type="button" value="Delete" class="delete" onclick="return deleteFun();">
				 </s:else>
				 
				</s:if>
				 <input type="submit" value="Back To List" class="back" onclick="backtolistFun();">
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
	<s:if test="%{flag =='AA'||flag =='RR'||flagSendBack=='BB'}">
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
</s:if>			
			
<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="2" cellpadding="2" class="formbg" border="0">		
			
			<tr>
			<td width="15%" colspan="4"><B>Employee Information</B></td>
			</tr>
			
			<tr>
				<td width="25%" colspan="1">
				<label class="set" name="employee" id="employee1" ondblclick="callShowDiv(this);">
				<%=label.get("employee")%></label> :<font
					color="red">*</font></td>
				<td width="20%" colspan="3">				
				<s:textfield name="empToken" readonly="true" cssStyle="background-color: #F2F2F2;"/>
				<s:hidden name="empId"  />
				<s:hidden name="applicationDate"/>
				
				<s:textfield name="empName" size="61" readonly="true" cssStyle="background-color: #F2F2F2;"/>
					<s:if test="%{f9Flage ==''}">
				<img id="ctrlHide"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'WorkersCompInjury_f9action.action')" />
				</s:if>				
				</td>
             	 
				
				</tr>
			

        
		 
		 <tr>
			<td width="25%" colspan="1">
			<label class="set" name="emp.home" id="emp.home" ondblclick="callShowDiv(this);">
				<%=label.get("emp.home")%></label>
			 :</td>
			<td width="20"colspan="1">
			<s:textfield name="empHomeState" maxlength="200"/>
			</td>
			<td width="20%" colspan="1">&nbsp;</td>
           	<td width="25%" colspan="1">&nbsp;</td>		 
			</tr>

         
 

<s:hidden name="empSSN" />
<s:hidden name="socialInsNo" />
 


    <tr>
			<td width="25%"  colspan="1"><label class="set" name="cworklocation" id="cworklocation" ondblclick="callShowDiv(this);">
				<%=label.get("cworklocation")%></label> :</td>
			<td width="20%" colspan="1">
					<s:textfield name="companyWork" maxlength="200"/>
					
					</td>
					<td width="20%"> <label class="set" name="dep" id="dep" ondblclick="callShowDiv(this);">
				<%=label.get("dep")%></label> :</td>
					<td width="20%"><s:textfield name="department" maxlength="100" readonly="true"/> 
					<s:hidden name="departmentCode"></s:hidden>
					 <img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16" id='ctrlHide'
									onclick="javascript:callsF9(500,325,'WorkersCompInjury_f9department.action');">
					
					</td>
					 
	</tr>



    <tr>
			<td width="25%" colspan="1" ><label class="set" name="exe" id="exe" ondblclick="callShowDiv(this);">
				<%=label.get("exe")%></label> :</td>
			<td width="20%" colspan="1"><s:textfield name="executive" maxlength="200"/></td>
			
			
			
			<td width="20%" colspan="1"><label class="set" name="manPhone" id="manPhone" ondblclick="callShowDiv(this);">
				<%=label.get("manPhone")%></label> :</td>
			<td width="25%" colspan="1">
			<s:hidden name="region"></s:hidden>
			<s:textfield name="managerPhone" maxlength="20" /></td>
			
			
				
	</tr>

    

   <tr>
			<td width="25%" colspan="1"><label class="set" name="manName" id="manName" ondblclick="callShowDiv(this);">
				<%=label.get("manName")%></label>:</td>
			<td width="20%" colspan="1"><s:textfield name="managerName" maxlength="200"/></td>
				
				<td width="20%" colspan="1"><label class="set" name="ms" id="ms" ondblclick="callShowDiv(this);">
				<%=label.get("ms")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="maritalStatus" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
			
  </tr>
	
   
<tr>
			<td width="25%" colspan="1"><label class="set" name="no18" id="no18" ondblclick="callShowDiv(this);">
				<%=label.get("no18")%></label>:<font
					color="red">*</font></td>
			<td width="20%" colspan="1"><s:textfield maxlength="5" name="noOfDependancy" onkeypress="return numbersWithDot();"/></td>
			
					 
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
				<%=label.get("doi")%></label> :<font
					color="red">*</font></td>
  <td width="12%" colspan="1"><s:textfield name="doi" onkeypress="return numbersWithHiphen();"/><s:a
								href="javascript:NewCal('paraFrm_doi','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a></td>
	<td><label class="set" name="toi" id="toi" ondblclick="callShowDiv(this);">
				<%=label.get("toi")%></label><font
					color="red">*</font></td>			
	
	<td colspan="1"><s:textfield name="toi" onkeypress="return numbersWithColon();"  maxlength="5" /> (HH24:MM)</td>
		</tr>
	
	
	
	
	<tr>
			<td width="13%"><label class="set" name="hwd" id="hwd" ondblclick="callShowDiv(this);">
				<%=label.get("hwd")%></label> :<font
					color="red">*</font></td>
			<td colspan="3">
			<s:textfield name="hrsWorkedDate"  size="12" onkeypress="return numbersWithColon();"  maxlength="5" />(HH24:MM)
			</td>
				 

</tr>

	<tr>
		<td width="25%" colspan="1"><label class="set" name="from" id="from" ondblclick="callShowDiv(this);">
				<%=label.get("from")%></label> :<font
					color="red">*</font></td>
		<td width="20%" colspan="1"><s:textfield name="normalWorkingHrsFrom" size="12" onkeypress="return numbersWithColon();"  maxlength="5" />(HH24:MM) </td>  
		 			
        <td width="20%" colspan="1"><label class="set" name="to" id="to" ondblclick="callShowDiv(this);">
				<%=label.get("to")%></label> :<font
					color="red">*</font></td>
        <td width="25%" colspan="1"><s:textfield name="normalWorkingHrsTo" onkeypress="return numbersWithColon();"   maxlength="5" />(HH24:MM) </td> 
</tr>
	
	 
	

	<tr>
  
   <td><label class="set" name="dek" id="dek" ondblclick="callShowDiv(this);">
				<%=label.get("dek")%></label></td>
    <td width="12%" colspan="1"><s:textfield name="dke" onkeypress="return numbersWithHiphen();"/><s:a
								href="javascript:NewCal('paraFrm_dke','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a></td>

</tr>

<tr>
			<td width="20%" colspan="1"><label class="set" name="iname" id="iname" ondblclick="callShowDiv(this);">
				<%=label.get("iname")%></label> :</td>
			<td width="20%" colspan="1"><s:textfield name="injuryReportedName"/></td>
<td width="20%" colspan="1"><label class="set" name="title" id="title" ondblclick="callShowDiv(this);">
				<%=label.get("title")%></label> :</td>
			<td width="25%" colspan="1"><s:textfield name="title"/></td>


</tr>

<tr>
	<td width="13%"><label class="set" name="incidentResult" id="incidentResult" ondblclick="callShowDiv(this);">
				<%=label.get("incidentResult")%></label> :</td>
	<td colspan="1">
	<s:select list="#{'Y':'Yes','N':'No'}" name="incidentResult" onchange="callLossWorkDay('incidentResult')"/>
	</td>
	
   <td width="20%" colspan="1" id="L1"><label class="set" name="probable" id="probable" ondblclick="callShowDiv(this);">
				<%=label.get("probable")%></label> :</td>
   <td width="25%" colspan="1" id="L2">
   <s:textfield name="probableLengthDisability" onkeypress="return numbersWithDot();"/> Days</td>
</tr>
	
<tr>
  
   <td><label class="set" name="lostwork" id="lostwork" ondblclick="callShowDiv(this);">
				<%=label.get("lostwork")%></label></td>
   <td width="12%" colspan="3"><s:textfield name="lostWorkDayDate" onkeypress="return numbersWithHiphen();"/><s:a
								href="javascript:NewCal('paraFrm_lostWorkDayDate','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a></td>	
</tr>	
	
<tr>
	<td width="13%"><label class="set" name="hinjurework" id="hinjurework" ondblclick="callShowDiv(this);">
				<%=label.get("hinjurework")%></label> :</td>
	<td colspan="1"><s:select list="#{'N':'No','Y':'Yes'}" name="injuredReturnWork" onchange="callReturnWork()"/></td>	
	
	 <td id="W1"><label class="datereturn" name="datereturn" id="ssn" ondblclick="callShowDiv(this);">
				<%=label.get("datereturn")%></label></td>
    <td width="12%" colspan="1" id="W2"><s:textfield name="dor"  onkeypress="return numbersWithHiphen();"/><s:a
								href="javascript:NewCal('paraFrm_dor','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a>
		</td>
</tr>
 
 <tr>
	<td width="13%"></td>
	<td colspan="1"></td>
	 <td id="W4">Time :</td>
    <td width="12%" colspan="1" id="W3">
    <s:textfield  maxlength="5" name="tor"   onkeypress="return numbersWithColon();"/>(HH24:MM)</td>
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
	<s:textarea name="addressAccident" cols="50" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
	 <img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_addressAccident','addressOfAcc','', '', '500','500');">
	
	 
	 
	 </td>
	</tr>
   
   <tr><td colspan ="1" width="50%"><label class="set" name="addressphone" id="addressphone" ondblclick="callShowDiv(this);">
				<%=label.get("addressphone")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="addressPhone" cols="50" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
	<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_addressPhone','addressphone','', '', '500','500');">
	
	
	</td>
	</tr>
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="deschow" id="deschow" ondblclick="callShowDiv(this);">
				<%=label.get("deschow")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="describeInjury" cols="50" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
	<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_describeInjury','deschow','', '', '500','500');">
	
	
	</td>
	</tr>
   
   <tr><td colspan ="1" width="50%"><label class="set" name="partofbody" id="partofbody" ondblclick="callShowDiv(this);">
				<%=label.get("partofbody")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea  name="partOfBodyAffected" cols="50" rows="3"    onkeypress="return imposeMaxLength(event, this, 500);" />
	<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_partOfBodyAffected','partofbody','', '', '500','500');">
	
	
	</td>
	</tr>
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="nameaddrr" id="nameaddrr" ondblclick="callShowDiv(this);">
				<%=label.get("nameaddrr")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea  name="nameAddressPhy" cols="50" rows="3"    onkeypress="return imposeMaxLength(event, this, 500);"/>
	<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_nameAddressPhy','nameaddrr','', '', '500','500');">
	
	
	</td>
	</tr>
   
   
   
   <tr><td colspan ="1" width="50%"><label class="set" name="typeoftrement" id="typeoftrement" ondblclick="callShowDiv(this);">
				<%=label.get("typeoftrement")%></label>  :</td>
	<td colspan ="3" width="50%"><s:textarea name="typeOfTretment" cols="50" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
	<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_typeOfTretment','typeoftrement','', '', '500','500');">
	
	</td>
	</tr>
   
   <tr><td></td>
	</tr>
   
    <tr><td colspan ="1" width="50%"><label class="set" name="reason" id="reason" ondblclick="callShowDiv(this);">
				<%=label.get("reason")%></label> :</td>
	<td colspan ="3" width="50%"><s:textarea name="reasonToDoubt" cols="50" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
							<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reasonToDoubt','reason','', '', '500','500');">
	</td>
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
	
		
 <tr>
		<td colspan="4">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td colspan="2">
   
   <s:if test="%{flag ==''}">
				 <input type="button" value="Draft" class="save" onclick="return draftFun();">
				 <input type="button" value="Submit" class="save" onclick="return submitFun();">
				 <input type="button" value="Reset" class="reset" onclick="resetFun();">
				  <s:if test="%{workersCode ==''}">
				
				</s:if>
				 <s:else>
				  <input type="button" value="Delete" class="delete" onclick="return deleteFun();">
				 </s:else>
				 
				</s:if>
				 <input type="submit" value="Back To List" class="back" onclick="backtolistFun();">
				 <input type="button" value="Print" class="print" onclick="printFun();">
   </td>
			</tr>
			</table>
			</td>
			</tr>
	
	
	
	</table>
		

</s:form>
<script>

onloadData();
function onloadData(){
callLossWorkDay('incidentResult');
callReturnWork();
}

		
		function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
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
	
	var vv=confirm("Do you really want to submit this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_submit.action';
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
		if(document.getElementById('paraFrm_noOfDependancy').value==''){
		alert('Please enter number of dependant under age 18');
		return false;
		}
		if(isNaN(document.getElementById('paraFrm_noOfDependancy').value)){
			alert('Please enter no of dependancy as a number');
			document.getElementById('paraFrm_noOfDependancy').focus();
			return false;			
			}
		
		
		if(document.getElementById('paraFrm_doi').value==''){
		alert('Please enter/select date of injury');
		return false;
		}
		var check= validateDate('paraFrm_doi', 'doi');
			if(!check){
			return false;
		}
		
		
		if(document.getElementById('paraFrm_toi').value==''){
		alert('Please enter time of injury');
		return false;
		}		
		
		if(IsValidTime(document.getElementById('paraFrm_toi').value)){
			}
			else{
			document.getElementById('paraFrm_toi').focus();
			return false;
			}
		if(document.getElementById('paraFrm_hrsWorkedDate').value==''){
		alert('Please enter Hours Worked Date Of Injury');
		return false;
		}	
		if(IsValidTime(document.getElementById('paraFrm_hrsWorkedDate').value)){
			}
			else{
			document.getElementById('paraFrm_hrsWorkedDate').focus();
			return false;
			}
		
	if(document.getElementById('paraFrm_normalWorkingHrsFrom').value==''){
		alert('Please enter Normal Working Hours From');
		return false;
		}
		if(IsValidTime(document.getElementById('paraFrm_normalWorkingHrsFrom').value)){
			}
			else{
			document.getElementById('paraFrm_normalWorkingHrsFrom').focus();
			return false;
			}
		if(document.getElementById('paraFrm_normalWorkingHrsTo').value==''){
		alert('Please enter Normal Working Hours To');
		return false;
		}
		if(IsValidTime(document.getElementById('paraFrm_normalWorkingHrsTo').value)){
			}
			else{
			document.getElementById('paraFrm_normalWorkingHrsTo').focus();
			return false;
			}
			
			if(document.getElementById('paraFrm_tor').value==''){		
			
			}
			else{
			if(IsValidTime(document.getElementById('paraFrm_tor').value)){
			}
			else{
			document.getElementById('paraFrm_tor').focus();
			return false;
			}
			}
		
		if(!document.getElementById('paraFrm_dke').value==''){		
			var check1= validateDate('paraFrm_dke', 'dek');
			if(!check1){
			return false;
		}
			}
			if(!document.getElementById('paraFrm_lostWorkDayDate').value==''){		
			var check= validateDate('paraFrm_lostWorkDayDate', 'lostwork');
			if(!check){
			return false;
		}
			}
			
			if(document.getElementById('paraFrm_injuredReturnWork').value==''){			
			if(document.getElementById('paraFrm_dor').value==''){
	alert('Please enter/select date returned');
	return false;
	}
	}		
			
			
			if(!document.getElementById('paraFrm_dor').value==''){		
			var check= validateDate('paraFrm_dor', 'datereturn');
			if(!check){
			return false;
		}
			}
		
		
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_reset.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteFun() {
	if(document.getElementById('paraFrm_empId').value==''){
		alert('Please select employee');
		return false;
		}
	var vv=confirm('Do you want to delete application');
	if(vv){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjury_delete.action';
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
	function printFun() {	
	window.print();
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
	
	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>