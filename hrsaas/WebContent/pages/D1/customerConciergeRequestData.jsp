<!-- Added by Anantha lakshmi  -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CustomerConciergeServiceRequest" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Customer Concierge Program Service Alert Request </strong></td>
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
		  <s:else>
		  
		  </s:else>
		<!-- Approver Comments Section Ends -->
		
		<tr>
						<td >
							<table width="100%">
								<tr>
									<td width="100%">
										<b>NOTE</b> : In support of the Customer Concierge Program,You are to complete a form and leave at all applicable client locations after service has been performed .Please utilize  this request form to re-order the "Service Alert" stickys notes
									</td>
								</tr>
							</table>
						</td>
					</tr>
		<!-- Employee Information Section Begins -->
		<!--  -->
		<s:if test="draftListFlag">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4" ><b>Complete the following information for Shipping Purposes</b></td>
				</tr>	
				<tr>
					<td class="formtext" width="15%">
						<label name="Name"	id="Name" ondblclick="callShowDiv(this);"> <%=label.get("Name")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						 <s:textfield name="custName" size="30"  maxlength="50"/> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%></label>:</td>
					<td colspan="3">
						 <s:textfield name="streetAddr" size="30" maxlength="20" /> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="CustCity"	id="CustCity" ondblclick="callShowDiv(this);"> <%=label.get("CustCity")%></label>:</td>
					<td colspan="3">
						 <s:textfield name="custCity" size="30"  maxlength="20"/> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="CustState"	id="CustState" ondblclick="callShowDiv(this);"> <%=label.get("CustState")%></label>:</td>
					<td colspan="3">
						 <s:textfield name="custState" size="30" maxlength="20"  /> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="CustPinCode"	id="CustPinCode" ondblclick="callShowDiv(this);"> <%=label.get("CustPinCode")%></label>:</td>
					<td colspan="3">
						 <s:textfield name="custPinCode"  size="30"  maxlength="10" onkeypress="return numbersOnly();" /> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="15%">
						<label name="MngrName"	id="MngrName" ondblclick="callShowDiv(this);"> <%=label.get("MngrName")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						 <s:textfield name="mngrName" size="30"   maxlength="50" /> 
					</td>
				</tr>
				<tr>
					<td class="formtext" width="20%">
						<label name="NumAlertPads"	id="NumAlertPads" ondblclick="callShowDiv(this);"> <%=label.get("NumAlertPads")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						 <s:textfield name="numAlertPads" size="30"    maxlength="1" onkeypress="return numbersOnly();" /> 
					</td>
				</tr>
				
				
				<td class="formtext" width="15%">Status :</td>
					<td height="22" width="25%">				
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
					</td>
					
			</table>
			</td>
		</tr>
		</s:if>
			
		
		<!-- DRAFT LIST END -->
		
		<!-- PENDING LIST START setPendingListFlag  -->
		<s:if test="pendingListFlag">
			<tr>
				<td>
					<table width="100%" align="right" class="formbg">
						<tr>
							<td colspan="4" ><b>Complete the following information for Shipping Purposes</b></td>
						</tr>	
						<tr>
							<td class="formtext" width="15%">
								<label name="Name"	id="Name" ondblclick="callShowDiv(this);"> <%=label.get("Name")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%></label>:</td>
							<td colspan="3">
								<s:property  value="streetAddr" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustCity"	id="CustCity" ondblclick="callShowDiv(this);"> <%=label.get("CustCity")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custCity" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustState"	id="CustState" ondblclick="callShowDiv(this);"> <%=label.get("CustState")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custState"/> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="CustPinCode"	id="CustPinCode" ondblclick="callShowDiv(this);"> <%=label.get("CustPinCode")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custPinCode"  /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="MngrName"	id="MngrName" ondblclick="callShowDiv(this);"> <%=label.get("MngrName")%></label>:</td>
							<td colspan="3">
								 <s:property  value="mngrName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="20%"><label name="NumAlertPads"	id="NumAlertPads" ondblclick="callShowDiv(this);"> <%=label.get("NumAlertPads")%></label>:</td>
							<td colspan="3">
								 <s:property  value="numAlertPads"  /> 
							</td>
						</tr>
						
						
						<td class="formtext" width="15%">Status :</td>
							<td height="22" width="25%">				
								<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
							</td>
							
					</table>
				</td>
			</tr>
		</s:if>	
		<!-- PENDING LIST END -->
		<!-- START APPROVEWD LIST  -->
		
			<s:if test="approvedListFlag">
			
			<tr>
				<td>
					<table width="100%" align="right" class="formbg">
						<tr>
							<td colspan="4" ><b>Complete the following information for Shipping Purposes</b></td>
						</tr>	
						<tr>
							<td class="formtext" width="15%">
								<label name="Name"	id="Name" ondblclick="callShowDiv(this);"> <%=label.get("Name")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%></label>:</td>
							<td colspan="3">
								<s:property  value="streetAddr" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustCity"	id="CustCity" ondblclick="callShowDiv(this);"> <%=label.get("CustCity")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custCity" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustState"	id="CustState" ondblclick="callShowDiv(this);"> <%=label.get("CustState")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custState"/> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="CustPinCode"	id="CustPinCode" ondblclick="callShowDiv(this);"> <%=label.get("CustPinCode")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custPinCode"  /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="MngrName"	id="MngrName" ondblclick="callShowDiv(this);"> <%=label.get("MngrName")%></label>:</td>
							<td colspan="3">
								 <s:property  value="mngrName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="20%"><label name="NumAlertPads"	id="NumAlertPads" ondblclick="callShowDiv(this);"> <%=label.get("NumAlertPads")%></label>:</td>
							<td colspan="3">
								 <s:property  value="numAlertPads"  /> 
							</td>
						</tr>
						
						
						<td class="formtext" width="15%">Status :</td>
							<td height="22" width="25%">				
								<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
							</td>
							
					</table>
				</td>
			
			
			</tr>
				
			</s:if>
		
		
		<!-- END APPROVED LIST -->
		<!-- REJECTED LIST START -->
			<s:if test="rejectedListFlag">
				<tr>
				<td>
					<table width="100%" align="right" class="formbg">
						<tr>
							<td colspan="4" ><b>Complete the following information for Shipping Purposes</b></td>
						</tr>	
						<tr>
							<td class="formtext" width="15%">
								<label name="Name"	id="Name" ondblclick="callShowDiv(this);"> <%=label.get("Name")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%></label>:</td>
							<td colspan="3">
								<s:property  value="streetAddr" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustCity"	id="CustCity" ondblclick="callShowDiv(this);"> <%=label.get("CustCity")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custCity" /> 
							</td>
						</tr>
						
						<tr>
							<td class="formtext" width="15%"><label name="CustState"	id="CustState" ondblclick="callShowDiv(this);"> <%=label.get("CustState")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custState"/> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="CustPinCode"	id="CustPinCode" ondblclick="callShowDiv(this);"> <%=label.get("CustPinCode")%></label>:</td>
							<td colspan="3">
								 <s:property  value="custPinCode"  /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label name="MngrName"	id="MngrName" ondblclick="callShowDiv(this);"> <%=label.get("MngrName")%></label>:</td>
							<td colspan="3">
								 <s:property  value="mngrName" /> 
							</td>
						</tr>
						<tr>
							<td class="formtext" width="20%"><label name="NumAlertPads"	id="NumAlertPads" ondblclick="callShowDiv(this);"> <%=label.get("NumAlertPads")%></label>:</td>
							<td colspan="3">
								 <s:property  value="numAlertPads"  /> 
							</td>
						</tr>
					
						
						<td class="formtext" width="15%">Status :</td>
							<td height="22" width="25%">				
								<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
							</td>
							
					</table>
				</td>
			
			
			</tr>
			</s:if>
		<!--REJECTED LIST END  -->
		<!-- Employee Information Section Ends -->
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
			<td>
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
function draftFun(){
	try{
		var name=document.getElementById("paraFrm_custName").value;
		var mngrName=document.getElementById("paraFrm_mngrName").value;
		var maxNumOfserPads =document.getElementById("paraFrm_numAlertPads").value;
		if(name==""){
			alert("Please Enter Name");
			return false;
		}
		if(mngrName==""){
			alert("Please Enter Manager Name");
			return false;
		}
		if(maxNumOfserPads==""){
			alert("Please Enter Number Of Service Alert Pads Needed,Maximum Order of 2(two)");
			return false;
		}
	}catch(e){
		alert(e);
	}
	
	
	var con=confirm('Do you really want to draft this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'D';	
		document.getElementById('paraFrm').target = "_self";     	
		document.getElementById('paraFrm').action = 'CustomerConciergeServiceRequest_draftFunction.action';
   		document.getElementById('paraFrm').submit();
	}
		
	
}

