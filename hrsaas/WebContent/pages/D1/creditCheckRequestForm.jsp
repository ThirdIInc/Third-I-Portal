<!-- Added by manish sakpal  on 10th March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CreditCheckRequest" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Credit Check Request</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span> <font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentsFlag">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
						}
					%>
				</table>
			 </td>
		  </tr>
		  </s:if>
		<!-- Approver Comments Section Ends -->
		
		<!-- Section I Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" width="25%">
						<label name="creditRequestingPerson" id="creditRequestingPerson"
							ondblclick="callShowDiv(this);"> <%=label.get("creditRequestingPerson")%>
						</label> :
					</td>
					<td colspan="3">						
						<s:hidden name="creditRequestingPersonID" />
						<s:textfield name="creditRequestingPersonToken" size="18" readonly="true" /> 
						<s:textfield name="creditRequestingPersonName" size="80" readonly="true" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="25%">
						<label name="creditAuthorizingPerson" id="creditAuthorizingPerson"
							ondblclick="callShowDiv(this);"> <%=label.get("creditAuthorizingPerson")%>
						</label> :
					</td>
					<td height="22" colspan="3">						
						<s:hidden name="creditAuthorizingPersonID" />
						<s:textfield name="creditAuthorizingPersonToken" size="18" readonly="true" />
						<s:textfield name="creditAuthorizingPersonName" size="80" readonly="true" />
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif"
							height="16" align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CreditCheckRequest_f9Authorizer.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Section I Ends -->
		
		
		<!-- Section II Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					 <td colspan="4">
					 	<b>A Credit Check is requested for the following.
					 	   Please provide the following information.
					 	</b>
					 </td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="companyName" id="companyName"
							ondblclick="callShowDiv(this);"> <%=label.get("companyName")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="companyName" size="30" maxlength="40"/>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="streetAddress" id="streetAddress"
							ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textarea name="streetAddress" cols="32" rows="3" onkeypress="return imposeMaxLength(event, this, 200);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_streetAddress','streetAddress','', '', '200','200');">		
					</td>
					
					<td class="formtext" width="15%">
						<label name="phoneNumber" id="phoneNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("phoneNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="phoneNumber" size="30" maxlength="20" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="city" id="city"
							ondblclick="callShowDiv(this);"> <%=label.get("city")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" colspan="4">
						<s:hidden name="cityId"/>						
						<s:textfield name="cityName" size="30" maxlength="40"/>
					<!-- 
								<img id='ctrlHide' src="../pages/images/recruitment/search2.gif"
							height="16" align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CreditCheckRequest_f9city.action');">
					 -->	
					
					</td>					
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="state" id="state"
							ondblclick="callShowDiv(this);"> <%=label.get("state")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="state" size="30" maxlength="40"/>
					</td>
					
					<td class="formtext" width="15%">
						<label name="zipCode" id="zipCode"
							ondblclick="callShowDiv(this);"> <%=label.get("zipCode")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="zipCode" size="30" maxlength="20" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="amountOfCreditToBeAdvance" id="amountOfCreditToBeAdvance"
							ondblclick="callShowDiv(this);"> <%=label.get("amountOfCreditToBeAdvance")%>
						</label> :
					</td>
					<td height="22" colspan="4">
						<s:textfield name="amountOfCreditToBeAdvance" size="30" maxlength="15" onkeypress="return numbersWithDot();"/>
					</td>					
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="isMonthlyAnnually" id="isMonthlyAnnually"
							ondblclick="callShowDiv(this);"> <%=label.get("isMonthlyAnnually")%>
						</label> :
					</td>
					<td height="22" colspan="2">
						<s:radio name="isMonthlyAnnually" value="%{isMonthlyAnnually}" list="#{'an':'Annual'}"
							onclick="setIsMonthlyAnnually(this);">
						</s:radio>&nbsp;&nbsp;
						<s:radio name="isMonthlyAnnually" value="%{isMonthlyAnnually}" list="#{'mo':'Monthly'}"
							onclick="setIsMonthlyAnnually(this);">
						</s:radio>&nbsp;&nbsp;
						<s:radio name="isMonthlyAnnually" value="%{isMonthlyAnnually}" list="#{'ti':'Time'}"
							onclick="setIsMonthlyAnnually(this);">
						</s:radio>
					</td>					
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="isExistingCustomer" id="isExistingCustomer"
							ondblclick="callShowDiv(this);"> <%=label.get("isExistingCustomer")%>
						</label> :
					</td>
					<td height="22" colspan="2">
						<s:radio name="isExistingCustomer" value="%{isExistingCustomer}" list="#{'ys':'Yes'}"
							onclick="setIsExistingCustomer(this);">
						</s:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:radio name="isExistingCustomer" value="%{isExistingCustomer}" list="#{'no':'No'}"
							onclick="setIsExistingCustomer(this);">
						</s:radio>
					</td>					
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="customerName" id="customerName"
							ondblclick="callShowDiv(this);"> <%=label.get("customerName")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="customerName" maxlength="35" size="30" />
					</td>
					
					<td class="formtext" width="15%">
						<label name="typeOfEquipment" id="typeOfEquipment"
							ondblclick="callShowDiv(this);"> <%=label.get("typeOfEquipment")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="typeOfEquipment" maxlength="30" size="30" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="comments" id="comments"
							ondblclick="callShowDiv(this);"> <%=label.get("comments")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textarea name="comments" cols="32" rows="4" onkeypress="return imposeMaxLength(event, this, 200);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments','comments','', '', '200','200');">
					</td>
					
					<td class="formtext" width="15%">
						<label name="numberOfSites" id="numberOfSites"
							ondblclick="callShowDiv(this);"> <%=label.get("numberOfSites")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="numberOfSites" maxlength="20" size="30" onkeypress="return numbersOnly();"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Section II Ends -->
		
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td width="15%">
						<b><label name="completedBy" id="completedBy"
							ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
						</label></b> :
					</td>
					<td width="25%">						
						<s:hidden name="completedByID" />
						<s:property value="completedBy"/>
					</td>
					<td width="15%">
						<b><label name="completedDate" id="completedDate"
							ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
						</label></b> :
					</td>
					<td width="25%">						
						<s:hidden name="completedDate" />
						<s:property value="completedDate" />
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
				</tr>
			</table>
			</td>
		</tr>
	<s:hidden name="creditID" />
	<s:hidden name="trackingNumber" />	
	<s:hidden name="status" />
	<input type="hidden" name="isMonthlyAnnuallyRadioOptionValue" id="isMonthlyAnnuallyRadioOptionValue"
			value='<s:property value="isMonthlyAnnuallyRadioOptionValue"/>' />
	<input type="hidden" name="isExistingCustomerRadioOptionValue" id="isExistingCustomerRadioOptionValue"
			value='<s:property value="isExistingCustomerRadioOptionValue"/>' />
	</table>
