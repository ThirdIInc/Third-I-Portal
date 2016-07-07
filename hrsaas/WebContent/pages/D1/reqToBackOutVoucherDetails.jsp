<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReqToBackOutVoucher" validate="true" id="paraFrm" validate="true" theme="simple">

<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td colspan="4">
			  <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
				<tr>				
					<td>
						<strong class="text_head">
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />
						</strong>
					</td>
					<td width="100%" class="txt"><strong>Request To Back Out Voucher</strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>	
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
		<s:if test="approverCommentsFlag">
		<tr>
			<td colspan="4">
				<table width="100%" class="formbg">
					<tr>
					<td	colspan="5" align="left">
						<b>Approver Comments</b>
					</td>
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
		 </s:if>
		  <!-- Approver Comments Section Ends -->

		<tr>
			<td colspan="4">
			<table width="100%" align="center" class="formbg">
				<tr>
					<td	colspan="4" align="left">
						<b>Vendor Information</b>
					</td>
				</tr>
				
				<tr>					
					<td width="20%">
						<label name="fromName"	id="fromName" ondblclick="callShowDiv(this);"> <%=label.get("fromName")%>	</label> :
					</td>
					<td colspan="3">
						<s:hidden name="employeeID" />
						<s:textfield name="employeeToken" size="30" readonly="true" />
						<s:textfield name="fromName" size="91" readonly="true" />
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<label name="vendorName"	id="vendorName" ondblclick="callShowDiv(this);"><%=label.get("vendorName")%></label> <font color="red">*</font> :
					</td>
					<td width="30%">
						<s:textfield name="vendorName" size="30" />
					</td>
					
					<td width="15%">
						<label name="toDate"	id="toDate" ondblclick="callShowDiv(this);"> <%=label.get("toDate")%>	</label> :
					</td>
					
					<td width="25%" align="left">
						<s:textfield name="toDate" size="30" readonly="true" />
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<label name="vendorNumber"	id="vendorNumber" ondblclick="callShowDiv(this);"><%=label.get("vendorNumber")%></label> <font color="red">*</font> :
					</td>
					<td width="30%">
						<s:textfield name="vendorNumber" size="30" />
					</td>
					
					<td width="15%">Status : </td>
					
					<td width="25%" align="left">
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:154" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
					</td>				
				</tr>
				
				<tr>
					<td width="20%" >
						<label name="purchaseOrderNumber"	id="purchaseOrderNumber" ondblclick="callShowDiv(this);"><%=label.get("purchaseOrderNumber")%></label> <font color="red">*</font> :
					</td>
					<td width="30%">
						<s:textfield name="purchaseOrderNumber" maxlength="25" size="30" />
					</td>
					<td width="15%">&nbsp;</td>
					
					<td width="25%" align="left">&nbsp;</td>			
				</tr>
				
				<tr>
					<td width="20%">
						<label name="lineNumber"	id="lineNumber" ondblclick="callShowDiv(this);"> <%=label.get("lineNumber")%>	</label> :
					</td>
					<td width="30%">
						<s:textfield name="lineNumber" size="30" onkeypress="return numbersOnly();" />
					</td>
					
					<td width="15%">
						<label name="quantity"	id="quantity" ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>	</label> :
					</td>
					
					<td width="25%" align="left">
						<s:textfield name="quantity" size="30" maxlength="10" onkeypress="return numbersOnly();" />
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<label name="voucherNumber"	id="voucherNumber" ondblclick="callShowDiv(this);"><%=label.get("voucherNumber")%></label> :
					</td>
					<td width="30%">
						<s:textfield name="voucherNumber" maxlength="20" size="30" />
					</td>
					<td width="15%">&nbsp;</td>
					
					<td width="25%" align="left">&nbsp;</td>				
				</tr>
				
				<tr>
					<td width="20%">
						<label name="reasonForRequest"	id="reasonForRequest" ondblclick="callShowDiv(this);"><%=label.get("reasonForRequest")%></label> <font color="red">*</font> :
					</td>
					<td colspan="3">
						<s:textarea name="reasonForRequest" cols="50" rows="5" onkeypress="return imposeMaxLength(event, this, 1000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_reasonForRequest','reasonForRequest','', '', '1000','1000');"> 
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<label name="rma"	id="rma" ondblclick="callShowDiv(this);"> <%=label.get("rma")%>	</label> :
					</td>
					<td width="30%">
						<s:textfield name="rma" size="30"  />
					</td>
					
					<td width="15%">
						<label name="airBillNumber"	id="airBillNumber" ondblclick="callShowDiv(this);"> <%=label.get("airBillNumber")%>	</label> :
					</td>
					
					<td width="25%" align="left">
						<s:textfield name="airBillNumber" size="30" maxlength="15" onkeypress="return numbersOnly();" />
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<label name="didVendorIssueCreditMemo"	id="didVendorIssueCreditMemo" ondblclick="callShowDiv(this);"><%=label.get("didVendorIssueCreditMemo")%></label> :
					</td>
					<td width="30%">						
							<s:radio name="didVendorIssueCreditMemo"  value="%{didVendorIssueCreditMemo}"
						list="#{'ys':'Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', 'no':'No'}"
						onclick="setType(this);"></s:radio>
					</td>
					<td width="15%">&nbsp;</td>
					
					<td width="25%" align="left">&nbsp;</td>			
				</tr>
				
				<tr>
					<td width="20%">
						<label name="comments"	id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label> :
					</td>
					<td colspan="3">
						<s:textarea name="comments" cols="50" rows="5" onkeypress="return imposeMaxLength(event, this, 1000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','', '', '1000','1000');">
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

