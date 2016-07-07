<!-- Added by manish sakpal  on 1th March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ASIPOReconciliation" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">ASI PO Reconciliation Form</strong></td>
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

		<tr>
			<td>
				<table width="100%">
					<tr>
						<td width="100%">
							<b>NOTE</b> : This form was developed to process requests for ASI purchase orders that are either not ASI(Section I), or there is a discrepancy with an existing purchase order which 
									was created by GREAT VALLY (Section II). When you have completed this form, it will be automatically forwarded to Purchasing. 
									Purchasing will respond within 72 hours of your request (three working days)
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
		  </s:if>
		<!-- Approver Comments Section Ends -->
		
		<!-- Section I Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					 <td colspan="4"><b>Section I : Purchase Order Not On ASI</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="purchaseRequisition" id="purchaseRequisition"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseRequisition")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="purchaseRequisition" size="30" maxlength="20" onkeypress="return numbersOnly();" />
					</td>
					<td class="formtext" width="15%">
						<label name="datePartsWereReceived" id="datePartsWereReceived"
							ondblclick="callShowDiv(this);"> <%=label.get("datePartsWereReceived")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:textfield  size="30" maxlength="10" theme="simple" name="datePartsWereReceived" value="%{datePartsWereReceived}" onkeypress="return numbersWithHiphen();"/>
							<a	href="javascript:NewCal('paraFrm_datePartsWereReceived','DDMMYYYY');"> 
								<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage" height="16"
									align="absmiddle" width="16"> 
							</a> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="purchaseOrderNumberOnSlip" id="purchaseOrderNumberOnSlip"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseOrderNumberOnSlip")%>
						</label> :
					</td>
					<td height="22" colspan="3">						
						<s:textfield name="purchaseOrderNumberOnSlip" size="30" maxlength="20" onkeypress="return numbersOnly();" />
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="decisionOnePartNumber" id="decisionOnePartNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("decisionOnePartNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="decisionOnePartNumber" size="30" maxlength="20" onkeypress="return allCharacters();"/>
					</td>
					<td class="formtext" width="15%">
						<label name="vendorName" id="vendorName"
							ondblclick="callShowDiv(this);"> <%=label.get("vendorName")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="vendorName" size="30" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="comments" id="comments"
							ondblclick="callShowDiv(this);"> <%=label.get("comments")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textarea name="comments" cols="32" rows="4" onkeypress="return imposeMaxLength(event, this, 2000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','', '', '2000','2000');">
					</td>
					<td class="formtext" width="15%">
						Status :
					</td>
					<td height="22" width="25%">				
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
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
					 <td colspan="2"><b>Section II : Problems With Existing Purchase Order</b></td>
				</tr>
				<tr>
					<td class="formtext" width="16%">
						<label name="purchaseOrderNumber" id="purchaseOrderNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseOrderNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="purchaseOrderNumber" size="30" maxlength="20" onkeypress="return numbersWithHiphen();"/>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="16%">
						<label name="problemDescription" id="problemDescription"
							ondblclick="callShowDiv(this);"> <%=label.get("problemDescription")%>
						</label> :
					</td>
					<td height="22" colspan="3">						
						<s:textarea name="problemDescription" cols="100" rows="4"  onkeypress="return imposeMaxLength(event, this, 2000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_problemDescription','problemDescription','', '', '2000','2000');">		
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
					<td class="formtext" width="15%"><b>
						<label name="completedBy" id="completedBy"
							ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
						</label></b> :
					</td>
					<td height="22" width="25%">
						<s:hidden name="completedByID"/>						
						<s:hidden name="completedBy" />
						<s:property value="completedBy"/>
					</td>
					<td class="formtext" width="15%"><b>
						<label name="completedDate" id="completedDate"
							ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
						</label></b> :
					</td>
					<td height="22" width="25%">	
						<s:hidden name="completedDate"/>
						<s:property value="completedDate"/>													
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
	<s:hidden name="applicationID" />	
	<s:hidden name="status" />
	<s:hidden name="trackingNumber" />
	</table>
