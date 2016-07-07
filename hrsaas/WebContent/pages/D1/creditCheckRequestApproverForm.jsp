<!-- Added by manish sakpal  on 11th March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CreditCheckRequestApprover" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Credit Check Request Approval</strong></td>
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
		
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><b>Approver Comments</b></td>
						<s:if test="%{newStatus == 'Approved' || newStatus == 'Rejected'}">
							<td colspan="3">
								<s:textarea theme="simple" cols="70" rows="3" name="approverComments" />
							</td>
						</s:if>
						<s:else>	
							<td colspan="3" id="ctrlShow" >
								<s:textarea theme="simple" cols="70" rows="3" name="approverComments" />
							</td>
						</s:else>		
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Approved Date</td>
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
						<s:textfield name="creditRequestingPersonToken" size="15" readonly="true" /> 
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
						<s:textfield name="creditAuthorizingPersonToken" size="15" readonly="true" />
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
						<s:textfield name="companyName" size="30" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="streetAddress" id="streetAddress"
							ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textarea name="streetAddress" cols="31" rows="3"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_streetAddress','streetAddress','', '', '2000','2000');">		
					</td>
					
					<td class="formtext" width="15%">
						<label name="phoneNumber" id="phoneNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("phoneNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="phoneNumber" size="30" maxlength="20" onkeypress="return numbersWithHiphen();"/>
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
						<s:textfield name="cityName" size="30" readonly="true"/>
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif"
							height="16" align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CreditCheckRequest_f9city.action');">
					</td>					
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="state" id="state"
							ondblclick="callShowDiv(this);"> <%=label.get("state")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="state" size="30" />
					</td>
					
					<td class="formtext" width="15%">
						<label name="zipCode" id="zipCode"
							ondblclick="callShowDiv(this);"> <%=label.get("zipCode")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="zipCode" size="30" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="amountOfCreditToBeAdvance" id="amountOfCreditToBeAdvance"
							ondblclick="callShowDiv(this);"> <%=label.get("amountOfCreditToBeAdvance")%>
						</label> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="amountOfCreditToBeAdvance" size="30" onkeypress="return numbersWithDot();"/>
					</td>
					<td class="formtext" width="15%">
						Status : 
					</td>
					<td height="22" width="25%">						
						<s:select name="newStatus" cssStyle="width:130" disabled="true" id="ctrlShow" list="#{'Pending':'Pending','Approved':'Approved','Rejected':'Rejected'}" />					
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
						<s:textfield name="customerName" size="30" />
					</td>
					
					<td class="formtext" width="15%">
						<label name="typeOfEquipment" id="typeOfEquipment"
							ondblclick="callShowDiv(this);"> <%=label.get("typeOfEquipment")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="typeOfEquipment" size="30" />
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="comments" id="comments"
							ondblclick="callShowDiv(this);"> <%=label.get("comments")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textarea name="comments" cols="31" rows="4"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments','comments','', '', '2000','2000');">
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
	<s:hidden name="status" />
	<s:hidden name="trackingNumber" />
	<input type="hidden" name="isMonthlyAnnuallyRadioOptionValue" id="isMonthlyAnnuallyRadioOptionValue"
			value='<s:property value="isMonthlyAnnuallyRadioOptionValue"/>' />
	<input type="hidden" name="isExistingCustomerRadioOptionValue" id="isExistingCustomerRadioOptionValue"
			value='<s:property value="isExistingCustomerRadioOptionValue"/>' />
	</table>
</s:form>

<script>
function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		if(document.getElementById('paraFrm_status').value == 'P')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}
		
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'X';
		}
			
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'CreditCheckRequestApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'Z';
		}else {
			document.getElementById('paraFrm_status').value = 'R';		
		}
			
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'CreditCheckRequestApprover_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm_status').value = 'B';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'CreditCheckRequestApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CreditCheckRequestApprover_backToListPage.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
</script>	