function setType(id){	
	var setvalue=document.getElementById('paraFrm_creditMemoRadio').value = id.value;	
}	

function sendforapprovalFun()
{		
		try
		{
			var vendorNameVar = document.getElementById('paraFrm_vendorName').value;
		var vendorNumberVar = document.getElementById('paraFrm_vendorNumber').value;
		var reasonForRequestVar = document.getElementById('paraFrm_reasonForRequest').value;
		var purchaseOrderNumberVar = document.getElementById('paraFrm_purchaseOrderNumber').value;
		
		if(vendorNameVar=="")
		{
			alert("Please enter "+document.getElementById('vendorName').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_vendorName').focus();
		 	return false;		
		}
		
		if(vendorNumberVar=="")
		{
			alert("Please enter "+document.getElementById('vendorNumber').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_vendorNumber').focus();
		 	return false;		
		}
		
		if(purchaseOrderNumberVar=="")
		{
			alert("Please enter "+document.getElementById('purchaseOrderNumber').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_purchaseOrderNumber').focus();
		 	return false;		
		}
		
		if(reasonForRequestVar=="")
		{
			alert("Please enter "+document.getElementById('reasonForRequest').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_reasonForRequest').focus();
		 	return false;		
		}
		
		var lineNumberVar = trim(document.getElementById('paraFrm_lineNumber').value);
		if(lineNumberVar!=""){
			if(isNaN(document.getElementById('paraFrm_lineNumber').value)){
				alert('Please enter line number in numbers only');
				document.getElementById('paraFrm_lineNumber').focus();
				return false;			
			}
		}
		
		var quantityVar = trim(document.getElementById('paraFrm_quantity').value);
		if(quantityVar!="")
		{
			if(isNaN(document.getElementById('paraFrm_quantity').value)){
				alert('Please enter quantity in numbers only');
				document.getElementById('paraFrm_quantity').focus();
				return false;			
			}
		}
		
		var airBillNumberVar = trim(document.getElementById('paraFrm_airBillNumber').value);
		if(airBillNumberVar!="")
		{
			if(isNaN(document.getElementById('paraFrm_airBillNumber').value)){
				alert('Please enter air bill number in numbers only');
				document.getElementById('paraFrm_airBillNumber').focus();
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
    	document.getElementById('paraFrm').action='ReqToBackOutVoucher_sendForApprovalFunction.action';
		document.getElementById('paraFrm').submit();
	}		
}
function draftFun()
{	
	try
	{
		document.getElementById('paraFrm_status').value = 'D';	
		var vendorNameVar = document.getElementById('paraFrm_vendorName').value;
		var vendorNumberVar = document.getElementById('paraFrm_vendorNumber').value;
		var reasonForRequestVar = document.getElementById('paraFrm_reasonForRequest').value;
		var purchaseOrderNumberVar = document.getElementById('paraFrm_purchaseOrderNumber').value;
		
		if(vendorNameVar=="")
		{
			alert("Please enter "+document.getElementById('vendorName').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_vendorName').focus();
		 	return false;		
		}
		
		if(vendorNumberVar=="")
		{
			alert("Please enter "+document.getElementById('vendorNumber').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_vendorNumber').focus();
		 	return false;		
		}
		
		if(purchaseOrderNumberVar=="")
		{
			alert("Please enter "+document.getElementById('purchaseOrderNumber').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_purchaseOrderNumber').focus();
		 	return false;		
		}
		
		if(reasonForRequestVar=="")
		{
			alert("Please enter "+document.getElementById('reasonForRequest').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_reasonForRequest').focus();
		 	return false;		
		}
		
		var lineNumberVar = trim(document.getElementById('paraFrm_lineNumber').value);
		if(lineNumberVar!=""){
			if(isNaN(document.getElementById('paraFrm_lineNumber').value)){
				alert('Please enter line number in numbers only');
				document.getElementById('paraFrm_lineNumber').focus();
				return false;			
			}
		}
		
		var quantityVar = trim(document.getElementById('paraFrm_quantity').value);
		if(quantityVar!="")
		{
			if(isNaN(document.getElementById('paraFrm_quantity').value)){
				alert('Please enter quantity in numbers only');
				document.getElementById('paraFrm_quantity').focus();
				return false;			
			}
		}
		
		var airBillNumberVar = trim(document.getElementById('paraFrm_airBillNumber').value);
		if(airBillNumberVar!="")
		{
			if(isNaN(document.getElementById('paraFrm_airBillNumber').value)){
				alert('Please enter quantity in numbers only');
				document.getElementById('paraFrm_airBillNumber').focus();
				return false;			
			}
		}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
				
		// For disabaling the button after clicking once	
		for (var i = 0; i < document.all.length; i++) 
		{
			if(document.all[i].id == 'save') 
			{
				//alert(document.all[i]);
				document.all[i].disabled=true;
			}
		}
				
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='ReqToBackOutVoucher_draftFunction.action';
	  	document.getElementById('paraFrm').submit();		
		  
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'ReqToBackOutVoucher_reset.action';
     	document.getElementById('paraFrm').submit();
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReqToBackOutVoucher_back.action';
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
      	document.getElementById('paraFrm').action = 'ReqToBackOutVoucher_delete.action';
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
      	document.getElementById('paraFrm').action = 'ReqToBackOutVoucher_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}

function imposeMaxLength(Event, Object, MaxLen)
	{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
</script>