</s:form>

<script>
	function sendforapprovalFun()
{		
	try
	{
		var datePartsWereReceivedVar = document.getElementById('paraFrm_datePartsWereReceived').value;
		
		if(datePartsWereReceivedVar=="")
		{
			alert("Please enter "+document.getElementById('datePartsWereReceived').innerHTML.toLowerCase());
			
		  	document.getElementById('paraFrm_datePartsWereReceived').focus();
		 	return false;		
		}
		
		if(datePartsWereReceivedVar!="")
		{
			if(!validateDate('paraFrm_datePartsWereReceived', 'datePartsWereReceived')) {
				document.getElementById('paraFrm_datePartsWereReceived').focus();
				return false;
			}
		}
		
		var purchaseRequisitionVar = trim(document.getElementById('paraFrm_purchaseRequisition').value);
		if(purchaseRequisitionVar!=""){
			if(isNaN(document.getElementById('paraFrm_purchaseRequisition').value)){
				alert('Please enter purchase requisition # in numbers only');
				document.getElementById('paraFrm_purchaseRequisition').focus();
				return false;			
			}
		}
		
		var purchaseOrderNumberOnSlipVar = trim(document.getElementById('paraFrm_purchaseOrderNumberOnSlip').value);
		if(purchaseRequisitionVar!=""){
			if(isNaN(document.getElementById('paraFrm_purchaseOrderNumberOnSlip').value)){
				alert('Please enter PO number referenced on packing slip OR shipping label in numbers only');
				document.getElementById('paraFrm_purchaseOrderNumberOnSlip').focus();
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
    	document.getElementById('paraFrm').action='ASIPOReconciliation_sendForApprovalFunction.action';
		document.getElementById('paraFrm').submit();
	}		
}


function draftFun()
{	
	try
	{
		var datePartsWereReceivedVar = document.getElementById('paraFrm_datePartsWereReceived').value;
		
		if(datePartsWereReceivedVar=="")
		{
			alert("Please Enter Date Parts Were Received .");
		  	document.getElementById('paraFrm_datePartsWereReceived').focus();
		 	return false;		
		}
		
		if(datePartsWereReceivedVar!="")
		{
			if(!validateDate('paraFrm_datePartsWereReceived', 'datePartsWereReceived')) {
				document.getElementById('paraFrm_datePartsWereReceived').focus();
				return false;
			}
		}
		
		var purchaseRequisitionVar = trim(document.getElementById('paraFrm_purchaseRequisition').value);
		if(purchaseRequisitionVar!=""){
			if(isNaN(document.getElementById('paraFrm_purchaseRequisition').value)){
				alert('Please enter purchase requisition # in numbers only');
				document.getElementById('paraFrm_purchaseRequisition').focus();
				return false;			
			}
		}
		
		var purchaseOrderNumberOnSlipVar = trim(document.getElementById('paraFrm_purchaseOrderNumberOnSlip').value);
		if(purchaseOrderNumberOnSlipVar!=""){
			if(isNaN(document.getElementById('paraFrm_purchaseOrderNumberOnSlip').value)){
				alert('Please enter PO number referenced on packing slip OR shipping label in numbers only');
				document.getElementById('paraFrm_purchaseOrderNumberOnSlip').focus();
				return false;			
			}
		}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		document.getElementById('paraFrm_status').value = 'D';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='ASIPOReconciliation_draftFunction.action';
	  	document.getElementById('paraFrm').submit();		
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'ASIPOReconciliation_reset.action';
     	document.getElementById('paraFrm').submit();
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ASIPOReconciliation_back.action';
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
      	document.getElementById('paraFrm').action = 'ASIPOReconciliation_delete.action';
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
      	document.getElementById('paraFrm').action = 'ASIPOReconciliation_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}


function imposeMaxLength(Event, Object, MaxLen)
	{
	
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}	
</script>	