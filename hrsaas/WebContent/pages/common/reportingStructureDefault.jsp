<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStr" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        		<tr>
				        <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				       <td width="93%" class="txt"><strong class="formhead">Reporting
							Structure For <%
				       	String structureType = (String) request
				       				.getAttribute("structureType");
				       		out.println(structureType);
				       		//request.setAttribute("type", structureType);
				       %> </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				    </tr>
	        	</table>
	       </td>
        </tr>
        
         <tr>
	          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	            <tr>
	              <td><s:hidden name="structureFor" value="%{reportingType}"/><s:hidden name="hiddenStructure" value="<%= structureType%>"/>
						<s:submit name="Back" value="   Back   " cssClass="token"
						action="ReportingStr_goToStartingPage" theme="simple"/>
						<s:if test="%{viewFlag}">
							<input type="button" class="token" value="Report" onclick="return report();" />
						</s:if>
					</td>
			        <td width="22%" class="txt"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
	            </tr>
	          </table>            
	            <label></label></td>
	   </tr>
        
        <tr>
			<td width="100%" colspan="6">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="100%" colspan="6" class="formhead"><strong class="forminnerhead">
						Default	Structure
						</strong></td>
					</tr>
					
					<tr>
						<td width="100%" colspan="6" ><s:if test="%{defaultAllCheck}">
						  <b><font color="red">The current reporting hierarchy is same as
						 <s:property value="defaultName" /></font></b></s:if></td>
					</tr>
					
					<tr>
						<td width="100%" colspan="6">
							<s:if test="%{defaultCheck}">
								<input type="checkbox" name="defaultCheck" id="defaultCheck"
									checked="checked" onclick="validateCheckBox('default');" />
								</s:if> <s:else>
								<input type="checkbox" name="defaultCheck" id="defaultCheck"
									onclick="validateCheckBox('default');" />
							</s:else>Apply this reporting hierarchy of <%=structureType%> to all types of Applications</td>
							
					</tr>
					
					<tr>
						<td width="100%" colspan="6"><s:if test="%{sameAsCheck}">
							<input type="checkbox" name="sameAsCheck" id="sameAsCheck"
								checked="checked" onclick="validateCheckBox('same');" />
						</s:if> <s:else>
							<input type="checkbox" name="sameAsCheck" id="sameAsCheck"
								onclick="validateCheckBox('same');" />
						</s:else>Apply the reporting hierarchy of &nbsp;
						<s:if test="structureFlag">
							<s:select name="sameStr" headerKey="" headerValue="Select" cssStyle="width:120" list="structureMap"/>
						</s:if>
						<s:else>
							<s:select name="sameStr" cssStyle="width:120" list="#{'':'Select'}"/>
						</s:else>
							&nbsp;to <%=structureType%>
							
						</td>
					</tr><s:hidden name="isSelected"/>
								
					<!-- ADDED BY REEBA -->
					<tr>
						<td width="50%" colspan="3"><s:if test="%{managerRecord}">
							<input type="checkbox" name="managerRecord" id="managerRecord"
								checked="checked"  onclick="toggle('Record','0');"/>
						</s:if> <s:else>
							<input type="checkbox" name="managerRecord" id="managerRecord" onclick="toggle('Record','0');"/>
						</s:else>
							Apply reporting hierarchy defined in Official Details
						</td>
						<td width="13%" colspan="1">Manager Levels:</td>
						<td width="7%" colspan="1">
							<s:textfield name="managerLevel" size="5" onkeypress="return numbersOnly();"/>
						</td>
						<td colspan="1" width="30%"><input type="button" class="add"
						theme="simple" value=" Add Exceptions"
						onclick="return addExceptions();" /></td>
					</tr>
					
					<s:hidden name = "fromEmpIdValues" />
					<s:hidden name = "toEmpIdValues" />
						<%
							int i = 1;
								int count = 0;
						%>
						<s:iterator value="exceptionEmpList" status="stat">
							<s:hidden name="fromEmpIdIt" id="<%= "fromEmpIdIt"+i %>" />
							<s:hidden name="fromEmpTokenIt" id="<%= "fromEmpTokenIt"+i %>" />
							<s:hidden name="fromEmpNameIt" id="<%= "fromEmpNameIt"+i %>" />
							<s:hidden name="excepSrNoIterator"
								id="<%= "excepSrNoIterator"+i %>" />
							<s:hidden name="toEmpIdIt" id="<%= "toEmpIdIt"+i %>" />
							<s:hidden name="toEmpTokenIt" id="<%= "toEmpTokenIt"+i %>" />
							<s:hidden name="toEmpNameIt" id="<%= "toEmpNameIt"+i %>" />

							<%
								i++;
										count++;
							%>
						</s:iterator>
						<s:hidden name="ReportingStr.Structure" />
						<s:hidden name="excepSrNo" />
						<input type="hidden" name="excepRowNum" id="excepRowNum"
							value="<%=count %>" />
					
					<tr>
						<td width="100%" colspan="6">&nbsp;</td>
					</tr>
						
					<tr>
						<td width="100%" colspan="6" align="center"><input type="button" class="save" theme="simple"  
								value=" Process" onclick="return validateProcess();"/></td>
					</tr>
				</table>
			</td>
		</tr>
	
		<tr>
			<td width="100%" colspan="6">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
					<td width="100%" colspan="6"><strong class="formhead">
					If hierarchy different from Reporting Manager under Official Details
					</strong></td>
					</tr>
					
					<tr>
						<td width="100%" colspan="6">
							<input type="checkbox" name="newRecord" id="newRecord" onclick="toggle('Record','1');"/>
							Define new/update hierarchy for <%=structureType%>
							&nbsp;&nbsp;&nbsp;<input type="button" class="token" theme="simple"  
								value="   Go   " onclick="return validateGo();"/>
						</td>
					</tr>
					
						
