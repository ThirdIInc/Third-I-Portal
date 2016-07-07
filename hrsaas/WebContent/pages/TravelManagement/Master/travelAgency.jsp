<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelAgencyList" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />


	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel Agency Detail</strong></td>
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
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td class="formtext" width="20%">
						<label name="agency.name"	id="agency.name" ondblclick="callShowDiv(this);"> <%=label.get("agency.name")%>	</label><font color="red">*</font>:
					</td>
					<td height="22" colspan="2"><s:textfield name="agencyName"
						size="30" onkeypress="return allCharacters();" /></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="contactPerson"	id="contactPerson" ondblclick="callShowDiv(this);"><%=label.get("contact.person")%>:</label><font color="red">*</font>:
					</td>
					<td height="22" colspan="2"><s:textfield name="contactPerson"
						size="30" onkeypress="return allCharacters();" /></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label><font color="red">*</font>:
					</td>

					<td colspan="1"><s:textarea name="address" cols="50" rows="3"
						onkeyup="callLength('address','addresslength','2000');" /> 
							<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_address','address','', 'praFrm_addresslength', '2000','2000');">&nbsp;&nbsp;
						</td>
						<td id='ctrlHide' colspan="1">Remaining chars <s:textfield name="addresslength"
						readonly="true" size="5"></s:textfield></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="city" id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label> <font color="red">*</font>:
					</td>
					<td colspan="2"><s:hidden theme="simple" name="cityId" /> <s:textfield
						size="30" theme="simple" name="city" readonly="true" /> <img
						id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearchCity();"></td>
				</tr>
 


				<tr>
					<td width="20%" class="formtext">
						<label name="phone1" id="phone1" ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label>:
					</td>
					<td height="22" colspan="2"><s:textfield name="phone1"
						size="30"  onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="phone2" id="phone2" ondblclick="callShowDiv(this);"><%=label.get("phone2")%></label>:
					</td>
					<td height="22" colspan="2"><s:textfield name="phone2"
						size="30" onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="emailId1" id="emailId1" ondblclick="callShowDiv(this);"><%=label.get("emailId1")%></label>:
					</td>
					<td height="22" colspan="2"><s:textfield name="emailId1" size="30" /></td>
				</tr>

				<tr>
					<td width="20%" class="formtext">
						<label name="emailId2"	id="emailId2" ondblclick="callShowDiv(this);"><%=label.get("emailId2")%></label>:
					</td>
					<td height="22" colspan="2"><s:textfield name="emailId2" size="30" /></td>
				</tr>



				<tr>
					<td width="20%">
						<label class="set" name="travelmode" id="travelmode" ondblclick="callShowDiv(this);"><%=label.get("travelmode")%></label>
					<font color="red">*</font>:</td>
					<td width="76%" colspan="2"><s:hidden name="travelmodeCode"></s:hidden> <s:textarea
						name="travelmode" cols="30" rows="3" readonly="true"></s:textarea>
					<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function(); "></td>

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

				</tr>
			</table>
			</td>
		</tr>
<s:hidden name="agencycode" />
	</table>
	
</s:form>

<script>

function saveFun()
{

	
		var agencyNameVar = trim(document.getElementById('paraFrm_agencyName').value);
		
		if(agencyNameVar=="")
		{			
			alert("Please enter "+document.getElementById('agency.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_agencyName').focus();
		 	return false;
		}
		
		var contactPersonVar = trim(document.getElementById('paraFrm_contactPerson').value);
		if(contactPersonVar=="")
		{
			alert("Please enter "+document.getElementById('contactPerson').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_contactPerson').focus();
		 	return false;
		}
     	
     	var addressVar = trim(document.getElementById('paraFrm_address').value); 
     	if(addressVar=="")
		{
			alert("Please enter "+document.getElementById('address').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_address').focus();
		 	return false;
		} 
     	 
     	 if(addressVar != "" && addressVar.length >500)
		{
			alert("Maximum length of "+document.getElementById('address').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
    	}
     	 
     	 
     	 var cityVar = trim(document.getElementById('paraFrm_city').value);
     	if(cityVar=="")
		{
			alert("Please select "+document.getElementById('city').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_city').focus();
		 	return false;
		} 
		
		
		
		
		var travelmodeVar = trim(document.getElementById('paraFrm_travelmode').value);
     	if(travelmodeVar=="")
		{
			alert("Please select "+document.getElementById('travelmode').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_travelmode').focus();
		 	return false;
		} 
		
		/*
		var emailID1var = document.getElementById("paraFrm_emailId1").value;
		var emailID2var = document.getElementById("paraFrm_emailId2").value;
		if(emailID1var=="")
		{
			return true;
		}
		else
		{			
			if(!validateEmail('paraFrm_emailId1'))
   			{   
				return false;
			} 
		}	
		
		if(!validateEmail('paraFrm_emailId2'))
   		{   
			return false;
			} 	
		*/
		
		// For disabaling the button after clicking once	
		/*for (var i = 0; i < document.all.length; i++) 
		{
			if(document.all[i].id == 'save') 
			{
				
				document.all[i].disabled=true;
			}
		}*/
		
		for(i=0;i<2;i++){
		 	var id=document.getElementById("save").id="save"+i;
	     	document.getElementById(id).disabled="true";
		}
				
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='TravelAgencyList_save.action';
	  	document.getElementById('paraFrm').submit();		
		  
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'TravelAgencyList_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	return true;
}

function backFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TravelAgencyList_back.action';
		document.getElementById('paraFrm').submit();
}



function editFun()
{
		document.getElementById("paraFrm").action="TravelAgencyList_edit.action";
	    document.getElementById("paraFrm").submit();
       return false;
}


function callSearchCity() 
{
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'TravelAgencyList_f9city.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e)
	{
		alert("Error : "+e);
	}	
}	


	  function callF9Function()
 {   
 
   callsF9(500,325,'TravelAgencyList_f9selectTravelMode.action');
 }

function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TravelAgencyList_delete.action';
		document.getElementById('paraFrm').submit();
	}
}


</script>