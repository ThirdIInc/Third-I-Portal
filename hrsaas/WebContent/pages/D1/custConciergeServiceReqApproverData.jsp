<!-- Added by Anantha lakshmi  created on 17th March 2011  -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CustConciergeServiceReqApprover" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Customer Concierge Program Service Alert Request Approver </strong></td>
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
		<s:if test="approverCommentFlag">
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
		  	<tr>
			  	<td>
					<table width="100%" class="formbg">
			  			<tr>
							<td colspan="2"><b>Approver Comments</b><font color="red">*</font></td>
							<td colspan="3"><s:textarea theme="simple" cols="70" rows="2"
								name="approverComments"  /></td>
						</tr>
				</table>
				</td>
			</tr>
			<!-- Approver List Details Start-->
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
								int count1 = 0;
							%>
							<s:iterator value="approverCommentList">
								<tr>
									<td class="sortableTD"><%=++count1%></td>
									<td class="sortableTD"><s:property value="apprName" /></td>
									<td class="sortableTD"><s:property value="apprComments" /></td>
									<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
									<td class="sortableTD"><s:property value="apprStatus" /></td>
								</tr>
							</s:iterator>
							<%
								if(count1 == 0) {
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
			<!-- Approver List Details end  -->		
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
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				
				<!--  -->
					
				
				<!--  -->
				<tr>
					<td colspan="4" ><b>Complete the following information for Shipping Purposes</b></td>
				</tr>	
				
				<tr>
					<td class="formtext" width="15%">
						<label name="Name"	id="Name" ondblclick="callShowDiv(this);"> <%=label.get("Name")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						 <s:property value="CustName"  /> 
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="15%">
						<label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%></label>:</td>
					<td colspan="3">
						 <s:property value="streetAddr" /> 
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="CustCity"	id="CustCity" ondblclick="callShowDiv(this);"> <%=label.get("CustCity")%></label>:</td>
					<td colspan="3">
						 <s:property value="custCity"  /> 
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="CustState"	id="CustState" ondblclick="callShowDiv(this);"> <%=label.get("CustState")%></label>:</td>
					<td colspan="3">
						 <s:property value="custState" /> 
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="CustPinCode"	id="CustPinCode" ondblclick="callShowDiv(this);"> <%=label.get("CustPinCode")%></label>:</td>
					<td colspan="3">
						 <s:property value="custPinCode" /> 
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="15%">
						<label name="MngrName"	id="MngrName" ondblclick="callShowDiv(this);"> <%=label.get("MngrName")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						<s:property value="mngrName"  /> 
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="20%">
						<label name="NumAlertPads"	id="NumAlertPads" ondblclick="callShowDiv(this);"> <%=label.get("NumAlertPads")%></label><font color="red">*</font>:</td>
					<td colspan="3">
						 <s:property value="numAlertPads" /> 
					</td>
				</tr>
				
				
				
				
				<td class="formtext" width="15%">
						Status :
					</td>
					<td height="22" width="25%">				
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:150" list="#{'':'New Application','D':'Draft','P':'Pending','A':'Approved','X':'Approved','R':'Rejected','Z':'Rejected','B':'Sent Back'}"/>
					</td>
					
			</table>
			</td>
		</tr>
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

function approveFun()
{
	var apprComment=document.getElementById("paraFrm_approverComments").value;
	if(apprComment=="")
	{
		alert("Please Enter Approver Comment");
		return false;
	}
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
    	document.getElementById('paraFrm').action = 'CustConciergeServiceReqApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var apprComment=document.getElementById("paraFrm_approverComments").value;
	if(apprComment=="")
	{
		alert("Please Enter Approver Comment");
		return false;
	}
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
    	document.getElementById('paraFrm').action = 'CustConciergeServiceReqApprover_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	
	var apprComment=document.getElementById("paraFrm_approverComments").value;
	if(apprComment=="")
	{
		alert("Please Enter Approver Comment");
		return false;
	}
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm_status').value = 'B';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'CustConciergeServiceReqApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}



function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CustConciergeServiceReqApprover_back.action';
		document.getElementById('paraFrm').submit();
}

function printFun() {	
	window.print();
}
</script>