function sendforapprovalFun()
{	
	try{
		var name=document.getElementById("paraFrm_custName").value;
		var mngrName=document.getElementById("paraFrm_mngrName").value;
		var maxNumOfserPads =document.getElementById("paraFrm_numAlertPads").value;
		if(name==""){
			alert("Please enter Name");
			document.getElementById('paraFrm_custName').focus();
			return false;
		}
		if(mngrName==""){
			alert("Please enter Manager Name");
			document.getElementById("paraFrm_mngrName").focus();
			return false;
		}
		if(maxNumOfserPads==""){
			alert("Please enter Number Of Service Alert Pads Needed,Maximum Order of 2(two)");
			document.getElementById("paraFrm_numAlertPads").focus();
			return false;
		}
	}catch(e){
		alert(e);
	}
	 var con=confirm('Do you really want to  send this application for approval ?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'P';	
		document.getElementById('paraFrm').target = "_self";     	
		document.getElementById('paraFrm').action = 'CustomerConciergeServiceRequest_sendForApprovalFunction.action';
   		document.getElementById('paraFrm').submit();
	}	
	
}

function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'CustomerConciergeServiceRequest_reset.action';
     	document.getElementById('paraFrm').submit();
}
function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CustomerConciergeServiceRequest_back.action';
		document.getElementById('paraFrm').submit();
}
function deleteFun() 
{
	 var con=confirm('Do you want to delete this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CustomerConciergeServiceRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
}
function printFun() {	
	window.print();
}
function numbersonly1(myfield)
	{
	
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789-/").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
</script>