<!--					<tr>-->
<!--						<td width="25%" colspan="1">&nbsp;</td>-->
<!--						<td width="15%" colspan="1" align="center"><input type="button" class="token" theme="simple"  -->
<!--								value="   Go   " onclick="return validateGo();"/></td>-->
<!--					</tr>-->
				</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
checkSelectedCheckBox();

function checkSelectedCheckBox(){
	var defaultCheckBox = document.getElementById('defaultCheck').checked;
	var sameAsCheckBox  = document.getElementById('sameAsCheck').checked;
	var managerCheck	= document.getElementById('managerRecord').checked;
	
	if(defaultCheckBox || sameAsCheckBox || managerCheck){
		document.getElementById('paraFrm_isSelected').value = 'true';
	}
}

function validateCheckBox(checkBox){
	if(checkBox == 'default'){
		document.getElementById('newRecord').checked = false;
		//document.getElementById('managerRecord').checked = false;
		document.getElementById('sameAsCheck').checked = false;
	}
	
	if(checkBox == 'same'){
		document.getElementById('defaultCheck').checked = false;
		document.getElementById('newRecord').checked = false;
		//document.getElementById('managerRecord').checked = false;
	}
}

function toggle(id,type){
	//alert(type);
	if(type==0){	
		if(document.getElementById('manager'+id).checked){
			document.getElementById('new'+id).checked=false;
			//document.getElementById('defaultCheck').checked = false;
			//document.getElementById('sameAsCheck').checked = false;
		}
	}
	else{	
		//document.getElementById('newRecord').checked=false;
		if(document.getElementById('new'+id).checked)
			document.getElementById('manager'+id).checked=false;
		
	}
}