</s:form>

<script>
function setIsMonthlyAnnually(id){
	var opt=document.getElementById('isMonthlyAnnuallyRadioOptionValue').value =id.value;	
}

function setIsExistingCustomer(id){
	var opt=document.getElementById('isExistingCustomerRadioOptionValue').value =id.value;	
}

	function sendforapprovalFun()
{		
	try
	{
		var creditAuthorizingPersonNameVar = document.getElementById('paraFrm_creditAuthorizingPersonName').value;
		if(creditAuthorizingPersonNameVar == "")
		{
			alert("Please select "+document.getElementById('creditAuthorizingPerson').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_creditAuthorizingPersonName').focus();
		 	return false;		
		}
		
		var companyNameVar = document.getElementById('paraFrm_companyName').value;
		if(companyNameVar == "")
		{
			alert("Please enter "+document.getElementById('companyName').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_companyName').focus();
		 	return false;		
		}
		
		var streetAddressVar = document.getElementById('paraFrm_streetAddress').value;
		if(streetAddressVar == "")
		{
			alert("Please enter "+document.getElementById('streetAddress').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_streetAddress').focus();
		 	return false;		
		}
		
		var cityNameVar = document.getElementById('paraFrm_cityName').value;
		if(cityNameVar == "")
		{
			alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_cityName').focus();
		 	return false;		
		}

		var stateVar = document.getElementById('paraFrm_state').value;
		if(stateVar == "")
		{
			alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_state').focus();
		 	return false;		
		}
		
		var zipCodeVar = document.getElementById('paraFrm_zipCode').value;
		if(zipCodeVar == "")
		{
			alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_zipCode').focus();
		 	return false;		
		}
		
		
		var amountOfCreditToBeAdvanceVar = trim(document.getElementById('paraFrm_amountOfCreditToBeAdvance').value);
		if(amountOfCreditToBeAdvanceVar!=""){
			if(isNaN(document.getElementById('paraFrm_amountOfCreditToBeAdvance').value)){
				alert('Please enter amount of credit to be advanced in number');
				document.getElementById('paraFrm_amountOfCreditToBeAdvance').focus();
				return false;			
			}		
		}
		
		var numberOfSitesVar = trim(document.getElementById('paraFrm_numberOfSites').value);
		if(numberOfSitesVar!=""){
			if(isNaN(document.getElementById('paraFrm_numberOfSites').value)){
				alert('Please enter data in numbers only for number of sites');
				document.getElementById('paraFrm_numberOfSites').focus();
				return false;			
			}
		}
		
	}catch(e)
	  {
			alert("Exception occurred in send for approver function."+e);
	  }
		
	 var con=confirm('Do you really want to send this application for approval?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'P';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='CreditCheckRequest_sendForApprovalFunction.action';
		document.getElementById('paraFrm').submit();
	}		
}


function draftFun()
{	
	try
	{
		var creditAuthorizingPersonNameVar = document.getElementById('paraFrm_creditAuthorizingPersonName').value;
		if(creditAuthorizingPersonNameVar == "")
		{
			alert("Please select "+document.getElementById('creditAuthorizingPerson').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_creditAuthorizingPersonName').focus();
		 	return false;		
		}
		
		var companyNameVar = document.getElementById('paraFrm_companyName').value;
		if(companyNameVar == "")
		{
			alert("Please enter "+document.getElementById('companyName').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_companyName').focus();
		 	return false;		
		}
		
		var streetAddressVar = document.getElementById('paraFrm_streetAddress').value;
		if(streetAddressVar == "")
		{
			alert("Please enter "+document.getElementById('streetAddress').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_streetAddress').focus();
		 	return false;		
		}
		
		var cityNameVar = document.getElementById('paraFrm_cityName').value;
		if(cityNameVar == "")
		{
			alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_cityName').focus();
		 	return false;		
		}

		var stateVar = document.getElementById('paraFrm_state').value;
		if(stateVar == "")
		{
			alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_state').focus();
		 	return false;		
		}
		
		var zipCodeVar = document.getElementById('paraFrm_zipCode').value;
		if(zipCodeVar == "")
		{
			alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase()+".");
		  	document.getElementById('paraFrm_zipCode').focus();
		 	return false;		
		}
		
		var amountOfCreditToBeAdvanceVar = trim(document.getElementById('paraFrm_amountOfCreditToBeAdvance').value);
		if(amountOfCreditToBeAdvanceVar!=""){
			if(isNaN(document.getElementById('paraFrm_amountOfCreditToBeAdvance').value)){
				alert('Please enter amount of credit to be advanced in number');
				document.getElementById('paraFrm_amountOfCreditToBeAdvance').focus();
				return false;			
			}		
		}
		
		var numberOfSitesVar = trim(document.getElementById('paraFrm_numberOfSites').value);
		if(numberOfSitesVar!=""){
			if(isNaN(document.getElementById('paraFrm_numberOfSites').value)){
				alert('Please enter data in numbers only for number of sites');
				document.getElementById('paraFrm_numberOfSites').focus();
				return false;			
			}
		}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		document.getElementById('paraFrm_status').value = 'D';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='CreditCheckRequest_draftFunction.action';
	  	document.getElementById('paraFrm').submit();
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'CreditCheckRequest_reset.action';
     	document.getElementById('paraFrm').submit();
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CreditCheckRequest_back.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}

function deleteFun() 
{
	 var con=confirm('Do you want to delete this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CreditCheckRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'C';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CreditCheckRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}

function imposeMaxLength(Event, Object, MaxLen)
{
  return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}	
</script>	