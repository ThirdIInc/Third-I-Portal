<!-- Added by manish sakpal (2nd Feb 2011)-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="DisputeManagementSystem" validate="true" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<s:hidden name="disputeID" />	
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
						<strong class="text_head">Dispute Management System</strong>
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
							<label class="set" name="disputeRaisedBy" id="disputeRaisedBy"
										ondblclick="callShowDiv(this);"> <%=label.get("disputeRaisedBy")%>
									</label><font color="red">*</font> :
						</td>
						<td height="22" colspan="2">
							<s:hidden name="disputeRaisedID" /> 
							<s:textfield name="disputeRaisedBy" size="30" readonly="true" />
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearchDisputeRaisedBy();">
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="typeOfDispute" id="typeOfDispute"
										ondblclick="callShowDiv(this);"> <%=label.get("typeOfDispute")%>
									</label> : 	
						</td>
						<td height="22" colspan="2">
							<s:select headerKey="" headerValue="---- Select type of dispute ----" name="typeOfDispute" list="#{'L':'Violation of Labour act ','B':'Violation of Bonus policy'}"></s:select>
						</td>
					</tr> 
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="underAct" id="underAct"
										ondblclick="callShowDiv(this);"> <%=label.get("underAct")%>
									</label> : 	
						</td>
						<td height="22" colspan="2">
							<s:hidden name="underActID" />
							<s:textfield name="underAct" size="30" readonly="true" />
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearchUnderAct();">
						</td>
					</tr>

					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="disputeStatement" id="disputeStatement"
										ondblclick="callShowDiv(this);"> <%=label.get("disputeStatement")%>
									</label> :  
						</td>
						<td height="22" colspan="2">
							<s:textfield name="disputeStatement" size="30" />
						</td>	
					</tr>


					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="description" id="description"
										ondblclick="callShowDiv(this);"> <%=label.get("description")%>
							</label> : 	
						</td>
						<td height="22" colspan="2">
							<s:textarea name="description" cols="50" rows="3" />
						</td>
					</tr> 				
					
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="loggedOnDateLabel" id="loggedOnDateLabel"
										ondblclick="callShowDiv(this);"> <%=label.get("loggedOnDateLabel")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:textfield  size="30" maxlength="10" theme="simple" name="loggedOnDate" value="%{loggedOnDate}" />
							<a	href="javascript:NewCal('paraFrm_loggedOnDate','DDMMYYYY');"> 
										<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage" height="16"
										align="absmiddle" width="16"> 
							</a> 
						</td>
					</tr>

					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="resolutionStatement" id="resolutionStatement"
										ondblclick="callShowDiv(this);"> <%=label.get("resolutionStatement")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:textfield name="resolutionStatement"	size="30" />
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="resolutionMethod" id="resolutionMethod"
										ondblclick="callShowDiv(this);"> <%=label.get("resolutionMethod")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:select headerKey="" headerValue="---- Select type of method ----" name="resolutionMethod" list="#{'N':'Negotiation','C':'Concillation','O':'Other'}"></s:select>
						</td>
					</tr>		
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="committeeName" id="committeeName"
										ondblclick="callShowDiv(this);"> <%=label.get("committeeName")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:hidden name="committeeID" />
							<s:textfield name="committeeName" size="30"/>
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearchCommitteeName();">
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="committeeType" id="committeeType"
										ondblclick="callShowDiv(this);"> <%=label.get("committeeType")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:textfield name="committeeType" size="30" readonly="true" />
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="representativeName" id="representativeName"
										ondblclick="callShowDiv(this);"> <%=label.get("representativeName")%>
									</label> : 
						</td>
						<td height="22" colspan="2">							
							<s:textfield name="representativeName" size="30" readonly="true" />							
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="formtext">
							<label class="set" name="otherInvolvedParties" id="otherInvolvedParties"
										ondblclick="callShowDiv(this);"> <%=label.get("otherInvolvedParties")%>
									</label> : 
						</td>
						<td height="22" colspan="2">
							<s:textfield name="otherInvolvedParties" size="30"/>
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
</s:form>

<script>

function saveFun()
{
		var disputeRaisedByVar = trim(document.getElementById('paraFrm_disputeRaisedBy').value);
		if(disputeRaisedByVar=="")
		{			
			alert("Please enter "+document.getElementById('disputeRaisedBy').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_disputeRaisedByVar').focus();
		 	return false;
		}
		
		var loggedOnDateVar = trim(document.getElementById('paraFrm_loggedOnDate').value);
		if(loggedOnDateVar!="")
		{			
			if(!validateDate('paraFrm_loggedOnDate',"loggedOnDateLabel")){
					document.getElementById('paraFrm_loggedOnDate').focus();
					return false;   	
   				}		
		}
				 
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='DisputeManagementSystem_save.action';
	  	document.getElementById('paraFrm').submit();		
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'DisputeManagementSystem_reset.action';
     	document.getElementById('paraFrm').submit();      
}

function backFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DisputeManagementSystem_back.action';
		document.getElementById('paraFrm').submit();
}

function editFun()
{
		document.getElementById("paraFrm").action="DisputeManagementSystem_edit.action";
	    document.getElementById("paraFrm").submit();
       return false;
}


function callSearchDisputeRaisedBy() 
{
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'DisputeManagementSystem_f9DisputeRaisedBy.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e)
	{
		alert("Error : "+e);
	}	
}	


function callSearchUnderAct() 
{
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'DisputeManagementSystem_f9UnderAct.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e)
	{
		alert("Error : "+e);
	}	
}

	
function callSearchCommitteeName() 
{
	try
	{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'DisputeManagementSystem_f9CommitteeName.action';
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
		document.getElementById("paraFrm").target = "_self";
		document.getElementById("paraFrm").action = 'DisputeManagementSystem_deleteRecord.action';
		document.getElementById("paraFrm").submit();
	}	
}
</script>