function addExceptions(){
	try{
	if(!document.getElementById('managerRecord').checked){
		alert("Please check Apply Reporting Manager under Official Details as reporting hierarchy");
		return false;
	}
	var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
	document.getElementById('paraFrm').target = "wind";
	document.getElementById('paraFrm').action = "ReportingStr_gotoExceptionPage.action";
	document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}






function validateProcess(){
	var defaultCheck    = document.getElementById('defaultCheck').checked;
	var sameCheck       = document.getElementById('sameAsCheck').checked;
	var managerCheck	= document.getElementById('managerRecord').checked;
	var hiddenStructure = document.getElementById('paraFrm_hiddenStructure').value;
	
	var isSelected    = document.getElementById('paraFrm_isSelected').value;
	//alert(isSelected);
	
	if(isSelected && !defaultCheck && !sameCheck && !managerCheck){
		var conf = confirm("This will remove the manager level/default/same as hierarchy of "+ hiddenStructure+" .Do you really want to proceed ? ");
		
		if(conf) {
			document.getElementById('paraFrm').action = "ReportingStr_rollBack.action?structureKey="+hiddenStructure;
			document.getElementById('paraFrm').submit();
		}
		return false;
	}
	
	if(isSelected == "" && !defaultCheck && !sameCheck && !managerCheck){
		alert('Please select atleast one checkbox to process');
		 document.getElementById('managerCheck').focus();
		return false;
	}
	
	if(defaultCheck){
		var conf = confirm("The reporting hierarchy of "+hiddenStructure+" will be applied to all types of applictions.\n Click ok to proceed and click cancel to stop this action.");
	     
	  	if(conf) {
	  		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "ReportingStr_defaultFunction.action?structureKey="+hiddenStructure;
			document.getElementById('paraFrm').submit();
		}
	}
	
	if(sameCheck){
		var selectValue = document.getElementById('paraFrm_sameStr').value ;
		if(selectValue == ""){
			alert('Please select a value from the drop down list');
			document.getElementById('paraFrm_sameStr').focus();
			return false;
		}
		
		//ADDED BY REEBA
		 var getSelectedValue = document.getElementById('paraFrm_sameStr');
		 var selectedValue = getSelectedValue.options[getSelectedValue.selectedIndex].text;
		
		var conf = confirm("Do you really want to make reporting hierarchy for "+hiddenStructure+" same as "+selectedValue+" ?");
   
	  	if(conf) {
	  		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "ReportingStr_sameAsFunction.action?structureKey="+hiddenStructure;
			document.getElementById('paraFrm').submit();
		}
	}
	
	if(managerCheck){
		var conf = confirm("The reporting hierarchy of "+hiddenStructure+" will be applied as per the reporting manager defined under Official details."
								+"\n Click OK to proceed or CANCEL to stop this action.");
	     
	  	if(conf) {
	  		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "ReportingStr_saveManagerHierarchy.action?structureKey="+hiddenStructure;
			document.getElementById('paraFrm').submit();
		}
	}
}

function validateGo(){
	var newCheck = document.getElementById('newRecord').checked;
	var hiddenStructure = document.getElementById('paraFrm_hiddenStructure').value;
	
	if(!newCheck){
		alert('Please select the checkbox');
		document.getElementById('newRecord').focus();
		return false;
	}else{
		document.getElementById('paraFrm').action = "ReportingStr_forwardToMainPage.action?structureKey="+hiddenStructure;
		document.getElementById('paraFrm').submit();
	}
	/*else{
		 var conf = confirm("Do you really want to make separate reporting hierarchy for "+hiddenStructure+"?");
		 
		 if(conf) {
			document.getElementById('paraFrm').action = "ReportingStr_forwardToMainPage.action?structureKey="+hiddenStructure;
			document.getElementById('paraFrm').submit();
		}
	}*/
}

function defaultFun(){
    //alert("hi"+document.getElementById('defaultCheck').checked);
     var checkDefault = document.getElementById('defaultCheck').checked;
     
    if(checkDefault)
     	var conf=confirm("This reporting hierarchy will be applied to all types of applictions.\n Click ok to proceed and click cancel to stop this action.");
     else
        var conf=confirm("Do you really want to make this record other than default record ?");
        
  	if(conf) {
		document.getElementById('paraFrm').action="ReportingStr_defaultFunction.action";
		document.getElementById('paraFrm').submit();
	}
}

function setSameStructure(obj){
	
   //  alert("hi "+document.getElementById('paraFrm_sameStr').value);
      var checkSame = document.getElementById('sameAsCheck').checked;
  	  var checkDept = document.getElementById('paraFrm_sameStr').value ;
  	// alert("obj"+obj);
  	  if(obj == 'drop'){
	  	  if(!checkSame){
			  alert("Please select the check box ");
			  document.getElementById('sameAsCheck').focus();
			  return false;
		  }
  	  }
  	 if(checkDept == ""){
		alert("Please select from drop down");
		//document.getElementById('sameAsCheck').checked="";
		document.getElementById('paraFrm_sameStr').focus();
		return false;
	}
		
    if(checkSame)
     	var conf=confirm("Do you really want to make this record same as "+checkDept+" ?");
     else
        var conf=confirm("Do you really want to make this record separate ?");
  	if(conf) {
		document.getElementById('paraFrm').action="ReportingStr_sameAsFunction.action";
		document.getElementById('paraFrm').submit();
	}
}

function report(){
	var structureFor = document.getElementById('paraFrm_structureFor').value;
	callReport('ReportingStr_report.action?reportType=R&structureKey='+structureFor)
}
</script>

