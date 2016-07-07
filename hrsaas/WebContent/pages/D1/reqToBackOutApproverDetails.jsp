<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReqToBackOutApprover" validate="true" id="paraFrm" validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Request to Back Out Voucher Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- Approver Comments Section Begins -->
		<tr>
			<td  colspan="4">
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
							<td class="sortableTD" align="center"><%=++count%></td>
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
		
		<tr>
			<td colspan="4">
			<table width="100%" align="center" class="formbg">
				<tr>
					<td	colspan="4" align="left">
						Vendor Information
					</td>
				</tr>
				
				<tr>					
					<td class="formtext" width="20%">
						<label name="fromName"	id="fromName" ondblclick="callShowDiv(this);"> <%=label.get("fromName")%>	</label> :
					</td>
					<td height="22" width="30%">
						<s:hidden name="employeeID" />
						<s:property value="employeeToken" />
						<s:property value="fromName"/>
					</td>
					
					
					<td class="formtext" width="15%">
						<label name="toDate"	id="toDate" ondblclick="callShowDiv(this);"> <%=label.get("toDate")%>	</label> :
					</td>
					
					<td height="22" width="25%" align="left">
						<s:property value="toDate"/>
					</td>
				</tr>

				
				<tr>
					<td width="20%" class="formtext">
						<label name="vendorName"	id="vendorName" ondblclick="callShowDiv(this);"><%=label.get("vendorName")%></label> <font color="red">*</font> :
					</td>
					<td height="22" width="30%">
						<s:property value="vendorName"/>
					</td>
					<td width="15%">Status : </td>
					
					<td width="25%" align="left">
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:138" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
					</td>				
				</tr>
				
				
				<tr>
					<td width="20%" class="formtext">
						<label name="vendorNumber"	id="vendorNumber" ondblclick="callShowDiv(this);"><%=label.get("vendorNumber")%></label> <font color="red">*</font> :
					</td>
					<td height="22" width="30%">
						<s:property value="vendorNumber"/>
					</td>
					<td class="formtext" width="15%">&nbsp;</td>
					
					<td height="22" width="25%" align="left">&nbsp;</td>			
				</tr>
				
				
				<tr>
					<td width="20%" class="formtext">
						<label name="purchaseOrderNumber"	id="purchaseOrderNumber" ondblclick="callShowDiv(this);"><%=label.get("purchaseOrderNumber")%></label> <font color="red">*</font> :
					</td>
					<td height="22" width="30%">
						<s:property value="purchaseOrderNumber"/>
					</td>
					<td class="formtext" width="15%">&nbsp;</td>
					
					<td height="22" width="25%" align="left">&nbsp;</td>			
				</tr>
				
				
				<tr>
					<td class="formtext" width="20%">
						<label name="lineNumber"	id="lineNumber" ondblclick="callShowDiv(this);"> <%=label.get("lineNumber")%>	</label> :
					</td>
					<td height="22" width="30%">
						<s:property value="lineNumber"/>
					</td>
					
					<td class="formtext" width="15%">
						<label name="quantity"	id="quantity" ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>	</label> :
					</td>
					
					<td height="22" width="25%" align="left">
						<s:property value="quantity" />
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="formtext">
						<label name="voucherNumber"	id="voucherNumber" ondblclick="callShowDiv(this);"><%=label.get("voucherNumber")%></label> :
					</td>
					<td height="22" width="30%">
						<s:property value="voucherNumber"/>
					</td>
					<td class="formtext" width="15%">&nbsp;</td>
					
					<td height="22" width="25%" align="left">&nbsp;</td>				
				</tr>
								
				
				<tr>
					<td width="20%" class="formtext">
						<label name="reasonForRequest"	id="reasonForRequest" ondblclick="callShowDiv(this);"><%=label.get("reasonForRequest")%></label> <font color="red">*</font> :
					</td>
					<td height="22" colspan="3">
						<s:property value="reasonForRequest"/> 							
					</td>
				</tr>
				
				
				
				<tr>
					<td class="formtext" width="20%">
						<label name="rma"	id="rma" ondblclick="callShowDiv(this);"> <%=label.get("rma")%>	</label> :
					</td>
					<td height="22" width="30%">
						<s:property value="rma"/>
					</td>
					
					<td class="formtext" width="15%">
						<label value="airBillNumber"	id="airBillNumber" ondblclick="callShowDiv(this);"> <%=label.get("airBillNumber")%>	</label> :
					</td>
					
					<td height="22" width="25%" align="left">
						<s:property value="airBillNumber"/>
					</td>
				</tr>

				
				
				<tr>
					<td width="20%" class="formtext">
						<label name="didVendorIssueCreditMemo"	id="didVendorIssueCreditMemo" ondblclick="callShowDiv(this);"><%=label.get("didVendorIssueCreditMemo")%></label> :
					</td>
					<td height="22" width="30%">						
							<s:radio name="didVendorIssueCreditMemo"  value="%{didVendorIssueCreditMemo}"
						list="#{'ys':'Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', 'no':'No'}"
						onclick="setType(this);" disabled="true"></s:radio>
					</td>
					<td width="15%">&nbsp;</td>
					
					<td height="22" width="25%" align="left">&nbsp;</td>			
				</tr>

				
				
				<tr>
					<td width="20%" class="formtext">
						<label name="comments"	id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label> :
					</td>
					<td height="22" colspan="3">
						<s:property value="comments" /> 
					</td>
				</tr>	
			</table>
		  </td>
		</tr>
		
		<tr>
    	   <td colspan="4">
      			<table width="100%" cellspacing ="2" cellpadding="2" class="formbg" border="0">
   					<tr>
						<td width="20%">
							<b><label name="completedBy" id="completedBy" ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>	</label></b> :
						</td>
						<td width="30%">
							<s:hidden name="completedByID" />
							<s:property value="completedByName"/>
						</td>
					
						<td width="15%">
							<b><label name="completedOn"	id="completedOn" ondblclick="callShowDiv(this);"> <%=label.get("completedOn")%>	</label></b> :
						</td>
					
						<td width="25%" >
							<s:hidden name="completedOn" />
							<s:property value="completedOn"/>
						</td>
					</tr>
				</table>
		   </td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<s:hidden name="requestID" />
			<s:hidden name="creditMemoRadio" />
			<s:hidden name="status" />
			<s:hidden name="listTypeDetailPage" />
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
    	document.getElementById('paraFrm').action = 'ReqToBackOutApprover_approveApplication.action';
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
    	document.getElementById('paraFrm').action = 'ReqToBackOutApprover_rejectApplication.action';
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
    	document.getElementById('paraFrm').action = 'ReqToBackOutApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReqToBackOutApprover_closeApplication.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
</script>