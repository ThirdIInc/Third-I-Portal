<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CustomerMaster" validate="true" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />

	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>
						<strong class="text_head"> 
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /> 
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Customer Details</strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
						<div align="right"><span class="style2"></span>
							<font color="red">*</font>Indicates Required
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td class="formtext" width="20%">
							<label name="customer.name" id="customer.name" ondblclick="callShowDiv(this);"> <%=label.get("customer.name")%></label><font color="red">*</font>:
						</td>
						<td height="22" colspan="2">
							<s:textfield name="customerName" size="30" onkeypress="return allCharacters(this);" />
						</td>
					</tr>
					
					
					
					<tr>
						<td width="20%" class="formtext">
							<label name="customer.project" id="customer.project" ondblclick="callShowDiv(this);"><%=label.get("customer.project")%></label><font color="red">*</font>:
						</td>
						<td>
							<s:hidden theme="simple" name="projectId" /> 
							<s:textfield size="30" theme="simple" name="projectName" readonly="true" /> 
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearchProject();">
						</td>
					</tr> 
					
					<tr>
						<td width="20%" class="formtext">
							<label name="contact.person"	id="contact.person" ondblclick="callShowDiv(this);"><%=label.get("contact.person")%></label> :
						</td>
						<td height="22" colspan="2">
							<s:textfield name="contactPerson" size="30" onkeypress="return allCharacters(this);" />
						</td>
					</tr>

					<tr>
					<td width="20%" class="formtext">
						<label name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label> :
					</td>
					<td colspan="4">
						<s:textarea name="address" cols="50" rows="3" onkeyup="callLength('address','addresslength','2000');" /> 
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide' width="12" theme="simple" onclick="javascript:callWindow('paraFrm_address','address','', 'praFrm_addresslength', '2000','2000');">&nbsp;&nbsp;
					</td>	
					<td id='ctrlHide'>Remaining chars <s:textfield name="addresslength" readonly="true" size="5"></s:textfield></td>
				</tr>


					<tr>
						<td width="20%" class="formtext">
							<label name="customer.city" id="customer.city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :
						</td>
						<td>
							<s:hidden theme="simple" name="cityId" /> 
							<s:textfield size="30" theme="simple" name="city" readonly="true" /> 
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearchCity();">
						</td>
					</tr> 				
					
					
					<tr>
						<td width="20%" class="formtext">
							<label name="customer.phone" id="customer.phone" ondblclick="callShowDiv(this);"><%=label.get("customer.phone")%></label>:
						</td>
						<td height="22" colspan="2">
							<s:textfield name="phone" size="30" maxlength="15" onkeypress="return numbersOnly();"/>
						</td>
					</tr>
				

					<tr>
						<td width="20%" class="formtext">
							<label name="customer.email" id="customer.email" ondblclick="callShowDiv(this);"><%=label.get("customer.email")%></label>:
						</td>
						<td height="22" colspan="2">
							<s:textfield name="emailId"	size="30" onkeypress="return validateEmail(this);"/>
						</td>
					</tr>
			</table>
		   </td>
		</tr>

		
		
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="78%">
						  	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					 	</td>
					</tr>
				</table>
			</td>
		</tr>

	</table>
	<s:hidden name="customercode" />
</s:form>

<script>

function saveFun()
{
		var customerNameVar = trim(document.getElementById('paraFrm_customerName').value);
		var projectNameVar = trim(document.getElementById('paraFrm_projectName').value);
		if(customerNameVar=="") {			
			alert("Please enter "+document.getElementById('customer.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_customerName').focus();
		 	return false;
		}
		
		if(projectNameVar=="") {			
			alert("Please select "+document.getElementById('customer.project').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_projectName').focus();
		 	return false;
		}
		
	 
     	var addressVar = trim(document.getElementById('paraFrm_address').value); 
		if(addressVar != "" && addressVar.length >2000) {
			alert("Maximum length of "+document.getElementById('address').innerHTML.toLowerCase()+" is 2000 characters.");
			return false;
    	}
     	 
		 	
	 	for(i=0;i<2;i++){
		 	var id=document.getElementById("save").id="save"+i;
	     	document.getElementById(id).disabled="true";
		}
		document.getElementById('paraFrm').target = "_self";
     		document.getElementById('paraFrm').action='CustomerMaster_save.action';
  		document.getElementById('paraFrm').submit();
		 
}


function resetFun()  {
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'CustomerMaster_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	return true;
}

function backFun()  {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CustomerMaster_back.action';
		document.getElementById('paraFrm').submit();
}

function editFun() {
		document.getElementById("paraFrm").action="CustomerMaster_edit.action";
	    document.getElementById("paraFrm").submit();
       return false;
}


function callSearchCity() {
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'CustomerMaster_f9city.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e)
	{
		alert("Error : "+e);
	}	
}	


function callSearchProject() 
{
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'CustomerMaster_f9Project.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e)
	{
		alert("Error : "+e);
	}	
}	


function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CustomerMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
}


</script>