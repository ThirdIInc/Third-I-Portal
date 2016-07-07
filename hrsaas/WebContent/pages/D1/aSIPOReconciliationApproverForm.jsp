<!-- Added by manish sakpal  on 8th March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ASIPOReconciliationApprover" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">ASI PO Reconciliation Approval Form</strong></td>
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
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><b>Approver Comments</b></td>
						<td colspan="3"><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="approverComments" /></td>
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
					 <td colspan="4"><b>Section I : Purchase Order Not On ASI</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="purchaseRequisition" id="purchaseRequisition"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseRequisition")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:property value="purchaseRequisition"/>
					</td>
					<td class="formtext" width="15%">
						<label name="datePartsWereReceived" id="datePartsWereReceived"
							ondblclick="callShowDiv(this);"> <%=label.get("datePartsWereReceived")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:property  value="datePartsWereReceived"/>
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="purchaseOrderNumberOnSlip" id="purchaseOrderNumberOnSlip"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseOrderNumberOnSlip")%>
						</label> :
					</td>
					<td height="22" colspan="3">
						<s:property  value="purchaseOrderNumberOnSlip"/>						
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="decisionOnePartNumber" id="decisionOnePartNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("decisionOnePartNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:property  value="decisionOnePartNumber"/>	
					</td>
					<td class="formtext" width="15%">
						<label name="vendorName" id="vendorName"
							ondblclick="callShowDiv(this);"> <%=label.get("vendorName")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">						
						<s:hidden name="vendorNumber"/>
						<s:property  value="vendorName"/>	
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="comments" id="comments"
							ondblclick="callShowDiv(this);"> <%=label.get("comments")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:property value="comments"/>
					</td>
					<td class="formtext" width="15%">
						Status :
					</td>
					<td height="22" width="25%">						
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:145" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
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
					 <td colspan="4"><b>Section II : Problems With Existing Purchase Order</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="purchaseOrderNumber" id="purchaseOrderNumber"
							ondblclick="callShowDiv(this);"> <%=label.get("purchaseOrderNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:property  value="purchaseOrderNumber"/>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="problemDescription" id="problemDescription"
							ondblclick="callShowDiv(this);"> <%=label.get("problemDescription")%>
						</label> :
					</td>
					<td height="22" colspan="3">						
						<s:property  value="problemDescription"/>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="completedBy" id="completedBy"
							ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
						</label> :
					</td>
					<td height="22" width="25%">
						<s:hidden name="completedByID"/>						
						<s:property  value="completedBy"/>
					</td>
					<td class="formtext" width="15%">
						<label name="completedDate" id="completedDate"
							ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
						</label> :
					</td>
					<td height="22" width="25%">	
						<s:property  value="completedDate"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Section II Ends -->
		
		
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
	<s:hidden name="purchaseID" />	
	<s:hidden name="status" />
	<s:hidden name="trackingNumber" />
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
    	document.getElementById('paraFrm').action = 'ASIPOReconciliationApprover_approveApplication.action';
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
    	document.getElementById('paraFrm').action = 'ASIPOReconciliationApprover_rejectApplication.action';
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
    	document.getElementById('paraFrm').action = 'ASIPOReconciliationApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ASIPOReconciliationApprover_closeApplication.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
</script>	